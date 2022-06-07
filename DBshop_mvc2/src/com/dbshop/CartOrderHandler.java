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
		
		int count = Integer.parseInt(request.getParameter("count")); //cart.jsp의 form로부터 받아옴
		
		
		Vector<ProductDto> v = new Vector<>();
		int productNum = 0;
		Dao dao = new Dao();
		int cartNum = 0; //이건 왜 잇지?
		
		for(int i=1; i<count; i++){
			if(request.getParameter("buy"+i)!=null){
				productNum = Integer.parseInt(request.getParameter("productNum"+i));
				//체크박스 이름(name)이 productNum<%=count %>이고, 이 이름의 값(value)인 ${dto.productNum}이 productNum가 된다.
				v.add(dao.cartProductOrder(productNum)); // "SELECT * FROM product WHERE productNum=?";
														 //ProductDto가 리턴값임. 근데 이걸 벡터값에 포장함(cartOrder에서 좀 쓰려고,,)
			}
		}
		
		request.setAttribute("ProductData", v);
		
		return "cartOrder.jsp";
	}

}
