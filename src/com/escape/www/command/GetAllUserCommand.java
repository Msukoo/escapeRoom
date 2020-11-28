package com.escape.www.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.escape.www.dao.UserDAO;
import com.escape.www.dto.BookingDTO;
import com.escape.www.dto.UserDTO;

public class GetAllUserCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String t = request.getParameter("t");
		String q = request.getParameter("q");
		ArrayList<UserDTO> userList = new ArrayList<UserDTO>();
		UserDAO userdao = UserDAO.getUserDAO();
		if(q==null||q=="") { //검색어가 없을경우
			userList = userdao.getAllUser();
		}
		else if(q!=null) { //있을경우
			q = q.trim();
			userList = userdao.getAllUser(t, q);
			request.setAttribute("t", t);
			request.setAttribute("q", q);
		}
		request.setAttribute("userList", userList);
	}

}
