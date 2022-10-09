package sk.machine.event;

import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;

public class StageReadyEvent extends ApplicationEvent {

    private final ApplicationContext context;

    public StageReadyEvent(ApplicationContext context, Stage stage) {
        super(stage);
        this.context = context;
    }

    public ApplicationContext getContext() {
        return context;
    }

    public Stage getStage() {
        return (Stage) getSource();
    }

}
