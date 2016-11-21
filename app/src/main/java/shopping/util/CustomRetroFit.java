package shopping.util;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *日志输出
 *@auth ccs
 *create at 2016/3/28 23:22
 */
public class CustomRetroFit {
    private static Interceptor interceptor=new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();
            //公共的head
            Request.Builder requestBuilder = original.newBuilder()
                    .header("Content-Type","application/json")
                    .header("charset", "utf-8")
                    .header("access-Key","123456789")
                    .method(original.method(), original.body());

            Request request = requestBuilder.build();
            return chain.proceed(request);
        }
    };

    private static OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor)
            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build();



    private static Retrofit retrofit=new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).baseUrl("http://120.76.153.166:1100/").client(okHttpClient).build();

    public static Retrofit getRetrofit(){
        return retrofit;
    }



}
