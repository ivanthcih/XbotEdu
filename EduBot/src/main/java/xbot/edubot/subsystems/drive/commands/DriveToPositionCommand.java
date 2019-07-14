package xbot.edubot.subsystems.drive.commands;

import com.google.inject.Inject;

import xbot.common.command.BaseCommand;
import xbot.edubot.subsystems.drive.DriveSubsystem;

public class DriveToPositionCommand extends BaseCommand {

    DriveSubsystem drive;
    
    @Inject
    public DriveToPositionCommand(DriveSubsystem driveSubsystem) {
        this.drive = driveSubsystem;
    }
    
    public void setTargetPosition(double position) {
        // This method will be called by the test, and will give you a goal distance.
        // You'll need to remember this target position and use it in your calculations.
    }
    
    @Override
    public void initialize() {
        // If you have some one-time setup, do it here.
    }

    @Override
    public void execute() {
        // Here you'll need to figure out a technique that:
        // - Gets the robot to move to the target position 
        // - Hint: use drive.distanceSensor.get() to find out where you are
        // - Gets the robot stop (or at least be moving really really slowly) at the target position
       
        // How you do this is up to you. If you get stuck, ask a mentor or student for some hints!
    }
    
    @Override
    public boolean isFinished() {
        /* Modify this to RETURN TRUE once you have met your goal, 
         and you're moving fairly slowly (ideally stopped) */
        return false;
    }

}
