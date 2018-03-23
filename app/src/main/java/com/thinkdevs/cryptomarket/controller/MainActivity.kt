package com.thinkdevs.cryptomarket.controller

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.TabLayout
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.thinkdevs.cryptomarket.R
import com.thinkdevs.cryptomarket.adapter.TabPagerAdapter
import com.thinkdevs.cryptomarket.constant.ADMOB_UNIT
import com.thinkdevs.cryptomarket.model.Helper
import com.thinkdevs.cryptomarket.utils.CryptCurrency
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.yesButton


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		setSupportActionBar(toolbar)
		supportActionBar!!.title = "Crypto Market Cap"
		
		
		val toggle = ActionBarDrawerToggle(
				this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
		drawer_layout.addDrawerListener(toggle)
		toggle.syncState()
		
		nav_view.setNavigationItemSelectedListener(this)
		configureTabLayout()
//		setDefaultFragment()
		adsmobs()
		
	}
	
	private fun configureTabLayout() {
		tab_layout.addTab(tab_layout.newTab().setText("Bit coin"))
		tab_layout.addTab(tab_layout.newTab().setText("Ethereum"))
		tab_layout.addTab(tab_layout.newTab().setText("Litecoin"))
		tab_layout.addTab(tab_layout.newTab().setText("Ripple"))
		
		supportActionBar!!.elevation = 0f
		tab_layout.tabMode = TabLayout.MODE_SCROLLABLE
		tab_layout.tabGravity = TabLayout.GRAVITY_FILL
		
		
		val adapter = TabPagerAdapter(supportFragmentManager, tab_layout.tabCount)
		pager.adapter = adapter
		pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))
		tab_layout.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
			override fun onTabReselected(tab: TabLayout.Tab?) {
			}
			
			override fun onTabUnselected(tab: TabLayout.Tab?) {
			}
			
			override fun onTabSelected(tab: TabLayout.Tab?) {
				pager.currentItem = tab!!.position
				pager.visibility = View.VISIBLE
				pager.currentItem = tab.position
				
				when {
					tab.position == 0 -> {
						toolbar.title = "Bit coin"
						CryptCurrency.logAmplitudeEvent("BIT_COIN_TAB")
					}
					tab.position == 1 -> {
						toolbar.title = "Ethereum"
						CryptCurrency.logAmplitudeEvent("ETHEREUN_TAB")
					}
					tab.position == 2 -> {
						toolbar.title = "Litecoin"
						CryptCurrency.logAmplitudeEvent("LITECOIN_TAB")
					}
					tab.position == 3 -> {
						toolbar.title = "Ripple"
						CryptCurrency.logAmplitudeEvent("RIPPLE_TAB")
					}
				}
			}
			
		})
		
	}
	
	override fun onBackPressed() {
		if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
			drawer_layout.closeDrawer(GravityCompat.START)
		} else {
			super.onBackPressed()
		}
	}
	
	override fun onCreateOptionsMenu(menu: Menu): Boolean {
		// Inflate the menu; this adds items to the action bar if it is present.
		menuInflater.inflate(R.menu.main, menu)
		return true
	}
	
	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		return when (item.itemId) {
			R.id.wallet -> true
			else -> super.onOptionsItemSelected(item)
		}
	}
	
	
	override fun onNavigationItemSelected(item: MenuItem): Boolean {
		// Handle navigation view item clicks here.
		when (item.itemId) {
			R.id.nav_home -> {
				CryptCurrency.logAmplitudeEvent("HOME_MENU")

//				val global = BitCoin()
//				val manager :FragmentManager = supportFragmentManager
//				manager.beginTransaction()
//						.replace(R.id.mainRelative, global).commit()
			}
			R.id.nav_fullist -> {
				startActivity(Intent(this@MainActivity, FullListActivity::class.java))
				CryptCurrency.logAmplitudeEvent("FULL_LIST_MENU")
				overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim)
				
				
			}
			
			R.id.nav_global -> {
				startActivity(Intent(this@MainActivity, GlobalMarketActivity::class.java))
				CryptCurrency.logAmplitudeEvent("GLOBAL_MENU")
				overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim)
				
			}
			
			R.id.nav_wallet->{
				//startActivity(Intent(this@MainActivity, WalletActivity::class.java))
				alert  ("Coming Soon " +
						"Contact us info@thinkdevs.com", "Wallet"){
					yesButton {  }
				}.show()
				CryptCurrency.logAmplitudeEvent("WALLET_MENU")
				
			}
			R.id.nav_news -> {
				CryptCurrency.logAmplitudeEvent("NEWS_MENU")
				startActivity(Intent(this@MainActivity, NewsActivity::class.java))
				overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim)
				
			}
			R.id.nav_portfolio -> {
				CryptCurrency.logAmplitudeEvent("PORTFOLIO_MENU")
				startActivity(Intent(this@MainActivity, PortofolioActivity::class.java))
				overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim)
				
			}
			
			R.id.nav_converter->{
				startActivity(Intent(this@MainActivity, ConverterActivity::class.java))
				CryptCurrency.logAmplitudeEvent("CONVERTER")
				overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim)
				
			}
			R.id.nav_share -> {
				share()
			}
			
			R.id.nav_about->{
			about()
			}
			
			R.id.nav_support->{
				sendEmailSupport()
				
			}
			
		}
		
		drawer_layout.closeDrawer(GravityCompat.START)
		return true
	}
	
	private fun about(){
		CryptCurrency.logAmplitudeEvent("ABOUT")
		
		alert("A Cryptocurrency is a digital" +
				" asset designed to work as a medium of exchange that uses" +
				" cryptography to secure its transactions, to control the " +
				"creation of additional units, and to verify the transfer of assets.","Coin Market App "){
			
			yesButton {
			
			}
		}.show()
	}
	
	private fun sendEmailSupport(){
		val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.fromParts(
				"mailto", "info@thinkdevs.com", null))
		emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject")
		emailIntent.putExtra(Intent.EXTRA_TEXT, "Body")
		startActivity(Intent.createChooser(emailIntent, "Send email..."))
	
	}
	
	private fun adsmobs() {
		if (CryptCurrency.isNetworkAvailable(this)) {
			CryptCurrency.logAmplitudeEvent("ADS_HOME")
			val isConnected = true
			if (isConnected) {
				val adView = AdView(this)
				adView.adSize = AdSize.BANNER
				adView.adUnitId = ADMOB_UNIT
				layout_ad.addView(adView)
				val adResquest = AdRequest.Builder().build()
				mAdView.loadAd(adResquest)
			} else {
				println("IS NOT CONNECTED")
				
				layout_ad.visibility = View.GONE
			}
		} else {
			layout_ad.visibility = View.GONE
		}
		
		
	}
	
	
	private fun share() {
		CryptCurrency.logAmplitudeEvent("SHARE_MENU")
		
		val message = "https://play.google.com/store/apps/details?id=$packageName"
		val share = Intent(Intent.ACTION_SEND)
		share.type = "text/plain"
		share.putExtra(Intent.EXTRA_TITLE, "Crypto Market")
		share.putExtra(Intent.EXTRA_TEXT, "Hey, check Crypto Market App" + " "
				+ Uri.parse(message))
		startActivity(Intent.createChooser(share, "Share with : "))
	}
	
}
