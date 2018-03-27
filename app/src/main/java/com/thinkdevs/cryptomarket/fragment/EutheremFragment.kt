package com.thinkdevs.cryptomarket.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.thinkdevs.cryptomarket.R
import com.thinkdevs.cryptomarket.model.Crypto
import com.thinkdevs.cryptomarket.model.Helper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_news.*
import android.widget.SeekBar
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.Legend.LegendForm
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.utils.ColorTemplate


/**
 * A simple [Fragment] subclass.
 */
class EutheremFragment : Fragment() {
	var composite: CompositeDisposable? = null
	
	lateinit var mChart: BarChart
	private val mSeekBarX: SeekBar? = null
	val mSeekBarY: SeekBar? = null
	var arraylist = ArrayList<Crypto>()
	
	
	override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
							  savedInstanceState: Bundle?): View? {
		// Inflate the layout for this fragment
		val view = inflater!!.inflate(R.layout.fragment_news, container, false)
		composite = CompositeDisposable()
		
		mChart = view.findViewById(R.id.chart1)
		
		mChart.setDrawBarShadow(false)
		mChart.setDrawValueAboveBar(false)
		mChart.description.isEnabled = false


//		val mv = XYMarkerView(this, xAxisFormatter)
//		mv.setChartView(mChart) // For bounds control
		//mChart.marker = mv // Set the marker to the chart
		
		
		setUpbar()
		
		getBitcoins()
		
		
		
		
		return view
	}
	
	fun setUpbar() {
	
	}
	
	
	fun getBitcoins() {
		val apiservice = Helper.mainUrl()
		composite!!.add(apiservice.get_ethereum()
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(
						{ result ->
							handlResponse(result)
							
							
						}, { error ->
					handleError(error)
				}
				))
	}
	
	private fun handleError(error: Throwable?) {
		no_internet.visibility = View.VISIBLE
		no_internet.text = getString(R.string.no_connection)
		
	}
	
	private fun handlResponse(result: ArrayList<Crypto>) {
		arraylist = result
//		println("Results goes here $arraylist")
		// if more than 60 entries are displayed in the chart, no values will be
		// drawn
		mChart.setMaxVisibleValueCount(60)
		
		mChart.setPinchZoom(false)
		
		mChart.setDrawGridBackground(false)
		
		val xAxis = mChart.xAxis
		xAxis.position = XAxis.XAxisPosition.BOTTOM
		xAxis.setDrawGridLines(false)
		xAxis.granularity = 1f // only intervals of 1 day
		xAxis.labelCount = 7
		
		
		val rightAxis = mChart.axisRight
		rightAxis.setDrawGridLines(false)
//		rightAxis.typeface = mTfLight
		rightAxis.setLabelCount(8, false)
//		rightAxis.valueFormatter = custom
		rightAxis.spaceTop = 15f
		rightAxis.axisMinimum = 0f // this replaces setStartAtZero(true)
		
		
		val l = mChart.legend
		l.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
		l.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
		l.orientation = Legend.LegendOrientation.HORIZONTAL
		l.setDrawInside(false)
		l.form = LegendForm.SQUARE
		l.formSize = 9f
		l.textSize = 11f
		l.xEntrySpace = 4f
		
		
		arraylist.forEach { model ->
			coin_name.text = model.name
			coin_prince.text = "$ ${model.price_usd}"
			symbol.text = model.symbol
			market_cap.text = "$ ${model.market_cap_usd}"
			volume.text = "$ ${model.max_supply}"
			available_supply.text = model.available_supply
			update.text = "Last Updated :${model.last_updated}"
			when {
				model.symbol == "LTC" -> {
					val resouldId = context.resources.getIdentifier("ic_ethereum", "drawable", activity.packageName)
					coinImage.setImageResource(resouldId)
				}
			}
			
			//setData(12, model.market_cap_usd.toFloat(), result)
			
			
		}
	}
	
	
	private fun setData(count: Int, range: Float, result: ArrayList<Crypto>) {
//		println("===range $range")
//		println("===count $count")
		
		val start = 1f
		
		val yVals1 = ArrayList<BarEntry>()
		
		result.forEachIndexed { index, crypto ->
			
			//			println("Rank -- ${crypto.rank}")
			
			
		}
//		println("Outside an array $result")
		
		var i = start.toInt()
		while (i < start + count.toFloat() + 1f) {
			val mult = range + 1
//			println("===range $range")
			val doub = (Math.random() * mult).toFloat()
			yVals1.add(BarEntry(i.toFloat(), doub))

//			if (Math.random() * 100 < 25) {
////				yVals1.add(BarEntry(i.toFloat(), `val`, resources.getDrawable(R.drawable.star)))
//			} else {
//				yVals1.add(BarEntry(i.toFloat(), doub))
////					yVals1.add(BarEntry(index.toFloat(), crypto.rank.toFloat()))
//
//			}
//			i++
		}
		val set1: BarDataSet
		
		if (mChart.data != null && mChart.data.dataSetCount > 0) {
			set1 = mChart.data.getDataSetByIndex(0) as BarDataSet
			set1.values = yVals1
			mChart.data.notifyDataChanged()
			mChart.notifyDataSetChanged()
		} else {
			set1 = BarDataSet(yVals1, "The year 2017")
			
			set1.setDrawIcons(false)
			
			set1.setColors(*ColorTemplate.MATERIAL_COLORS)
			
			val dataSets = ArrayList<IBarDataSet>()
			dataSets.add(set1)
			
			val data = BarData(dataSets)
			data.setValueTextSize(10f)
			//data.setValueTypeface(mTfLight)
			data.barWidth = 0.9f
			
			mChart.data = data
		}
	}
	
	
}
