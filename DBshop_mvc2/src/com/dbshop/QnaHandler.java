package com.dbshop;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QnaHandler implements DbshopHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String insertText = request.getParameter("qnaSearch");
		//qnaSearch는 qnaPage.jsp로부터 받아온다. 받아노는 값이 없어도 if로 아래처럼 처리가능!

		if (insertText != null) {
			/*
			 Dao dao = new Dao();
			 Vector<QnaDto> v = dao.searchQna(item, insertText);
			 request.setAttribute("qnaData", v);
			 */

			Dao dao = new Dao();
			// Vector<QnaDto> v = dao.selectQnaData();
			int boardTotalCount = dao.searchQnaCnt( insertText); // 전체 데이터의 양(전체 게시글수)
			int pageRow = 5; // 한 페이지에 출력될 행의 개수
			int nowPage = 1; // 현재 페이지 번호
			int totalPage = (int) Math.ceil(boardTotalCount / (double) pageRow); // 전체 페이지 개수
			int countPage = 5; // 한 페이지에 출력될 페이징 번호의 개수

			if (request.getParameter("nowPage") != null) {
				nowPage = Integer.parseInt(request.getParameter("nowPage"));
			}

			
			int startPage = ((nowPage - 1) / countPage) * countPage + 1; 
			int endPage = startPage - 1 + countPage;

			if (endPage > totalPage) {
				endPage = totalPage;
			}

			Map<String, Integer> map = new HashMap<>();
			map.put("nowPage", nowPage);
			map.put("startPage", startPage);
			map.put("endPage", endPage);
			map.put("totalPage", totalPage);

			Vector<QnaDto> v = dao.searchSelectQna(insertText, nowPage, pageRow);
			//"(select * from qna WHERE title LIKE ? limit ?, ?)order by qnaNum DESC ";

			request.setAttribute("pageData", map);
			request.setAttribute("qnaData", v);

		} else {

			Dao dao = new Dao();
			// Vector<QnaDto> v = dao.selectQnaData();
			int boardTotalCount = dao.selectQnaCnt(); // 전체 데이터의 양(전체 게시글수)
			int pageRow = 5; // 한 페이지에 출력될 행의 개수
			int nowPage = 1; // 현재 페이지 번호
			int totalPage = (int) Math.ceil(boardTotalCount / (double) pageRow); // 전체 페이지 개수
			int countPage = 5; // 한 페이지에 출력될 페이징 번호의 개수

			if (request.getParameter("nowPage") != null) {
				nowPage = Integer.parseInt(request.getParameter("nowPage"));
			}

			// nowPage가 11이라면
			int startPage = ((nowPage - 1) / countPage) * countPage + 1; 
			int endPage = startPage - 1 + countPage;

			if (endPage > totalPage) {
				endPage = totalPage;
			}
			
			//nowPage, startPage, endPage, totalPage 하나로 묶으면 한번에 전달가능하므로 그냥 묶는 용도로 map 생성함
			Map<String, Integer> map = new HashMap<>();
			map.put("nowPage", nowPage);
			map.put("startPage", startPage);
			map.put("endPage", endPage);
			map.put("totalPage", totalPage);

			Vector<QnaDto> v = dao.selectQna(nowPage, pageRow); 
			//"select * from qna order by qnaNum desc limit ?, ?";

			request.setAttribute("pageData", map);
			request.setAttribute("qnaData", v);

		}

		return "/qnaPage.jsp";
	}
}