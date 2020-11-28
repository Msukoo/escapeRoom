package com.escape.www.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.escape.www.dao.UserDAO;
import com.escape.www.dto.UserDTO;

public class ModifyUserInfoCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserDTO userdto = new UserDTO();
		userdto.setUser_email((String)session.getAttribute("idSession"));
		userdto.setUser_id(request.getParameter("id"));
		userdto.setUser_pw(request.getParameter("pw"));
		userdto.setUser_phone
			(request.getParameter("phone1").trim() + request.getParameter("phone2").trim() + request.getParameter("phone3").trim());
		userdto.setUser_prefer(request.getParameter("prefer"));
		
		UserDAO userdao = UserDAO.getUserDAO();
		userdao.modifyUserInfo(userdto);
		
	}

}
