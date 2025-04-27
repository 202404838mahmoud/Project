import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ProjectOOP extends Application {
    public void start(Stage primaryStage) throws Exception {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(15);  
        pane.setVgap(15);
        pane.setPadding(new Insets(15, 15, 15, 15));
        
        Scene scene = new Scene(pane, 450, 350);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Shop Login");
        primaryStage.show();
        
        // main window code :
        Label userNameLabel = new Label("User Name:");
        userNameLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 18));
        
        Label passwordLabel = new Label("Password:");
        passwordLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 18));
        
        TextField usernameField = new TextField();
        TextField passwordField = new TextField();
        
        Button btnSubmit = new Button("Submit");
        btnSubmit.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        btnSubmit.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
        
        Button btnCreateAccount = new Button("Create Account");
        btnCreateAccount.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        btnCreateAccount.setStyle("-fx-background-color: white; -fx-text-fill: black;");
        
        
        pane.add(userNameLabel, 0, 0);
        pane.add(usernameField, 1, 0);
        
        pane.add(passwordLabel, 0, 1);
        pane.add(passwordField, 1, 1);
        
        pane.add(btnCreateAccount, 1, 3);
        pane.add(btnSubmit, 1, 2);
        
       
        GridPane.setMargin(btnCreateAccount, new Insets(10, 0, 0, 0));
        GridPane.setMargin(btnSubmit, new Insets(10, 0, 0, 0));
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
