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
        alert("�����ڰ� �ƴմϴ�.");
        location.replace("Main.do");
    </script>    
</c:if>
    
    	
    
<form name="orderform" id="orderform" method="get" class="orderform" >
<%int count = 1; %>
 	
	<input type="hidden" name="cmd" value="order">
	<div class="basketdiv" id="basket">
		<div>
   			<input type="button" value="��ۿϷ� ��ȸ" onclick="location.href='DeliveryOnly.do'">
   			<input type="button" value="����غ��� ��ȸ" onclick="location.href='DeliveryReadyOnly.do'">
   		</div>
   		
        <div class="row head">
            <div class="subdiv">
                <div class="check">����</div>
                <div class="img">�̹���</div>
                <div class="pname">��ǰ��</div>
            </div>
            <div class="subdiv">
                <div class="basketprice">�����̵�</div>
                <div class="num">��ۿ���</div>
                <div class="sum">����</div>
            </div>
            <div class="subdiv">
                <div class="basketcmd">���</div>
            </div>
            <div class="split"></div>
        </div>     
             
      	<!-- ���Ⱑ �ٽ� �ڡڡ�/ �ֹ��̶� ��ǰ�̶� ��ġ�ϴ� ������ ��� ��Ÿ��/ for each ���� count ����! -->
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
		                         		����غ���
		                             </c:if>
		                             <c:if test="${dto.result eq '2' }">
		                         		��ۿϷ�
		                             </c:if>
		                         </div>
		                     </div>
		                     <div class="sum">${dto.quantity }</div>
		                 </div>
		                 <div class="subdiv">
		                     <div class="basketcmd">
		                     	<a class="abutton" onclick="location.href='DeliveryProc.do?orderNum=${dto.orderNum }'">���</a>
		                     	<a class="abutton" onclick="location.href='DeliveryCancelProc.do?orderNum=${dto.orderNum }'">������</a>
		                     </div>
		                 </div>
		             </div>
		             
				</c:if>
      		</c:forEach>
            <% count++; %>
       	</c:forEach>
     
        <input type="hidden" value="<%=count %>" name="count">  
 
        <div class="basketrowcmd">
         	<input class="abutton" type="submit" value="���û�ǰ���" onclick="javascript:basket.deliverySel()">
            <input class="abutton" type="submit" value="���û�ǰ������" onclick="javascript:basket.deliverySelCancel()">
        </div>
 
	</div>
</form>

<script> 
	//form �ϳ��� submit 2�� �ϱ����� �ڹٽ�ũ��Ʈ�� �̿��ߴ�.
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