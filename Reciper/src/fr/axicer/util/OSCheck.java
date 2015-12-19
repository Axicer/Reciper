package fr.axicer.util;

public class OSCheck {
	public static OSEnum getOS(){
		String os = System.getProperty("os.name").toLowerCase();
		if(os.contains("win")){
			return OSEnum.WINDOWS;
		}else if(os.contains("mac")){
			return OSEnum.MAC;
		}else if(os.contains("linux")){
			return OSEnum.LINUX;
		}else if(os.contains("sol")){
			return OSEnum.SOLARIS;
		}
		return OSEnum.OTHER;
	}
}
