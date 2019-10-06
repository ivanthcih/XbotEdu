package xbot.edubot.subsystems.drive.commands;

import com.google.inject.Inject;
import xbot.common.command.BaseCommand;
import xbot.common.controls.sensors.DistanceSensor;
import xbot.common.math.PIDFactory;
import xbot.common.math.PIDManager;
import xbot.edubot.subsystems.drive.DriveSubsystem;
import xbot.edubot.subsystems.pose.PoseSubsystem;

public class DriveToPositionCommand extends BaseCommand {

    DriveSubsystem drive;
    PoseSubsystem pose;
    PIDManager pid;
    double targetPosition;
    double position;

    @Inject
    public DriveToPositionCommand(DriveSubsystem driveSubsystem, PoseSubsystem pose, PIDFactory pf) {
        this.drive = driveSubsystem;
        this.pose = pose;
        this.pid = pf.createPIDManager("DriveToPoint");

        pid.setEnableErrorThreshold(true); // Turn on distance checking
        pid.setErrorThreshold(0.1);
        pid.setEnableDerivativeThreshold(true); // Turn on speed checking
        pid.setDerivativeThreshold(0.1);

        pid.setP(1.25);
        pid.setD(5);

    }

    public void setTargetPosition(double position) {
        targetPosition = position;
    }

    @Override
    public void initialize() {
        pid.reset();
    }

    @Override
    public void execute() {
        double position = pose.getPosition();
        System.out.println("Position: " + position);
        double power = pid.calculate(targetPosition, position);
        System.out.println("Power: " + power);
        drive.tankDrive(power, power);
    }

    @Override
    public boolean isFinished() {
        return pid.isOnTarget();
    }

}
