package com.example.weatherapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application()



//All apps that use Hilt must contain an Application class that is annotated with @HiltAndroidApp.

//@HiltAndroidApp triggers Hilt's code generation,
// including a base class for your application that serves as the application-level dependency container.

//Add application name  in manifest file