package com.example.dajiayiguan;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		new Handler().postDelayed(new Runnable(){

			@Override
			public void run() {
				Intent intent = new Intent(SplashActivity.this, StartActivity.class);
				startActivity(intent);
				finish();
			}
			
		}, 1000*3);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.splash, menu);
		return true;
	}

}
