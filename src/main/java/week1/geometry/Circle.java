package week1.geometry;

/**
 * Circle class representing a circle shape
 * 
 * @author Bhavya Jain
 */
public class Circle extends Shape {
    private double radius;

    /**
     * Constructor to initialize the circle with a radius
     * 
     * @param radius the radius of the circle
     */
    public Circle(double radius) {
        this.radius = radius;
    }

    /**
     * Calculate the area of the circle
     * 
     * @return area of the circle (π * radius²)
     */
    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
