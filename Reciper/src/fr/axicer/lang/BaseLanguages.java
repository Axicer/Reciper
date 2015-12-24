package fr.axicer.lang;

import java.io.File;

public enum BaseLanguages {
	
	FR(new File(BaseLanguages.class.getResource("base/FR.lang").getFile())),
	EN(new File(BaseLanguages.class.getResource("base/EN.lang").getFile()));
	
	private File file;
	
	private BaseLanguages(File f) {
		setFile(f);
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
}
