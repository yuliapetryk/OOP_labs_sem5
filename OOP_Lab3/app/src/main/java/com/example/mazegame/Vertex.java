package com.example.mazegame;

public class Vertex {

    int key;
    Integer[] edges;
    boolean visited;
    int distance;
    Vertex previous;
    GameActivity.Color color;
    final int INFINITY = 10000;

    public Vertex(int key) {
        this.key = key;
        this.visited = false;
        this.distance = INFINITY;
        this.previous = null;
        this.color = GameActivity.Color.WHITE;
    }

    public void setVisitedToTrue() { this.visited = true; }

    public void setDistance(int d) { this.distance = d; }

    public void setPrevious(Vertex v) { this.previous = v; }

    public void setColor(GameActivity.Color color) { this.color = color; }
}
