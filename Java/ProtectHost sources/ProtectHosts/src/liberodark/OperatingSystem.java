package liberodark;

import java.io.File;

public enum OperatingSystem {
	LINUX("linux", "linux", "unix"),
	WINDOWS("windows", "win"),
	OSX("osx", "mac"),
	UNKNOWN("unknown", new String[0]);
	
	private final String name;
    private final String[] aliases;
    
    private OperatingSystem(String name, String ... aliases) {
    	this.name = name;
        this.aliases = aliases == null ? new String[]{} : aliases;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String[] getAliases() {
        return this.aliases;
    }
    
    public boolean isSupported() {
        return this != UNKNOWN;
    }
    
    public String getJavaDir() {
    	String separator = System.getProperty("file.separator");
        String path = System.getProperty("java.home") + separator + "bin" + separator;
        if (OperatingSystem.getCurrentPlatform() == WINDOWS && new File(path + "javaw.exe").isFile()) {
            return path + "javaw.exe";
        }
        
        return path + "java";
    }
    
    public static OperatingSystem getCurrentPlatform() {
    	String osName = System.getProperty("os.name").toLowerCase();
        for (OperatingSystem os : OperatingSystem.values()) {
            for (String alias : os.getAliases()) {
                if (!osName.contains(alias)) continue;
                return os;
            }
        }
        
        return UNKNOWN;
    }
}
