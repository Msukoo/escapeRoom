package com.escape.www.command;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.escape.www.dao.ThemaDAO;
import com.escape.www.dto.ThemaDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class InsertThemaCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext application = request.getServletContext();
		final String FOLDER_NAME = "image/thema";
		String realPath = application.getRealPath(FOLDER_NAME); //넣을파일의 경로
		String path = application.getContextPath();
		//String directory = application.getRealPath("C:\\JavaStudy\\JSP\\ESCAPE_ROOM\\ESCAPE_ROOM\\WebContent\\image\\thema");
		int maxSize =  1024 * 1024 * 100;
		String encoding = "UTF-8";
		String fileName = null;
		String originFile = "";
		
		MultipartRequest multipartRequest = new MultipartRequest(request, realPath, maxSize, encoding, new DefaultFileRenamePolicy()); //form데이터객체
		Enumeration fileNames = multipartRequest.getFileNames();
		while(fileNames.hasMoreElements()){
			String parameter = (String)fileNames.nextElement();
			originFile = multipartRequest.getOriginalFileName(parameter);
			fileName = multipartRequest.getFilesystemName(parameter);
			
			if(!fileName.endsWith(".pdf")&& !fileName.endsWith(".jpg") && !fileName.endsWith(".JPG")){
				File file = new File(realPath + originFile);
				file.delete();
			}
		}
		ThemaDTO themadto = new ThemaDTO();
		themadto.setThem_name(multipartRequest.getParameter("name"));
		themadto.setThem_about(multipartRequest.getParameter("about"));
		themadto.setThem_level(multipartRequest.getParameter("level"));
		themadto.setThem_img(path + "/" + FOLDER_NAME + "/" + fileName );
		
		ThemaDAO themadao = ThemaDAO.getThemaDAO();
		themadao.insertThema(themadto);
	}

}
