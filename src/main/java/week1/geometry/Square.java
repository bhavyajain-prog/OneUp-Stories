package week1.geometry;

/**
 * Square class representing a square shape
 * 
 * @author Bhavya Jain
 */
public class Square extends Shape {
    private double side;

    /**
     * Constructor to initialize the square with a side length
     * 
     * @param side the length of the side of the square
     */
    public Square(double side) {
        this.side = side;
    }

    /**
     * Calculate the area of the square
     * 
     * @return area of the square (side²)
     */
    @Override
    public double getArea() {
        return side * side;
    }

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
    }
}
