package com.escape.www.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.escape.www.dao.QnADAO;
import com.escape.www.dto.QnADTO;

public class ModifyQnAOkCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		QnADTO qnadto = new QnADTO();
		qnadto.setQa_no(Integer.parseInt(request.getParameter("no")));
		qnadto.setQa_title(request.getParameter("title"));
		qnadto.setQa_contents(request.getParameter("contents"));
		
		QnADAO qnadao = QnADAO.getQnABoardDAO();
		qnadao.modifyOk(qnadto);

	}

}
