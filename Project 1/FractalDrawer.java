//Written by Amara Marx (marx0109)
// FractalDrawer class draws a fractal of a shape indicated by user input
import java.awt.Color;
import java.util.Scanner;
public class FractalDrawer {
    private double totalArea = 0;  // member variable for tracking the total area

    private String shape; // variable for user entered shape

    public int canvasWidth = 1000;

    public int canvasHeight = 1000;// string that holds the colors for each iteration

    public Color[] levelColor = {Color.YELLOW, Color.LIGHT_GRAY, Color.ORANGE, Color.BLACK, Color.YELLOW, Color.LIGHT_GRAY};

    public FractalDrawer(String shape) { // constructor
        this.shape = shape;
    } //Determines which fractal to draw depending on user input

    public double drawFractal( ) {
        Canvas draw = new Canvas(canvasHeight, canvasWidth);
        if (shape.equals("circle")) {
            drawCircleFractal(150, canvasWidth/2, canvasHeight/2 - 150, Color.BLACK, draw, 0);
            return totalArea;
        }
        else if (shape.equals("triangle")) {
            drawTriangleFractal(250, 300, (canvasWidth/2) - 125, (canvasHeight/2), Color.ORANGE, draw, 0);
            return totalArea;
        }
        else if (shape.equals("rectangle")) {
            drawRectangleFractal(300,300,(canvasWidth/2) - 150, (canvasHeight/2) - 300, Color.BLACK, draw, 0);
            return totalArea;
        }
        else return 0;
    }

    //drawTriangleFractal draws a triangle fractal using recursive techniques

    public void drawTriangleFractal(double width, double height, double x, double y, Color c, Canvas can, int level) {
        Triangle myTriangle = new Triangle(x, y, width, height);
        double area = myTriangle.calculateArea(); // calculates the area of the triangle in each iteration
        totalArea += area; // keeps track of the total area of all triangles in the fractal
        myTriangle.setColor(c);
        can.drawShape(myTriangle);
        if (level < 6) { //recursively calls 3 triangles for each level, and adjusts values (width, height, position, etc.)
            drawTriangleFractal(width/2, height/2, x + (width*0.75), y - (width/2), levelColor[level], can, level + 1 );
            drawTriangleFractal(width/2, height/2, x - (width*0.25), y - (width/2), levelColor[level], can, level + 1 );
            drawTriangleFractal(width/2, height/2, x + width/4, y, levelColor[level], can, level + 1 );
        }
    }
    // drawCircleFractal draws a circle fractal using recursive techniques
    public void drawCircleFractal(double radius, double x, double y, Color c, Canvas can, int level) {
        Circle myCircle = new Circle(x, y, radius);
        double area = myCircle.calculateArea(); //calculates the area of the circle in each iteration
        totalArea += area; // keeps track of the total area of all circles in the fractal
        myCircle.setColor(c);
        can.drawShape(myCircle);
        if (level < 6){ //recursively calls 4 circles for each level and adjust values as needed.
            drawCircleFractal(radius/2, x + (radius), y, levelColor[level], can, level+1);
            drawCircleFractal(radius/2, x - (radius), y, levelColor[level], can, level+1);
            drawCircleFractal(radius/2, x, y + radius, levelColor[level], can, level+1);
            drawCircleFractal(radius/2, x, y - radius, levelColor[level], can, level+1);
        }
    }
    public void drawRectangleFractal(double width, double height, double x, double y, Color c, Canvas can, int level) {
        Rectangle myRectangle = new Rectangle(x, y, width, height);
        double area = myRectangle.calculateArea(); //calculates the area of the rectangle in each iteration
        totalArea += area; // keeps track of the total area of all rectangles in the fractal
        myRectangle.setColor(c);
        can.drawShape(myRectangle);
        if (level < 6){
            drawRectangleFractal(width/2, height/2, x - width/4, y -(height* (0.5/2)), levelColor[level], can, level+1);
            drawRectangleFractal(width/2, height/2, x - width/4, y + (height - (height*(0.5/2))), levelColor[level], can, level+1);
            drawRectangleFractal(width/2, height/2, x + width - (width/4), y - (height/4), levelColor[level], can, level+1);
            drawRectangleFractal(width/2, height/2, x + width - (width/4), y + (height - (height/4)), levelColor[level], can, level+1);
        }
    }

    // main asks user for shape input, and then draws the corresponding fractal.
    // prints area of fractal.
    public static void main(String[] args) {
        String shape;
        Scanner s = new Scanner(System.in);
        System.out.println(""" 
                What shape would you lke to turn into a fractal?
                Options:
                   circle
                   triangle
                   rectangle""");
        String next = s.next(); // gets user input and saves it as next.
        //the while loop makes sure the user input was valid
        while (!((next.equals("circle")) || (next.equals("triangle")) || (next.equals("rectangle")))) {
            System.out.println("Invalid input please enter a circle, triangle, or rectangle.");
            next = s.next();
        }
        shape = next; //sets shape to next once we are sure it was a valid input
        FractalDrawer frac = new FractalDrawer(shape); // creates new object
        frac.drawFractal(); // calls object so the shape will be drawn
        System.out.println("The total area of the fractal is " + frac.totalArea); //prints out the total area of the fractal
    }
}
