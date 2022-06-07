package com.dbshop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AnswerInsertProcHandler implements DbshopHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int qnaNum = Integer.parseInt(request.getParameter("qnaNum")); //qnaRead.jsp�� form�κ��� ����
		String content = request.getParameter("content");
		
		QnaAnswerDto dto = new QnaAnswerDto();
		dto.setAnswerNum(qnaNum); //answerNum�� qnaNum�� ���ƾ���
		dto.setContent(content);
		
		Dao dao = new Dao();
		int result = dao.insertAnswer(dto); //"INSERT INTO qnaAnswer VALUES (?,?,NOW())";
		
		return "QnaRead.do"; //����ΰ��� qnaNum�� �ʿ��ѵ� ��� �����Ѱ���? ���� qnaNum�� �ڵ����� ���޵ǳ�?
							//answerInsertProc���� QnaReadHandler�� ���°� �ϳ��� ��û ������ ���޵ȵ��ϴ�..
	}

}
