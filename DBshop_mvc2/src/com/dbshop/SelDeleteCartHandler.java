package com.dbshop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SelDeleteCartHandler implements DbshopHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//DeliverySelHandler, DeliverySelCancelHandler�� ���� �����̴� -��¶�� �� �ڵ鷯 �Ⱦ���(orderchk ����� ��)
		
		int count = Integer.parseInt(request.getParameter("count"));
		
		
		Dao dao = new Dao();
		int cartNum = 0;
		
		for(int i=1; i<count; i++){
			if(request.getParameter("buy"+i)!=null){
				cartNum = Integer.parseInt(request.getParameter("buy"+i));
				//cart.jsp�� ����, üũ�ڽ� �̸��� name="buy<%=count %>"�̰�, �� �̸��� ���� value="${dto.cartNum}"�� cartNum�� �ȴ�.
				dao.selDeleteCart(cartNum); //"DELETE FROM cart WHERE cartNum=?";
			}
		}
		return "Cart.do";
	}

}
