package ui.controller;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dataaccess.storage.AddressDto;
import dataaccess.storage.AuthorDto;
import dataaccess.storage.BookDto;
import business.BookDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AddBookController {
	@FXML Button btnAuthor;
	@FXML Button save;
	@FXML TextField title;
	@FXML TextField id;
	@FXML TextField ISBN;
	@FXML TextField CopyNumber;
	@FXML TableView<BookDto> table;
	@FXML TableColumn c0;
	@FXML TableColumn c1;
	@FXML TableColumn c2;
	@FXML TableColumn c3;
	@FXML TableColumn c4;
	@FXML TextField firstname;
	@FXML TextField lastname;
	@FXML TextField phone;
	@FXML TextArea shortBio;
	@FXML TextField street;
	@FXML TextField city;
	@FXML TextField state;
	@FXML TextField zipcode;
	
	
	private BookDao book;
	private List<BookDto>books;
	static List<AuthorDto> authordto;
	static AddressDto address;
	List<BookDto> bookData=null;
	
	public AddBookController(){
		book = new BookDao("C:\\Users\\Example\\Desktop\\Oriyon\\MppProject\\src\\docs\\book.txt");
		authordto = new ArrayList<AuthorDto>();
		//readListBook(books);
		Object data;
		try {
			
			data = book.getBookList();
			//System.out.println("test1");
		if(data == null){
			books = new ArrayList<BookDto>();
		}
		else{
			books = book.getBookList();
			//readListBook();
			//System.out.println(books.size());
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void validate(){
		if((title.getText() == null || title.getText().length() == 0)||(ISBN.getText() == null || ISBN.getText().length() == 0)||(CopyNumber.getText() == null || CopyNumber.getText().length() == 0)){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error occurred.");
			alert.setHeaderText(null);
			alert.setContentText("please provide appropriate input");
			alert.showAndWait();
		}
		else{
			addBook();
		}
	}

	public void addBook(){
		try{
		//id.setText((books.size()+1)+"");
			//System.out.println(address.getState()+" "+address.getCity());
			for (AuthorDto authorDto2 : authordto) {
				System.out.println("author list");
				System.out.println(authorDto2.getFirstName()+" "+authorDto2.getLastName()+" "+authorDto2.getAddress());
			}
		BookDto booka = new BookDto(authordto, title.getText(), ISBN.getText(),CopyNumber.getText());
		books.add(booka);
		book.addBook(books);
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText("New book has been added successfully.");

		alert.showAndWait();
		}catch(Exception ex){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error occurred.");
			alert.setHeaderText(null);
			alert.setContentText(ex.getMessage());

			alert.showAndWait();
		}
		readListBook();
		
	}
	public void addAuthor(){
		address = new AddressDto(this.street.getText().trim(),
				this.city.getText().trim(),
				this.state.getText().trim(),
				this.zipcode.getText().trim());
		authordto.add(new AuthorDto(firstname.getText(), lastname.getText(), shortBio.getText(), address, phone.getText()));
		
		// add author info close	
		Stage stage = (Stage) save.getScene().getWindow();
	    stage.close();	
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void readListBook(){
		ObservableList bookData = null;
		try {
			 books = book.getBookList();
			 System.out.println(books.size());
			 bookData = FXCollections.observableArrayList(books);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		table.setItems(bookData);
		c0.setCellValueFactory(new PropertyValueFactory<BookDto, String>("id"));
		c1.setCellValueFactory(new PropertyValueFactory<BookDto, String>("title"));
		c2.setCellValueFactory(new PropertyValueFactory<BookDto, String>("isbn"));
		c3.setCellValueFactory(new PropertyValueFactory<BookDto, String>("copyNumber"));
		c4.setCellValueFactory(new PropertyValueFactory<AuthorDto, String>("authors"));
			//for (BookDto o : books) {
				//System.out.println(o.);
			//}
			

	}
	public void showAddBookAuthorWondow() throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/AddAuthorInformation.fxml"));
		System.out.println("no error");
		Parent root1 = null;
		root1 = fxmlLoader.load();
		System.out.println(root1);
		Stage stage = new Stage();
		//Scene scene = new Scene(root1);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Add Library Member");
        stage.setScene(new Scene(root1));
        stage.show();
	}

}
