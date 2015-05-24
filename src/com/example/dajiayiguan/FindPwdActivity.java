package com.example.dajiayiguan;

import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class FindPwdActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_login);
		
		LayoutParams params = new LayoutParams(0, 0);
		params.width = LayoutParams.FILL_PARENT;
		params.height = LayoutParams.FILL_PARENT;
		
		View v = View.inflate(this, R.layout.activity_find_pwd, null);
		mContentLayout.addView(v, params);
		
		mTitleView.setText("’“ªÿ√‹¬Î");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	public void onNextClicked( View v )
	{
		// TODO Auto-generated method stub
		Intent intent = new Intent(FindPwdActivity.this, ResetPwdActivity.class);
		startActivity(intent);
		
	}
	
	public void onGetAuthCodeClicked( View v )
	{
		RequestParams params = new RequestParams();
		EditText mobile = (EditText)findViewById(R.id.editText1);
		params.put("uid", User.getInstance().getUid());
		params.put("mobile", mobile.getText().toString());
		PBHttpHelper.get("", params, new AsyncHttpResponseHandler(){
			public void onSuccess(String data){
				JSONObject obj = null;
				try {
					obj = new JSONObject(data);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
