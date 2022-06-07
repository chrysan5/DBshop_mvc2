package com.dbshop;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardUpadteHandler implements DbshopHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int productNum = Integer.parseInt(request.getParameter("productNum"));
		
		Dao dao = new Dao();
		Vector<ProductDto> v = dao.ReadProduct(productNum);
		//결과가 벡터값인 이유는 아마도 파일 업로드기 때문에...
		
		request.setAttribute("BoardUpdateData", v);
		
		return "/boardUpdate.jsp";
	}

}
