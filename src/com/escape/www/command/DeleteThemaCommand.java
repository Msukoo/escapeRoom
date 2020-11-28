package com.escape.www.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.escape.www.dao.ThemaDAO;
import com.escape.www.dto.ThemaDTO;

public class DeleteThemaCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ThemaDAO themadao = ThemaDAO.getThemaDAO();
		ThemaDTO themadto = new ThemaDTO();
		int code = Integer.parseInt(request.getParameter("code"));
		themadao.deleteThema(code);
	}

}
