package design.factory;

/**
 * Created by abc on 2015/7/2.
 */
public class MyFactory {

    public Pizza createPizza(String type){
        Pizza pizza=null;
        if(type.equals("a")){
            pizza=new APizza();
        }else if(type.equals("b")){
            pizza=new BPizza();
        }
        return pizza;
    }

    public void test(){
        APizza pizza=(APizza)createPizza("a");
        pizza.all();

    }

}
