package com.dbshop;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainHandler implements DbshopHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		//String kind = request.getParameter("kind"); 
		//���ο� ���� top.jsp�� form�κ���(select option����) ����. �̰� ���� MainKindHandler�� ���� ������
		//��, select option���� �׸� Ŭ���ϸ� �̵��ϴ°ɷ� �����ߴ�.
		String insertText = request.getParameter("search"); 
		//searchâ�� ���� �Է����� ��� ���ο� ���� top.jsp�� form�κ��� ����
		
		
		if(insertText==null || insertText.equals("")){ //---�ƹ����� �������, �ٰ�����
			Dao dao = new Dao();
			Vector<ProductDto> v = dao.selectProduct(); //"SELECT * FROM product";
			request.setAttribute("data", v);	
		}else{ 
			Dao dao = new Dao();
			Vector<ProductDto> v = dao.searchProduct(insertText); //"SELECT * FROM product WHERE NAME LIKE ?";
			request.setAttribute("data", v);
		}
		return "/main.jsp";
	}
}
