package IterorAndCollection;

/**
 * Created by abc on 2015/7/7.
 */
public class DinnerMenuIterator implements MyIterator {
    MenuItem[] items;
    int position = 0;

    public DinnerMenuIterator(MenuItem[] items) {
        this.items = items;
    }


    @Override
    public boolean isNext() {
        if(position>=items.length||items[position]==null){
            return false;
        }else{
            return true;
        }

    }

    @Override
    public Object next() {
        MenuItem item=items[position];
        position++;
        return item;
    }
}
