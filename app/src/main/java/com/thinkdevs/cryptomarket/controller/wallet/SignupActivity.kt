package com.thinkdevs.cryptomarket.controller.wallet

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.thinkdevs.cryptomarket.R
import com.thinkdevs.cryptomarket.controller.MainActivity
import com.thinkdevs.cryptomarket.utils.App
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.toolbar.view.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.yesButton

class SignupActivity : AppCompatActivity() {
	var auth:FirebaseAuth?= null
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_signup)
		
		signup.nav_nack.setOnClickListener {
			onBackPressed()
			finish()
		}
		signup.coinName.text = "Registration"
		signup.coinImage.visibility = View.GONE
		signup.nav_share.visibility = View.GONE
		
		auth = FirebaseAuth.getInstance()
		
		btn_reset_password.setOnClickListener {
			startActivity(Intent(this, ForgotPassword::class.java))
		}
		
		sign_in_button.setOnClickListener {
//			startActivity(Intent(this, LoginActivity::class.java))
			finish()
		}
		
		sign_up_button.setOnClickListener {
			val email_text = email.text.toString()
			val password_text = password.text.toString()
			
			if (email_text.isEmpty()){
			email.error = "Enter email address!"
			}else if (password_text.isEmpty()){
			password.error ="Enter password!"
			}else if(password_text.length < 6){
				password.error ="Password too short, enter minimum 6 characters!"
			}else{
				progressBar.visibility = View.VISIBLE
				auth!!.createUserWithEmailAndPassword(email_text, password_text)
						.addOnCompleteListener { task ->
							progressBar.visibility = View.GONE
							if (!task.isSuccessful){
								alert("Authentication failed $task", "Error"){
									yesButton {  }
								}.show()
							}else{
								val user:FirebaseUser = auth!!.currentUser!!
								App.pref.saveEmail = user.email!!
								App.pref.checkLogin = true
								startActivity(Intent(this, MainActivity::class.java))
								
							}
						}
			}
		}
		
	}
	
	override fun onResume() {
		super.onResume()
		progressBar.visibility = View.GONE
	}
}
