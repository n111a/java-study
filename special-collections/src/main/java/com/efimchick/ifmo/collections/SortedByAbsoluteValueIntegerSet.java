package com.efimchick.ifmo.collections;

import java.util.*;

class SortedByAbsoluteValueIntegerSet implements SortedSet {
    //using logic of simple linked list
    Element first;


    @Override
    public Comparator comparator() {
        return new Comparator() {
            @Override
            //for comparing elements by absolut value
            public int compare(Object o1, Object o2) {
                return Math.abs((Integer) o1 > Math.abs((Integer) o2) ? 1:-1);
            }
        } ;
    }

    @Override
    public SortedSet subSet(Object fromElement, Object toElement) {
        return null;
    }

    @Override
    public SortedSet headSet(Object toElement) {
        return null;
    }

    @Override
    public SortedSet tailSet(Object fromElement) {
        return null;
    }

    @Override
    public Object first() {
        return null;
    }

    @Override
    public Object last() {
        return null;
    }

    @Override
    public int size() {
        Element current = first;
        if(current == null)
            return 0;
        int size = 1;
        while(current.getNextElement()!=null) {
            current = current.getNextElement();
            size++;
        }
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        Element current = first;
        do{
            if(current.getData() == (Integer) o)
                return true;
            current = current.getNextElement();
        }
        while(current.getNextElement()!=null) ;
        return false;
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            Element current = first;
            @Override
            public boolean hasNext() {

                if(current != null)
                    return true;

                return false;
            }

            @Override
            public Object next() {
                Integer ret = current.getData();
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
    public boolean add(Object o) {
        Element newElement = new Element((Integer) o);
        if(first == null) {
            this.first = new Element((Integer) o);
        }
        else
        {
            Element current = this.first;
            //finding place for new element
            while(current.getNextElement()!=null)
            {
                if(Math.abs((Integer) o) > Math.abs(current.getNextElement().getData()))
                    current = current.getNextElement();
                else
                    break;
            }
            //linking with new element
            newElement.setNextElement(current.getNextElement());
            current.setNextElement(newElement);
        }

        return false;
    }

    @Override
    public boolean remove(Object o) {
        Element last = this.first;
        Element current;
        if(last.getData() == (Integer) o)
            this.first = last.getNextElement();
        //find element o and rewrite links missing this element
        while(last.getNextElement() != null)
        {
            current = last.getNextElement();
            if(current.getData() == (Integer) o) {
                last.setNextElement(current.getNextElement());
                break;
            }
            last = current;
        }

        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        for(Object i: c)
            add(i);
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return a;
    }
}
class Element {
    //store element data and link on next element
    private Integer data;
    private Element nextElement;


    public Element(Integer data) {

        this.data = data;
    }

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    public Element getNextElement() {
        return nextElement;
    }

    public void setNextElement(Element nextElement) {
        this.nextElement = nextElement;
    }

}


