package ui.controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ParentWindowController {

	public ParentWindowController(){

	}

	public void showAddLibraryMemberWondow() throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/AddLibrarymember.fxml"));

		Parent root1 = null;
		root1 = (Parent)fxmlLoader.load();

		Stage stage = new Stage();
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
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Add Library Member");
        stage.setScene(new Scene(root1));
        stage.show();
	}

	public void showDisplayLibraryMemberWondow() throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/DisplayLibraryMember.fxml"));

		Parent root1 = null;
		root1 = (Parent)fxmlLoader.load();

		Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Add Library Member");
        stage.setScene(new Scene(root1));
        stage.show();
	}

	public void showCheckoutRecordWindow() throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/CheckoutItemForm.fxml"));

		Parent root1 = null;
		root1 = (Parent)fxmlLoader.load();

		Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Checkout Book");
        stage.setScene(new Scene(root1));
        stage.show();
	}
}
