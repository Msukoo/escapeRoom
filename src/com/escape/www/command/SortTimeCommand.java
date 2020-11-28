package com.escape.www.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.escape.www.dao.BookingDAO;
import com.escape.www.dao.ThemaDAO;
import com.escape.www.dto.BookingDTO;
import com.escape.www.dto.ThemaDTO;

public class SortTimeCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String thema = request.getParameter("thema");
		String date = request.getParameter("date");
		
		ArrayList<BookingDTO> bookingList = new ArrayList<BookingDTO>();
		BookingDAO bookdao = BookingDAO.getBookingDAO();
		bookingList = bookdao.sortListforBooking(thema, date);
		
		request.setAttribute("bookingList", bookingList);		
		request.setAttribute("select1", thema);
		request.setAttribute("select2", date);
	}

}
