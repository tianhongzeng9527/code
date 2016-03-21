package com.filetool.main;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;


public class InputGraph {

    public Map<Integer, Vector<Node>> graph;
    public Vector<Node> nodes = null;

    public InputGraph() {

        graph = new HashMap<Integer, Vector<Node>>();
        for (int i = 0; i < 25; i++) {
            nodes = new Vector<>();
            for (int j = 1; j <= 8; j++) {
                nodes.add(new Node((i + j) % 25));
            }
            graph.put(i, nodes);
        }
//		//for node A
//		nodes = new Vector<Node>();
//		nodes.add(new Node(2));
//		nodes.add(new Node(3));
//		nodes.add(new Node(5));
//		graph.put(1, nodes);
//
//
//
//
//		// for node B
//		nodes = new Vector<Node>();
//		nodes.add(new Node(1));
//		nodes.add(new Node(3));
//		nodes.add(new Node(4));
//		graph.put(2, nodes);
//
//		// for node C
//		nodes = new Vector<Node>();
//		nodes.add(new Node(4));
//		graph.put(3, nodes);
//
//
//		// for node D
//		nodes = new Vector<Node>();
//		nodes.add(new Node(3));
//		graph.put(4, nodes);
//
//
//		// for node E
//		nodes = new Vector<Node>();
//		nodes.add(new Node(6));
//		nodes.add(new Node(4));
//		graph.put(5, nodes);
//
//
//		// for node F
//		nodes = new Vector<Node>();
//		nodes.add(new Node(3));
//		graph.put(6, nodes);


    }

    public Map<Integer, Vector<Node>> getInput() {

        return graph;
    }

}
