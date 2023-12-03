package com.example.mazegame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;


public class FingerLine extends View {
    private Paint mPaint;
    private Path mPath;
    private int[][] solutionPath;
    int solutionCellsVisited;
    private ArrayList<Boolean> solved;
    private boolean solvedMaze;

    public FingerLine(Context context) {
        this(context, null);
        init();
    }

    public FingerLine(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FingerLine(Context context, AttributeSet attrs, int[][] solutionPath) {
        super(context, attrs);
        this.solutionPath = solutionPath;
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Style.STROKE);
        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.solver));
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(10f);
        mPath = new Path();

        solutionCellsVisited = 0;
        solvedMaze = false;
        solved = new ArrayList<Boolean>();

        for (int i = 0; i < solutionPath.length; i++) {
            solved.add(false);
        }
    }

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(event.getX(), event.getY());
                return true;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                return false;
        }

        invalidate();
        boolean xInCell;
        boolean yInCell;

        for (int i = 0; i < solutionPath.length; i++) {

            xInCell = (event.getX() >= solutionPath[i][0] && event.getX() <= solutionPath[i][2]);
            yInCell = (event.getY() >= solutionPath[i][1] && event.getY() <= solutionPath[i][3]);

            if (xInCell && yInCell) {
                solved.set(i, true);

                if (!solved.contains(false) && !solvedMaze) {
                    GameActivity.startGameSolvedAnimation();
                    Toast.makeText(this.getContext(), R.string.maze_solved, Toast.LENGTH_SHORT).show();
                    solvedMaze = true;
                    return true;
                }
            }
        }
        return true;
    }
}