package design.factory;

/**
 * Created by abc on 2015/7/2.
 */
public abstract class PizzaClient {


    public Pizza orderPizza(String type){
        Pizza pizza=null;
        pizza=createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.box();
        pizza.cut();
        return pizza;
    }

    public abstract Pizza createPizza(String type);
}
