<%@page import="com.dbshop.ProductDto"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>

    <style>
        *,html,body{
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        #wrap{
            width: 1024px;
            font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Robonto,
             Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
            margin: 0 auto;
            display: flex;
            justify-content: space-between;
        }
        #wrap>div{
            border: 1px solid black;
        }


        #leftImg{
            width: 500px;
            height: 600px;
        }
        #leftImg img{
            width: 100%;
            height: 100%;
        }
        #rightMain{
            width: 500px;
            height: 600px;
        }
        #rightMain input[type="button"]{
        	display: inline-block;
        }
        #num > *{
        	display: inline-block;
        	font-size: 20px;
            padding-bottom: 10px;
        }
        #num input[type="button"]{
        	width: 25px;
        	height: 25px;
		    border: 1px solid black;
        	background-color:white;
            cursor: pointer;
            border-radius: 3px;
            box-shadow: 2px 2px 2px 1px gainsboro;
        }
        .prReadBtn{
        	padding: 10px;
		    background-color:#282828;  
		    color: white;  
        }
        #title{
            width: 100%;
        	font-size: 23px;
            height: 40px;
            background-color: #f9f9f9; 
            margin-bottom: 15px;
        }
        #price{
            margin-bottom: 15px;
            width: 100%;
            font-size: 18px;
            font-weight: bold;
            height: 40px;
            background-color: #f9f9f9; 
        }
        #spec{
            width: 100%;
            color: rgb(214, 214, 214);
            height: 150px;
            background-color: #f9f9f9;
            margin-bottom: 15px;
        }
        #content{
        	width:100%;
        	height: 170px;
            background: #f9f9f9; 
            margin-bottom: 15px;
        }
        .specDiv1{
            width: 100px;
            margin-bottom: 5px;
            display: inline-block;
        }
        .specDiv2{
            display: inline-block;
            width: 390px;
        }
        input[type="submit"]{
            width: 240px;
            height: 50px;
            margin-left: 5px;
            cursor: pointer;
            border-radius: 3px;
            font-weight: bold;
        }
        #num{
            border-bottom: 3px dotted black;
            padding-bottom: 25px;
            margin-bottom: 25px;
        }
    </style>
</head>
<body>
<%
	ProductDto productDto = (ProductDto)request.getAttribute("productData");
%>

<jsp:include page="top.jsp"/>
	<div id="wrap">
        <div id="leftImg">
            <img src="img/<%=productDto.getImage()%>">
        </div>
		<form name="frm">
        <div id="rightMain">
            <div id="title"><%=productDto.getName() %></div>
            <div id="price"><%=productDto.getPrice() %></div>
            <div id="spec">
                <div class="specDiv1">������</div><div class="specDiv2"> 430�� ���� </div>
                <div class="specDiv1">ī������</div><div class="specDiv2"> ������ �Һ� ī�� �ȳ� </div>
                <div class="specDiv1">��ۺ�</div><div class="specDiv2"> 2,500��(50,000�� �̻� ���� �� ����)</div>
                <div class="specDiv1"></div><div class="specDiv2">��ۺ� �����ǰ ����> </div>
            </div>
            <div id="content"><%=productDto.getContents() %></div>
            <div id="num"><div>���� : </div>
            	&nbsp&nbsp<input type="button" id="downBtn" value="-" onclick='count("minus")'>
            	&nbsp
            	<input type="hidden" name="quantity" id="quantity">
            	<div id="result" name="num"> 1 </div>
            	&nbsp
            	<input type="button" id="upBtn" value="+" onclick='count("plus")'>
            </div>
            <input type="submit" value="��ٱ���" class="prReadBtn"  onclick="cart()">
            <input type="submit" value="����" class="prReadBtn" onclick="order()">
            <input type="hidden" name="productNum" value="<%=productDto.getProductNum() %>">
        </div>
        </form>
    </div>
    
    
    <script>
    //�ϳ��� form�� submit�� 2�� �̻��̶� ��ũ��Ʈ�� cart()�� order()���� �ּҸ� ������
    function cart() {
    	const total = document.getElementById("result");
		document.getElementById("quantity").value = total.innerText;
		frm.action="CartProc.do";
	}
    function order() {
    	const total = document.getElementById("result");
		document.getElementById("quantity").value = total.innerText;
		frm.action="Order.do";
	}
    
    
    //���� +,- �ϱ�
    function count(type)  {
        // ����� ǥ���� element
        const resultElement = document.getElementById('result');
        
        // ���� ȭ�鿡 ǥ�õ� ��
        let number = resultElement.innerText;
        
        // ���ϱ�� ����
        if(type === 'plus') {
            number = parseInt(number) + 1;
        }else if(type === 'minus')  {
        	if(number>1){ //���̳ʽ� �� ������
        		number = parseInt(number) - 1;	
        	}
        }
        // ��� ���
        resultElement.innerText = number;
    }

    
    </script>
    <jsp:include page="footer.jsp"/>
</body>
</html>