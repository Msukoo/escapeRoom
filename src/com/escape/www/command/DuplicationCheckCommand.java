package com.escape.www.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.escape.www.dao.UserDAO;
import com.escape.www.dto.UserDTO;

public class DuplicationCheckCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDAO userdao = UserDAO.getUserDAO();
		String id = request.getParameter("id");
		boolean isDuplication = userdao.duplicationCheck(id);
		request.setAttribute("okId", id);
		request.setAttribute("isDuplication", isDuplication);
	}
}

