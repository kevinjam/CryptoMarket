package com.thinkdevs.cryptomarket.utils

import android.content.Context
import android.content.SharedPreferences
import com.thinkdevs.cryptomarket.constant.EMAIL_ID
import com.thinkdevs.cryptomarket.constant.PREF_FILENAME
import com.thinkdevs.cryptomarket.constant.USER_LOGIN

/**
 * Created by kevinjanvier on 27/03/2018.
 */
class SharedPref(context: Context) {
	val prefs: SharedPreferences = context.getSharedPreferences(PREF_FILENAME, 0)
	val TAG = SharedPref::class.java.simpleName
	
	var saveEmail:String
		get() = prefs.getString(EMAIL_ID, "")
		set(value) = prefs.edit().putString(EMAIL_ID, value).apply()
	
	var checkLogin:Boolean
		get() = prefs.getBoolean(USER_LOGIN, false)
		set(value) = prefs.edit().putBoolean(USER_LOGIN, value).apply()
	
}