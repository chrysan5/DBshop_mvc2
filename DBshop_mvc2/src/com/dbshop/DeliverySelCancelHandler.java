package com.dbshop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeliverySelCancelHandler implements DbshopHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int count = Integer.parseInt(request.getParameter("count"));
		
		
		Dao dao = new Dao();
		int orderNum = 0;
		
		for(int i=1; i<count; i++){
			if(request.getParameter("sel"+i)!=null){
				orderNum = Integer.parseInt(request.getParameter("sel"+i)); 
				//üũ�ڽ� �̸��� name="sel<%=count %>"�̰�, �� �̸��� ���� value="${dto.orderNum}"�� orderNum�� �ȴ�.
				dao.deliveryCancel(orderNum); //"UPDATE orders SET result=1 WHERE orderNum=?";
			}
		}
		return "Delivery.do";
	}

}
