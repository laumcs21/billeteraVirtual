package uniquindio.edu.poo.billetera_app;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uniquindio.edu.poo.billetera_model.Billetera_virtual;
import uniquindio.edu.poo.billetera_model.Servidor;
import uniquindio.edu.poo.rabbitmq.TransactionConsumer;
import uniquindio.edu.poo.rabbitmq.TransactionProducer;

public class App extends Application {

    private static Scene scene;
    private static TransactionProducer transactionProducer;

    @Override
    public void start(Stage stage) throws IOException {

        transactionProducer = new TransactionProducer();
        TransactionConsumer transactionConsumer = new TransactionConsumer();
        transactionConsumer.start();

        scene = new Scene(loadFXML("InicioSesion"), 640, 480);
        stage.setScene(scene);
        stage.setTitle("Inicio de Sesión");
        stage.setOnCloseRequest(event -> {
            Billetera_virtual.getInstancia().detenerHiloRespaldo();
        });
        stage.show();
    }

    public static void setRoot(String fxml, String title) throws IOException {
        scene.setRoot(loadFXML(fxml));
        Stage stage = (Stage) scene.getWindow();
        stage.setTitle(title);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        Path path = Paths.get("src/main/java/uniquindio/edu/poo/billetera_view/" + fxml + ".fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(path.toUri().toURL());
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        new Servidor();
        launch();
    }

    public static TransactionProducer getTransactionProducer() {
        return transactionProducer;
    }
}
