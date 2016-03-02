package ui.controller;



import java.util.ArrayList;
import java.util.List;

import dataaccess.storage.BookDto;
import business.BookDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AddBookController {
	@FXML TextField title;
	@FXML TextField id;
	@FXML TextField ISBN;
	@FXML TextField CopyNumber;
	@FXML TableView<BookDto> table;
	@FXML TableColumn c0;
	@FXML TableColumn c1;
	@FXML TableColumn c2;
	@FXML TableColumn c3;
	private BookDao book;
	private List<BookDto>books;
	ObservableList<BookDto> bookData=null;
	public AddBookController(){
		book = new BookDao("C:\\Users\\Example\\Desktop\\Oriyon\\MppProject\\src\\docs\\book.txt");
		//readListBook(books);
		Object data;
		try {
			data = book.getBookList();

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
		id.setText((books.size()+1)+"");
		BookDto booka = new BookDto(null, title.getText(), ISBN.getText(),CopyNumber.getText());
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void readListBook(){

		//ObservableList bookData;
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
		c2.setCellValueFactory(new PropertyValueFactory<BookDto, String>("ISBN"));
		c3.setCellValueFactory(new PropertyValueFactory<BookDto, String>("copyNumber"));
			/*for (BookDto o : books) {
				System.out.println(o.getId());
			}
			*/

	}

}
