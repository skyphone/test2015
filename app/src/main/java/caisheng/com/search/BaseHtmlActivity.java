package caisheng.com.search;

import android.app.Activity;
import android.os.Build;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by abc on 2015/7/28.
 */
public class BaseHtmlActivity extends Activity   {

    public WebView webView;




    protected void initView() {
        //setting
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(this, "android");
        String userAgent = webView.getSettings().getUserAgentString();
        userAgent = userAgent.replace("Safari", "");
        webView.getSettings().setUserAgentString(userAgent);
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
       // webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); // 没有网络使用缓存。

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            webView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        }
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                Log.v("aaaa", "error=" + description + "--" + failingUrl);
            }
        });
        webView.setWebChromeClient(chrome);
    }

    WebChromeClient chrome = new WebChromeClient() {
        public void onConsoleMessage(String message, int lineNumber, String sourceID) {
            Log.v("aaaa", message + " -- From line " + lineNumber + " of " + sourceID);
        }

    };



    @JavascriptInterface
    public void closeLoader(String a, String b) {

    }


    @JavascriptInterface
    public void openLoader(String a, String b) {

    }





}
