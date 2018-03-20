package com.thinkdevs.cryptomarket.controller

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.thinkdevs.cryptomarket.R
import com.thinkdevs.cryptomarket.constant.COIN_DETAILS
import com.thinkdevs.cryptomarket.model.CryptoModel
import kotlinx.android.synthetic.main.activity_more.*
import kotlinx.android.synthetic.main.toolbar.view.*
import java.text.SimpleDateFormat
import java.util.*

class MoreActivity : AppCompatActivity() {
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_more)
		
		val model = intent.getSerializableExtra(COIN_DETAILS) as CryptoModel
		println("moddddd $model")
		coinbar.coinName.text = model.name
		coinpriceUpdate.text = "$ ${model.price_usd}"
		price_usd.text = "$ ${model.price_usd}"
		price_btc.text = "฿ ${model.price_btc}"
		hours24.text = "$ ${model.h_volume_usd}"
		market_cap.text = "$ ${model.market_cap_usd}"
		available_supply.text = model.available_supply
		total_supply.text = model.total_supply
		max_supply.text = model.max_supply
		change1.text = model.percent_change_1h
		change24.text = model.percent_change_24h
		change7.text = model.percent_change_7d
		
		val unix_seconds:Long =model.last_updated.toLong()
		val date =Date(unix_seconds*1000L)
		//Simple
		val jdx = SimpleDateFormat("dd MMM yyyy hh:mm:ss zzz")
		jdx.timeZone = TimeZone.getTimeZone("GMT-4")
		val mydate = jdx.format(date)
		latest_Updates.text = mydate
		
		
		coinbar.nav_share.setOnClickListener {
			share(model)
		}
		
		coinbar.nav_nack.setOnClickListener {
			onBackPressed()
		}
		
		
		
		when {
			model.symbol == "BTC" -> {
				val resouldId = resources.getIdentifier("ic_bitcoin", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "ETH" -> {
				val resouldId = resources.getIdentifier("ic_ethereum", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "XRP" -> {
				val resouldId = resources.getIdentifier("ic_ripple", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "BCH" -> {
				val resouldId = resources.getIdentifier("ic_bitcoin_cash", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "LTC" -> {
				val resouldId = resources.getIdentifier("ic_litecoin", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "ADA" -> {
				val resouldId = resources.getIdentifier("ic_ethereum", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "NEO" -> {
				val resouldId = resources.getIdentifier("ic_neo", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			
			model.symbol == "XLM" -> {
				val resouldId = resources.getIdentifier("ic_neo", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "EOS" -> {
				val resouldId = resources.getIdentifier("ic_eos", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "ADA" -> {
				val resouldId = resources.getIdentifier("ic_ada", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "ADX" -> {
				val resouldId = resources.getIdentifier("ic_adx", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "STEEM" -> {
				val resouldId = resources.getIdentifier("ic_steemd", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			
			model.symbol == "FCT" -> {
				val resouldId = resources.getIdentifier("ic_fct", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "R" -> {
				val resouldId = resources.getIdentifier("ic_steemd", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "SC" -> {
				val resouldId = resources.getIdentifier("ic_sc", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
				
			}
			model.symbol == "SKY" -> {
				val resouldId = resources.getIdentifier("ic_sky", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			
			model.symbol == "BNT" -> {
				val resouldId = resources.getIdentifier("ic_bnt", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			
			model.symbol == "BCN" -> {
				val resouldId = resources.getIdentifier("ic_bcn", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "BTS" -> {
				val resouldId = resources.getIdentifier("ic_bts", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "CNX" -> {
				val resouldId = resources.getIdentifier("ic_cnx", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "WAVES" -> {
				val resouldId = resources.getIdentifier("ic_waves", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "DCR" -> {
				val resouldId = resources.getIdentifier("ic_dcr", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "BTM" -> {
				val resouldId = resources.getIdentifier("ic_btm", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "BAT" -> {
				val resouldId = resources.getIdentifier("ic_bat", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			
			model.symbol == "BLOCK" -> {
				val resouldId = resources.getIdentifier("ic_block", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			
			model.symbol == "BNB" -> {
				val resouldId = resources.getIdentifier("ic_bnb", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			
			model.symbol == "BTCD" -> {
				val resouldId = resources.getIdentifier("ic_btcd", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			
			model.symbol == "BTG" -> {
				val resouldId = resources.getIdentifier("ic_btg", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			
			model.symbol == "CVC" -> {
				val resouldId = resources.getIdentifier("ic_cvc", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			
			model.symbol == "DASH" -> {
				val resouldId = resources.getIdentifier("ic_dash", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "DGB" -> {
				val resouldId = resources.getIdentifier("ic_dgb", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "DOGE" -> {
				val resouldId = resources.getIdentifier("ic_doge", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "EDG" -> {
				val resouldId = resources.getIdentifier("ic_edg", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "EMC2" -> {
				val resouldId = resources.getIdentifier("ic_emc2", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "ETC" -> {
				val resouldId = resources.getIdentifier("ic_etc", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			
			model.symbol == "ETHOS" -> {
				val resouldId = resources.getIdentifier("ic_ethos", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "ETP" -> {
				val resouldId = resources.getIdentifier("ic_etp", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "FUN" -> {
				val resouldId = resources.getIdentifier("ic_fun", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "GAME" -> {
				val resouldId = resources.getIdentifier("ic_game", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "GAS" -> {
				val resouldId = resources.getIdentifier("ic_gas", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "GBYTE" -> {
				val resouldId = resources.getIdentifier("ic_gbyte", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "GNO" -> {
				val resouldId = resources.getIdentifier("ic_gno", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "GNT" -> {
				val resouldId = resources.getIdentifier("ic_gnt", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "GRS" -> {
				val resouldId = resources.getIdentifier("ic_grs", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			
			model.symbol == "HSR" -> {
				val resouldId = resources.getIdentifier("ic_hsr", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "ICN" -> {
				val resouldId = resources.getIdentifier("ic_icn", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "KMD" -> {
				val resouldId = resources.getIdentifier("ic_kmd", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "KNC" -> {
				val resouldId = resources.getIdentifier("ic_knc", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "LSK" -> {
				val resouldId = resources.getIdentifier("ic_lsk", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "MAID" -> {
				val resouldId = resources.getIdentifier("ic_maid", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "MCO" -> {
				val resouldId = resources.getIdentifier("ic_mco", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "MNX" -> {
				val resouldId = resources.getIdentifier("ic_mnx", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "MONA" -> {
				val resouldId = resources.getIdentifier("ic_mona", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "MTL" -> {
				val resouldId = resources.getIdentifier("ic_mtl", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "NAV" -> {
				val resouldId = resources.getIdentifier("ic_nav", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "NXS" -> {
				val resouldId = resources.getIdentifier("ic_nxs", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "NXT" -> {
				val resouldId = resources.getIdentifier("ic_nxt", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "OMG" -> {
				val resouldId = resources.getIdentifier("ic_omg", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "PAY" -> {
				val resouldId = resources.getIdentifier("ic_pay", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "PIVX" -> {
				val resouldId = resources.getIdentifier("ic_pivx", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			
			model.symbol == "POT" -> {
				val resouldId = resources.getIdentifier("ic_pot", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "POWER" -> {
				val resouldId = resources.getIdentifier("ic_power", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "PPC" -> {
				val resouldId = resources.getIdentifier("ic_ppc", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "PPT" -> {
				val resouldId = resources.getIdentifier("ic_ppt", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "PURA" -> {
				val resouldId = resources.getIdentifier("ic_pura", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "QASH" -> {
				val resouldId = resources.getIdentifier("ic_qash", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "QTUM" -> {
				val resouldId = resources.getIdentifier("ic_qtum", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "RDN" -> {
				val resouldId = resources.getIdentifier("ic_rdn", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "REP" -> {
				val resouldId = resources.getIdentifier("ic_rep", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			
			model.symbol == "SALT" -> {
				val resouldId = resources.getIdentifier("ic_salt", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "SAN" -> {
				val resouldId = resources.getIdentifier("ic_san", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "SNGLS" -> {
				val resouldId = resources.getIdentifier("ic_sngls", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "SNT" -> {
				val resouldId = resources.getIdentifier("ic_snt", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "START" -> {
				val resouldId = resources.getIdentifier("ic_start", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "STORJ" -> {
				val resouldId = resources.getIdentifier("ic_storj", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "SYS" -> {
				val resouldId = resources.getIdentifier("ic_sys", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "TRX" -> {
				val resouldId = resources.getIdentifier("ic_trx", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "UBQ" -> {
				val resouldId = resources.getIdentifier("ic_ubq", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			
			model.symbol == "USDT" -> {
				val resouldId = resources.getIdentifier("ic_usdt", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			
			model.symbol == "VEN" -> {
				val resouldId = resources.getIdentifier("ic_ven", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "VTC" -> {
				val resouldId = resources.getIdentifier("ic_vtc", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "WTC" -> {
				val resouldId = resources.getIdentifier("ic_wtc", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "XEM" -> {
				val resouldId = resources.getIdentifier("ic_xem", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "XLM" -> {
				val resouldId = resources.getIdentifier("ic_xlm", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "XMR" -> {
				val resouldId = resources.getIdentifier("ic_xmr", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "XRP" -> {
				val resouldId = resources.getIdentifier("ic_xrp", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "XUC" -> {
				val resouldId = resources.getIdentifier("ic_xuc", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "XVG" -> {
				val resouldId = resources.getIdentifier("ic_xvg", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "XZC" -> {
				val resouldId = resources.getIdentifier("ic_xzc", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			
			model.symbol == "ZEC" -> {
				val resouldId = resources.getIdentifier("ic_zec", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "ZEN" -> {
				val resouldId = resources.getIdentifier("ic_zen", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
			model.symbol == "ZRX" -> {
				val resouldId = resources.getIdentifier("ic_zrx", "drawable", packageName)
				coinbar.coinImage.setImageResource(resouldId)
			}
		}
	}
	
	private fun share(model: CryptoModel) {
		val message = "https://play.google.com/store/apps/details?id=$packageName"
		val share = Intent(Intent.ACTION_SEND)
		share.type = "text/plain"
		share.putExtra(Intent.EXTRA_TITLE, "Latest Bitcoin (BTC) details")
		share.putExtra(Intent.EXTRA_TITLE, "Price: $ ${model.price_usd}")
		share.putExtra(Intent.EXTRA_TEXT, "Price (BTC):฿ ${model.price_btc}")
		share.putExtra(Intent.EXTRA_TEXT, "24h Volume:$ ${model.h_volume_usd}")
		share.putExtra(Intent.EXTRA_TEXT, "Market Cap:$ ${model.market_cap_usd}")
		share.putExtra(Intent.EXTRA_TEXT, "Change 1h: % ${model.percent_change_1h}")
		share.putExtra(Intent.EXTRA_TEXT, "Change 24h: % ${model.percent_change_24h}")
		share.putExtra(Intent.EXTRA_TEXT, "Change 7d: % ${model.percent_change_7d}")
		share.putExtra(Intent.EXTRA_TEXT, "Hey checkout this Soccer App " + "\n Download it from playStore "+ " "
				+ Uri.parse(message))
		startActivity(Intent.createChooser(share, "Shared via : "))
	}
	
	override fun onBackPressed() {
		super.onBackPressed()
		finish()
	}
}
