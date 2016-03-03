package ui.controller;

import java.util.List;

import dataaccess.storage.AddressDto;
import dataaccess.storage.AuthorDto;
import dataaccess.storage.BookDto;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddAuthorInfoController {
	@FXML TextField firstname;
	@FXML TextField lastname;
	@FXML TextField phone;
	@FXML TextArea shortBio;
	@FXML TextField street;
	@FXML TextField city;
	@FXML TextField state;
	@FXML TextField zipcode;
	@FXML Button save;
	@FXML Button cancel;
	
	List<AuthorDto> authors;
	public AddAuthorInfoController(){
		
	}
	
	public void setAuthor(List<AuthorDto> authors){
		this.authors = authors;
	}
	
	public void addAuthor(){
		AddressDto address = new AddressDto(this.street.getText().trim(),
				this.city.getText().trim(),
				this.state.getText().trim(),
				this.zipcode.getText().trim());
		authors.add(new AuthorDto(firstname.getText(), lastname.getText(), shortBio.getText(), address, phone.getText()));
	}
	public void cancel(){
		Stage stage = (Stage) cancel.getScene().getWindow();
	    stage.close();
	}
}
