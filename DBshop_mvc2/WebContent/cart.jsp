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

    
    
<form name="orderform" id="orderform" method="get" class="orderform" >
<%int count = 1; %>
	<input type="hidden" name="cmd" value="order">
    <div class="basketdiv" id="basket">
        <div class="row head">
            <div class="subdiv">
                <div class="check">����</div>
                <div class="img">�̹���</div>
                <div class="pname">��ǰ��</div>
            </div>
            <div class="subdiv">
                <div class="basketprice">����</div>
                <div class="num">����</div>
                <div class="sum">�հ�</div>
            </div>
            <div class="subdiv">

                <div class="basketcmd">����</div>
            </div>
            <div class="split"></div>
        </div>
        
		<c:forEach var="dto" items="${ requestScope.cartData }">
			<c:forEach var="dto2" items="${ requestScope.productData }">
				<c:if test="${dto.prodnum eq dto2. productNum}">
				
				
		            <div class="row data">
		                <div class="subdiv">
		                    <div class="check"><input type="checkbox" name="buy<%=count %>" value="${dto.cartNum }" onclick="javascript:basket.checkItem();">&nbsp;</div>
		                    <div class="img" style="padding-top: 2px;"><img src="./img/${dto2.image }" width="60"></div>
		                    <div class="pname">
		                        <span>${dto2.name }</span>
		                        <input type="hidden" name="productNum<%=count %>" value="${dto2.productNum }">
		                    </div>
		                </div>
		                <div class="subdiv">
		                    <div class="basketprice"><input type="hidden" name="p_price<%=count %>" id="p_price1" class="p_price" value="${dto2.price }">${dto2.price }��</div>
		                    <div class="num">
		                        <div class="quantity">
		                            <input type="text" name="p_num<%=count %>" id="p_num1" size="2" maxlength="4" class="p_num" value="${ dto.quantity }" readonly>
		                        </div>
		                    </div>
		                    <div class="sum">${dto.quantity * dto2.price }��</div>
		                </div>
		                <div class="subdiv">
		                    <div class="basketcmd"><a class="abutton" onclick="location.href='CartDelete.do?cartNum=${dto.cartNum }'">����</a></div>
		                </div>
		            </div>
		            
		            
            	</c:if>
            </c:forEach>
            <%count++; %>
       	</c:forEach>
    
       	<input type="hidden" value="<%=count %>" name="count">  

        <div class="basketrowcmd">
        	<input class="abutton" type="submit" value="���û�ǰ����" onclick="javascript:basket.selDeleteCart()">
            <a class="abutton" onclick="location.href='CartClear.do'">��ٱ��Ϻ���</a>
        </div>

        <div class="bigtext right-align sumcount" id="sum_p_num">��ǰ����: 0��</div>
        <div class="bigtext right-align box blue summoney" id="sum_p_price">�հ�ݾ�: 0��</div>

        <div id="goorder" class="">
            <div class="clear"></div>
            <div class="buttongroup center-align cmd">
                <a><input class="orderbtn" type="submit" value="������ ��ǰ �ֹ�" onclick="javascript:basket.cartOrder()" style="border:0; font-size: 1.2em;"></a>
            </div>
        </div>
    </div>
</form>

<script>
let basket = {
	    totalCount: 0, 
	    totalPrice: 0,
	    //���� -�̰� ���� �Ȱ�
	    reCalc: function(){
	        this.totalCount = 0;
	        this.totalPrice = 0;
	        document.querySelectorAll(".p_num").forEach(function (item) {
	            if(item.parentElement.parentElement.parentElement.previousElementSibling.firstElementChild.firstElementChild.checked == true){
	                var count = parseInt(item.getAttribute('value'));
	                this.totalCount += count;
	                var price = item.parentElement.parentElement.previousElementSibling.firstElementChild.getAttribute('value');
	                this.totalPrice += count * price;
	            }
	        }, this); // forEach 2��° �Ķ���ͷ� ��ü�� �Ѱܼ� this �� ��ü���ͷ��� ����Ű���� ��. - thisArg
	    },
	    //ȭ�� ������Ʈ
	    updateUI: function () {
	        document.querySelector('#sum_p_num').textContent = '��ǰ����: ' + this.totalCount.formatNumber() + '��';
	        document.querySelector('#sum_p_price').textContent = '�հ�ݾ�: ' + this.totalPrice.formatNumber() + '��';
	    },
	    checkItem: function () {
	        this.reCalc();
	        this.updateUI();
	    },
	    selDeleteCart: function(){
	    	orderform.action="SelDeleteCart.do"
	    },
	    cartOrder: function(){
	    	if(this.totalPrice.formatNumber()==0){
	    		alert("��ǰ�� üũ�� �ּ���.");
	    	}else{
	    		orderform.action="CartOrder.do"
	    	}
	    }
	}
	// ���� 3�ڸ� �޸����
	Number.prototype.formatNumber = function(){
	    if(this==0) return 0;
	    let regex = /(^[+-]?\d+)(\d{3})/;
	    let nstr = (this + '');
	    while (regex.test(nstr)) nstr = nstr.replace(regex, '$1' + ',' + '$2');
	    return nstr;
	};
</script>
<jsp:include page="footer.jsp"/>
</body>
</html>