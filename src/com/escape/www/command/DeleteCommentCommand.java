package com.escape.www.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.escape.www.dao.CommentDAO;
import com.escape.www.dao.ReviewDAO;

public class DeleteCommentCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		CommentDAO comt = CommentDAO.getCommentDAO();
		comt.delete(no);
	}

}
