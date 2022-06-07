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
		
		//============ 조회수 추가 ================================================(이거 모르겠다! 왜 쿠키만들지)
		Cookie[] cookie = request.getCookies(); // 저장된 쿠키 불러오기
		String cookieValue = null;
		for(int i=0; i<cookie.length; i++) { // 요청정보로부터 쿠키를 가져온다
			cookieValue = cookie[0].getValue();
		}
		
		int idx = Integer.parseInt(request.getParameter("productNum"));
		
		// session = 글번호(idx) + 쿠키로 구성
		HttpSession session = request.getSession(); //세션이 있으면 불러와지고, 없으면 생성됨
		if(session.getAttribute(idx+":cookie") == null) { //세션에 쿠키정보 없는 경우 
			session.setAttribute(idx+":cookie", idx+":"+cookieValue); //세션 생성
		}else { //세션에 쿠키정보 있는 경우 
			session.setAttribute(idx+":cookie ex", session.getAttribute(idx+":cookie")); 
			if(!session.getAttribute(idx+":cookie").equals(idx+":"+cookieValue)) {
				session.setAttribute(idx+":cookie", idx+":"+cookieValue);
			}
		}
		
		ProductDto productDto = new ProductDto();
		productDto.setProductNum(idx);
		
		
		Dao dao = new Dao();
		
		// 조회수 카운트 처리(쿠키 처리 삭제)
		//if(!session.getAttribute(idx+":cookie").equals(session.getAttribute(idx+":cookie ex"))) {
			dao.ReadHit(productDto); // "update product set hits=hits+1 where productNum=?";
		//}
		//==============조회수 추가 완료===============================================
			
			
		int productNum = Integer.parseInt(request.getParameter("productNum"));
		//productNum을 idx에서 productNum으로 바꿔줌
		
		Vector<ProductDto> v = dao.ReadProduct(productNum); //결과가 벡터값인 이유는 아마도 파일 업로드기 때문에...
		request.setAttribute("BoardReadData", v);
		
		return "/boardRead.jsp";
	}

}
