package com.example.mazegame;

public class MazeBuilder {

    public static void carveWay(Graph graph, Vertex v, MazeView mazeView) {
        int n = (int) Math.sqrt(graph.size);

        for (int i = 0; i < v.edges.length; i++) {

            if (v.edges[i] != null && !graph.V[v.edges[i]].visited) {
                Vertex biggerVertex;
                Vertex smallerVertex;
                if (v.key >= graph.V[v.edges[i]].key) {
                    biggerVertex = v;
                    smallerVertex = graph.V[v.edges[i]];
                } else {
                    biggerVertex = graph.V[v.edges[i]];
                    smallerVertex = v;
                }

                int row = biggerVertex.key / n;
                int column = biggerVertex.key % n;

                if (biggerVertex.key / n == smallerVertex.key / n) {
                    mazeView.verticalLines[row][column] = false;
                } else {
                    mazeView.horizontalLines[row][column] = false;
                }

                graph.V[v.edges[i]].setVisitedToTrue();

                carveWay(graph, graph.V[v.edges[i]], mazeView);
            }
        }

    }

    public static void removeEdges(MazeView mazeView) {

        int counter = 0;
        for (int linesRow = 0; linesRow < mazeView.verticalLines.length; linesRow++) {

            for (int linesColumn = 1; linesColumn < mazeView.verticalLines[0].length - 1; linesColumn++) {
                if (mazeView.verticalLines[linesRow][linesColumn] == false) {

                    Integer[] currentEdges = mazeView.graph.V[counter].edges;
                    Integer[] currentEdgesNextVertex = mazeView.graph.V[counter + 1].edges;
                    Integer currentVertexKey = (linesRow * mazeView.mazeSize) + (linesColumn - 1);
                    Integer neighborVertexKey = currentVertexKey + 1;
                    Integer[] newEdgesV = GameActivity.removeValueFromArray(currentEdges, neighborVertexKey);
                    Integer[] newEdgesNeighbor = GameActivity.removeValueFromArray(currentEdgesNextVertex, currentVertexKey);
                    mazeView.graph.V[counter].edges = newEdgesV;
                    mazeView.graph.V[counter + 1].edges = newEdgesNeighbor;
                }
                counter++;
            }
            counter++;
        }

        counter = 0;

        for (int linesRow = 1; linesRow < mazeView.horizontalLines.length - 1; linesRow++) {

            for (int linesColumn = 0; linesColumn < mazeView.horizontalLines[0].length; linesColumn++) {

                if (mazeView.horizontalLines[linesRow][linesColumn] == false) {

                    Integer[] currentEdges = mazeView.graph.V[counter].edges;
                    Integer[] currentEdgesNextVertex = mazeView.graph.V[counter + mazeView.mazeSize].edges;

                    Integer currentVertexKey = ((linesRow - 1) * mazeView.mazeSize) + (linesColumn);
                    Integer neighborVertexKey = currentVertexKey + mazeView.mazeSize;
                    Integer[] newEdgesV = GameActivity.removeValueFromArray(currentEdges, neighborVertexKey);
                    Integer[] newEdgesNeighbor = GameActivity.removeValueFromArray(currentEdgesNextVertex, currentVertexKey);

                    mazeView.graph.V[counter].edges = newEdgesV;
                    mazeView.graph.V[counter + mazeView.mazeSize].edges = newEdgesNeighbor;
                }
                counter++;
            }
        }
    }
}
