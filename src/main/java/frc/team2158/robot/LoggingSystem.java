package frc.team2158.robot;

import java.io.IOException;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

public class LoggingSystem {
    private static final Logger LOGGER = Logger.getLogger(LoggingSystem.class.getName());
    private static final class CustomFormatter extends Formatter {
        private final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS");

        /**
         * Returns a formatted version of the given record.
         * @param record the record to format.
         * @return a String representation of the record.
         */
        @Override
        public String format(LogRecord record) {
            return MessageFormat.format("{0} - [{1}::{2}] - [{3}] - {4}\n",
                    dateFormat.format(new Date(record.getMillis())), record.getSourceClassName(),
                    record.getSourceMethodName(), record.getLevel(), record.getMessage());
        }
    }
    private static final Formatter FORMATTER = new CustomFormatter();

    private static LoggingSystem instance;

    /**
     * Creates an instance of LoggingSystem. Throws an exception if the file for the log can't be opened or written to.
     * @throws IOException thrown if the FileHandler can't be instantiated as a result of some strange IO trickery.
     */
    private LoggingSystem(String outputPath) throws IOException {
        // Create Logger instance on the global level so that we get all messages passed up to us.
        Logger logger = Logger.getLogger("");

        // Remove the default ConsoleHandler from the global logger. Alternatively one could change its formatter and
        // just not make a new ConsoleHandler but this should be more versatile.
        for(Handler handler : logger.getHandlers()) {
            logger.removeHandler(handler);
        }

        // Create the ConsoleHandler and add it before the FileHandler so that if something bad happens we can at least
        // say so on the console if not in the log as well.
        ConsoleHandler consoleHandler = new ConsoleHandler() {{
            setFormatter(FORMATTER);
        }};
        logger.addHandler(consoleHandler);

        // Finally, create the FileHandler and add it to the Logger.
        FileHandler fileHandler = new FileHandler(outputPath) {{
            setFormatter(FORMATTER);
        }};
        logger.addHandler(fileHandler);

        // Tell the Logger that we've finished initializing the LoggingSystem and that nothing *super* bad has happened.
        LOGGER.info("Initialized logging system.");
    }

    /**
     * Returns the instance of the LoggingSystem if one exists. If one doesn't one is created and then returned.
     * @return the instance of LoggingSystem.
     */
    public static LoggingSystem getInstance() {
        if(instance == null) {
            try {
                instance = new LoggingSystem("/home/lvuser/output.log");
            }
            catch(IOException e) {
                throw new RuntimeException("Unable to start logging system. Error is as follows:\n" + e.toString());
            }
        }
        return instance;
    }
}
