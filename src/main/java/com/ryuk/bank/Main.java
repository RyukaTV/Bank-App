package com.ryuk.bank;

import com.ryuk.bank.DAO.ClientDAO;
import com.ryuk.bank.DTO.ClientDTO;

import javafx.animation.FadeTransition;
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
import javafx.util.Duration;

public class Main extends Application {
	
	private final BorderPane root = new BorderPane();

	@SuppressWarnings("exports")
	@Override
	public void start(Stage primaryStage) {				
		final Scene scene = new Scene(root, 800, 500);
		scene.getStylesheets().add(getClass().getResource("/css/login.css").toExternalForm());
		
		final VBox loginForm = new VBox(10);
		final Group loginContainer = new Group(loginForm);
		final StackPane center = new StackPane(loginContainer);
		root.setCenter(center);
		final TextField tf = new TextField();
		tf.getStyleClass().add("fields");

		final PasswordField pf = new PasswordField();
		pf.getStyleClass().add("fields");

		final Button btn_login = new Button("Connexion");
		loginForm.getChildren().addAll(new Label("Identifiant :"), tf, new Label("Mot de passe:"), pf, new Label(),
				btn_login);
		
		btn_login.setOnAction(e -> {
		    if (!tf.getText().trim().isEmpty() && !pf.getText().trim().isEmpty()) {
		    	final ClientDTO client= ClientDAO.getUserbyLoginandMdp(tf.getText(), pf.getText());
		        if (client != null) {
		            final FadeTransition fadeOut = new FadeTransition(Duration.millis(500), root);
		            fadeOut.setFromValue(1.0);
		            fadeOut.setToValue(0.0);
		            fadeOut.setOnFinished(ev -> {
		                root.getChildren().clear();
		                scene.getStylesheets().remove(getClass().getResource("/css/login.css").toExternalForm());
		                homePage(client);
		            });
		            fadeOut.play();
		        }
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
		
		
		primaryStage.setTitle("Bank App");
		primaryStage.setMaximized(true);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private void homePage(final ClientDTO user) {
		final Scene scene= root.getScene();	
		scene.getStylesheets().add(getClass().getResource("/css/home.css").toExternalForm());
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
