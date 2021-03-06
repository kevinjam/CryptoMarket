package com.thinkdevs.cryptomarket.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.thinkdevs.cryptomarket.R
import com.thinkdevs.cryptomarket.constant.NEWS_DETAILS
import com.thinkdevs.cryptomarket.model.Articles
import kotlinx.android.synthetic.main.activity_news_details.*
import kotlinx.android.synthetic.main.toolbar.view.*
import android.widget.Toast
import android.content.ActivityNotFoundException
import android.net.Uri
import android.util.Log


class NewsDetails : AppCompatActivity() {
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_news_details)
		
		news_toolbar.coinImage.visibility = View.GONE
		
		news_toolbar.nav_nack.setOnClickListener {
			onBackPressed()
		}
		
		val model = intent.getSerializableExtra(NEWS_DETAILS) as Articles
//
		news_title_sub.text = model.source.name
		news_date.text = model.publishedAt
		news_title.text = model.title
		news_description.text = model.description
		
		Log.e("MY_CLASS =====" , "Class ${model}")
		
		news_toolbar.coinName.text =model.source.name
		if (model.author != null){
			authorName.text = model.author
		}
		news_toolbar.nav_share.setOnClickListener { share(model) }
		
		
		Glide.with(this@NewsDetails).load(model.urlToImage).into(news_image)
		
		openweb.setOnClickListener {
			openWebPage(model.url)
		}
		

	}
	
	fun openWebPage(url: String) {
		try {
			val webpage = Uri.parse(url)
			val myIntent = Intent(Intent.ACTION_VIEW, webpage)
			startActivity(myIntent)
		} catch (e: ActivityNotFoundException) {
			Toast.makeText(this, "No application can handle this request. Please install a web browser or check your URL.", Toast.LENGTH_LONG).show()
			e.printStackTrace()
		}
		
	}
	
	
	private fun share(model: Articles) {
		val message = model.description
		val share = Intent(Intent.ACTION_SEND)
		share.type = "text/plain"
		share.putExtra(Intent.EXTRA_TITLE, "Latest Bitcoin (BTC) details")
		share.putExtra(Intent.EXTRA_TITLE, "Price: $ ${model.title}")
		share.putExtra(Intent.EXTRA_TEXT, "Price (BTC):฿ ${model.source.name}")
		share.putExtra(Intent.EXTRA_TEXT, "Hey checkout this  : " + "${model.url}"
				+ Uri.parse(message))
		startActivity(Intent.createChooser(share, "Shared via : "))
	}
	
	override fun onBackPressed() {
		super.onBackPressed()
		overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out)
		
	}
}


