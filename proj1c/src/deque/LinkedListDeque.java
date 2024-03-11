package deque;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>{
    private class Node{
        public Node prev;
        public Node next;
        public T item;


        public Node(Node prev, Node next, T item)
        {
            this.prev = prev;
            this.next = next;
            this.item = item;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next= sentinel;
        size = 0;
    }

    public boolean contains(T targetItem)
    {
        for(T item : this)
        {
            if(item.equals(targetItem))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString()
    {
        return this.toList().toString();
    }
    @Override
    public boolean equals(Object other){
        if(other instanceof LinkedListDeque otherLLDq)
        {
            if(otherLLDq.size() == this.size)
            {
                for(T item : this)
                {
                    if(!otherLLDq.contains(item))
                    {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;

    }
    @Override
    public Iterator<T> iterator()
    {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T>{
        private int pos;
        private Node pointer;

        public LinkedListDequeIterator()
        {
            pos= 0;
            pointer = sentinel.next;
        }

        @Override
        public boolean hasNext(){
            return pos < size;
        }

        @Override
        public T next(){
             T returnValue = pointer.item;
             pointer= pointer.next;
             pos++;
             return returnValue;
        }

    }


    @Override
    public void addFirst(T x) {
        Node newNode = new Node(sentinel,sentinel.next,x);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size++;
    }

    @Override
    public void addLast(T x) {
        Node newNode = new Node(sentinel.prev,sentinel,x);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<T>();
        Node pointer = sentinel.next;
        while(pointer != sentinel)
        {
            returnList.add(pointer.item);
            pointer = pointer.next;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {

        return sentinel.next==sentinel ;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if(isEmpty())
        {
            return null;
        }else{
            Node removedNode = sentinel.next;
            T item = removedNode.item;
            removedNode.item = null;

            sentinel.next = removedNode.next;
            removedNode.next.prev = sentinel;
            removedNode.prev=null;
            removedNode.next=null;

            return item;
        }
    }

    @Override
    public T removeLast() {
        if(isEmpty())
        {
            return null;
        }else{
            Node removedNode = sentinel.prev;
            T item = removedNode.item;
            removedNode.item = null;

            sentinel.prev = removedNode.prev;
            removedNode.prev.next = sentinel;
            removedNode.prev=null;
            removedNode.next=null;

            return item;
        }
    }

    @Override
    public T get(int index) {
        if(index < 0 || index >= size)
        {
            return null;
        }else{
            Node p = sentinel.next;
            for(int i = 0; i < index; i++)
            {
                p = p.next;
            }
            return p.item;
        }

    }

    private T getRecursive(Node start, int index)
    {
        if(index == 0)
        {
            return start.item;
        }else{
            return getRecursive(start.next, index-1);
        }
    }

    @Override
    public T getRecursive(int index) {
        if(index < 0 || index >= size)
        {
            return null;
        }else{
            return getRecursive(sentinel.next, index);
        }
    }

}
