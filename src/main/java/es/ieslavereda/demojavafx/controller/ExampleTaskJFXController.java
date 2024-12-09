package es.ieslavereda.demojavafx.controller;

import es.ieslavereda.demojavafx.model.Filosofo;
import es.ieslavereda.demojavafx.model.Tenedor;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;

public class ExampleTaskJFXController {
    @FXML
    private Label filosofo1;
    @FXML
    private Label filosofo2;
    @FXML
    private Label filosofo3;
    @FXML
    private Label filosofo4;
    @FXML
    private Label filosofo5;

    @FXML
    private Label tenedor1;
    @FXML
    private Label tenedor2;
    @FXML
    private Label tenedor3;
    @FXML
    private Label tenedor4;
    @FXML
    private Label tenedor5;

    private List<Thread> threads;

    private List<Tenedor> tenedores;

    @FXML
    public void initialize() {
        List<Filosofo> filosofos = new ArrayList<>();
        filosofos = setFilosofos(filosofos);
        for (Filosofo filosofo : filosofos) {
            filosofo.come();
        }
    }

    public List<Filosofo> setFilosofos(List<Filosofo> filosofos){
        tenedores = new ArrayList<>();
        tenedores.add(new Tenedor(tenedor1, 1));
        tenedores.add(new Tenedor(tenedor2, 2));
        tenedores.add(new Tenedor(tenedor3, 3));
        tenedores.add(new Tenedor(tenedor4, 4));
        tenedores.add(new Tenedor(tenedor5, 5));

        filosofos = new ArrayList<>();
        filosofos.add(new Filosofo(filosofo1, tenedores.get(4), tenedores.get(0)));
        filosofos.add(new Filosofo(filosofo2, tenedores.get(0), tenedores.get(1)));
        filosofos.add(new Filosofo(filosofo3, tenedores.get(1), tenedores.get(2)));
        filosofos.add(new Filosofo(filosofo4, tenedores.get(2), tenedores.get(3)));
        filosofos.add(new Filosofo(filosofo5, tenedores.get(3), tenedores.get(4)));
        return filosofos;
    }
    @FXML
    protected void onHelloButtonClick() {

    }


}