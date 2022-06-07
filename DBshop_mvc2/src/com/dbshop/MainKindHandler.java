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

		String kind = request.getParameter("kind"); // ���ο� ���� top.jsp�κ��� get������� ����
		String insertText = request.getParameter("search"); // ���ο� ���� top.jsp�� form�κ��� ����

		Dao dao = new Dao();
		Vector<ProductDto> v = dao.selectProductKind(Integer.parseInt(kind)); //"SELECT * FROM product WHERE kind=?";
		request.setAttribute("data", v);

		return "/main.jsp";
	}

}
