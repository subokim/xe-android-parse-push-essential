package com.gunmania.pushexample;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.parse.Parse;
import com.parse.PushService;

public class MainApplication extends Application {	
	private static MainApplication instance = new MainApplication();
	public static boolean is_push;
	
	public MainApplication() {
		instance = this;
	}

	public static Context getContext() {
		return instance;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Parse.initialize(this, "ce9XlOOEY3Z9HBy4VhCAJMk0XawhGokmmZYwn9s2", "WJOtBf6fuH1ETz9yQINP71LFx5dXGwqOTKo7JARP");
		
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
		is_push =  pref.getBoolean("pushnotify", true);
		
		if (is_push == true) {
			PushService.setDefaultPushCallback(this, MainActivity.class);
			PushService.subscribe(this, "", MainActivity.class);
		}
		else {
			PushService.setDefaultPushCallback(this, null);
	    	PushService.unsubscribe(this, "");
		}
	}

}