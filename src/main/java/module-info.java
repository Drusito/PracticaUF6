module com.example.practicauf6 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jdbc;


    opens com.example.practicauf6 to javafx.fxml;
    exports com.example.practicauf6;
}