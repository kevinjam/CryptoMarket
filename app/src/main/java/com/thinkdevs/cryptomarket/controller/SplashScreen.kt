package com.thinkdevs.cryptomarket.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import com.thinkdevs.cryptomarket.R

class SplashScreen : AppCompatActivity() {
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		requestWindowFeature( Window.FEATURE_NO_TITLE )
		window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN)
		setContentView(R.layout.splash_screen)
		
		Handler().postDelayed({
			startActivity(Intent(this, MainActivity::class.java))
			overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim)
		}, 1000)
	}
}
