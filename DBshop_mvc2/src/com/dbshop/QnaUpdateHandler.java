package com.dbshop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class QnaUpdateHandler implements DbshopHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		  String qnaNum = request.getParameter("qnaNum");
    	  String insertPw = request.getParameter("insertPw"); 
	      String qnaId = request.getParameter("qnaId"); //3개 모두 qnaRead.jsp의 form로부터 post방식으로 받은것

	      
	      //아이디를 위해 세션정보를 불러옴, 비번을 알기 위해서도 불러옴(세션 정보에는 비번 정보가 없다 (왠지는 몰라))
	      HttpSession session = request.getSession(); //java에서는 세션을 불러오기위해 이걸 써줘야하지만 jsp에서는 밑의 줄만 쓰면됨
	      UsersDto usersDto = (UsersDto)session.getAttribute("userInfo"); //session정보를 dto에 담을 수 있음
	      
	      //비번을 위해 내정보 불러옴
	      Dao dao = new Dao();
	      usersDto = dao.getMyInfo(usersDto.getId()); // "select * from users where id=?";
	      String myPw = usersDto.getPw();
	      String myId = usersDto.getId();
    	  
	   
	    	  
	       String goPage = "";
	       if(myId.equals(qnaId) && myPw.equals(insertPw)) { // 내글이 맞고, 비번이 맞다면
	    	   QnaDto qnaDto = dao.ReadQnaData(Integer.parseInt(qnaNum)); //"SELECT * FROM qna WHERE qnaNum=?";
	    	   request.setAttribute("qnaData", qnaDto); //이걸 qnaUpdate.jsp로 전달
		          
	    	   goPage = "/qnaUpdate.jsp";
	       }else{  
	    	   goPage = "/pwchkerr.jsp";
	       }
	       return goPage;
	}

}
