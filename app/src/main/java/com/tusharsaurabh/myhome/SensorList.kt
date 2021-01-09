package com.tusharsaurabh.myhome

import java.io.Serializable

class SensorList : Serializable {

    val sensors: MutableList<SensorObject> = ArrayList()

    init{

    }

    fun getSensors():Array<SensorObject>{
        return sensors.toTypedArray()
    }

    fun addSensor(obj:SensorObject){
        this.sensors.add(obj)
    }

    fun getSensorMode(name:String):Array<String>{
        for (sensor in this.sensors) {
            if (sensor.getName() == name){
                return sensor.getMode().toTypedArray()
            }

        }

        return arrayOf("Sensor Mode Not Found")
    }

    fun isEmpty(): Boolean {
        if (this.sensors.size > 0){
            return false
        } else {
            return true
        }
    }

    fun getSensorObjectForName(name:String): SensorObject? {
        for (sensor in this.sensors){
            if (sensor.getName() == name){
                return sensor
            }
        }
        return null
    }
}