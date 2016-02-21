package design.observer;

/**
 * Created by abc on 2015/9/6.
 */
public class Desktop2 implements Update {

    WeatherData weatherData;

    public Desktop2(WeatherData w){
        this.weatherData=w;
        weatherData.add(this);
    }

    @Override
    public void update(String text) {

    }
}
