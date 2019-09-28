package xbot.edubot.subsystems.drive.commands;

import com.google.inject.Inject;

import xbot.common.command.BaseCommand;
import xbot.edubot.operator_interface.OperatorInterface;
import xbot.edubot.subsystems.drive.DriveSubsystem;

public class ArcadeDriveWithJoysticksCommand extends BaseCommand {

    DriveSubsystem drive;
    OperatorInterface operator;

    @Inject
    public ArcadeDriveWithJoysticksCommand(DriveSubsystem driveSubsystem, OperatorInterface oi) {
        drive = driveSubsystem;
        this.requires(drive);

    }

    
    @Override
    public void initialize() { // maybe add some here?
        
    }

    @Override
    public void execute() { // add the code here


    }

}
