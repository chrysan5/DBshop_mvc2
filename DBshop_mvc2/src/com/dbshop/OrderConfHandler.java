package com.dbshop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OrderConfHandler implements DbshopHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		HttpSession session = request.getSession();
		UsersDto usersDto = (UsersDto)session.getAttribute("userInfo");
	    String id = usersDto.getId();

	    
		int prodnum = Integer.parseInt(request.getParameter("prodnum")); //order.jsp에서 form으로 받음
																		
		OrderDto orderDto = new OrderDto();
		orderDto.setId(id);
		orderDto.setProdnum(prodnum);
		
		Dao dao = new Dao();
		int result = dao.insertOrder(orderDto); //"INSERT INTO orders VALUES (NULL,?,?,?,DEFAULT,now())";
		
		
		String goPage="";
		if(result>0){
			OrderDto odto = dao.readOrder(orderDto.getOrderNum()); //"SELECT * FROM orders WHERE orderNum=?";
			request.setAttribute("orderData", odto);
			request.setAttribute("userData", usersDto);
			
			goPage = "/orderConf.jsp";
		}else{
			goPage = "/orderErr.jsp";
		}
		
		return goPage;
	}
}
