package uniquindio.edu.poo.billetera_app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.nio.file.Paths;
import java.nio.file.Path;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("InicioSesion"), 640, 480);
        stage.setScene(scene);
        stage.setTitle("Inicio de Sesión");
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
        launch();
    }

}