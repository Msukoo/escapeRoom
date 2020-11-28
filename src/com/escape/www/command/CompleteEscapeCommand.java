package com.escape.www.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.escape.www.dao.BookingDAO;
import com.escape.www.dao.UserDAO;
import com.escape.www.dto.BookingDTO;

public class CompleteEscapeCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookingDTO bookdto = new BookingDTO();
		String id = request.getParameter("id");
		String status = request.getParameter("status");
		bookdto.setBook_no(request.getParameter("no"));
		bookdto.setBook_id(id);
		bookdto.setBook_status(status);
		
		BookingDAO bookdao = BookingDAO.getBookingDAO();
		bookdao.updateStatus(bookdto);
		
		UserDAO userdao = UserDAO.getUserDAO();
		userdao.modifyUserInfo(id, status);
		
	}

}
