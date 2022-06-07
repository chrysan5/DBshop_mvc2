package com.dbshop;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeliveryHandler implements DbshopHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Dao dao = new Dao();
		Vector<OrderDto> v = dao.selectOrder(); // "SELECT * FROM orders";
		Vector<ProductDto> v2 = dao.selectProduct(); //"SELECT * FROM product";

		request.setAttribute("productData", v2);
		request.setAttribute("orderData", v);

		return "deliveryPage.jsp";
	}

}
