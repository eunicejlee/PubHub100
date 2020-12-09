//BookTag POJO (Plain Old Java Object)

package examples.pubhub.model;

public class BookTag {
	private String isbn13;
	private String tagName;
	
	//constructor
	public BookTag(String isbn, String tagName) {
		this.isbn13=isbn;
		this.tagName=tagName;
	}
	
	//default constructor
	public BookTag() {}

	public String getIsbn13() {
		return isbn13;
	}

	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
	//TestCase
	public String toString() {
		return "ISBN: " + isbn13 + " Tag: " + tagName;
	}

}
 