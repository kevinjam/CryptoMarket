package com.thinkdevs.cryptomarket.controller

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.thinkdevs.cryptomarket.R
import com.thinkdevs.cryptomarket.adapter.TabPagerAdapter
import com.thinkdevs.cryptomarket.constant.ADMOB_UNIT
import com.thinkdevs.cryptomarket.controller.wallet.LoginActivity
import com.thinkdevs.cryptomarket.model.Helper
import com.thinkdevs.cryptomarket.utils.App
import com.thinkdevs.cryptomarket.utils.CryptCurrency
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.nav_header_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
	var auth:FirebaseAuth?= null
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		
		
		auth = FirebaseAuth.getInstance()
		setSupportActionBar(toolbar)
		supportActionBar!!.title = "Crypto Market"
		
		val toggle = ActionBarDrawerToggle(
				this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
		drawer_layout.addDrawerListener(toggle)
		toggle.syncState()
		
		nav_view.setNavigationItemSelectedListener(this)
		configureTabLayout()
		adsmobs()
		hideItem()
		
	}
	
	fun hideItem(){
		val navigation = findViewById<NavigationView>(R.id.nav_view)
		navigation.setNavigationItemSelectedListener(this)
		navigation.setCheckedItem(R.id.nav_home)
		
		
		val menu_nav:Menu = navigation.menu
		val user_email:TextView = navigation.getHeaderView(0).findViewById(R.id.email_nav_user)
		
		if (App.pref.checkLogin){
			user_email.text = auth!!.currentUser!!.email
			menu_nav.findItem(R.id.nav_portfolio).isVisible = true
			
		}
		
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
		val logout = menu.findItem(R.id.logout)
		val wallet = menu.findItem(R.id.wallet)
		if (!App.pref.checkLogin){
			logout.isVisible = false
		}else{
			wallet.isVisible = false
			
		}
		return true
	}
	
	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		when (item.itemId) {
			R.id.wallet ->{
				startActivity(Intent(this@MainActivity, LoginActivity::class.java))
				CryptCurrency.logAmplitudeEvent("WALLET_MENU")
				return true
				
			}
			R.id.logout->{
				alert ("Are you Sure you want to Logout?", "Logout"){
					yesButton {
						auth!!.signOut()
						App.pref.checkLogin = false
						val authListener = FirebaseAuth.AuthStateListener {
							firebaseAuth->
							val user:FirebaseUser = firebaseAuth.currentUser!!
							startActivity(Intent(this@MainActivity, LoginActivity::class.java))
							
						}
					}
					noButton {  }
				}.show()
				return true
			}
		}
	
		return  super.onOptionsItemSelected(item)
		
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
				startActivity(Intent(this@MainActivity, LoginActivity::class.java))
//				alert  ("Coming Soon " +
//						"Contact us info@thinkdevs.com", "Wallet"){
//					yesButton {  }
//				}.show()
				CryptCurrency.logAmplitudeEvent("WALLET_MENU")
				
			}
			R.id.nav_news -> {
				startActivity(Intent(this@MainActivity, NewsActivity::class.java))
				overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim)
				CryptCurrency.logAmplitudeEvent("NEWS_MENU")
				
				
			}
			R.id.nav_portfolio -> {
//				startActivity(Intent(this@MainActivity, PortofolioActivity::class.java))
//				overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim)
				CryptCurrency.logAmplitudeEvent("PORTFOLIO_MENU")
				alert("Coming Soon " +
						"Contact us info@thinkdevs.com", "Portfolio") {
					yesButton { }
				}.show()
				
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
