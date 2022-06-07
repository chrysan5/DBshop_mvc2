package com.dbshop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class QnaReadHandler implements DbshopHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		
		int qnaNum = Integer.parseInt(request.getParameter("qnaNum")); //qnaPage.jsp�κ��� �޾ƿ��ų�(qnaPage���� �ö�)
																		//Ȥ�� qnaRead.jsp�κ��� ����(������/������)
												//AnswerDeleteProcHandler�� AnswerInsertProcHandler�� ���ļ� ���⵵��
		
		
		//--qnaAnswer���� �ڵ�
		Dao dao = new Dao();																
		boolean result = dao.checkExistAns(qnaNum); //"select count(answerNum) from qnaAnswer where answerNum=?"; 
		if(result == true) { //qnaNum�� ���� answerNum�� qnaAnswer ���̺� ������ = �亯�� ������
							 //��, qnaNum�� answerNum�� ��ġ�Ҷ� �ش� �ۿ� �亯 �޸� ������
			QnaAnswerDto qadto = dao.readAnswer(qnaNum); // "SELECT * FROM qnaAnswer WHERE answerNum=?";
			request.setAttribute("qaData", qadto); 
	    }
		//-- 
		
		
		//--qna���̺� ���� �ڵ�
		/*QnaDto qnaDto = dao.ReadQnaData(qnaNum);
		QnaDto prevQnaDto = dao.ReadQnaData(qnaNum-1);
		QnaDto nextQnaDto = dao.ReadQnaData(qnaNum+1);
		�̷����ϸ� QnaRead.jsp ���� �������� ���ϰԵ� */
		
		QnaDto qnaDto = dao.ReadQnaData(qnaNum); //"SELECT * FROM qna WHERE qnaNum=?";
		QnaDto prevQnaDto = dao.ReadPrevQnaData(qnaNum); // "SELECT * FROM qna WHERE qnaNum=(select max(qnaNum) from qna where qnaNum<?)";
		QnaDto nextQnaDto = dao.ReadNextQnaData(qnaNum); //"SELECT * FROM qna WHERE qnaNum=(select min(qnaNum) from qna where qnaNum>?)";
		
	
		request.setAttribute("qnaData", qnaDto);
		request.setAttribute("prevQnaData", prevQnaDto);
		request.setAttribute("nextQnaData", nextQnaDto);
	      
		return "/qnaRead.jsp";
	}

}
