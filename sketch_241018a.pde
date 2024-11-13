Simulation simulation;

void setup() {
  size(1820, 980);
  simulation = new Simulation();
}

void draw() {
    background(255);
    
    Particle[] particles = simulation.getParticles();
    for (int i = 0; i < simulation.getNumParticles(); i++) {
        particles[i].update(0.1);

        // Check for collision with each obstacle
        ObstacleNode currentObstacle = simulation.getObstacles();
        while (currentObstacle != null) {
            particles[i].handleCollision(currentObstacle.getObstacle());
            currentObstacle = currentObstacle.getNext();
        }

        fill(0, 255, 0);
        ellipse((float) particles[i].getX(), (float) particles[i].getY(), (float) particles[i].getRadius() * 2, (float) particles[i].getRadius() * 2);
    }

    ObstacleNode currentObstacle = simulation.getObstacles();
    while (currentObstacle != null) {
        Obstacle obs = currentObstacle.getObstacle();
        fill(255, 0, 0);
        ellipse((float) obs.getX(), (float) obs.getY(), (float) obs.getRadius() * 2, (float) obs.getRadius() * 2);
        currentObstacle = currentObstacle.getNext();
    }
}


void keyPressed() {
  if (key == '+') {
    simulation.addParticles(2);
  } else if (key == '-') {
    simulation.removeParticles(10);
  }
  if (key == 'G' || key == 'g') {
            simulation.toggleGravity();
        }
}

void mousePressed() {
    if (mouseButton == LEFT) {
        simulation.addObstacle(new Obstacle(mouseX, mouseY, 30));
    } else if (mouseButton == RIGHT) {
        double threshold = 30.0;

        ObstacleNode current = simulation.getObstacles();
        while (current != null) {
            Obstacle obs = current.getObstacle();
            double distance = Vector2DMath.magnitude(mouseX - obs.getX(), mouseY - obs.getY());

            if (distance <= threshold) {
                simulation.removeObstacle(obs);
                break;
            }
            current = current.getNext();
        }
    }
    
}
