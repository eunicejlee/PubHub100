package examples.pubhub.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.dao.BookTagDAO;
import examples.pubhub.model.BookTag;
import examples.pubhub.utilities.DAOUtilities;

//Servlet implementation class DeleteTagServlet
@WebServlet("/DeleteTag")
public class DeleteTagServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isSuccess=false;
		String isbn13 = request.getParameter("isbn13");
		//String tagName=request.getParameter("tagName");
		BookTag bookTag = new BookTag();
		
		bookTag.setIsbn13(isbn13);
		bookTag.setTagName(request.getParameter("tagName"));
		
		BookTagDAO dao = DAOUtilities.getBookTagDAO();
		isSuccess=dao.deleteTag(bookTag);
		
		if(isSuccess){
			request.getSession().setAttribute("message", "Book Tag successfully removed");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("ViewBookDetails?isbn13=" + isbn13);
		} else {
			request.getSession().setAttribute("message", "There was a problem updating this tag");
			request.getSession().setAttribute("messageClass", "alert-danger");
			request.getRequestDispatcher("bookDetails.jsp").forward(request, response);
		}
	}
}
		
