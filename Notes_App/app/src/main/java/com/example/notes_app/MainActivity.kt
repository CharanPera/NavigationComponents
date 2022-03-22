package com.example.notes_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = findNavController(R.id.container)
        Toast.makeText(this, "Click + button to add Notes", Toast.LENGTH_SHORT).show()
        Toast.makeText(this, "Long press on Items to delete Notes", Toast.LENGTH_SHORT).show()

        fab_.setOnClickListener(){
            floatingButtonOnClicked()
        }
    }

    private fun floatingButtonOnClicked() {
        navController.navigate(R.id.action_listFragment_to_addFragment)
        hideFloatingButton()
    }

    fun showFloatingButton(){
        fab_.show()
        fab_.visibility = View.VISIBLE
    }
    fun hideFloatingButton(){
        fab_.hide()
    }

    override fun onStart() {
        super.onStart()
        Log.d("Charan", "Onstart")
    }
}