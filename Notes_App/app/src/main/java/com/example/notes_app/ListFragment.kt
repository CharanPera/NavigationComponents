package com.example.notes_app

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment(), NotesAdapter.Interaction {

    private lateinit var noteViewModel: AddNotesViewModel

    private var allnotes: MutableList<Notes> = arrayListOf()
    private var noteAdapter: NotesAdapter = NotesAdapter(allnotes, this)
    private var notesLiveData: LiveData<List<Notes>>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initRecyclerview()
        initObservers()
    }

    private fun initRecyclerview() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@ListFragment.context)
            adapter = noteAdapter
        }
    }

    private fun initViewModel() {

        noteViewModel =
            ViewModelProvider(
                this,
                defaultViewModelProviderFactory
            ).get(AddNotesViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        if (requireActivity() is MainActivity)
            (requireActivity() as MainActivity).showFloatingButton()
    }

    private fun initObservers() {

        notesLiveData = noteViewModel.selectedItems()
        Log.d("LOGCharan", "ListObserver")
        notesLiveData!!.observe(viewLifecycleOwner, Observer { listOfNotes ->
            listOfNotes?.let {
                allnotes = it.toMutableList()
                Log.d("CHARANPERA4", "${allnotes}")
                noteAdapter.setData(allnotes)
            }
        })
    }

    override fun onSelectedItem(position: Int, item: Notes) {
        val navDirection = ListFragmentDirections.actionListFragmentToEditFragment(item)
        findNavController().navigate(navDirection)
    }

    override fun onDeleteItem(item: Int) {
        alertdialog(item)
    }


    fun alertdialog(item: Int){
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(R.string.dialogTitle)
        builder.setMessage(R.string.dialogText)
        builder.setPositiveButton(R.string.dialogYes) { dialogInterface, which ->
            noteViewModel.deleteNote(item)
            Toast.makeText(requireActivity(), "Notes deleted", Toast.LENGTH_SHORT).show()


        }
        builder.setNegativeButton(R.string.dialogNo) { dialogInterface, which ->
            Toast.makeText(requireContext(), "clicked NO", Toast.LENGTH_LONG).show()
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }
}
