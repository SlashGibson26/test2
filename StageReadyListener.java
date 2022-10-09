package sk.machine.listener;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import sk.machine.controller.MainScene;
import sk.machine.event.StageReadyEvent;
import sk.machine.logging.ApplicationLogger;
import sk.machine.rest.RestApiAgent;

@Component
public class StageReadyListener implements ApplicationListener<StageReadyEvent> {

    private final ApplicationLogger logger;
    private ApplicationContext context;

    public StageReadyListener(ApplicationLogger logger) {
        this.logger = logger;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent stageReadyEvent) {
        context = stageReadyEvent.getContext();
        loadMainScene(stageReadyEvent.getStage());
        logger.info("Application started...");
    }

    private void loadMainScene(Stage stage) {
        try {
            var loader = new FXMLLoader(MainScene.class.getResource("MainScene.fxml"));
            var scene = new Scene(loader.load());
            stage.setScene(scene);

            MainScene controller = loader.getController();
            var apiAgent = context.getBean(RestApiAgent.class);
            controller.initialize(apiAgent, logger);

            stage.show();
        } catch (Exception exception) {
            logger.error(exception);
        }
    }
}
