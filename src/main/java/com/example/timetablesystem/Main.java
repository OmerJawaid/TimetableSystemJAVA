package com.example.timetablesystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Correctly specify the location of the FXML file
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/timetablesystem/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 768, 525);
        primaryStage.setTitle("Login System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
