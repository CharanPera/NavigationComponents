package com.example.weatherapp

import android.content.Context
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter
import androidx.lifecycle.Observer
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.databinding.ActivityMainBinding.inflate
import com.example.weatherapp.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private  val viewModel: WeatherViewModel by viewModels()
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = inflate(layoutInflater)
        setContentView(binding.root)

        binding.data = viewModel
        binding.lifecycleOwner = this

        binding.buttonSearch.setOnClickListener {
            var city = binding.cityEd.text.toString()
            city = city.trim()
            it.hideKeyboard()
            if (city == "") {
                Toast.makeText(this, "Please enter City name", Toast.LENGTH_SHORT).show()
            } else {
                binding.tvCity.text = city
                viewModel.getWeather("${city}")
            }
        }

        viewModel.responseReceived.observe(this, Observer {
            if(it){
                //binding.parentLayout.visibility = View.VISIBLE
                Toast.makeText(this, "Weather Data received", Toast.LENGTH_SHORT).show()
            }
            else
                Toast.makeText(this, "Server Down! Please try again later", Toast.LENGTH_SHORT).show()
        })
    }

    fun View.hideKeyboard() {
        val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }
}


