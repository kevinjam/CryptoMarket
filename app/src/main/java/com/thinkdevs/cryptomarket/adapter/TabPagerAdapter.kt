package com.thinkdevs.cryptomarket.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.thinkdevs.cryptomarket.fragment.BitCoin

/**
 * Created by kevinjanvier on 19/03/2018.
 */
class TabPagerAdapter(fm:FragmentManager, private var tabcount:Int):FragmentPagerAdapter(fm) {
	override fun getItem(position: Int): Fragment? {
		return when(position){
			0-> BitCoin()
			1-> BitCoin()
			2-> BitCoin()
			4-> BitCoin()
			else-> null
			
		}
	}
	
	override fun getCount(): Int {
		return tabcount
	}
	
}