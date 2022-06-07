package com.dbshop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QnaInsertProcHandler implements DbshopHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		String id = request.getParameter("id"); 
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		
		QnaDto qnaDto = new QnaDto();
		qnaDto.setId(id);
		qnaDto.setTitle(title);
		qnaDto.setContent(content);
		
		Dao dao = new Dao();
		int result = dao.insertQna(qnaDto);
		
		return "Qna.do";
	}

}
