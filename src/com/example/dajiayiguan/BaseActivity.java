package com.example.dajiayiguan;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import android.support.v4.app.FragmentActivity;

public class BaseActivity extends FragmentActivity {
	
	
	protected ImageView mLeftImageView,mRightImageView;
	protected TextView  mTitleView;
	protected Button    mBtnLeft, mBtnRight;
	protected RelativeLayout mContentLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base);

		mLeftImageView = (ImageView)this.findViewById(R.id.imageView1);
		mRightImageView = (ImageView)this.findViewById(R.id.ImageView01);
		mBtnLeft = (Button)this.findViewById(R.id.button1);
		mBtnRight = (Button)this.findViewById(R.id.Button01);
		mContentLayout = (RelativeLayout)this.findViewById(R.id.content_layout);
		mBtnLeft.setOnClickListener( new View.OnClickListener(){

			@Override
			public void onClick(View btn) {
				
				if( btn == mBtnLeft ){
					onLeftButtonClicked();
				}
				else if( btn == mBtnRight ){
					onRightButtonClicked();
				}
			}
			
		});
		
		mTitleView = (TextView)this.findViewById(R.id.textView1);
		mTitleView.setText("");
		
		this.setRightButtonVisible(false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.base, menu);
		return true;
	}
	
	public void setRightButtonIcon(int resId)
	{
		mRightImageView.setImageResource(resId);
	}
	
	public void setLeftButtonVisible( boolean visible ){
		mLeftImageView.setVisibility( visible ? View.VISIBLE : View.GONE );
		//mBtnLeft.setVisibility( visible ? View.VISIBLE : View.GONE );
		mBtnLeft.setEnabled(visible);
	}
	
	public void setRightButtonVisible( boolean visible ){
		mRightImageView.setVisibility( visible ? View.VISIBLE : View.GONE );
		mBtnRight.setVisibility( visible ? View.VISIBLE : View.GONE );
	}

	protected void onLeftButtonClicked()
	{
		finish();
	}

	protected void onRightButtonClicked()
	{
		
	}
}
