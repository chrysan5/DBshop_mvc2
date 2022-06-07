package com.dbshop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CartDeleteHandler implements DbshopHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int cartNum = Integer.parseInt(request.getParameter("cartNum")); //cart.jsp로부터 받음
		
		Dao dao = new Dao();
		dao.deleteCart(cartNum); //"DELETE FROM cart WHERE cartNum=?";
		
		return "Cart.do";
	}

}
