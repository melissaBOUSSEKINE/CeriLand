module com.example.ceribnb {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;

    opens com.example.ceribnb to javafx.fxml;
    exports com.example.ceribnb;
    exports com.example.ceribnb.models;
    opens com.example.ceribnb.models to com.fasterxml.jackson.databind;
}