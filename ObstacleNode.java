public class ObstacleNode {
    private Obstacle obstacle;
    private ObstacleNode next;

    public ObstacleNode(Obstacle obstacle) {
        this.obstacle = obstacle;
        this.next = null;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public ObstacleNode getNext() {
        return next;
    }

    public void setNext(ObstacleNode next) {
        this.next = next;
    }
}
