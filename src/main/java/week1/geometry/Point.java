package week1.geometry;

/**
 * Point class representing a point in 2D space
 * 
 * @author Bhavya Jain
 */

public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    /**
     * Calculate distance from another point
     * @param other point from which distance is to be calculated
     * @return distance between this point and other point
     */
    public double distanceFrom(Point other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Translate the point by given dx and dy
     * @param dx x-offset
     * @param dy y-offset
     * @return translated point
     */
    public Point translate(double dx, double dy) {
        this.x += dx;
        this.y += dy;
        return this;
    }
}
