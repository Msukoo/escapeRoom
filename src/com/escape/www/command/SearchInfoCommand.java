package com.escape.www.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.escape.www.dao.UserDAO;
import com.escape.www.dto.UserDTO;

public class SearchInfoCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mode = request.getParameter("mode");
		UserDTO userdto = new UserDTO();
		if(mode.equals("1")) {
			userdto.setUser_name(request.getParameter("name"));
			userdto.setUser_pw(request.getParameter("pw"));
		}else if(mode.equals("2")){
			userdto.setUser_id(request.getParameter("id"));
			userdto.setUser_name(request.getParameter("name"));
		}
		UserDAO userdao = UserDAO.getUserDAO();
		String result = userdao.searchInfo( mode, userdto);
		
		request.setAttribute("mode", mode);
		request.setAttribute("result", result);
		
	}

}
