package com.dbshop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AnswerInsertProcHandler implements DbshopHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int qnaNum = Integer.parseInt(request.getParameter("qnaNum")); //qnaRead.jsp의 form로부터 받음
		String content = request.getParameter("content");
		
		QnaAnswerDto dto = new QnaAnswerDto();
		dto.setAnswerNum(qnaNum); //answerNum는 qnaNum과 같아야함
		dto.setContent(content);
		
		Dao dao = new Dao();
		int result = dao.insertAnswer(dto); //"INSERT INTO qnaAnswer VALUES (?,?,NOW())";
		
		return "QnaRead.do"; //여기로갈때 qnaNum이 필요한데 어떻게 전달한거지? 위의 qnaNum이 자동으로 전달되나?
							//answerInsertProc에서 QnaReadHandler로 가는게 하나의 요청 단위라서 전달된듯하다..
	}

}
