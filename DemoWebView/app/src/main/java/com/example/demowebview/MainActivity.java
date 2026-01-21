package com.example.demowebview;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        webView = findViewById(R.id.webView);

        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
    }

    public void backButton(View view) {
        if(webView.canGoBack()) {
            webView.goBack();
        }
    }

    public void fwdButton(View view) {
        if(webView.canGoForward()) {
            webView.goForward();
        }
    }

    public void reloadButton(View view) {
        webView.reload();
    }

    public void goButton(View view) {
        String url = editText.getText().toString();
        if(!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "http://" + url;
        }
        webView.loadUrl(url);
    }
}