package com.escape.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.*;

import com.escape.www.dto.BookingDTO;
import com.escape.www.dto.ThemaDTO;
import com.escape.www.dto.UserDTO;

public class ThemaDAO {
	private static ThemaDAO thdao = new ThemaDAO();
	private final String CONNECTION_POOL = "jdbc/escape";
	private final String TABLE_NAME = "thema";
	private DataSource dataSource;
	private final String GET_ALL_THEMA = "select * from " + TABLE_NAME;
	private final String INSERT_THEMA = "insert into " + TABLE_NAME + "(them_name, them_about, them_level, them_img) value(?, ?, ?, ?)";
	private final String GET_THEMA = "select * from " + TABLE_NAME + " where them_code=?";
	private final String DELETE_THEMA = "delete from " + TABLE_NAME + " where them_code=?";
	private final String MODIFY_THEMA = "update " + TABLE_NAME + " set them_name=?, them_about=?, them_level=?, them_img=? where them_code=?";
	
	private ThemaDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/" + CONNECTION_POOL);
		}catch(NamingException e) {
			e.printStackTrace();
		}
	}
	public static ThemaDAO getThemaDAO() {
		return thdao;
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
	public ArrayList<ThemaDTO> getAllThema() {
		ArrayList<ThemaDTO> themaList = new ArrayList<ThemaDTO>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(GET_ALL_THEMA);
			rs = ps.executeQuery();
			while(rs.next()) {
				ThemaDTO themadto = new ThemaDTO();
				themadto.setThem_name(rs.getString("them_name"));
				themadto.setThem_about(rs.getString("them_about"));
				themadto.setThem_level(rs.getString("them_level"));
				themadto.setThem_code(rs.getInt("them_code"));
				themadto.setThem_img(rs.getString("them_img"));
				
				themaList.add(themadto);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, ps, rs);
		}
		return themaList;
	}
	/*public String getThemaCode(String thema) {
		String code = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(GET_THEMACODE);
			rs = ps.executeQuery();
			while(rs.next()) {
				ThemaDTO themadto = new ThemaDTO();
				themadto.setThem_no(rs.getInt("them_no"));
				themaList.add(themadto);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, ps, rs);
		}
		return code;
	}	*/
	public void insertThema(ThemaDTO themadto) {
		Connection conn=null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(INSERT_THEMA);
			ps.setString(1, themadto.getThem_name());
			ps.setString(2, themadto.getThem_about());
			ps.setString(3, themadto.getThem_level());
			ps.setString(4, themadto.getThem_img());
			result = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, ps);
		}
	}
	public ThemaDTO modifyThema(int code) {
		ThemaDTO themadto = new ThemaDTO();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(GET_THEMA);
			ps.setInt(1, code);
			rs = ps.executeQuery();
			if(rs.next()) {
				themadto.setThem_name(rs.getString("them_name"));
				themadto.setThem_about(rs.getString("them_about"));
				themadto.setThem_level(rs.getString("them_level"));
				themadto.setThem_code(rs.getInt("them_code"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, ps, rs);
		}
		return themadto;
	}
	public void deleteThema(int code) {
		Connection conn = null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(DELETE_THEMA);
			ps.setInt(1, code);
			result = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, ps);
		}
	}
	public void modifyThemaOk(ThemaDTO themadto) {
		Connection conn = null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(MODIFY_THEMA);
			ps.setString(1, themadto.getThem_name());
			ps.setString(2, themadto.getThem_about());
			ps.setString(3, themadto.getThem_level());
			ps.setString(4, themadto.getThem_img());
			ps.setInt(5, themadto.getThem_code());
			result = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, ps);
		}
	}
	
			
}
