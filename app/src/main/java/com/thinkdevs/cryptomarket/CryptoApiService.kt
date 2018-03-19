package com.thinkdevs.cryptomarket

import com.thinkdevs.cryptomarket.model.CryptoModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit



/**
 * Created by kevinjanvier on 19/03/2018.
 */
interface CryptoApiService {
	@GET("ticker")
	
	fun getbitcoin(): Observable<ArrayList<CryptoModel>>
	
	
	
}