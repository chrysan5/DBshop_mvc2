package com.dbshop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductReadHandler implements DbshopHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//OrderHandler�� ����
		
		int productNum = Integer.parseInt(request.getParameter("productNum")); //main.jsp���� get������� ����
		
		Dao dao = new Dao();
		ProductDto productDto = dao.ReadProduct2(productNum); // "SELECT * FROM product WHERE productNum=?";
		//boardRead�� boardUpdate�ʹ� �ٸ��� dao.ReadProduct�� ��������. 
		//���� ���ε�� ���������� ����ȰŸ� ����� vector�� ��ߵǳ�? �𸣰���

		
		request.setAttribute("productData", productDto);
	
		return "/productRead.jsp";
		
	}

}
