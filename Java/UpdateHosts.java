import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.swing.JOptionPane;

public class UpdateHosts {
  public static void main(String [] args) throws IOException, InterruptedException {
    if (JOptionPane.showConfirmDialog(null, "You want to protect your hosts ?", "Protect Hosts", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
      Path $sHostsPath = Paths.get("C:\\Windows\\System32\\drivers\\etc\\hosts");
      if (!Files.exists($sHostsPath)) {
        JOptionPane.showMessageDialog(null, "hosts absent", "error", JOptionPane.WARNING_MESSAGE);
      } else {
        Path $savedHosts = Paths.get("C:\\Windows\\System32\\drivers\\etc\\hosts.bak");
        if (!Files.exists($savedHosts)) {
          Files.copy($sHostsPath, $savedHosts);
        }
        Runtime.getRuntime().exec("sc config Dnscache start= disable").waitFor();
        Thread.sleep(100);
        Runtime.getRuntime().exec("net stop Dnscache").waitFor();
        Path $sFilePath = Paths.get("hosts.txt");
        URL url = new URL("http://yurfile.altervista.org/download.php?fid=L0NNUC9ob3N0cy50eHQ=");
        URLConnection connection = url.openConnection();
        connection.setUseCaches(false);
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
        try (InputStream in = connection.getInputStream()) {
          Files.copy(in, $sFilePath);
          JOptionPane.showMessageDialog(null, "New hosts is Updated", "Success", JOptionPane.INFORMATION_MESSAGE);
          Files.move($sFilePath, $sHostsPath, StandardCopyOption.REPLACE_EXISTING);
          Files.deleteIfExists(Paths.get("C:\\Windows\\System32\\drivers\\etc\\hosts.tmp"));
          Runtime.getRuntime().exec("ipconfig /flushdns").waitFor();
          JOptionPane.showMessageDialog(null, "New hosts is installed", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
      }
    }
  }
}
