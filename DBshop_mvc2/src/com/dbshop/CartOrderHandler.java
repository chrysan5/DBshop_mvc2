package com.dbshop;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CartOrderHandler implements DbshopHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int count = Integer.parseInt(request.getParameter("count")); //cart.jsp�� form�κ��� �޾ƿ�
		
		
		Vector<ProductDto> v = new Vector<>();
		int productNum = 0;
		Dao dao = new Dao();
		int cartNum = 0; //�̰� �� ����?
		
		for(int i=1; i<count; i++){
			if(request.getParameter("buy"+i)!=null){
				productNum = Integer.parseInt(request.getParameter("productNum"+i));
				//üũ�ڽ� �̸�(name)�� productNum<%=count %>�̰�, �� �̸��� ��(value)�� ${dto.productNum}�� productNum�� �ȴ�.
				v.add(dao.cartProductOrder(productNum)); // "SELECT * FROM product WHERE productNum=?";
														 //ProductDto�� ���ϰ���. �ٵ� �̰� ���Ͱ��� ������(cartOrder���� �� ������,,)
			}
		}
		
		request.setAttribute("ProductData", v);
		
		return "cartOrder.jsp";
	}

}
