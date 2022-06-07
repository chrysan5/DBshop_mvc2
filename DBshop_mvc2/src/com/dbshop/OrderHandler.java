package com.dbshop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderHandler implements DbshopHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//productReadHandler와 같다
		
		int productNum = Integer.parseInt(request.getParameter("productNum")); //productRead로부터 받음
		
		Dao dao = new Dao();
		ProductDto productDto = dao.ReadProduct2(productNum); //"SELECT * FROM product WHERE productNum=?";
		
		request.setAttribute("productData", productDto);
		
		return "order.jsp";
	}

}
