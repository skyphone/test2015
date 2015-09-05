package caisheng.com.search;

import android.os.Bundle;
import android.webkit.WebView;

public class HtmlActivity extends BaseHtmlActivity {
     String homeUrl = "file:///android_asset/dist/index.html#!http://app.aolaigo.com/home.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html);
        webView=(WebView)findViewById(R.id.home);
        initView();
        webView.loadUrl(homeUrl);

    }


}
