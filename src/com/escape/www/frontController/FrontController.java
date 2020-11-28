package com.escape.www.frontController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.escape.www.command.*;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}
	
	public void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		Command command = null;
		String viewPage = null;
		String commandName = request.getServletPath();
		boolean direct = false;
		int no = 0;
		
		//로그인마이페이지
		if(commandName.contains("/joinOk.do")) {
			command = new JoinOkCommand();
			command.excute(request, response);
			viewPage = "login.do";
			direct = true;
		}else if(commandName.contains("/idSearch.do")) {
			viewPage = "escape_user/idSearch.jsp";
		}else if(commandName.contains("/join.do")) {
			viewPage = "escape_user/join.jsp";
		}else if(commandName.contains("/duplicationCheck.do")) {
			viewPage = "escape_user/duplicationCheck.jsp";
		}else if(commandName.contains("/duplicationCheckOk.do")) { //중복확인창
			command = new DuplicationCheckCommand();
			command.excute(request, response);
			viewPage = "duplicationCheckOk.jsp";
		}else if(commandName.contains("/useId.do")) { //아이디사용
			viewPage = "escape_user/join.jsp";
		}else if(commandName.contains("/login.do")) {
			viewPage = "escape_user/login.jsp";
		}else if(commandName.contains("/loginOk.do")) {
			command = new LoginOkCommand();
			command.excute(request, response);
			viewPage = "escape_user/login.jsp";
		}else if(commandName.contains("/logout.do")) {
			command = new LogoutCommand();
			command.excute(request, response);
			viewPage = "index.jsp";
		}else if(commandName.contains("/searchInfo.do")) { //아이디비번찾기
			viewPage = "escape_user/searchInfo.jsp";
		}else if(commandName.contains("/searchInfoOk.do")) { //아이디,비번찾기 ㄱ
			command = new SearchInfoCommand();
			command.excute(request, response);
			viewPage = "escape_user/searchInfoResult.jsp";
		}else if(commandName.contains("/mypage.do")) {
			command = new getUserCommand();
			command.excute(request, response);
			viewPage = "escape_user/mypage.jsp";
		}else if(commandName.contains("/mypage_booking.do")) {
			command = new GetMyBookingCommand();
			command.excute(request, response);
			viewPage = "escape_user/mypage_booking.jsp";
		}else if(commandName.contains("/modifyUserInfo.do")) {
			command = new ModifyUserInfoCommand();
			command.excute(request, response);
			viewPage = "mypage.do";
		}else if(commandName.contains("/mypage_review.do")) {
			command = new GetMyReviewCommand();
			command.excute(request, response);
			viewPage = "escape_user/mypage_review.jsp";
		}else if(commandName.contains("/cancelBooking.do")) { //예약취소
			command = new CancelBookingCommand();
			command.excute(request, response);
			viewPage = "mypage_booking.do";
			direct = true;
		}
		
		
		
		//QnA페이지
		else if(commandName.contains("/QnA.do")) {
			command = new QnAListCommand();
			command.excute(request, response);
			viewPage = "escape_user/QnA.jsp";
		}else if(commandName.contains("/writeQnA.do")) {
			viewPage = "escape_user/writeQnA.jsp";
		}else if(commandName.contains("/writeQnAOk.do")) {
			command = new WriteQnAOkCommand();
			command.excute(request, response);
			viewPage = "QnA.do";
			direct = true;
		}else if(commandName.contains("/QnAview.do")) {
			command = new QnAViewCommand();
			command.excute(request, response);
			viewPage = "escape_user/QnAView.jsp";
		}else if(commandName.contains("/deleteQnA.do")) {
			command = new DeleteQnACommand();
			command.excute(request, response);
			viewPage = "QnA.do";
		}else if(commandName.contains("/modifyQnA.do")) {
			command = new ModifyQnACommand();
			command.excute(request, response);
			viewPage = "escape_user/modifyQnA.jsp";
		}else if(commandName.contains("/modifyQnAOk.do")) {
			command = new ModifyQnAOkCommand();
			command.excute(request, response);
			viewPage = "QnAview.do?no="+request.getParameter("no");
		}else if(commandName.contains("/replyQnA.do")) {
			command = new ReplyQnACommand();
			command.excute(request, response);
			viewPage = "escape_user/replyQnA.jsp";
		}else if(commandName.contains("/replyQnAOk.do")) {
			command = new ReplyQnAOkCommand();
			command.excute(request, response);
			viewPage = "QnA.do";
			direct = true;
		}
		
		//리뷰페이지
		else if(commandName.contains("/review.do")) {
			command = new ReviewListCommand();
			command.excute(request, response);
			viewPage = "escape_user/review.jsp";
		}else if(commandName.contains("/writeReview.do")) {
			command = new GetThemaCommand();
			command.excute(request, response);
			viewPage = "escape_user/writeReview.jsp";
		}else if(commandName.contains("/writeReviewOk.do")) {
			command = new WriteReviewOkCommand();
			command.excute(request, response);
			viewPage = "review.do";
			direct = true;
		}else if(commandName.contains("/reviewView.do")) {
			command = new ReviewViewCommand();
			command.excute(request, response);
			viewPage = "escape_user/reviewView.jsp";
		}else if(commandName.contains("/deleteReview.do")) {
			command = new DeleteReviewCommand();
			command.excute(request, response);
			viewPage = "review.do";
		}else if(commandName.contains("/modifyReview.do")) {
			command = new ModifyReviewCommand();
			command.excute(request, response);
			viewPage = "escape_user/modifyReview.jsp";
		}else if(commandName.contains("/modifyReviewOk.do")) {
			command = new ModifyReviewOkCommand();
			command.excute(request, response);
			viewPage = "reviewView.do?no=" + request.getParameter("no");
		}else if(commandName.contains("/writeComt.do")) {
			command = new writeCommentOkCommand();
			command.excute(request, response);
			viewPage = "reviewView.do?no=" + request.getParameter("rNo");
			direct = true;
		}else if(commandName.contains("/deleteComt.do")) {
			command = new DeleteCommentCommand();
			command.excute(request, response);
			viewPage = "reviewView.do?no="+ request.getParameter("rNo");
		}
		
		
		//예약페이지
		else if(commandName.contains("/booking.do")) {
			command = new GetThemaCommand();
			command.excute(request, response);
			viewPage = "escape_user/booking.jsp";
		}else if(commandName.contains("/sortTime.do")) {
			command = new SortTimeCommand();
			command.excute(request, response);
			viewPage = "booking.do";
		}else if(commandName.contains("/bookingOk.do")) {
			command = new BookingOkCommand();
			command.excute(request, response);
			viewPage = "mypage_booking.do";
			direct = true;
		}
		
		//테마페이지
		else if(commandName.contains("/thema.do")) {
			command = new GetThemaCommand();
			command.excute(request, response);
			viewPage = "escape_user/thema.jsp";
		}
		
		
		//관리자페이지
		else if(commandName.contains("/admin_index.do")) {
			command = new AdminIndexCommand();
			command.excute(request, response);
			viewPage = "escape_admin/admin_index.jsp";
		}
		else if(commandName.contains("/admin_booking.do")) {
			command = new GetBookingCommand();
			command.excute(request, response);
			viewPage = "escape_admin/admin_booking.jsp";
		}else if(commandName.contains("/complete.do")) {
			command = new CompleteEscapeCommand();
			command.excute(request, response);
			viewPage = "admin_booking.do";
			direct = true;
		}else if(commandName.contains("/admin_user.do")) {
			command = new GetAllUserCommand();
			command.excute(request, response);
			viewPage = "escape_admin/admin_user.jsp";
		}else if(commandName.contains("/admin_them.do")) {
			command = new GetThemaCommand();
			command.excute(request, response);
			viewPage = "escape_admin/admin_them.jsp";
		}else if(commandName.contains("/inserThema.do")) {
			viewPage = "escape_admin/admin_insertThema.jsp";
		}else if(commandName.contains("/insertThemaOk.do")) {
			command = new InsertThemaCommand();
			command.excute(request, response);
			viewPage = "admin_them.do";
			direct = true;
		}else if(commandName.contains("/modifyTrophy.do")) {
			command = new UpdateTrophyCommand();
			command.excute(request, response);
			viewPage = "admin_user.do";
		}else if(commandName.contains("/modifyThema.do")) {
			command = new ModifyThemaCommand();
			command.excute(request, response);
			viewPage = "escape_admin/admin_modifyThema.jsp";
		}else if(commandName.contains("/modifyThemaOk.do")) {
			command = new ModifyThemaOkCommand();
			command.excute(request, response);
			viewPage = "admin_them.do";
			direct = true;
		}else if(commandName.contains("/deleteThema.do")) {
			command = new DeleteThemaCommand();
			command.excute(request, response);
			viewPage = "admin_them.do";
			direct = true;
		}
		
		
		
		if(!direct) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}else {
			response.sendRedirect(viewPage);
		}
	}

}
