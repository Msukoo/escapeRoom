package com.escape.www.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.escape.www.dao.ReviewDAO;
import com.escape.www.dao.UserDAO;
import com.escape.www.dto.ReviewDTO;
import com.escape.www.dto.UserDTO;

public class GetMyReviewCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String idSession = (String)session.getAttribute("idSession");
		ArrayList<ReviewDTO> myReviewList = new ArrayList<ReviewDTO>();
		
		ReviewDAO reviewdao = ReviewDAO.getReviewBoardDAO(); 
		myReviewList = reviewdao.getReviews(idSession);
		request.setAttribute("myReviewList", myReviewList);
		
		UserDTO userdto = new UserDTO(); //회원정보
		UserDAO userdao = UserDAO.getUserDAO();
		userdto = userdao.getUser(idSession);
		request.setAttribute("userdto", userdto);
	}

}

