package com.efimchick.ifmo.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

class MedianQueue implements Queue {
    //logic of queue is based on sorted by value in ascending order on simple doubly linked list
    // start element is smallest in the queue
    int length = 0;
    QueueElement start;
    @Override
    public int size() {
        return length;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator iterator() {

        return new Iterator() {
            QueueElement current = start;
            @Override
            public boolean hasNext() {
                if(current != null)
                    return true;
                return false;
            }

            @Override
            public Object next() {
                String ret =  current.getData().toString();
                current = current.getNextElement();
                return ret;
            }
        };
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

    @Override
    public boolean add(Object o) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public void clear() {}

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public boolean offer(Object o) {
        QueueElement newElement = new QueueElement((Integer) o);
        //if queue is empty set first element of it
        if(this.start == null){
            this.start = newElement;
        }else {
            QueueElement current = this.start;
            //case if new element is smallest
            if((Integer) o < current.getData())
            {
                //move first element and place newElement on it's place
                current.setPreviousElement(newElement);
                newElement.setNextElement(current);
                this.start = newElement;
            }
            else {
                int i = 0;
                //finding place for new element
                while (current.getNextElement() != null && i< length) {
                    if ((Integer) o > current.getNextElement().getData())
                        current = current.getNextElement();
                    else
                        break;
                    i++;
                }
                //set links for new element
                newElement.setNextElement(current.getNextElement());
                newElement.setPreviousElement(current);
                //set link of new element in previous and next to newElement
                if(current.getNextElement() != null)
                    current.getNextElement().setPreviousElement(newElement);
                current.setNextElement(newElement);

            }
        }
        length++;
        return false;
    }

    @Override
    public Object remove() {return null;
    }

    @Override
    public Object poll() {
        QueueElement current = this.start;
        int end = length%2 == 0 ? length/2 - 1 :length/2; //find index of central element or in case of even length - smaller from two elements
        for(int i = 0; i<end; i++)
            current = current.getNextElement();
        //rewrite links on polled element
        if(current.getNextElement() != null)
            current.getNextElement().setPreviousElement(current.getPreviousElement());
        if(current.getPreviousElement() != null)
            current.getPreviousElement().setNextElement(current.getNextElement());
        length--;
        if(length == 0)
            start = null;
        //set new start element if only one element remains
        if(length == 1) {
            this.start = current.getNextElement();
            this.start.setPreviousElement(null);
            this.start.setNextElement(null);
        }
        return current.getData();

    }

    @Override
    public Object element() {return null;}

    @Override
    public Object peek() {
        //get the first element on the top of the queue (just gets, without pulling it out)
        QueueElement current = this.start;
        int end = length%2 == 0 ? length/2 - 1 :length/2;
        for(int i = 0; i<end; i++)
            current = current.getNextElement();
        return current.getData();
    }
}
class QueueElement {
    //each element stores links on next and previous ones and data
    private Integer data;
    private QueueElement nextElement;
    private QueueElement previousElement;

    public QueueElement(Integer data){
        this.data = data;
    }


    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    public QueueElement getNextElement() {
        return nextElement;
    }

    public void setNextElement(QueueElement nextElement) {
        this.nextElement = nextElement;
    }

    public QueueElement getPreviousElement() {
        return previousElement;
    }

    public void setPreviousElement(QueueElement previousElement) {
        this.previousElement = previousElement;
    }
}
