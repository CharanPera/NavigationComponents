package com.example.notes_app

import android.app.Application
import android.util.Log
import io.realm.Realm
import io.realm.Realm.init
import io.realm.RealmConfiguration

class NotesApp: Application() {

    override fun onCreate() {
        super.onCreate()
        init(this)
        Log.d("LOGCharan", "RealmInit")
        val configuration = RealmConfiguration.Builder()
            .name("myNotesapp.realm")
            .deleteRealmIfMigrationNeeded()
            .allowWritesOnUiThread(true)
            .build()

        Realm.getInstance(configuration)
       }
}