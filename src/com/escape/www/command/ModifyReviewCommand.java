package com.escape.www.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.escape.www.dao.ReviewDAO;
import com.escape.www.dao.ThemaDAO;
import com.escape.www.dto.ReviewDTO;
import com.escape.www.dto.ThemaDTO;

public class ModifyReviewCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReviewDTO revdto = new ReviewDTO();
		ReviewDAO revdao = ReviewDAO.getReviewBoardDAO();
		int no = Integer.parseInt(request.getParameter("no"));
		revdto = revdao.getViewForModify(no);
		request.setAttribute("revdto", revdto);
		
		ArrayList<ThemaDTO> themaList = new ArrayList<ThemaDTO>();
		ThemaDAO themadao = ThemaDAO.getThemaDAO();
		themaList = themadao.getAllThema();
		request.setAttribute("themaList", themaList);
	}

}
