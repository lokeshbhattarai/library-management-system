package dataaccess.storage;

import java.util.List;

public class CheckoutRecordDto {
	private List<CheckoutEntryDto> checkoutEntryDtos;
	private List<FineDto> fines;
	
	public List<CheckoutEntryDto> getCheckoutEntryDtos() {
		return checkoutEntryDtos;
	}
	public void setCheckoutEntryDtos(List<CheckoutEntryDto> checkoutEntryDtos) {
		this.checkoutEntryDtos = checkoutEntryDtos;
	}
	public List<FineDto> getFines() {
		return fines;
	}
	public void setFines(List<FineDto> fines) {
		this.fines = fines;
	}
	
	
	
}
