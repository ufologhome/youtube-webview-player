package com.example.youtubewebview;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = findViewById(R.id.webview);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setMediaPlaybackRequiresUserGesture(false);
        settings.setAllowFileAccess(true);
        settings.setAllowContentAccess(true);

        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());

        String videoId = "3JIPDZqkeSo"; // <-- замени при необходимости

        String html =
                "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<meta name='viewport' content='width=device-width, initial-scale=1.0'>" +
                "<style>html,body{margin:0;padding:0;background:black;height:100%;}</style>" +
                "</head>" +
                "<body>" +
                "<iframe width='100%' height='100%' " +
                "src='https://www.youtube.com/embed/" + videoId + "?autoplay=1&playsinline=1&mute=1' " +
                "frameborder='0' " +
                "allow='autoplay; encrypted-media' " +
                "allowfullscreen>" +
                "</iframe>" +
                "</body>" +
                "</html>";

        webView.loadDataWithBaseURL(
                "https://www.youtube.com",
                html,
                "text/html",
                "UTF-8",
                null
        );
    }
}
