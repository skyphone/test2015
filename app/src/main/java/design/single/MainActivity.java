package design.single;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.PhoneNumberUtils;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


/*js 调用安卓方法   callPhone.html 文件*/


public class MainActivity extends Activity {

    WebView mWebView;
    Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mWebView = new WebView(this);
        setContentView(mWebView);
        initWebViewListener();

    }


    private void initWebViewListener() {
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.requestFocus();
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return true;
            }
        });
        mWebView.addJavascriptInterface(this, "android_js");
        mWebView.loadUrl("file:///android_asset/callPhone.html");
    }

        @JavascriptInterface
        public void callPhone(final String str) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "请输入您的手机号码", Toast.LENGTH_SHORT).show();
                    if (str.length() == 0) {
                        Toast.makeText(getApplicationContext(), "请输入您的手机号码", Toast.LENGTH_SHORT).show();
                    } else {
                        boolean ok = true;
                        char[] tmp = str.toCharArray();
                        for (char aTmp : tmp) {
                            if (!PhoneNumberUtils.isReallyDialable(aTmp)) {
                                ok = false;
                                break;
                            }
                        }
                        if (ok) {
                            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + str));
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "电话号码的格式错误", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }
}
