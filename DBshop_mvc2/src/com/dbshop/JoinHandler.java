package com.dbshop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JoinHandler implements DbshopHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String zipcode = request.getParameter("zipcode");
		String address = request.getParameter("address");
		String address2 = request.getParameter("address2");
		String phone = request.getParameter("phone");
		
		
		UsersDto dto = new UsersDto(id, pw, name, email, zipcode, address, address2, phone);
		//request.setAttribute("data", dto); //이건 누가 잘못만든듯
		
		Dao dao = new Dao();
		int result = dao.insertUser(dto);
		//int result 생략하고 dao.insertUser(dto); 라고 써도됨
		
		return "Main.do";
		
		
	}

}
