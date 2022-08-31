package com.ork.cryptocurrency.service

import com.ork.cryptocurrency.model.CryptoModel
import io.reactivex.rxjava3.core.Observer
import retrofit2.Call
import retrofit2.http.GET

interface CryptoAPI {


    //https://api.nomics.com/v1/currencies/
    //ticker?key=601a17efe0e1cb5d2b88d7e6dfdf9a76b24abaa9

    @GET("ticker?key=601a17efe0e1cb5d2b88d7e6dfdf9a76b24abaa9")
    fun getData(): Call<List<CryptoModel>>
}