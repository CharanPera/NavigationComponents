package com.example.explicitintent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.item_button)

        button.setOnClickListener{
            val name = findViewById<EditText>(R.id.edittext_Name).text.toString()
            val age = findViewById<EditText>(R.id.edittext_age).text.toString()

            val intent  = Intent(this@MainActivity, Activity2::class.java)
            intent.putExtra(Activity2.NAME, name)
            intent.putExtra(Activity2.AGE, age)


            startActivity(intent)

        }



    }
}