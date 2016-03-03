package ui.controller;

import java.io.IOException;

import dataaccess.storage.LoginUserDto;
import dataaccess.storage.RoleDto;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ParentWindowController {
	@FXML MenuItem addMember;
	@FXML MenuItem searchMember;

	private LoginUserDto loggedInUser;

	public ParentWindowController(){

	}

	public void setLoginUser(LoginUserDto user){
		this.loggedInUser = user;
		if(user.getRole() == RoleDto.Both){
			addMember.setDisable(false);
			searchMember.setDisable(false);
		}
		else if(user.getRole() == RoleDto.Administrator){
			addMember.setDisable(false);
			searchMember.setDisable(false);
		}
		else{
			addMember.setDisable(true);
			searchMember.setDisable(true);
		}
	}

	public void showAddLibraryMemberWondow() throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/AddLibraryMember.fxml"));

		Parent root1 = null;
		root1 = (Parent)fxmlLoader.load();

		Stage stage = new Stage();
		stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Add Library Member");
        stage.setScene(new Scene(root1));
        stage.show();
	}

	public void showAddBookWondow() throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/AddBook.fxml"));

		Parent root1 = null;
		root1 = (Parent)fxmlLoader.load();

		Stage stage = new Stage();
		stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Add new Book");
        stage.setScene(new Scene(root1));
        stage.show();
	}
	public void showEditBookWondow() throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/EditBook.fxml"));

		Parent root1 = null;
		root1 = (Parent)fxmlLoader.load();

		Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Search and Edit Book");
        stage.setScene(new Scene(root1));
        stage.show();
	}

	public void showDisplayLibraryMemberWondow() throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/DisplayLibraryMember.fxml"));

		Parent root1 = null;
		root1 = (Parent)fxmlLoader.load();

		Stage stage = new Stage();
		stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Search Library Member");
        stage.setScene(new Scene(root1));
        stage.show();
	}

	public void showCheckoutRecordWindow() throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/CheckoutItemForm.fxml"));

		Parent root1 = null;
		root1 = (Parent)fxmlLoader.load();

		Stage stage = new Stage();
		stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Checkout Book");
        stage.setScene(new Scene(root1));
        stage.show();
	}
}
