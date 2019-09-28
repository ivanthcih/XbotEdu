package xbot.edubot;

public class MockHeadingSensor {
    private double yaw = 0; // originally 0
    
    public void setYaw(double yaw) {
        this.yaw = yaw;
    }
    
    public double getYaw() {
        return this.yaw;
    }
}