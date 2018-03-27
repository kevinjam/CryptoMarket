package com.thinkdevs.cryptomarket.controller.wallet

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.thinkdevs.cryptomarket.R
import kotlinx.android.synthetic.main.passwordforgot.*
import kotlinx.android.synthetic.main.toolbar.view.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.yesButton

class ForgotPassword : AppCompatActivity() {
	var auth:FirebaseAuth?= null
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.passwordforgot)
		toolbar.nav_share.visibility = View.GONE
		toolbar.nav_nack.setOnClickListener {
			onBackPressed()
			finish()
		}
		toolbar.coinImage.visibility = View.GONE
		toolbar.coinName.text = "Forgot password?"
		
		auth = FirebaseAuth.getInstance()
		btn_back.setOnClickListener {
			finish()
		}
		
		
		btn_reset_password.setOnClickListener {
			val email_txt = email.text.toString()
			
			
			if (email_txt.isEmpty()){
				email.error ="Enter your registered email id"
			}else{
				progressBar.visibility = View.VISIBLE
				auth!!.sendPasswordResetEmail(email_txt)
						.addOnCompleteListener { task ->
							if (task.isSuccessful){
								alert("We have sent you instructions to reset your password!", "Sent"){
									yesButton {  }
								}.show()
							}else{
								alert("Failed to send reset email!", "Error"){
									yesButton {  }
								}.show()
							}
							progressBar.visibility = View.GONE
							
							
						}
			}
			
			
			
		}
	}
}
