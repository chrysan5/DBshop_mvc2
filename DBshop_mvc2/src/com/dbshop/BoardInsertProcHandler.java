package com.dbshop;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardInsertProcHandler implements DbshopHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String dir = request.getServletContext().getRealPath("/upload");
		File f = new File(dir);
		if(!f.exists()){// ���� �� ������ �������� �ʴ´ٸ�
			f.mkdir();
		}
		
		String path = dir;
		int maxSize = 10 * 1024 *1024;//10 �ް�
		String enc = "utf-8";
		DefaultFileRenamePolicy dfrp = new DefaultFileRenamePolicy();
		// ���ϸ� �ߺ�ó��
		
		MultipartRequest mrequest = new MultipartRequest(request,path,maxSize,enc,dfrp);// ���ε� ��
		
		String kind =mrequest.getParameter("kind");
		int price = Integer.parseInt(mrequest.getParameter("price"));
		String name =mrequest.getParameter("name");
		String contents =mrequest.getParameter("contents");
		String image =mrequest.getOriginalFileName("image");
		
		Dao dao = new Dao();
		dao.boardInsert(kind, price, name, contents, image);
		
		return "Board.do";
	}
}
