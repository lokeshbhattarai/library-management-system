package business;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import dataaccess.DataReader;
import dataaccess.storage.BookCopyDto;
import dataaccess.storage.BookDto;

public class BookDao {

	private DataReader reader;

	public BookDao(String path) {
		this.reader = new DataReader(path);
	}

	//check if book with given isbn is in library
	public boolean isBookAvailableInLibrary(String bookIsbn){
		List<BookDto> bookList;
		try {
			bookList = (List<BookDto>)reader.read();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		Iterator<BookDto> itr = bookList.iterator();
		boolean caseHit = false;;
		
		while(itr.hasNext()){
			BookDto book = itr.next();
			if(book.getIsbn().equals(bookIsbn)){
				caseHit = true;
				break;
			}
			
		}
		return caseHit;
	}

	public List<BookCopyDto> getListOfCopiesAvailable(String bookIsbn) throws Exception{
		List<BookDto> bookList = (List<BookDto>)reader.read();
		Iterator<BookDto> itr = bookList.iterator();
		BookDto bookRequested = null;
		
		while(itr.hasNext()){
			BookDto book = itr.next();
			if(book.getIsbn().equals(bookIsbn)){
				bookRequested = book;
				break;
			}
			
		}
		
		if(bookRequested!=null){
			Iterator<BookCopyDto> itrBookCopy = bookRequested.getBookCopies().iterator();
			List<BookCopyDto> bookCopyList = new ArrayList<>();
			
			while (itrBookCopy.hasNext()) {
				BookCopyDto bookCopyDto = (BookCopyDto) itrBookCopy.next();
				
				if(bookCopyDto.isAvailable()){
					bookCopyList.add(bookCopyDto);
				}
				
			}
			
			return bookCopyList;
			
		}else{
			return new ArrayList<BookCopyDto>();
		}
		
	}
	
	public void logCheckoutOfCopy(String bookIsbn, UUID copyNumber){
		//TODO
	}

	public void addBook(List<BookDto> book){
		reader.write(book);
		//System.out.println(book.get(0));
	}

	@SuppressWarnings("unchecked")
	public List<BookDto> getBookList() throws Exception{
		return (List<BookDto>)reader.read();
	}

	public BookDto searchByTitle(String title){
		List<BookDto> allbook= null;
		BookDto bookdto=null;
		try {
			allbook = (List<BookDto>)reader.read();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (BookDto bookDto : allbook) {
			if(bookDto.getTitle()==title)
				bookdto=bookDto;
		}
		return bookdto;
	}
}
