package xbot.edubot.basic_understanding;

import org.apache.log4j.Logger;
import org.junit.Test;

import xbot.common.command.XScheduler;
import xbot.common.injection.BaseWPITest;

public class C_ObserveFightingCommands extends BaseWPITest{
	
	protected Logger log;
	
	@Test
	public void watchCommandsFight() {
		log = Logger.getLogger(C_ObserveFightingCommands.class);
		
		CommandA cmda = injector.getInstance(CommandA.class);
		CommandB cmdb = injector.getInstance(CommandB.class);
		
		// CommandA starts. 
		cmda.start();
		
		XScheduler scheduler = injector.getInstance(XScheduler.class);
		
		for (int i = 0; i < 5; i++) {
			log.info("SCHEDULER ON STEP " + i);
			scheduler.run();
		}
		
		// Now we start CommandB. When you run this test, observe
		// that CommandA is no longer writing messages to the console - CommandB
		// has interrupted it and now has control!
		
		cmdb.start();
		
		for (int i = 0; i < 5; i++) {
			log.info("SCHEDULER ON STEP " + (i+4));
			scheduler.run();
		}
	}
}
