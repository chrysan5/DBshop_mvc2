package com.dbshop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductReadHandler implements DbshopHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//OrderHandler와 같다
		
		int productNum = Integer.parseInt(request.getParameter("productNum")); //main.jsp에서 get방식으로 받음
		
		Dao dao = new Dao();
		ProductDto productDto = dao.ReadProduct2(productNum); // "SELECT * FROM product WHERE productNum=?";
		//boardRead와 boardUpdate와는 다르게 dao.ReadProduct를 하지않음. 
		//파일 업로드와 직접적으로 관계된거만 결과를 vector로 써야되나? 모르겟음

		
		request.setAttribute("productData", productDto);
	
		return "/productRead.jsp";
		
	}

}
