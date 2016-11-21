package com.example.android_webview;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {

	private String url = "http://www.baidu.com";
	private WebView webview;
	private ProgressDialog dialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.web);
		// Uri uri=Uri.parse(url);
		// Intent intent=new Intent(Intent.ACTION_VIEW,uri);
		// startActivity(intent);
		init();

	}

	private void init() {
		// TODO Auto-generated method stub
		webview = (WebView) findViewById(R.id.webView);
		// WebView加载本地资源
		// webview.loadUrl("file:///android_asset/example.html");
		// WebView加载web资源
		webview.loadUrl(url);
		// 覆盖WebView默认通过第三方或者是系统浏览器打开网页的行为，使得网页可以在WebView中打开
		webview.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				// 返回值是true的时候控制网页在webview中去打开，如果为false调用系统或者是第三方浏览器打开
				view.loadUrl(url);
				return true;
			}
			// webviewclient帮助webview去处理一些页面控制盒请求通知

		});

		// 启用支持JavaScript
		WebSettings settings = webview.getSettings();
		settings.setJavaScriptEnabled(true);
		//WebView加载页面优先使用缓存加载
		settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		webview.setWebChromeClient(new WebChromeClient(){
			
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				// TODO Auto-generated method stub
				//newProgress 1-100之间的整数
				if(newProgress==100){
					//网页加载完毕，关闭ProgressDialog
					closeDialog();
				}
				else{
					//网页正在加载,打开ProgressDialog
					openDialog(newProgress);
				}
				
			}

			private void openDialog(int newProgress) {
				// TODO Auto-generated method stub
				if(dialog==null){
					dialog=new ProgressDialog(MainActivity.this);
					dialog.setTitle("正在加载");
					dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
					dialog.setProgress(newProgress);
					dialog.show();
				}
				else{
					dialog.setProgress(newProgress);
				}
			}

			private void closeDialog() {
				// TODO Auto-generated method stub
				if(dialog!=null&&dialog.isShowing()){
					dialog.dismiss();
					dialog=null;
				}
			}
		});

	};

	// 改写物理按键——返回的逻辑
	public boolean onKeyDown(int keyCode, KeyEvent event){

		if(keyCode==KeyEvent.KEYCODE_BACK){
			if(webview.canGoBack()){
				webview.goBack();//返回上一页面
				return true;
			}else{
				System.exit(0);
			}
		}
		return super.onKeyDown(keyCode, event);
	}
}
