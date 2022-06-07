<%@page import="com.dbshop.UsersDto"%>
<%@page import="com.dbshop.QnaAnswerDto"%>
<%@page import="com.dbshop.QnaDto"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
    <style>
        *,html,body{
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        #wrap{
            width: 900px;
            font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Robonto,
             Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
            margin: 0 auto;
        }
        #wrap>div{
            margin-bottom: 10px;
        }

        
        #pageTitle{
            font-size: 30px;
            font-weight: bold;
            height: 100px;
            text-align: center;
            width: 100%;
            border-bottom: 1px solid gainsboro;
            padding: 25px;
        }
        #top{
            width: 100%;
            height: 100px;
            border-bottom: 1px solid gainsboro;
            padding-top: 25px;
        }
        #title{
            font-size: 20px;
        }
        #id, #date{
            font-size: 12px;
            display: inline-block;
            color: gray;
        }
        #content{
            min-height: 200px;
            max-height: 400px;
        }
        #mnd{
            justify-content: flex-end;
            display: flex;
            border-bottom: 1px solid gainsboro; 
            padding-bottom: 10px;
        }
        #mnd div{
            display: inline-block;
            margin-left: 5px;
        }
        input[type="password"]{
            width: 150px;
            height: 25px;
            border: 1px solid gainsboro;
        }
        #mnd input[type="submit"], input[type="button"]{
            width: 50px;
            height: 25px;
            background-color: rgb(241, 241, 241);
            color: black;
            border: 1px solid gainsboro; 
            cursor: pointer;
            
        }
        #list{
            width: 250px;
            height: 50px;
            background-color: whitesmoke;
            color: black;
            font-weight: bold;
            margin-left: 330px;
            margin-bottom: 20px;
            margin-top: 20px;
            border: 1px solid black;
            cursor: pointer;
        }
        #replyBox{
            width: 100%;
            height: 100px;
            background-color: rgb(236, 249, 255);
            position: relative;
            margin-top: 10px;
        }
        #reply{
            width: 90%;
            height: 80%;
            position: absolute;
            top: 10px;
            left: 10px;
        }
        #ansDelBtn{
        	width:20px;
        	height: 20px;
        }
        #ansSubBtn{
            position: absolute;
            bottom: 10px;
            right: 15px;
        }
        #prev, #next{
            height: 70px;
            width: 100%;
            border-bottom: 1px solid gainsboro;
            border-top: 1px solid gainsboro;
            padding-top: 24px;
            color: gray;
            font-size: 14px;
            cursor: pointer;
        }
        a{
        	color: black;
        	text-decoration: none;
        }
        
        
       #replyWrap{
			width: 900px;
			min-heght: 100px;
			max-height: 600px;
			border: 1px solod grey;
       }
       #empty{
           width: 100%;
           height: 100px;
       }
       #replyList{
       		width:100%;
       		margin: 0 auto;
            position: relative;
       }
       #replyList div{
           width: 100%;
           min-height: 90px;
           border: 1px solid rgb(163, 163, 163);
           background-color: rgb(236, 249, 255);
           position: absolute;
           top: 10px;
           min-height: 70px;
           padding-right: 20px;
           font-size: 15px;
           padding-top: 10px;
       }
       #ansDelBtn{
            position: absolute;
            right: 4px;
            top: 14px;
            border: 1px solid rgb(163, 163, 163);
       }
</style>
</head>
<body>
<jsp:include page="top.jsp"/>	
<% 
    QnaDto qnaDto = (QnaDto)request.getAttribute("qnaData"); // QnaReadHandler로부터 받음
    QnaDto prevQnaDto = (QnaDto)request.getAttribute("prevQnaData"); 
    QnaDto nextQnaDto = (QnaDto)request.getAttribute("nextQnaData"); 
    UsersDto usersDto = (UsersDto)session.getAttribute("userInfo");
%>   
    

	<div id="wrap">
    <form action="QnaUpdate.do" method="post"> <!-- form action에는 get방식처럼 ?-&- 달수 없음. method가 있으니까.. -->
        <div id="pageTitle">Q&amp;A</div>
        <div id="top">
            <div id="title"><%=qnaDto.getTitle()%></div>
            <div id="id"><%=qnaDto.getId()%></div>
            <div id="date"><%=qnaDto.getQdate()%></div>
        </div>
        <div id="content"><%=qnaDto.getContent()%></div>
		
		<!-- 참고로 수정은 비번을 확인하고 삭제는 비번 체크없이 진행됨 -->
        <div id="mnd">
        <%if(usersDto != null && (usersDto.getLv()==10 || usersDto.getId().equals(qnaDto.getId()))) {%>
            <div id="showPw"><span style="font-size: 13px; color: gray;">비밀번호 : </span><input type="password" name="insertPw" id="insertPw"></div>
            <div><input type="submit" value="수정" id="update"></div>
            <div><input type="button" value="삭제" onclick="location.href='QnaDelete.do?qnaNum=<%=qnaDto.getQnaNum()%>&id=<%=qnaDto.getId()%>'"></div>
        <% }%>
        </div>
        
		<input type="hidden" name="qnaNum" value="<%=qnaDto.getQnaNum()%>">
		<input type="hidden" name="qnaId" value="<%=qnaDto.getId()%>">
	</form>
	


	<!-- 댓글 있는 곳 -->
	<c:if test="${not empty requestScope.qaData}">
		<div id="replyWrap">
			<div id="replyList">
				<div>${requestScope.qaData.content}</div>
				<c:if test="${sessionScope.userInfo.lv eq 10}">
	            	<!-- 오리지널코드 <input type="button" id="ansDelBtn" value="x"
	             	onclick="location.href='AnswerDeleteProc.do?answerNum=<%=qnaDto.getQnaNum()%>'">-->
	             	<input type="button" id="ansDelBtn" value="x"
	             	onclick="location.href='AnswerDeleteProc.do?qnaNum=<%=qnaDto.getQnaNum()%>'">
				</c:if>
			</div>
		</div>
		<div id="empty"></div>
	</c:if>	
	
	<c:if test="${empty requestScope.qaData && sessionScope.userInfo.lv eq 10}">
		<div id="replyBox">
			<form action="AnswerInsertProc.do" method="post">
	           	<textarea id="reply" name="content"></textarea>
	           	<input type="submit" id="ansSubBtn" value="등록">
	           	<input type="hidden" name="qnaNum" value="<%=qnaDto.getQnaNum()%>">
	        </form>
	    </div>
    </c:if>
    
    <input type="button" id="list" value="목록" onclick="location.href='Qna.do'">

       
       
	<c:if test="<%=prevQnaDto.getQnaNum() != 0 %>">
       	<div id="prev"><span style="padding-right: 30px;">이전글</span>
       	<a href="QnaRead.do?qnaNum=<%=prevQnaDto.getQnaNum()%>"><%=prevQnaDto.getTitle()%></a></div> 
	</c:if>
	<c:if test="<%=nextQnaDto.getQnaNum() != 0 %>">
       	<div id="next"><span style="padding-right: 30px;">다음글</span>
       	<a href="QnaRead.do?qnaNum=<%=nextQnaDto.getQnaNum()%>"><%=nextQnaDto.getTitle()%></a></div> 
	</c:if>

	
</div>    
    
	

<script>
	/*$(function(){  submit클릭 동시에  form action통해 이동하므로 이게 안됨 
						-대신 수정하기 버튼을 생성뒤에 누르면 이 창이 나오게 한다음 수정을 누를 수는 있음
	    $("#update").click(function(){
	        $("#showPw").css("visibility","visible")
	    });
	});*/
	

	var insertPw = document.frm.insertPw;
	
    function chkAll() {
        if(title.insertPw=="") { //title은 머지?
			alert("비밀번호를 입력해주세요.");
			document.frm.title.focus();
			//return false;
        }else{
        	document.frm.submit();
        }
    }
	


</script>
<jsp:include page="footer.jsp"/>
</body>
</html>