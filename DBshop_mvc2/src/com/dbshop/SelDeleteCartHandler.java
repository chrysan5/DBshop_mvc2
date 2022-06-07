package com.dbshop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SelDeleteCartHandler implements DbshopHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//DeliverySelHandler, DeliverySelCancelHandler와 같은 원리이다 -어쨋든 이 핸들러 안쓰임(orderchk 만들다 맘)
		
		int count = Integer.parseInt(request.getParameter("count"));
		
		
		Dao dao = new Dao();
		int cartNum = 0;
		
		for(int i=1; i<count; i++){
			if(request.getParameter("buy"+i)!=null){
				cartNum = Integer.parseInt(request.getParameter("buy"+i));
				//cart.jsp에 보면, 체크박스 이름이 name="buy<%=count %>"이고, 이 이름의 값인 value="${dto.cartNum}"이 cartNum가 된다.
				dao.selDeleteCart(cartNum); //"DELETE FROM cart WHERE cartNum=?";
			}
		}
		return "Cart.do";
	}

}
