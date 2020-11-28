package com.escape.www.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.escape.www.dao.QnADAO;
import com.escape.www.dto.QnADTO;

public class ReplyQnAOkCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		QnADTO qnadto = new QnADTO();
		qnadto.setQa_id(request.getParameter("id"));
		qnadto.setQa_title(request.getParameter("title"));
		qnadto.setQa_contents(request.getParameter("contents"));
		qnadto.setQa_groupNum(Integer.parseInt(request.getParameter("gNum")));
		qnadto.setQa_indentNum(Integer.parseInt(request.getParameter("iNum"))+1);
		qnadto.setQa_stepNum(Integer.parseInt(request.getParameter("sNum"))+1);
		QnADAO bDAO = QnADAO.getQnABoardDAO();
		bDAO.updateStepNum(qnadto);
		bDAO.replyOk(qnadto);
	}

}
