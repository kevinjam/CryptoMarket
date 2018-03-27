package com.thinkdevs.cryptomarket.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.thinkdevs.cryptomarket.R
import com.thinkdevs.cryptomarket.constant.COIN_DETAILS
import com.thinkdevs.cryptomarket.controller.MoreActivity
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
		val id_more = itemView!!.findViewById<ConstraintLayout>(R.id.id_more)
		
		
		fun bindview(model: CryptoModel, context: Context) {
			coinName.text = model.name
			marketcoin.text = "$ ${model.market_cap_usd}"
			priceCoin.text = "Price: $${model.price_usd}"
			changeCoin.text ="Change: ${model.percent_change_24h} %"
			
			if (model.percent_change_24h.startsWith("-")){
				if (model.percent_change_24h.startsWith("-1") || model.percent_change_24h.contentEquals("-2")){
					println("Less than 5")
					changeCoin.setTextColor(Color.RED)
					
				}else{
					println("Greater Than 5")
					changeCoin.setTextColor(Color.BLUE)
					
				}
			}else{
				changeCoin.setTextColor(Color.YELLOW)
				
			}
			
		
			
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
					val resouldId = context.resources.getIdentifier("ic_ada", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "NEO" -> {
					val resouldId = context.resources.getIdentifier("ic_neo", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				
				model.symbol == "XLM" -> {
					val resouldId = context.resources.getIdentifier("ic_xml", "drawable", context.packageName)
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
				
				model.symbol == "BLOCK" -> {
					val resouldId = context.resources.getIdentifier("ic_block", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				
				model.symbol == "BNB" -> {
					val resouldId = context.resources.getIdentifier("ic_bnb", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				
				model.symbol == "BTCD" -> {
					val resouldId = context.resources.getIdentifier("ic_btcd", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				
				model.symbol == "BTG" -> {
					val resouldId = context.resources.getIdentifier("ic_btg", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				
				model.symbol == "CVC" -> {
					val resouldId = context.resources.getIdentifier("ic_cvc", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				
				model.symbol == "DASH" -> {
					val resouldId = context.resources.getIdentifier("ic_dash", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "DGB" -> {
					val resouldId = context.resources.getIdentifier("ic_dgb", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "DOGE" -> {
					val resouldId = context.resources.getIdentifier("ic_doge", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "EDG" -> {
					val resouldId = context.resources.getIdentifier("ic_edg", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "EMC2" -> {
					val resouldId = context.resources.getIdentifier("ic_emc2", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "ETC" -> {
					val resouldId = context.resources.getIdentifier("ic_etc", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				
				model.symbol == "ETHOS" -> {
					val resouldId = context.resources.getIdentifier("ic_ethos", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "ETP" -> {
					val resouldId = context.resources.getIdentifier("ic_etp", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "FUN" -> {
					val resouldId = context.resources.getIdentifier("ic_fun", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "GAME" -> {
					val resouldId = context.resources.getIdentifier("ic_game", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "GAS" -> {
					val resouldId = context.resources.getIdentifier("ic_gas", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "GBYTE" -> {
					val resouldId = context.resources.getIdentifier("ic_gbyte", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "GNO" -> {
					val resouldId = context.resources.getIdentifier("ic_gno", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "GNT" -> {
					val resouldId = context.resources.getIdentifier("ic_gnt", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "GRS" -> {
					val resouldId = context.resources.getIdentifier("ic_grs", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				
				model.symbol == "HSR" -> {
					val resouldId = context.resources.getIdentifier("ic_hsr", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "ICN" -> {
					val resouldId = context.resources.getIdentifier("ic_icn", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "KMD" -> {
					val resouldId = context.resources.getIdentifier("ic_kmd", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "KNC" -> {
					val resouldId = context.resources.getIdentifier("ic_knc", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "LSK" -> {
					val resouldId = context.resources.getIdentifier("ic_lsk", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "MAID" -> {
					val resouldId = context.resources.getIdentifier("ic_maid", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "MCO" -> {
					val resouldId = context.resources.getIdentifier("ic_mco", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "MNX" -> {
					val resouldId = context.resources.getIdentifier("ic_mnx", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "MONA" -> {
					val resouldId = context.resources.getIdentifier("ic_mona", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "MTL" -> {
					val resouldId = context.resources.getIdentifier("ic_mtl", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "NAV" -> {
					val resouldId = context.resources.getIdentifier("ic_nav", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "NXS" -> {
					val resouldId = context.resources.getIdentifier("ic_nxs", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "NXT" -> {
					val resouldId = context.resources.getIdentifier("ic_nxt", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "OMG" -> {
					val resouldId = context.resources.getIdentifier("ic_omg", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "PAY" -> {
					val resouldId = context.resources.getIdentifier("ic_pay", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "PIVX" -> {
					val resouldId = context.resources.getIdentifier("ic_pivx", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				
				model.symbol == "POT" -> {
					val resouldId = context.resources.getIdentifier("ic_pot", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "POWER" -> {
					val resouldId = context.resources.getIdentifier("ic_power", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "PPC" -> {
					val resouldId = context.resources.getIdentifier("ic_ppc", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "PPT" -> {
					val resouldId = context.resources.getIdentifier("ic_ppt", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "PURA" -> {
					val resouldId = context.resources.getIdentifier("ic_pura", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "QASH" -> {
					val resouldId = context.resources.getIdentifier("ic_qash", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "QTUM" -> {
					val resouldId = context.resources.getIdentifier("ic_qtum", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "RDN" -> {
					val resouldId = context.resources.getIdentifier("ic_rdn", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "REP" -> {
					val resouldId = context.resources.getIdentifier("ic_rep", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				
				model.symbol == "SALT" -> {
					val resouldId = context.resources.getIdentifier("ic_salt", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "SAN" -> {
					val resouldId = context.resources.getIdentifier("ic_san", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "SNGLS" -> {
					val resouldId = context.resources.getIdentifier("ic_sngls", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "SNT" -> {
					val resouldId = context.resources.getIdentifier("ic_snt", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "START" -> {
					val resouldId = context.resources.getIdentifier("ic_start", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "STORJ" -> {
					val resouldId = context.resources.getIdentifier("ic_storj", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "SYS" -> {
					val resouldId = context.resources.getIdentifier("ic_sys", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "TRX" -> {
					val resouldId = context.resources.getIdentifier("ic_trx", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "UBQ" -> {
					val resouldId = context.resources.getIdentifier("ic_ubq", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				
				model.symbol == "USDT" -> {
					val resouldId = context.resources.getIdentifier("ic_usdt", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				
				model.symbol == "VEN" -> {
					val resouldId = context.resources.getIdentifier("ic_ven", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "VTC" -> {
					val resouldId = context.resources.getIdentifier("ic_vtc", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "WTC" -> {
					val resouldId = context.resources.getIdentifier("ic_wtc", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "XEM" -> {
					val resouldId = context.resources.getIdentifier("ic_xem", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "XLM" -> {
					val resouldId = context.resources.getIdentifier("ic_xlm", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "XMR" -> {
					val resouldId = context.resources.getIdentifier("ic_xmr", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "XRP" -> {
					val resouldId = context.resources.getIdentifier("ic_xrp", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "XUC" -> {
					val resouldId = context.resources.getIdentifier("ic_xuc", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "XVG" -> {
					val resouldId = context.resources.getIdentifier("ic_xvg", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "XZC" -> {
					val resouldId = context.resources.getIdentifier("ic_xzc", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				
				model.symbol == "ZEC" -> {
					val resouldId = context.resources.getIdentifier("ic_zec", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "ZEN" -> {
					val resouldId = context.resources.getIdentifier("ic_zen", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
				model.symbol == "ZRX" -> {
					val resouldId = context.resources.getIdentifier("ic_zrx", "drawable", context.packageName)
					coinImage.setImageResource(resouldId)
				}
			}
			
			id_more.setOnClickListener {
			
				val intent=Intent(context, MoreActivity::class.java)
				intent.putExtra(COIN_DETAILS, model)
				context.startActivity(intent)
			}
			
		}
		
	}
}