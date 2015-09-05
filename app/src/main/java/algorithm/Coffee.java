package algorithm;

/**
 * Created by abc on 2015/7/3.
 */
public class Coffee {
    void preparePecipe() {
        boilWater();
        brewCoffeeGrinds();
        pourInCup();
        addSugarAndMilk();
    }

    public void boilWater() {
        System.out.println("boiling water");
    }

    public void brewCoffeeGrinds() {
        System.out.printf("dripping cofff");
    }

    public void pourInCup() {
        System.out.printf("pouring into  cup");
    }

    public void addSugarAndMilk() {
        System.out.printf("adding sugar and milk");
    }

}
