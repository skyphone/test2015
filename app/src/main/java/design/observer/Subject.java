package design.observer;

/**
 * Created by abc on 2015/9/6.
 */
public interface Subject {
    void add(Update update);
    void remove(Update update);
    void notiAll();
}
