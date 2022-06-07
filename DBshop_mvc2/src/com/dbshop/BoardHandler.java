package com.dbshop;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardHandler implements DbshopHandler {

   @Override
   public String process(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
	   
      Dao dao = new Dao();
      //Vector<ProductDto> v = dao.selectProduct(); // "SELECT * FROM product";
      //����¡������ �Ʒ�ó�� �ڵ���,,
     
      int boardTotalCount = dao.selectBoardCnt(); //��ü �Խñۼ� //"select count(productNum) from product";
      int pageRow = 10; // �� �������� ��µ� ���� ����
      int nowPage = 1; // ���� ������ ��ȣ
      int totalPage = (int)Math.ceil(boardTotalCount/(double)pageRow); // ��ü ������ ����
      int countPage = 10; // �� �������� ��µ� ����¡ ��ȣ�� ����
      
      if(request.getParameter("nowPage") != null) {
         nowPage = Integer.parseInt(request.getParameter("nowPage")); //�̰� ��� �޾ƿ´� ����??
      }
      
      // nowPage�� 11�̶��
      int startPage = ((nowPage-1)/countPage)*countPage+1; // 11 - 1 / 10 * 10 + 1 = 11
      int endPage = startPage-1 + countPage;
      
      if(endPage > totalPage) {
         endPage = totalPage;
      }
      
      Map<String, Integer> map = new HashMap<>(); //map�� �׳� �Ʒ� 4������ ���� ����, setAttribute�� �ѹ濡 ���� �� �����Ƿ�
      map.put("nowPage", nowPage);
      map.put("startPage", startPage);
      map.put("endPage", endPage);
      map.put("totalPage", totalPage);
      
      Vector<ProductDto> v = dao.selectBoard(nowPage, pageRow); //"select * from product limit ?, ?";
      
      request.setAttribute("pageData", map); 
      request.setAttribute("BoardData", v);
      
      return "/board.jsp";
   }

}