package com.example.assignment3;

import javafx.scene.canvas.GraphicsContext;

import java.util.Optional;

public class MyLine extends MyShape {
    
    private MyPoint startPoint;
    private MyPoint endPoint;
    private MyColor color;

    
    //Constructors
    MyLine (GraphicsContext gc) { //Default Constructor creates diagonal MyLine object
        super(new MyPoint(),null);

        setStartPoint(new MyPoint());
        setEndPoint(new MyPoint((int) gc.getCanvas().getWidth(), (int) gc.getCanvas().getHeight()));
        setColor(MyColor.CADETBLUE);
    }

    MyLine (MyPoint startPoint, MyPoint endPoint) {
        super(new MyPoint(),null);
        setStartPoint(startPoint);
        setEndPoint(endPoint);
        setColor(MyColor.CADETBLUE);
    }

    MyLine (MyPoint startPoint, MyPoint endPoint, MyColor color) {
        super(new MyPoint(),null);
        setStartPoint(startPoint);
        setEndPoint(endPoint);
        this.color = Optional.ofNullable(color).orElse(MyColor.CADETBLUE);
    }
    
    //Setters
    public void setStartPoint(MyPoint startPoint) {
        this.startPoint = startPoint;
    }
    public void setEndPoint(MyPoint endPoint) {
        this.endPoint = endPoint;
    }
    public void setColor (MyColor color) {
        this.color = color;
    }
    //Getters
    public MyPoint getStartPoint() {
        return startPoint;
    }
    public MyPoint getEndPoint() {
        return endPoint;
    }
    public MyColor getColor () {
        return color;
    }
    
    //Methods
    public double length() {
        double dx = endPoint.getX() - startPoint.getX();
        double dy = endPoint.getY() -startPoint.getY();
        return Math.sqrt(dx*dx + dy * dy);
    }
    @Override
    public double area() {
        return length();
    } //Assume height of line is 1 pixel. Area = length * height
    @Override
    public double perimeter() {
        return 2 * length() + 2;
    } // Assume height of line is 1 pixel. Perimeter = 2 * length + 2*1

    @Override
    public String toString() {
        return "This is MyLine Object with start point " + startPoint + ", end point " + endPoint + ", area " + (int)area() + ", perimeter " + (int)perimeter() + " ,and color " + color;
    }
    @Override
    public void draw (GraphicsContext GC) {
        GC.setFill(color.getJavaFXColor());
        GC.setLineWidth(5.0);
        GC.strokeLine(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY() );
    }


    @Override
    public MyRectangle getMyBoundingRectangle() {
        MyPoint topLeft = new MyPoint (startPoint,color);
        double width = endPoint.getX() - startPoint.getX() + 1; // Minimum width = 1
        double height = endPoint.getY() - startPoint.getY() + 1; // Minimum height = 1
        MyColor new_color = MyColor.AQUA;
        new_color.setA(0);
        return new MyRectangle(topLeft, width, height, new_color);
    }

    @Override
    public boolean pointInMyShape(MyPoint p) { //NEEDS WORK
        return false;
    }
}
