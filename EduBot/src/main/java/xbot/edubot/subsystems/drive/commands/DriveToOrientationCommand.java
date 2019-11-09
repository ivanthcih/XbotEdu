package xbot.edubot.subsystems.drive.commands;

import com.google.inject.Inject;

import xbot.common.command.BaseCommand;
import xbot.edubot.subsystems.drive.DriveSubsystem;

public class DriveToOrientationCommand extends BaseCommand{
    
    DriveSubsystem drive; // write class-level variables here
    double GoalYaw;
    double speed;
    double currentYaw;
    double oldYaw;
    double power;

    @Inject
    public DriveToOrientationCommand(DriveSubsystem driveSubsystem) {
        this.drive = driveSubsystem;
    }
    
    public void setTargetHeading(double heading) {
        GoalYaw = heading; // This method will be called by the test, and will give you a goal heading.
    }

    public static double seeDirection(double goal, double initial){ // checks angle so it can decide to turn left or right
        initial = angleChange(initial);
        goal = angleChange(goal);
        double GoalDistYaw = goal - initial;

        if (GoalDistYaw >= 180) { // turns 
            GoalDistYaw = GoalDistYaw - 360;
        }
        if (GoalDistYaw <= -180) { // turns
            GoalDistYaw = 360 + GoalDistYaw;
        }

        return GoalDistYaw;
    }

    public static double angleChange(Double angle){ // makes it a legal angle
        if(angle >= 180){
            angle = (angle % 360) - 360;
        } else if(angle <= -180){
            angle = (angle % 360) + 360;
        }
        return angle;
    }
    
    
    @Override
    public void initialize() {
        /*
        initialize currentYaw, oldYaw, and speed to get it started
        */
        currentYaw = drive.getYaw();
        oldYaw = currentYaw;      

        speed = 0;
    }

    @Override
    public void execute() {
        currentYaw = drive.getYaw();

        double distAway = seeDirection(GoalYaw, currentYaw);
        speed = currentYaw - oldYaw;

        power = 5 * distAway/90 - (speed);
        /*
          5 is a constant(don't know why), subtracted by speed because
          the equation could be inaccurate. divided by 90 bc brought from turnleft90
        */
        drive.tankDrive(-power, power); 
        oldYaw = currentYaw;
    }

    @Override
    public boolean isFinished() { // checks if its near the goal and makes the mock robot stop
        boolean nearGoal = Math.abs( drive.getYaw() - GoalYaw) < 0.7;
        boolean movingSlow = Math.abs(speed) < 0.1;

       if(nearGoal && movingSlow){
            return true;
       }
       
       return false;

    }

    public static double OrientatTest(double Angle){ // just a method that calls custom tests
        if(Math.abs(Angle)>180){
            Angle = (Angle % 180) - 180;
        }
        return Angle;
    }



}
