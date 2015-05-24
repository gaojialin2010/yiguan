package com.example.dajiayiguan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.loopj.android.http.*;

public class PBHttpHelper {

	  private static AsyncHttpClient _client = null;

	  public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
		  AsyncHttpClient client = new AsyncHttpClient();
		  client.setTimeout(10000);
	      client.get(url, params, responseHandler);
	      _client = client;
	  }

	  public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler, int timeout) {
		  AsyncHttpClient client = new AsyncHttpClient();
		  client.setTimeout(timeout*1000);
	      client.post(url, params, responseHandler);
	      _client = client;
	  }
	  public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
		  post(url,params,responseHandler,10);
	  }

	public static String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	// 妫�煡缃戠粶 鏄惁姝ｅ父
	public static boolean checkNet(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netWrokInfo = manager.getActiveNetworkInfo();
		if (netWrokInfo == null || !netWrokInfo.isAvailable()) {
			// Toast.makeText(context, "褰撳墠鐨勭綉缁滀笉鍙敤锛岃寮�惎缃戠粶", Toast.LENGTH_LONG).show();
			return false;
		// } else if (netWrokInfo.getTypeName().equals("MOBILE")
		//		& netWrokInfo.getExtraInfo().equals("cmwap")) {
			// Toast.makeText(context, "cmwap缃戠粶涓嶅彲鐢紝璇烽�鎷ヽmnet缃戠粶", Toast.LENGTH_LONG).show();
			// return false;
		} else {
			return true;
		}
	}
}
