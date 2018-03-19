package com.thinkdevs.cryptomarket.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.thinkdevs.cryptomarket.R
import com.thinkdevs.cryptomarket.model.CryptoModel

/**
 * Created by kevinjanvier on 19/03/2018.
 */
class BitCoinAdapter(var context: Context, var list: ArrayList<CryptoModel>) : RecyclerView.Adapter<BitCoinAdapter.ViewHolder>() {
	override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
		
		val view = LayoutInflater.from(parent!!.context).inflate(R.layout.item_coin, parent, false)
		return ViewHolder(view)
	}
	
	override fun getItemCount(): Int {
		return list.count()
	}
	
	override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
		holder!!.bindview(list[position], context)
	}
	
	inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
		
		val coinName = itemView!!.findViewById<TextView>(R.id.coinName)
		val marketcoin = itemView!!.findViewById<TextView>(R.id.marketcoin)
		val priceCoin = itemView!!.findViewById<TextView>(R.id.priceCoin)
		val changeCoin = itemView!!.findViewById<TextView>(R.id.changeCoin)
		val coinImage = itemView!!.findViewById<ImageView>(R.id.coinImage)
		
		fun bindview(model: CryptoModel, context: Context) {
			coinName.text = model.name
			marketcoin.text = "$ ${model.market_cap_usd}"
			priceCoin.text = "$ ${model.price_usd}"
			changeCoin.text ="${model.percent_change_24h} %"
			
			when {
				model.symbol == "BTC" -> {
					val resouldId = context.resources.getIdentifier("ic_bitcoin", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "ETH" -> {
					val resouldId = context.resources.getIdentifier("ic_ethereum", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "XRP" -> {
					val resouldId = context.resources.getIdentifier("ic_ripple", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "BCH" -> {
					val resouldId = context.resources.getIdentifier("ic_bitcoin_cash", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "LTC" -> {
					val resouldId = context.resources.getIdentifier("ic_litecoin", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "ADA" -> {
					val resouldId = context.resources.getIdentifier("ic_ethereum", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "NEO" -> {
					val resouldId = context.resources.getIdentifier("ic_neo", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				
				model.symbol == "XLM" -> {
					val resouldId = context.resources.getIdentifier("ic_neo", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "EOS" -> {
					val resouldId = context.resources.getIdentifier("ic_eos", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "ADA" -> {
					val resouldId = context.resources.getIdentifier("ic_ada", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "ADX" -> {
					val resouldId = context.resources.getIdentifier("ic_adx", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "STEEM" -> {
					val resouldId = context.resources.getIdentifier("ic_steemd", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				
				model.symbol == "FCT" -> {
					val resouldId = context.resources.getIdentifier("ic_fct", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "R" -> {
					val resouldId = context.resources.getIdentifier("ic_steemd", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "SC" -> {
					val resouldId = context.resources.getIdentifier("ic_sc", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
					
				}
				model.symbol == "SKY" -> {
					val resouldId = context.resources.getIdentifier("ic_sky", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				
				model.symbol == "BNT" -> {
					val resouldId = context.resources.getIdentifier("ic_bnt", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				
				model.symbol == "BCN" -> {
					val resouldId = context.resources.getIdentifier("ic_bcn", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "BTS" -> {
					val resouldId = context.resources.getIdentifier("ic_bts", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "CNX" -> {
					val resouldId = context.resources.getIdentifier("ic_cnx", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "WAVES" -> {
					val resouldId = context.resources.getIdentifier("ic_waves", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "DCR" -> {
					val resouldId = context.resources.getIdentifier("ic_dcr", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "BTM" -> {
					val resouldId = context.resources.getIdentifier("ic_btm", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "BAT" -> {
					val resouldId = context.resources.getIdentifier("ic_bat", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				
			}
			
		}
		
	}
}