package otcapp.com.qrcode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import caisheng.com.search.R;

public class QRCodeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
    }

    public void multi(View v){
        Intent startScanner = new Intent(this, SimpleScannerActivity.class);
        startActivity(startScanner);
    }
}
