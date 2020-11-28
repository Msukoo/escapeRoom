package com.escape.www.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.escape.www.dao.CommentDAO;
import com.escape.www.dao.ReviewDAO;
import com.escape.www.dto.CommentDTO;
import com.escape.www.dto.ReviewDTO;

public class ReviewViewCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReviewDTO revdto = new ReviewDTO();
		ReviewDAO revdao = ReviewDAO.getReviewBoardDAO();
		int no = Integer.parseInt(request.getParameter("no"));
		revdto = revdao.getView(no);
		revdao.updateHit(no);
		request.setAttribute("revdto", revdto);
		
		CommentDTO comtdto = new CommentDTO();
		CommentDAO comtdao = CommentDAO.getCommentDAO();
		ArrayList<CommentDTO> comtList = comtdao.getComment(no);
		request.setAttribute("comtList", comtList);
	}

}
