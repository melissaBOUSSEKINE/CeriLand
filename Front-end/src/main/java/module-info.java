module com.example.ceribnb {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    opens com.example.ceribnb to javafx.fxml;
    exports com.example.ceribnb;
}