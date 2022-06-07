package com.dbshop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CartProcHandler implements DbshopHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int quantity = Integer.parseInt(request.getParameter("quantity")); //productRead���� �޾ƿ�
		int prodnum = Integer.parseInt(request.getParameter("productNum")); //productRead���� �޾ƿ�
		
		HttpSession session = request.getSession();
		UsersDto usersDto = (UsersDto)session.getAttribute("userInfo");
		
		if(usersDto.getId()==null){
			return "cartProc.jsp";
		}else{
			String id = usersDto.getId();
			Dao dao = new Dao();
			dao.insertCart(id, prodnum, quantity); //"INSERT INTO cart VALUES(NULL,?,?,?,NOW())";
			
			return "cartProc.jsp";
		}
	}

}
