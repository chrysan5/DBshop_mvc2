package com.dbshop;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainKindHandler implements DbshopHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String kind = request.getParameter("kind"); // 메인에 속한 top.jsp로부터 get방식으로 받음
		String insertText = request.getParameter("search"); // 메인에 속한 top.jsp의 form로부터 받음

		Dao dao = new Dao();
		Vector<ProductDto> v = dao.selectProductKind(Integer.parseInt(kind)); //"SELECT * FROM product WHERE kind=?";
		request.setAttribute("data", v);

		return "/main.jsp";
	}

}
