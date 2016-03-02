package business;

import java.util.List;

import dataaccess.DataReader;
import dataaccess.storage.BookCopyDto;
import dataaccess.storage.BookDto;

public class BookDao {

	private DataReader reader;

	public BookDao(String path) {
		this.reader = new DataReader(path);
	}

	//check if book with given isbn is in library
	public boolean isBookAvailableInLibrary(){
		return true;
	}

	public List<BookCopyDto> getListOfCopiesAvailable() throws Exception{
		reader.read();
		return null;
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
