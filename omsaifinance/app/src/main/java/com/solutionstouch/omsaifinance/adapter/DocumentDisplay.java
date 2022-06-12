package com.solutionstouch.omsaifinance.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.solutionstouch.omsaifinance.R;

import org.apache.commons.io.FilenameUtils;

public class DocumentDisplay extends AppCompatActivity {

    Button button;
    WebView webView;
    String str;
    String ext;
    String doc;
    @SuppressLint("SetJavaScriptEnabled")
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_document);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        webView = (WebView) findViewById(R.id.webView1);
        String str = getIntent().getStringExtra("document");
     //   button = (Button) findViewById( R.id.mybutt );
    //  String pdf = "http://www.pc-hardware.hu/PDF/konfig.pdf";
      //String doc="<iframe src='https://omsaifinance.solutionstouch.com/upload/borrower_documents/Godaddy_hosting_chat_2.png' width='100%' height='100%' style='border: none;'></iframe>";

        ext = FilenameUtils.getExtension(str);

        if (ext.equals("pdf")){
            doc="https://docs.google.com/gview?embedded=true&url=https://omsaifinance.solutionstouch.com/upload/borrower_documents/"+str;
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(doc));
            startActivity(intent);
            Log.d("format",doc);
        }
        else {
             doc="<iframe src='https://omsaifinance.solutionstouch.com/upload/borrower_documents/"+str+"' width='100%' height='100%' style='border: none;'></iframe>";
            Log.d("format",doc);
        }

      //  String doc="<iframe src='https://omsaifinance.solutionstouch.com/upload/borrower_documents/"+str+"' width='100%' height='100%' style='border: none;'></iframe>";

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.getSettings().setAllowFileAccess(true);
        webView.loadData( doc , "text/html", "UTF-8");
        webView.setInitialScale(1);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowFileAccessFromFileURLs(true);
        webView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(webView!= null && webView.canGoBack())
            webView.goBack();// if there is previous page open it
        else
            super.onBackPressed();//if there is no previous page, close app
    }



}
