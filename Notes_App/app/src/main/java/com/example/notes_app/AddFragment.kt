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
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.fragment_add.*
import kotlin.math.log

class AddFragment: Fragment() {

    companion object{
        fun newInstance() = AddFragment()

    }
    var mSelectedColorId :Int=0
    private lateinit var viewModel: AddNotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddNotesViewModel::class.java)
        viewModel.liveData.observe(viewLifecycleOwner, {
            if(!it){
                Toast.makeText(requireContext(), "Please enter Title and Description", Toast.LENGTH_SHORT).show()
            }else{
                Navigation.findNavController(requireActivity(),R.id.container).popBackStack()
                Toast.makeText(requireActivity(), "Notes added succesfully", Toast.LENGTH_SHORT).show()
            }
        })
        listner()
    }

    private fun listner() {
        btnAdd.setOnClickListener{
            viewModel.saveNotes(addTitle.text.toString(), addDescription.text.toString(), addTag.text.toString(),mSelectedColorId )

        }
        button_group.addOnButtonCheckedListener{
                _, checkedId, isChecked ->

            if(!isChecked){
                mSelectedColorId = 0
            }
            else {
                if (checkedId == R.id.button_teal)
                    mSelectedColorId = 1
                else if (checkedId == R.id.button_purple)
                    mSelectedColorId = 2
                else if (checkedId == R.id.button_red)
                    mSelectedColorId = 3
                else if (checkedId == R.id.button_green)
                    mSelectedColorId = 4
                else if (checkedId == R.id.button_yellow)
                    mSelectedColorId = 5
                else
                    mSelectedColorId = 6
            }

        }

        }

    }

