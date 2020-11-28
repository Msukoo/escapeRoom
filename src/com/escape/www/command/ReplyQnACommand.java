package com.escape.www.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.escape.www.dao.QnADAO;
import com.escape.www.dto.QnADTO;

public class ReplyQnACommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		QnADAO qnadao = QnADAO.getQnABoardDAO();
		QnADTO qnadto = qnadao.getView(Integer.parseInt(request.getParameter("no")));
		request.setAttribute("qnadto", qnadto);
	}

}
