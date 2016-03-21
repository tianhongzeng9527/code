package com.filetool.main;


//This will be used as the Queue elemen in the Holder.java

public class Node {

    public int val;
    public boolean visited;
    public int weight;
    public boolean isNecessary;
    public boolean isVisited;


    public Node() {

    }

    public Node(int _val, int weight) {
        this.val = _val;
        this.visited = false;
        this.weight = weight;
        this.isNecessary = false;
    }

    public Node(int _val) {
        this.isNecessary = false;
        this.val = _val;
//	  this.pos = _val-64;
        this.visited = false;
        this.weight = 1;
    }

    public boolean isVisited() {

        return visited;
    }

    public int getNodeVal() {
        return val;
    }

    public boolean equals(Node node){
        return node.val == this.val;
    }

    public boolean isEqual(Node node) {
        return node.val == this.val;
    }

}
