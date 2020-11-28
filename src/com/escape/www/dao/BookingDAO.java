package com.escape.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.escape.www.dto.ReviewDTO;
import com.escape.www.dto.BookingDTO;

public class BookingDAO {
	private static BookingDAO bdao = new BookingDAO();
	private final String CONNECTION_POOL = "jdbc/escape";
	private final String TABLE_NAME = "booking";
	private DataSource dataSource;
	private final String GET_SORT_BOOKING_LIST = "select * from " + TABLE_NAME + " where book_thema=? and book_date=?";
	private final String INSERT_BOOKING = "insert into " + TABLE_NAME + "(book_no, book_date, book_thema, book_time, book_id) value(?, ?, ?, ?, ?)";
	private final String GET_MY_BOOKING_LIST = "select * from " + TABLE_NAME + " where book_id=? order by book_no desc";
	private final String GET_TODAY_BOOKING_LIST = "select * from " + TABLE_NAME + " where book_date=? order by book_time";
	private final String GET_ALL_BOOKING_LIST = "select * from " + TABLE_NAME + " order by book_no desc";
	private final String GET_SEARCH_BOOKING_LIST1 = "select * from " + TABLE_NAME + " where book_thema like ? order by book_no desc";
	private final String GET_SEARCH_BOOKING_LIST2 = "select * from " + TABLE_NAME + " where book_id like ? order by book_no desc";
	private final String UPDATE_STATUS = "update " + TABLE_NAME + " set book_status=? where book_no=? and book_id=?";
	private final String CANCEL_BOOKING = "update " + TABLE_NAME + " set book_status=? where book_no=?";
	
	private BookingDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/" + CONNECTION_POOL);
		}catch(NamingException e) {
			e.printStackTrace();
		}
	}
	public static BookingDAO getBookingDAO() {
		return bdao;
	}
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public void close(Connection conn, PreparedStatement ps, ResultSet rs){

		try {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close(Connection conn, PreparedStatement ps){
		try {
			if(ps != null) ps.close();
			if(conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<BookingDTO> sortListforBooking(String thema, String date) {
		ArrayList<BookingDTO> bookList = new ArrayList<BookingDTO>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(GET_SORT_BOOKING_LIST);
			ps.setString(1, thema);
			ps.setString(2, date);
			rs = ps.executeQuery();
			while(rs.next()) {
				BookingDTO bookingdto = new BookingDTO();
				bookingdto.setBook_no(rs.getString("book_no"));
				bookingdto.setBook_date(rs.getString("book_date"));
				bookingdto.setBook_thema(rs.getString("book_thema"));
				bookingdto.setBook_time(rs.getString("book_time"));
				bookingdto.setBook_id(rs.getString("book_id"));
				bookingdto.setBook_status(rs.getString("book_status"));
				bookList.add(bookingdto);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, ps, rs);
		}
		return bookList;
	}
	public void bookingOk(BookingDTO bookdto) {
		Connection conn=null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(INSERT_BOOKING);
			ps.setString(1, bookdto.getBook_no());
			ps.setString(2, bookdto.getBook_date());
			ps.setString(3, bookdto.getBook_thema());
			ps.setString(4, bookdto.getBook_time());
			ps.setString(5, bookdto.getBook_id());
			result = ps.executeUpdate();
			System.out.println(result);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, ps);
		}
	}
	public ArrayList<BookingDTO> getMyBookingList(String idSession) {
		ArrayList<BookingDTO> bookList = new ArrayList<BookingDTO>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(GET_MY_BOOKING_LIST);
			ps.setString(1, idSession);
			rs = ps.executeQuery();
			while(rs.next()) {
				BookingDTO bookingdto = new BookingDTO();
				bookingdto.setBook_no(rs.getString("book_no"));
				bookingdto.setBook_date(rs.getString("book_date"));
				bookingdto.setBook_thema(rs.getString("book_thema"));
				bookingdto.setBook_time(rs.getString("book_time"));
				bookingdto.setBook_id(rs.getString("book_id"));
				bookingdto.setBook_status(rs.getString("book_status"));
				bookList.add(bookingdto);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, ps, rs);
		}
		return bookList;
	}
	public ArrayList<BookingDTO> getBookingList() {
		ArrayList<BookingDTO> bookList = new ArrayList<BookingDTO>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(GET_ALL_BOOKING_LIST);
			rs = ps.executeQuery();
			while(rs.next()) {
				BookingDTO bookingdto = new BookingDTO();
				bookingdto.setBook_no(rs.getString("book_no"));
				bookingdto.setBook_date(rs.getString("book_date"));
				bookingdto.setBook_thema(rs.getString("book_thema"));
				bookingdto.setBook_time(rs.getString("book_time"));
				bookingdto.setBook_id(rs.getString("book_id"));
				bookingdto.setBook_status(rs.getString("book_status"));
				bookList.add(bookingdto);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, ps, rs);
		}
		return bookList;
	}
	public ArrayList<BookingDTO> getBookingList(String t, String q) {
		ArrayList<BookingDTO> bookList = new ArrayList<BookingDTO>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			if(t.equals("1")) {
				ps = conn.prepareStatement(GET_SEARCH_BOOKING_LIST1);
			}
			else if(t.equals("2")) {
				ps = conn.prepareStatement(GET_SEARCH_BOOKING_LIST2);
			}
			ps.setString(1, "%"+q+"%");
			rs = ps.executeQuery();
			while(rs.next()) {
				BookingDTO bookingdto = new BookingDTO();
				bookingdto.setBook_no(rs.getString("book_no"));
				bookingdto.setBook_date(rs.getString("book_date"));
				bookingdto.setBook_thema(rs.getString("book_thema"));
				bookingdto.setBook_time(rs.getString("book_time"));
				bookingdto.setBook_id(rs.getString("book_id"));
				bookingdto.setBook_status(rs.getString("book_status"));
				bookList.add(bookingdto);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, ps, rs);
		}
		return bookList;
	}
	public void updateStatus(BookingDTO bookdto) {
		Connection conn=null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(UPDATE_STATUS);
			ps.setString(1, bookdto.getBook_status());
			ps.setString(2, bookdto.getBook_no());
			ps.setString(3, bookdto.getBook_id());
			result = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, ps);
		}
	}
	public ArrayList<BookingDTO> getTodayBookingList(String today) {
		ArrayList<BookingDTO> bookList = new ArrayList<BookingDTO>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(GET_TODAY_BOOKING_LIST);
			ps.setString(1, today);
			rs = ps.executeQuery();
			while(rs.next()) {
				BookingDTO bookingdto = new BookingDTO();
				bookingdto.setBook_no(rs.getString("book_no"));
				bookingdto.setBook_date(rs.getString("book_date"));
				bookingdto.setBook_thema(rs.getString("book_thema"));
				bookingdto.setBook_time(rs.getString("book_time"));
				bookingdto.setBook_id(rs.getString("book_id"));
				bookingdto.setBook_status(rs.getString("book_status"));
				bookList.add(bookingdto);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, ps, rs);
		}
		return bookList;
	}
	public void cancelBooking(String no) {
		Connection conn=null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(CANCEL_BOOKING);
			ps.setString(1, "3");
			ps.setString(2, no);
			result = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, ps);
		}
		
	}
}
