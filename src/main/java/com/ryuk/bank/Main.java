package com.ryuk.bank;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

	@SuppressWarnings("exports")
	@Override
	public void start(Stage primaryStage) {
        final VBox loginForm = new VBox(10);
        loginForm.setAlignment(Pos.CENTER);
        loginForm.setPrefSize(200, 250);
        
        final TextField tf= new TextField();
        
        final PasswordField pf= new PasswordField();
        
        loginForm.getChildren().addAll(new Label("Identifiant :"), tf, new Label("Mot de passe:"), pf, new Button("Connexion"));
        

        final Group loginContainer = new Group(loginForm);
        final StackPane center = new StackPane(loginContainer);
        center.setAlignment(Pos.CENTER);
        final BorderPane root = new BorderPane();
        root.setCenter(center);

        final Scene scene = new Scene(root, 800, 500);
        scene.getStylesheets().add(getClass().getResource("/css/login.css").toExternalForm());
        primaryStage.setTitle("Bank App");
        primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
