import java.io.*;
import java.awt.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import java.awt.event.*;

import javax.swing.*;

import java.security.Key;

public class crypto {

	public static void main(String args[]) {
		FileEncryptUI obj = new FileEncryptUI();
		Thread Threadobject = new Thread(obj);
		try {
			Threadobject.start();
			Thread.sleep(1500);
			new FileEncryptUI().start();
			Thread.sleep(100);
			obj.exitLoad();
		} catch (Exception e) {
		}

	}
}

class FileEncryptUI extends JFrame implements ActionListener, MouseListener, Runnable

{

	// declare form UI controls

	private JLabel helpLabel, // label containing image for 'Help' link on
								// encrypt panel
			helpLabel1; // label containing image for 'Help' link on decrypt
						// panel

	private JTextField txtEncFile, // encrypt source file
			txtDecFile; // decrypt source file

	private TextArea textarea; // Help field

	private JPasswordField passwordField, // Password 1
			passwordField1, // Confirm Pasword
			passwordField2; // Dec Password

	private JButton btnEncBrw, // browse encrypt source file
			btnDecBrw, // browse decrypt source file
			btnEncRun, // run encryption
			btnDecRun, // run decryption
			btnExit, // Enc Exit
			btnexit2; // Dec Exit

	private JTabbedPane tab; // JTabbedPane which holds three panels

	private JPanel pnlEnc, // main encryption panel
			pnlDec, // main decryption panel
			pnlEncRow1, // enc sub panel
			pnlEncRow2, // enc sub panel
			pnlEncRow3, // enc sub panel
			pnlEncRow4, // enc sub panel
			pnlDecRow1, // dec sub panel
			pnlDecRow4, // dec sub panel
			pnlDecRow5, // dec sub panel
			pnlAbt, // main about panel
			loadpanel; // Starting Frame(Loading) panel

	private JMenuBar menuBar; // MenuBar for main frame

	private JMenu menuNew, // Menu 'New' for menubar.
			menuHelp; // Menu 'Help' for menubar.

	private JMenuItem menuItemExit, // Menu item Exit
			menuItemHelp; // Menu item Help

	private key des; // DES object

	private JFrame load = new JFrame(); // Starting Frame(Loading)
	private JFrame main = new JFrame(); // Main Frame

	/*
	 * Default constructor to launch program
	 */

	public FileEncryptUI() {

		des = new key();

		// encryption panel

		txtEncFile = new JTextField("Click here to Browse.", 30);
		txtEncFile.setToolTipText("Click here to Browse.");
		txtEncFile.addMouseListener(this);
		txtEncFile.setEditable(false);
		txtEncFile.setBackground(Color.white);
		txtEncFile.setBorder(BorderFactory.createEtchedBorder());

		passwordField = new JPasswordField("               ", 14);
		passwordField.setToolTipText("Password which is required to decrypt the encrypted file.");
		passwordField.addMouseListener(this);
		passwordField.setBorder(BorderFactory.createEtchedBorder());

		passwordField1 = new JPasswordField("               ", 14);
		passwordField1.setToolTipText("Confirm's the password entered above.");
		passwordField1.addMouseListener(this);
		passwordField1.setBorder(BorderFactory.createEtchedBorder());

		btnExit = new JButton("Exit");
		btnExit.setToolTipText("Click to exit from program.");
		btnExit.setPreferredSize(new Dimension(80, 20));
		btnExit.addActionListener(this);
		btnExit.setMnemonic(KeyEvent.VK_X);

		btnEncBrw = new JButton("  Browse  ");
		btnEncBrw.setToolTipText("Click to browse for file which you may encrypt.");
		btnEncBrw.addActionListener(this);
		btnEncBrw.setMnemonic(KeyEvent.VK_B);

		btnEncRun = new JButton("Encrypt");
		btnEncRun.setToolTipText("Click to encrypt the selected file.");
		btnEncRun.setPreferredSize(new Dimension(80, 20));
		btnEncRun.addActionListener(this);
		btnEncRun.setMnemonic(KeyEvent.VK_E);

		pnlEncRow1 = new JPanel(new BorderLayout());
		pnlEncRow1.setPreferredSize(new Dimension(300, 20));
		pnlEncRow1.setBackground(new Color(10, 10, 10, 10));
		pnlEncRow1.add(new JLabel("File: "), "West");
		pnlEncRow1.add(txtEncFile, "Center");
		pnlEncRow1.add(btnEncBrw, "East");

		pnlEncRow2 = new JPanel(new BorderLayout());
		pnlEncRow2.setPreferredSize(new Dimension(300, 20));
		pnlEncRow2.setBackground(new Color(10, 10, 10, 10));
		pnlEncRow2.add(new JLabel("Password: "), "West");
		pnlEncRow2.add(passwordField, "Center");

		pnlEncRow3 = new JPanel(new BorderLayout());
		pnlEncRow3.setPreferredSize(new Dimension(300, 20));
		pnlEncRow3.setBackground(new Color(10, 10, 10, 10));
		pnlEncRow3.add(new JLabel("Confirm:     "), "West");
		pnlEncRow3.add(passwordField1, "Center");

		pnlEncRow4 = new JPanel(new BorderLayout());
		pnlEncRow4.setPreferredSize(new Dimension(290, 20));
		pnlEncRow4.setBackground(new Color(10, 10, 10, 10));
		pnlEncRow4.add(btnEncRun, "West");
		ImageIcon icon0 = new ImageIcon("Images/help.gif");
		helpLabel = new JLabel(icon0);
		helpLabel.setToolTipText("Click to view Help Contents");
		helpLabel.addMouseListener(this);
		pnlEncRow4.add(helpLabel, "Center");
		pnlEncRow4.add(btnExit, "East");

		pnlEnc = new JPanel(new FlowLayout());
		pnlEnc.setBackground(new Color(10, 10, 10, 10));
		pnlEnc.add(new JLabel("Select a file to be encrypted"));
		pnlEnc.add(pnlEncRow1);
		pnlEnc.add(new JLabel("                                        "), "Center");
		pnlEnc.add(pnlEncRow2);
		pnlEnc.add(pnlEncRow3);
		pnlEnc.add(new JLabel("                                        "), "Center");
		pnlEnc.add(pnlEncRow4);

		// decryption panel

		txtDecFile = new JTextField("Click here to Browse.", 30);
		txtDecFile.setToolTipText("Click here to Browse.");
		txtDecFile.addMouseListener(this);
		txtDecFile.setEditable(false);
		txtDecFile.setBackground(Color.white);
		txtDecFile.setBorder(BorderFactory.createEtchedBorder());

		passwordField2 = new JPasswordField("               ", 14);
		passwordField2.setToolTipText("Enter the password entered while encrypting the file selected.");
		passwordField2.addMouseListener(this);
		passwordField2.setBorder(BorderFactory.createEtchedBorder());

		btnDecBrw = new JButton("  Browse  ");
		btnDecBrw.setToolTipText("Click to browse for file.");
		btnDecBrw.addActionListener(this);
		btnDecBrw.setMnemonic(KeyEvent.VK_B);

		btnDecRun = new JButton("Decrypt");
		btnDecRun.setToolTipText("Click to decrypt the selected file.");
		btnDecRun.setPreferredSize(new Dimension(80, 20));
		btnDecRun.addActionListener(this);
		btnDecRun.setMnemonic(KeyEvent.VK_D);

		btnexit2 = new JButton("Exit");
		btnexit2.setToolTipText("Click to exit program.");
		btnexit2.setPreferredSize(new Dimension(80, 20));
		btnexit2.addActionListener(this);
		btnexit2.setMnemonic(KeyEvent.VK_D);
		btnexit2.setMnemonic(KeyEvent.VK_X);

		pnlDecRow1 = new JPanel(new BorderLayout());
		pnlDecRow1.setPreferredSize(new Dimension(300, 20));
		pnlDecRow1.setBackground(new Color(10, 10, 10, 10));
		pnlDecRow1.add(new JLabel("File: "), "West");
		pnlDecRow1.add(txtDecFile, "Center");
		pnlDecRow1.add(btnDecBrw, "East");

		pnlDecRow4 = new JPanel(new BorderLayout());
		pnlDecRow4.setPreferredSize(new Dimension(300, 20));
		pnlDecRow4.setBackground(new Color(10, 10, 10, 10));
		pnlDecRow4.add(new JLabel("Password: "), "West");
		pnlDecRow4.add(passwordField2, "Center");

		pnlDecRow5 = new JPanel(new BorderLayout());
		pnlDecRow5.setPreferredSize(new Dimension(290, 20));
		pnlDecRow5.setBackground(new Color(10, 10, 10, 10));
		pnlDecRow5.add(btnDecRun, "West");
		ImageIcon icon1 = new ImageIcon("Images/help.gif");
		helpLabel1 = new JLabel(icon1);
		helpLabel1.setToolTipText("Click to view Help Contents");
		helpLabel1.addMouseListener(this);
		pnlDecRow5.add(helpLabel1, "Center");
		pnlDecRow5.add(btnexit2, "East");

		pnlDec = new JPanel(new FlowLayout());
		pnlDec.setBackground(new Color(10, 10, 10, 10));
		pnlDec.add(new JLabel("Select the encrypted file to decrypt."));
		pnlDec.add(pnlDecRow1);
		pnlDec.add(new JLabel("                                       "), "Center");
		pnlDec.add(pnlDecRow4);
		pnlDec.add(new JLabel("                                                    "), "Center");
		pnlDec.add(new JLabel("                                                    "), "Center");
		pnlDec.add(new JLabel("                                                    "), "Center");
		pnlDec.add(new JLabel("                                                    "), "Center");
		pnlDec.add(new JLabel("                                       "), "Center");

		pnlDec.add(pnlDecRow5);

		// about panel
		pnlAbt = new JPanel(new FlowLayout());
		pnlAbt.setBackground(new Color(0, 0, 0, 255));

		ImageIcon icon2 = new ImageIcon("src/Images/logo_garena1.jpg");
		pnlAbt.add(new JLabel(icon2), "North");
		JLabel label1 = new JLabel("Email: svp911@gmail.com");
		label1.setForeground(Color.WHITE);
		label1.setFont(new Font("Chiller", Font.BOLD, 27));
		pnlAbt.add(label1);

		// main tab
		tab = new JTabbedPane();
		tab.setPreferredSize(new Dimension(310, 150));
		tab.setBackground(new Color(255, 255, 255, 255));
		tab.setForeground(new Color(0, 0, 0, 255));
		tab.add("Encrypt", pnlEnc);
		tab.add("Decrypt", pnlDec);
		tab.add("About", pnlAbt);

		// main frame

		menuBar = new JMenuBar();

		menuNew = new JMenu("File");
		menuItemExit = new JMenuItem("Exit");
		menuItemExit.addMouseListener(this);
		menuNew.add(menuItemExit);
		menuHelp = new JMenu("Help");
		menuItemHelp = new JMenuItem("Help Topics");
		menuItemHelp.addMouseListener(this);
		menuHelp.add(menuItemHelp);
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
		} catch (Exception e) {
		}
		menuBar.add(menuNew);
		menuBar.add(menuHelp);

		try {
			main.setSize(360, 288);
			main.setLocation(320, 400);
			main.add(tab, "Center");
			Image icon3 = Toolkit.getDefaultToolkit().getImage("Icons/logo2.png");
			main.setIconImage(icon3);
			main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			main.setResizable(false);
			String title = ("||:.     W A P S A     .:||");
			main.setTitle(title);
			main.setJMenuBar(menuBar);
		} catch (Exception e) {
		}

		// Load Frame Panel
		loadpanel = new JPanel(new BorderLayout());
		loadpanel.setPreferredSize(new Dimension(300, 20));
		loadpanel.setBackground(new Color(10, 10, 10, 10));
		ImageIcon icon5 = new ImageIcon("Images/blink.gif");
		loadpanel.add(new JLabel(icon5), "Center");

	}

	public void run() {

		load.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		load.setUndecorated(true);
		Image icon4 = Toolkit.getDefaultToolkit().getImage("icon.gif");
		load.setIconImage(icon4);
		load.getContentPane().add(loadpanel, "Center");
		load.setVisible(true);
		load.setSize(360, 288);
		load.setLocation(320, 400);
		load.setResizable(false);
		String title1 = ("File Krypt Zone ");
		load.setTitle(title1);

	}

	public void exitLoad() {

		load.setVisible(false);

	}

	public void start() {
		main.setVisible(true);
	}

	/*
	 * Receive and process user interactions
	 * 
	 */

	public void mousePressed(MouseEvent em) {

		JTextField tf = (JTextField) em.getSource();
		if (tf == txtEncFile) {
			File file = getFileDialogOpen("*.*");
			if (file == null)
				return;
			txtEncFile.setText(file.getAbsolutePath());
		}
		if (tf == txtDecFile) {
			File file = getFileDialogOpen("*.crypt");
			if (file == null)
				return;
			txtDecFile.setText(file.getAbsolutePath());
		}
		try {
			JPasswordField cod = (JPasswordField) em.getSource();

			if (cod == passwordField) {
				passwordField.setText("");
			} else if (cod == passwordField1) {
				passwordField1.setText("");
			} else if (cod == passwordField2) {
				passwordField2.setText("");
			}
		} catch (Exception e) {
		}
	}

	public void mouseReleased(MouseEvent em) {
		try {
			JMenuItem jmi = (JMenuItem) em.getSource();
			if (jmi == menuItemExit) {
				System.exit(0);
			}
			if (jmi == menuItemHelp) {
				File file = new File("Help/Help.chm");
				Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL " + file.getAbsolutePath());
			}
		} catch (Exception e) {
		}
	}

	public void mouseClicked(MouseEvent em) {
		try {
			JLabel jl = (JLabel) em.getSource();
			if (jl == helpLabel) {
				File file = new File("Help/Help.chm");

				Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL " + file.getAbsolutePath());
			}
		} catch (Exception e) {
		}

		try {
			JLabel jl = (JLabel) em.getSource();
			if (jl == helpLabel1) {
				File file = new File("Help/Help.chm");

				Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL " + file.getAbsolutePath());
			}
		} catch (Exception e) {
		}

		JTextField tf = (JTextField) em.getSource();
		if (tf == txtEncFile) {
			File file = getFileDialogOpen("*.*");
			if (file == null)
				return;
			txtEncFile.setText(file.getAbsolutePath());
		}
		if (tf == txtDecFile) {
			File file = getFileDialogOpen("*.crypt");
			if (file == null)
				return;
			txtDecFile.setText(file.getAbsolutePath());
		}

		JPasswordField cod = (JPasswordField) em.getSource();
		if (cod == passwordField) {
			passwordField.setText("");
		} else if (cod == passwordField1) {
			passwordField1.setText("");
		} else if (cod == passwordField2) {
			passwordField2.setText("");
		}
	}

	public void mouseEntered(MouseEvent em) {

	}

	public void mouseExited(MouseEvent em) {

	}

	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();

		// browse for source file
		if (btn == btnEncBrw) {
			File file = getFileDialogOpen("*.*");
			if (file == null)
				return;
			txtEncFile.setText(file.getAbsolutePath());
		}

		// browse for encrypted files
		if (btn == btnDecBrw) {
			File file = getFileDialogOpen("*.crypt");
			if (file == null)
				return;
			txtDecFile.setText(file.getAbsolutePath());
		}

		// perform encryption
		if (btn == btnEncRun) {
			char[] input = passwordField.getPassword();
			String inp = new String(input);
			char[] input1 = passwordField1.getPassword();
			String inp1 = new String(input1);

			if (inp.equals(inp1)) {
				// open file and read data
				File file = new File(txtEncFile.getText());
				byte data[] = readByteFile(file);

				// Create files
				String pat = file.getAbsolutePath();
				File encFile = new File(pat + ".enc");
				File encKey = new File(pat + ".key");
				File fpassword = new File(pat + ".pas");
				File keyFile = new File(pat + ".pekey");
				File pasFile = new File(pat + ".penc");

				try {
					BufferedWriter out = new BufferedWriter(new FileWriter(fpassword));
					out.write("" + inp);
					out.close();

				} catch (IOException E) {
					JOptionPane.showMessageDialog(null, "Cannot read path and write to file.", "Error Message",
							JOptionPane.ERROR_MESSAGE);
				}

				// encrypt and save as new data and key as new files
				data = des.encrypt(data);
				if (writeByteFile(encFile.getAbsolutePath(), data)
						&& writeObjectFile(encKey.getAbsolutePath(), des.getKey()))

				{
					byte datapas[] = readByteFile(fpassword);
					datapas = des.encrypt(datapas);

					writeObjectFile(keyFile.getAbsolutePath(), des.getKey());
					writeByteFile(pasFile.getAbsolutePath(), datapas);

					try {
						fpassword.delete();
						File myzip = new File(pat + ".crypt");
						FileOutputStream fos = new FileOutputStream(myzip);
						ZipOutputStream zos = new ZipOutputStream(fos);
						String file1path = encFile.getAbsolutePath();
						String file1name = encFile.getName();
						String file2path = encKey.getAbsolutePath();
						String file2name = encKey.getName();
						String file3path = pasFile.getAbsolutePath();
						String file3name = pasFile.getName();
						String file4path = keyFile.getAbsolutePath();
						String file4name = keyFile.getName();
						addToZipFile(file1path, file1name, zos);
						addToZipFile(file2path, file2name, zos);
						addToZipFile(file3path, file3name, zos);
						addToZipFile(file4path, file4name, zos);
						zos.close();
						fos.close();
						encFile.delete();
						encKey.delete();
						pasFile.delete();
						keyFile.delete();
					} catch (Exception elkkk) {
					}

					JOptionPane.showMessageDialog(null, "File encrypted as: " + file.getName() + ".enc\n"
							+ "Encryption key: " + file.getName() + ".key\n", "Done", JOptionPane.INFORMATION_MESSAGE);

				} else {
				}
			} else {
				JOptionPane.showMessageDialog(null, "Passwords do not match.", "Error Message",
						JOptionPane.ERROR_MESSAGE);
			}

		}

		// perform decryption
		if (btn == btnDecRun) {
			try {

				String ax = txtDecFile.getText();
				ZipFile zipFile = new ZipFile(ax);
				File abc = new File(zipFile.getName());

				String abcpath = abc.getAbsolutePath();
				String abcpat = abc.getParent();
				new FileEncryptUI().unzip(abcpath, abcpat);

				String stemp = ax.substring(0, ax.length() - 5);
				String s = stemp + "enc";
				String s1 = stemp + "key";
				String s2 = stemp + "pekey";
				String s3 = stemp + "penc";
				File fileenc = new File(s);
				File fileenckey = new File(s1);
				File filepas = new File(s3);
				File filepaskey = new File(s2);

				String ow = new String();
				char[] inputpass = passwordField2.getPassword();
				String inppass = new String(inputpass);
				// get encrypted file and key
				if (!fileenc.exists()) {
					JOptionPane.showMessageDialog(null, "Encrypted file not found or cannot be accessed.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (!fileenckey.exists()) {
					JOptionPane.showMessageDialog(null, "Key file not found or cannot be accessed.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (!filepaskey.exists()) {
					JOptionPane.showMessageDialog(null, "Cod file not found or cannot be accessed.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				byte data1[] = readByteFile(filepas);
				Key key1 = (Key) readObjectFile(filepaskey);
				data1 = des.decrypt(data1, key1);

				File newpasfile = new File("pass.abcdef");
				FileOutputStream fos = new FileOutputStream(newpasfile);
				fos.write(data1);
				fos.close();

				FileInputStream fstream1 = new FileInputStream(newpasfile);
				DataInputStream in1 = new DataInputStream(fstream1);
				BufferedReader br1 = new BufferedReader(new InputStreamReader(in1));
				ow = br1.readLine();
				in1.close();

				if (inppass.equals(ow)) {
					// use key to decrypt data

					byte data[] = readByteFile(fileenc);
					Key key = (Key) readObjectFile(fileenckey);
					data = des.decrypt(data, key);

					// restore original file and remove encrypted file and key

					String filename = abc.getAbsolutePath().substring(0, abc.getAbsolutePath().length() - 5);

					if (writeByteFile(filename, data)) {
						fileenc.delete();
						fileenckey.delete();
						filepas.delete();
						filepaskey.delete();
						abc.delete();

						JOptionPane.showMessageDialog(null, "File sucessfully decrypted.", "Done",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
					}
				} else {
					fileenc.delete();
					fileenckey.delete();
					filepas.delete();
					filepaskey.delete();
					JOptionPane.showMessageDialog(null, "Password entered is wrong.", "Error",
							JOptionPane.ERROR_MESSAGE);

				}
			} catch (IOException l) {
				l.printStackTrace();
			}
		}

		// exit

		if (btn == btnexit2) {
			System.exit(0);
		}

		if (btn == btnExit) {
			System.exit(0);
		}
	}

	/*
	 * Allow user to select a file using an Open Dialog and return the file
	 * return A valid file that the user has selected, or null
	 */

	protected File getFileDialogOpen(String filter)

	{
		FileDialog fd = new FileDialog(this, "Select File", FileDialog.LOAD);
		fd.setFile(filter);
		fd.setVisible(true);

		if (fd.getFile() == null)
			return null;
		File file = new File(fd.getDirectory() + fd.getFile());

		if (!file.canRead()) {
			JOptionPane.showMessageDialog(null, "Selected file cannot be read.", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		return file;
	}

	/*
	 * Reads a file and returns its contents as an array of bytes
	 * 
	 */
	protected byte[] readByteFile(File file) {
		byte data[] = null;
		try {
			FileInputStream fis = new FileInputStream(file);

			int c, i = 0;
			data = new byte[(int) file.length()];
			while ((c = fis.read()) != -1)
				data[i++] = (byte) c;
			fis.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, file.getName() + " File not found or cannot be read.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return null;
		}
		return data;
	}

	/*
	 * Writes byte contents to a file
	 * 
	 */

	protected boolean writeByteFile(String fileName, byte[] data) {
		File file = new File(fileName);
		if (!file.canWrite()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Unable to create file " + file.getName() + " for writing.",
						"Error", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}

		try {
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(data);
			fos.close();
			return true;
		}

		catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Unable to write to file " + file.getName(), "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	/*
	 * Reads a file and returns its contents as an array of objects
	 * 
	 */
	protected Object readObjectFile(File file) {
		Object obj;
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			obj = (Object) ois.readObject();
			ois.close();
		}

		catch (IOException e) {
			JOptionPane.showMessageDialog(null, file.getName() + " not found or cannot be read.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return null;
		}

		catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, file.getName() + " does not contain a readable object.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return null;
		}
		return obj;
	}

	/*
	 * Makes Zip Files
	 * 
	 */
	public static void addToZipFile(String filePath, String fileName, ZipOutputStream zos)
			throws FileNotFoundException, IOException {

		File file = new File(filePath);
		FileInputStream fis = new FileInputStream(file);
		ZipEntry zipEntry = new ZipEntry(fileName);
		zos.putNextEntry(zipEntry);

		byte[] bytes = new byte[1024];
		int length;
		while ((length = fis.read(bytes)) >= 0) {
			zos.write(bytes, 0, length);
		}

		zos.closeEntry();
		fis.close();
	}

	/*
	 * 
	 * Unzip zip file
	 * 
	 */

	public void unzip(String zipFilePath, String destDirectory) throws IOException {
		File destDir = new File(destDirectory);
		if (!destDir.exists()) {
			destDir.mkdir();
		}
		ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
		ZipEntry entry = zipIn.getNextEntry();
		// iterates over entries in the zip file
		while (entry != null) {
			String filePath = destDirectory + File.separator + entry.getName();
			if (!entry.isDirectory()) {
				// if the entry is a file, extracts it
				extractFile(zipIn, filePath);
			} else {
				// if the entry is a directory, make the directory
				File dir = new File(filePath);
				dir.mkdir();
			}
			zipIn.closeEntry();
			entry = zipIn.getNextEntry();
		}
		zipIn.close();
	}

	private void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
		byte[] bytesIn = new byte[4096];
		int read = 0;
		while ((read = zipIn.read(bytesIn)) != -1) {
			bos.write(bytesIn, 0, read);
		}
		bos.close();
	}

	/*
	 * Writes an object to a file
	 * 
	 */

	protected boolean writeObjectFile(String fileName, Object data) {
		File file = new File(fileName);
		if (!file.canWrite()) {
			try {
				file.createNewFile();
			}

			catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Unable to create file " + file.getName() + " for writing.",
						"Error", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}

		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(data);
			oos.close();
			return true;
		}

		catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Unable to write to file " + file.getName(), "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

	}
}