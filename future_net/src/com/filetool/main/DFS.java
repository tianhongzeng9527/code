package com.filetool.main;

import java.util.Map;
import java.util.Stack;
import java.util.Vector;

public class DFS {
    private Stack<Node> nodeStack;
    private Node startNode;
    private Node endNode;
    private int minPathLength;
    private Stack<Node> min_path;
    private Map<Integer, Vector<Node>> graph;
    public int sum = 0;
    private int depthLimit = 0;
    private int curentDepth = 0;

    public DFS(Map<Integer, Vector<Node>> graph, Node _startNode,
               Node _endNode, int depthLimit) {
        this.depthLimit = depthLimit;
        this.graph = graph;
        minPathLength = -1;
        nodeStack = new Stack<>();
        min_path = new Stack<>();
        this.startNode = _startNode;
        this.endNode = _endNode;
    }

    public void getAvailablePath() {
        DFSImplementation();
    }

    private Node getUnvisitedChildNode(Node parent) {
        Node result = null;
        Vector<Node> vector = graph.get(parent.val);
        int i = 0;
        for(Node node1: vector){
            if((vistied[node1.val] != 1) && (!node1.isVisited)){
                result = node1;
                result.isVisited = true;
                vistied[node1.val] = 1;
                break;
            }
//            if(i >= 2){
//                break;
//            }
            i++;
        }
        return result;
    }
    private void setChildUnVistited(Node parent){
        Vector<Node> vector = graph.get(parent.val);
        for(Node node1: vector){
            node1.isVisited = false;
        }
    }

    int[] vistied = new int[600];
    private void DFSImplementation() {
        vistied[startNode.val] = 1;
        nodeStack.add(startNode);
        while (!nodeStack.isEmpty()) {
            Node node = nodeStack.peek();
            Node childNode = getUnvisitedChildNode(node);
            if (childNode != null) {
                nodeStack.add(childNode);
                curentDepth++;
                if(childNode.val == endNode.val){
                    curentDepth = 0;
                    if (getPathNodeVal(nodeStack).containsAll(Main.list)) {
                        sum++;
                        int tmpPathLength = getPathLength(nodeStack);
                        if (minPathLength == -1) {
                            minPathLength = tmpPathLength;
                            stackToVector(min_path, nodeStack);
                        } else if (minPathLength > tmpPathLength) {
                            minPathLength = tmpPathLength;
                            stackToVector(min_path, nodeStack);
                        }
                    }
                    vistied[nodeStack.peek().val] = 0;
                    setChildUnVistited(nodeStack.peek());
                    nodeStack.pop();
                }
                else{
                    if(Main.list.contains(childNode.val)){
                        curentDepth = 0;
                    }
                }
            } else {
                vistied[nodeStack.peek().val] = 0;
                setChildUnVistited(nodeStack.peek());
                nodeStack.pop();
            }
        }
    }

    public void printMinPath(){
        for(Node node : min_path){
            System.out.print(node.val+" ");
        }
        System.out.println();
    }

    private void printLength(Stack<Node> path){
        for(Node node : path){
            System.out.print(node.val+"   ");
        }
        System.out.println();
    }

    private int getPathLength(Stack<Node> path) {
        int sum = 0;
        for (Node node : path) {
            sum += node.weight;
        }
        return sum;
    }

    private Vector<Integer> getPathNodeVal(Stack<Node> path) {
        Vector<Integer> vector = new Vector<>();
        for (Node node : path) {
            vector.add(node.val);
        }
        return vector;
    }

    private void stackToVector(Vector<Node> vector, Stack<Node> stack){
        vector.clear();
        for(Node node: stack){
            vector.add(node);
        }
    }
}
