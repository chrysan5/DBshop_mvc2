<%@page import="java.util.Vector"%>
<%@page import="com.dbshop.ProductDto"%>
<%@page import="com.dbshop.UsersDto"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<style>
.checkout-full-box {
	width: 100%;
	height: auto;
}

.checkout-bg-color {
    background-color: #f9f9f9;
    width: 100%;
    height: 100%;
   	padding-bottom: 200px;
   	margin-bottom: -200px;
}

.checkout-full-wrp {
	max-width: 1440px;
    width: 100%;
    margin: 0 auto !important;
    padding: 0;
    font-family: 'Galano Grotesque',"Apple SD Gothic Neo","Noto Sans KR","Helvetica Neue",sans-serif;
    font-size: 13px;
    line-height: 1.2;
    color: #444;
}

.checkout-header-wrp {
    overflow: hidden;
    display: -ms-flexbox;
    display: -webkit-flex;
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin: 0 auto;
    padding: 100px 0 90px;
}

.checkout-info-wrp {
	position: relative;
	width: 100%;
	height: auto;
	margin: 0 auto;
}



.checkout-wrp-left {
	padding-right: calc(46.9% - 24px);
}

.checkout-wrp-right {
	position: fixed;
	top: 43%;
	left: calc(53.1% + 42px);
    width: calc(53.1% - 42px);
}

.left-section-delivery-info {
	width: auto;
}

.left-section-box {
	margin: 0px 0px 10px;
	padding: 80px;
	background-color: white;
}

.delivery-condition {
	position: relative;
	padding-bottom: 40px;
}

.delivery-condition-name {
	display: flex;
	justify-content: space-between;
}

.delivery-condition-info {
	margin: 0;
	padding: 0;
	list-style: none;
	display: block;
}

.delivery-request {
	margin: 25px 0 20px;
}

.btn-request {
	display: flex;
	align-items: center;
	justify-content: space-between;
	flex-direction: row;
	width: 100%;
	padding: 13px 12px 11px;
	text-align: left;
	background-color: #f9f9f9;
}

.btn-change-addr {
	position: absolute;
	top: 0;
	right: 0;
}

.left-section-btn {
    padding: 8px 9px 6px 8px;
    border: 1px solid rgb(221, 221, 221);
    background: rgb(255, 255, 255);
    cursor: pointer;
}

.delivery-user {
	position: relative;
    padding: 40px 0 0;
    border-top: 1px solid #ececec;
}

.delivery-user-info {
	margin: 0;
	padding: 0;
	list-style: none;
}

.info-type {
	display: inline-block;
	width: 50px;
	margin-right: 10px;
    font-size: 13px;
    font-weight: normal;
    color: #999;
}

.info-value {
    font-weight: normal;
    color: #555;
}

.btn-change-order {
	position: absolute;
    top: 40px;
    right: 0;
    padding: 8px 9px 6px 8px;
    border: 1px solid #ddd;
}

.section-order-info {
	width: auto;
}

.order-prod-list {
    margin: 0;
    padding: 0;
    border-bottom: 1px solid #111;
    list-style: none;
}

.order-prod-info {
	display: flex;
    justify-content: flex-start;
    align-items: center;
    flex-direction: row;
    padding: 40px 0 40px 20px;
    border-top: 1px solid #ececec;
}

.order-prod-img-a {
	width: 80px;
    display: block;
    position: relative;
    overflow: hidden;
    padding-top: 106px;
}

.order-prod-img-span {
    display: block;
    position: absolute;
    inset: 0px;
    transform: translate(50%, 50%);
}

.order-prod-img-span > img {
    max-width: 100%;
    max-height: 100%;
    width: 100%;
    height: auto;
    object-fit: contain;
    position: absolute;
    top: 0px;
    left: 0px;
    transform: translate(-50%, -50%);
}

.order-prod-text {
	display: flex;
	width: 100%;
    justify-content: flex-start;
    align-items: flex-start;
    flex-direction: column;
    margin-left: 25px;
}

.prod-brand {
	margin: 0 0 10px;
}

.prod-name {
	margin: 0 0 10px;
    width: 100%;
    overflow: hidden;
    text-overflow: ellipsis;
    line-height: 1.6;
    text-decoration: none;
}

.order-price-text {
    margin: 0;
    padding-top: 25px;
    display: flex;
    justify-content: flex-start;
    align-items: flex-end;
    flex-direction: column;
}

.price-unit {
	margin-left: 3px;
}

.checkout-middle-toolTip {
	outline: none;
	background: none;
	border: none;
}

.checkout-middle-toolTip:focus {
	outline: none;
	border: none;
}

.section-payment-info {
	width: auto;
}

.section-payment-info-title {
	border-bottom: 1px solid #ececec;
	padding-bottom: 20px;
}

.payment-type-list {
	padding: 20px 0;
	margin: 0;
	list-style: none;
}

.right-section-info {
	max-width: 634px;
	width: auto;
	margin: 0;
	margin-right: 70px;
}

.right-section-price-info {
	background-color: white;
	position: relative;
	margin-right: 70px;
	padding: 80px;
}

.expected-price-list {
	padding: 20px 0;
	margin: 0;
	list-style: none;
}

.expected-price-item {
	margin: 0;
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.total-expected-price {
    margin-top: 20px;
    margin-bottom: 100px;
    padding-top: 20px;
    display: flex;
    align-items: center;
    justify-content: space-between;
    border-top: 1px solid #ececec;
}

.btn-order {
	width: 100%;
	position: absolute;
	bottom: 0;
	left: 0;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 80px;
	background-color:lightgray;
	border: none;
	cursor: pointer;
	
}

.modal-change-addr {
	background-color: white;
	width: 520px;
	height: 680px;
	position: relative;
    padding: 110px 0px 0px;
}

.mh-modal-header {
    position: absolute;
    top: 0px;
    left: 0px;
    width: 100%;
    max-width: 520px;
    height: 110px;
    padding: 40px;
    display: flex;
    align-items: center;
    flex-direction: row;
    justify-content: flex-start;
}

.mh-modal-body-wrap {
	max-height: 490px;
    width: 100%;
    overflow: auto;
}

.mh-modal-body {
    overflow: hidden auto;
    position: relative;
    display: flex;
    align-items: center;
    justify-content: flex-start;
    flex-direction: column;
    width: 100%;
    height: 490px;
    padding: 0px 40px 20px;
    margin: 0px 0px 80px;
}

.delivery-modal-info-list {
    width: 100%;
    list-style: none;
    margin: 0;
    padding: 0;
    display: flex;
    display: -ms-flexbox;
    display: -webkit-flex;
    flex-direction: column;
    align-items: flex-start;
    justify-content: flex-start;
    border-bottom: none;
}

.mh-modal-sector {
	position: relative;
    margin-bottom: 20px;
    padding-bottom: 20px;
    border-bottom: 1px solid rgb(236, 236, 236);
}

.delivery-modal-info-item-title {
	padding: 14px 0 0 0;
    display: flex;
    align-items: flex-start;
    width: 85px;
}

	.delivery-modal-info-item-value {
    list-style: none;
    margin: 0;
    padding: 0;
    width: calc(100% - 85px);
}
</style>
</head>
<body>

<%
   UsersDto usersDto = (UsersDto)session.getAttribute("userInfo");
    if(usersDto == null) { // �α����� �ȵƴٸ�
%> 
	<script>
		alert("�α����� ���ּ���.");
		location.replace("login.jsp");
	</script>
<%} %>
<jsp:include page="top.jsp"/>


<%
	//ProductDto productDto = (ProductDto)request.getAttribute("productData");
	// int quantity = Integer.parseInt(request.getParameter("quantity")); */
%>


<% 
	Vector<ProductDto> v = (Vector<ProductDto>) request.getAttribute("ProductData");
	int count = Integer.parseInt(request.getParameter("count")); 
	//cart.jsp�� form�κ��� �޾ƿ� - cart.jsp���� CartOrderHandler, cartOrder.jsp���� �ϳ��� requestScope�� �ޱ� ����
	int sum=0;
	int quantity=0;
%>

<div class="checkout-full-box">
<div class="checkout-bg-color">
<div class="checkout-full-wrp">

	<!-- ��ܹڽ� ���� -->
	<div class="checkout-header-wrp">
		<div style="font-weight: bolder; font-size: xx-large; height: 50px;">���/����</div>
	</div>
	<!-- ��ܹڽ� �� -->
	<!-- �ϴܹڽ� ���� -->
	<div class="checkout-info-wrp">
		<!-- �ϴ������ڽ� ���� -->
		<div class="checkout-wrp-left">
			<!-- ���/�ֹ��� ���� ���� -->
			<div class="left-section-delivery-info left-section-box">
				<h3 class="f20-bd-333 mgb20">��� ����</h3>
				<div class="delivery-condition">
					<div class="delivery-condition-name">
						<h3 class="f25-bd-333" id="name">${userInfo.name}</h3>
					</div>
					<ul class="delivery-condition-info">
						<li class="f13-777 mgt10">${userInfo.address}</li>
						<li class="f13-777 mgt10">${userInfo.phone}</li>
					</ul>
					<h4 class="f15-bd-333 delivery-request">��� ��û����</h4>
					<input type="text" class="btn-v2 f13-555 btn-request" placeholder="�Է��� �ּ���."/>
				</div>
				<div class="delivery-user">
					<h3 class="f20-bd-333 mgb20">�ֹ��� ����</h3>
					<ul class="delivery-user-info">
						<li class="mgb10">
							<span class="info-type">�̸�</span>
							<strong class="info-value">${userInfo.name}</strong>
						</li>
						<li class="mgb10">
							<span class="info-type">����ó</span>
							<strong class="info-value">${userInfo.phone}</strong>
						</li>
						<li class="mgb10">
							<span class="info-type">�̸���</span>
							<strong class="info-value">${userInfo.email}</strong>
						</li>
					</ul>
				</div>
			</div>
			<!-- ���/�ֹ��� ���� �� -->
			<!-- ��� ��ǰ ���� -->
			<div class="section-order-info left-section-box">
				<h3 class="f20-bd-333 mgb20">��� ��ǰ</h3>
					<input type="hidden" class="check-out-box-prodId-for-js"/>
					<%for(int i=0;i<v.size();i++){ //���� ���ؾȵʾ�,,
						for(int j=1; j<count; j++){
							if(Integer.parseInt(request.getParameter("productNum"+j)) == (v.get(i).getProductNum())){
								quantity = Integer.parseInt(request.getParameter("p_num"+(j)));
							}
						}
					%>
					<ul class="order-prod-list">
						<li class="order-prod-info">
							<div class="order-prod-img">
								<a class="order-prod-img-a">
									<span class="order-prod-img-span">
										<img src="img/<%=v.get(i).getImage()%>">
									</span>
								</a>
							</div>
				
				
							<div class="order-prod-text">
								<strong class="prod-name f13-bd-333 mgb10"><%=v.get(i).getName()%></strong>
								<strong class="prod-price f18-bd-333">���� : <%=v.get(i).getPrice() %></strong>
								<strong class="prod-price f18-bd-333">���� : <%=quantity %></strong>
							</div>
						</li>
					</ul>
					<%
							sum += v.get(i).getPrice() * quantity;
						} 
					%>
				<div class="order-price-text">
					
					<strong class="f15-bd-purple">
						�հ� : <span class="price-unit"><%=sum%>��</span>
					</strong>
				</div>
			</div>
			<!-- ��� ��ǰ �� -->
			<!-- ���� ���� ���� -->
			<div class="section-payment-info left-section-box">
				<h3 class="section-payment-info-title f20-bd-333">��������</h3>
				<ul class="payment-type-list">
					<li class="payment-type-item mgb10">
						<input type="radio" name="pay-type-item" value="mutong" />
						<span class="mgl5">������ �Ա�</span>
					</li>
					<li class="payment-type-item mgb10">
						<input type="radio" name="pay-type-item" value="card" />
						<span class="mgl5">ī�����</span>
					</li>
				</ul>
			</div>
			<!-- ���� ���� �� -->
		</div>
		<!-- �ϴ������ڽ� �� -->
		<!-- �ϴܿ����ڽ� ���� -->
		<div class="checkout-wrp-right">
			<div class="right-section-info">
				<div class="right-section-price-info">
					<h3 class="mgb20 f20-bd-333">���� �ݾ�</h3>
					<ul class="expected-price-list">
						<li class="expected-price-item mgb10">
							<span class="f15-aaa expected-price-title">�� ��ǰ �ݾ�</span>
							<strong class="f18-333 expected-price-value"><%=sum%> ��</strong>
						</li>
					</ul>
					<p class="f15-aaa total-expected-price">
						<span class="f15-aaa total-expected-price-title">�� ���� ���� �ݾ�</span>
						<strong class="f18-333 expected-price-value"><%=sum%> ��</strong>
					</p>
					<form action="CartOrderConf.do" method="post">
						<%	
							int cartNum=0;
							int productNum=0;
							for(int i=1;i<count;i++){ 
								if(request.getParameter("buy"+i) != null){
									cartNum= Integer.parseInt(request.getParameter("buy"+i));
									quantity = Integer.parseInt(request.getParameter("p_num"+i));
									productNum = Integer.parseInt(request.getParameter("productNum"+i));
						%>
								<input type="hidden" name="cartNum<%=i %>" value="<%=cartNum %>">
								<input type="hidden" name="prodnum<%=i %>" value="<%=productNum %>">
								<input type="hidden" name="quantity<%=i %>" value="<%=quantity %>">
						<%
								}
							} %>
							<input type="hidden" name="count" value="<%=count %>">
						<button class="f18-bd-fff btn-order" type="submit">�ֹ� �Ϸ��ϱ�</button>
					</form>
					
				</div>
			</div>
		</div>
		<!-- �ϴܿ����ڽ� �� -->
	</div>
</div>
</div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>