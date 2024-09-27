module org.example.firstview {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.firstview to javafx.fxml;
    exports org.example.firstview;
}