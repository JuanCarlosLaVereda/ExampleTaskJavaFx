package es.ieslavereda.demojavafx.model;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.awt.*;

public class Tenedor {
    @FXML
    private Label label;
    private int id;

    public Tenedor(Label label, int id) {
        this.label = label;
        this.id = id;
    }

    public Label getLabel() {
        return label;
    }

    public int getId() {
        return id;
    }
}
