package com.example.dajiayiguan;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class PBAsyncFileLoader {

	final static String TAG = "AsyncFileLoader";
	
	String _fileURL = null;
	String _filePath = null;
	FileLoaderCallback _listener = null;
	int percent = 0;
	Handler handler = new Handler() {
		public void handleMessage(Message message) {
			boolean success = false; 
			if(message.what==1) success = true;
			// _listener.fileLoaded(this, success);
			onLoadFinish(success);
		}
	};
	Object  userData = null;
	
	PBAsyncFileLoader(String url, String path, FileLoaderCallback listener)
	{
		_fileURL = url; _filePath = path; _listener = listener;
	}
	public void setUserData(Object data)
	{
		userData = data;
	}
	public Object getUserData() {
		return userData;
	}
	public String getFileURL() {
		return _fileURL;
	}
	public String getFilePath() {
		return _filePath;
	}
	public void load() 
	{
		new Thread() {
			@Override
			public void run() {
				boolean res = loadFileFromUrl();
				int what = 0;
				if(res) what = 1;
				Message message = handler.obtainMessage(what);
				handler.sendMessage(message);
			}
		}.start();
		
		// boolean res = loadFileFromUrl();
		// _listener.fileLoaded(this, res);
	}

	public boolean loadFileFromUrl() {
		String url = _fileURL;
		URL m;
		InputStream i = null; FileOutputStream fout = null;
		try {
			// Log.d("DEBUG", "url:"+url);
			Log.d(TAG, "loadFileFromUrl:"+url);
			m = new URL(url);
			URLConnection conn = m.openConnection();
			conn.connect();
			int fileSize = conn.getContentLength();//根据响应获取文件大小
			if(fileSize <= 0) return false;
			i = (InputStream) m.getContent();
			fout = new FileOutputStream(_filePath);
			byte[] b = new byte[4096]; 
			int totalLen = 0;
			int len = i.read(b);
			while(len>0) {
				fout.write(b, 0, len); totalLen += len;
				len = i.read(b);
				percent = (int)(((float)totalLen / (float)fileSize) * 100);
				if(percent > 99) percent = 99;
				_listener.fileLoadingPercent(this, percent);
			}
			i.close();
			fout.close();
			Log.d(TAG, "load "+_fileURL+" ok: "+totalLen+" bytes.");
			if(totalLen==0) {
				Log.d(TAG, "TotalLen zero:"+totalLen);
				return false;
			}
			// unzip files
			if(_filePath.substring(_filePath.length()-4).equals(".zip")) 
			{
				Log.d(TAG, "unzip:"+_filePath);
				boolean res = PBFileUtil.unpackZip(_filePath);
				if(res) {
					// clear zip file
					File f = new File(_filePath);
					res = f.delete();
					Log.d(TAG, "delete file:"+res);
					res = f.createNewFile();
					Log.d(TAG, "create file:"+res);
					Log.d(TAG, "now file size:"+f.length()+" bytes");

					_listener.fileLoadingPercent(this, 100);
					Log.d(TAG, "unzip success:"+_filePath);
				} else {
					Log.d(TAG, "Unzip failed:%d"+totalLen);
					return false;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	Handler delayHandler = new Handler();
	private void onLoadFinish(boolean isSuccess)
	{
		if(isSuccess){
			_listener.fileLoaded(this, isSuccess);
		} else {
			delayHandler.postDelayed( new Runnable(){
				@Override
				public void run() {
					load();
				}
			}, 10000);
		}
	}
	public interface FileLoaderCallback {
		public void fileLoaded(PBAsyncFileLoader loader, boolean isSuccess);
		public void fileLoadingPercent(PBAsyncFileLoader loader, int percent);
	}

}