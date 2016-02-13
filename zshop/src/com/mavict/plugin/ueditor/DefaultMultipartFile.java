/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mavict.plugin.ueditor;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import org.apache.commons.io.FileUtils;

@SuppressWarnings({"serial","resource"})
public class DefaultMultipartFile implements MultipartFile, Serializable {

	private String name;
	private String originalName;
	private String contentType;
	private File dfosFile;

	public DefaultMultipartFile(String name,InputStream input,String originalName,String contentType) throws IOException {
		this.name = name;
		File dfosFile = new File(FileUtils.getTempDirectory(), PathFormat.random());
		FileUtils.copyInputStreamToFile(input, dfosFile);
		this.dfosFile = dfosFile;
		this.contentType = contentType;
		this.originalName = originalName;
	}

	public String getName() {
		return name;
	}
	
	public String getExtension() {
		String originalFilename = getOriginalFilename();
		int pos = originalFilename.lastIndexOf(".");
		if (pos == -1) {
			return "";
		}
		return originalFilename.substring(pos);
	}

	public String getOriginalFilename() {
		String filename = this.originalName;
		if (filename == null) {
			// Should never happen.
			return "";
		}
		// check for Unix-style path
		int pos = filename.lastIndexOf("/");
		if (pos == -1) {
			// check for Windows-style path
			pos = filename.lastIndexOf("\\");
		}
		if (pos != -1)  {
			// any sort of path separator found
			return filename.substring(pos + 1);
		}
		else {
			// plain name
			return filename;
		}
	}

	public String getContentType() {
		return this.contentType;
	}

	public boolean isEmpty() {
		return (getSize() == 0);
	}

	public long getSize() {
		return fileExists()?dfosFile.length():0;
	}
	
	public File getStoreLocation() {
		return this.dfosFile;
	}
	
	private boolean fileExists() {
		return this.dfosFile!=null && dfosFile.exists();
	}

	public byte[] getBytes() throws IOException {
		return fileExists()?FileUtils.readFileToByteArray(this.dfosFile):new byte[0];
	}

	public InputStream getInputStream() throws IOException {
		return fileExists()?new FileInputStream(this.dfosFile):new ByteArrayInputStream(new byte[0]);
	}

	public String getStorageDescription() {
		return "at [" + dfosFile.getAbsolutePath() + "]";
	}

	public void transferTo(File dest) throws IOException, IllegalStateException {
		if (dest.exists() && !dest.delete()) {
			throw new IOException("Destination file [" + dest.getAbsolutePath() + "] already exists and could not be deleted");
		} 
		try {
			FileUtils.copyFile(dfosFile, dest);
		} catch (IOException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new IOException("Could not transfer to file: " + ex.getMessage());
		}
	}

	@Override
	public String toString() {
		return "DefaultMultipartFile [name=" + name + ", originalName="
				+ originalName + ", contentType=" + contentType + ", dfosFile="
				+ dfosFile + "]";
	}

}
