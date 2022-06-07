package com.dbshop;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

public class OrderchkHandler implements DbshopHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		UsersDto usersDto = (UsersDto) session.getAttribute("userInfo");

		Dao dao = new Dao();
		Vector<OrderDto> v = dao.selectOrder(usersDto.getId()); // "SELECT * FROM orders where id=?";
		Vector<ProductDto> v2 = dao.selectProduct(); //"SELECT * FROM product";
		
		request.setAttribute("productData", v2);
		request.setAttribute("orderData", v);

		return "orderchk.jsp";
	}

}
