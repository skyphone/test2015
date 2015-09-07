package observer;

/**
 * Created by abc on 2015/9/6.
 */
public class Test {
    public void test(){
        WeatherData weatherData=new WeatherData();
        Desktop1 desktop1=new Desktop1(weatherData);
        Desktop2 desktop2=new Desktop2(weatherData);
        weatherData.notiAll();
    }


}
