package com.example.notes_app

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable

open class Notes(
    @PrimaryKey()
    var id:Int?= null,
    var title: String = "",
    var description: String = "",
    var tag:String = "",
    var color:Int? = null
): RealmObject(), Serializable
