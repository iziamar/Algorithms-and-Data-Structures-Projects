// Written by Amara Marx (marx0109)
import java.awt.Color;
import java.lang.Math;
public class Triangle{
    private double xPosition;
    private double yPosition;
    private double width;
    private double height;
    private Color triangleColor;

    public Triangle(double xPosition, double yPosition, double width, double height){    // Constructor initializes Circle variables
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.width = width;
        this.height = height;
    }
    public double calculatePerimeter(){
        return width + Math.sqrt(Math.pow(width, 2)+ Math.pow(4*height, 2));
    }
    public double calculateArea(){
        return (width*height)/2;
    }
    public void setColor(Color triangleColor){
        this.triangleColor = triangleColor;
    }
    public void setPos(double xPosition, double yPosition){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }
    public void setHeight(double height){
        this.height = height;
    }
    public void setWidth(double width){
        this.width = width;
    }

    public Color getColor(){
        return triangleColor;
    }
    public double getXPos(){
        return xPosition;
    }
    public double getYPos(){
        return yPosition;
    }
    public double getHeight(){
        return height;
    }
    public double getWidth(){
        return width;
    }
}