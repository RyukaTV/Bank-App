package com.ryuk.bank;

import com.ryuk.bank.DAO.ClientDAO;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

	@SuppressWarnings("exports")
	@Override
	public void start(Stage primaryStage) {
		final VBox loginForm = new VBox(10);
		final TextField tf = new TextField();
		tf.getStyleClass().add("fields");

		final PasswordField pf = new PasswordField();
		pf.getStyleClass().add("fields");

		final Button btn_login = new Button("Connexion");
		loginForm.getChildren().addAll(new Label("Identifiant :"), tf, new Label("Mot de passe:"), pf, new Label(),
				btn_login);
		
		btn_login.setOnAction(e -> {
			if (!tf.getText().trim().equals("") && !pf.getText().trim().equals("")) {	
				System.out.println(ClientDAO.getUserbyLoginandMdp(tf.getText(), pf.getText()));
			}
		});
		
		tf.setOnKeyPressed(e -> {
			if (e.getCode().equals(KeyCode.ENTER)) {
				pf.requestFocus();
			}
			
		});
		pf.setOnKeyPressed(e -> {
			if (e.getCode().equals(KeyCode.ENTER)) {
				btn_login.arm();
				btn_login.fire();
			}
		});

		final Group loginContainer = new Group(loginForm);
		final StackPane center = new StackPane(loginContainer);
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
