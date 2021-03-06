package com.thinkdevs.cryptomarket.model

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.thinkdevs.cryptomarket.constant.BASE_URL
import com.thinkdevs.cryptomarket.constant.NEWS_API
import com.thinkdevs.cryptomarket.service.CryptoApiService
import com.thinkdevs.cryptomarket.utils.SelfSigningClientBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by kevinjanvier on 19/03/2018.
 */
class Helper {
	companion object {
		fun mainUrl(): CryptoApiService {
			val retrofit = Retrofit.Builder()
					.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
					.addConverterFactory(GsonConverterFactory.create())
					.baseUrl(BASE_URL)
					.build()

			return retrofit.create(CryptoApiService::class.java)
		}
		
		val gson = GsonBuilder().setLenient().create()
		
		
		fun newsUrl(context: Context):CryptoApiService{
			val retrofit = Retrofit.Builder()
					.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
					.addConverterFactory(GsonConverterFactory.create(gson))
					.baseUrl(NEWS_API)
					.build()
			
			return retrofit.create(CryptoApiService::class.java)
		}
		
		
		
		
		
	}
	
	
}