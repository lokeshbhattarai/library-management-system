package business;

import java.util.List;

import dataaccess.DataReader;
import dataaccess.storage.CheckoutEntryDto;
import dataaccess.storage.CheckoutRecordDto;

public class CheckoutRecordDao {

	private DataReader dataReader;
	
	public CheckoutRecordDao(String filePath, String memberId){
		dataReader = new DataReader(filePath+"-"+memberId);
		
	}
	
	public void addCheckoutEntryForMember(CheckoutEntryDto checkoutEntryDto){
		try {
			List<CheckoutEntryDto> checkoutEntryDtos = (List<CheckoutEntryDto>)dataReader.read();
			checkoutEntryDtos.add(checkoutEntryDto);
			dataReader.write(checkoutEntryDtos);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
		
}
