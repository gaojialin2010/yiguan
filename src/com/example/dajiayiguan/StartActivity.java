package com.example.dajiayiguan;

import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.support.v4.view.PagerAdapter;  
import android.support.v4.view.ViewPager;  
import android.support.v4.view.ViewPager.OnPageChangeListener;  
import android.view.View;  
import android.view.View.OnClickListener;
import android.view.ViewGroup;  
import android.view.ViewGroup.LayoutParams;  
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;  
import android.widget.LinearLayout; 

public class StartActivity extends Activity implements OnPageChangeListener {
	
	/** 
     * ViewPager 
     */  
    private ViewPager viewPager;  
      
    /** 
     * 装点点的ImageView数组 
     */  
    private ImageView[] tips;  
      
    /** 
     * 装ImageView数组 
     */  
    private ImageView[] mImageViews;  
      
    /** 
     * 图片资源id 
     */  
    private int[] imgIdArray ;  
    
    private Button btnStart;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 //定义全屏参数
        int flag=WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //获得当前窗体对象
        Window window = this.getWindow();
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);
		setContentView(R.layout.activity_start);
		
		ViewGroup group = (ViewGroup)findViewById(R.id.viewGroup);  
        viewPager = (ViewPager) findViewById(R.id.viewPager);  
          
        //载入图片资源ID  
        imgIdArray = new int[]{R.drawable.start1,R.drawable.start2,R.drawable.start3};  
          
          
        //将点点加入到ViewGroup中  
        tips = new ImageView[imgIdArray.length];  
        for(int i=0; i<tips.length; i++){  
            ImageView imageView = new ImageView(this);  
            imageView.setLayoutParams(new LayoutParams(10,10));  
            tips[i] = imageView;  
            if(i == 0){  
               // tips[i].setBackgroundResource(R.drawable.page_indicator_focused);  
            }else{  
               // tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);  
            }  
              
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT,    
                    LayoutParams.WRAP_CONTENT));  
            layoutParams.leftMargin = 5;  
            layoutParams.rightMargin = 5;  
            group.addView(imageView, layoutParams);  
        }  
          
          
        //将图片装载到数组中  
        mImageViews = new ImageView[imgIdArray.length];  
        for(int i=0; i<mImageViews.length; i++){  
            ImageView imageView = new ImageView(this);  
            mImageViews[i] = imageView;  
            imageView.setBackgroundResource(imgIdArray[i]);  
        }  
          
        //设置Adapter  
        viewPager.setAdapter(new MyAdapter());  
        //设置监听，主要是设置点点的背景  
        viewPager.setOnPageChangeListener(this);  
        //设置ViewPager的默认项, 设置为长度的100倍，这样子开始就能往左滑动  
        viewPager.setCurrentItem(0); 
        
        btnStart = (Button)this.findViewById(R.id.button1);
        btnStart.setVisibility(View.GONE);
        
        btnStart.setOnClickListener( new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(StartActivity.this, LoginActivity.class);
				startActivity(intent);
				finish();
			}
        	
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.start, menu);
		return true;
	}
	
	
	public class MyAdapter extends PagerAdapter{  
		  
        @Override  
        public int getCount() {  
            return 3;  
        }  
  
        @Override  
        public boolean isViewFromObject(View arg0, Object arg1) {  
            return arg0 == arg1;  
        }  
  
        @Override  
        public void destroyItem(View container, int position, Object object) {  
            ((ViewPager)container).removeView(mImageViews[position % mImageViews.length]);
        }  
  
        /** 
         * 载入图片进去，用当前的position 除以 图片数组长度取余数是关键 
         */  
        @Override
        public Object instantiateItem(View container, int position) {
        	
        	int index = position % mImageViews.length;
        	
        	((ViewPager)container).removeView(mImageViews[index]);
            ((ViewPager)container).addView(mImageViews[index], 0);  
            return mImageViews[index];
        }
    }  
  
    @Override  
    public void onPageScrollStateChanged(int arg0) {  
          
    }  
  
    @Override  
    public void onPageScrolled(int arg0, float arg1, int arg2) {  
          
    	if( arg0 == 2 ){
    		btnStart.setVisibility(View.VISIBLE);
    	}
    	else {
    		btnStart.setVisibility(View.GONE);
    	}
    }  
  
    @Override  
    public void onPageSelected(int arg0) {  
        setImageBackground(arg0 % mImageViews.length);  
    }  
      
    /** 
     * 设置选中的tip的背景 
     * @param selectItems 
     */  
    private void setImageBackground(int selectItems){  
        for(int i=0; i<tips.length; i++){  
            if(i == selectItems){  
                //tips[i].setBackgroundResource(R.drawable.page_indicator_focused);  
            }else{  
                //tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);  
            }  
        }  
    }
}
