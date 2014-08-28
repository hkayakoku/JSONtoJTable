import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.json.JSONObject;

public class Main extends JFrame {

	private JFrame frame;
	Pojo myPojo = new Pojo();
	JsonAuto jsonAuto = new JsonAuto();
	JTable table;
	private JScrollPane scrollPane;
	private DefaultTableModel myModel = new DefaultTableModel();
	JTableRowColumn jSutun;

	// String[] saveOperations;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 680, 430);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		table = new JTable();

		JPanel panelMain = new JPanel();
		frame.getContentPane().add(panelMain);
		panelMain.setLayout(new BorderLayout(0, 0));

		JMenuBar menuBar = new JMenuBar();
		panelMain.add(menuBar, BorderLayout.NORTH);

		JMenu mnDosya = new JMenu("Dosya");
		menuBar.add(mnDosya);

		JMenuItem jbYeni = new JMenuItem("Yeni");
		jbYeni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				myPojo.setjTableColumn(table.getColumnCount());
				myPojo.setjTableRow(table.getRowCount());

				jSutun = new JTableRowColumn(myPojo);
				jSutun.setVisible(true);

				modelReset();
				modifyRowAndColumn();
				myPojo.setJtableLoaded(true);

			}
		});
		mnDosya.add(jbYeni);

		JMenuItem jbAc = new JMenuItem("A\u00E7");
		jbAc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ac
				boolean swich = false;
				String path;
				
				JFileChooserOpen jfOpen = new JFileChooserOpen();
				
				System.out.println(jfOpen.fileChooser.getSelectedFile().toString());				


				path = jfOpen.fileChooser.getSelectedFile().toString();
				if (!jsonAuto.checkIfValid(path)) {
					String message = "Hata:\nJSON Dosyasý Bulunamadý!";
					JOptionPane.showMessageDialog(new JFrame(), message,
							"Dialog", JOptionPane.ERROR_MESSAGE);
				} else {
					String message = "Dosya Bulundu!";
					JOptionPane.showMessageDialog(new JFrame(), message,
							"Dialog", JOptionPane.INFORMATION_MESSAGE);
					swich = true;
				}

				if (swich) {

					modelReset();
					jsonAuto.setURL(path);
					myPojo.setAllDataHere(jsonAuto
							.parseAndAssignTwoDimentional());
					myPojo.setTAGS(jsonAuto.getObjectNames());

					for (String arr : myPojo.getTAGS()) {
						myModel.addColumn(arr);
					}

					for (int i = 0; i < myPojo.getAllDataHere().length; i++) {

						Object[] a = myPojo.allDataHere[i];
						myModel.addRow(a);
					}

					myPojo.setJtableLoaded(true);

				}// end if switch

				// table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				table.setModel(myModel);
				
			}
		});
		mnDosya.add(jbAc);

		JMenuItem jbSunucudanYukle = new JMenuItem("Sunucudan Y\u00FCkle...");
		jbSunucudanYukle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				boolean swich = false;

				myPojo.setURL(JOptionPane
						.showInputDialog("Sunucu Adresini Giriniz..."));
				if (!jsonAuto.checkIfValid(myPojo.getURL())) {
					String message = "Hata:\nJSON Dosyasý Bulunamadý!";
					JOptionPane.showMessageDialog(new JFrame(), message,
							"Dialog", JOptionPane.ERROR_MESSAGE);
				} else {
					String message = "Dosya Bulundu!";
					JOptionPane.showMessageDialog(new JFrame(), message,
							"Dialog", JOptionPane.INFORMATION_MESSAGE);
					swich = true;
				}

				if (swich) {

					modelReset();
					jsonAuto.setURL(myPojo.getURL());
					myPojo.setAllDataHere(jsonAuto
							.parseAndAssignTwoDimentional());
					myPojo.setTAGS(jsonAuto.getObjectNames());

					for (String arr : myPojo.getTAGS()) {
						myModel.addColumn(arr);
					}

					for (int i = 0; i < myPojo.getAllDataHere().length; i++) {

						Object[] a = myPojo.allDataHere[i];
						myModel.addRow(a);
					}

					myPojo.setJtableLoaded(true);

				}// end if switch

				// table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				table.setModel(myModel);

			}// end action performed

		});

		JMenuItem jbTemizle = new JMenuItem("Temizle");
		jbTemizle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				modelReset();

			}
		});
		mnDosya.add(jbTemizle);
		mnDosya.add(jbSunucudanYukle);

		JMenu mnKaydet = new JMenu("Kaydet");
		menuBar.add(mnKaydet);

		JMenuItem jbSunucuyaGonder = new JMenuItem("Sunucuya G\u00F6nder");
		jbSunucuyaGonder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				FtpRegister ftpreg = new FtpRegister(myPojo);

				// if (myPojo.isJtableLoaded()) {
				// System.out.println("Loaded");
				// buildJsonFile();
				// } else {
				// System.out.println("Couldnot load la");
				// }
				//

				if (myPojo.getServer() == null) {
					System.out.println("null");
					ftpreg.setVisible(true);
				}

				myPojo.setDirectory(".");
				myPojo.setFileName(JOptionPane
						.showInputDialog("Dosya Adini Giriniz"));
				buildJsonFile();

				if (ftpreg.UploadFile(myPojo)) {
					String message = "\"" + myPojo.getFileName()
							+ "\" dosyasi\n baþarýyla gönderildi!";
					JOptionPane.showMessageDialog(new JFrame(), message,
							"Dialog", JOptionPane.INFORMATION_MESSAGE);
				} else {
					String message = "Hata:\n" + "\"" + myPojo.getFileName()
							+ "\" dosyasi gönderilirken hata oluþtu";
					JOptionPane.showMessageDialog(new JFrame(), message,
							"Dialog", JOptionPane.ERROR_MESSAGE);
				}
				deleteFile();

			}

			private void deleteFile() {
				// TODO Auto-generated method stub

				File file = new File(myPojo.getDirectory() + File.separator
						+ myPojo.getFileName());
				file.delete();

			}
		});
		mnKaydet.add(jbSunucuyaGonder);

		JMenuItem mnýtmNewMenuItem = new JMenuItem("Diske Kaydet");
		mnýtmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// kaydetislem
				// saveOperations = getSaveOperations();

				getSaveOperations();

				if (myPojo.isJtableLoaded()) {
					System.out.println("Loaded");
					buildJsonFile();
				} else {
					System.out.println("Couldnot load la");
				}

			}

		});
		mnKaydet.add(mnýtmNewMenuItem);

		JMenu mnEkle = new JMenu("Ekle");
		menuBar.add(mnEkle);

		JMenuItem mnýtmNewMenuItem_1 = new JMenuItem(
				"Ftp Ba\u011Flant\u0131s\u0131");
		mnýtmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				FtpRegister ftpreg = new FtpRegister(myPojo);
				ftpreg.setVisible(true);
				if (myPojo.getServer() != null) {

					ftpreg.getValues();

				}

			}
		});
		mnEkle.add(mnýtmNewMenuItem_1);

		JMenu mnTable = new JMenu("Tablo");
		menuBar.add(mnTable);

		JMenuItem mnItmSatir = new JMenuItem("Sat\u0131r&Sutun Ekle");
		mnItmSatir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// satirsutunekle

				myPojo.setjTableColumn(table.getColumnCount());
				myPojo.setjTableRow(table.getRowCount());

				jSutun = new JTableRowColumn(myPojo);
				jSutun.setVisible(true);
				// System.out.println("row" + table.getRowCount());
				// System.out.println("column" + table.getColumnCount());

				modifyRowAndColumn();

			}
		});
		mnTable.add(mnItmSatir);

		JMenuItem mnýtmStunIsimleri = new JMenuItem("S\u00FCtun \u0130simleri");
		mnýtmStunIsimleri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// sutunisimleri
				ColumnNames colNames = new ColumnNames(myPojo);
				colNames.initialize(myPojo);
				colNames.setVisible(true);

				colNames.btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						myPojo.TAGS = colNames.stringColNames;

						System.out.println("colnames "
								+ colNames.stringColNames[0]);
						System.out.println("tag " + myPojo.TAGS[0]);

						String message = "Kolon Deðiþiklikleri Kaydedildi!";
						JOptionPane.showMessageDialog(new JFrame(), message,
								"Dialog", JOptionPane.INFORMATION_MESSAGE);

						colNames.setVisible(false);

						String getData[][];
						int row = table.getRowCount();
						int col = table.getColumnCount();
						getData = new String[row][col];
						for (int i = 0; i < table.getRowCount(); i++) {
							getData[i] = getRowAt(i);
						}

						modelReset();

						for (int j = 0; j < col; j++) {
							myModel.addColumn(myPojo.TAGS[j]);
						}

						for (int i = 0; i < row; i++) {
							myModel.addRow(getData[i]);
						}

					}
				});

			}
		});
		mnTable.add(mnýtmStunIsimleri);

		scrollPane = new JScrollPane(table);
		panelMain.add(scrollPane, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// frame.setVisible(true);
	}

	private void modelReset() {

		myModel.setRowCount(0);
		myModel.setColumnCount(0);

	}

	public void getSaveOperations() {

		String[] data = new String[2];
		JFileChooser jFileChooser = new JFileChooser();
		int rVal = jFileChooser.showSaveDialog(Main.this);

		if (rVal == JFileChooser.APPROVE_OPTION) {

			System.out.println(jFileChooser.getSelectedFile().getName());
			System.out.println(jFileChooser.getCurrentDirectory().toString());
			data[0] = jFileChooser.getCurrentDirectory().toString();
			data[1] = jFileChooser.getSelectedFile().getName();
			myPojo.setDirectory(data[0]);
			myPojo.setFileName(data[1]);
			// filename.setText(c.getSelectedFile().getName());
			// dir.setText(c.getCurrentDirectory().toString());
		}
		if (rVal == JFileChooser.CANCEL_OPTION) {

			System.out.println("Cancel");
			// filename.setText("You pressed cancel");
			// dir.setText("");
		}

	}

	private void buildJsonFile() {

		String[][] getData;

		getData = new String[table.getRowCount()][table.getColumnCount()];
		for (int i = 0; i < table.getRowCount(); i++) {
			getData[i] = getRowAt(i);
		}

		try {
			generateJSON(getData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void generateJSON(String[][] getData) throws Exception {

		JSONObject dataset = new JSONObject();

		String mainTag = JOptionPane
				.showInputDialog("Lutfen Baþlýk Ýsmini Giriniz");

		System.out.println("ROW: " + table.getRowCount());
		System.out.println("COLUMN: " + table.getColumnCount());

		Map<String, String> map;
		for (int i = 0; i < table.getRowCount(); i++) {
			map = new HashMap<>();
			for (int k = 0; k < table.getColumnCount(); k++) {

				getData[i][k] = setFormattedOutput(getData[i][k]);
				System.out.println("getData: " + getData[i][k]);

				map.put(k + myPojo.TAGS[k], getData[i][k]);
			}
			dataset.accumulate(mainTag, map);
		}

		 System.out.println("dataset : " +dataset.toString());
		 String datasetnew = dataset.toString();
		 System.out.println("datasetreplace : " + datasetnew.replaceAll("\\\\\\\\", "\\\\"));
		 datasetnew = datasetnew.replaceAll("\\\\\\\\", "\\\\");
		 

		String path = myPojo.getDirectory() + File.separator
				+ myPojo.getFileName();
		System.out.println(path);

		FileWriter file = new FileWriter(path);
		try {
			String myString = datasetnew;
//			Charset.forName("UTF-8").encode(myString);

			file.write(myString);
			System.out.println("Successfully Copied JSON Object to File...");
			// System.out.println("\nJSON Object: " + dataset);

		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			file.flush();
			file.close();
		}

	}

	private String setFormattedOutput(String value) {

		for (int i = 0; i < value.length(); i++) {
			if (isnonUnicode(value.charAt(i))) {
				System.out.println(unicodeEscaped(value.charAt(i)));
				value = value.replace(value.charAt(i) + "",
						unicodeEscaped(value.charAt(i)));
			}
		}

		return value;
	}

	private String unicodeEscaped(char ch) {
		if (ch < 0x10) {
			return "\\u000" + Integer.toHexString(ch);
		} else if (ch < 0x100) {
			return "\\u00" + Integer.toHexString(ch);
		} else if (ch < 0x1000) {
			return "\\u0" + Integer.toHexString(ch);
		}
		return "\\u" + Integer.toHexString(ch);
	}

	// value = value.replaceAll("ç", "\\u00E7");
	// value = value.replaceAll("Ç", "\\u00C7");
	// value = value.replaceAll("ð", "\\u011F");
	// value = value.replaceAll("Ð", "\\u011E");
	// value = value.replaceAll("ý", "\\u0131");
	// value = value.replaceAll("Ý", "\\u0130");
	// value = value.replaceAll("ö", "\\u00F6");
	// value = value.replaceAll("Ö", "\\u00D6");
	// value = value.replaceAll("þ", "\\u015F");
	// value = value.replaceAll("Þ", "\\u015E");
	// value = value.replaceAll("ü", "\\u00FC");
	// value = value.replaceAll("Ü", "\\u00DC");

	private boolean isnonUnicode(char crx) {

		if (crx == 'ç' || crx == 'Ç' || crx == 'ð' || crx == 'Ð' || crx == 'ý' || crx == 'Ý' || 
				crx == 'ö' || crx == 'Ö' || crx == 'þ' || crx == 'Þ'|| crx == 'ü'|| crx == 'Ü') {
			return true;
		} else
			return false;
	}

	private String[] getRowAt(int row) {

		String[] result = new String[myPojo.TAGS.length];

		for (int i = 0; i < myPojo.TAGS.length; i++) {

			result[i] = (String) table.getModel().getValueAt(row, i);
			// System.out.println(result[i]);
		}

		return result;
	}

	public void modifyRowAndColumn() {
		jSutun.okButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				myPojo.setjTableRow((Integer) jSutun.spinnerSatir.getValue());
				myPojo.setjTableColumn((Integer) jSutun.spinnerSutun.getValue());

				jSutun.setVisible(false);

				System.out.println("spinnersatir " + myPojo.getjTableRow());
				System.out.println("spinnersutun " + myPojo.getjTableColumn());

				myModel.setRowCount(myPojo.getjTableRow());
				myModel.setColumnCount(myPojo.getjTableColumn());

				List<String> list_data = new ArrayList<String>();
				String[] newTAGS;

				table.setModel(myModel);

				for (int i = 0; i < myPojo.getjTableColumn(); i++) {

					list_data.add(i + table.getColumnName(i));
					System.out.println("Column " + i + " "
							+ table.getColumnName(i));
				}

				newTAGS = new String[list_data.size()];
				list_data.toArray(newTAGS);

				myPojo.setTAGS(newTAGS);

				// String line = "Hello~There~This~Is";
				// String dataValue[] = line.split("~");
				// Vector<String> v = new Vector<>(Arrays.asList(dataValue));
				// myModel.setColumnCount(v.size());
				// myModel.addRow(v);

				// myModel.addColumn(columnName);

			}
		});
	}

	public void refreshTable(Pojo myPojo) {
		// TODO Auto-generated method stub

		// Object [] a = myPojo.TAGS;
		// System.out.println(myPojo.TAGS[0]);
		// myModel.setColumnIdentifiers(a);
		// table.setModel(myModel);
		// JTableHeader th = table.getTableHeader();
		// TableColumnModel tcm = th.getColumnModel();
		// TableColumn tc = tcm.getColumn(0);
		// System.out.println(tcm.getColumn(0));
		// tc.setHeaderValue( "denem" );
		// th.repaint();

		// ChangeName(table,0,"Stu_name");

		String[][] getData;
		String[] deneme = { "aa", "bb", "cc" };

		getData = new String[table.getRowCount()][table.getColumnCount()];
		for (int i = 0; i < table.getRowCount(); i++) {
			// getData[i] = getRowAt(i);
			getData[i] = deneme;
		}

		modelReset();

		System.out.println("hey tehere" + myPojo.TAGS[0]);

		myModel.setRowCount(2);
		myModel.setColumnCount(3);

		for (int i = 0; i < myPojo.TAGS.length; i++) {
			myModel.addColumn(myPojo.TAGS[i]);
		}

		for (int i = 0; i < table.getRowCount(); i++) {
			myModel.addRow(getData[i]);
		}

		System.out.println(myModel.getColumnName(0));

		table.setModel(myModel);

	}

	// public void ChangeName(JTable table, int col_index, String col_name){
	// table.getColumnModel().getColumn(col_index).setHeaderValue(col_name);
	// }
}
