package com.dbshop;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

public class CheckIdAjax{ 
	public CheckIdAjax(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {		
		
		
		String id = request.getParameter("id"); //join.jsp���� ����
		
		Dao dao = new Dao();
		JSONObject rtn = dao.CheckDataJSon(id);  
		//"select count(idx) from users where id=?";�� ��� ���̵������ִ� rtn����

		response.setCharacterEncoding("utf-8");
		response.getWriter().print(rtn.toString());	
	}
}

