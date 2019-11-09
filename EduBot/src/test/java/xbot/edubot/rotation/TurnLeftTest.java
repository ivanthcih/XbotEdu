package xbot.edubot.rotation;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import xbot.edubot.BaseDriveTest;
import xbot.edubot.subsystems.drive.commands.TurnLeft90DegreesCommand;

public class TurnLeftTest extends BaseDriveTest{ // tests the the turning for TurnLeft90
    @Test
    public void From150(){
       double result = TurnLeft90DegreesCommand.To90Angle(240); // 240 how degrees you are moving
       assertEquals(-120, result, 0.001); // -120 is the goal

    }

    @Test
    public void Opposite150(){
        double result = TurnLeft90DegreesCommand.To90Angle(0); 
        assertEquals(0, result, 0.001); 
 
     }

     @Test
    public void Opposite370(){
        double result = TurnLeft90DegreesCommand.To90Angle(370); 
        assertEquals(-170, result, 0.001);
 
     }

     @Test
     public void From370(){
      double result = TurnLeft90DegreesCommand.To90Angle(360); 
      assertEquals(-180, result, 0.001);

   }
}