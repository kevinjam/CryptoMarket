package com.thinkdevs.cryptomarket.controller

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.thinkdevs.cryptomarket.R
import com.thinkdevs.cryptomarket.adapter.TabPagerAdapter
import com.thinkdevs.cryptomarket.fragment.BitCoin
import com.thinkdevs.cryptomarket.fragment.GlobalMarker
import com.thinkdevs.cryptomarket.fragment.NewsFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		setSupportActionBar(toolbar)
		supportActionBar!!.title = "Home "
		
		
		val toggle = ActionBarDrawerToggle(
				this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
		drawer_layout.addDrawerListener(toggle)
		toggle.syncState()
		
		nav_view.setNavigationItemSelectedListener(this)
		configureTabLayout()
//		setDefaultFragment()
		
	}
	
	private fun configureTabLayout() {
		tab_layout.addTab(tab_layout.newTab().setText("Bit coin"))
		tab_layout.addTab(tab_layout.newTab().setText("Ethereum"))
		tab_layout.addTab(tab_layout.newTab().setText("Litecoin"))
		tab_layout.addTab(tab_layout.newTab().setText("Ripple"))
		
		
		val adapter = TabPagerAdapter(supportFragmentManager, tab_layout.tabCount)
		pager.adapter = adapter
		pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))
		tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
			override fun onTabReselected(tab: TabLayout.Tab?) {
				pager.currentItem = tab!!.position
			}
			
			override fun onTabUnselected(tab: TabLayout.Tab?) {
			}
			
			override fun onTabSelected(tab: TabLayout.Tab?) {
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
		when (item.itemId) {
			R.id.action_settings -> return true
			else -> return super.onOptionsItemSelected(item)
		}
	}
	
	
	override fun onNavigationItemSelected(item: MenuItem): Boolean {
		// Handle navigation view item clicks here.
		when (item.itemId) {
			R.id.nav_home -> {
//				val global = BitCoin()
//				val manager :FragmentManager = supportFragmentManager
//				manager.beginTransaction()
//						.replace(R.id.mainRelative, global).commit()
			}
			R.id.nav_global -> {
				startActivity(Intent(this@MainActivity, HomeActivity::class.java))
				
				
			}
			R.id.nav_news -> {
				
//				val global = GlobalMarker()
//				val manager :FragmentManager = supportFragmentManager
//				manager.beginTransaction()
//						.replace(R.id.mainRelative, global, global.tag).commit()
				startActivity(Intent(this@MainActivity, HomeActivity::class.java))
				
			}
			R.id.nav_portfolio -> {
			
			}
			R.id.nav_share -> {
				share()
			}
			
		}
		
		drawer_layout.closeDrawer(GravityCompat.START)
		return true
	}
	
	private fun share() {
		val message = "https://play.google.com/store/apps/details?id=$packageName"
		val share = Intent(Intent.ACTION_SEND)
		share.type = "text/plain"
		share.putExtra(Intent.EXTRA_TITLE, "LiveScore App")
		share.putExtra(Intent.EXTRA_TEXT, "Hey, check Crypto Market App" + " "
				+ Uri.parse(message))
		startActivity(Intent.createChooser(share, "Share Via : "))
	}
	
}
