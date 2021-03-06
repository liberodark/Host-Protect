package liberodark;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.swing.JOptionPane;

public class UpdateHost {
	public ProtectHosts protectHosts;
	
	public File dirSecuZer;
	
	public UpdateHost(ProtectHosts protectHosts, File dirSecuZer) {
		this.protectHosts = protectHosts;
		this.dirSecuZer = dirSecuZer;
	}
	
	public boolean windows() throws IOException, InterruptedException {
		Path HostsPath = Paths.get("C:/Windows/System32/drivers/etc/hosts");
		
		if (!Files.exists(HostsPath)) {
			JOptionPane.showMessageDialog(null, "hosts absent", "error", JOptionPane.WARNING_MESSAGE);
		} 
		
		else {
			Path savedHosts = Paths.get("C:/Windows/System32/drivers/etc/hosts.bak");
			
			if (!Files.exists(savedHosts)) {
				Files.copy(HostsPath, savedHosts);
			}
			
			Runtime.getRuntime().exec("net stop Dnscache && sc config Dnscache start= disabled").waitFor();
			Path FilePath = Paths.get(dirSecuZer.getAbsolutePath() + "/hosts.txt");
			
			URL url = new URL("http://yurfile.altervista.org/download.php?fid=L0NNUC9ob3N0cy50eHQ=");
			URLConnection connection = url.openConnection();
			connection.setUseCaches(false);
			connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
			
			try (InputStream in = connection.getInputStream()) {
				Files.copy(in, FilePath);
				//protectHosts.getLogger().success("New hosts is Updated");
				JOptionPane.showMessageDialog(null, "New hosts is Updated", "Success", JOptionPane.INFORMATION_MESSAGE);
				Files.move(FilePath, HostsPath, StandardCopyOption.REPLACE_EXISTING);
				
				return true;
			}
		}
		
		return false;
	}
	
	public boolean mac() throws IOException, InterruptedException {
		Path HostsPath = Paths.get("/private/etc/hosts");
		
		if (!Files.exists(HostsPath)) {
			JOptionPane.showMessageDialog(null, "hosts absent", "error", JOptionPane.WARNING_MESSAGE);
		} 
		
		else {
			Path $savedHosts = Paths.get("/private/etc/hosts.bak");
			
			if (!Files.exists($savedHosts)) {
				Files.copy(HostsPath, $savedHosts);
			}
			
			Runtime.getRuntime().exec("sudo dscacheutil -flushcache;sudo killall -HUP mDNSResponder").waitFor();
			Path $sFilePath = Paths.get(dirSecuZer.getAbsolutePath() + "hosts.txt");
			
			URL url = new URL("http://yurfile.altervista.org/download.php?fid=L0NNUC9ob3N0cy50eHQ=");
			URLConnection connection = url.openConnection();
			connection.setUseCaches(false);
			connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
			
			try (InputStream in = connection.getInputStream()) {
				Files.copy(in, $sFilePath);
				protectHosts.getLogger().success("New hosts is Updated");
				Files.move($sFilePath, HostsPath, StandardCopyOption.REPLACE_EXISTING);
				
				return true;
			}
		}
		
		return false;
	}
	
	public boolean linux() throws IOException, InterruptedException {
		Path $sHostsPath = Paths.get("/etc/hosts");
		
		if (!Files.exists($sHostsPath)) {
			JOptionPane.showMessageDialog(null, "hosts absent", "error", JOptionPane.WARNING_MESSAGE);
		} 
		
		else {
			Path $savedHosts = Paths.get("/etc/hosts.bak");
			
			if (!Files.exists($savedHosts)) {
				Files.copy($sHostsPath, $savedHosts);
			}
			
			
			// Debian/Ubuntu
			Runtime.getRuntime().exec("sudo /etc/rc.d/init.d/nscd restart").waitFor();
			
			// Linux + systemd
			Runtime.getRuntime().exec("sudo systemctl restart network.service").waitFor();
			
			// Fedora / Arch Linux & d�riv�s utilisant Network Manager
			Runtime.getRuntime().exec("sudo systemctl restart NetworkManager.service").waitFor();
			
			// Arch Linux & d�riv�s utilisant Wicd
			Runtime.getRuntime().exec("sudo systemctl restart wicd.service").waitFor();
						
			Path $sFilePath = Paths.get(dirSecuZer.getAbsolutePath() + "hosts.txt");
			
			URL url = new URL("http://yurfile.altervista.org/download.php?fid=L0NNUC9ob3N0cy50eHQ=");
			URLConnection connection = url.openConnection();
			connection.setUseCaches(false);
			connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
			
			try (InputStream in = connection.getInputStream()) {
				Files.copy(in, $sFilePath);
				protectHosts.getLogger().success("New hosts is Updated");
				Files.move($sFilePath, $sHostsPath, StandardCopyOption.REPLACE_EXISTING);
				
				return true;
			}
		}
		
		return false;
	}
	
	public File getDirSecuZer() {
		return dirSecuZer;
	}
}
