module com.example.sep2 {
  requires javafx.controls;
  requires javafx.fxml;

  opens model to javafx.fxml, javafx.base;
  opens viewmodel to javafx.fxml;
  opens view to javafx.fxml;
  exports application;
}