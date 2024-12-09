package es.ieslavereda.demojavafx.model;
import es.ieslavereda.demojavafx.ExampleTaskJFXApplication;
import es.ieslavereda.demojavafx.controller.ExampleTaskJFXController;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.control.Label;

import javafx.fxml.FXML;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Filosofo {
    private Tenedor tenedor_derecha;
    private Tenedor tenedor_izquierda;
    private Label label;

    public Filosofo(Label label, Tenedor tenedor_derecha, Tenedor tenedor_izquierda) {
        this.tenedor_derecha = tenedor_derecha;
        this.tenedor_izquierda = tenedor_izquierda;
        this.label = label;
    }

    public Label getLabel() {
        return label;
    }

    public Tenedor getTenedor_derecha() {
        return tenedor_derecha;
    }

    public Tenedor getTenedor_izquierda() {
        return tenedor_izquierda;
    }


    public void setTenedor_derecha(Tenedor tenedor_derecha) {
        this.tenedor_derecha = tenedor_derecha;
    }

    public void setTenedor_izquierda(Tenedor tenedor_izquierda) {
        this.tenedor_izquierda = tenedor_izquierda;
    }

    public void come(){
       TaskComer taskComer = new TaskComer();
       Thread thread = new Thread(taskComer);
       thread.start();
    }

    private class TaskComer extends Task<List<RegionColor>> {

        public TaskComer() {
            valueProperty().addListener((observableValue, oldValue, newValues) -> {

                if (newValues != null) {
                    for (RegionColor newValue : newValues) {
                        newValue.getRegion().setBackground(new Background(new BackgroundFill(newValue.getColor(), new CornerRadii(5), new Insets(-5))));
                    }

                }
            });
        }
        @Override
        protected List<RegionColor> call() throws Exception {
            while (true){
                Tenedor tenedor_menor;
                Tenedor tenedor_mayor;
                if (tenedor_izquierda.getId()<tenedor_derecha.getId()){
                    tenedor_menor = tenedor_izquierda;
                    tenedor_mayor = tenedor_derecha;
                } else {
                    tenedor_menor = tenedor_derecha;
                    tenedor_mayor = tenedor_izquierda;
                }
                synchronized (tenedor_menor) {
                    updateValue(Arrays.asList(new RegionColor(tenedor_menor.getLabel(), Color.RED),
                                    new RegionColor(label, Color.RED))
                    );
                    Thread.sleep(1);
                    synchronized (tenedor_mayor) {
                        updateValue(Arrays.asList(new RegionColor(tenedor_mayor.getLabel(), Color.RED), new RegionColor(label, Color.GREEN),
                                new RegionColor(tenedor_derecha.getLabel(), Color.GREEN), new RegionColor(tenedor_izquierda.getLabel(), Color.GREEN)));
                        Thread.sleep(3000);

                        // Después de comer, el filósofo deja ambos tenedores
                        updateValue(Arrays.asList(new RegionColor(tenedor_derecha.getLabel(), Color.TRANSPARENT), new RegionColor(tenedor_izquierda.getLabel(), Color.TRANSPARENT),
                                new RegionColor(label, Color.TRANSPARENT)));
                        Thread.sleep(1);
                    }
                }
                Thread.sleep(4000);
            }
        }
    }
}


