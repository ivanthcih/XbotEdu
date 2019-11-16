package xbot.edubot.subsystems.drive.commands;

import com.google.inject.Inject;

import edu.wpi.first.wpilibj.Joystick;
import xbot.common.command.BaseCommand;
import xbot.edubot.operator_interface.OperatorInterface;
import xbot.edubot.subsystems.drive.DriveSubsystem;

public class ArcadeDriveWithJoysticksCommand extends BaseCommand {

    DriveSubsystem drive;
    OperatorInterface operate;
    double getLeftY;
    double getLeftX;
    double Right;
    double Left;

    @Inject
    public ArcadeDriveWithJoysticksCommand(DriveSubsystem driveSubsystem, OperatorInterface oi) {
        drive = driveSubsystem;
        operate = oi;
        this.requires(drive);

    }

    
    @Override
    public void initialize() { 
    }

    @Override
    public void execute() { // based on left joystick
        getLeftY = operate.gamepad.getLeftVector().y;
        getLeftX = operate.gamepad.getLeftVector().x;
        
        Left = getLeftY + getLeftX;
        Right = getLeftY - getLeftX;

        drive.tankDrive(Left, Right);

    }

}
