package com.escape.www.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.escape.www.dao.BookingDAO;
import com.escape.www.dao.ThemaDAO;
import com.escape.www.dto.BookingDTO;

public class BookingOkCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String idSession = (String)session.getAttribute("idSession");

		String no1 = request.getParameter("date");
		no1 = no1.substring(2);
		no1 = no1.replace("-", "");
		
		BookingDTO bookdto = new BookingDTO();
		bookdto.setBook_no(no1 + request.getParameter("code") + request.getParameter("time"));
		bookdto.setBook_id(idSession);
		bookdto.setBook_date(request.getParameter("date"));
		bookdto.setBook_thema(request.getParameter("thema"));
		bookdto.setBook_time(request.getParameter("time"));
		
		BookingDAO bookdao = BookingDAO.getBookingDAO();
		bookdao.bookingOk(bookdto);
	}
}
