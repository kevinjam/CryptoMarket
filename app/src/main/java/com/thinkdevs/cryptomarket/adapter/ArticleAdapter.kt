package com.thinkdevs.cryptomarket.adapter

import android.content.Context
import android.content.Intent
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.thinkdevs.cryptomarket.R
import com.thinkdevs.cryptomarket.constant.NEWS_DETAILS
import com.thinkdevs.cryptomarket.controller.NewsDetails
import com.thinkdevs.cryptomarket.model.Articles

/**
 * Created by kevinjanvier on 21/03/2018.
 */
class ArticleAdapter(var context:Context, var list:ArrayList<Articles>):RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
	
	override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
		
		val view = LayoutInflater.from(parent!!.context).inflate(R.layout.item_news, parent, false)
		return ViewHolder(view)
	}
	
	override fun getItemCount(): Int {
		return list.count()
	}
	
	override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
		holder!!.bindnews(context,list[position])
	}
	
	inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView){
		var newsTitle = itemView!!.findViewById<TextView>(R.id.news_title)
		var newsDescp = itemView!!.findViewById<TextView>(R.id.news_description)
		var newsDate = itemView!!.findViewById<TextView>(R.id.news_date)
		var newsImage = itemView!!.findViewById<ImageView>(R.id.news_image)
		var more = itemView!!.findViewById<ConstraintLayout>(R.id.constraint_details)
		
		
		
		fun bindnews(context: Context,model:Articles){
			newsTitle.text = model.title.substring(0, 20) +"..."
			newsDescp.text = model.title
			newsDate.text = model.publishedAt
			
			Glide.with(context).load(model.urlToImage).into(newsImage)
			more.setOnClickListener {
				val intent = Intent(context, NewsDetails::class.java)
				intent.putExtra(NEWS_DETAILS, model)
				context.startActivity(intent)
			}
		}
	}
}