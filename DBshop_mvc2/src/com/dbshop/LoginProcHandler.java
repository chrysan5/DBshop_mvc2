package com.dbshop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginProcHandler implements DbshopHandler {

   @Override
   public String process(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {

      String id = request.getParameter("id");
      String pw = request.getParameter("pw");
      
      //=============== id 저장 쿠키 ==============
      String save = request.getParameter("save"); //true라는 문자열. boolean 아님!!
      											  //이 과정을 통해서 id 저장 체크시 id 저장됨
            

      Dao dao = new Dao();
      UsersDto usersDto = dao.login(id, pw); //id, pw맞는 usersDto 찾아서 값을 담는다
      
      //======================================
   
      
      String goPage = null;
      if(usersDto.getIdx() > 0) { 
         HttpSession session = request.getSession();
         //request의 getSession() 메서드는 서버에 생성된 세션이 있다면 세션을 반환하고, 없다면 새 세션을 생성하여 반환함.
         
         // 아이디 저장 체크를 안하고 , 로그인에 성공했을 경우
         if(save == null && usersDto.getIdx() != 0) { 
            Cookie cookie = new Cookie("saveid",usersDto.getId()); // 쿠키 객체를 생성하고
            cookie.setMaxAge(0);
            response.addCookie(cookie); // 쿠키 객체를 웹브라우저로 보낸다
            session.setAttribute("userInfo", usersDto); //쿠키와 상관없이, 로그인 성공할 경우 세션 정보도 생성된다.
            //session.setAttribute("logindata", usersDto); 원래 이거여서 logindata 찾으면 userInfo로 바꿔주기!
         // 아이디 저장을 체크하고, 로그인에 성공했을 경우
         }else if(save != null && save.equals("true") && usersDto.getIdx() != 0) {   
            Cookie cookie = new Cookie("saveid",usersDto.getId());
            cookie.setMaxAge(60*60*24*7);
            response.addCookie(cookie);
            session.setAttribute("userInfo", usersDto);
         }
         
         if(usersDto.getUseyn() == 2){ //탈퇴한 회원일 경우
            goPage = "/userDrop.jsp";
         }else{
            session.setAttribute("userInfo", usersDto); 
            goPage = "redirect:/Main.do";
         }
      }else {
         goPage = "/loginerr.jsp";
      }
      return goPage;
   }

}