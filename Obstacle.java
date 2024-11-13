public class Obstacle {
    private double x;
    private double y;
    private double radius;

    public Obstacle(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.radius = r;
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public double getRadius() { return radius; }

    public void setX(double xPos) { this.x = xPos; }
    public void setY(double yPos) { this.y = yPos; }
    public void setRadius(double r) {
        if (r > 0) {
            this.radius = r;
        }
        else {
            System.out.println("Radius must be positive.");
        }
    }

    public boolean checkCollision(Particle p) {
        double distance = Vector2DMath.magnitude(x-p.getX(), y-p.getY());
        return distance <= radius + p.getRadius();
    }



    public double[] collide(Particle p) {
        double[] normal = Vector2DMath.normal(p.getX() - this.x, p.getY() - this.y);
        double distance = radius + p.getRadius();
        p.setX(x + normal[0] * distance);
        p.setY(y + normal[1] * distance);
        return normal;
    }
}
