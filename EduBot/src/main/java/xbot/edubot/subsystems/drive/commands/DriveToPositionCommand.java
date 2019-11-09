package xbot.edubot.subsystems.drive.commands;

import com.google.inject.Inject;


import xbot.common.command.BaseCommand;
import xbot.edubot.subsystems.drive.DriveSubsystem;

public class DriveToPositionCommand extends BaseCommand {

    DriveSubsystem drive;
    double targetGoal; // target goal
    double currentPos; // class level variable
    double speed;
    double oldPos;
    
    @Inject
    public DriveToPositionCommand(DriveSubsystem driveSubsystem) {
        this.drive = driveSubsystem;
    }
    
    public void setTargetPosition(double position) {
        targetGoal = position; // basically sets the target position and stores it as a value
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
        currentPos = drive.getDistance();
        speed = oldPos - currentPos;
        
        double leftPower = 0;
        if(currentPos > targetGoal){ // makes the blue circle move and tracks the loops it goes through
            leftPower = -0.3;
        }
        else{
            leftPower = 0.5;
        }
        drive.tankDrive(leftPower, -leftPower);
        // How you do this is up to you. If you get stuck, ask a mentor or student for some hints!
        
        oldPos = currentPos; // overrites the position after it moves onto a new pos
    }
    
    @Override
    public boolean isFinished() {
        boolean nearGoal = Math.abs(currentPos - targetGoal) < 0.1;  // if the difference is less than 0.1, then true
        boolean movingSlow = Math.abs(speed) < 0.2; // if current speed is < 0.2, then true
        // the decimal numbers = the velocity/speed


        if(nearGoal && movingSlow){ 
            return true;
        }
        
        return false;
        /* Modify this to RETURN TRUE once you have met your goal, 
         and you're moving fairly slowly (ideally stopped) */
    }
}
