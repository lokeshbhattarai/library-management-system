package ui.controller;

import business.LoginDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginPageController {
	@FXML TextField username;
	@FXML PasswordField password;
	@FXML Label status;

	private LoginDao login;

	public LoginPageController(){
		login = new LoginDao();
	}

	public void login(){
		//String username = this.username.getText().trim();
		//String password = this.password.getText().trim();

		if(true/*this.validateUser(username, password)*/){
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/ParentWindow.fxml"));

				Parent root1 = (Parent)fxmlLoader.load();

		        Stage stage = new Stage();
		        stage.setMaximized(true);
		        stage.setTitle("Library Management System");
		        stage.setScene(new Scene(root1));

		        Stage currentStage = (Stage)this.username.getScene().getWindow();
		        currentStage.close();

		        stage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		else{
			this.status.setText("Invalid username or password !!!");
		}
	}

	public boolean validateUser(String username, String password){
		login.validateUser();

		return false;
		//System.out.println("login...");
	}
}
