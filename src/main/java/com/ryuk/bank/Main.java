package com.ryuk.bank;

import java.util.List;

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
import javafx.scene.chart.PieChart;
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
	private final Insets marginLabel= new Insets(40, 0, 0, 20);

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
						lb_info.getStyleClass().add("label-error");
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
			} catch (final Exception ex) {
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
		btn_logout.getStyleClass().addAll("btn", "btn-outline-dark");
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
		btn_logout.setAlignment(Pos.TOP_CENTER);
		
		final VBox aside = new VBox(20);
		aside.getStyleClass().add("aside");
		aside.getChildren().add(btn_logout);
		for (final CompteBancaireDTO compte : CompteBancaireDAO.getComptesByUserId(user.getId())) {
			aside.getChildren().add(new Label("compte n°" + compte.getNumeroCompte()));
		}

		
		root.setLeft(aside);

		final EventHandler<MouseEvent> event= new EventHandler<MouseEvent>() {
			@Override
			public void handle(final MouseEvent event) {
				// TODO Auto-generated method stub
				final FadeTransition fadeOut = new FadeTransition(Duration.millis(500), root.getCenter());
				fadeOut.setFromValue(1.0);
				fadeOut.setToValue(0.0);
				fadeOut.setOnFinished(ev -> {
					final Label lb= new Label(((Label) event.getSource()).getText());
					lb.getStyleClass().add("label-title");
					final Group labelContainer = new Group(lb);
					final StackPane center = new StackPane(labelContainer);
					StackPane.setAlignment(labelContainer, Pos.TOP_LEFT); 
					StackPane.setMargin(labelContainer, marginLabel);
					
					root.setCenter(center);
				});
				fadeOut.play();								
			}
			
		};
		
		for (final Node item : aside.getChildren()) {
			if (!(item instanceof Label)) {
				continue;
			}
			item.setOnMouseClicked(event);
		}
		
		final Label lb = new Label("Bienvenue " + user.getPrenom());
		lb.getStyleClass().add("label-title");
		final Group labelContainer = new Group(lb);
		final StackPane center = new StackPane(labelContainer);
		StackPane.setAlignment(labelContainer, Pos.TOP_LEFT); 
		StackPane.setMargin(labelContainer, marginLabel);
		
		final PieChart circle = new PieChart();
		final List<CompteBancaireDTO> accounts = CompteBancaireDAO.getComptesByUserId(user.getId());

		for (final CompteBancaireDTO compte : accounts) {
			if (compte.getStatus().equals("inactif")) {
				continue;
			}
		    circle.getData().add(new PieChart.Data(compte.getNumeroCompte(), compte.getSolde()));
		}
		
		final Label title = new Label("Gestions des comptes");
		title.getStyleClass().add("piechart-Legend");
		final VBox chartBox = new VBox(10, title, circle);
		chartBox.getStyleClass().add("container-piechart");
		//chartBox.setAlignment(Pos.TOP_CENTER);
		
		final Group grp= new Group(chartBox);
		StackPane.setAlignment(grp, Pos.TOP_RIGHT);
		center.getChildren().add(grp);
		root.setCenter(center);

		stage.setScene(scene);
		stage.setMaximized(false);
		stage.setMaximized(true);
	}

	public static void main(final String[] args) {
		launch(args);
	}
}
