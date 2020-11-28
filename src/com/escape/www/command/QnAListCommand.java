package com.escape.www.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.escape.www.dao.QnADAO;
import com.escape.www.dto.QnADTO;

public class QnAListCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String t = request.getParameter("t");
		String q = request.getParameter("q");
		int page = 0;
		if(request.getParameter("page")==null) {
			page=1;
		}else {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int numOfPage = 0;
		ArrayList<QnADTO> list = new ArrayList<QnADTO>();
		QnADAO qnadao = QnADAO.getQnABoardDAO();
		
		if(q==null||q=="") { //검색어가 없을경우
			numOfPage = qnadao.getNumOfPage();
			list = qnadao.getListOfPage(page);
		}
		else if(q!=null) { //있을경우
			q = q.trim();
			request.setAttribute("t", t);
			request.setAttribute("q", q);
			numOfPage = qnadao.getNumOfPage(t, q);
			list = qnadao.getListOfPage(page, t, q);
		}
		
		request.setAttribute("list", list);
		request.setAttribute("numOfPage", numOfPage);
	}

}
