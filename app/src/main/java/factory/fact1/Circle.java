package factory.fact1;

/**
 * Created by abc on 2015/7/20.
 */
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.printf("circle");
    }
    public void pr(){
        System.out.printf("abc");
    }
}
