package com.dbshop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CartClearHandler implements DbshopHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
	    UsersDto usersDto = (UsersDto)session.getAttribute("userInfo");
		
		Dao dao = new Dao();
		dao.ClearCart(usersDto.getId()); //"DELETE FROM cart WHERE id=?";
		
		return "Cart.do";
	}

}
