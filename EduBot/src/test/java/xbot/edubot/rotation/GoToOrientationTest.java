package xbot.edubot.rotation;

import org.junit.Test;

import xbot.edubot.rotation.RotationTestVisualizer.OrientationTest;
import xbot.edubot.rotation.RotationTestVisualizer.SelectableOrientationTest;
import xbot.edubot.subsystems.drive.commands.DriveToOrientationCommand;

public class GoToOrientationTest extends BaseOrientationEngineTest implements SelectableOrientationTest {

    @Test
    public void testGoToOrientation() {
        DriveToOrientationCommand command = injector.getInstance(DriveToOrientationCommand.class);
        double initial = 160;
        double goal = 130;
        
        command.setTargetHeading(goal);

        setUpTestEnvironment(command, initial, goal);
        runTestEnv();
    }

    @Override
    public void invokeOrientationTest(OrientationTest test) {
        switch (test) {
            case ROTATE_TO_ORIENTATION:
                this.testGoToOrientation();
                break;
            default:
                throw new RuntimeException("The requested orientation test is not available in this test class.");
        }
    }
}
