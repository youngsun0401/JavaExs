<%@page import="java.util.Enumeration"%>
<%@page import="java.io.File"%>
<%@page import="service.BoardDAO"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	Enumeration files = null;
	MultipartRequest multipartRequest
		= new MultipartRequest(
				request, 
				"/home/SUN/eclipse-workspace/JSPstart/WebContent/images", // 업로드 주소 
				1024 * 1024 * 100,// maxSize: 이미지 최대 크기(바이트)
				"UTF-8",// encoding
				new DefaultFileRenamePolicy()
				);
	files = multipartRequest.getFileNames();
	String name = (String)files.nextElement();
	File test = multipartRequest.getFile(name);
	BoardDAO.get();
	BoardDAO.upload(test);

%>
