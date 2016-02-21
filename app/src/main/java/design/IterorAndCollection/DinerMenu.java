package design.IterorAndCollection;

/**
 * Created by abc on 2015/7/7.
 */
public class DinerMenu {
    static final int MAX_SIZE=6;
    int numberOfItems=0;
    MenuItem[] items;
    public DinerMenu(){
        items=new MenuItem[MAX_SIZE];
        addItem("abc","asdf");
        addItem("asd","ljl");
    }


    public void addItem(String name,String des){
        MenuItem item=new MenuItem(name,des);
        if(numberOfItems>=items.length){
            System.out.printf("full");
        }else{
            items[numberOfItems]=item;
            numberOfItems++;
        }
    }

    @Deprecated
    public MenuItem[] getItems(){
        return items;
    }

    public MyIterator createIterator(){
        return new DinnerMenuIterator(items);
    }

}
