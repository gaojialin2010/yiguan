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

public class LoginActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_login);
		
		LayoutParams params = new LayoutParams(0, 0);
		params.width = LayoutParams.MATCH_PARENT;
		params.height = LayoutParams.FILL_PARENT;
		
		View v = View.inflate(this, R.layout.activity_login, null);
		mContentLayout.addView(v, params);
		
		mTitleView.setText("¿ìËÙ×¢²á");
		
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
		RequestParams params = new RequestParams();
		EditText userName = (EditText)findViewById(R.id.editText1);
		EditText passWord = (EditText)findViewById(R.id.editText2);
		params.put("username", userName.getText().toString());
		params.put("password", passWord.getText().toString());
		PBHttpHelper.get("", params, new AsyncHttpResponseHandler(){
			public void onSuccess(String data){
				JSONObject obj = null;
				try {
					obj = new JSONObject(data);
					String uid = obj.optString("uid");
					String mobile = obj.optString("mobile");
					String realName = obj.optString("realName");
					String sex = obj.optString("sex");
					User.getInstance().initUser(uid, mobile, realName, sex);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				LoginActivity.this.runOnUiThread(new Runnable(){

					@Override
					public void run() {
						// TODO Auto-generated method stub
						Intent intent = new Intent(LoginActivity.this, MainActivity.class);
						startActivity(intent);
						finish();
					}
					
				});
			}
		});
		
		
	}

	public void onForgetPwdClicked( View v )
	{
		Intent intent = new Intent(this, FindPwdActivity.class);
		startActivity(intent);
	}

	public void onRegistClicked( View v )
	{
		Intent intent = new Intent(this, RegistActivity.class);
		startActivity(intent);
	}
}
