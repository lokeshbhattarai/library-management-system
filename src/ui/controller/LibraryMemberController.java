package ui.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import business.LibraryMemberDao;
import dataaccess.storage.AddressDto;
import dataaccess.storage.LibraryMemberDto;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LibraryMemberController {
	@FXML TextField firstName;
	@FXML TextField lastName;
	@FXML TextField phoneNo;
	@FXML TextField state;
	@FXML TextField city;
	@FXML TextField street;
	@FXML TextField zipCode;

	LibraryMemberDao libraryMembeRepo;
	List<LibraryMemberDto> members;

	boolean isBeingEdited = false;
	LibraryMemberDto memberToEdit = null;
	DisplayLibraryMemberController parent = null;

	public LibraryMemberController() throws Exception{
		libraryMembeRepo = new LibraryMemberDao("c:\\data\\LibraryMember.txt");

		Object data = libraryMembeRepo.getMemberList();
		if(data == null){
			members = new ArrayList<LibraryMemberDto>();
		}
		else{
			members = libraryMembeRepo.getMemberList();
		}
		//LibraryMemberDto object = (LibraryMemberDto)libraryMember.getMemberList();
	}

	public void setEditMode(LibraryMemberDto member, DisplayLibraryMemberController parent){
		isBeingEdited = true;
		this.parent = parent;

		this.memberToEdit = member;
		this.firstName.setText(member.getFirstName());
		this.lastName.setText(member.getLastName());
		this.phoneNo.setText(member.getPhoneNo());
		this.state.setText(member.getAddress().getState());
		this.city.setText(member.getAddress().getCity());
		this.street.setText(member.getAddress().getStreet());
		this.zipCode.setText(member.getAddress().getZipCode());
	}

	public void addLibraryMember(){
		try{
			if(!isBeingEdited){
				add();
			}
			else{
				edit();
			}
		}
		catch(Exception ex){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error occurred.");
			alert.setHeaderText(null);
			alert.setContentText(ex.getMessage());

			alert.showAndWait();
		}
	}

	public void add(){
		AddressDto address = new AddressDto(this.street.getText().trim(),
				this.city.getText().trim(),
				this.state.getText().trim(),
				this.zipCode.getText().trim());

		LibraryMemberDto member = new LibraryMemberDto(this.firstName.getText().trim(),
									this.lastName.getText().trim(),
									address,
									this.phoneNo.getText().trim());

		members.add(member);
		libraryMembeRepo.addMember(members);

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText("New library member has been added successfully.");

		alert.showAndWait();
	}

	public void edit() throws Exception{
		for (LibraryMemberDto libraryMemberDto : this.members) {
			//System.out.println(libraryMemberDto.getMemberId().toString() + " : " + memberToEdit.getMemberId().toString());
			if(libraryMemberDto.equals(memberToEdit)){

				libraryMemberDto.setFirstName(this.firstName.getText());
				libraryMemberDto.setLastName(this.lastName.getText());
				libraryMemberDto.setPhoneNo(this.phoneNo.getText());
				libraryMemberDto.getAddress().setState(this.state.getText());
				libraryMemberDto.getAddress().setCity(this.city.getText());
				libraryMemberDto.getAddress().setStreet(this.street.getText());
				libraryMemberDto.getAddress().setZipCode(this.zipCode.getText());

				libraryMembeRepo.addMember(members);
				parent.SearchMembers();

				Stage stage = (Stage)this.firstName.getScene().getWindow();
			    stage.close();

				/*Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Library member has been edited successfully.");

				alert.showAndWait();*/

				break;
			}
		}
	}
}
