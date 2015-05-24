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

public class ResetPwdActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_login);
		
		LayoutParams params = new LayoutParams(0, 0);
		params.width = LayoutParams.FILL_PARENT;
		params.height = LayoutParams.FILL_PARENT;
		
		View v = View.inflate(this, R.layout.activity_reset_pwd, null);
		mContentLayout.addView(v, params);
		
		mTitleView.setText("÷ÿ…Ë√‹¬Î");
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
		EditText passWord1 = (EditText)findViewById(R.id.editText1);
		EditText passWord2 = (EditText)findViewById(R.id.editText2);
		String pwd1 = passWord1.getText().toString();
		String pwd2 = passWord2.getText().toString();
		if(pwd1.equals(pwd2)){
			params.put("uid", User.getInstance().getUid());
			params.put("pwd", passWord2.getText().toString());
			PBHttpHelper.get("", params, new AsyncHttpResponseHandler(){
				public void onSuccess(String data){
					JSONObject obj = null;
					try {
						obj = new JSONObject(data);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ResetPwdActivity.this.runOnUiThread(new Runnable(){
	
						@Override
						public void run() {
							// TODO Auto-generated method stub
							Intent intent = new Intent(ResetPwdActivity.this, LoginActivity.class);
							intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							startActivity(intent);
						}
						
					});
				}
			});
		}
		
	}

	public void onForgetPwdClicked( View v )
	{
		
	}

	public void onRegistClicked( View v )
	{
		
	}
}
