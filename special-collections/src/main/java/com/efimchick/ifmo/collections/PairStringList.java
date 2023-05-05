package com.efimchick.ifmo.collections;

import java.util.*;
import java.util.stream.Collectors;

class PairStringList implements List<String> {
    private Node head;

    PairStringList()
    {
        this.head = null;
    }

    @Override
    public int size() {

        if(this.head == null){
            return 0;
        }else {
            int listSize = 1;
            Node currentNode = head;
            while(currentNode.getNextNode() != null){
                listSize ++;
                currentNode = currentNode.getNextNode();
            }
            return listSize;
        }
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
    public Iterator<String> iterator() {

        return new Iterator<String>() {
            Node currentNode = head;

            @Override
            public boolean hasNext() {
                if (currentNode != null)
                    return true;
                else
                    return false;
            }
            @Override
            public String next() {
                String element = currentNode.getData();
                currentNode = currentNode.getNextNode();
                return element;
            }
        };
    }

    @Override
    public Object[] toArray() {
        String[] array = new String[this.size()];
        Iterator iterator = iterator();
        int i=0;
        while(iterator.hasNext()) {
            array[i] = (String) iterator.next();
            i++;
        }
        return array;
    }

    @Override
    public <String> String[] toArray(String[] a) {
        return a;
    }

    @Override
    public boolean add(String s) {
        Node newNode1 = new Node(s,1);
        Node newNode2 = new Node(s, 2);
        linkNodes(newNode1,newNode2);
        //if there is no nodes in list, pushes it to the head
        if(this.head == null){
            head = newNode1;
        }else {
            Node currentNode = head;
            //find last node in the list and add new first after it
            while(currentNode.getNextNode() != null){
                currentNode = currentNode.getNextNode();
            }
            currentNode.setNextNode(newNode1);
            newNode1.setPreviousNode(currentNode);

        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Node currentNode = this.head;
        int size = size();
        for(int i = 0; i < size; i++)
        {
            //find node with Object o value
            if(currentNode.getData().equals((String)o))
            {
                //case if node in the head of list. Set new head
                if(i == 0) {
                    this.head = currentNode.getNextNode().getNextNode();
                    this.head.setPreviousNode(null);
                }
                //delete links on pair of nodes
                else {
                    currentNode.getPreviousNode().setNextNode(currentNode.getNextNode().getNextNode());
                    currentNode.getNextNode().getNextNode().setPreviousNode(currentNode.getPreviousNode());
                }
                currentNode = currentNode.getNextNode().getNextNode();
            }
        }
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends String> c) {
        c.stream().forEach(str -> add(str));
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        for(String str: c)
        {
            add(index,str);
            index+=2;
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        this.head = null;
    }

    @Override
    public String get(int index) {
        Node currentNode = head;
        if(index > size())
            return null;
        //find node by index
        for(int i =0; i<index; i++)
        {
            currentNode = currentNode.getNextNode();
        }

        return currentNode.getData();
    }

    @Override
    public String set(int index, String element) {
        Node currentNode = this.head;
        for(int i = 0; i<index; i++)
            currentNode = currentNode.getNextNode();

        //set value for another node from pair
        if(currentNode.getNum() == 2)
            currentNode.getPreviousNode().setData(element);
        else
            currentNode.getNextNode().setData(element);
        currentNode.setData(element);
        return element;
    }

    @Override
    public void add(int index, String element) {
        Node currentNode = this.head;
        Node newNode1 = new Node(element,1);
        Node newNode2 = new Node(element, 2);
        linkNodes(newNode1,newNode2);

        if (index != 0){
            for(int i = 0; i<index; i++)
                currentNode = currentNode.getNextNode();

            if(currentNode.getNum() == 1)
                currentNode = currentNode.getPreviousNode(); //if index of first node of pair, getting second node
            newNode2.setNextNode(currentNode.getNextNode());
            currentNode.getNextNode().setPreviousNode(newNode2);
            currentNode.setNextNode(newNode1);
            newNode1.setPreviousNode(currentNode);
        }
        else
        {
            // add node in start of list
            newNode2.setNextNode(currentNode);
            currentNode.setPreviousNode(newNode2);
            this.head = newNode1;
        }


    }

    @Override
    public String remove(int index) {
        //delete pair of nodes on given index
        Node currentNode = this.head;
        if (index >=2) {
            //find element on given index
            for (int i = 0; i < index; i++)
                currentNode = currentNode.getNextNode();
            //in case if index points on second node of pair
            if(currentNode.getNum() == 2) {
                currentNode.getNextNode().setPreviousNode(currentNode.getPreviousNode().getPreviousNode());
                currentNode.getPreviousNode().getPreviousNode().setNextNode(currentNode.getNextNode());
            }
            //in case if index points on first node of pair
            else
            {
                currentNode.getNextNode().getNextNode().setPreviousNode(currentNode.getPreviousNode());
                currentNode.getPreviousNode().setNextNode(currentNode.getNextNode().getNextNode());
            }
        } else {
            //delete two first nodes
            this.head = currentNode.getNextNode().getNextNode();
            this.head.setPreviousNode(null);
        };
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<String> listIterator() {
        return null;
    }

    @Override
    public ListIterator<String> listIterator(int index) {
        return null;
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        return null;
    }

    private void linkNodes(Node node1, Node node2) //set links between two nodes on each other
    {
        node1.setNextNode(node2);
        node2.setPreviousNode(node1);
    }
}

class Node {
    // store links on next and previous nodes, data and serial number in pair
    private String data;
    private Node nextNode;
    private Node previousNode;
    private int num;

    public Node(String data, int num){
        this.num = num;
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }


    public Node getPreviousNode() {
        return previousNode;
    }

    public void setPreviousNode(Node previousNode) {
        this.previousNode = previousNode;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}