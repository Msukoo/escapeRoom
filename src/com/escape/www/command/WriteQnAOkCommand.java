package com.escape.www.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.escape.www.dao.QnADAO;
import com.escape.www.dto.QnADTO;

public class WriteQnAOkCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		QnADTO qnadto = new QnADTO();
		String contents = request.getParameter("contents");
		contents = contents.replace("\r\n","<br>");
		qnadto.setQa_title(request.getParameter("title"));
		qnadto.setQa_contents(contents);
		qnadto.setQa_noti(request.getParameter("noti"));
		qnadto.setQa_id(request.getParameter("id"));
		
		QnADAO qnadao = QnADAO.getQnABoardDAO();
		qnadao.writeQnA(qnadto);
		
	}

}
