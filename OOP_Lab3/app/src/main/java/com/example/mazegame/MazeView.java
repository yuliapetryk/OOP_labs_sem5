package com.example.mazegame;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.core.content.ContextCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import java.util.Arrays;
import java.util.Random;

public class MazeView extends View {

    public Paint mazePaint;
    public int screenWidth;
    public int screenHeight;
    int cellWidth;
    int cellHeight;
    int padding;
    DisplayMetrics displaymetrics;
    int mazeSize = 3;
    public boolean[][] verticalLines;
    public boolean[][] horizontalLines;
    public Integer[] listOfSolutionVertecesKeys;
    public int lengthOfSolutionPath;
    public Graph graph;
    public double fractionOfWallsToRemove = 0.15;

    public MazeView(Context context, int size) {
        super(context);
        this.mazeSize = size;
        this.graph = new Graph(size);
        init();
    }

    public MazeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

        displaymetrics = new DisplayMetrics();
        padding = 64;
        ((Activity) getContext()).getWindowManager()
                .getDefaultDisplay()
                .getMetrics(displaymetrics);
        screenWidth = displaymetrics.widthPixels - padding;
        screenHeight = displaymetrics.heightPixels - padding;
        cellWidth = screenWidth / mazeSize;
        cellHeight = cellWidth;

        verticalLines = new boolean[mazeSize][mazeSize + 1];
        horizontalLines = new boolean[mazeSize + 1][mazeSize];

        for (int i = 0; i < mazeSize; i++) {
            Arrays.fill(verticalLines[i], Boolean.TRUE);
        }
        for (int j = 0; j < mazeSize + 1; j++) {
            Arrays.fill(horizontalLines[j], Boolean.TRUE);
        }
        horizontalLines[mazeSize][0] = false;
        int graphStartKey = ( (Double) (graph.size - Math.sqrt(graph.size)) ).intValue();
        graph.V[graphStartKey].visited = true;

        MazeBuilder.carveWay(graph, graph.V[graphStartKey], this);

        Random rand = new Random();
        for (int holes = 0; holes < Math.floor(Math.pow(fractionOfWallsToRemove * mazeSize, 2)); holes++) {

            int randomXvertical = rand.nextInt(mazeSize - 1);
            int randomYvertical = rand.nextInt(mazeSize - 1) + 1;
            verticalLines[randomXvertical][randomYvertical] = false;

            int randomXhorizontal = rand.nextInt(mazeSize - 1) + 1;
            int randomYhorizontal = rand.nextInt(mazeSize - 1);
            horizontalLines[randomXhorizontal][randomYhorizontal] = false;
        }

        MazeBuilder.removeEdges(this);

        listOfSolutionVertecesKeys = LongestPath.findLongestPath(graph);

        lengthOfSolutionPath = listOfSolutionVertecesKeys.length;

        int endKey = listOfSolutionVertecesKeys[0];

        boolean horizontalEnd = (endKey < mazeSize || endKey >= mazeSize * (mazeSize - 1)) ? true : false;
        if (horizontalEnd) {
            if (endKey < mazeSize) {
                horizontalLines[0][endKey] = false;
            } else {
                horizontalLines[mazeSize][endKey % mazeSize] = false;
            }
        } else {
            if (endKey % mazeSize == 0) {
                verticalLines[endKey / mazeSize][0] = false;
            } else {
                verticalLines[endKey / mazeSize][mazeSize] = false;
            }
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for(int i = 0; i < mazeSize + 1; i++) {
            for(int j = 0; j < mazeSize + 1; j++) {

                float x = j * cellWidth + padding / 2;
                float y = i * cellHeight + padding / 2;

                if(i < mazeSize && verticalLines[i][j]) {
                    canvas.drawLine(x,
                            y,
                            x,
                            y + cellHeight,
                            mazePaint);
                }

                if(j < mazeSize && horizontalLines[i][j]) {

                    canvas.drawLine(x,
                            y,
                            x + cellWidth,
                            y,
                            mazePaint);
                }
            }
        }
    }
}
