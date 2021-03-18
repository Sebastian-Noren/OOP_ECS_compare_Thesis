module org.ecsthesis {
    requires javafx.controls;
    requires javafx.fxml;

    opens ecs_bank to javafx.fxml;
    exports ecs_bank;
}