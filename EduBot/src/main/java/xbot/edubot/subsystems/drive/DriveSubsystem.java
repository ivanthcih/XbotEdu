package xbot.edubot.subsystems.drive;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import edu.wpi.first.wpilibj.MockDistanceSensor;
import xbot.common.command.BaseSubsystem;
import xbot.common.controls.actuators.XCANTalon;
import xbot.common.injection.wpi_factories.CommonLibFactory;
import xbot.edubot.MockHeadingSensor;

@Singleton
public class DriveSubsystem extends BaseSubsystem { //subclass extends superclass
    boolean inPrecisionMode = false;
    public double leftPower = 1;
    public double rightPower = 1;

    public MockDistanceSensor distanceSensor;
    public MockHeadingSensor gyro;
    
    public XCANTalon frontLeft;
    public XCANTalon frontRight; // elements example
    public XCANTalon rearLeft;
    public XCANTalon rearRight;
        
    @Inject
    public DriveSubsystem(CommonLibFactory factory) { // an example of constructor, also a subclass-ish
        // instantiate speed controllers and sensors here, save them as class members
        distanceSensor = new MockDistanceSensor();
        gyro = new MockHeadingSensor();
        
        frontLeft = factory.createCANTalon(1);
        rearLeft = factory.createCANTalon(3);// classes
        frontRight = factory.createCANTalon(2);
        rearRight = factory.createCANTalon(4);
    }
    
    public void tankDrive(double leftPower, double rightPower) { // also example of constructor
        // You'll need to take these power values and assign them to all of the motors. As
        // an example, here is some code that has the frontLeft motor to spin according to
        // the value of leftPower:

        if(inPrecisionMode) {
            leftPower = leftPower/2;
            rightPower = rightPower/2;
        }
        
        frontLeft.simpleSet(leftPower);
        rearLeft.simpleSet(leftPower);
        frontRight.simpleSet(rightPower);
        rearRight.simpleSet(rightPower);
    }

    public boolean TogglePrecisionMode() {  // a subclass
        if(inPrecisionMode){
            inPrecisionMode = false; // if inPrecisionMode true, then need to toggle
        }
        else{
            inPrecisionMode = true; // if inPrecisionMode false (which is default), then no need to toggle
        }
        return inPrecisionMode;
    }

    public double getDistance(){ // calls the DistanceSensor and tracks the current distance robot has traveled
        return distanceSensor.getDistance();
    }

    public double getYaw(){
        return gyro.getYaw();
    }
}