
public class Pojo {

	private String URL;
	String [][] allDataHere;
	String [] TAGS;
	private String server;
	private int port;
	private String user;
	private String pass;
	private String fileName;
	private String directory;
	private boolean isJtableLoaded = false;

	private int jTableRow = 0;
	private int jTableColumn = 0;
	
	
	
	public int getjTableRow() {
		return jTableRow;
	}

	public void setjTableRow(int jTableRow) {
		this.jTableRow = jTableRow;
	}

	public int getjTableColumn() {
		return jTableColumn;
	}

	public void setjTableColumn(int jTableColumn) {
		this.jTableColumn = jTableColumn;
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public boolean isJtableLoaded() {
		return isJtableLoaded;
	}

	public void setJtableLoaded(boolean isJtableLoaded) {
		this.isJtableLoaded = isJtableLoaded;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		fileName = fileName + ".json";
		this.fileName = fileName;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		if(uRL.lastIndexOf("http") == -1 )
			uRL = "http://" + uRL;
		URL = uRL;
	}

	public String[][] getAllDataHere() {
		return allDataHere;
	}

	public void setAllDataHere(String[][] allDataHere) {
		this.allDataHere = allDataHere;
	}

	public String[] getTAGS() {
		
		return TAGS;
	}

	public void setTAGS(String[] tAGS) {
		
		for (int i = 0; i < tAGS.length; i++) {
			tAGS [i] = tAGS[i].substring(1);
		}
		
		TAGS = tAGS;
	}
	
	
}
