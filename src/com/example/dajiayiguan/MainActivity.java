package com.example.dajiayiguan;

import java.util.zip.Inflater;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;

public class MainActivity extends BaseActivity {

	private ImageButton bottomBtns[] = new ImageButton[2];
	private TextView	bottomLabels[] = new TextView[2];
	private LeftMainFragment tabLayouts[] = new LeftMainFragment[2];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);

		LayoutParams params = new LayoutParams(0, 0);
		params.width = LayoutParams.MATCH_PARENT;
		params.height = LayoutParams.MATCH_PARENT;
		
		LayoutInflater inflater = LayoutInflater.from(this);
		View v = inflater.inflate( R.layout.activity_main, null, false);
		mContentLayout.addView(v, params);
		
		mTitleView.setText("教育培训");
		this.setLeftButtonVisible(false);

		bottomBtns[0] = (ImageButton) this.findViewById(R.id.imageButton2);
		bottomBtns[1] = (ImageButton) this.findViewById(R.id.ImageButton04);
		bottomLabels[0] = (TextView) this.findViewById(R.id.textView2);
		bottomLabels[1] = (TextView) this.findViewById(R.id.TextView04);
		tabLayouts[0] = new LeftMainFragment();//(Fragment) getSupportFragmentManager().findFragmentByTag("R.id.fragment1");
		tabLayouts[1] = new LeftMainFragment();//(Fragment) getSupportFragmentManager().findFragmentByTag("R.id.fragment01");

		tabLayouts[0].setContentResId(R.layout.layout_tab1);
		tabLayouts[1].setContentResId(R.layout.layout_tab2);
		
		showTab(0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	

	int bottomBtnResource[] = {
			R.drawable.icon_education_1,
			R.drawable.icon_personal_1
	};
	int bottomBtnResourceSel[] = {
			R.drawable.icon_education,
			R.drawable.icon_personal
	};
	String titles[] = {
			"教育培训",
			"个人中心"
	};
	int currentTabIndex = 0;
	public void showTab( int index )
	{	
		FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        
		for( int i = 0; i < 2; i++ ){
			bottomBtns[i].setImageResource(bottomBtnResource[i]);
			bottomLabels[i].setTextColor(0xff444444);
		}
		bottomBtns[index].setImageResource(bottomBtnResourceSel[index]);
		bottomLabels[index].setTextColor(0xff3cc8fc);
		//tabLayouts[index].setVisibility(View.VISIBLE);
		mTitleView.setText(titles[index]);
		
        transaction.replace(R.id.fragments_parent, tabLayouts[index]);
        transaction.commit();
        currentTabIndex = index;
	}

	public void onBottomBtnClicked( View v ){
		
		int currentIndex = -1;
		for( int i = 0; i < bottomBtns.length; i++ ){
			if( v == bottomBtns[i] ){
				currentIndex = i;
				break;
			}
		}
		if( currentIndex == -1 )
			return;
		
		this.showTab(currentIndex);
	}
	
	public void onItemClicked( View v ){
		
		if( v.getId() == R.id.button1 ){
			// 我的学分
			Intent intent = new Intent(this, MyScoreActivity.class);
			startActivity(intent);
		}
		else if( v.getId() == R.id.Button01 ){
			// 我的问卷
		}
		else if( v.getId() == R.id.Button03 ){
			// 意见反馈
		}
		else if( v.getId() == R.id.Button02 ){
			// 系统设置
		}
		else if( v.getId() == R.id.imageButton1 ){
			// 新培训
		}
		else if( v.getId() == R.id.ImageButton02 ){
			// 我的培训
		}
		else if( v.getId() == R.id.ImageButton03 ){
			// 调查问卷
		}
		else if( v.getId() == R.id.ImageButton01 ){
			// 在线教育
		}
	}
}
