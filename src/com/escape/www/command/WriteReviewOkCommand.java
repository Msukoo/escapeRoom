package com.escape.www.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.escape.www.dao.ReviewDAO;
import com.escape.www.dto.ReviewDTO;

public class WriteReviewOkCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReviewDTO revdto = new ReviewDTO();
		String contents = request.getParameter("contents");
		contents = contents.replace("\r\n","<br>");
		revdto.setRev_thema(request.getParameter("thema"));
		revdto.setRev_title(request.getParameter("title"));
		revdto.setRev_contents(contents);
		revdto.setRev_noti(request.getParameter("noti"));
		revdto.setRev_id(request.getParameter("id"));
		
		ReviewDAO revdao = ReviewDAO.getReviewBoardDAO();
		revdao.write(revdto);
	}

}
