module OOPThesis {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens ecs_bank to javafx.fxml;
    opens ecs_bank.controller to javafx.fxml;
    exports ecs_bank;
    exports ecs_bank.controller;
}