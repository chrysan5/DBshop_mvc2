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
				//체크박스 이름이 name="sel<%=count %>"이고, 이 이름의 값인 value="${dto.orderNum}"이 orderNum가 된다.
				dao.deliveryCancel(orderNum); //"UPDATE orders SET result=1 WHERE orderNum=?";
			}
		}
		return "Delivery.do";
	}

}
