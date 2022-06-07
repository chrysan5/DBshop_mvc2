package com.dbshop;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AnswerDeleteProcHandler implements DbshopHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		/*�������� �ڵ�
		int answerNum = Integer.parseInt(request.getParameter("answerNum")); //qnaRead.jsp�κ��� get������� ����
		
		Dao dao = new Dao();
		dao.deleteAnswer(answerNum);  // "DELETE from qnaAnswer where answerNum=?";
		                               //�̶� deleteAnswer�� �Ű������� int answerNum �̴�
		
		String qnaNum = answerNum+"";

		//return "QnaRead.do?qnaNum=<%=answerNum%>"; �ȵ� ---�ٵ� �� �̰� AnswerInsertProc.java���� �ʿ����..?
		//���⼭ qnaNum�� ������ qnaReadHandler�� ���� qnaRead�� ���ߵ�
		//return "QnaRead.do?qnaNum=<%=answerNum%>"; �ȵǹǷ� ansDel.jsp���ļ� ����, ansDel.jsp�� qnaNum�� ������
		request.setAttribute("qnaNum", answerNum); //���� answerNum�� qnaNum�� �ٲ㵵 �Ǵ��� �׽�Ʈ�ϱ�
		
		return "/ansDel.jsp"; 
		
		//ansDel.jsp ������ �Ʒ��� ����=============================
		<%
			int qnaNum = (int)request.getAttribute("qnaNum");
		%>
		<script>
			location.href="QnaRead.do?qnaNum=<%=qnaNum%>";
		</script>
		======================================================*/
		
		
		//qnaRead.jsp���� AnswerDeleteProcHandler�� ���� QnaReadHandler���� �ٽ� qnaRead�� ���°� �ϳ��� ��û�����̹Ƿ�
		//�Ʒ��� qnaNum�� QnaReadHandler�� �ڵ� ���޵ȴ�! �׷��� ���� �ڵ带 �Ʒ�ó�� �� �� ����
		
		int qnaNum = Integer.parseInt(request.getParameter("qnaNum")); //qnaRead.jsp�� form�κ��� ����
		
		Dao dao = new Dao();
		dao.deleteAnswer(qnaNum);  // "DELETE from qnaAnswer where answerNum=?";
		
		return "QnaRead.do";
	}

}
