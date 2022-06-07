package com.dbshop;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BoardReadHandler implements DbshopHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//============ ��ȸ�� �߰� ================================================(�̰� �𸣰ڴ�! �� ��Ű������)
		Cookie[] cookie = request.getCookies(); // ����� ��Ű �ҷ�����
		String cookieValue = null;
		for(int i=0; i<cookie.length; i++) { // ��û�����κ��� ��Ű�� �����´�
			cookieValue = cookie[0].getValue();
		}
		
		int idx = Integer.parseInt(request.getParameter("productNum"));
		
		// session = �۹�ȣ(idx) + ��Ű�� ����
		HttpSession session = request.getSession(); //������ ������ �ҷ�������, ������ ������
		if(session.getAttribute(idx+":cookie") == null) { //���ǿ� ��Ű���� ���� ��� 
			session.setAttribute(idx+":cookie", idx+":"+cookieValue); //���� ����
		}else { //���ǿ� ��Ű���� �ִ� ��� 
			session.setAttribute(idx+":cookie ex", session.getAttribute(idx+":cookie")); 
			if(!session.getAttribute(idx+":cookie").equals(idx+":"+cookieValue)) {
				session.setAttribute(idx+":cookie", idx+":"+cookieValue);
			}
		}
		
		ProductDto productDto = new ProductDto();
		productDto.setProductNum(idx);
		
		
		Dao dao = new Dao();
		
		// ��ȸ�� ī��Ʈ ó��(��Ű ó�� ����)
		//if(!session.getAttribute(idx+":cookie").equals(session.getAttribute(idx+":cookie ex"))) {
			dao.ReadHit(productDto); // "update product set hits=hits+1 where productNum=?";
		//}
		//==============��ȸ�� �߰� �Ϸ�===============================================
			
			
		int productNum = Integer.parseInt(request.getParameter("productNum"));
		//productNum�� idx���� productNum���� �ٲ���
		
		Vector<ProductDto> v = dao.ReadProduct(productNum); //����� ���Ͱ��� ������ �Ƹ��� ���� ���ε�� ������...
		request.setAttribute("BoardReadData", v);
		
		return "/boardRead.jsp";
	}

}
