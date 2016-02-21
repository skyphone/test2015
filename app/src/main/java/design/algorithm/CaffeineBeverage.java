package design.algorithm;

/**
 * Created by abc on 2015/7/3.
 */
public abstract class CaffeineBeverage {
    void prepareRecipe() {
        boilWater();
        brew();
        purInCup();
        addCondiments();
    }

    abstract void brew();

    abstract void addCondiments();

    void boilWater() {
        System.out.printf("boild water");

    }

    void purInCup() {
        System.out.printf("puring into cup");
    }

}
