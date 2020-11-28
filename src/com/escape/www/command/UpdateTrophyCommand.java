package com.escape.www.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.escape.www.dao.BookingDAO;
import com.escape.www.dao.UserDAO;
import com.escape.www.dto.UserDTO;

public class UpdateTrophyCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		int num = Integer.parseInt(request.getParameter("trophy"));
		UserDTO userdto = new UserDTO();
		userdto.setUser_id(id);
		userdto.setUser_trophy(num);
		
		UserDAO userdao = UserDAO.getUserDAO();
		userdao.modifyTrophy(userdto);
	}

}
