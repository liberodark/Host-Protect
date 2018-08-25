package liberodark;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import fr.bestdevelop.ml.logger.LoggerLib;

public class ProtectHosts {
	public static final LoggerLib logger = new LoggerLib(Constants.APP_NAME);
	
	private UpdateHost updateHost;
	
	public ProtectHosts(UpdateHost updateHost) {
		this.updateHost = updateHost;
	}
	
	public void start() throws IOException {
		getLogger().setDirLogger(updateHost.getDirSecuZer());
		if (JOptionPane.showConfirmDialog(null, "You want to protect your hosts ?", "Protect Hosts", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
		getLogger().info(Constants.APP_NAME + " started on " + System.getProperty("os.name") + "(" + System.getProperty("os.arch") + ") version " + System.getProperty("os.version") + "...");
		OperatingSystem os = OperatingSystem.getCurrentPlatform();
			
		if (os.equals((Object)OperatingSystem.WINDOWS)) {
			try {
				if(updateHost.windows()) {
					getLogger().success("New hosts is installed");
					JOptionPane.showMessageDialog(null, "New hosts is installed", "Success", JOptionPane.INFORMATION_MESSAGE);
				}
			} 
			catch (IOException ex) {
				ex.printStackTrace();
				getLogger().error(ex);
				JOptionPane.showMessageDialog(null, "hosts absent", "Error", JOptionPane.ERROR_MESSAGE);
			} 
			catch (InterruptedException ex) {
				ex.printStackTrace();
				getLogger().error(ex);
				JOptionPane.showMessageDialog(null, "hosts absent", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
			
		if (os.equals((Object)OperatingSystem.OSX)) {
			try {
				if(updateHost.mac()) {
					getLogger().success("New hosts is installed");
					JOptionPane.showMessageDialog(null, "New hosts is installed", "Success", JOptionPane.INFORMATION_MESSAGE);
				}
			} 
			catch (IOException ex) {
				ex.printStackTrace();
				getLogger().error(ex);
				JOptionPane.showMessageDialog(null, "hosts absent", "Error", JOptionPane.ERROR_MESSAGE);
			} 
			catch (InterruptedException ex) {
				ex.printStackTrace();
				getLogger().error(ex);
				JOptionPane.showMessageDialog(null, "hosts absent", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
			
		if (os.equals((Object)OperatingSystem.LINUX)) {
			try {
				if(updateHost.linux()) {
					getLogger().success("New hosts is installed");
					JOptionPane.showMessageDialog(null, "New hosts is installed", "Success", JOptionPane.INFORMATION_MESSAGE);
				}
			} 
			catch (IOException ex) {
				ex.printStackTrace();
				getLogger().error(ex);
				JOptionPane.showMessageDialog(null, "hosts absent", "Error", JOptionPane.ERROR_MESSAGE);
			} 
			catch (InterruptedException ex) {
				ex.printStackTrace();
				getLogger().error(ex);
				JOptionPane.showMessageDialog(null, "hosts absent", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
			
		if (os.equals((Object)OperatingSystem.UNKNOWN)) {
			getLogger().error("This operating system is unknown or unsupported.");
			JOptionPane.showMessageDialog(null, "This operating system is unknown or unsupported.", Constants.APP_NAME, JOptionPane.ERROR_MESSAGE);
		}
		
		getLogger().save(new File(updateHost.getDirSecuZer(), "logs.txt"));
		
		}
	}
	
	public void setSystemLookNFeel() {
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException ex) {
        	getLogger().warning("Can't set the system look n feel : ", ex);
        }
        catch (InstantiationException ex) {
        	getLogger().warning("Can't set the system look n feel : ", ex);
        }
        catch (IllegalAccessException ex) {
        	getLogger().warning("Can't set the system look n feel : ", ex);
        }
        catch (UnsupportedLookAndFeelException ex) {
        	getLogger().warning("Can't set the system look n feel : ", ex);
        }
	}
	
	public LoggerLib getLogger() {
		return logger;
	}
}
