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

public class Filosofo {
    private Tenedor tenedor_derecha;
    private Tenedor tenedor_izquierda;
    private Label label;

    public Filosofo(Label label, Tenedor tenedor_derecha, Tenedor tenedor_izquierda) {
        tenedor_derecha = tenedor_izquierda;
        tenedor_izquierda = tenedor_derecha;
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
        TaskComer task = new TaskComer();

        // Crear un hilo que ejecute la tarea
        Thread thread = new Thread(task);
        thread.setDaemon(true);  // Hacer que el hilo sea daemon para que no bloquee el cierre de la app
        thread.start();  // Iniciar el hilo que ejecuta la tarea de comer
    }

    private class TaskComer extends Task<RegionColor> {

        public TaskComer() {
            valueProperty().addListener((observableValue, oldValue, newValue) -> {
                if (newValue != null) {
                    newValue.getRegion().setBackground(new Background(new BackgroundFill(newValue.getColor(), new CornerRadii(5), new Insets(-5))));
                }
            });
        }
        @Override
        protected RegionColor call() throws Exception {
            while (true){
                synchronized (getTenedor_izquierda()) {
                    updateValue(new RegionColor(tenedor_izquierda.getLabel(), Color.RED));
                    updateValue(new RegionColor(label, Color.RED));

                    // Filósofo espera por el tenedor derecho
                    synchronized (getTenedor_derecha()) {
                        updateValue(new RegionColor(tenedor_derecha.getLabel(), Color.RED));

                        // Ahora el filósofo tiene ambos tenedores, puede empezar a comer
                        updateValue(new RegionColor(label, Color.GREEN));
                        updateValue(new RegionColor(tenedor_derecha.getLabel(), Color.GREEN));
                        updateValue(new RegionColor(tenedor_izquierda.getLabel(), Color.GREEN));

                        // Simulación de comer (3 segundos)
                        Thread.sleep(3000);

                        // Después de comer, el filósofo deja ambos tenedores
                        updateValue(new RegionColor(tenedor_derecha.getLabel(), Color.TRANSPARENT));
                        updateValue(new RegionColor(tenedor_izquierda.getLabel(), Color.TRANSPARENT));
                        updateValue(new RegionColor(label, Color.TRANSPARENT));
                    }
                }

                // El filósofo se toma una siesta (5 segundos)
                Thread.sleep(5000);
            }
        }
    }
}


