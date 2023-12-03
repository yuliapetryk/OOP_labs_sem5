package com.example.mazegame;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class LongestPath {

    public static Integer[] findLongestPath(Graph graph) {

        Graph transposedGraph = graph.negateGraph();

        ArrayList<Integer> resultArray = new ArrayList<Integer>();

        double n = Math.sqrt(transposedGraph.size);
        int startIndex = ( (Double) (transposedGraph.size - n) ).intValue();

        Vertex start = transposedGraph.V[startIndex];

        int maxDistance = 0;
        boolean isOuter;
        boolean isStart;
        Vertex farthestVertex = start;
        start.setColor(GameActivity.Color.GRAY);
        start.setDistance(0);
        Queue<Vertex> Q = new LinkedList<Vertex>();
        Q.add(start);

        while (!Q.isEmpty()) {
            Vertex u = Q.remove();

            for (int i = 0; i < u.edges.length; i++) {

                if (u.edges.length > 0) {
                    if (transposedGraph.V[u.edges[i]].color == GameActivity.Color.WHITE) {
                        transposedGraph.V[u.edges[i]].setColor(GameActivity.Color.GRAY);
                        transposedGraph.V[u.edges[i]].setDistance(u.distance + 1);
                        isOuter = ((u.edges[i] + 1) % n < 2 || u.edges[i] < n || u.edges[i] > (n * (n - 1) - 1));
                        isStart = (transposedGraph.V[u.edges[i]].equals(start));

                        if (transposedGraph.V[u.edges[i]].distance > maxDistance && isOuter && !isStart) {
                            maxDistance = u.distance + 1;
                            farthestVertex = transposedGraph.V[u.edges[i]];
                        }
                        transposedGraph.V[u.edges[i]].setPrevious(u);
                        Q.add(transposedGraph.V[u.edges[i]]);
                    }
                }

            }
            u.setColor(GameActivity.Color.BLACK);
        }

        Vertex currVertex = farthestVertex;
        resultArray.add(currVertex.key);
        while (currVertex.previous != null) {
            currVertex = currVertex.previous;
            resultArray.add(currVertex.key);
        }
        Integer[] result = new Integer[resultArray.size()];
        resultArray.toArray(result);
        return result;
    }
}
