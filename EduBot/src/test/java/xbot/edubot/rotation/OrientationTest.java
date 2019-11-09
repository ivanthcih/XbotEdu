package xbot.edubot.rotation;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import xbot.edubot.BaseDriveTest;
import xbot.edubot.subsystems.drive.commands.DriveToOrientationCommand;

public class OrientationTest extends BaseDriveTest{
    
    double result;
    @Test // what makes it have the ability to run the methods
    public void test(){
        result = DriveToOrientationCommand.OrientatTest(-150);

    }
}