package design.factory.fact1;

/**
 * Created by abc on 2015/7/20.
 */
public class ShapeFactory {

    public Shape getShare(String type){
        if(type==null){
            return null;
        }
        if(type.equals("circle")){
            return new Circle();
        }else if(type.equals("Square")){
            return new Square();
        }else if(type.equals("Trial")){
            return new Trial();
        }
        return null;
    }
}
