package xbot.edubot.subsystems.drive;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import edu.wpi.first.wpilibj.MockDistanceSensor;
import xbot.common.command.BaseSubsystem;
import xbot.common.controls.actuators.XCANTalon;
import xbot.common.injection.wpi_factories.CommonLibFactory;
import xbot.edubot.MockHeadingSensor;

@Singleton
public class DriveSubsystem extends BaseSubsystem {

    public MockDistanceSensor distanceSensor;
    public MockHeadingSensor gyro;
    public XCANTalon frontLeft;
    public XCANTalon frontRight;
    public XCANTalon rearLeft;
    public XCANTalon rearRight;

    public boolean precisionMode;

    @Inject
    public DriveSubsystem(CommonLibFactory factory) {
        distanceSensor = new MockDistanceSensor();
        gyro = new MockHeadingSensor();
        frontLeft = factory.createCANTalon(1);
        rearLeft = factory.createCANTalon(3);
        frontRight = factory.createCANTalon(2);
        rearRight = factory.createCANTalon(4);

        precisionMode = false;
    }

    public void tankDrive(double leftPower, double rightPower) {
        if (precisionMode) {
            frontLeft.simpleSet(leftPower / 2);
            frontRight.simpleSet(rightPower / 2);
            rearLeft.simpleSet(leftPower / 2);
            rearRight.simpleSet(rightPower / 2);
        } else {
            frontLeft.simpleSet(leftPower);
            frontRight.simpleSet(rightPower);
            rearLeft.simpleSet(leftPower);
            rearRight.simpleSet(rightPower);
        }
    }

    public boolean getPrecisionMode() {
        return precisionMode;
    }

    public void togglePrecisionMode() {
        precisionMode = !precisionMode;
    }
}
