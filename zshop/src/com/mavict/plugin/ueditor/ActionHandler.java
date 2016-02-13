package com.mavict.plugin.ueditor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionHandler {
	
	private static final Map<String,Action> ACTION_MAP = Collections.unmodifiableMap(Action.getActionMap());

	private final ConfigManager configManager;
	private final StorageManager storageManager;
	
	public ActionHandler(ConfigManager configManager) {
		this(configManager,new StorageManager(new LocalStorageService()));
	}

	public ActionHandler(ConfigManager configManager,StorageManager storageManager) {
		this.configManager = configManager;
		this.storageManager = storageManager;
	}
	
	public void handle(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.write(doHandleInternal(request));
		writer.close();
	}
	
	private String doHandleInternal(HttpServletRequest request) {
		String callbackName = request.getParameter("callback");
		if (callbackName != null) {
			if (!validCallbackName(callbackName)) {
				return resultToJson(new BaseResult(ActionState.ILLEGAL));
			}
			return callbackName+"("+doInvokeInternal(request)+");";
		} else {
			return doInvokeInternal(request);
		}
	}
	
	private String doInvokeInternal(HttpServletRequest request) {
		Result result = null;
		String actionType = request.getParameter("action");
		if (actionType == null || !ACTION_MAP.containsKey(actionType)) {
			result = new BaseResult(ActionState.INVALID_ACTION);
		} else if (configManager == null || !configManager.valid()) {
			result = new BaseResult(ActionState.CONFIG_ERROR);
		} else {
			Action action = ACTION_MAP.get(actionType);
			switch (action) {
				case CONFIG:
					return configManager.getAllConfigString();
				case UPLOAD_IMAGE:
				case UPLOAD_SCRAWL:
				case UPLOAD_VIDEO:
				case UPLOAD_FILE:
					result = storageManager.upload(configManager.getConfig(action), request);
					break;
				case CATCH_IMAGE:
					result = storageManager.capture(configManager.getConfig(action), request);	
					break;
				case LIST_IMAGE:
				case LIST_FILE:
					result = storageManager.list(configManager.getConfig(action), request);
					break;
			}
		}
		return resultToJson(result);
	}
	
	private String resultToJson(Result result) {
		StringBuilder builder = new StringBuilder();
		char[] chars = result.toJSONObject().toString().toCharArray();
		for (char ch : chars) {
			if (ch < 256) {
				builder.append(ch);
			} else {
				builder.append("\\u" + Integer.toHexString(ch & 0xffff));
			}
		}
		return builder.toString();
	}
	
	private boolean validCallbackName(String name) {
		if (name.matches( "^[a-zA-Z_]+[\\w0-9_]*$")) {
			return true;
		}
		return false;
	}
	
}