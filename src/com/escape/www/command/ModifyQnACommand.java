package com.escape.www.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.escape.www.dao.QnADAO;
import com.escape.www.dto.QnADTO;

public class ModifyQnACommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		QnADTO qnadto = new QnADTO();
		QnADAO qnadao = QnADAO.getQnABoardDAO();
		int no = Integer.parseInt(request.getParameter("no"));
		qnadto = qnadao.getView(no);
		request.setAttribute("qnadto", qnadto);

	}

}
