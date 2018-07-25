package com;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

 
public class file_getPart extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8"); // ���ñ���
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		Collection<Part> parts = request.getParts();
		System.out.print(parts.size());
		for (Part part : parts) {
			if (part.getContentType() != null) {
				// ��ȡform-data; name="file"; filename="asp.NET.docx"
				String filename = part.getHeader("content-disposition");
				// ��ȡfilename
				filename = filename.substring(filename.indexOf("filename=\"")
						+ "filename=\"".length(), filename.length() - 1);
				// �ǿ��ж�
				if (filename != null && filename != "") {
					InputStream is = part.getInputStream();
					File file = new File("d:\\" + filename);
					FileOutputStream fos = new FileOutputStream(file);

					byte[] b = new byte[1024];
					int bcount = 0;// ����ȡ������
					bcount = is.read(b);
					while (bcount != -1) {
						fos.write(b, 0, bcount);
						bcount = is.read(b);
					}
					is.close();
					fos.close();
				}
			}
		}
	}

}
