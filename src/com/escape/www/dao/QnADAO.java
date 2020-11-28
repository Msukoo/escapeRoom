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

import com.escape.www.dto.QnADTO;


public class QnADAO {
	private static QnADAO qdao = new QnADAO();
	private final String CONNECTION_POOL = "jdbc/escape";
	private final String TABLE_NAME = "QnA";
	private final int numOfContents = 20;
	private DataSource dataSource;
	private String INSERT_QnA = "insert into " + TABLE_NAME + "(qa_title, qa_id, qa_contents, qa_noti, qa_groupNum) value(?, ?, ?, ?, ?)";
	private final String GET_LIST = "select * from " + TABLE_NAME + " order by qa_noti desc, qa_groupNum desc, qa_stepNum asc limit ?, ?";
	private final String GET_NUM_OF_PAGE = "select count(*) as numOfPage from " + TABLE_NAME;
	private final String GET_NUM_OF_LOOKPAGE1 = "select count(*) as numOfPage from " + TABLE_NAME + " where qa_noti is null and qa_title like ?";
	private final String GET_NUM_OF_LOOKPAGE2 = "select count(*) as numOfPage from " + TABLE_NAME + " where qa_noti is null and (qa_title like ? or qa_contents like ?)";
	private final String GET_NUM_OF_LOOKPAGE3 = "select count(*) as numOfPage from " + TABLE_NAME + " where qa_noti is null and qa_id like ?";
	private final String GET_LOOKUP_LIST1 = "select * from " + TABLE_NAME + " where qa_noti is null and qa_title like ? order by qa_groupNum desc, qa_stepNum asc limit ?, ?";
	private final String GET_LOOKUP_LIST2 = "select * from " + TABLE_NAME + " where qa_noti is null and qa_title like ? or qa_contents like? order by qa_groupNum desc, qa_stepNum asc limit ?, ?";
	private final String GET_LOOKUP_LIST3 = "select * from " + TABLE_NAME + " where qa_noti is null and qa_id like ? order by qa_groupNum desc, qa_stepNum asc limit ?, ?";
	private final String GET_NEXT_NUM = "select MAX(qa_no)+1 as nextNum from " + TABLE_NAME;
	private final String GET_VIEW = "select * from "+ TABLE_NAME + " where qa_no=?";
	private final String UPDATE_HIT = "update " + TABLE_NAME + " set qa_hit=(qa_hit+1) where qa_no=?";
	private final String DELETE_VIEW = "delete from " + TABLE_NAME + " where qa_no=?";
	private final String MODIFY_VIEW = "update " + TABLE_NAME + " set qa_title=?, qa_contents=? where qa_no=?";
	private final String INSERT_REPLY = "insert into " + TABLE_NAME + " (qa_title, qa_id, qa_contents, qa_groupNum, qa_indentNum, qa_stepNum) values(?, ?, ?, ?, ?, ?)";
	private final String UPDATE_REPLY = "update " + TABLE_NAME + " set qa_stepNum=(qa_stepNum+1) where qa_groupNum=? and qa_stepNum>?";
	
	private QnADAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/" + CONNECTION_POOL);
		}catch(NamingException e) {
			e.printStackTrace();
		}
	}
	public static QnADAO getQnABoardDAO() {
		return qdao;
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
	
	public void writeQnA(QnADTO qnadto) {
		Connection conn=null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(INSERT_QnA);
			ps.setString(1, qnadto.getQa_title());
			ps.setString(2, qnadto.getQa_id());
			ps.setString(3, qnadto.getQa_contents());
			ps.setString(4, qnadto.getQa_noti());
			ps.setInt(5, getNextGroupNum());
			result = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, ps);
		}
	}
	
	public int getNextGroupNum() {
		int nextNum = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(GET_NEXT_NUM);
			rs = ps.executeQuery();
			if(rs.next()) {
				nextNum = rs.getInt("nextNum");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return nextNum;
	}
	
	public int getNumOfPage() {
		int numOfPage = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(GET_NUM_OF_PAGE);
			rs = ps.executeQuery();
			if(rs.next()) {
				numOfPage = rs.getInt("numOfPage")/numOfContents;
				numOfPage = (rs.getInt("numOfPage")%numOfContents)==0? numOfPage : numOfPage+1;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, ps, rs);
		}
		return numOfPage;
	}
	
	public int getNumOfPage(String t, String q) {
		int numOfPage = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			if(t.equals("1")) {
				ps = conn.prepareStatement(GET_NUM_OF_LOOKPAGE1);
				ps.setString(1, "%"+q+"%");
			}else if(t.equals("2")) {
				ps = conn.prepareStatement(GET_NUM_OF_LOOKPAGE2);
				ps.setString(1, "%"+q+"%");
				ps.setString(2, "%"+q+"%");
			}else if(t.equals("3")) {
				ps = conn.prepareStatement(GET_NUM_OF_LOOKPAGE3);
				ps.setString(1, "%"+q+"%");
			}
			rs = ps.executeQuery();
			if(rs.next()) {
				numOfPage = rs.getInt("numOfPage")/numOfContents;
				numOfPage = (rs.getInt("numOfPage")%numOfContents)==0? numOfPage : numOfPage+1;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, ps, rs);
		}
		return numOfPage;
	}
	
	public ArrayList<QnADTO> getListOfPage(int page) {
		ArrayList<QnADTO> list = new ArrayList<QnADTO>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(GET_LIST);
			ps.setInt(1, (page-1)*numOfContents);
			ps.setInt(2, numOfContents);
			rs = ps.executeQuery();
			while(rs.next()) {
				QnADTO qnadto = new QnADTO();
				qnadto.setQa_no(rs.getInt("qa_no"));
				qnadto.setQa_id(rs.getString("qa_id"));
				qnadto.setQa_title(rs.getString("qa_title"));
				qnadto.setQa_contents(rs.getString("qa_contents"));
				qnadto.setQa_hit(rs.getInt("qa_hit"));
				qnadto.setQa_wTime(rs.getString("qa_wTime"));
				qnadto.setQa_groupNum(rs.getInt("qa_groupNum"));
				qnadto.setQa_stepNum(rs.getInt("qa_stepNum"));
				qnadto.setQa_indentNum(rs.getInt("qa_indentNum"));
				qnadto.setQa_noti(rs.getString("qa_noti"));
				
				list.add(qnadto);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, ps, rs);
		}
		return list;
	}
	
	public ArrayList<QnADTO> getListOfPage(int page, String t, String q) {
		ArrayList<QnADTO> list = new ArrayList<QnADTO>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			if(t.equals("1")) {
				ps = conn.prepareStatement(GET_LOOKUP_LIST1);
				ps.setString(1, "%"+q+"%");
				ps.setInt(2, (page-1)*numOfContents);
				ps.setInt(3, numOfContents);
			}else if(t.equals("2")) {
				ps = conn.prepareStatement(GET_LOOKUP_LIST2);
				ps.setString(1, "%"+q+"%");
				ps.setString(2, "%"+q+"%");
				ps.setInt(3, (page-1)*numOfContents);
				ps.setInt(4, numOfContents);
			}else if(t.equals("3")) {
				ps = conn.prepareStatement(GET_LOOKUP_LIST3);
				ps.setString(1, "%"+q+"%");
				ps.setInt(2, (page-1)*numOfContents);
				ps.setInt(3, numOfContents);
			}
			rs = ps.executeQuery();
			while(rs.next()) {
				QnADTO qnadto = new QnADTO();
				qnadto.setQa_no(rs.getInt("qa_no"));
				qnadto.setQa_id(rs.getString("qa_id"));
				qnadto.setQa_title(rs.getString("qa_title"));
				qnadto.setQa_contents(rs.getString("qa_contents"));
				qnadto.setQa_hit(rs.getInt("qa_hit"));
				qnadto.setQa_wTime(rs.getString("qa_wTime"));
				qnadto.setQa_groupNum(rs.getInt("qa_groupNum"));
				qnadto.setQa_stepNum(rs.getInt("qa_stepNum"));
				qnadto.setQa_indentNum(rs.getInt("qa_indentNum"));
				qnadto.setQa_noti(rs.getString("qa_noti"));
				
				list.add(qnadto);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, ps, rs);
		}
		return list;
	}
	
	public QnADTO getView(int no) {
		QnADTO qnadto = new QnADTO();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(GET_VIEW);
			ps.setInt(1, no);
			rs = ps.executeQuery();
			if(rs.next()) {
				qnadto.setQa_no(rs.getInt("qa_no"));
				qnadto.setQa_id(rs.getString("qa_id"));
				qnadto.setQa_title(rs.getString("qa_title"));
				qnadto.setQa_contents(rs.getString("qa_contents"));
				qnadto.setQa_hit(rs.getInt("qa_hit"));
				qnadto.setQa_wTime(rs.getString("qa_wTime"));
				qnadto.setQa_groupNum(rs.getInt("qa_groupNum"));
				qnadto.setQa_stepNum(rs.getInt("qa_stepNum"));
				qnadto.setQa_indentNum(rs.getInt("qa_indentNum"));
				qnadto.setQa_noti(rs.getString("qa_noti"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, ps, rs);
		}
		return qnadto;
	}
	
	public void updateHit(int no) { //조회수 업데이트
		Connection conn = null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(UPDATE_HIT);
			ps.setInt(1, no);
			result = ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, ps);
		}
	}
	
	public void delete(int no) {  //게시물 삭제
		Connection conn = null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(DELETE_VIEW);
			ps.setInt(1, no);
			result = ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, ps);
		}
	}
	public void modifyOk(QnADTO qnadto) {
		Connection conn = null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(MODIFY_VIEW);
			ps.setString(1, qnadto.getQa_title());
			ps.setString(2, qnadto.getQa_contents());
			ps.setInt(3, qnadto.getQa_no());
			result = ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, ps);
		}
	}
	
	public void updateStepNum(QnADTO qnadto) {
		Connection conn = null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(UPDATE_REPLY);
			ps.setInt(1, qnadto.getQa_stepNum());
			ps.setInt(2, qnadto.getQa_groupNum());
			ps.setInt(3, qnadto.getQa_stepNum());
			result = ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, ps);
		}
	}
	
	public void replyOk(QnADTO qnadto) {
		Connection conn = null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(INSERT_REPLY);		
			ps.setString(1, qnadto.getQa_title());
			ps.setString(2, qnadto.getQa_id());
			ps.setString(3, qnadto.getQa_contents());
			ps.setInt(4, qnadto.getQa_groupNum());
			ps.setInt(5, qnadto.getQa_indentNum());
			ps.setInt(6, qnadto.getQa_stepNum());
			result = ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, ps);
		}
	}
			
}
