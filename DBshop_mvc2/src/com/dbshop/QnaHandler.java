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
		//qnaSearch�� qnaPage.jsp�κ��� �޾ƿ´�. �޾Ƴ�� ���� ��� if�� �Ʒ�ó�� ó������!

		if (insertText != null) {
			/*
			 Dao dao = new Dao();
			 Vector<QnaDto> v = dao.searchQna(item, insertText);
			 request.setAttribute("qnaData", v);
			 */

			Dao dao = new Dao();
			// Vector<QnaDto> v = dao.selectQnaData();
			int boardTotalCount = dao.searchQnaCnt( insertText); // ��ü �������� ��(��ü �Խñۼ�)
			int pageRow = 5; // �� �������� ��µ� ���� ����
			int nowPage = 1; // ���� ������ ��ȣ
			int totalPage = (int) Math.ceil(boardTotalCount / (double) pageRow); // ��ü ������ ����
			int countPage = 5; // �� �������� ��µ� ����¡ ��ȣ�� ����

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
			int boardTotalCount = dao.selectQnaCnt(); // ��ü �������� ��(��ü �Խñۼ�)
			int pageRow = 5; // �� �������� ��µ� ���� ����
			int nowPage = 1; // ���� ������ ��ȣ
			int totalPage = (int) Math.ceil(boardTotalCount / (double) pageRow); // ��ü ������ ����
			int countPage = 5; // �� �������� ��µ� ����¡ ��ȣ�� ����

			if (request.getParameter("nowPage") != null) {
				nowPage = Integer.parseInt(request.getParameter("nowPage"));
			}

			// nowPage�� 11�̶��
			int startPage = ((nowPage - 1) / countPage) * countPage + 1; 
			int endPage = startPage - 1 + countPage;

			if (endPage > totalPage) {
				endPage = totalPage;
			}
			
			//nowPage, startPage, endPage, totalPage �ϳ��� ������ �ѹ��� ���ް����ϹǷ� �׳� ���� �뵵�� map ������
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