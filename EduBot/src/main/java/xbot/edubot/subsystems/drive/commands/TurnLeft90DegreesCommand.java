package xbot.edubot.subsystems.drive.commands;

import com.google.inject.Inject;

import xbot.common.command.BaseCommand;
import xbot.edubot.subsystems.drive.DriveSubsystem;

// init(initialized) is first, then isFinished goes before execute method

public class TurnLeft90DegreesCommand extends BaseCommand {
    
    DriveSubsystem drive; // write class-level variables here
    double leftPower;
    double speed;
    double GoalYaw;
    double currentYaw;
    double oldYaw;

    @Inject
    public TurnLeft90DegreesCommand(DriveSubsystem driveSubsystem) {
        this.drive = driveSubsystem;
    }
    
    @Override
    public void initialize() { 
        GoalYaw = drive.getYaw() + 90;
        if(Math.abs(GoalYaw) > 180){ // turn 240 degrees, -120 degrees| if i put -180 then it will turn around in circles
            GoalYaw = (GoalYaw % 180) - 180;
        }
    }
    
    @Override
    public void execute() { // the main part of the code 
        currentYaw = drive.getYaw();
        speed = currentYaw - oldYaw;

        double GoalDistYaw = Math.abs(GoalYaw - currentYaw);  // the distance between the Goal and Current Yaw

        leftPower = GoalDistYaw/90 * 5 - speed*1; // the closer you get, the power goes lower | subtracted by the velocity

        drive.tankDrive(-leftPower, leftPower);
        
        oldYaw = currentYaw;
    }

    public boolean isFinished(){ //suggestion: need to add an isFinished here?
        boolean nearGoal = Math.abs( drive.getYaw() - GoalYaw) < 0.7;
        boolean movingSlow = Math.abs(speed) < 0.6;

       if(nearGoal && movingSlow){
            return true;
       }

        return false;
    }
    
    public static double To90Angle(double Angle){ // for HelpTurnLeftTest
        if(Math.abs(Angle) > 180){
            return((Angle % 180) - 180);
        }
        return Angle;
    }

}

