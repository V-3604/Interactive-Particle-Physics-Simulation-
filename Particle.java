public class Particle {
    private double x;
    private double y;
    private double radius;
    private double velocityX;
    private double velocityY;
    private double gravity;
    private boolean gravityEnabled = true;

    public Particle(double xPos, double yPos, double r, double initialVelocityX, double initialVelocityY) {
        this.x = xPos;
        this.y = yPos;
        this.radius = r;
        this.velocityX = initialVelocityX;
        this.velocityY = initialVelocityY;
        this.gravity = 9.8;
    }

    public void setGravityEnabled(boolean enabled) {
        this.gravityEnabled = enabled;
    }
    public double getX() { return x; }
    public double getY() { return y; }
    public double getRadius() { return radius; }
    public double getVelocityX() { return velocityX; }
    public double getVelocityY() { return velocityY; }
    public double getGravity() { return gravity; }
    public void setX(double xPos) { this.x = xPos; }
    public void setY(double yPos) { this.y = yPos; }

    /**
     * this method sets the radius of the particle.
     *
     * @param radius The new radius of the particle.(Must be positive.)
     * @throws IllegalArgumentException if the radius is negative or zero.
     */
    public void setRadius(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius must be positive.");
        }
        this.radius = radius;
    }

    public void setVelocityX(double vx) { this.velocityX = vx; }
    public void setVelocityY(double vy) { this.velocityY = vy; }
    public void setGravity(double g) { this.gravity = g; }

    public void update(double dt) {
        if(gravityEnabled){
            velocityY += gravity * dt;
        }

        x += velocityX * dt;
        y += velocityY * dt;

        if (y + radius >= 980) {
            y = 980 - radius;
            if(gravityEnabled){
                velocityY = -velocityY * 0.7;
            }

        }
    }


    public void handleCollision(Obstacle obstacle) {
        if (obstacle.checkCollision(this)) {
            double[] normal = obstacle.collide(this);


            if (Vector2DMath.magnitude(velocityX, velocityY) < 0.2) {
                velocityX = normal[0]*0.5;
                velocityY = normal[1]*0.5;
            }
            else {
                // otherwise just reflect
                double[] reflect = Vector2DMath.reflect(normal, velocityX, velocityY);
                velocityX = reflect[0]*0.6;
                velocityY = reflect[1]*0.6;
            }
        }
    }

    public void setVelocity(double vx, double vy) {
        velocityX = vx;
        velocityY = vy;
    }
}
