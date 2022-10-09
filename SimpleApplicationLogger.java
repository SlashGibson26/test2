package sk.machine.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class SimpleApplicationLogger implements ApplicationLogger {

    private static final Logger logger;

    static {
        logger = LogManager.getLogger();
    }

    public void info(String message) {
        logger.info(message);
    }

    public void error(Exception exception) {
        logger.error(exception);
    }

}
