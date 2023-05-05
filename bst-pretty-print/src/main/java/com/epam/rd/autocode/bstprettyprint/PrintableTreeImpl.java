package com.epam.rd.autocode.bstprettyprint;

import java.util.Arrays;
import java.util.stream.Collectors;

public class PrintableTreeImpl implements PrintableTree{
    private Node root = null;
    @Override
    public void add(int value) {
        if(root == null) {
            root = new Node(value);
            return;
        }
        Node current = root;
        while(current!=null)
            if ((value == current.getData()))
                return;
            else if(value < current.getData())
                if(current.getLeft() == null) {
                    current.setLeft(new Node(value));
                    break;
                }
                else
                    current = current.getLeft();
            else
            if(current.getRight() == null) {
                current.setRight(new Node(value));
                break;
            }
            else
                current = current.getRight();
    }

    @Override
    public String prettyPrint() {
        StringBuilder treeSB = new StringBuilder();
        prettyString(root, new StringBuilder("\n"), treeSB);
        String str = treeSB.substring(1);
        return removeSpaces(str);
    }

    private void prettyString(Node node, StringBuilder lineSB, StringBuilder treeSB)
    {
        if (node == null) return;

        int depth = lineSB.length();
        int i = "\n │".indexOf(lineSB.charAt(depth - 1));
        int j = (node.getLeft() != null ? 2 : 0) + (node.getRight() != null ? 1 : 0);

        lineSB.append(spaces(node.getSize() + 1));
        prettyString(node.getLeft(), lineSB, treeSB);
        lineSB.setLength(depth - 1);

        treeSB.append(lineSB);

        treeSB.append("\n┌└".charAt(i));
        treeSB.append(node.getData());
        treeSB.append(" ┐┘┤".charAt(j));

        lineSB.append("\n│ ".charAt(i));
        lineSB.append(spaces(node.getSize()));
        lineSB.append(" │ │".charAt(j));
        prettyString(node.getRight(), lineSB, treeSB);
    }
    private String removeSpaces(String str)
    {
        return Arrays.stream(str.split("\n"))
                .map(s -> s.charAt(s.length() - 1) == ' ' ? s.substring(0,s.length() - 1) : s)
                .collect(Collectors.joining("\n")) + "\n";
    }
    private String spaces(int number)
    {
        return " ".repeat(number);
    }

}
class Node {
    private final int data;
    private Node left = null;
    private Node right = null;

    public Node(int data) {
        this.data = data;
    }

    public int getSize() {
        return Integer.toString(data).length();
    }

    public int getData() {
        return data;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
