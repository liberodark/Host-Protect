package fr.bestdevelop.suapp;

import java.util.logging.Logger;

import fr.bestdevelop.suapp.linux.LinuxSudo;
import fr.bestdevelop.suapp.mac.MacSudo;
import fr.bestdevelop.suapp.mac.MacSuperUserDetector;
import fr.bestdevelop.suapp.posix.PosixSudo;
import fr.bestdevelop.suapp.posix.PosixSuperUserDetector;
import fr.bestdevelop.suapp.win.WinSudo;
import fr.bestdevelop.suapp.win.WinSuperUserDetector;

public abstract class SuperUserApplication implements ISuperUserApplication {
	private final static Logger logger = Logger.getLogger(SuperUserApplication.class.getName());
	
	public final int sudo(String[] args) {
		String os = SU.getOS();
		
		ISudo sudo = null;
		
		if ("windows".equals(os)) {
			sudo = new WinSudo();
		} else if ("linux".equals(os)) {
			sudo = new LinuxSudo();
		} else if ("mac".equals(os)) {
			sudo = new MacSudo();
		} else {
			logger.warning(String.format("Unsupported platform '%s, falling back to posix'",os));
			sudo = new PosixSudo();
		}
		
		return sudo.sudo(args);
	}
	
	public final int sudo() {
		return sudo(new String[]{});
	}
	
	public final boolean isSuperUser() {
		
		String os = SU.getOS();
		
		ISuperUserDetector detector = null;
		
		if ("windows".equals(os)) {
			detector = new WinSuperUserDetector();
		} 
		else if ("linux".equals(os)) {
			detector = new PosixSuperUserDetector();
		} 
		else if ("mac".equals(os)) {
			detector = new MacSuperUserDetector();
		}
		
		if (null == detector) {
			throw new IllegalStateException(String.format("Unsupported operating system: %s",os));
		}
		
		return detector.isSuperUser();
	}
}
