package com.dbshop;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd = request.getRequestURI();
		cmd = cmd.substring(cmd.lastIndexOf("/"));

		DbshopHandler handler = null;

		if (cmd.equals("/Board.do")) {
			handler = new BoardHandler();
		} else if (cmd.equals("/AnswerInsertProc.do")) {
	         handler = new AnswerInsertProcHandler();
	    } else if (cmd.equals("/AnswerDeleteProc.do")) {
	         handler = new AnswerDeleteProcHandler(); 
		} else if (cmd.equals("/BoardInsert.do")) { //BoardWrite을 BoardInsert로 바꿨음
			handler = new BoardInsertHandler();
		} else if (cmd.equals("/BoardInsertProc.do")) {
			handler = new BoardInsertProcHandler();
		} else if (cmd.equals("/BoardRead.do")) {
			handler = new BoardReadHandler();
		} else if (cmd.equals("/BoardUpadte.do")) {
			handler = new BoardUpadteHandler();
		} else if (cmd.equals("/BoardUpadteProc.do")) {
			handler = new BoardUpadteProcHandler();
		} else if (cmd.equals("/BoardDelete.do")) {
			handler = new BoardDeleteHandler();
		} else if (cmd.equals("/Cart.do")) {
			handler = new CartHandler(); //basket.jsp를 cart.jsp로 바꿨다
		} else if (cmd.equals("/CartProc.do")) {
			handler = new CartProcHandler();
		} else if (cmd.equals("/CartDelete.do")) {
			handler = new CartDeleteHandler();
		} else if (cmd.equals("/CartClear.do")) {
			handler = new CartClearHandler();
		} else if (cmd.equals("/CartOrder.do")) {
			handler = new CartOrderHandler();
		} else if (cmd.equals("/CartOrderConf.do")) {
			handler = new CartOrderConfHandler();
		} else if (cmd.equals("/CheckIdAjax.do")) {
			new CheckIdAjax(request, response);
		} else if (cmd.equals("/Delivery.do")) {
			handler = new DeliveryHandler();
		} else if (cmd.equals("/DeliveryProc.do")) {
			handler = new DeliveryProcHandler();
		} else if (cmd.equals("/DeliveryCancelProc.do")) {
			handler = new DeliveryCancelProcHandler();
		} else if (cmd.equals("/DeliverySel.do")) {
			handler = new DeliverySelHandler();
		} else if (cmd.equals("/DeliverySelCancel.do")) {
			handler = new DeliverySelCancelHandler();
		} else if (cmd.equals("/DeliveryOnly.do")) {
			handler = new DeliveryOnlyHandler();
		} else if (cmd.equals("/DeliveryReadyOnly.do")) {
			handler = new DeliveryReadyOnlyHandler();
		} else if (cmd.equals("/Join.do")) {
			handler = new JoinHandler();
		} else if (cmd.equals("/Login.do")) {
			handler = new LoginHandler();
		} else if (cmd.equals("/LoginProc.do")) {
			handler = new LoginProcHandler();
		} else if (cmd.equals("/Logout.do")) {
			handler = new LogoutHandler();
		} else if (cmd.equals("/MyPage.do")) {
			handler = new MyPageHandler();
		} else if (cmd.equals("/MainKind.do")) { //이건 메인을 분류별로 출력하는것
			handler = new MainKindHandler();
		} else if (cmd.equals("/Order.do")) {
			handler = new OrderHandler();
		} else if (cmd.equals("/OrderConf.do")) {
			handler = new OrderConfHandler();
		} else if (cmd.equals("/Orderchk.do")) {
			handler = new OrderchkHandler();
		} else if (cmd.equals("/QnaUpdate.do")) {
			handler = new QnaUpdateHandler();
		} else if (cmd.equals("/PwChk.do")) {
			handler = new PwChkHandler();
		} else if (cmd.equals("/ProductRead.do")) { 
			handler = new ProductReadHandler();
		} else if (cmd.equals("/QnaInsert.do")) { //QnaWrite를 QnaInsert로 바꿨음
			handler = new QnaInsertHandler();
		} else if (cmd.equals("/QnaInsertProc.do")) {
			handler = new QnaInsertProcHandler();
		} else if (cmd.equals("/QnaRead.do")) {
			handler = new QnaReadHandler();
		} else if (cmd.equals("/QnaUpdateProc.do")) {
			handler = new QnaUpdateProcHandler();
		} else if (cmd.equals("/QnaDelete.do")) {
			handler = new QnaDeleteHandler();
		} else if (cmd.equals("/Qna.do")) {
			handler = new QnaHandler();
		} else if (cmd.equals("/SelDeleteCart.do")) {
			handler = new SelDeleteCartHandler();
		} else if (cmd.equals("/Tos.do")) {
			handler = new TosHandler();
		} else if (cmd.equals("/UsersUpdate.do")) { 
			handler = new UsersUpdateHandler();
		} else if (cmd.equals("/UsersUpdateProc.do")) { //Editmember를 이걸로 바꿈
			handler = new UsersUpdateProcHandler();
		} else if (cmd.equals("/UsersDeleteProc.do")) { //Deletemember를 이걸로 바꿈
			handler = new UsersDeleteProcHandler();
		} else {
			handler = new MainHandler();
		}

		if (!cmd.equals("/CheckIdAjax.do")) {
			String viewPage = handler.process(request, response);
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}
	}

}