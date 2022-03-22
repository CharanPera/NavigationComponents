package com.example.weatherapp.viewmodel

import android.content.ContentValues.TAG

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.model.Weather
import com.example.weatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


@HiltViewModel
class WeatherViewModel
@Inject
constructor(private val repository: WeatherRepository) : ViewModel() {

    val progressLivedata = MutableLiveData<Boolean>()

    val _resp = MutableLiveData<Weather>()

    val date1 = MutableLiveData<String>()
    val date2 = MutableLiveData<String>()
    val date3 = MutableLiveData<String>()

    val fc1 = MutableLiveData<String>()
    val fc2 = MutableLiveData<String>()
    val fc3 = MutableLiveData<String>()

    val viewdata = MutableLiveData<Boolean>()


    val weatherResp: LiveData<Weather>
        get() = _resp


    private fun date(int: Int): String {
        val date = Calendar.getInstance()
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        date.add(Calendar.DATE, int)
        return sdf.format(date.timeInMillis)
    }

    val responseReceived = MutableLiveData<Boolean>()

//  A ViewModelScope is defined for each ViewModel in our app.
//  Any coroutine launched in this scope is automatically canceled if the ViewModel is cleared


    public fun getWeather(city: String) = viewModelScope.launch {
        //Progress bar sets true until response is successs
        progressLivedata.value = true

        //Views are visibility is set false (Weather app attributes)
        viewdata.value = false

        repository.getWeather(city).let { response ->

            //let takes the object it is invoked upon as the parameter and
            // returns the result of the lambda expression.

            Log.d(TAG, "Entered in view Model:${response.message()}")
            progressLivedata.value = false

            viewdata.value = true

            Log.d(
                TAG,
                "FC:${response.body()?.forecast?.get(0)?.wind} / ${response.body()?.forecast?.get(0)?.temperature} "
            )

            if (response.isSuccessful) {
                responseReceived.value = true
                Log.d(TAG, "Response succesful:${response.message()}")
                Log.d(TAG, "Temperature:${response.body()?.temperature}")
                date1.value = date(1)
                Log.d(TAG, "Date: ${date1}")
                date2.value = date(2)
                date3.value = date(3)

                fc1.value = "${response.body()?.forecast?.get(0)?.temperature} / ${
                    response.body()?.forecast?.get(0)?.wind
                }"
                fc2.value = "${response.body()?.forecast?.get(1)?.temperature} / ${
                    response.body()?.forecast?.get(1)?.wind
                }"
                fc3.value = "${response.body()?.forecast?.get(2)?.temperature} / ${
                    response.body()?.forecast?.get(2)?.wind
                }"

                _resp.postValue(response.body())

            } else {
                responseReceived.value = false
                Log.d(TAG, "getWeatherResponce:${response.message()}")
            }

        }
    }
}