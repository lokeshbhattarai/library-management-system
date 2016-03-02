package ui.controller;

import java.util.List;

import business.BookDao;
import dataaccess.FilePath;
import dataaccess.storage.AddressDto;
import dataaccess.storage.AuthorDto;
import dataaccess.storage.BookDto;
import dataaccess.storage.LibraryMemberDto;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

public class EditBookController {
	@FXML TableView<BookDto> table;
	@FXML TextField title;
	@FXML TextField ISBN;
	@FXML TextField copyNumber;
	@FXML TextField firstName;
	@FXML TextField lastName;
	@FXML TableColumn t;
	@FXML TableColumn i;
	@FXML TableColumn n;
	@FXML TableColumn a;
	@FXML TableColumn action;
	private BookDao book;
	private List<BookDto>books;
	static List<AuthorDto> authordto;
	static AddressDto address;
	List<BookDto> bookData=null;
	public EditBookController(){
		
	}
	
	@SuppressWarnings("unchecked")
	public void searchBook() throws Exception{
		book = new BookDao(FilePath.BOOK_RECORD);
		books = book.getBookList();
		for (BookDto book : books) {
			for (AuthorDto author : book.getAuthors()) {
				System.out.println("book: " + book.getTitle()+", author: "+ author.getFirstName());
			}
		}
		
		for (BookDto authorDto2 : books) {
			authordto = authorDto2.getAuthors();
		}
		
		
		ObservableList<BookDto> bookData = null;
		bookData =  FXCollections.observableArrayList();
		for (BookDto bookDto : books) {
			bookData.add(bookDto);
		}
		this.t.setCellValueFactory(new Callback<CellDataFeatures<BookDto, String>,
                ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<BookDto, String> data){
						StringProperty p = new SimpleStringProperty(data.getValue().getTitle());
						return p;
					}
					});
		this.i.setCellValueFactory(new Callback<CellDataFeatures<BookDto, String>,
                ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<BookDto, String> data){
						StringProperty p = new SimpleStringProperty(data.getValue().getIsbn());
						return p;
					}
					});
		this.n.setCellValueFactory(new Callback<CellDataFeatures<BookDto, String>,
                ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<BookDto, String> data){
						StringProperty p = new SimpleStringProperty(data.getValue().getCopyNumber());
						return p;
					}
					});
		this.a.setCellValueFactory(new Callback<CellDataFeatures<BookDto, String>,
                ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<BookDto, String> data){
						StringProperty p = new SimpleStringProperty(authordto.iterator().next().getFirstName());
						return p;
					}
					});
		Callback<TableColumn<BookDto, String>, TableCell<BookDto, String>> cellFactory = //
                new Callback<TableColumn<BookDto, String>, TableCell<BookDto, String>>()
                {
                    @Override
                    public TableCell call( final TableColumn<BookDto, String> param )
                    {
                        final TableCell<BookDto, String> cell = new TableCell<BookDto, String>()
                        {

                            final Button btn = new Button( "Edit" );

                            @Override
                            public void updateItem( String item, boolean empty )
                            {
                                super.updateItem( item, empty );
                                if ( empty )
                                {
                                    setGraphic( null );
                                    setText( null );
                                }
                                else
                                {
                                    btn.setOnAction( ( ActionEvent event ) ->
                                            {
                                            	BookDto member = getTableView().getItems().get( getIndex() );
                                                try {
													//switchToEditMode(member);
												} catch (Exception e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
                                    } );
                                    setGraphic( btn );
                                    setText( null );
                                }
                            }
                        };
                        return cell;
                    }
                };
        //this.action.setCellFactory( cellFactory );
		this.table.setItems(bookData);
	}
}
