package proCode;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class MainClass extends Application {

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		/*
		 * the login page starts from here! contents : Labels: userName , password
		 * TextFields : usernameTF , passwordTF Images : imgIcon , imgBG
		 */

		VBox pane = new VBox();
		pane.setAlignment(Pos.BASELINE_CENTER);
		pane.setPadding(new Insets(30, 30, 30, 30));

		Label icon = new Label("MyCart");
		icon.setFont(Font.font("Cairo", FontWeight.BOLD, 40));

		Label userName = new Label("Username :");

		TextField usernameTF = new TextField();
		usernameTF.setMaxWidth(150);

		Label password = new Label("Password :");

		TextField passwordTF = new TextField();
		passwordTF.setMaxWidth(150);

		userName.setFont(Font.font("Cairo", FontWeight.BOLD, 20));
		userName.setTextFill( Color.web("#FFFFFF"));
		password.setTextFill(Color.web("#FFFFFF"));
		icon.setTextFill(Color.web("#FFFFFF"));
		password.setFont(Font.font("Cairo", FontWeight.BOLD, 20));
		Button btnLogIn = new Button("Login ");
		Button btnSignUp = new Button("sign up ");

		BackgroundImage setimgBG = new BackgroundImage(new Image("file:C:/Users/user/Downloads/safe_shopping_1.jpg"),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));
		Background imgBG = new Background(setimgBG);
		pane.setBackground(imgBG);

		pane.getChildren().addAll(icon, new Label(""), new Label(""), userName, usernameTF, password, passwordTF,
				new Label(""), btnLogIn, new Label(""), btnSignUp);

		Scene loginScene = new Scene(pane, 500, 500);
		Image imgIcon = new Image("file:C:/Users/user/Downloads/WhatsApp Image 2025-05-01 at 10.55.49 PM (1).jpeg");
		stage.setResizable(false);
		stage.getIcons().add(imgIcon);
		stage.setTitle("MyCart");
		// design part ends here
		// functionality follows :
		btnLogIn.setOnAction(e -> {
			try (Scanner input = new Scanner(new File("usersInfo.txt"))) {
				String enteredUsername = usernameTF.getText();
				String enteredPassword = passwordTF.getText();
				boolean found = false;
				String usernameFromFile= "";
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

				if (found) {
					JOptionPane.showMessageDialog(null, "Login successful! "+ usernameFromFile );
					
				} else {
					JOptionPane.showMessageDialog(null, "Incorrect username or password , enter sign up for creating new account ");
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "An error occurred while entering the information , try again!");
			}
		});
		
		stage.setScene(loginScene);
		stage.show();
	}
}