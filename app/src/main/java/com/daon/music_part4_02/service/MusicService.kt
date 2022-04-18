package com.daon.music_part4_02.service

import retrofit2.Call
import retrofit2.http.GET

interface MusicService {

    @GET("/v3/6dbd937e-a2fa-4e28-a98a-f3bbaf847f1a")
    fun listMusics() : Call<MusicDto>
}