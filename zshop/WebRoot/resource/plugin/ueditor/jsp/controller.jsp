<%@page import="com.mavict.plugin.ueditor.ConfigManager"%>
<%@page import="com.mavict.plugin.ueditor.ActionHandler"%>
<%@page import="com.mavict.plugin.ueditor.StorageManager"%>
<%@page import="com.mavict.plugin.ueditor.LocalStorageService"%>
<%@page import="java.io.FileInputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%
	request.setCharacterEncoding("UTF-8");
	ActionHandler actionHandler = null;
	String cachedKey = "ueditorActionHandler";
	Object cachedActionHandler = application.getAttribute(cachedKey);
	if (cachedActionHandler instanceof ActionHandler) {
		actionHandler = (ActionHandler)cachedActionHandler;
	} else {
		String contextPath = request.getContextPath();
		String rootPath = request.getRealPath("/");
		String currentPath = request.getRequestURI();
		if (contextPath.length()>0) {
			currentPath = currentPath.replaceFirst(contextPath, "/");
		}
		int lastPost = currentPath.lastIndexOf("/",currentPath.length()-1);
		if (lastPost>=0) {
			currentPath = currentPath.substring(0,lastPost);
		}
		String configPath = request.getRealPath(currentPath+"/config.json");
		ConfigManager configManager = new ConfigManager(rootPath,contextPath,new FileInputStream(configPath));
		StorageManager storageManager = new StorageManager(new LocalStorageService());
		actionHandler = new ActionHandler(configManager,storageManager);
		application.setAttribute(cachedKey, actionHandler);
	}
	actionHandler.handle(request,response);
%>