module com.example.utazasiiroda {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.utazasiiroda to javafx.fxml;
    exports com.example.utazasiiroda;
}