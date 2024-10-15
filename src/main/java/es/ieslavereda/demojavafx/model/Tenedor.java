package es.ieslavereda.demojavafx.model;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.awt.*;

public class Tenedor {
    @FXML
    private Label label;

    public Tenedor(Label label) {
        this.label = label;
    }

    public Label getLabel() {
        return label;
    }


}
