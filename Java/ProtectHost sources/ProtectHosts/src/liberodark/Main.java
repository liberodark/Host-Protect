package liberodark;

import java.io.File;
import java.io.IOException;

import fr.bestdevelop.suapp.SU;
import fr.bestdevelop.suapp.SuperUserApplication;

public class Main extends SuperUserApplication {
	
	private static ProtectHosts protectHosts;
	private static UpdateHost updateHost;
	
	public static void main(String[] args) {
		SU.run(new Main(), args);
	}
	
	public int run(String[] args) {
		File directory = DirGenerator.getDirectory(".SecuZer");
		protectHosts = new ProtectHosts(updateHost = new UpdateHost(protectHosts, directory));
		protectHosts.getLogger().info("main() called!");
		protectHosts.setSystemLookNFeel();
		try {
			protectHosts.start();
		} 
		catch (IOException ex) {
			ex.printStackTrace();
			protectHosts.getLogger().error(ex);
		}
		
		return 0;
	}
	
	public static ProtectHosts getProtectHosts() {
		return protectHosts;
	}
	
	public static UpdateHost getUpdateHost() {
		return updateHost;
	}
	
}
