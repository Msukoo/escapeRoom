package com.escape.www.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.escape.www.dao.UserDAO;
import com.escape.www.dto.UserDTO;

public class LoginOkCommand implements Command{
	
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		UserDTO userdto = new UserDTO();
		String id = request.getParameter("id");
		userdto.setUser_id(id);
		userdto.setUser_pw(request.getParameter("pw"));
		UserDAO userdao = UserDAO.getUserDAO();
		boolean isExistUser = userdao.ExistUserCheck(userdto);
		if(isExistUser) {
			HttpSession session = request.getSession();
			session.setAttribute("idSession", id);
			request.setAttribute("login", true);
		}else {
			request.setAttribute("login", false);
		}
	}
}
