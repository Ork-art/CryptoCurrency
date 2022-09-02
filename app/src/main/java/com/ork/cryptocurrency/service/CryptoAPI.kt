package com.ork.cryptocurrency.service

import com.ork.cryptocurrency.model.CryptoModel
import io.reactivex.Observable
import retrofit2.http.GET

interface CryptoAPI {


    //https://api.nomics.com/v1/currencies/
    //ticker?key=601a17efe0e1cb5d2b88d7e6dfdf9a76b24abaa9

    @GET("ticker?key=601a17efe0e1cb5d2b88d7e6dfdf9a76b24abaa9")
    fun getData(): Observable<List<CryptoModel>>

}