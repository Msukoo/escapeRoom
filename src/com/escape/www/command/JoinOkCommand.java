package com.escape.www.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.escape.www.dao.UserDAO;
import com.escape.www.dto.UserDTO;

public class JoinOkCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDTO userdto = new UserDTO();
		userdto.setUser_id(request.getParameter("id"));
		userdto.setUser_name(request.getParameter("name"));
		userdto.setUser_email(request.getParameter("email"));
		userdto.setUser_pw(request.getParameter("pw"));
		String phone = request.getParameter("phone1") + request.getParameter("phone2") + request.getParameter("phone3");
		userdto.setUser_phone(phone);
		userdto.setUser_prefer(request.getParameter("prefer"));
		
		UserDAO mdao = UserDAO.getUserDAO();
		mdao.joinOk(userdto);
		
	}

}
