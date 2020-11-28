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

import com.escape.www.dto.CommentDTO;
import com.escape.www.dto.QnADTO;

public class CommentDAO {
	private static CommentDAO comtdao = new CommentDAO();
	private final String CONNECTION_POOL = "jdbc/escape";
	private final String TABLE_NAME = "comment";
	private DataSource dataSource;
	private String GET_COMMENT = "select * from " + TABLE_NAME + " where comt_revno=? order by comt_no desc";
	private String INSERT_COMMENT = "insert into " + TABLE_NAME + "(comt_id, comt_contents, comt_revno) value(?, ?, ?)";
	private String DELETE_COMMENT = "delete from " + TABLE_NAME + " where comt_no=?";
	private CommentDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/" + CONNECTION_POOL);
		}catch(NamingException e) {
			e.printStackTrace();
		}
	}
	public static CommentDAO getCommentDAO() {
		return comtdao;
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
	public ArrayList<CommentDTO> getComment(int no) {
		ArrayList<CommentDTO> list = new ArrayList<CommentDTO>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(GET_COMMENT);
			ps.setInt(1, no);
			rs = ps.executeQuery();
			while(rs.next()) {
				CommentDTO comtdto = new CommentDTO();
				comtdto.setComt_no(rs.getInt("comt_no"));
				comtdto.setComt_id(rs.getString("comt_id"));
				comtdto.setComt_contents(rs.getString("comt_contents"));
				comtdto.setComt_wtime(rs.getString("comt_wtime"));
				comtdto.setComt_revno(rs.getInt("comt_revno"));
				
				list.add(comtdto);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, ps, rs);
		}
		return list;
	}
	public void write(CommentDTO comtdto) {
		Connection conn = null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(INSERT_COMMENT);
			ps.setString(1, comtdto.getComt_id());
			ps.setString(2, comtdto.getComt_contents());
			ps.setInt(3, comtdto.getComt_revno());
			result = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, ps);
		}
	}
	public void delete(int no) {
		Connection conn = null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(DELETE_COMMENT);
			ps.setInt(1, no);
			result = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, ps);
		}
	}
	
	
	
}
