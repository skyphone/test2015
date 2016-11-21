package shopping;

import android.app.Activity;
import android.os.Bundle;


/**
 * <br/> Description: jxjava demo   http://gank.io/post/560e15be2dca930e00da1083
 * FuncX和ActionX的区别。FuncX包装的是有返回值的方法，用于Observable的变换、组合等等；ActionX用于包装无返回值的方法，用于subscribe方法的闭包参数
 如果你需要将一个类型的对象经过处理（非异步）直接转化成下一个类型，推荐用map，否则的话就用flatMap。
 * <br/> Author:  chencaisheng
 * <br/> Version: 1.0
 * <br/> Date:  2016-11-21  下午 3:02
 */
public class RetroMultiActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_retro_multi);


//        getInfo1();
//        getInfoMulti();
    }

/*


    //活动签到验证手机号
    @GET("/api/Activity/ActivityMobileCheck")
    Observable<JsonObject > checkMobile(@QueryMap Map<String, String> options);


//单个请求
    public void getInfo1(){
        final Map<String, String> jsonObject = new HashMap<>();
        jsonObject.put("activityId", "40");
        jsonObject.put("mobile", "159866090099");

        Retrofit retrofit = CustomRetrofit.getRetrofit();
        retrofit.create(HomeInterface.class).checkMobile(jsonObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<JsonObject>() {
                    @Override
                    public void onCompleted() {
                        Log.e("json", "complete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("json", "error");
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {
                        Log.e("json", jsonObject.toString());

                    }
                });
    }



//多个请求

    public void getInfoMulti(){
        Retrofit retrofit = CustomRetrofit.getRetrofit();
        retrofit.create(HomeInterface.class).getMyCommerces("4089")
                .flatMap(new Func1<JsonObject, Observable<JsonObject>>() {
                    @Override
                    public Observable<JsonObject> call(JsonObject jsonObject) {

                        Log.e("first", jsonObject.toString());
                        Retrofit retrofit = CustomRetrofit.getRetrofit();

                        return retrofit.create(HomeInterface.class).getStarList(2);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<JsonObject>() {
                    @Override
                    public void call(JsonObject jsonObject) {

                        Log.e("second", jsonObject.toString());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
    }





        //简单的操作
        Observable.create(new Observable.OnSubscribe<Drawable>() {
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                Drawable dr = getResources().getDrawable(R.drawable.ic_launcher
                );
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                subscriber.onNext(dr);
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .subscribe(new Observer<Drawable>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Drawable drawable) {

                        imageview.setImageDrawable(drawable);
                    }
                });



      //Action1


        String[] names={"sad","asdfasdf"};
        Observable.from(names).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Toast.makeText(RxjavaActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });



        //简单的变换。string to bitmap.

        Observable.just("img/sdas.png").map(new Func1<String, Bitmap>() {
            @Override
            public Bitmap call(String s) {

                return null;
            }
        }).subscribe(new Action1<Bitmap>() {
            @Override
            public void call(Bitmap bitmap) {

            }
        });


    */
}
