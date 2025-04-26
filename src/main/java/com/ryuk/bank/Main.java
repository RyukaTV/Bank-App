package com.ryuk.bank;

import com.ryuk.bank.DAO.ClientDAO;
import com.ryuk.bank.DAO.CompteBancaireDAO;
import com.ryuk.bank.DTO.ClientDTO;
import com.ryuk.bank.DTO.CompteBancaireDTO;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

	private Stage stage;

	@SuppressWarnings("exports")
	@Override
	public void start(Stage primaryStage) {
		final BorderPane root = new BorderPane();
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
		final Label lb_info = new Label();
		loginForm.getChildren().addAll(lb_info, new Label("Identifiant :"), tf, new Label("Mot de passe:"), pf,
				new Label(), btn_login);

		btn_login.setOnAction(e -> {
			try {
				if (!tf.getText().trim().isEmpty() && !pf.getText().trim().isEmpty()) {
					final ClientDTO client = ClientDAO.getUserbyLoginandMdp(tf.getText(), pf.getText());
					if (client == null) {
						lb_info.setText("Identifiant/mot de passe incorrect");
						lb_info.setStyle("-fx-text-fill: red;");
						tf.setText(null);
						pf.setText(null);
						tf.requestFocus();
						final PauseTransition pause = new PauseTransition(Duration.seconds(5));
						pause.setOnFinished(ae -> {
							final FadeTransition fadeOut = new FadeTransition(Duration.millis(500), lb_info);
							fadeOut.setFromValue(1.0);
							fadeOut.setToValue(0.0);
							fadeOut.setOnFinished(ev -> {
								lb_info.setText(null);
								lb_info.setOpacity(1.0);
							});
							fadeOut.play();
						});
						pause.play();
						return;
					}
					final FadeTransition fadeOut = new FadeTransition(Duration.millis(500), root);
					fadeOut.setFromValue(1.0);
					fadeOut.setToValue(0.0);
					fadeOut.setOnFinished(ev -> {
						root.getChildren().clear();
						homePage(client);
					});
					fadeOut.play();
				}
			} catch (Exception ex) {
				// TODO: handle exception
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
		primaryStage.setScene(scene);
		primaryStage.setMaximized(false);
		primaryStage.setMaximized(true);
		this.stage = primaryStage;
		primaryStage.show();
	}

	private void homePage(final ClientDTO user) {
		final BorderPane root = new BorderPane();
		final Scene scene = new Scene(root, 800, 500);
		scene.getStylesheets().add(getClass().getResource("/css/home.css").toExternalForm());

		
		final Button btn_logout = new Button("Déconnexion");
		btn_logout.getStyleClass().addAll("btn", "btn-outline-light");
		VBox.setMargin(btn_logout, new Insets(20, 0, 0, 10));

		btn_logout.setOnAction(e -> {
			final FadeTransition fadeOut = new FadeTransition(Duration.millis(500), root);
			fadeOut.setFromValue(1.0);
			fadeOut.setToValue(0.0);
			fadeOut.setOnFinished(ev -> {
				root.getChildren().clear();
				start(stage);
			});
			fadeOut.play();
		});
		btn_logout.setAlignment(Pos.TOP_LEFT);
		
		final VBox aside = new VBox(20);
		aside.getChildren().add(btn_logout);
		for (final CompteBancaireDTO compte : CompteBancaireDAO.getComptesByUserId(user.getId())) {
			aside.getChildren().add(new Label("compte n°" + compte.getNumeroCompte()));
		}

		
		root.setLeft(aside);

		final EventHandler<MouseEvent> event= new EventHandler<MouseEvent>() {
			@Override
			public void handle(final MouseEvent event) {
				// TODO Auto-generated method stub
				final Label lb= new Label(((Label) event.getSource()).getText());
				lb.setStyle("-fx-text-fill: black");
				lb.setAlignment(Pos.TOP_LEFT);
				final Group labelContainer = new Group(lb);
				final StackPane center = new StackPane(labelContainer);
				root.setCenter(center);
			}
			
		};
		
		for (final Node item : aside.getChildren()) {
			if (!(item instanceof Label)) {
				continue;
			}
			item.setOnMouseClicked(event);
		}
		
		final Label lb = new Label("Bienvenue " + user.getPrenom());
		lb.setStyle("-fx-text-fill: black");
		lb.setAlignment(Pos.TOP_LEFT);
		final Group labelContainer = new Group(lb);
		final StackPane center = new StackPane(labelContainer);
		root.setCenter(center);

		stage.setScene(scene);
		stage.setMaximized(false);
		stage.setMaximized(true);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
