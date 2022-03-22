package com.example.notes_app

import android.icu.text.CaseMap
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.realm.Realm
import io.realm.RealmResults
import io.realm.kotlin.where

class AddNotesViewModel : ViewModel() {


    val list = ArrayList<Notes>()
    var mutableSelectedItem: MutableLiveData<List<Notes>> = MutableLiveData()

    //    val currentNote : LiveData<Boolean> = MutableLiveData()
    var liveData = MutableLiveData<Boolean>()

    //val selectedItem: LiveData<Notes> get() = MutableLiveData<>()
    fun saveNotes(title: String, des: String, tag: String, color: Int) {

        val realm = Realm.getDefaultInstance()

        val checktitleNull = title.isBlank()
        val checkDesNUll = des.isBlank()

        val currentIdnum: Number? = realm.where(Notes::class.java).max("id")
        val nextId: Int
        nextId = if (currentIdnum == null)
            1
        else
            currentIdnum.toInt() + 1
        val notes = Notes(
            nextId,
            title,
            des,
            tag,
            color
        )
        if (checktitleNull || checkDesNUll) {
            Log.d("CHARAN", "IT is NULL")
            liveData.value = false
        } else {
            realm.executeTransactionAsync { realm ->
                realm.copyToRealm(notes)
            }


            liveData.postValue(true)
        }
    }

    fun editNotes(id: Int, title: String, des: String, tag: String, color: Int) {
        val realm = Realm.getDefaultInstance()

        val notes = Notes(id, title, des, tag, color)
        realm.executeTransactionAsync { realm ->
            realm.copyToRealmOrUpdate(notes)
        }
    }

    fun deleteNote(id: Int) {
        val realm = Realm.getDefaultInstance()


        realm.executeTransactionAsync { r: Realm ->
            var item = r.where(Notes::class.java).equalTo("id", id).findFirst()
            Log.d("CharanPera", "${id}")
            item!!.deleteFromRealm()
            item = null
        }
    }

    fun selectedItems(): LiveData<List<Notes>> {
        Log.d("LOGCharan", "selectedItemsCalled")
        val result = getNotesList()
        result.addChangeListener { results ->
            val resultArray: List<Notes> = results.toList()
            mutableSelectedItem.postValue(resultArray)
            Log.d("CHARANKUMAR", "$resultArray")
        }
        Log.d("LOGCharan", "mutable data, ${mutableSelectedItem.value}")
        return mutableSelectedItem
    }

    private fun getNotesList(): RealmResults<Notes> {
        val realm = Realm.getDefaultInstance()
        return realm.where(Notes::class.java).findAllAsync()
    }
}
