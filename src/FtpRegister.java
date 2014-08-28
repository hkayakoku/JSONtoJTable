import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;


public class FtpRegister extends JFrame {

	private JPanel contentPane;
	private JTextField jtServer;
	private JTextField jtUser;
	private JTextField jtPort;
	private JPasswordField pfPassword;
	Pojo myPojo;

	

	public FtpRegister(Pojo myPojo) {
		this.myPojo = myPojo; 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 332, 291);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Server");
		lblNewLabel.setBounds(33, 64, 64, 14);
		contentPane.add(lblNewLabel);
		
		jtServer = new JTextField();
		jtServer.setBounds(103, 61, 143, 20);
		contentPane.add(jtServer);
		jtServer.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("User");
		lblNewLabel_1.setBounds(33, 90, 50, 14);
		contentPane.add(lblNewLabel_1);
		
		jtUser = new JTextField();
		jtUser.setBounds(103, 87, 143, 20);
		contentPane.add(jtUser);
		jtUser.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Pass");
		lblNewLabel_2.setBounds(33, 116, 50, 14);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Gonder");
		btnNewButton.setBounds(61, 189, 185, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				myPojo.setServer(jtServer.getText());
				myPojo.setPort(Integer.parseInt(jtPort.getText()));
				myPojo.setUser(jtUser.getText());
				myPojo.setPass(pfPassword.getText());
				
				FtpConnect ftpConnect = new FtpConnect();
				
				
				if(ftpConnect.checkConnection(myPojo.getServer(), myPojo.getPort(), myPojo.getUser(), myPojo.getPass()))
				{
					setVisible(false);
//					panel2.setVisible(false);
//					panel1.setVisible(true);
				}
				
				
			}
		});
		contentPane.add(btnNewButton);
		
		jtPort = new JTextField();
		jtPort.setBounds(160, 144, 86, 20);
		contentPane.add(jtPort);
		jtPort.setColumns(10);
		
		JLabel lblPort = new JLabel("Port");
		lblPort.setBounds(113, 147, 46, 14);
		contentPane.add(lblPort);
		
		pfPassword = new JPasswordField();
		pfPassword.setBounds(103, 113, 143, 20);
		contentPane.add(pfPassword);
	}


	public void getValues() {
		jtServer.setText(myPojo.getServer());
		jtPort.setText(""+myPojo.getPort());
		jtUser.setText(myPojo.getUser());
		pfPassword.setText(myPojo.getPass());
		
	}


	public boolean UploadFile(Pojo myPojo2) {
		
		// TODO Auto-generated method stub
		
        FTPClient ftpClient = new FTPClient();
        try {
 
            ftpClient.connect(myPojo2.getServer(), myPojo2.getPort());
            ftpClient.login(myPojo2.getUser(), myPojo2.getPass());
            ftpClient.enterLocalPassiveMode();
 
//            System.out.println("baglanti " + ftpClient.isAvailable());
            
            String pathName = myPojo2.getDirectory()+File.separator+myPojo2.getFileName();
            
            int reply = ftpClient.getReplyCode();
            
            if (!FTPReply.isPositiveCompletion(reply)) {
            	ftpClient.disconnect();
                System.out.println("FTP connection failed for " + myPojo2.getServer());
            }

            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
 
            // APPROACH #1: uploads first file using an InputStream
            File firstLocalFile = new File(pathName);
 
            String firstRemoteFile = myPojo2.getFileName();
            InputStream inputStream = new FileInputStream(firstLocalFile);
 
            ftpClient.changeWorkingDirectory("/public_html");
            
            System.out.println("Start uploading first file");
            boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
            inputStream.close();
            if (done) {
                System.out.println("The first file is uploaded successfully.");
                return true;
            }
 
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                return false;
            }
        }
		return false;
	}

}
