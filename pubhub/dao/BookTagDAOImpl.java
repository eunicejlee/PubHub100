package examples.pubhub.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import examples.pubhub.model.*;
import examples.pubhub.utilities.DAOUtilities;

//Implementation for the BookTagDAO

public class BookTagDAOImpl implements BookTagDAO {
	
	Connection connection = null;	// Our connection to the database
	PreparedStatement stmt = null;	// We use prepared statements to help protect against SQL injection
	
	/*------------------------------------------------------------------------------------------------*/
	
	@Override
	public List<BookTag> getAllBookTags(String isbn) {
		
		List<BookTag> booktags = new ArrayList<>();
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM Book_Tags WHERE isbn_13=?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, isbn);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				BookTag bookTag = new BookTag();
				
				bookTag.setIsbn13(rs.getString("isbn_13"));
				bookTag.setTagName(rs.getString("tag_name"));
				
				booktags.add(bookTag);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeResources();
			}
			
			return booktags;
		}
		
	/*------------------------------------------------------------------------------------------------*/
	
	@Override
	public List<Book> getBooksByTag(String tagName) {
		List<Book> books=new ArrayList<>();
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM Books JOIN Book_tags ON Books.isbn_13=Book_Tags.isbn_13 WHERE tag_name=?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, tagName);
			
			ResultSet rs=stmt.executeQuery();
			
			while (rs.next()) {
				Book book=new Book();
				
				book.setIsbn13(rs.getString("isbn_13"));
				book.setAuthor(rs.getString("author"));
				book.setTitle(rs.getString("title"));
				book.setPublishDate(rs.getDate("publish_date").toLocalDate());
				book.setPrice(rs.getDouble("price"));
				book.setContent(rs.getBytes("content"));
				
				books.add(book);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		return books;
	}
	
	/*------------------------------------------------------------------------------------------------*/
	
	@Override
	public boolean addTag(BookTag bookTag) {
		
		try {
			connection=DAOUtilities.getConnection();
			String sql="INSERT INTO Book_Tags VALUES (?,?)";
			stmt=connection.prepareStatement(sql);
			
			stmt.setString(1, bookTag.getIsbn13());
			stmt.setString(2, bookTag.getTagName());
			
			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}
	
	/*------------------------------------------------------------------------------------------------*/
	
	@Override
	public boolean deleteTag(BookTag bookTag) {
		try {
			connection=DAOUtilities.getConnection();
			String sql="DELETE FROM Book_Tags WHERE isbn_13=? AND tag_name=?";
			stmt=connection.prepareStatement(sql);
			
			stmt.setString(1, bookTag.getIsbn13());
			stmt.setString(2, bookTag.getTagName());
			
			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}
			
	/*------------------------------------------------------------------------------------------------*/

	// Closing all resources is important, to prevent memory leaks. 
	// Ideally, you really want to close them in the reverse-order you open them
	private void closeResources() {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			System.out.println("Could not close statement!");
			e.printStackTrace();
		}
		
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			System.out.println("Could not close connection!");
			e.printStackTrace();
		}
	}
	
}

