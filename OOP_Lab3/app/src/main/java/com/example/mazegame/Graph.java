package com.example.mazegame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Graph {

    int size;
    Vertex[] V;

    public Graph(int n) {
        this.size = n*n;
        this.V = new Vertex[size];
        int counter = 0;

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {

                V[counter] = new Vertex(counter);

                boolean top = (r == 0) ? false : true;
                boolean right = (c == n - 1) ? false : true;
                boolean bottom = (r == n - 1) ? false : true;
                boolean left = (c == 0) ? false : true;

                ArrayList<Integer> edgesArrayList = new ArrayList<Integer>();
                if (top) {
                    edgesArrayList.add(n * (r - 1) + c); // n * r + c
                }
                if (right) {
                    edgesArrayList.add(n * r + (c + 1));
                }
                if (bottom) {
                    edgesArrayList.add(n * (r + 1) + c);
                }
                if (left) {
                    edgesArrayList.add(n * r + (c - 1));
                }
                V[counter].edges = new Integer[edgesArrayList.size()];
                Collections.shuffle(edgesArrayList);
                V[counter].edges = edgesArrayList.toArray(V[counter].edges);

                counter++;
            }
        }
    }

    public Graph negateGraph() {

        int n = ( (Double) Math.sqrt(this.size) ).intValue();
        Graph result = new Graph(n);

        for (int i = 0; i < result.size; i++) {

            Integer[] oldEdges = this.V[i].edges;
            Integer[] newAdjacencyList = result.V[i].edges;
            ArrayList<Integer> edgesToRemove = new ArrayList<Integer>();
            for (int e = 0; e < newAdjacencyList.length; e++) {
                if (Arrays.asList(oldEdges).contains(newAdjacencyList[e])) {
                    edgesToRemove.add(newAdjacencyList[e]);
                }
            }
            for (int newE = 0; newE < edgesToRemove.size(); newE++) {
                newAdjacencyList = GameActivity.removeValueFromArray(newAdjacencyList, edgesToRemove.get(newE));
            }

            result.V[i].edges = newAdjacencyList;
        }
        return result;
    }
}
