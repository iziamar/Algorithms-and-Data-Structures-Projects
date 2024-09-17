// Written by Amara Marx (marx0109)
import java.awt.Color;
import java.lang.Math;
public class Circle{
    private double xPosition;
    private double yPosition;
    private double radius;
    private Color circleColor;
    public Circle(double xPosition, double yPosition, double radius){    // Constructor initializes Circle variables
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.radius = radius;
    }
    public double calculatePerimeter(){
        double perimeter = 2 * Math.PI * radius;  // Calculates Perimeter with given radius using P = 2(pi)r
        return perimeter;
    }
    public double calculateArea(){
        double area = Math.PI * Math.pow(radius, 2);
        return area;
    }
    public void setColor(Color circleColor){
        this.circleColor = circleColor;
    }
    public void setPos(double xPosition, double yPosition){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }
    public void setRadius(double radius){
        this.radius = radius;
    }
    public Color getColor(){
        return circleColor;
    }
    public double getXPos(){
        return xPosition;
    }
    public double getYPos(){
        return yPosition;
    }
    public double getRadius(){
        return radius;
    }

}