package com.thinkdevs.cryptomarket.utils;

import android.content.Context;
import android.graphics.Typeface;

import java.util.Hashtable;

/**
 * Created by kevinjanvier on 19/03/2018.
 */

public class IconManager {
	private static Hashtable<String, Typeface> cache_icons = new Hashtable<>();
	public static Typeface get_icons(String path, Context context){
		Typeface icons = cache_icons.get(path);

		if (icons == null){
			icons = Typeface.createFromAsset(context.getAssets(), path);
			cache_icons.put(path, icons);
		}
		return icons;
	}
}
