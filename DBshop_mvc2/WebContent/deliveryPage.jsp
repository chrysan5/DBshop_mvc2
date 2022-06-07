<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
	<link rel="stylesheet" href="./css/cart.css" />
</head>
<body>
<jsp:include page="top.jsp"/>
<c:if test="${sessionScope.userInfo.lv != 10}">
   <script>
        alert("관리자가 아닙니다.");
        location.replace("Main.do");
    </script>    
</c:if>
    
    	
    
<form name="orderform" id="orderform" method="get" class="orderform" >
<%int count = 1; %>
 	
	<input type="hidden" name="cmd" value="order">
	<div class="basketdiv" id="basket">
		<div>
   			<input type="button" value="배송완료 조회" onclick="location.href='DeliveryOnly.do'">
   			<input type="button" value="배송준비중 조회" onclick="location.href='DeliveryReadyOnly.do'">
   		</div>
   		
        <div class="row head">
            <div class="subdiv">
                <div class="check">선택</div>
                <div class="img">이미지</div>
                <div class="pname">상품명</div>
            </div>
            <div class="subdiv">
                <div class="basketprice">고객아이디</div>
                <div class="num">배송여부</div>
                <div class="sum">수량</div>
            </div>
            <div class="subdiv">
                <div class="basketcmd">배송</div>
            </div>
            <div class="split"></div>
        </div>     
             
      	<!-- 여기가 핵심 ★★★/ 주문이랑 상품이랑 일치하는 것으로 목록 나타냄/ for each 마다 count 증가! -->
		<c:forEach var="dto" items="${ requestScope.orderData }">
			<c:forEach var="dto2" items="${ requestScope.productData }">
				<c:if test="${dto.prodnum eq dto2. productNum}">		
     						
		             <div class="row data">
		                 <div class="subdiv">
		                     <div class="check"><input type="checkbox" name="sel<%=count %>" value="${dto.orderNum }">&nbsp;</div>
		                     <div class="img" style="padding-top: 2px;"><img src="./img/${dto2.image }" width="60"></div>
		                     <div class="pname">
		                         <span>${dto2.name }</span>
		                         <input type="hidden" name="productNum<%=count %>" value="${dto2.productNum }">
		                     </div>
		                 </div>
		                 <div class="subdiv">
		                     <div class="basketprice">${dto.id }</div>
		                     <div class="num">
		                         <div class="quantity">
		                         	<c:if test="${dto.result eq '1' }">
		                         		배송준비중
		                             </c:if>
		                             <c:if test="${dto.result eq '2' }">
		                         		배송완료
		                             </c:if>
		                         </div>
		                     </div>
		                     <div class="sum">${dto.quantity }</div>
		                 </div>
		                 <div class="subdiv">
		                     <div class="basketcmd">
		                     	<a class="abutton" onclick="location.href='DeliveryProc.do?orderNum=${dto.orderNum }'">배송</a>
		                     	<a class="abutton" onclick="location.href='DeliveryCancelProc.do?orderNum=${dto.orderNum }'">배송취소</a>
		                     </div>
		                 </div>
		             </div>
		             
				</c:if>
      		</c:forEach>
            <% count++; %>
       	</c:forEach>
     
        <input type="hidden" value="<%=count %>" name="count">  
 
        <div class="basketrowcmd">
         	<input class="abutton" type="submit" value="선택상품배송" onclick="javascript:basket.deliverySel()">
            <input class="abutton" type="submit" value="선택상품배송취소" onclick="javascript:basket.deliverySelCancel()">
        </div>
 
	</div>
</form>

<script> 
	//form 하나에 submit 2개 하기위해 자바스크립트를 이용했다.
	let basket = {
		deliverySel: function(){
	    	orderform.action="DeliverySel.do"
	    },
	    deliverySelCancel: function(){
	    	orderform.action="DeliverySelCancel.do"
	    }
	}
</script>
<jsp:include page="footer.jsp"/>
</body>
</html>