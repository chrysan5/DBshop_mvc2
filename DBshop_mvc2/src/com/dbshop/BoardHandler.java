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
      //페이징때문에 아래처럼 코딩함,,
     
      int boardTotalCount = dao.selectBoardCnt(); //전체 게시글수 //"select count(productNum) from product";
      int pageRow = 10; // 한 페이지에 출력될 행의 개수
      int nowPage = 1; // 현재 페이지 번호
      int totalPage = (int)Math.ceil(boardTotalCount/(double)pageRow); // 전체 페이지 개수
      int countPage = 10; // 한 페이지에 출력될 페이징 번호의 개수
      
      if(request.getParameter("nowPage") != null) {
         nowPage = Integer.parseInt(request.getParameter("nowPage")); //이걸 어디서 받아온단 말임??
      }
      
      // nowPage가 11이라면
      int startPage = ((nowPage-1)/countPage)*countPage+1; // 11 - 1 / 10 * 10 + 1 = 11
      int endPage = startPage-1 + countPage;
      
      if(endPage > totalPage) {
         endPage = totalPage;
      }
      
      Map<String, Integer> map = new HashMap<>(); //map은 그냥 아래 4가지를 묶는 역할, setAttribute에 한방에 넣을 수 있으므로
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