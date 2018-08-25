package liberodark;

import java.io.File;

public class DirGenerator {
	
	public static File getDirectory(String appName) {
		String userHome = System.getProperty("user.home", ".");
		File appDir;
		
		OperatingSystem os = OperatingSystem.getCurrentPlatform();
		if (os.equals((Object)OperatingSystem.WINDOWS)) {
			String appDataDir = System.getenv("APPDATA");
			if (appDataDir != null) {
				appDir = new File(appDataDir, appName + "/");
			}
			else {
				appDir = new File(userHome, appName + "/");
			}
		}
		else {
			if (os.equals((Object)OperatingSystem.OSX)) {
				appDir = new File(userHome, "Library/Application Support/" + appName);
			}
			else {
				if (os.equals((Object)OperatingSystem.LINUX)) {
					appDir = new File(userHome, appName + "/");
				}
				else {
					appDir = new File(userHome, appName + "/");
				}
			}
		}
		
		if ((!appDir.exists()) && (!appDir.mkdirs())) {
			throw new RuntimeException("The working directory could not be created: " + appDir);
		}
		return appDir;
	}
}
