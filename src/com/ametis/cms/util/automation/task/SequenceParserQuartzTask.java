/**
 * 
 */
package com.ametis.cms.util.automation.task;

import java.io.IOException;
import java.util.logging.SimpleFormatter;

import com.ametis.cms.util.parser.SequenceParser;

/**
 * @author Mashuri Hasan
 *
 */
public class SequenceParserQuartzTask {
	private SequenceParser parser;

	public void executeParse(){
		/*Logger logger = Logger.getLogger("sequence-App");
		FileHandler logFile;
		SimpleFormatter simpleFormatter = new SimpleFormatter();
		try {
			logFile = new FileHandler("sequence-app-%g.log", 5242880, 10, true);
			logFile.setFormatter(simpleFormatter);
			logger.addHandler(logFile);
		} catch (IOException e) {
			String warning = "Failure Initializing Logging";
			logger.severe(warning);
		}*/

		parser.refreshSequence();
	}
}
