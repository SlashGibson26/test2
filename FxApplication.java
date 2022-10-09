package sk.machine.main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import sk.machine.event.StageReadyEvent;
import sk.machine.logging.ApplicationLogger;

public class FxApplication extends Application {

    private ConfigurableApplicationContext context;
    private ApplicationLogger logger;

    public static void run() {
        launch();
    }

    @Override
    public void init() throws Exception {
        super.init();
        context = new SpringApplicationBuilder(SuperMain.class)
                .headless(false)
                .build()
                .run();
        logger = context.getBean(ApplicationLogger.class);
    }

    @Override
    public void start(Stage stage) {
        context.publishEvent(new StageReadyEvent(context, stage));
    }

    @Override
    public void stop() throws Exception {
        logger.info("Exiting application...");
        super.stop();
        context.stop();
        Platform.exit();
        System.exit(0);
    }
}
