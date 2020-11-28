package com.escape.www.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.escape.www.dao.BookingDAO;
import com.escape.www.dto.BookingDTO;

public class GetBookingCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String t = request.getParameter("t");
		String q = request.getParameter("q");
		ArrayList<BookingDTO> bookingList = new ArrayList<BookingDTO>();
		BookingDAO bookdao = BookingDAO.getBookingDAO();
		if(q==null||q=="") { //검색어가 없을경우
			bookingList = bookdao.getBookingList();
		}
		else if(q!=null) { //있을경우
			q = q.trim();
			bookingList = bookdao.getBookingList(t, q);
			request.setAttribute("t", t);
			request.setAttribute("q", q);
		}
		request.setAttribute("bookingList", bookingList);
	}

}
