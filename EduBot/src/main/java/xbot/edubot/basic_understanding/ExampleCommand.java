package xbot.edubot.basic_understanding;

import xbot.common.command.BaseCommand;

public class ExampleCommand extends BaseCommand {

    @Override
    public void initialize() {
        log.info("Initializing!");
    }

    @Override
    public void execute() {
        log.info("Executing.");
    }
    
    @Override
    public boolean isFinished() {
        log.info("Checking isFinished.");
        return super.isFinished();
    }
    
    
}
