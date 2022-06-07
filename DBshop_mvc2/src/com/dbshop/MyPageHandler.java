package com.dbshop;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyPageHandler implements DbshopHandler {

   @Override
   public String process(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {

      String idx = request.getParameter("idx"); 
      String pw = request.getParameter("pw"); //pwchkupdate.jsp�� ���� �޾ƿԴ�
      
      HttpSession session = request.getSession(); //�Ʒ� ������ �����ϱ� ���� ������ �ҷ���. 
      UsersDto usersDto = (UsersDto)session.getAttribute("userInfo"); //������������ id�� �޾ƿ������� �� ���� ������
      
      Dao dao = new Dao();
      boolean result = dao.pwChk(idx, usersDto.getId(), pw); //id, pw �´� �������� Ȯ���ϴ� ����
      
      String goPage = "";
      if(result == true) { // ��й�ȣ�� �´ٸ�
         usersDto = dao.getMyInfo(usersDto.getId()); //id�� �ش��ϴ� ��� ������ �����ͼ�
         request.setAttribute("usersDto", usersDto); //userDto�� ����
         
         goPage = "/myPage.jsp";
      }else { // ��й�ȣ�� Ʋ���ٸ�
         goPage = "/pwchkerr.jsp";
      }
      return goPage;
   }

}