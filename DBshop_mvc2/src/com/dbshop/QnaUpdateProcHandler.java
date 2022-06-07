package com.dbshop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QnaUpdateProcHandler implements DbshopHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		int qnaNum = Integer.parseInt(request.getParameter("qnaNum")); 
		String title = request.getParameter("title");
		String content = request.getParameter("content"); //3개 다 qnaUpdate.jsp의 form로부터 받음
		
		
		QnaDto qnaDto = new QnaDto();
		qnaDto.setQnaNum(qnaNum);
		qnaDto.setTitle(title);
		qnaDto.setContent(content);
		
		Dao dao = new Dao();
		dao.updateQnaData(qnaDto); ///"update qna set title=?, content=? where qnaNum=?";
		
	
		return "QnaRead.do";
		
		
	
	}

}
