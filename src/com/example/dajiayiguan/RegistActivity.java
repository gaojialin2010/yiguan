package com.example.dajiayiguan;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;

public class RegistActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_login);
		
		LayoutParams params = new LayoutParams(0, 0);
		params.width = LayoutParams.FILL_PARENT;
		params.height = LayoutParams.FILL_PARENT;
		
		View v = View.inflate(this, R.layout.activity_regist, null);
		mContentLayout.addView(v, params);
		
		mTitleView.setText("µÇÂ¼");
		
		this.setLeftButtonVisible(false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	public void onLoginClicked( View v )
	{
		
	}

	public void onForgetPwdClicked( View v )
	{
		Intent intent = new Intent(this, FindPwdActivity.class);
		startActivity(intent);
	}

	public void onRegistClicked( View v )
	{
	}
}
