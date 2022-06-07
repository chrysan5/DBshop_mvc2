package com.dbshop;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class QnaDeleteHandler implements DbshopHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		int qnaNum = Integer.parseInt(request.getParameter("qnaNum")); //qnaRead.jsp로부터 get 방식으로 받음
		String qnaId = request.getParameter("id"); //qnaRead.jsp로부터  get 방식으로 받음
		
		

	    HttpSession session = request.getSession();
	    UsersDto usersDto = (UsersDto)session.getAttribute("userInfo");
	    String myId = usersDto.getId();
	     
	      
	      
	    if(myId.equals(qnaId) || usersDto.getId().equals("1")) { 
	    	Dao dao = new Dao();
			dao.deleteQnaData(qnaNum); //"DELETE from qna where qnaNum=?";
	    	return "/Qna.do";
	    }else{
		    return "qnaDeleteNot.jsp";
		}
	    	  
	 }
	
}
