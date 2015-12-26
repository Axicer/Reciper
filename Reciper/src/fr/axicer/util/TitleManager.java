package fr.axicer.util;

import fr.axicer.Reciper;

public class TitleManager {

	public static String getTitle() {
		return Reciper.AppName+" v"+String.valueOf(Reciper.AppVersion);
	}
}
