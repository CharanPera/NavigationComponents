package com.example.notes_app

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_edit.*

class EditFragment : Fragment() {

    lateinit var MainActivity: AddNotesViewModel
    private var _id: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.fragment_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editingNotes()
        settingViewModel()
        updatenotes()
    }

    override fun onResume() {
        super.onResume()
        if (requireActivity() is MainActivity)
            (requireActivity() as MainActivity).hideFloatingButton()
    }

    private fun updatenotes() {
        btnEdit.setOnClickListener {
            noteViewModel.editNotes(
                _id,
                editTitle.text.toString(),
                editDescription.text.toString(),
                editTag.text.toString(),
                colorPosition
            )
            Navigation.findNavController(requireActivity(), R.id.container).popBackStack()
            Toast.makeText(requireActivity(), "Notes Updated Succesfully", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun editingNotes() {
        arguments?.let {
            val safeArgs = EditFragmentArgs.fromBundle(it)
            val note = safeArgs.myArg
            _id = note?.id!!
            Log.d("CHARAN", "EDITING NOTE ID -> ${note.id}")
            editTitle.setText(note.title.toString())
            editDescription.setText(note.description.toString())
            editTag.setText(note.tag.toString())
            colorPosition = note.color!!
        }
    }

    var colorPosition = 0

    private fun settingViewModel() {
        noteViewModel = ViewModelProvider(
            this,
            defaultViewModelProviderFactory
        ).get(AddNotesViewModel::class.java)
    }

}
