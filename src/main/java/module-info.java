module com.example.sep2 {
  requires javafx.controls;
  requires javafx.fxml;
  requires java.desktop;
  requires remoteobserver;
  requires java.rmi;

  opens client to java.rmi;
  opens shared to remoteobserver, java.rmi;
  opens model to javafx.fxml, javafx.base;
  opens viewmodel to javafx.fxml;
  opens view to javafx.fxml;
  exports application;
  exports model;
  opens view.guest to javafx.fxml;
  opens view.member to javafx.fxml;
  opens view.admin to javafx.fxml;
}