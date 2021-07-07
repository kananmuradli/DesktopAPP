module az.javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens az.javafx.model;
    opens az.javafx to javafx.fxml;
    exports az.javafx;
}