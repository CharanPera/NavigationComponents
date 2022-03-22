package com.example.explicitintent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Activity2 : AppCompatActivity() {

    companion object {
        const val NAME = "NAME"
        const val AGE = "AGE"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

//        val myIntent = getIntent()
//        val name = myIntent.getShortExtra(NAME)

        val name = intent.getStringExtra(NAME)
        val age = intent.getStringExtra(AGE)

        findViewById<TextView>(R.id.item_textViewName).text = "Hi! " + name
        findViewById<TextView>(R.id.item_textViewAge).text = "Your age is " + age


    }
}