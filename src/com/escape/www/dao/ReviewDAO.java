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

import com.escape.www.dto.QnADTO;
import com.escape.www.dto.ReviewDTO;
import com.escape.www.dto.ReviewDTO;

public class ReviewDAO {
	private static ReviewDAO rdao = new ReviewDAO();
	private final String CONNECTION_POOL = "jdbc/escape";
	private final String TABLE_NAME = "review";
	private DataSource dataSource;
	private final int numOfContents = 20;
	private final String GET_MY_REVIEWS = "select * from " + TABLE_NAME + " where rev_id=? order by rev_no desc";
	private final String GET_ALL_REVIEWS = "select * from " + TABLE_NAME + " order by rev_no desc";
	private final String GET_LIST = "select * from " + TABLE_NAME + " order by rev_noti desc, rev_no desc limit ?, ?";
	private final String INSERT_REVIEW = "insert into " + TABLE_NAME + "(rev_thema, rev_title, rev_id, rev_contents, rev_noti) value(?, ?, ?, ?, ?)";
	private final String GET_NUM_OF_PAGE = "select count(*) as numOfPage from " + TABLE_NAME;
	private final String GET_NUM_OF_LOOKPAGE1 = "select count(*) as numOfPage from " + TABLE_NAME + " where rev_noti is null and rev_thema or like ?";
	private final String GET_NUM_OF_LOOKPAGE2 = "select count(*) as numOfPage from " + TABLE_NAME + " where rev_noti is null and (rev_title like ? or rev_contents like ?)";
	private final String GET_NUM_OF_LOOKPAGE3 = "select count(*) as numOfPage from " + TABLE_NAME + " where rev_noti is null and rev_id like ?";
	private final String GET_LOOKUP_LIST1 = "select * from " + TABLE_NAME + " where rev_noti is null and rev_thema like ? order by rev_no desc limit ?, ?";
	private final String GET_LOOKUP_LIST2 = "select * from " + TABLE_NAME + " where rev_noti is null and rev_title like ? or rev_contents like? order by rev_no desc limit ?, ?";
	private final String GET_LOOKUP_LIST3 = "select * from " + TABLE_NAME + " where rev_noti is null and rev_id like ? order by rev_no desc limit ?, ?";
	private final String GET_VIEW = "select * from "+ TABLE_NAME + " where rev_no=?";
	private final String UPDATE_HIT = "update " + TABLE_NAME + " set rev_hit=(rev_hit+1) where rev_no=?";
	private final String DELETE_VIEW = "delete from " + TABLE_NAME + " where rev_no=?";
	private final String MODIFY_VIEW = "update " + TABLE_NAME + " set rev_thema=?, rev_title=?, rev_contents=? where rev_no=?";
	
	private ReviewDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/" + CONNECTION_POOL);
		}catch(NamingException e) {
			e.printStackTrace();
		}
	}
	public static ReviewDAO getReviewBoardDAO() {
		return rdao;
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
	
	public ArrayList<ReviewDTO> getReviews() {  //모든리뷰
		ArrayList<ReviewDTO> list = new ArrayList<ReviewDTO>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(GET_ALL_REVIEWS);
			rs = ps.executeQuery();
			while(rs.next()) {
				ReviewDTO reviewdto = new ReviewDTO();
				reviewdto.setRev_no(rs.getInt("rev_no"));
				reviewdto.setRev_title(rs.getString("rev_title"));
				reviewdto.setRev_id(rs.getString("rev_id"));
				reviewdto.setRev_thema(rs.getString("rev_thema"));
				reviewdto.setRev_wtime(rs.getString("rev_wtime"));
				reviewdto.setRev_contents(rs.getString("rev_contents"));
				reviewdto.setRev_hit(rs.getInt("rev_hit"));
				reviewdto.setRev_noti(rs.getString("rev_noti"));
				list.add(reviewdto);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, ps, rs);
		}
		return list;
	}
	
	public ArrayList<ReviewDTO> getReviews(String id) { //내가 쓴리뷰
		ArrayList<ReviewDTO> list = new ArrayList<ReviewDTO>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(GET_MY_REVIEWS);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				ReviewDTO reviewdto = new ReviewDTO();
				reviewdto.setRev_no(rs.getInt("rev_no"));
				reviewdto.setRev_title(rs.getString("rev_title"));
				reviewdto.setRev_id(rs.getString("rev_id"));
				reviewdto.setRev_thema(rs.getString("rev_thema"));
				reviewdto.setRev_wtime(rs.getString("rev_wtime"));
				reviewdto.setRev_contents(rs.getString("rev_contents"));
				reviewdto.setRev_hit(rs.getInt("rev_hit"));
				reviewdto.setRev_noti(rs.getString("rev_noti"));
				list.add(reviewdto);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, ps, rs);
		}
		return list;
	}
	public void write(ReviewDTO revdto) { //글쓰기
		Connection conn = null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(INSERT_REVIEW);
			ps.setString(1, revdto.getRev_thema());
			ps.setString(2, revdto.getRev_title());
			ps.setString(3, revdto.getRev_id());
			ps.setString(4, revdto.getRev_contents());
			ps.setString(5, revdto.getRev_noti());
			result = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, ps);
		}
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
	
	public ArrayList<ReviewDTO> getListOfPage(int page) {
		ArrayList<ReviewDTO> list = new ArrayList<ReviewDTO>();
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
				ReviewDTO reviewdto = new ReviewDTO();
				reviewdto.setRev_no(rs.getInt("rev_no"));
				reviewdto.setRev_title(rs.getString("rev_title"));
				reviewdto.setRev_id(rs.getString("rev_id"));
				reviewdto.setRev_thema(rs.getString("rev_thema"));
				reviewdto.setRev_wtime(rs.getString("rev_wtime"));
				reviewdto.setRev_contents(rs.getString("rev_contents"));
				reviewdto.setRev_hit(rs.getInt("rev_hit"));
				reviewdto.setRev_noti(rs.getString("rev_noti"));
				list.add(reviewdto);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, ps, rs);
		}
		return list;
	}
	
	public ArrayList<ReviewDTO> getListOfPage(int page, String t, String q) {
		ArrayList<ReviewDTO> list = new ArrayList<ReviewDTO>();
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
				ReviewDTO revdto = new ReviewDTO();
				revdto.setRev_no(rs.getInt("rev_no"));
				revdto.setRev_title(rs.getString("rev_title"));
				revdto.setRev_id(rs.getString("rev_id"));
				revdto.setRev_thema(rs.getString("rev_thema"));
				revdto.setRev_wtime(rs.getString("rev_wtime"));
				revdto.setRev_contents(rs.getString("rev_contents"));
				revdto.setRev_hit(rs.getInt("rev_hit"));
				revdto.setRev_noti(rs.getString("rev_noti"));
				list.add(revdto);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, ps, rs);
		}
		return list;
	}
	public ReviewDTO getView(int no) {
		ReviewDTO revdto = new ReviewDTO();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(GET_VIEW);
			ps.setInt(1, no);
			rs = ps.executeQuery();
			if(rs.next()) {
				revdto.setRev_no(rs.getInt("rev_no"));
				revdto.setRev_title(rs.getString("rev_title"));
				revdto.setRev_id(rs.getString("rev_id"));
				revdto.setRev_thema(rs.getString("rev_thema"));
				revdto.setRev_wtime(rs.getString("rev_wtime"));
				revdto.setRev_contents(rs.getString("rev_contents"));
				revdto.setRev_hit(rs.getInt("rev_hit"));
				revdto.setRev_noti(rs.getString("rev_noti"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, ps, rs);
		}
		return revdto;
	}
	public ReviewDTO getViewForModify(int no) {
		ReviewDTO revdto = new ReviewDTO();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String contents = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(GET_VIEW);
			ps.setInt(1, no);
			rs = ps.executeQuery();
			if(rs.next()) {
				revdto.setRev_no(rs.getInt("rev_no"));
				revdto.setRev_title(rs.getString("rev_title"));
				revdto.setRev_id(rs.getString("rev_id"));
				revdto.setRev_thema(rs.getString("rev_thema"));
				revdto.setRev_wtime(rs.getString("rev_wtime"));
				contents = rs.getString("rev_contents").replace("<br>", "\r\n");
				revdto.setRev_contents(contents);
				revdto.setRev_hit(rs.getInt("rev_hit"));
				revdto.setRev_noti(rs.getString("rev_noti"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, ps, rs);
		}
		return revdto;
	}
	public void updateHit(int no) {
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
	public void modifyOk(ReviewDTO revdto) {
		Connection conn = null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(MODIFY_VIEW);
			ps.setString(1, revdto.getRev_thema());
			ps.setString(2, revdto.getRev_title());
			ps.setString(3, revdto.getRev_contents());
			ps.setInt(4, revdto.getRev_no());
			result = ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, ps);
		}
	}
	
}
