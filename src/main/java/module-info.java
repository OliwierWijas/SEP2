module com.example.sep2 {
  requires javafx.controls;
  requires javafx.fxml;

  opens model to javafx.fxml;
  exports model;
}