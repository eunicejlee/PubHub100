package examples.pubhub.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.dao.*;
import examples.pubhub.model.*;
import examples.pubhub.utilities.DAOUtilities;

//This servlet will take you to main search book by tag module

@WebServlet("/SearchBook")
public class SearchBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tagName=request.getParameter("tagName");
		
		BookTagDAO dao=DAOUtilities.getBookTagDAO();
		List<Book> bookList=dao.getBooksByTag(tagName);
		
		request.getSession().setAttribute("books", bookList);
		request.getRequestDispatcher("searchResult.jsp").forward(request, response);
	}
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
        processRequest(request, response); 
    } 
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
        processRequest(request, response); 
    } 
} 