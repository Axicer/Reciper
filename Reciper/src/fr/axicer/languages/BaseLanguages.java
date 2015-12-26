package fr.axicer.languages;

import java.io.File;

public enum BaseLanguages {
	
	FR(new File(BaseLanguages.class.getResource("resources/FR.lang").getFile())),
	EN(new File(BaseLanguages.class.getResource("resources/EN.lang").getFile()));
	
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
