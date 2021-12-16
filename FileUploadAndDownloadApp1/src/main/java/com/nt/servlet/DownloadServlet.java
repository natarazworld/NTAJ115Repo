package com.nt.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;


@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
	private static final String GET_PHOTO_PATH_BY_NO = "SELECT PHOTO_PATH FROM SERVLET_UPLOAD_CUSTOMER WHERE CNO=?";
	private static final String GET_RESUME_PATH_BY_NO = "SELECT RESUME_PATH FROM SERVLET_UPLOAD_CUSTOMER WHERE CNO=?";

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// check wheather request is coming from download resume or download photo
		// hyperlink or not
		int no = 0;
		String query = null;
		if (req.getParameter("pid") != null) {
			no = Integer.parseInt(req.getParameter("pid"));
			query = GET_PHOTO_PATH_BY_NO;
		} else if (req.getParameter("rid") != null) {
			no = Integer.parseInt(req.getParameter("rid"));
			query = GET_RESUME_PATH_BY_NO;
		}

		// Load jdbc driver class
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}

		try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
				PreparedStatement ps = con.prepareStatement(query)) {
			// set value to query parameter
			ps.setInt(1, no);
			// execute Query
			String FILE_PATH = null;
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next())
					FILE_PATH = rs.getString(1);

				// create InputStream pointing to File to be downloaded
				try (InputStream is = new FileInputStream(FILE_PATH);
						// create output stream pointing to response object
						OutputStream os = res.getOutputStream()) {
					// get the MIME type of file to be downloaded and make it response contentn type
					ServletContext sc = getServletContext();
					String mimeType = sc.getMimeType(FILE_PATH);
					mimeType = (mimeType != null) ? mimeType : "application/octet-stream"; // ternary operation
					// get the length of file to be downloaded and make it as response content
					// length
					File file = new File(FILE_PATH);
					res.setContentLengthLong(file.length());
					// Give instruction to browser to make the recieved response content as the
					// downloadable file
					res.setHeader("Content-Disposition", "attachment;fileName=" + file.getName());
					// copy File content to response object
					IOUtils.copy(is, os);

				} // try3
			} // try2
		} // try1
		catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
