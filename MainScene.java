package sk.machine.controller;

import javafx.fxml.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import sk.machine.logging.ApplicationLogger;
import sk.machine.rest.RestApiAgent;
import sk.machine.status.StatusUtil;

import java.io.IOException;

public class MainScene {

    @FXML
    private AnchorPane rootAnchorPane;
    @FXML
    private TextField urlTextField;
    @FXML
    private TextArea responseArea;
    @FXML
    private Label statusLabel;

    @FXML
    private Button resetButton;
    @FXML
    private Button sendButton;

    private RestApiAgent restApiAgent;
    private ApplicationLogger logger;


    public void initialize(RestApiAgent restApiAgent,
                           ApplicationLogger logger) {
        this.restApiAgent = restApiAgent;
        this.logger = logger;
    }

    public void onSendButtonAction() {
        try {
            var url = urlTextField.getText().trim();
            var response = restApiAgent.sendGetRequest(url);
            statusLabel.setText(StatusUtil.createStatusMessage(response.getStatus()));
            responseArea.setText(response.getResponse());
        } catch (IOException exception) {
            logger.error(exception);
        }
    }

    public void onResetButtonAction() {
        responseArea.setText("");
        statusLabel.setText("");
    }


}
