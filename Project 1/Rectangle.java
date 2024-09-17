// Written by Amara Marx (marx0109)
import java.awt.Color;
import java.lang.Math;
public class Rectangle{
    private double xPosition;  // x position of upper left corner
    private double yPosition; // y position of upper left corner
    private double width;
    private double height;
    private Color rectangleColor;

    public Rectangle(double xPosition, double yPosition, double width, double height){ //initializes Rectangle variables
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.width = width;
        this.height = height;
    }
    public double calculatePerimeter(){ // calculates perimeter of rectangle using P = 2w + 2h
        return (2*width) + (2*height);
    }
    public double calculateArea(){ // calculates area of rectangle using A = w * h
        return (width*height);
    }
    public void setColor(Color rectangleColor){
        this.rectangleColor = rectangleColor;
    }
    public void setPos(double xPosition, double yPosition){ // sets the position of the upper left corner
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
        return rectangleColor;
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