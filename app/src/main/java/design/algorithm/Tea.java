package design.algorithm;

/**
 * Created by abc on 2015/7/3.
 */
public class Tea {
    void prepareRecipe() {
        boilWater();
        steepTeaBag();
        pourInCup();
        addLemon();
    }

    public void boilWater() {
        System.out.printf("boiling water");
    }

    public void steepTeaBag() {
        System.out.printf("steeming the tea");
    }

    public void addLemon() {
        System.out.printf("adding lemon");
    }

    public void pourInCup() {
        System.out.printf("puring in cup");
    }
}
