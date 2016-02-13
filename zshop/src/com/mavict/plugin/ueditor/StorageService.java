package com.mavict.plugin.ueditor;

public interface StorageService {

	public Result save(Config conf,MultipartFile file);
	
	public Result list(Config conf,int start);
	
}
