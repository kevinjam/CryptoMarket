package com.thinkdevs.cryptomarket.model

import com.thinkdevs.cryptomarket.utils.CryptCurrency
import java.io.Serializable

/**
 * Created by kevinjanvier on 19/03/2018.
 */
data class CryptoModel(
		val id: String,
		var name: String,
		var symbol: String,
		val rank: String,
		val price_usd: String,
		val price_btc: String,
		val h_volume_usd: String,
		val market_cap_usd: String,
		val available_supply: String,
		val total_supply: String,
		val max_supply: String,
		val percent_change_1h: String,
		val percent_change_24h: String,
		val percent_change_7d: String,
		val last_updated: String
) : Serializable {
	
	constructor(name: String, symbol: String) : this(name, symbol, "", "", "", ""
			, "", "", "", "", "", "", ""
			, "", "") {
	}
	
	override fun toString(): String {
		return "$name ($symbol)"
	}
	
}

data class Crypto(
		val id: String,
		var name: String,
		var symbol: String,
		val rank: String,
		val price_usd: String,
		val price_btc: String,
		val h_volume_usd: String,
		val market_cap_usd: String,
		val available_supply: String,
		val total_supply: String,
		val max_supply: String,
		val percent_change_1h: String,
		val percent_change_24h: String,
		val percent_change_7d: String,
		val last_updated: String
){
	override fun toString(): String {
		return CryptCurrency.toStringGson().toJson(this)
	}
}


class GlobalMarket(
		var total_market_cap_usd: Double,
		var total_24h_volume_usd: Double,
		var bitcoin_percentage_of_market_cap: Double,
		var active_currencies: Int,
		var active_assets: Int,
		var active_markets: Int,
		var last_updated: Long
)


class Articles(var source: SourceModel, var author: String, var title: String,
			   var description: String, var url: String, var urlToImage: String, var publishedAt: String) : Serializable

class SourceModel(var id: String, var name: String) : Serializable

