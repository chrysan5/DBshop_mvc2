package com.dbshop;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainHandler implements DbshopHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		//String kind = request.getParameter("kind"); 
		//메인에 속한 top.jsp의 form로부터(select option으로) 받음. 이걸 따로 MainKindHandler로 빼서 수정함
		//즉, select option에서 항목 클릭하면 이동하는걸로 수정했다.
		String insertText = request.getParameter("search"); 
		//search창에 뭔가 입력했을 경우 메인에 속한 top.jsp의 form로부터 받음
		
		
		if(insertText==null || insertText.equals("")){ //---아무값도 없을경우, 다가져옴
			Dao dao = new Dao();
			Vector<ProductDto> v = dao.selectProduct(); //"SELECT * FROM product";
			request.setAttribute("data", v);	
		}else{ 
			Dao dao = new Dao();
			Vector<ProductDto> v = dao.searchProduct(insertText); //"SELECT * FROM product WHERE NAME LIKE ?";
			request.setAttribute("data", v);
		}
		return "/main.jsp";
	}
}
