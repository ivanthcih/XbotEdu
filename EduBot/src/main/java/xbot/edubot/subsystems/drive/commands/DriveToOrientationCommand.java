package xbot.edubot.subsystems.drive.commands;

import com.google.inject.Inject;

// import edu.wpi.first.wpilibj.PowerDistributionPanel;
import xbot.common.command.BaseCommand;
import xbot.common.injection.wpi_factories.CommonLibFactory;
import xbot.common.math.PIDFactory;
import xbot.common.math.PIDManager;
import xbot.common.subsystems.drive.control_logic.HeadingModule;
import xbot.edubot.subsystems.drive.DriveSubsystem;
import xbot.edubot.subsystems.pose.PoseSubsystem;

public class DriveToOrientationCommand extends BaseCommand {

    DriveSubsystem drive;
    PoseSubsystem pose;
    PIDManager pid;
    HeadingModule headingModule;
    double setYaw;

    @Inject
    public DriveToOrientationCommand(DriveSubsystem driveSubsystem, CommonLibFactory clf, PIDFactory pf,
            PoseSubsystem pose) {
        this.pose = pose;
        this.drive = driveSubsystem;
        pid = pf.createPIDManager("Rotate");
        headingModule = clf.createHeadingModule(pid);

        pid.setEnableErrorThreshold(true); // Turn on distance checking
        pid.setErrorThreshold(0.5);
        pid.setEnableDerivativeThreshold(true); // Turn on speed checking
        pid.setDerivativeThreshold(0.5);

        pid.setP(1);
        pid.setD(10);
    }

    public void setTargetHeading(double heading) {
        setYaw = heading;
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        double power = headingModule.calculateHeadingPower(setYaw);
        // double currentYaw = pose.getCurrentHeading().getValue();
        drive.frontLeft.simpleSet(-power);
        drive.frontRight.simpleSet(power);
    }

    @Override
    public boolean isFinished() {
        return pid.isOnTarget();
    }
}
