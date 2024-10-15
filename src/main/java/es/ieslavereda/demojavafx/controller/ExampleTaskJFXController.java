package es.ieslavereda.demojavafx.controller;

import es.ieslavereda.demojavafx.model.Filosofo;
import es.ieslavereda.demojavafx.model.RegionColor;
import es.ieslavereda.demojavafx.model.Tenedor;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class ExampleTaskJFXController {
    @FXML
    private Label filosofo1_label;
    @FXML
    private Label filosofo2_label;
    @FXML
    private Label filosofo3_label;
    @FXML
    private Label filosofo4_label;
    @FXML
    private Label filosofo5_label;

    @FXML
    private Label tenedor1_label;
    @FXML
    private Label tenedor2_label;
    @FXML
    private Label tenedor3_label;
    @FXML
    private Label tenedor4_label;
    @FXML
    private Label tenedor5_label;

    private List<Thread> threads;

    private List<Tenedor> tenedores;
    private List<Filosofo> filosofos;

    @FXML
    public void initialize() {

    }

    public ExampleTaskJFXController(){
        tenedores = new ArrayList<>();
        tenedores.add(new Tenedor(tenedor1_label));
        tenedores.add(new Tenedor(tenedor2_label));
        tenedores.add(new Tenedor(tenedor3_label));
        tenedores.add(new Tenedor(tenedor4_label));
        tenedores.add(new Tenedor(tenedor5_label));

        filosofos = new ArrayList<>();
        filosofos.add(new Filosofo(filosofo1_label, tenedores.get(1), tenedores.get(4)));
        filosofos.add(new Filosofo(filosofo2_label, tenedores.get(2), tenedores.get(0)));
        filosofos.add(new Filosofo(filosofo3_label, tenedores.get(3), tenedores.get(1)));
        filosofos.add(new Filosofo(filosofo4_label, tenedores.get(4), tenedores.get(2)));
        filosofos.add(new Filosofo(filosofo5_label, tenedores.get(0), tenedores.get(3)));

        threads = new ArrayList<>();
        threads = new ArrayList<>();
        for (Filosofo filosofo : filosofos) {
            threads.add(new Thread(filosofo::come));
        }
    }
    @FXML
    protected void onHelloButtonClick() {
        for (Thread thread : threads) {
            if (!thread.isAlive()) {
                thread.start();
            }
        }
    }


}