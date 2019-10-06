package xbot.edubot.subsystems.drive.commands;

import com.google.inject.Inject;

import xbot.common.command.BaseCommand;
import xbot.edubot.operator_interface.OperatorInterface;
import xbot.edubot.subsystems.drive.DriveSubsystem;

public class ArcadeDriveWithJoysticksCommand extends BaseCommand {

    DriveSubsystem drive;
    OperatorInterface oi;

    @Inject
    public ArcadeDriveWithJoysticksCommand(DriveSubsystem driveSubsystem, OperatorInterface oi) {
        this.drive = driveSubsystem;
        this.oi = oi;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        double x = oi.gamepad.getLeftVector().x;
        double y = oi.gamepad.getLeftVector().y;

        double leftPower = y + x;
        double rightPower = y - x;

        drive.tankDrive(leftPower, rightPower);
    }

}
