package com.thinkdevs.cryptomarket.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.thinkdevs.cryptomarket.fragment.BitCoin
import com.thinkdevs.cryptomarket.fragment.NewsFragment

/**
 * Created by kevinjanvier on 19/03/2018.
 */
class TabPagerAdapter(fm:FragmentManager, private var tabcount:Int):FragmentPagerAdapter(fm) {
	override fun getItem(position: Int): Fragment? {
		return when(position){
			0-> BitCoin()
			1-> NewsFragment()
			2-> NewsFragment()
			3-> NewsFragment()
			else-> BitCoin()
			
		}
	}
	
	override fun getCount(): Int {
		return tabcount
	}
	
}