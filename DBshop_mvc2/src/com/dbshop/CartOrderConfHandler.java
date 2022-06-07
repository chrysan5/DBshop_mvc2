package com.dbshop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CartOrderConfHandler implements DbshopHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		UsersDto usersDto = (UsersDto)session.getAttribute("userInfo");
	    String id = usersDto.getId();
	    
		int count = Integer.parseInt(request.getParameter("count"));
		int cartNum = 0;
		int prodnum = 0;
		int quantity = 0;
		
		Dao dao = new Dao();
		
		for(int i=1; i<count; i++){
			if(request.getParameter("cartNum"+i)!=null){
				cartNum = Integer.parseInt(request.getParameter("cartNum"+i));
				prodnum = Integer.parseInt(request.getParameter("prodnum"+i));
				quantity = Integer.parseInt(request.getParameter("quantity"+i));
				
				dao.cartInsertOrder(id, prodnum, quantity); //"INSERT INTO orders VALUES (NULL,?,?,?,DEFAULT,now())";
				dao.selDeleteCart(cartNum); //"DELETE FROM cart WHERE cartNum=?";
			}
		}
		request.setAttribute("userData", usersDto);
		
		return "/orderConf.jsp";
	}

}
