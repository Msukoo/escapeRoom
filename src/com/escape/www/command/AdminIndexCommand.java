package com.escape.www.command;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.escape.www.dao.BookingDAO;
import com.escape.www.dao.UserDAO;
import com.escape.www.dto.BookingDTO;
import com.escape.www.dto.UserDTO;

public class AdminIndexCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd");
		Date date = new Date();
		String today = format1.format(date);
		
		ArrayList<BookingDTO> todayBookingList = new ArrayList<BookingDTO>();
		BookingDAO bookdao = BookingDAO.getBookingDAO();
		todayBookingList = bookdao.getTodayBookingList(today);
		
		ArrayList<UserDTO> todayuserList = new ArrayList<UserDTO>();
		UserDAO userdao = UserDAO.getUserDAO();
		todayuserList = userdao.getTodayUser(today);
		
		request.setAttribute("todayBookingList", todayBookingList);
		request.setAttribute("todayuserList", todayuserList);
	}

}
