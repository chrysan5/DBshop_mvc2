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
	      String qnaId = request.getParameter("qnaId"); //3�� ��� qnaRead.jsp�� form�κ��� post������� ������

	      
	      //���̵� ���� ���������� �ҷ���, ����� �˱� ���ؼ��� �ҷ���(���� �������� ��� ������ ���� (������ ����))
	      HttpSession session = request.getSession(); //java������ ������ �ҷ��������� �̰� ����������� jsp������ ���� �ٸ� �����
	      UsersDto usersDto = (UsersDto)session.getAttribute("userInfo"); //session������ dto�� ���� �� ����
	      
	      //����� ���� ������ �ҷ���
	      Dao dao = new Dao();
	      usersDto = dao.getMyInfo(usersDto.getId()); // "select * from users where id=?";
	      String myPw = usersDto.getPw();
	      String myId = usersDto.getId();
    	  
	   
	    	  
	       String goPage = "";
	       if(myId.equals(qnaId) && myPw.equals(insertPw)) { // ������ �°�, ����� �´ٸ�
	    	   QnaDto qnaDto = dao.ReadQnaData(Integer.parseInt(qnaNum)); //"SELECT * FROM qna WHERE qnaNum=?";
	    	   request.setAttribute("qnaData", qnaDto); //�̰� qnaUpdate.jsp�� ����
		          
	    	   goPage = "/qnaUpdate.jsp";
	       }else{  
	    	   goPage = "/pwchkerr.jsp";
	       }
	       return goPage;
	}

}
