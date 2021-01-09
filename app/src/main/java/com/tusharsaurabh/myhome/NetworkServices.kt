package com.tusharsaurabh.myhome

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val IFTTT_URL = "https://maker.ifttt.com/trigger/"

private val iftttRetrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(IFTTT_URL)
    .build()

interface IftttService {
    @GET("{event}/with/key/bUSli4cRd920cAJadVLV1h")
    fun triggerService(@Path("event") event: String):
            Call<String>

}

object IFTTT{
    val iftttService : IftttService by lazy {
        iftttRetrofit.create(IftttService::class.java)
    }
}