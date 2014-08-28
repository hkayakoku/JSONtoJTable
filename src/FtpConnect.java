import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FtpConnect {

	public boolean checkConnection(String server, int port, String user,
			String pass) {

		FTPClient ftpClient = new FTPClient();
		boolean isConnected = false;

		try {
			ftpClient.connect(server, port);
			ftpClient.login(user, pass);
			ftpClient.enterLocalPassiveMode();

			int reply = ftpClient.getReplyCode();

			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
				String message = "FTP connection failed for " + server;
				JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
						JOptionPane.ERROR_MESSAGE);
			} else {
				String message = "Baglanti Kuruldu";
				JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
						JOptionPane.INFORMATION_MESSAGE);
				isConnected = true;
			}

		} catch (IOException ex) {
			System.out.println("Error: " + ex.getMessage());
			String message = "Baglanti Kurulamadi ";
			JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
					JOptionPane.ERROR_MESSAGE);
			// ex.printStackTrace();
		} finally {
			try {
				if (ftpClient.isConnected()) {
					ftpClient.logout();
					ftpClient.disconnect();
				}
			} catch (IOException ex) {
				// ex.printStackTrace();
			}
		}
		return isConnected;

	}
}
