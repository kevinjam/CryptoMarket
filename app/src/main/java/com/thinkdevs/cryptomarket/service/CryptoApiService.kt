package com.thinkdevs.cryptomarket.service

import com.thinkdevs.cryptomarket.model.Crypto
import com.thinkdevs.cryptomarket.model.CryptoModel
import com.thinkdevs.cryptomarket.model.GlobalMarket
import com.thinkdevs.cryptomarket.model.NewsModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Created by kevinjanvier on 19/03/2018.
 */
interface CryptoApiService {
	@GET("ticker")
	fun getbitcoin(): Observable<ArrayList<CryptoModel>>
	
	
	@GET("global")
	fun getglobal(): Observable<GlobalMarket>
	
	@GET("everything?sources=crypto-coins-news&apiKey=2f93de87387d400f9bd7d3028aef6213")
	fun getnews(): Observable<NewsModel>
	
	@GET("ticker/Ethereum/?convert=EUR")
	fun get_ticket(): Observable<ArrayList<CryptoModel>>
	
	@GET("ticker/?convert=EUR&limit=1000")
	fun get_full_list(): Observable<ArrayList<CryptoModel>>
	
	@GET("ticker/Ethereum")
	fun get_ethereum(): Observable<ArrayList<Crypto>>
	
	@GET("ticker/Litecoin")
	fun get_litecoin(): Observable<ArrayList<Crypto>>
	
	@GET("ticker/Ripple")
	fun get_ripple(): Observable<ArrayList<Crypto>>
}