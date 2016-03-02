package ui.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import business.BookDao;
import business.CheckoutRecordDao;
import business.LibraryMemberDao;
import dataaccess.storage.AuthorDto;
import dataaccess.storage.BookCopyDto;
import dataaccess.storage.CheckoutEntryDto;
import dataaccess.storage.LibraryMemberDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

public class CheckoutItemController {


	@FXML  TextField textFieldMemberId ,textFieldBookIsbn;
	@FXML  Label labelBookTitle,labelBookName, labelAuthor,labelCopies;
	@FXML  Button buttonCheckout;

	@FXML ListView< AuthorDto> listAuthor;
	@FXML ListView<BookCopyDto> listBookCopy;
	String bookFile;
	String libraryMemberFile;
	String checkoutRecordFile;
	private String memberId;
	private LibraryMemberDto libraryMemberDto;
	private String bookIsbn;
	
	 public CheckoutItemController(){
		 this.bookFile = "";
		 this.libraryMemberFile = "";
		 this.checkoutRecordFile = "";
	 }

	public void checkAvailabiltyForCheckout(){

		memberId = textFieldMemberId.getText();
		bookIsbn = textFieldBookIsbn.getText();

		System.out.println("check availabilty for checkout:memberId="+memberId);

		if(memberId.isEmpty() || bookIsbn.isEmpty()){
			showAlert("Required field is empty.");

		}else{
			/*
			 * 1. check if book is available and if any copy of books is available. If not then show information that none of the copies are available for checkout
			 * 2. If a copy of book is available for checkout then check if member id is valid and whether he has any book yet to return , if not then he can checkout book
			 *
			 * */

			
			
			

			BookDao bookDao = new BookDao(bookFile);
			LibraryMemberDao memberDao = new LibraryMemberDao(libraryMemberFile);
			
			if(!memberDao.doesUserExist(memberId)){
				showAlert("The requested member was not found in database.");
				return;
			}
			
			libraryMemberDto = memberDao.getLibraryMember(memberId);

			if(bookDao.isBookAvailableInLibrary(bookIsbn)){
				if(memberDao.canUserCheckoutBook(memberId)){

					showCheckoutFields();
					
					ObservableList<BookCopyDto> copiesAvailable = null;
					
					try {
						copiesAvailable = FXCollections.observableList(bookDao.getListOfCopiesAvailable(bookIsbn));
					} catch (Exception e) {
						e.printStackTrace();
					}
					if(copiesAvailable!=null && copiesAvailable.size()>0){
						// show list of copies available
						listBookCopy.setItems(copiesAvailable);

						listBookCopy.setCellFactory(new Callback<ListView<BookCopyDto>, ListCell<BookCopyDto>>(){

					            @Override
					            public ListCell<BookCopyDto> call(ListView<BookCopyDto> p) {

					                ListCell<BookCopyDto> cell = new ListCell<BookCopyDto>(){

					                    @Override
					                    protected void updateItem(BookCopyDto t, boolean bln) {
					                        super.updateItem(t, bln);
					                        if (t != null) {
					                            setText(t.getCopyNumber()+"");
					                        }
					                    }

					                };

					                return cell;
					            }
					        });


						ObservableList<AuthorDto> authors = getInitialAuthorData();

						listAuthor.setItems(authors);

						listAuthor.setCellFactory(new Callback<ListView<AuthorDto>, ListCell<AuthorDto>>(){

					            @Override
					            public ListCell<AuthorDto> call(ListView<AuthorDto> p) {

					                ListCell<AuthorDto> cell = new ListCell<AuthorDto>(){

					                    @Override
					                    protected void updateItem(AuthorDto t, boolean bln) {
					                        super.updateItem(t, bln);
					                        if (t != null) {
					                            setText(t.getFirstName()+" " + t.getLastName());
					                        }
					                    }

					                };

					                return cell;
					            }
					        });


					}else{
						showAlert("No copies of the book are available for checkout.");
					}

				}else{
					//TODO user can not checkout book coz he has some over due book
					//TODO show the list of over due books
				}
			}else{
				showAlert("Library does not have the book.");
			}

		}
	}
	
	public void checkOutBook(){
		BookCopyDto selectedCopy = listBookCopy.getSelectionModel().getSelectedItem();
		System.out.println("Selected copy UUID:"+selectedCopy.getCopyNumber());
		
		selectedCopy.setAvailable(false);
		selectedCopy.setCheckedBy(libraryMemberDto); 
		CheckoutEntryDto checkoutEntryDto = new CheckoutEntryDto(selectedCopy);
		
		CheckoutRecordDao  checkoutRecordDao = new CheckoutRecordDao(checkoutRecordFile, memberId);
		checkoutRecordDao.addCheckoutEntryForMember(checkoutEntryDto);
		
		new BookDao(bookFile).logCheckoutOfCopy(bookIsbn, selectedCopy.getCopyNumber());
		
		//mark the copy as unavailable, create a checkout entry and add it to checkout record of the member
		//update book record , checkout record
		
		
		
		
		
	}
	
	 private ObservableList getInitialTableData() {

	        List list = new ArrayList();
	        list.add(new BookCopyDto(UUID.randomUUID(), null));
	        list.add(new BookCopyDto(UUID.randomUUID(), null));
	        list.add(new BookCopyDto(UUID.randomUUID(), null));

	        ObservableList data = FXCollections.observableList(list);

	        return data;
	    }

	 private ObservableList getInitialAuthorData() {

	        List list = new ArrayList();
	        list.add(new AuthorDto("John","Harris","A great professor at MUM",null,null));
	        list.add(new AuthorDto("Renuka","Mohanraj","A great professor at MUM",null,null));
	        list.add(new AuthorDto("Paul","Corozza","A great professor at MUM",null,null));

	        ObservableList data = FXCollections.observableList(list);

	        return data;
	    }

	public void hideCheckoutFields(){
		labelBookTitle.setVisible(false);
		 labelBookName.setVisible(false);
		 labelAuthor.setVisible(false);
		 labelCopies.setVisible(false);
		 buttonCheckout.setVisible(false);
	}

	public void showCheckoutFields(){
		labelBookTitle.setVisible(true);
		 labelBookName.setVisible(true);
		 labelAuthor.setVisible(true);
		 labelCopies.setVisible(true);
		 buttonCheckout.setVisible(true);
		 listAuthor.setVisible(true);
		 listBookCopy.setVisible(true);
	}

	public void resetInputFields(){
		System.out.println("clear the fields");
		textFieldMemberId.clear();
		textFieldBookIsbn.clear();
		hideCheckoutFields();
	}

	public void showAlert(String message){

		Alert alert = new Alert(AlertType.INFORMATION, message, ButtonType.CANCEL);
		alert.show();
	}


}
