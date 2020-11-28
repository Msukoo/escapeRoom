package com.escape.www.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.escape.www.dao.BookingDAO;
import com.escape.www.dao.UserDAO;
import com.escape.www.dto.BookingDTO;
import com.escape.www.dto.UserDTO;

public class GetMyBookingCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String idSession = (String)session.getAttribute("idSession");
		UserDTO userdto = new UserDTO();
		UserDAO userdao = UserDAO.getUserDAO();
		userdto = userdao.getUser(idSession);
		request.setAttribute("userdto", userdto);
		
		ArrayList<BookingDTO> myBookingList = new ArrayList<BookingDTO>();
		BookingDAO bookdao = BookingDAO.getBookingDAO();
		myBookingList = bookdao.getMyBookingList(idSession);
		request.setAttribute("myBookingList", myBookingList);
	}

}
