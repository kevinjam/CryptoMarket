package com.thinkdevs.cryptomarket

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.TabLayout
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.thinkdevs.cryptomarket.adapter.TabPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		setSupportActionBar(toolbar)
		
	
		val toggle = ActionBarDrawerToggle(
				this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
		drawer_layout.addDrawerListener(toggle)
		toggle.syncState()
		
		nav_view.setNavigationItemSelectedListener(this)
		
		configureTabLayout()
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
				// Handle the camera action
			}
			R.id.nav_global -> {
			
			}
			R.id.nav_news -> {
			
			}
			R.id.nav_portfolio -> {
			
			}
			R.id.nav_share -> {
			
			}
			
		}
		
		drawer_layout.closeDrawer(GravityCompat.START)
		return true
	}
}