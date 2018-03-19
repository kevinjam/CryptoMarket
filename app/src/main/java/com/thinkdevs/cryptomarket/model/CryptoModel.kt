package com.thinkdevs.cryptomarket.model

import java.io.Serializable

/**
 * Created by kevinjanvier on 19/03/2018.
 */
data class CryptoModel(
		val id:String,
		val name:String,
		val symbol:String,
		val rank:String,
		val price_usd:String,
		val price_btc:String,
		val market_cap_usd:String,
		val available_supply:String,
		val total_supply:String,
		val max_supply:String,
		val percent_change_1h:String,
		val percent_change_24h:String,
		val percent_change_7d:String,
		val last_updated:String
		
		
		
		):Serializable

