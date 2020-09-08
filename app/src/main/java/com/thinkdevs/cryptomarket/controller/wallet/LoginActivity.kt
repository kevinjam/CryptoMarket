package com.thinkdevs.cryptomarket.controller.wallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.thinkdevs.cryptomarket.R
import com.thinkdevs.cryptomarket.controller.MainActivity
import com.thinkdevs.cryptomarket.utils.App
import com.thinkdevs.cryptomarket.utils.SharedPref
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_news_details.view.*
import kotlinx.android.synthetic.main.toolbar.view.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.yesButton

class LoginActivity : AppCompatActivity() {
	var auth:FirebaseAuth?= null
	
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		
		auth = FirebaseAuth.getInstance()
		if (auth!!.currentUser != null){
			startActivity(Intent(this@LoginActivity, MainActivity::class.java))
			finish()
			
		}
		setContentView(R.layout.activity_login)
		
		toolbar.nav_nack.setOnClickListener {
			onBackPressed()
			finish()
		}
		toolbar.nav_share.visibility = View.GONE
		toolbar.coinName.text = "Login"
		toolbar.coinImage.visibility = View.GONE
		
		auth = FirebaseAuth.getInstance()
		btn_signup.setOnClickListener {
			startActivity(Intent(this@LoginActivity, SignupActivity::class.java))
			
		}
		
		btn_facebook.setOnClickListener {
			alert("Coming Soon", "facebook Login"){
				yesButton {  }
			}.show()
		}
		
		btn_reset_password.setOnClickListener {
			startActivity(Intent(this@LoginActivity, ForgotPassword::class.java))
			
		}
		
		btn_login.setOnClickListener {
			val email_txt = email.text.toString()
			val password_txt = password.text.toString()
			
			when {
				TextUtils.isEmpty(email_txt) -> email.error="Enter email address!"
				password_txt.isEmpty() -> password.error="Enter password!"
				else -> {
					progressBar.visibility = View.VISIBLE
					auth!!.signInWithEmailAndPassword(email_txt, password_txt)
							.addOnCompleteListener { p0 ->
								progressBar.visibility = View.GONE
								if (!p0.isSuccessful){
									if (password_txt.length < 6){
										password.error=getString(R.string.minim_password)
									}else{
										println("--Error _- $p0")
										alert("Auth Failed", "Error"){
											yesButton {  }
										}.show()
										
									}
								}else{
									val user: FirebaseUser = auth!!.currentUser!!
									println("email ${user.email}")
									App.pref.saveEmail = user.email!!
									App.pref.checkLogin = true
									startActivity(Intent(this@LoginActivity, MainActivity::class.java))
									
								}
							}
				}
			}
			
			
			
			
		}
		
		
		
		
		
	}
}
