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
<c:if test="${sessionScope.userInfo == null}">
   <script>
        alert("로그인이 필요한 페이지 입니다.");
        location.replace("Login.do");
    </script>    
</c:if>
    
<div style="width: 100%; text-align: center; font-size: 2em;">주문 내역 확인</div><br><br>
	<input type="hidden" name="cmd" value="order">
	<div class="basketdiv" id="basket">
	
		<form name="orderform" id="orderform" method="get" class="orderform" >
			<div class="row head">
				<div class="subdiv">
                    <div class="check">주문번호</div>
                    <div class="img">이미지</div>
                    <div class="pname">상품명</div>
                </div>
                <div class="subdiv">
                    <div class="basketprice">수량</div>
                    <div class="num">합계</div>
                    <div class="sum">배송여부</div>
                </div>
                <div class="subdiv">
                    <div class="basketcmd">구매일</div>
                </div>
                <div class="split"></div>
            </div>
            
            
            <c:if test="${empty requestScope.orderData }">
                <div style="text-align: center;width: 100%;">주문 내역이 없습니다.</div>
            </c:if>
            
        	<c:forEach var="dto" items="${ requestScope.orderData }">
        		<c:forEach var="dto2" items="${ requestScope.productData }">
        			<c:if test="${dto.prodnum eq dto2. productNum}">
        			
        			
		                <div class="row data">
		                    <div class="subdiv">
		                        <div class="check">${dto.orderNum }</div>
		                        <div class="img" style="padding-top: 2px;"><img src="./img/${dto2.image }" width="60"></div>
		                        <div class="pname">
		                            <span>${dto2.name }</span>
		                        </div>
		                    </div>
		                    <div class="subdiv">
		                        <div class="basketprice">${ dto.quantity }</div>
		                        <div class="num">
		                            <div class="quantity">
		                                <%-- <input type="text" name="p_num1" id="p_num1" size="2" maxlength="4" class="p_num" value="${ dto.quantity }" readonly> --%>
		                                ${dto.quantity * dto2.price }원
		                            </div>
		                        </div>
		                        <c:if test="${dto.result eq '1'}">
		                        	<div>배송준비중</div>
		                        </c:if>
		                        <c:if test="${dto.result eq '2'}">
		                        	<div>배송완료</div>
		                        </c:if>
		                    </div>
		                    <div class="subdiv">
		                        <div class="basketcmd">${dto.orderDate }</div>
		                    </div>
		                </div>
		                
		                
                	</c:if>
                </c:forEach>
            </c:forEach>
    	</form>
    </div>
<jsp:include page="footer.jsp"/>    
</body>
</html>