package com.dbshop;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CartHandler implements DbshopHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
	    UsersDto usersDto = (UsersDto)session.getAttribute("userInfo");
	      
	    Dao dao = new Dao();
		Vector<CartDto> v = dao.selectCart(usersDto.getId()); //"SELECT * FROM cart where id=?";
		Vector<ProductDto> v2 = dao.selectProduct(); //"SELECT * FROM product";
		
		request.setAttribute("productData", v2);
		request.setAttribute("cartData", v);
		
		return "/cart.jsp";
	}

}
