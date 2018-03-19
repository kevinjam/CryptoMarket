package com.thinkdevs.cryptomarket

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.thinkdevs.cryptomarket.model.CryptoModel

import kotlinx.android.synthetic.main.activity_coin.*

class CoinActivity : AppCompatActivity() {
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_coin)
		setSupportActionBar(toolbar)
		
		val model = intent.getSerializableExtra("coin") as CryptoModel
		println("moddddd $model")
		
	}
	
}
