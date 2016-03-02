package business;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dataaccess.DataReader;
import dataaccess.storage.CheckoutEntryDto;
import dataaccess.storage.CheckoutRecordDto;
import dataaccess.storage.LibraryMemberDto;

public class CheckoutRecordDao {

	private DataReader dataReader;
	
	public CheckoutRecordDao(String filePath){
		dataReader = new DataReader(filePath);
		
	}
	
	public void addCheckoutEntryForMember(CheckoutEntryDto checkoutEntryDto, LibraryMemberDto libraryMember){
		
		List<CheckoutRecordDto> checkoutRecordList = getAllCheckoutRecords();
		Iterator<CheckoutRecordDto> itrCheckoutRecord = checkoutRecordList.iterator();
		
		if(itrCheckoutRecord.hasNext()){
			while(itrCheckoutRecord.hasNext()){
				
				CheckoutRecordDto checkoutRecordDto = itrCheckoutRecord.next();
				
				if(checkoutRecordDto.getLibraryMemberDto().equals(libraryMember)){
					//update the record
					checkoutRecordDto.getCheckoutEntryDtos().add(checkoutEntryDto);
					dataReader.write(checkoutRecordList);
					break;
				}
			}
		}else{
			//create new record and save it
			CheckoutRecordDto checkoutRecord= new CheckoutRecordDto(libraryMember);
			List<CheckoutEntryDto> checkoutEntryList = new ArrayList<CheckoutEntryDto>();
			checkoutEntryList.add(checkoutEntryDto);
			
			checkoutRecord.setCheckoutEntryDtos(checkoutEntryList);
			
			List<CheckoutRecordDto> checkoutRecList = new ArrayList<>();
			checkoutRecList.add(checkoutRecord);
			dataReader.write(checkoutRecList);
		}
		
		/*try {
			List<CheckoutRecordDto> checkoutRecList = new ArrayList<>();
			checkoutRecList.add(checkout)
			checkoutEntryDtos.add(checkoutEntryDto);
			dataReader.write(checkoutEntryDtos);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}
	
	
	public List<CheckoutRecordDto> getAllCheckoutRecords(){
		try {
			return (List<CheckoutRecordDto>)dataReader.read();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<CheckoutRecordDto>();
		}
		
	}
	
		
}
