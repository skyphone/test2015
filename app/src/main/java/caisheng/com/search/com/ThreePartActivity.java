//package caisheng.com.search.com;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Toast;
//
//import java.util.HashMap;
//
//import cai.com.myapplication.R;
//import cn.sharesdk.framework.Platform;
//import cn.sharesdk.framework.PlatformActionListener;
//import cn.sharesdk.framework.ShareSDK;
//import cn.sharesdk.onekeyshare.OnekeyShare;
//import cn.sharesdk.tencent.qq.QQ;
//
///**
// * http://wiki.mob.com/%E7%AC%AC%E4%B8%89%E6%96%B9%E7%99%BB%E5%BD%95/
// * 要数据，不要功能
//
// 如果你的应用拥有用户系统，就是说你的应用自己就有注册和登录功能，使用第三方登录只是为了拥有更多用户，那么你可以依照下面的步骤来做：
//
// 1、用户触发第三方登录事件
// 2、showUser(null)请求授权用户的资料（这个过程中可能涉及授权操作）
// 3、如果onComplete()方法被回调，将其参数Hashmap代入你应用的Login流程
// 4、否则提示错误，调用removeAccount()方法，删除可能的授权缓存数据
// 5、Login时客户端发送用户资料中的用户ID给服务端
// 6、服务端判定用户是已注册用户，则引导用户进入系统，否则返回特定错误码
// 7、客户端收到“未注册用户”错误码以后，代入用户资料到你应用的Register流程
// 8、Register时在用户资料中挑选你应用的注册所需字段，并提交服务端注册
// 9、服务端完成用户注册，成功则反馈客户端引导用户进入系统
// 10、否则提示错误，调用removeAccount()方法，删除可能的授权缓存数据
// 要功能，不要数据
//
// 如果你的应用不具备用户系统，而且也不打算维护这个系统，那么你可以依照下面的步骤来做：
//
// 1、用户触发第三方登录事件
// 2、调用platform.getDb().getUserId()请求用户在此平台上的ID
// 3、如果用户ID存在，则认为用户是合法用户，允许进入系统；否则调用authorize()
// 4、authorize()方法将引导用户在授权页面输入帐号密码，然后目标平台将验证此用户
// 5、如果onComplete()方法被回调，表示授权成功，引导用户进入系统
// 6、否则提示错误，调用removeAccount()方法，删除可能的授权缓存数据
// */
//public class ThreePartActivity extends Activity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.content_three_part);
//        ShareSDK.initSDK(this);
//    }
//
//
//
//    public void login(View v){
//        Platform platQq= ShareSDK.getPlatform(this, QQ.NAME);
////        platQq.removeAccount();
//        platQq.setPlatformActionListener(new PlatformActionListener() {
//            @Override
//            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
//
//            }
//
//            @Override
//            public void onError(Platform platform, int i, Throwable throwable) {
//
//            }
//
//            @Override
//            public void onCancel(Platform platform, int i) {
//
//            }
//        });
//        platQq.SSOSetting(false);
//        platQq.showUser(null);
//        Toast.makeText(ThreePartActivity.this, platQq.getDb().getUserId() == null ? "" : platQq.getDb().getUserId(), Toast.LENGTH_SHORT).show();
//
//    }
//      public void shareqq(View v){
//              ShareSDK.initSDK(this);
//              OnekeyShare oks = new OnekeyShare();
//              //关闭sso授权
//              oks.disableSSOWhenAuthorize();
//
//            // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
//              //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
//              // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
//              oks.setTitle("share");
//              // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
//              oks.setTitleUrl("http://sharesdk.cn");
//              // text是分享文本，所有平台都需要这个字段
//              oks.setText("我是分享文本");
//              // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//              oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
//              // url仅在微信（包括好友和朋友圈）中使用
//              oks.setUrl("http://sharesdk.cn");
//              // comment是我对这条分享的评论，仅在人人网和QQ空间使用
//              oks.setComment("我是测试评论文本");
//              // site是分享此内容的网站名称，仅在QQ空间使用
//              oks.setSite(getString(R.string.app_name));
//              // siteUrl是分享此内容的网站地址，仅在QQ空间使用
//              oks.setSiteUrl("http://sharesdk.cn");
//
//            // 启动分享GUI
//              oks.show(this);
//          }
//
//}
