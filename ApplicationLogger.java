package sk.machine.logging;

public interface ApplicationLogger {

    void info(String message);

    void error(Exception exception);

}
