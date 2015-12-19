package fr.axicer.util;

import fr.axicer.main.Main;

public class TitleManager {

	public static String getTitle() {
		return Main.AppName+" "+String.valueOf(Main.AppVersion);
	}
}
