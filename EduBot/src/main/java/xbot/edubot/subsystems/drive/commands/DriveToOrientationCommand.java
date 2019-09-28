package xbot.edubot.subsystems.drive.commands;

import com.google.inject.Inject;

import xbot.common.command.BaseCommand;
import xbot.edubot.subsystems.drive.DriveSubsystem;

public class DriveToOrientationCommand extends BaseCommand{
    
    DriveSubsystem drive; // write class-level variables here
    double GoalYaw;
    double currentPos;
    double speed;
    double currentYaw;
    double oldYaw;
    double leftPower;

    @Inject
    public DriveToOrientationCommand(DriveSubsystem driveSubsystem) {
        this.drive = driveSubsystem;
    }
    
    public void setTargetHeading(double heading) {
        GoalYaw = heading;

        // This method will be called by the test, and will give you a goal heading.
        // You'll need to remember this target position and use it in your calculations.
    }
    
    @Override
    public void initialize() {
        // If you have some one-time setup, do it here.
    }

    @Override
    public void execute() {
        // Here you'll need to figure out a technique that:
        // - Gets the robot to turn to the target orientation
        // - Gets the robot stop (or at least be moving really really slowly) at the target position
        currentYaw = drive.getYaw();
        speed = currentYaw - oldYaw;

        double GoalDistYaw = Math.abs(GoalYaw - currentYaw);  // make it turn to the shortest way!!
        // if(GoalDistYaw) // make it have the ability turn right and left 

        leftPower = GoalDistYaw/90 * 5 - speed*1;
        
        
        drive.tankDrive(-leftPower, leftPower); 

        oldYaw = currentYaw;
        
        // How you do this is up to you. If you get stuck, ask a mentor or student for some hints!
    }

    @Override
    public boolean isFinished() {
        boolean nearGoal = Math.abs( drive.getYaw() - GoalYaw) < 0.7;
        boolean movingSlow = Math.abs(speed) < 0.6;

       if(nearGoal && movingSlow){
            return true;
       }
       
       return false;

       // Modify this to return true once you have met your goal, 
       // and you're moving fairly slowly (ideally stopped)
    }

}
