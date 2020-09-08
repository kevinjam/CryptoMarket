package com.thinkdevs.cryptomarket.adapter

import com.thinkdevs.cryptomarket.fragment.BitCoin
import com.thinkdevs.cryptomarket.fragment.EutheremFragment
import com.thinkdevs.cryptomarket.fragment.LiteCoin
import com.thinkdevs.cryptomarket.fragment.RippleCoin

/**
 * Created by kevinjanvier on 19/03/2018.
 */
class TabPagerAdapter(fm: androidx.fragment.app.FragmentManager, private var tabcount:Int): androidx.fragment.app.FragmentPagerAdapter(fm) {
	override fun getItem(position: Int): androidx.fragment.app.Fragment? {
		return when(position){
			0-> BitCoin()
			1-> EutheremFragment()
			2-> LiteCoin()
			3-> RippleCoin()
			else-> BitCoin()
			
		}
	}
	
	override fun getCount(): Int {
		return tabcount
	}
	
}