package com.muktadir.canvas1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.Arrays;

public class WheelChartView extends View {

    public static final String TAG = "WheelChartView:";

    private float curX = -1;
    private float curY = -1;

    private float cX = 0; //center x
    private float cY = 0; // center y
    private float width = 0;
    private float height = 0;

    private float gutter = 0; // distance between outer circle and immediate inner circle
    private float maxRadius = 0;

    private int lines = 16;
    private int circles = 5;

    private int []chosenLevels;

    private String []lineTitles;

    private float sectionAngle = 0;

    private String bgColor = "#FF000000"; //opaque black

    private float circleStrokeWidth = 2f;
    private float lineStrokeWidth = 1f;
    private float polyStrokeWidth = 4f;

    private float pointStrokeWidth = 4f;
    private float pointRadius = 8f;

    private String circleColor = "#FFBBBBBB";
    private String lineColor = "#FFAAAAAA";
    private String polyColor = "#FF7eb240";

    private String centerColor = "#FF7eb240";
    private float centerRadius = 10f;

    private String labelColor = "#FFBBBBBB";
    private float labelSize = 12;

    private float maxWheelSize = 0;


    public WheelChartView(Context context) {
        super(context);
        chosenLevels = new int[lines];
        lineTitles = new String[lines];
    }

    public WheelChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        chosenLevels = new int[lines];
        lineTitles = new String[lines];
    }

    public WheelChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        chosenLevels = new int[lines];
        lineTitles = new String[lines];
    }

    public void populateTestTitles()
    {
        for(int i=0; i< lineTitles.length; ++i){

            lineTitles[i] = "Title " + i;

        }
    }

    public float getCurX() {
        return curX;
    }

    public void setCurX(float curX) {
        this.curX = curX;
    }

    public float getCurY() {
        return curY;
    }

    public void setCurY(float curY) {
        this.curY = curY;
    }

    public int getCircles() {
        return circles;
    }

    public void setCircles(int circles) {
        this.circles = circles;
    }

    public int getLines() {
        return lines;
    }

    public void setLines(int lines) {
        this.lines = lines;
    }

    public int[] getChosenLevels() {
        return chosenLevels;
    }

    public void setChosenLevels(int[] chosenLevels) {
        this.chosenLevels = chosenLevels;
    }

    public String[] getLineTitles() {
        return lineTitles;
    }

    public void setLineTitles(String[] lineTitles) {
        this.lineTitles = lineTitles;
    }

    public float getMaxWheelSize() {
        return maxWheelSize;
    }

    public void setMaxWheelSize(float maxWheelSize) {
        this.maxWheelSize = maxWheelSize;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public String getCenterColor() {
        return centerColor;
    }

    public void setCenterColor(String centerColor) {
        this.centerColor = centerColor;
    }

    public float getCenterRadius() {
        return centerRadius;
    }

    public void setCenterRadius(float centerRadius) {
        this.centerRadius = centerRadius;
    }

    public String getCircleColor() {
        return circleColor;
    }

    public void setCircleColor(String circleColor) {
        this.circleColor = circleColor;
    }

    public float getCircleStrokeWidth() {
        return circleStrokeWidth;
    }

    public void setCircleStrokeWidth(float circleStrokeWidth) {
        this.circleStrokeWidth = circleStrokeWidth;
    }

    public String getLineColor() {
        return lineColor;
    }

    public void setLineColor(String lineColor) {
        this.lineColor = lineColor;
    }

    public float getLineStrokeWidth() {
        return lineStrokeWidth;
    }

    public void setLineStrokeWidth(float lineStrokeWidth) {
        this.lineStrokeWidth = lineStrokeWidth;
    }

    public float getPointRadius() {
        return pointRadius;
    }

    public void setPointRadius(float pointRadius) {
        this.pointRadius = pointRadius;
    }

    public float getPointStrokeWidth() {
        return pointStrokeWidth;
    }

    public void setPointStrokeWidth(float pointStrokeWidth) {
        this.pointStrokeWidth = pointStrokeWidth;
    }

    public String getPolyColor() {
        return polyColor;
    }

    public void setPolyColor(String polyColor) {
        this.polyColor = polyColor;
    }

    public float getPolyStrokeWidth() {
        return polyStrokeWidth;
    }

    public void setPolyStrokeWidth(float polyStrokeWidth) {
        this.polyStrokeWidth = polyStrokeWidth;
    }

    public String getLabelColor() {
        return labelColor;
    }

    public void setLabelColor(String labelColor) {
        this.labelColor = labelColor;
    }

    public float getLabelSize() {
        return labelSize;
    }

    public void setLabelSize(float labelSize) {
        this.labelSize = labelSize;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        iniMeasurement();

        Path rect = new  Path();
        rect.addRect(0, 0, 250, 150, Path.Direction.CW);
        Paint cpaint = new Paint(Paint.ANTI_ALIAS_FLAG);

//        cpaint.setColor(Color.BLACK);
//        canvas.drawPath(rect, cpaint);


        cpaint.setColor(Color.parseColor(bgColor));

        canvas.drawPaint(cpaint);

        cpaint.setColor(Color.parseColor(circleColor));


        Log.e(TAG, "cX " + cX);
        Log.e(TAG, "cY " + cY);
        Log.e(TAG, "gutter " + gutter);
        Log.e(TAG, "maxRadius " + maxRadius);

        cpaint.setStyle(Paint.Style.STROKE);
        cpaint.setStrokeWidth(circleStrokeWidth);

        for(int i=1; i<= circles; ++i){

            canvas.drawCircle(cX, cY, gutter * i, cpaint);

        }

        float baseX;
        float baseY;

        cpaint.setStrokeWidth(lineStrokeWidth);
        cpaint.setColor(Color.parseColor(lineColor));

        for(int i = 0; i<lines; ++i){

            baseX = (float) (cX + gutter * circles * Math.cos(sectionAngle*i));
            baseY = (float) (cY + gutter * circles * Math.sin(sectionAngle*i));
            canvas.drawLine(cX, cY, baseX, baseY, cpaint);

        }
        //not yet clicked any where\
        float chosenPointX = 0;
        float chosenPointY = 0;
        if(curX > 0){
            //canvas.drawLine(cX, cY, curX, curY, cpaint);

            float angle = getAngle(curX, curY);
            int circleNo = getNearestCircle(curX, curY);
            int lineNo = getNearestLine(angle);
            Log.e(TAG, "Angle " + angle);
            Log.e(TAG, "Line no  " + lineNo);
            Log.e(TAG, "Circle no  " + circleNo);

            float distance = gutter * circleNo;

            chosenPointX = (float) (cX + distance * Math.cos(sectionAngle * lineNo));
            chosenPointY = (float) (cY + distance * Math.sin(sectionAngle * lineNo));


            chosenLevels[lineNo] = circleNo;

        }



        Log.e(TAG, Arrays.toString(chosenLevels));

        cpaint.setStrokeWidth(polyStrokeWidth);
        cpaint.setColor(Color.parseColor(polyColor));

        for (int i=1; i<lines; i=i+2)
            updatePolygon(i, canvas, cpaint);


        if(chosenPointX > 0){

            cpaint.setStrokeWidth(pointStrokeWidth);
            cpaint.setColor(Color.parseColor(lineColor));
            canvas.drawLine(cX, cY, chosenPointX, chosenPointY, cpaint);

            cpaint.setColor(Color.parseColor(lineColor));
            cpaint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(chosenPointX, chosenPointY, pointRadius, cpaint);

        }


        cpaint.setColor(Color.parseColor(centerColor));

        cpaint.setStyle(Paint.Style.FILL);

        canvas.drawCircle(cX, cY, centerRadius, cpaint);

        updateTitles(canvas, cpaint);

    }

    public void updatePolygon(int lineNo, Canvas canvas, Paint cpaint)
    {

        int pLineNo = lineNo - 1;
        int nLineNo = lineNo + 1;

        if(pLineNo <0)
            pLineNo = lines -1;

        if(nLineNo >= lines)
            nLineNo = 0;

        float prevX = (float) (cX + gutter * chosenLevels[pLineNo] * Math.cos(sectionAngle * pLineNo));
        float prevY = (float) (cY + gutter * chosenLevels[pLineNo] * Math.sin(sectionAngle * pLineNo));
        float nextX = (float) (cX + gutter * chosenLevels[nLineNo] * Math.cos(sectionAngle * nLineNo));
        float nextY = (float) (cY + gutter * chosenLevels[nLineNo] * Math.sin(sectionAngle * nLineNo));
        float x = (float) (cX + gutter * chosenLevels[lineNo] * Math.cos(sectionAngle * lineNo));
        float y = (float) (cY + gutter * chosenLevels[lineNo] * Math.sin(sectionAngle * lineNo));

        canvas.drawLine(x, y, nextX, nextY, cpaint);
        canvas.drawLine(x, y, prevX, prevY, cpaint);
    }

    public void iniMeasurement()
    {
        width = this.getWidth();
        height = this.getHeight();

        Log.e(TAG, "Width " + width);
        Log.e(TAG, "Height " + height);

        cX = width / 2;
        cY = height / 2;
        updateMaxRedius();
        updateGutter();

        sectionAngle = (float) (Math.PI / lines) * 2;

        Log.e(TAG, "cX " + cX);
        Log.e(TAG, "cY " + cY);
        Log.e(TAG, "gutter " + gutter);
        Log.e(TAG, "maxRadius " + maxRadius);
        Log.e(TAG, "sectionAngle " + sectionAngle);
    }

    public void updateMaxRedius(){

        if(width < height) {

            maxRadius = width / 2;

        } else {

            maxRadius = height / 2;

        }

        if(maxWheelSize > 0) if(maxRadius * 2 > maxWheelSize) maxRadius = maxWheelSize / 2;

    }

    public void updateGutter() // must be called after maxRadius is set
    {
        gutter = maxRadius/(circles+1);
    }

    public void updateTitles(Canvas canvas, Paint cpaint){

        cpaint.setTextSize(labelSize);
        cpaint.setColor(Color.parseColor(labelColor));
        cpaint.setStyle(Paint.Style.FILL);
        cpaint.setTypeface(Typeface.DEFAULT_BOLD);

        float baseX;
        float baseY;
        float angle;
        float tolerance = (float) (Math.PI / (lines *10));

        float distance;

        for(int i = 0; i<lines; ++i){

            angle = sectionAngle * i;

            distance = gutter * circles + cpaint.getTextSize();

            baseX = (float) (cX + distance * Math.cos(angle));
            baseY = (float) (cY + distance * Math.sin(angle));

            if(Math.abs(angle -  (Math.PI / 2)) < tolerance) {

                cpaint.setTextAlign(Paint.Align.CENTER);

                baseY += cpaint.getTextSize()/2;


            }else if(Math.abs(angle -  (Math.PI * 3 / 2)) < tolerance){

                cpaint.setTextAlign(Paint.Align.CENTER);


            }else if((angle > Math.PI / 2) && (angle < Math.PI * 3 / 2)) {

                cpaint.setTextAlign(Paint.Align.RIGHT);

                baseY += cpaint.getTextSize()/3;

            } else {

                cpaint.setTextAlign(Paint.Align.LEFT);

                baseY += cpaint.getTextSize()/3;

            }



            if(lineTitles[i] == null)
                canvas.drawText("Title " + i, baseX, baseY, cpaint);
            else
                canvas.drawText(lineTitles[i], baseX, baseY, cpaint);

        }

    }

    //This function may need improvement as getX and getY may return event's absolute positing in the window.

    public void UpdateWithMouseEvent(MotionEvent event){


        if (event.getAction() == MotionEvent.ACTION_DOWN){
            Log.e(TAG, "Touch coordinates : " +
                    String.valueOf(event.getX()) + "x" + String.valueOf(event.getY()));
        }
        this.setCurX(event.getX());
        this.setCurY(event.getY());

        /*

        if (event.getAction() == MotionEvent.ACTION_DOWN){
            Log.e(TAG, "Absolute Touch coordinates : " +
                    String.valueOf(event.getRawX()) + "x" + String.valueOf(event.getRawY()));
        }
        this.setCurX(event.getRawX() - this.getLeft() - window posx);
        this.setCurY(event.getRawY() - this.getTop() - window posy);

         */

    }

    //always returns positive
    public float getAngle(float x, float y){

//        float distance = (float) Math.sqrt((x-cX) * (x-cX) + (y - cY) * (y - cY));
//        atan2(y - cy, x - cx)
        float angle = (float) Math.atan2(y - cY, x - cX);

        if(angle < 0) angle += 2 * Math.PI;

        return angle;

    }

    // returns index
    public int getNearestLine(float angle){
        int lineNo =  Math.round(angle/sectionAngle);

        if(lineNo >= lines)
            lineNo -= lines;

        return lineNo;
    }

    public int getNearestCircle(float x, float y)
    {
        float distance = (float) Math.sqrt((x-cX) * (x-cX) + (y - cY) * (y - cY));

        int circleNo = Math.round(distance/gutter);

        if(circleNo > circles)
            circleNo = circles;

        return circleNo;

    }




}
