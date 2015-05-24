package com.example.dajiayiguan;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public class MyScoreActivity extends BaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);

		LayoutParams params = new LayoutParams(0, 0);
		params.width = LayoutParams.MATCH_PARENT;
		params.height = LayoutParams.MATCH_PARENT;
		
		View v = View.inflate(this, R.layout.layout_myscore, null);
		mContentLayout.addView(v, params);
		
		mTitleView.setText("我的学分");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
