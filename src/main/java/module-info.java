module org.ecsthesis {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.ecsthesis to javafx.fxml;
    exports org.ecsthesis;
}