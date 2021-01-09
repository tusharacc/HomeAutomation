package com.tusharsaurabh.myhome

import android.content.Context
import android.util.Log
import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.*

private lateinit var mqttClient: MqttAndroidClient

const val SOLACE_CLIENT_USER_NAME = "nrmsbkar"
const val SOLACE_CLIENT_PASSWORD = "NLcFxH4NOo5d"
const val SOLACE_MQTT_HOST = "tcp://postman.cloudmqtt.com:13661"
const val SOLACE_CONNECTION_TIMEOUT = 3
const val SOLACE_CONNECTION_KEEP_ALIVE_INTERVAL = 60
const val SOLACE_CONNECTION_CLEAN_SESSION = true
const val SOLACE_CONNECTION_RECONNECT = true

class mqtt(context: Context?) {

    companion object{
        val TAG = "myHomeMqTT"
    }

    lateinit var mqttClient: MqttAndroidClient
    lateinit var mqttAndroidClient: MqttAndroidClient
    val serverURI = SOLACE_MQTT_HOST
    private val clientId: String = MqttClient.generateClientId()

    fun setCallback(callback: MqttCallbackExtended?) {
        mqttAndroidClient.setCallback(callback)
    }

    init {
        mqttAndroidClient = MqttAndroidClient(context, serverURI, clientId)
        mqttAndroidClient.setCallback(object : MqttCallbackExtended {
            override fun connectComplete(b: Boolean, s: String) {
                Log.e(TAG, s)
            }

            override fun connectionLost(throwable: Throwable) {}

            @Throws(Exception::class)
            override fun messageArrived(
                topic: String,
                mqttMessage: MqttMessage
            ) {
                Log.e(TAG, mqttMessage.toString())
            }

            override fun deliveryComplete(iMqttDeliveryToken: IMqttDeliveryToken) {}
        })
        connect()
    }

    private fun connect() {

        val mqttConnectOptions = MqttConnectOptions()
        mqttConnectOptions.isAutomaticReconnect = SOLACE_CONNECTION_RECONNECT
        mqttConnectOptions.isCleanSession = SOLACE_CONNECTION_CLEAN_SESSION
        mqttConnectOptions.userName = SOLACE_CLIENT_USER_NAME
        mqttConnectOptions.password = SOLACE_CLIENT_PASSWORD.toCharArray()
        mqttConnectOptions.connectionTimeout = SOLACE_CONNECTION_TIMEOUT
        mqttConnectOptions.keepAliveInterval = SOLACE_CONNECTION_KEEP_ALIVE_INTERVAL
        try {
            mqttAndroidClient.connect(mqttConnectOptions, null, object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken) {
                    //val disconnectedBufferOptions = DisconnectedBufferOptions()
                    //disconnectedBufferOptions.isBufferEnabled = true
                    //disconnectedBufferOptions.bufferSize = 100
                    //disconnectedBufferOptions.isPersistBuffer = false
                    //disconnectedBufferOptions.isDeleteOldestMessages = false
                    //mqttAndroidClient.setBufferOpts(disconnectedBufferOptions)
                }

                override fun onFailure(
                    asyncActionToken: IMqttToken,
                    exception: Throwable
                ) {
                    Log.w(TAG, "Failed to connect to: $serverURI ; $exception")
                }
            })
        } catch (ex: MqttException) {
            ex.printStackTrace()
        }
    }

    fun publish(topic: String, msg: String, qos: Int = 0, retained: Boolean = false) {
        try {
            val message = MqttMessage()
            message.payload = msg.toByteArray()
            message.qos = qos
            message.isRetained = retained
            mqttAndroidClient.publish(topic, message.payload, qos, false)
            Log.d(TAG, "Message published to topic `$topic`: $msg")
        } catch (e: MqttException) {
            Log.d(TAG, "Error Publishing to $topic: " + e.message)
            e.printStackTrace()
        }
    }

    fun isConnected() : Boolean {
        return mqttAndroidClient.isConnected
    }

    fun destroy() {
        mqttAndroidClient.unregisterResources()
        mqttAndroidClient.disconnect()
    }
}