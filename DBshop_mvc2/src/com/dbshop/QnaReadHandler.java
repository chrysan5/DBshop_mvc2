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
	
		
		int qnaNum = Integer.parseInt(request.getParameter("qnaNum")); //qnaPage.jsp로부터 받아오거나(qnaPage에서 올때)
																		//혹은 qnaRead.jsp로부터 받음(이전글/다음글)
												//AnswerDeleteProcHandler와 AnswerInsertProcHandler를 거쳐서 오기도함
		
		
		//--qnaAnswer위한 코드
		Dao dao = new Dao();																
		boolean result = dao.checkExistAns(qnaNum); //"select count(answerNum) from qnaAnswer where answerNum=?"; 
		if(result == true) { //qnaNum과 같은 answerNum이 qnaAnswer 테이블에 있으면 = 답변이 있으면
							 //즉, qnaNum과 answerNum이 일치할때 해당 글에 답변 달린 상태임
			QnaAnswerDto qadto = dao.readAnswer(qnaNum); // "SELECT * FROM qnaAnswer WHERE answerNum=?";
			request.setAttribute("qaData", qadto); 
	    }
		//-- 
		
		
		//--qna테이블 위한 코드
		/*QnaDto qnaDto = dao.ReadQnaData(qnaNum);
		QnaDto prevQnaDto = dao.ReadQnaData(qnaNum-1);
		QnaDto nextQnaDto = dao.ReadQnaData(qnaNum+1);
		이렇게하면 QnaRead.jsp 글을 삭제하지 못하게됨 */
		
		QnaDto qnaDto = dao.ReadQnaData(qnaNum); //"SELECT * FROM qna WHERE qnaNum=?";
		QnaDto prevQnaDto = dao.ReadPrevQnaData(qnaNum); // "SELECT * FROM qna WHERE qnaNum=(select max(qnaNum) from qna where qnaNum<?)";
		QnaDto nextQnaDto = dao.ReadNextQnaData(qnaNum); //"SELECT * FROM qna WHERE qnaNum=(select min(qnaNum) from qna where qnaNum>?)";
		
	
		request.setAttribute("qnaData", qnaDto);
		request.setAttribute("prevQnaData", prevQnaDto);
		request.setAttribute("nextQnaData", nextQnaDto);
	      
		return "/qnaRead.jsp";
	}

}
