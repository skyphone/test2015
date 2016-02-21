package design.factory.fact1;

/**
 * Created by abc on 2015/7/20.
 */
public class Test {

    public void main(){
        ShapeFactory factory=new ShapeFactory();
        Shape shape1=factory.getShare("Circle");
        shape1.draw();

        Shape shape2=factory.getShare("Square");
    }


}
