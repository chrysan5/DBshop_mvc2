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
      
      //=============== id ���� ��Ű ==============
      String save = request.getParameter("save"); //true��� ���ڿ�. boolean �ƴ�!!
      											  //�� ������ ���ؼ� id ���� üũ�� id �����
            

      Dao dao = new Dao();
      UsersDto usersDto = dao.login(id, pw); //id, pw�´� usersDto ã�Ƽ� ���� ��´�
      
      //======================================
   
      
      String goPage = null;
      if(usersDto.getIdx() > 0) { 
         HttpSession session = request.getSession();
         //request�� getSession() �޼���� ������ ������ ������ �ִٸ� ������ ��ȯ�ϰ�, ���ٸ� �� ������ �����Ͽ� ��ȯ��.
         
         // ���̵� ���� üũ�� ���ϰ� , �α��ο� �������� ���
         if(save == null && usersDto.getIdx() != 0) { 
            Cookie cookie = new Cookie("saveid",usersDto.getId()); // ��Ű ��ü�� �����ϰ�
            cookie.setMaxAge(0);
            response.addCookie(cookie); // ��Ű ��ü�� ���������� ������
            session.setAttribute("userInfo", usersDto); //��Ű�� �������, �α��� ������ ��� ���� ������ �����ȴ�.
            //session.setAttribute("logindata", usersDto); ���� �̰ſ��� logindata ã���� userInfo�� �ٲ��ֱ�!
         // ���̵� ������ üũ�ϰ�, �α��ο� �������� ���
         }else if(save != null && save.equals("true") && usersDto.getIdx() != 0) {   
            Cookie cookie = new Cookie("saveid",usersDto.getId());
            cookie.setMaxAge(60*60*24*7);
            response.addCookie(cookie);
            session.setAttribute("userInfo", usersDto);
         }
         
         if(usersDto.getUseyn() == 2){ //Ż���� ȸ���� ���
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