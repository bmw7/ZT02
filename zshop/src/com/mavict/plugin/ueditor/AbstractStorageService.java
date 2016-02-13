package com.mavict.plugin.ueditor;

/**
 * 
 * 本包admin.utils.ueditor是百度编辑器ueditor上传模块的配置部分。
 * 使用百度编辑器，只需将本包内的所有文件拷贝至项目,并将ueditor下的jsp文件夹内controller.jsp 按照本项目配置做相应调整(替换)即可
 * 
 * ———— 沧海软件(北京)有限公司  2015/8/30
 * 
 * */
public abstract class AbstractStorageService implements StorageService {

	public Result save(Config conf,MultipartFile file) {
		if (file==null) {
			return new BaseResult(ActionState.NOT_MULTIPART_CONTENT);
		}
		if (file.isEmpty()) {
			return new BaseResult(ActionState.NOTFOUND_UPLOAD_DATA);
		}
		long maxSize = conf.getMaxSize();
		if (file.getSize()>maxSize) {
			return new BaseResult(ActionState.MAX_SIZE);
		}
		if (!conf.containAllowType(file.getExtension())) {
			return new BaseResult(ActionState.NOT_ALLOW_FILE_TYPE);
		}
		return doSaveInternel(conf, file);
	}
	
	protected abstract Result doSaveInternel(Config conf,MultipartFile file);

	@Override
	public Result list(Config conf,int start) {
		return doListInternel(conf, start);
	}
	
	protected abstract Result doListInternel(Config conf,int start);
	
}
