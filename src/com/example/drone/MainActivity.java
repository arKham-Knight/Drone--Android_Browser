package com.example.drone;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.graphics.AvoidXfermode;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


 
public class MainActivity extends ActionBarActivity {
	
	WebView myWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        
    setContentView(R.layout.activity_main);
        
    myWebView=(WebView)findViewById(R.id.myWebView);
        
    	myWebView.getSettings().setSupportZoom(true);
    	myWebView.getSettings().setBuiltInZoomControls(true);
    	myWebView.getSettings().setLoadWithOverviewMode(true);
        myWebView.getSettings().setUseWideViewPort(true);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.loadUrl("http://www.google.co.in"+"/");
        myWebView.requestFocus(View.FOCUS_DOWN|View.FOCUS_UP);
        
        //to load the page after entering the url
        OnClickListener btnLoad=new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText text=(EditText)findViewById(R.id.en_url);
				myWebView.loadUrl("http://www."+text.getText().toString());
				myWebView.requestFocus(View.FOCUS_DOWN);
			}
		};
		Button btnLoadd=(Button)findViewById(R.id.btn_load);
		btnLoadd.setOnClickListener(btnLoad);
		
		//for the load bar, which is not working
		final Activity activity=this;
		
		
		
		//for stating "page load completely.
        myWebView.setWebViewClient(new WebViewClient()
        {
        	String currenturl;
        	final EditText editText=(EditText)findViewById(R.id.en_url);
			@Override
			
			
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				currenturl=url;
				return false;
			}
			 

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				// TODO Auto-generated method stub
				super.onPageStarted(view, url, favicon);
				currenturl=url;
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				// TODO Auto-generated method stub
				super.onPageFinished(view, url);
				
				Toast.makeText(getBaseContext(),"Page Loaded Completely", Toast.LENGTH_SHORT).show();
				//activity.setProgress(Window.PROGRESS_END);
				
				//editText.setText(R.id.en_url);
				//String urll=myWebView.getOriginalUrl();
				editText.setText(currenturl);
			}  
			
			
        });
        myWebView.requestFocus(View.FOCUS_DOWN|View.FOCUS_UP);
        
        myWebView.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch(event.getAction()){
				case MotionEvent.ACTION_DOWN:
				case MotionEvent.ACTION_UP:
					if(!v.hasFocus()){
						v.requestFocus();
					}
					break;
				}
				return false;
			}
		});
         
        
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if(myWebView.canGoBack()==true)
			myWebView.goBack();
		else
			finish();
	}


	
    
    
    
   
}
