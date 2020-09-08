package com.thinkdevs.cryptomarket.adapter

import android.content.Context
import android.graphics.Color
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.thinkdevs.cryptomarket.R
import com.thinkdevs.cryptomarket.model.CryptoModel

/**
 * Created by kevinjanvier on 22/03/2018.
 */
class FullListAdapter(var context: Context, var list: ArrayList<CryptoModel>): androidx.recyclerview.widget.RecyclerView.Adapter<FullListAdapter.ViewHolder>() {
	
	
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val view = LayoutInflater.from(parent.context).inflate(R.layout.item_full_list, parent, false)
		return ViewHolder(view)
	}
	
	override fun getItemCount(): Int {
return list.count()	}
	
	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder!!.bindview(list[position])
		
	}
	
	inner class ViewHolder(itemView: View?): androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView!!){
		val name = itemView!!.findViewById<TextView>(R.id.name)
		val number = itemView!!.findViewById<TextView>(R.id.number)
		
		val market_price = itemView!!.findViewById<TextView>(R.id.market_price)
		val price = itemView!!.findViewById<TextView>(R.id.price)
		val volume24 = itemView!!.findViewById<TextView>(R.id.volume24)
//		val supply = itemView!!.findViewById<TextView>(R.id.supply)
//		val id_more = itemView!!.findViewById<ConstraintLayout>(R.id.id_more)
		
		
		fun bindview(model :CryptoModel){
			val position = adapterPosition
			number.text = position.toString()
			name.text = model.name
			market_price.text = model.market_cap_usd
			price.text = model.price_usd
			volume24.text = "${model.percent_change_24h } " + " %"
			
			
			if (model.percent_change_24h.startsWith("-")){
				volume24.setTextColor(Color.RED)
			}else{
				volume24.setTextColor(Color.GREEN)
				
			}
			//name.setBackgroundResource(R.drawable.grid_items_border)
			
			
			
			
		}
	}
}