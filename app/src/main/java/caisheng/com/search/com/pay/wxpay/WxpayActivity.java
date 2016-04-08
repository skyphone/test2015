package caisheng.com.search.com.pay.wxpay;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.security.MessageDigest;
import java.util.LinkedList;
import java.util.List;

import caisheng.com.search.R;


//https://github.com/cheyiliu/All-in-One/wiki/%E5%BE%AE%E4%BF%A1%E6%94%AF%E4%BB%98
public class WxpayActivity extends Activity {

    IWXAPI msgApi;
    PayReq req;
    public static final String APP_ID = "wxd803";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxpay);
    }

    public void wxpay(View v) {
        genPayReq();
    }


    /**
     * 3)调起支付
     * 商户服务器生成支付订单，先调用统一下单API(详见第7节)生成预付单，
     * 获取到prepay_id后将参数再次签名传输给APP发起支付。以下是调起微信支付的关键代码：
     */
    private void genPayReq() {
        //商户APP工程中引入微信JAR包，调用API前，需要先向微信注册您的APPID
        msgApi = WXAPIFactory.createWXAPI(this, null);
        msgApi.registerApp(APP_ID);
//        msgApi.isWXAppInstalled();
        req = new PayReq();
        req.appId = APP_ID;
        req.partnerId = "130191";
        req.prepayId = "wx201604081456136674c0425284574";
        req.packageValue = "Sign=WXPay";
        req.nonceStr = "mjwDVlDzv3";
        req.timeStamp = genTimeStamp() + "";
        req.sign = genAppSign();
        msgApi.sendReq(req);

    }

    private String genAppSign() {
        List<NameValuePair> params = new LinkedList<NameValuePair>();
        params.add(new BasicNameValuePair("appid", req.appId));
        params.add(new BasicNameValuePair("noncestr", req.nonceStr));
        params.add(new BasicNameValuePair("package", req.packageValue));
        params.add(new BasicNameValuePair("partnerid", req.partnerId));
        params.add(new BasicNameValuePair("prepayid", req.prepayId));
        params.add(new BasicNameValuePair("timestamp", req.timeStamp));
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < params.size(); i++) {
            sb.append(params.get(i).getName());
            sb.append('=');
            sb.append(params.get(i).getValue());
            sb.append('&');
        }
        sb.append("key=");
        sb.append("XdokzkMmnwf1JFPTs8Y");// 注意：不能hardcode在客户端，建议genPackage这个过程都由服务器端完成
        String appSign = getMessageDigest(sb.toString().getBytes()).toUpperCase();

        return appSign;
    }


    private long genTimeStamp() {
        return System.currentTimeMillis() / 1000;
    }

    private final String getMessageDigest(byte[] buffer) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(buffer);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }


}
