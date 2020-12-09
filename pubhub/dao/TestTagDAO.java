package examples.pubhub.dao;

import java.util.List;

import examples.pubhub.model.*;

public class TestTagDAO {

	public static void main(String[] args) {
		BookTagDAO dao = new BookTagDAOImpl();
		List<BookTag> list=dao.getAllBookTags("1111111111111");
		
		for (int i=0; i<list.size(); i++) {
			BookTag bt=list.get(i);
			System.out.println(bt.toString());
		}

	}

}
