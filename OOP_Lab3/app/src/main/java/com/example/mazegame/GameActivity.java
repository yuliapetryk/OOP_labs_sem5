package com.example.mazegame;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class GameActivity extends AppCompatActivity  {

    public enum Color {
        WHITE, GRAY, BLACK;
    }
    public MazeView mMazeView;
    public FingerLine mFingerLine;
    static ImageView home;
    ImageView arrow;
    public int mazeSize;
    DisplayMetrics displaymetrics = new DisplayMetrics();
    FrameLayout mFrameLayout;
    public final int PADDING = 64;
    public final int FAT_FINGERS_MARGIN = 25;


    @Override
    public  void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

        mFrameLayout = (FrameLayout)findViewById(R.id.mazeWrapper);
        ViewGroup.LayoutParams params = mFrameLayout.getLayoutParams();
        params.height = (int) Math.floor(displaymetrics.heightPixels * 0.7);
        mFrameLayout.setLayoutParams(params);

        ImageButton newMazeButton =  findViewById(R.id.newMazeButton);
        newMazeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                createMaze();
            }
        });
        newMazeButton.performClick();

    }

    public void createMaze() {

        if (mMazeView != null) {
            ((ViewGroup) mMazeView.getParent()).removeView(mMazeView);
        }
        if (mFingerLine != null) {
            ((ViewGroup) mFingerLine.getParent()).removeView(mFingerLine);
        }
        mazeSize = 10;
        mMazeView = new MazeView(this, mazeSize);


        int[][] solutionAreas = new int[mMazeView.lengthOfSolutionPath][4];

        int currentVertexKey;
        int totalMazeWidth = displaymetrics.widthPixels - PADDING;
        int cellSide = totalMazeWidth / mazeSize;
        int row, column;
        int topLeftX, topLeftY, bottomRightX, bottomRightY;

        for (int i = 0; i < mMazeView.lengthOfSolutionPath; i++) {

            currentVertexKey = mMazeView.listOfSolutionVertecesKeys[i];
            row = (currentVertexKey) / mazeSize;
            column = (currentVertexKey) % mazeSize;
            topLeftX = (PADDING / 2) + (column * cellSide) - FAT_FINGERS_MARGIN;
            topLeftY = (PADDING / 2) + (row * cellSide) - FAT_FINGERS_MARGIN;
            bottomRightX = (PADDING / 2) + ((column + 1) * cellSide) + FAT_FINGERS_MARGIN;
            bottomRightY = (PADDING / 2) + ((row + 1) * cellSide) + FAT_FINGERS_MARGIN;
            solutionAreas[i] = new int[]{ topLeftX, topLeftY, bottomRightX, bottomRightY };
        }

        mFrameLayout.addView(mMazeView);
        mFingerLine = new FingerLine(this, null, solutionAreas);
        mFrameLayout.addView(mFingerLine);

        int startCellArrowX = solutionAreas[mMazeView.lengthOfSolutionPath - 1][0] + 12 + FAT_FINGERS_MARGIN;
        int startCellArrowY = solutionAreas[mMazeView.lengthOfSolutionPath - 1][1] + 100 + FAT_FINGERS_MARGIN;
        arrow = (ImageView) findViewById(R.id.arrow);
        arrow.setX(startCellArrowX);
        arrow.setY(startCellArrowY);
        arrow.setVisibility(View.VISIBLE);


        int endCellHomeX = solutionAreas[0][0] + 8 + FAT_FINGERS_MARGIN;
        int endCellHomeY = solutionAreas[0][1] + 10 + FAT_FINGERS_MARGIN;
        home = (ImageView) findViewById(R.id.home);
        home.setX(endCellHomeX);
        home.setY(endCellHomeY);
        home.setVisibility(View.VISIBLE);;
    }


    public static Integer[] removeValueFromArray(Integer[] array, Integer value) {

        ArrayList<Integer> arrayList = new ArrayList<Integer>(Arrays.asList(array));
        arrayList.remove(arrayList.indexOf(value));

        Integer[] newArray = new Integer[arrayList.size()];
        arrayList.toArray(newArray);

        return newArray;
    }

    public static void startGameSolvedAnimation() {
        final Animation animation = new AlphaAnimation(1, 0);
        animation.setDuration(700);
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(3);
        animation.setRepeatMode(Animation.REVERSE);
        home.startAnimation(animation);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
