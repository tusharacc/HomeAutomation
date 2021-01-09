package com.tusharsaurabh.myhome

import android.util.Log
import java.io.Serializable

class SensorObject  (name:String, id:String,type: String, mode: ArrayList<String>) : Serializable {
    val sensor = HashMap<String,Any>()
    init {
        sensor["name"] = name
        sensor["id"] = id
        sensor["type"] = type
        sensor["mode"] = mode
    }

    fun getName(): String {
        return this.sensor["name"].toString()
    }

    fun getType(): String{
        return this.sensor["type"].toString()
    }

    fun getMode(): ArrayList<String>{
        this.sensor["mode"]!!::class.simpleName?.let { Log.e("test1238", it) }
        Log.e("test12347",this.sensor["mode"].toString())
        return this.sensor["mode"] as ArrayList<String>
    }
}