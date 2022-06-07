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
      String pw = request.getParameter("pw"); //pwchkupdate.jsp로 부터 받아왔다
      
      HttpSession session = request.getSession(); //아래 문장을 실행하기 위해 세션을 불러옴. 
      UsersDto usersDto = (UsersDto)session.getAttribute("userInfo"); //세션정보에서 id를 받아오기위해 이 문장 실행함
      
      Dao dao = new Dao();
      boolean result = dao.pwChk(idx, usersDto.getId(), pw); //id, pw 맞는 유저인지 확인하는 쿼리
      
      String goPage = "";
      if(result == true) { // 비밀번호가 맞다면
         usersDto = dao.getMyInfo(usersDto.getId()); //id에 해당하는 모든 정보를 가져와서
         request.setAttribute("usersDto", usersDto); //userDto에 넣음
         
         goPage = "/myPage.jsp";
      }else { // 비밀번호가 틀리다면
         goPage = "/pwchkerr.jsp";
      }
      return goPage;
   }

}