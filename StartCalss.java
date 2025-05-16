package application;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StartCalss extends Application {
	boolean found;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		// designing of the log in page :
		BorderPane lognPane = new BorderPane();
		Scene scene = new Scene(lognPane, 800, 500);
		VBox rightSidePane = new VBox();
		rightSidePane.setAlignment(Pos.CENTER);
		rightSidePane.setPadding(new Insets(15, 15, 15, 15));
		rightSidePane.setSpacing(20);

		TextField TFusername = new TextField();
		TFusername.setPromptText("Enter your username");
		TFusername.setStyle("-fx-prompt-text-fill: gray;");

		PasswordField TFpassword = new PasswordField();
		TFpassword.setPromptText("Enter your password");
		TFpassword.setStyle("-fx-prompt-text-fill: gray;");

		TFusername.setMaxWidth(200);
		TFpassword.setMaxWidth(200);

		Button btnLogin = new Button("Login");
		btnLogin.setStyle("-fx-background-color:#3366ff ; -fx-text-fill: white;");
		btnLogin.setMinWidth(100);

		Button btnSignUp = new Button("sign up");
		btnSignUp.setStyle("-fx-background-color:#1e7d14; -fx-text-fill: white;");

		Text txtcontactinfo = new Text();
		txtcontactinfo.setText("For more information\n call: 0599955322 ");

		Image imgicon = new Image("file:C:/Users/user/OneDrive/Изображения/imggg.png");
		stage.getIcons().add(imgicon);

		Text failLogin = new Text();
		rightSidePane.getChildren().addAll(TFusername, TFpassword, failLogin, btnLogin, btnSignUp, txtcontactinfo);
		lognPane.setRight(rightSidePane);

		VBox leftSidePane = new VBox();
		leftSidePane.setAlignment(Pos.CENTER);
		leftSidePane.setSpacing(20);

		Text txtLoginSentence = new Text("Fast. Smart. Seamless. Your perfect purchase starts here");
		txtLoginSentence.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		txtLoginSentence.setFill(Color.DARKBLUE);

		Image imgLoginPage = new Image("file:C:/Users/user/Downloads/Imgg.jpg");
		ImageView imgVLoginPage = new ImageView(imgLoginPage);
		imgVLoginPage.setFitWidth(550);
		imgVLoginPage.setFitHeight(350);

		leftSidePane.getChildren().addAll(txtLoginSentence, new Label(""), imgVLoginPage);
		lognPane.setLeft(leftSidePane);

		// functionality starts here :

		btnSignUp.setOnAction(e -> {

			VBox creatingAccountPage = new VBox();
			Scene SignUpScene = new Scene(creatingAccountPage, 800, 500);
			creatingAccountPage.setSpacing(30);
			creatingAccountPage.setAlignment(Pos.CENTER);
			creatingAccountPage.setPadding(new Insets(15, 15, 15, 15));

			Label snapBuyText = new Label("SnapBuy");
			snapBuyText.setFont(Font.font("Arial", FontWeight.BOLD, 32));
			snapBuyText.setTextFill(Color.DARKBLUE);

			TextField TFusernameCreation = new TextField();
			TFusernameCreation.setMaxWidth(200);
			TFusernameCreation.setPromptText("Username ");

			PasswordField TFpasswordCreation = new PasswordField();
			TFpasswordCreation.setPromptText("Password");
			TFpasswordCreation.setMaxWidth(200);

			PasswordField TFpasswordConfirmation = new PasswordField();
			TFpasswordConfirmation.setPromptText("Confirm password");
			TFpasswordConfirmation.setMaxWidth(200);

			Button btnCreateAccount = new Button("Create Account ");
			btnCreateAccount.setMaxWidth(200);
			btnCreateAccount.setStyle("-fx-background-color:#1e7d14; -fx-text-fill: white;");

			Button btnBackToTheLoginScene = new Button("Already have an account?");
			btnBackToTheLoginScene.setStyle("-fx-background-color: white; -fx-text-fill: blue;");
			// description of failure :
			Text failureDescription = new Text();
			failureDescription.setFill(Color.RED);
			creatingAccountPage.getChildren().addAll(snapBuyText, TFusernameCreation, TFpasswordCreation,
					TFpasswordConfirmation, failureDescription, btnCreateAccount, btnBackToTheLoginScene);

			btnBackToTheLoginScene.setOnAction(event -> {
				TFusername.clear();
				TFpassword.clear();
				failLogin.setText("");
				stage.setScene(scene);
			});

			btnCreateAccount.setOnAction(event -> {
				// Note : we can't say {{ TFusernameCreation.getText().equals(null) }}
				// because the Text node is never returns null . However , if it's empty
				// the contents are --> "" which represents as Empty NOT null .
				boolean specificationsMet = false;

				if (TFusernameCreation.getText().isEmpty()) {
					failureDescription.setText("Please enter username!");
				} else if (TFpasswordCreation.getText().isEmpty()) {
					failureDescription.setText("Please enter password!");
				} else if (TFpasswordCreation.getText().length() < 8) {
					failureDescription.setText("The password must contain more than seven characters.");
				} else if (TFpasswordConfirmation.getText().isEmpty()) {
					failureDescription.setText("Please confirm password!");
				} else if (!TFpasswordConfirmation.getText().equals(TFpasswordCreation.getText())) {
					failureDescription.setText("Incorrect password confirmation!");
				} else {
					specificationsMet = true;
				}
				if (specificationsMet) {
					// return the values in the text fields to empty
					saveToFile(TFusernameCreation.getText(), TFpasswordCreation.getText());

					TFusernameCreation.clear();
					TFpasswordCreation.clear();
					TFpasswordConfirmation.clear();
				}
			});

			stage.setScene(SignUpScene);
		});

		btnLogin.setOnAction(e -> {
			try (Scanner input = new Scanner(new File("userInfo.txt"))) {
				String enteredUsername = TFusername.getText();
				String enteredPassword = TFpassword.getText();
				found = false;
				String usernameFromFile = "";
				while (input.hasNextLine()) {
					String[] infoFromFile = input.nextLine().split(",");
					if (infoFromFile.length == 2) {
						usernameFromFile = infoFromFile[0];
						String passwordFromFile = infoFromFile[1];
						if (enteredUsername.equals(usernameFromFile) && enteredPassword.equals(passwordFromFile)) {
							found = true;
							break;
						}
					}
				}
				if (!found) {
					// setting the red text for the warning text
					failLogin.setFill(Color.RED);
					failLogin.setText("Username or password isn't \nfound , try again! ");
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "An error occurred while entering the information , try again!");
			}
		});

		stage.setScene(scene);
		stage.setTitle("SnapBuy");
		stage.setResizable(false);
		stage.show();

		// way to enter the platform

		if (found) {
			btnLogin.setOnAction(e -> {

				Pane pane = new Pane();
				Scene mainPage = new Scene(pane, 300, 300);
				stage.setResizable(true);
				stage.setScene(mainPage);
				stage.show();

			});
		}

	}

	private void saveToFile(String username, String password) {
		// this method is for saving data for new user into the file
		try (FileWriter writer = new FileWriter("userInfo.txt", true)) {
			writer.write(username + "," + password + "\n");
			Alert completedRegistrationAlert = new Alert(AlertType.INFORMATION);
			completedRegistrationAlert.setTitle("Notification");
			completedRegistrationAlert.setHeaderText("Hello " + username + " your new account is complete :)");
			completedRegistrationAlert.showAndWait();

		} catch (Exception e) {

			// JOptionPane.showMessageDialog(null, "Error saving user data , try again :(");

			Alert errorAlert = new Alert(AlertType.ERROR);
			errorAlert.setTitle("Error");
			errorAlert.setHeaderText("Error saving user data , try again :(");
			errorAlert.showAndWait();

		}
	}
}
