module uniquindio.edu.poo {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires java.logging;

    // Abrir paquetes para la reflexión de JavaFX (por ejemplo, para FXML)
    opens uniquindio.edu.poo.billetera_app to javafx.fxml;
    opens uniquindio.edu.poo.billetera_controller to javafx.fxml;
    opens uniquindio.edu.poo.billetera_model to javafx.fxml;

    // Exportar paquetes para que puedan ser utilizados por otros módulos
    exports uniquindio.edu.poo.billetera_app;
    exports uniquindio.edu.poo.billetera_controller;
    exports uniquindio.edu.poo.billetera_model;
}
