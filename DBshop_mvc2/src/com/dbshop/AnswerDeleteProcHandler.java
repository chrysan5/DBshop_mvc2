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
		
		/*오리지널 코드
		int answerNum = Integer.parseInt(request.getParameter("answerNum")); //qnaRead.jsp로부터 get방식으로 받음
		
		Dao dao = new Dao();
		dao.deleteAnswer(answerNum);  // "DELETE from qnaAnswer where answerNum=?";
		                               //이때 deleteAnswer의 매개변수는 int answerNum 이다
		
		String qnaNum = answerNum+"";

		//return "QnaRead.do?qnaNum=<%=answerNum%>"; 안됨 ---근데 왜 이게 AnswerInsertProc.java에는 필요없지..?
		//여기서 qnaNum을 가지고 qnaReadHandler을 거쳐 qnaRead로 가야됨
		//return "QnaRead.do?qnaNum=<%=answerNum%>"; 안되므로 ansDel.jsp거쳐서 가고, ansDel.jsp로 qnaNum을 보내줌
		request.setAttribute("qnaNum", answerNum); //여기 answerNum을 qnaNum로 바꿔도 되는지 테스트하기
		
		return "/ansDel.jsp"; 
		
		//ansDel.jsp 내용은 아래와 같다=============================
		<%
			int qnaNum = (int)request.getAttribute("qnaNum");
		%>
		<script>
			location.href="QnaRead.do?qnaNum=<%=qnaNum%>";
		</script>
		======================================================*/
		
		
		//qnaRead.jsp에서 AnswerDeleteProcHandler로 가서 QnaReadHandler거쳐 다시 qnaRead로 가는게 하나의 요청단위이므로
		//아래의 qnaNum은 QnaReadHandler로 자동 전달된다! 그래서 위의 코드를 아래처럼 쓸 수 있음
		
		int qnaNum = Integer.parseInt(request.getParameter("qnaNum")); //qnaRead.jsp의 form로부터 받음
		
		Dao dao = new Dao();
		dao.deleteAnswer(qnaNum);  // "DELETE from qnaAnswer where answerNum=?";
		
		return "QnaRead.do";
	}

}
