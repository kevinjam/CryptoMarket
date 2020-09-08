package com.thinkdevs.cryptomarket.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import android.view.View

import com.thinkdevs.cryptomarket.R

/**
 * Created by kevinjanvier on 22/03/2018.
 */

class SimpleDividerItemDecoration(context: Context) : androidx.recyclerview.widget.RecyclerView.ItemDecoration() {
	private val mDivider: Drawable = ContextCompat.getDrawable(context,R.drawable.line_divider)
	override fun onDrawOver(c: Canvas, parent: androidx.recyclerview.widget.RecyclerView, state: androidx.recyclerview.widget.RecyclerView.State?) {
		val left = parent.paddingLeft
		val right = parent.width - parent.paddingRight

		val childCount = parent.childCount
		for (i in 0 until childCount) {
			val child = parent.getChildAt(i)

			val params = child.layoutParams as androidx.recyclerview.widget.RecyclerView.LayoutParams

			val top = child.bottom + params.bottomMargin
			val bottom = top + mDivider.intrinsicHeight

			mDivider.setBounds(left, top, right, bottom)
			mDivider.draw(c)
		}
	}
}