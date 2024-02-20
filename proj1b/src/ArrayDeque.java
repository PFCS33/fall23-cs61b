import java.util.List;
import java.util.ArrayList;

public class ArrayDeque<T> implements Deque<T>{
    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int size;


    public ArrayDeque(){
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    private void resize(int capacity){
        T[] newItems = (T[])new Object[capacity];
        for(int i=0; i <size; i++)
        {
            newItems[i] = this.get(i);
        }
        nextFirst = capacity - 1;
        nextLast = size;
        items = newItems;
    }
    @Override
    public void addFirst(T x) {
        int totalLength = items.length;
        if(size == totalLength)
        {
            totalLength *= 2;
            resize(totalLength);
        }
        items[nextFirst] = x;
        size ++;
        nextFirst = (nextFirst - 1 + totalLength) % totalLength;
    }

    @Override
    public void addLast(T x) {
        int totalLength = items.length;
        if(size == totalLength)
        {
            totalLength *= 2;
            resize(totalLength );
        }
        items[nextLast] = x;
        size ++;
        nextLast = (nextLast + 1 ) % totalLength;
    }

    @Override
    public List toList() {
        List<T> returnedList = new ArrayList<>();
        for(int i = 0; i < size; i++)
        {
            returnedList.add( this.get(i));
        }
        return returnedList;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        int totalLength = items.length;
        double usageFactor = (double)(size-1) / totalLength;
        if(usageFactor < 0.25)
        {
            totalLength /= 2;
            resize(totalLength);
        }

        nextFirst = (nextFirst + 1 ) % totalLength;
        T returnedItem = items[nextFirst];
        items[nextFirst] = null;
        size--;
        return returnedItem;
    }

    @Override
    public T removeLast() {
        int totalLength = items.length;
        double usageFactor = (double)(size-1) / totalLength;
        if(usageFactor < 0.25)
        {
            totalLength /= 2;
            resize(totalLength);
        }
        nextLast = (nextLast -1 + totalLength ) % totalLength;
        T returnedItem = items[nextLast];
        items[nextLast] = null;
        size--;
        return returnedItem;
    }

    @Override
    public T get(int index) {
        if(index < 0 || index >= size)
        {
            return null;
        }else{
            int totalLength = items.length;
            return items[(nextFirst + 1 + index) % totalLength];
        }
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
        
    }
}
