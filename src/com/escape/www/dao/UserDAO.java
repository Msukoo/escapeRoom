package com.escape.www.dao;

import java.sql.*;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.*;

import com.escape.www.dto.UserDTO;

public class UserDAO {
	private static UserDAO userdto = new UserDAO();
	private final String CONNECTION_POOL = "jdbc/escape";
	private final String TABLE_NAME = "user";
	private DataSource dataSource;	
	private final String INSERT_USER = "insert into " + TABLE_NAME + " (user_email, user_name, user_id, user_pw, user_phone, user_prefer) value(?, ?, ?, ?, ?, ?)";	
	private final String EXIST_USER_CHECK = "select * from " + TABLE_NAME + " where user_id=? and user_pw=?";
	private final String GET_USER = "select * from " + TABLE_NAME + " where user_id=?";
	private final String GET_TODAY_USER = "select * from " + TABLE_NAME + " where user_jDate like ?";
	private final String MODIFY_USER_INFO = "update " + TABLE_NAME + " set user_email=?, user_pw=?, user_phone=?, user_prefer=? where user_id=?";
	private final String MODIFY_USER_TROPHY = "update " + TABLE_NAME + " set user_trophy=? where user_id=?";
	private final String MODIFY_USER_TROPHY1 = "update " + TABLE_NAME + " set user_trophy=(user_trophy+2) where user_id=?";
	private final String MODIFY_USER_TROPHY2 = "update " + TABLE_NAME + " set user_trophy=(user_trophy+1) where user_id=?";
	private final String DUPLICATION_CHECK = "select * from " + TABLE_NAME + " where user_id=?";
	private final String GET_All_USER = "select * from " + TABLE_NAME + " order by user_jDate desc";
	private final String GET_SEARCH_USER1 = "select * from " + TABLE_NAME + " where user_id like ? order by user_jDate desc";
	private final String GET_SEARCH_USER2 = "select * from " + TABLE_NAME + " where user_name like ? order by user_jDate desc";	
	private final String SEARCH_ID = "select user_id from " + TABLE_NAME + " where user_name=? and user_pw=?";
	private final String SEARCH_PW = "select user_pw from " + TABLE_NAME + " where user_id=? and user_name=?";

	private UserDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/" + CONNECTION_POOL);
		}catch(NamingException e) {
			e.printStackTrace();
		}
	}
	public static UserDAO getUserDAO() {
		return userdto;
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
	
	public void joinOk(UserDTO userdto) { //회원가입
		Connection conn=null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(INSERT_USER);
			ps.setString(1, userdto.getUser_email());
			ps.setString(2, userdto.getUser_name());
			ps.setString(3, userdto.getUser_id());
			ps.setString(4, userdto.getUser_pw());
			ps.setString(5, userdto.getUser_phone());
			ps.setString(6, userdto.getUser_prefer());
			result = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, ps);
		}
	}
	public boolean ExistUserCheck(UserDTO userdto) { //로그인
		Connection conn=null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean isExistUser = false;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(EXIST_USER_CHECK);
			ps.setString(1, userdto.getUser_id());
			ps.setString(2, userdto.getUser_pw());
			rs = ps.executeQuery();
			if(rs.next()) {
				isExistUser = true;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, ps, rs);
		}
		return isExistUser;
	}
	public UserDTO getUser(String idSession) {
		UserDTO userdto = new UserDTO();
		Connection conn=null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(GET_USER);
			ps.setString(1, idSession);
			rs = ps.executeQuery();
			if(rs.next()) {
				userdto.setUser_name(rs.getString("user_name"));
				userdto.setUser_email(rs.getString("user_email"));
				userdto.setUser_id(rs.getString("user_id"));
				userdto.setUser_pw(rs.getString("user_pw"));
				userdto.setUser_phone(rs.getString("user_phone"));
				userdto.setUser_prefer(rs.getString("user_prefer"));
				userdto.setUser_trophy(rs.getInt("user_trophy"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, ps, rs);
		}
		return userdto;
	}
	public void modifyUserInfo(UserDTO userdto) {
		Connection conn=null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(MODIFY_USER_INFO);
			ps.setString(1, userdto.getUser_email());
			ps.setString(2, userdto.getUser_pw());
			ps.setString(3, userdto.getUser_phone());
			ps.setString(4, userdto.getUser_prefer());
			ps.setString(5, userdto.getUser_id());
			result = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, ps);
		}
	}
	public boolean duplicationCheck(String id) {
		Connection conn=null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean isExistUser = false;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(DUPLICATION_CHECK);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				isExistUser = true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, ps, rs);
		}
		return isExistUser;
	}
	public void modifyUserInfo(String id, String status) {
		Connection conn=null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			conn = getConnection();
			if(status.equals("1")) {
				ps = conn.prepareStatement(MODIFY_USER_TROPHY1);
			}
			else if(status.equals("2")){
				ps = conn.prepareStatement(MODIFY_USER_TROPHY2);
			}
			ps.setString(1, id);
			result = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, ps);
		}
	}
	public void modifyTrophy(UserDTO userdto) {
		Connection conn=null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(MODIFY_USER_TROPHY);
			ps.setInt(1, userdto.getUser_trophy());
			ps.setString(2, userdto.getUser_id());
			result = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, ps);
		}
	}
	public ArrayList<UserDTO> getAllUser() {
		ArrayList<UserDTO> userList = new ArrayList<UserDTO>();
		Connection conn=null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(GET_All_USER);
			rs = ps.executeQuery();
			while(rs.next()) {
				UserDTO userdto = new UserDTO();
				userdto.setUser_name(rs.getString("user_name"));
				userdto.setUser_email(rs.getString("user_email"));
				userdto.setUser_id(rs.getString("user_id"));
				userdto.setUser_pw(rs.getString("user_pw"));
				userdto.setUser_phone(rs.getString("user_phone"));
				userdto.setUser_prefer(rs.getString("user_prefer"));
				userdto.setUser_trophy(rs.getInt("user_trophy"));
				userdto.setUser_jDate(rs.getString("user_jDate"));
				userList.add(userdto);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, ps, rs);
		}
		return userList;
	}
	
	public ArrayList<UserDTO> getAllUser(String t, String q) {
		ArrayList<UserDTO> userList = new ArrayList<UserDTO>();
		Connection conn=null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			if(t.equals("1")) {
				ps = conn.prepareStatement(GET_SEARCH_USER1);
			}
			else if(t.equals("2")) {
				ps = conn.prepareStatement(GET_SEARCH_USER2);
			}
			ps.setString(1, "%"+q+"%");
			rs = ps.executeQuery();
			while(rs.next()) {
				UserDTO userdto = new UserDTO();
				userdto.setUser_name(rs.getString("user_name"));
				userdto.setUser_email(rs.getString("user_email"));
				userdto.setUser_id(rs.getString("user_id"));
				userdto.setUser_pw(rs.getString("user_pw"));
				userdto.setUser_phone(rs.getString("user_phone"));
				userdto.setUser_prefer(rs.getString("user_prefer"));
				userdto.setUser_trophy(rs.getInt("user_trophy"));
				userdto.setUser_jDate(rs.getString("user_jDate"));
				userList.add(userdto);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, ps, rs);
		}
		return userList;
	}
	public String searchInfo(String mode, UserDTO userdto) {
		Connection conn=null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String result = null;
		try {
			conn = getConnection();
			if(mode.equals("1")) {
				ps = conn.prepareStatement(SEARCH_ID);
				ps.setString(1, userdto.getUser_name());
				ps.setString(2, userdto.getUser_pw());
				rs = ps.executeQuery();
				if(rs.next()) {
					result = rs.getString("user_id");
				}
			}else if(mode.equals("2")) {
				ps = conn.prepareStatement(SEARCH_PW);
				ps.setString(1, userdto.getUser_id());
				ps.setString(2, userdto.getUser_name());
				rs = ps.executeQuery();
				if(rs.next()) {
					result = rs.getString("user_pw");
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, ps, rs);
		}
			
		return result;
	}
	public ArrayList<UserDTO> getTodayUser(String today) {
		ArrayList<UserDTO> userList = new ArrayList<UserDTO>();
		Connection conn=null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(GET_TODAY_USER);
			ps.setString(1, "%"+today+"%");
			rs = ps.executeQuery();
			while(rs.next()) {
				UserDTO userdto = new UserDTO();
				userdto.setUser_name(rs.getString("user_name"));
				userdto.setUser_email(rs.getString("user_email"));
				userdto.setUser_id(rs.getString("user_id"));
				userdto.setUser_pw(rs.getString("user_pw"));
				userdto.setUser_phone(rs.getString("user_phone"));
				userdto.setUser_prefer(rs.getString("user_prefer"));
				userdto.setUser_trophy(rs.getInt("user_trophy"));
				userdto.setUser_jDate(rs.getString("user_jDate"));
				userList.add(userdto);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, ps, rs);
		}
		return userList;
	}
	
	
	
		
		
	
			
}
