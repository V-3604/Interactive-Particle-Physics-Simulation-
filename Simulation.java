public class Simulation {
    private int numParticles;
    private Particle[] particles;
    private ObstacleNode obstacleList;
    private boolean allGravityEnabled = true;

    public Simulation() {
        this.numParticles = 0;
        this.particles = new Particle[10];
        this.obstacleList = null;
    }

    public Particle[] getParticles() {
        return this.particles;
    }

    public int getNumParticles() {
        return this.numParticles;
    }

    public void toggleGravity() {
        allGravityEnabled = !allGravityEnabled;
        for (Particle particle : particles) {
            particle.setGravityEnabled(allGravityEnabled);
        }
    }

    public void addParticles(int amount) {
        while (this.numParticles + amount > this.particles.length) {
            Particle[] newParticles = new Particle[this.particles.length * 2];
            for (int i = 0; i < this.numParticles; i++) {
                newParticles[i] = this.particles[i];
            }
            this.particles = newParticles;
        }

        for (int i = 0; i < amount; i++) {
            double xPos = Math.random() * 1820;
            double yPos = Math.random() * 980;
            this.particles[this.numParticles] = new Particle(xPos, yPos, 20.0, Math.random()*10 - 5, Math.random()*10 - 5);
            this.numParticles++;
        }
    }


    public void removeParticles(int amount) {
        if (amount >= this.numParticles) {
            this.numParticles = 0;
            this.particles = new Particle[5];
        } else {
            this.numParticles -= amount;
        }
    }

    public ObstacleNode getObstacles() {
        return this.obstacleList;
    }

    public void addObstacle(Obstacle obstacle) {
        ObstacleNode newNode = new ObstacleNode(obstacle);

        if (this.obstacleList == null) {
            this.obstacleList = newNode;
        } else {
            ObstacleNode curr = this.obstacleList;
            while (curr.getNext() != null) {
                curr = curr.getNext();
            }
            curr.setNext(newNode);
        }
    }

    public void removeObstacle(Obstacle obstacle) {
        if (this.obstacleList == null) {       
            return;
        }

        if (this.obstacleList.getObstacle().equals(obstacle)) {
            this.obstacleList = this.obstacleList.getNext();  
            return;
        }
        ObstacleNode curr = this.obstacleList;  
        while (curr.getNext() != null) {
            if (curr.getNext().getObstacle().equals(obstacle)) {
                curr.setNext(curr.getNext().getNext());         
                return;
            }
            curr = curr.getNext();
        }
    }
}
