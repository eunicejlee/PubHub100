package examples.pubhub.dao;

import java.util.List;

import examples.pubhub.model.Book;
import examples.pubhub.model.BookTag;

//Interface for our Data Access Object to handle database queries related to Book_Tags.

public interface BookTagDAO {
	public List<BookTag> getAllBookTags(String isbn); //retrieve tags by given isbn.
	public List<Book> getBooksByTag(String tagName); //retrieve books by tag.
	
	//add or delete tag by referencing either isbn or tag.
	public boolean addTag(BookTag bookTag); 
	public boolean deleteTag(BookTag bookTag);

}
