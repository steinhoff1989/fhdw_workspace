package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import controller.Controler;
import model.Functions;

import javax.swing.JSpinner;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class Kryptographie extends JFrame {

	private JPanel pane_main;
	private JTextField txt_euklid_numberA;
	public JTextField getTxt_euklid_numberA() {
		return txt_euklid_numberA;
	}

	private JTextField txt_euklid_numberB;
	private JTextField txt_euklid_result;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField txt_primes_bitLength;
	private JTextField txt_primes_probability;
	private JTextArea txt_primes_result;
	private JProgressBar progressBar;
	private JLabel lblNumberOfTries;
	private JLabel lbl_primes_time;
	private JLabel lbl_primes_tries;
	private JLabel lbl_keys_pNEq;
	private JLabel lbl_keys_gcd;
	private JLabel lbl_keys_eMulD;
	
	public JLabel getLbl_keys_pNEq() {
		return lbl_keys_pNEq;
	}

	public void setLbl_keys_pNEq(JLabel lbl_keys_pNEq) {
		this.lbl_keys_pNEq = lbl_keys_pNEq;
	}

	public JLabel getLbl_keys_gcd() {
		return lbl_keys_gcd;
	}

	public void setLbl_keys_gcd(JLabel lbl_keys_gcd) {
		this.lbl_keys_gcd = lbl_keys_gcd;
	}

	public JLabel getLbl_keys_eMulD() {
		return lbl_keys_eMulD;
	}

	public void setLbl_keys_eMulD(JLabel lbl_keys_eMulD) {
		this.lbl_keys_eMulD = lbl_keys_eMulD;
	}

	private Controler controler;
	private JTextField textField_4;
	private JTextField txt_keys_binaryLength;
	private JTextField txt_keys_probability;
	private JTextArea txt_keys_phiN;
	private JTextArea txt_keys_d;
	private JTextArea txt_keys_e;
	private JTextArea txt_keys_n;
	private JTextArea txt_keys_q;
	private JTextArea txt_keys_p;
	private JLabel lbl_keys_time;
	private JTextArea txt_encrypt_txt;
	private JTextArea txt_encrypt_chiffreTxt;
	private JTextArea txt_encrypt_chiffreNumber;
	private JTextArea txt_encrypt_n;
	private JTextArea txt_encrypt_e;
	private JTextField txt_encrypt_blockSize;
	private JTextField txt_decrypt_k1;
	private JTextArea txt_decrypt_d;
	private JTextArea txt_decrypt_n;
	private JTextArea txt_decrypt_decryptedNumbers;
	private JTextArea txt_decrypt_encryptedTxt;
	private JComboBox<String> cb_keys_charset;
	private JLabel lblMaximumK;
	private JLabel lblMinimumL;
	private JButton btn_keys_calculate;
	private JTextField txt_encrypt_l;
	private JTextField txt_decrypt_l1;
	private JComboBox<String> cb_decrypt_charset1;
	private JTextArea txt_decrypt_decryptedText;
	private JComboBox<String> cb_encrypt_charset;
	private JTextField txt_encrypt_k2;
	private JTextField txt_encrypt_l2;
	private JComboBox<String> cb_encrypt_charset2;
	private JTextField txt_decrypt_k2;
	private JTextField txt_decrypt_l2;
	private JComboBox<String> cb_decrypt_charset2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Kryptographie frame = new Kryptographie();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Kryptographie() {
		setTitle("Kryptographie 2016");
		controler = new Controler(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 600);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Exit");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnNewMenu_2 = new JMenu("Help");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("About");
		mnNewMenu_2.add(mntmNewMenuItem_1);
		pane_main = new JPanel();
		pane_main.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pane_main);
		pane_main.setLayout(null);
		
		JTabbedPane tabbed_main = new JTabbedPane(JTabbedPane.TOP);
		tabbed_main.setBounds(5, 0, 717, 500);
		pane_main.add(tabbed_main);
		
		JPanel panel_createKeys = new JPanel();
//		panel_createKeys.addKeyListener(new KeyAdapter() 
//	    {
//	        public void keyPressed(KeyEvent evt)
//	        {
//	            if(evt.getKeyCode() == KeyEvent.VK_ENTER)
//	            {
//	            	Kryptographie.this.getBtn_keys_calculate().doClick();
//	            }
//	        }
//	    });
		tabbed_main.addTab("Create Keys", null, panel_createKeys, null);
		panel_createKeys.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Binary length per prime");
		lblNewLabel_4.setBounds(12, 13, 140, 16);
		panel_createKeys.add(lblNewLabel_4);
		
		txt_keys_binaryLength = new JTextField();
		txt_keys_binaryLength.setToolTipText("Binary length per prime");
		txt_keys_binaryLength.setText("512");
		txt_keys_binaryLength.setBounds(164, 10, 69, 22);
		panel_createKeys.add(txt_keys_binaryLength);
		txt_keys_binaryLength.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Probability of primes");
		lblNewLabel_5.setBounds(12, 50, 140, 16);
		panel_createKeys.add(lblNewLabel_5);
		
		txt_keys_probability = new JTextField();
		txt_keys_probability.setToolTipText("Probability of primes");
		txt_keys_probability.setText("99.999");
		txt_keys_probability.setColumns(10);
		txt_keys_probability.setBounds(164, 47, 69, 22);
		panel_createKeys.add(txt_keys_probability);
		
		JLabel lblP = new JLabel("p");
		lblP.setBounds(12, 80, 56, 16);
		panel_createKeys.add(lblP);
		
		JLabel lblQ = new JLabel("q");
		lblQ.setBounds(245, 80, 56, 16);
		panel_createKeys.add(lblQ);
		
		JLabel lblN = new JLabel("e");
		lblN.setBounds(12, 220, 56, 16);
		panel_createKeys.add(lblN);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(245, 109, 221, 99);
		panel_createKeys.add(scrollPane_2);
		
		txt_keys_q = new JTextArea();
		scrollPane_2.setViewportView(txt_keys_q);
		txt_keys_q.setWrapStyleWord(true);
		txt_keys_q.setLineWrap(true);
		txt_keys_q.setEditable(false);
		txt_keys_q.setBounds(174, 175, 221, 99);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(478, 109, 222, 99);
		panel_createKeys.add(scrollPane_3);
		
		txt_keys_n = new JTextArea();
		txt_keys_n.setWrapStyleWord(true);
		txt_keys_n.setLineWrap(true);
		txt_keys_n.setEditable(false);
		txt_keys_n.setBounds(398, 225, 222, 99);
		scrollPane_3.setViewportView(txt_keys_n);
		
		JLabel lblN_1 = new JLabel("n");
		lblN_1.setBounds(478, 80, 56, 16);
		panel_createKeys.add(lblN_1);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(12, 247, 221, 99);
		panel_createKeys.add(scrollPane_4);
		
		txt_keys_e = new JTextArea();
		txt_keys_e.setWrapStyleWord(true);
		txt_keys_e.setLineWrap(true);
		txt_keys_e.setEditable(false);
		txt_keys_e.setBounds(146, 276, 221, 99);
		scrollPane_4.setViewportView(txt_keys_e);
		
		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(478, 247, 222, 99);
		panel_createKeys.add(scrollPane_6);
		
		txt_keys_phiN = new JTextArea();
		txt_keys_phiN.setWrapStyleWord(true);
		txt_keys_phiN.setLineWrap(true);
		txt_keys_phiN.setEditable(false);
		txt_keys_phiN.setBounds(427, 276, 222, 99);
		scrollPane_6.setViewportView(txt_keys_phiN);
		
		JLabel lblD = new JLabel("d");
		lblD.setBounds(245, 220, 56, 16);
		panel_createKeys.add(lblD);
		
		JLabel lblPhin = new JLabel("phi(n)");
		lblPhin.setBounds(478, 220, 56, 16);
		panel_createKeys.add(lblPhin);
		
		JLabel lblP_1 = new JLabel("p\u2260q");
		lblP_1.setBounds(12, 444, 30, 16);
		panel_createKeys.add(lblP_1);
		
		lbl_keys_pNEq = new JLabel("");
		lbl_keys_pNEq.setBounds(131, 417, 102, 16);
		panel_createKeys.add(lbl_keys_pNEq);
		
		JLabel lblGcdephin = new JLabel("gcd(e,phi(n))");
		lblGcdephin.setBounds(12, 388, 96, 16);
		panel_createKeys.add(lblGcdephin);
		
		JLabel lblEdmodPhin = new JLabel("e*d \u2261");
		lblEdmodPhin.setBounds(12, 417, 56, 16);
		panel_createKeys.add(lblEdmodPhin);
		
		lbl_keys_gcd = new JLabel("");
		lbl_keys_gcd.setBounds(131, 388, 102, 16);
		panel_createKeys.add(lbl_keys_gcd);
		
		lbl_keys_eMulD = new JLabel("");
		lbl_keys_eMulD.setBounds(131, 444, 102, 16);
		panel_createKeys.add(lbl_keys_eMulD);
		
		btn_keys_calculate = new JButton("Calculate");
		btn_keys_calculate.setBounds(478, 10, 222, 59);
		panel_createKeys.add(btn_keys_calculate);
		
		JLabel Time = new JLabel("Time to generate keys:");
		Time.setBounds(478, 444, 140, 16);
		panel_createKeys.add(Time);
		
		lbl_keys_time = new JLabel("");
		lbl_keys_time.setBounds(609, 444, 91, 16);
		panel_createKeys.add(lbl_keys_time);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 109, 221, 99);
		panel_createKeys.add(scrollPane_1);
		
		txt_keys_p = new JTextArea();
		txt_keys_p.setWrapStyleWord(true);
		txt_keys_p.setLineWrap(true);
		txt_keys_p.setEditable(false);
		txt_keys_p.setBounds(0, 0, 221, 99);
		scrollPane_1.setViewportView(txt_keys_p);
		
		
		
		
		btn_keys_calculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controler.btn_keys_calculate(e);
			}
		});
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(245, 247, 221, 99);
		panel_createKeys.add(scrollPane_5);
		
		txt_keys_d = new JTextArea();
		txt_keys_d.setWrapStyleWord(true);
		txt_keys_d.setLineWrap(true);
		txt_keys_d.setEditable(false);
		txt_keys_d.setBounds(192, 248, 221, 99);
		scrollPane_5.setViewportView(txt_keys_d);
		
		cb_keys_charset = new JComboBox<String>();
		cb_keys_charset.setBounds(299, 11, 167, 20);
		panel_createKeys.add(cb_keys_charset);
		
		
		
		
		JLabel lblNewLabel_8 = new JLabel("Charset");
		lblNewLabel_8.setBounds(243, 14, 46, 14);
		panel_createKeys.add(lblNewLabel_8);
		
		lblMaximumK = new JLabel("Maximum k: ");
		lblMaximumK.setBounds(478, 389, 222, 14);
		panel_createKeys.add(lblMaximumK);
		
		lblMinimumL = new JLabel("Minimum l: ");
		lblMinimumL.setBounds(478, 418, 222, 14);
		panel_createKeys.add(lblMinimumL);
		
		JPanel encrypt = new JPanel();
		tabbed_main.addTab("Encrypt", null, encrypt, null);
		encrypt.setLayout(null);
		
		JLabel lblText = new JLabel("Text");
		lblText.setBounds(12, 13, 56, 16);
		encrypt.add(lblText);
		
		JLabel lblNewLabel_6 = new JLabel("e");
		lblNewLabel_6.setBounds(360, 13, 56, 16);
		encrypt.add(lblNewLabel_6);
		
		JScrollPane scrollPane_8 = new JScrollPane();
		scrollPane_8.setBounds(360, 30, 340, 89);
		encrypt.add(scrollPane_8);
		
		txt_encrypt_e = new JTextArea();
		txt_encrypt_e.setLineWrap(true);
		txt_encrypt_e.setWrapStyleWord(true);
		scrollPane_8.setViewportView(txt_encrypt_e);
		
		JScrollPane scrollPane_9 = new JScrollPane();
		scrollPane_9.setBounds(360, 148, 340, 89);
		encrypt.add(scrollPane_9);
		
		txt_encrypt_n = new JTextArea();
		txt_encrypt_n.setLineWrap(true);
		txt_encrypt_n.setWrapStyleWord(true);
		scrollPane_9.setViewportView(txt_encrypt_n);
		
		JLabel lblN_2 = new JLabel("n");
		lblN_2.setBounds(360, 132, 56, 16);
		encrypt.add(lblN_2);
		
		JLabel lblChiffretext = new JLabel("Encrypted numbers");
		lblChiffretext.setBounds(12, 314, 102, 16);
		encrypt.add(lblChiffretext);
		
		JScrollPane scrollPane_10 = new JScrollPane();
		scrollPane_10.setBounds(12, 341, 340, 116);
		encrypt.add(scrollPane_10);
		
		txt_encrypt_chiffreNumber = new JTextArea();
		txt_encrypt_chiffreNumber.setLineWrap(true);
		txt_encrypt_chiffreNumber.setWrapStyleWord(true);
		scrollPane_10.setViewportView(txt_encrypt_chiffreNumber);
		
		JScrollPane scrollPane_7 = new JScrollPane();
		scrollPane_7.setBounds(12, 30, 340, 207);
		encrypt.add(scrollPane_7);
		
		txt_encrypt_txt = new JTextArea();
		txt_encrypt_txt.setWrapStyleWord(true);
		txt_encrypt_txt.setLineWrap(true);
		scrollPane_7.setViewportView(txt_encrypt_txt);
		
		txt_encrypt_txt.setText("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.");
		
		JScrollPane scrollPane_11 = new JScrollPane();
		scrollPane_11.setBounds(360, 341, 340, 116);
		encrypt.add(scrollPane_11);
		
		txt_encrypt_chiffreTxt = new JTextArea();
		txt_encrypt_chiffreTxt.setLineWrap(true);
		txt_encrypt_chiffreTxt.setWrapStyleWord(true);
		scrollPane_11.setViewportView(txt_encrypt_chiffreTxt);
		
		JLabel lblChiffretext_1 = new JLabel("Encrypted text");
		lblChiffretext_1.setBounds(360, 314, 102, 16);
		encrypt.add(lblChiffretext_1);
		
		JButton btn_encrypt_calculate = new JButton("Encrypt with charset 1");
		btn_encrypt_calculate.setBounds(187, 248, 165, 58);
		encrypt.add(btn_encrypt_calculate);
		btn_encrypt_calculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					controler.btn_encrypt_calculate(e, false);
			}
		});
		
		txt_encrypt_blockSize = new JTextField();
		txt_encrypt_blockSize.setToolTipText("blockSize");
		txt_encrypt_blockSize.setBounds(35, 251, 50, 22);
		encrypt.add(txt_encrypt_blockSize);
		txt_encrypt_blockSize.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("k1:");
		lblNewLabel_7.setBounds(12, 254, 25, 16);
		encrypt.add(lblNewLabel_7);
		
		JLabel lblL = new JLabel("l1:");
		lblL.setBounds(101, 254, 16, 16);
		encrypt.add(lblL);
		
		txt_encrypt_l = new JTextField();
		txt_encrypt_l.setToolTipText("blockSize");
		txt_encrypt_l.setColumns(10);
		txt_encrypt_l.setBounds(127, 251, 50, 22);
		encrypt.add(txt_encrypt_l);
		
		cb_encrypt_charset = new JComboBox<String>();
		cb_encrypt_charset.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				controler.cb_encrypt_charset1_action();
				
			}
		});
		cb_encrypt_charset.setBounds(75, 283, 102, 20);
		encrypt.add(cb_encrypt_charset);
		
		JLabel lblCharset = new JLabel("Charset 1:");
		lblCharset.setBounds(12, 287, 56, 14);
		encrypt.add(lblCharset);
		
		JLabel lblCharset_1 = new JLabel("Charset 2:");
		lblCharset_1.setBounds(360, 283, 62, 14);
		encrypt.add(lblCharset_1);
		
		cb_encrypt_charset2 = new JComboBox<String>();
		cb_encrypt_charset2.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				controler.cb_encrypt_charset2_action();
				
			}
		});
		cb_encrypt_charset2.setBounds(423, 283, 102, 20);
		encrypt.add(cb_encrypt_charset2);
		
		JLabel lblK = new JLabel("k2:");
		lblK.setBounds(360, 254, 25, 16);
		encrypt.add(lblK);
		
		txt_encrypt_k2 = new JTextField();
		txt_encrypt_k2.setToolTipText("blockSize");
		txt_encrypt_k2.setColumns(10);
		txt_encrypt_k2.setBounds(385, 251, 50, 22);
		encrypt.add(txt_encrypt_k2);
		
		JLabel lblL_1 = new JLabel("l2:");
		lblL_1.setBounds(445, 254, 16, 16);
		encrypt.add(lblL_1);
		
		txt_encrypt_l2 = new JTextField();
		txt_encrypt_l2.setToolTipText("blockSize");
		txt_encrypt_l2.setColumns(10);
		txt_encrypt_l2.setBounds(475, 251, 50, 22);
		encrypt.add(txt_encrypt_l2);
		
		JButton btnCalculateSpecialRsa = new JButton("Encrypt with \r\ncharset 1 & 2");
		btnCalculateSpecialRsa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controler.btn_encrypt_calculate(e, true);
			}
		});
		btnCalculateSpecialRsa.setBounds(535, 248, 165, 58);
		encrypt.add(btnCalculateSpecialRsa);
		
		JPanel decrypt = new JPanel();
		decrypt.setLayout(null);
		tabbed_main.addTab("Decrypt", null, decrypt, null);
		
		JLabel lblEncryptedMessage = new JLabel("Encrypted message");
		lblEncryptedMessage.setBounds(12, 13, 112, 16);
		decrypt.add(lblEncryptedMessage);
		
		JLabel lblD_1 = new JLabel("d");
		lblD_1.setBounds(360, 13, 56, 16);
		decrypt.add(lblD_1);
		
		JScrollPane scrollPane_12 = new JScrollPane();
		scrollPane_12.setBounds(360, 30, 340, 89);
		decrypt.add(scrollPane_12);
		
		txt_decrypt_d = new JTextArea();
		txt_decrypt_d.setWrapStyleWord(true);
		txt_decrypt_d.setLineWrap(true);
		scrollPane_12.setViewportView(txt_decrypt_d);
		
		JScrollPane scrollPane_13 = new JScrollPane();
		scrollPane_13.setBounds(360, 148, 340, 89);
		decrypt.add(scrollPane_13);
		
		txt_decrypt_n = new JTextArea();
		txt_decrypt_n.setWrapStyleWord(true);
		txt_decrypt_n.setLineWrap(true);
		scrollPane_13.setViewportView(txt_decrypt_n);
		
		JLabel label_2 = new JLabel("n");
		label_2.setBounds(360, 132, 56, 16);
		decrypt.add(label_2);
		
		JLabel lblDecryptedText = new JLabel("Decrypted numbers");
		lblDecryptedText.setBounds(12, 314, 102, 16);
		decrypt.add(lblDecryptedText);
		
		JScrollPane scrollPane_14 = new JScrollPane();
		scrollPane_14.setBounds(12, 341, 340, 116);
		decrypt.add(scrollPane_14);
		
		txt_decrypt_decryptedNumbers = new JTextArea();
		txt_decrypt_decryptedNumbers.setLineWrap(true);
		txt_decrypt_decryptedNumbers.setWrapStyleWord(true);
		scrollPane_14.setViewportView(txt_decrypt_decryptedNumbers);
		
		JScrollPane scrollPane_15 = new JScrollPane();
		scrollPane_15.setBounds(12, 30, 340, 207);
		decrypt.add(scrollPane_15);
		
		txt_decrypt_encryptedTxt = new JTextArea();
		txt_decrypt_encryptedTxt.setWrapStyleWord(true);
		txt_decrypt_encryptedTxt.setLineWrap(true);
		scrollPane_15.setViewportView(txt_decrypt_encryptedTxt);
		
		JButton btn_decrypt_calculate = new JButton("Decrypt with charset 1");
		btn_decrypt_calculate.setBounds(187, 248, 165, 58);
		decrypt.add(btn_decrypt_calculate);
		
		btn_decrypt_calculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controler.btn_decrypt_calculate(e, false);
			}
		});
		
		txt_decrypt_k1 = new JTextField();
		txt_decrypt_k1.setToolTipText("blockSize");
		txt_decrypt_k1.setColumns(10);
		txt_decrypt_k1.setBounds(35, 251, 50, 22);
		decrypt.add(txt_decrypt_k1);
		
		JLabel lblK_1 = new JLabel("k1:");
		lblK_1.setBounds(12, 254, 25, 16);
		decrypt.add(lblK_1);
		
		JLabel lblCharset_2 = new JLabel("Charset 1:");
		lblCharset_2.setBounds(12, 287, 63, 14);
		decrypt.add(lblCharset_2);
		
		cb_decrypt_charset1 = new JComboBox<String>();
		cb_decrypt_charset1.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				controler.cb_decrypt_charset1_action();
				
			}
		});
		cb_decrypt_charset1.setBounds(75, 283, 102, 20);
		decrypt.add(cb_decrypt_charset1);
		
		
		
		JScrollPane scrollPane_16 = new JScrollPane();
		scrollPane_16.setBounds(360, 341, 340, 116);
		decrypt.add(scrollPane_16);
		
		txt_decrypt_decryptedText = new JTextArea();
		scrollPane_16.setViewportView(txt_decrypt_decryptedText);
		
		JLabel label_1 = new JLabel("Decrypted text");
		label_1.setBounds(360, 314, 102, 16);
		decrypt.add(label_1);
		
		JLabel lblNewLabel_9 = new JLabel("l1:");
		lblNewLabel_9.setBounds(101, 254, 16, 16);
		decrypt.add(lblNewLabel_9);
		
		txt_decrypt_l1 = new JTextField();
		txt_decrypt_l1.setToolTipText("blockSize");
		txt_decrypt_l1.setColumns(10);
		txt_decrypt_l1.setBounds(127, 251, 50, 22);
		decrypt.add(txt_decrypt_l1);
		
		JLabel lblK_2 = new JLabel("k2:");
		lblK_2.setBounds(360, 254, 25, 16);
		decrypt.add(lblK_2);
		
		txt_decrypt_k2 = new JTextField();
		txt_decrypt_k2.setToolTipText("blockSize");
		txt_decrypt_k2.setColumns(10);
		txt_decrypt_k2.setBounds(385, 251, 50, 22);
		decrypt.add(txt_decrypt_k2);
		
		JLabel lblL_2 = new JLabel("l2:");
		lblL_2.setBounds(445, 254, 16, 16);
		decrypt.add(lblL_2);
		
		txt_decrypt_l2 = new JTextField();
		txt_decrypt_l2.setToolTipText("blockSize");
		txt_decrypt_l2.setColumns(10);
		txt_decrypt_l2.setBounds(475, 251, 50, 22);
		decrypt.add(txt_decrypt_l2);
		
		cb_decrypt_charset2 = new JComboBox<String>();
		cb_decrypt_charset2.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				controler.cb_decrypt_charset2_action();
				
			}
		});
		cb_decrypt_charset2.setBounds(423, 283, 102, 20);
		decrypt.add(cb_decrypt_charset2);
		
		JLabel lblCharset_3 = new JLabel("Charset 2:");
		lblCharset_3.setBounds(360, 283, 56, 14);
		decrypt.add(lblCharset_3);
		
		JButton btn_decrypt_calculate2 = new JButton("Decrypt with charset 1 & 2");
		btn_decrypt_calculate2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controler.btn_decrypt_calculate(e, true);
			}
		});
		btn_decrypt_calculate2.setBounds(535, 248, 165, 58);
		decrypt.add(btn_decrypt_calculate2);
		
		JPanel panel = new JPanel();
		tabbed_main.addTab("Industrial prime", null, panel, null);
		panel.setLayout(null);
		
		JLabel lbl_primes_bitLength = new JLabel("Bit length");
		lbl_primes_bitLength.setBounds(12, 13, 66, 16);
		panel.add(lbl_primes_bitLength);
		
		txt_primes_bitLength = new JTextField();
		txt_primes_bitLength.setBounds(90, 10, 116, 22);
		panel.add(txt_primes_bitLength);
		txt_primes_bitLength.setColumns(10);
		
		txt_primes_probability = new JTextField();
		txt_primes_probability.setColumns(10);
		txt_primes_probability.setBounds(90, 45, 116, 22);
		panel.add(txt_primes_probability);
		
		JLabel lbl_primes_probability = new JLabel("Probability");
		lbl_primes_probability.setBounds(12, 48, 66, 16);
		panel.add(lbl_primes_probability);
		JLabel lbl_primes_result = new JLabel("Prime");
		lbl_primes_result.setBounds(12, 83, 66, 16);
		panel.add(lbl_primes_result);
		
		JButton btn_primes_calculate = new JButton("Calculate");
		btn_primes_calculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controler.btn_primes_calculate_clicked(e);
			}
		});
		
		btn_primes_calculate.setBounds(12, 260, 217, 25);
		panel.add(btn_primes_calculate);
		
				JButton btn_primes_empty = new JButton("Empty");
				btn_primes_empty.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controler.btn_primes_empty_clicked(e);
					}
				});
				btn_primes_empty.setBounds(241, 260, 216, 25);
				panel.add(btn_primes_empty);
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(90, 83, 367, 164);
				panel.add(scrollPane);
				
				txt_primes_result = new JTextArea();
				scrollPane.setViewportView(txt_primes_result);
				txt_primes_result.setEditable(false);
				txt_primes_result.setWrapStyleWord(true);
				txt_primes_result.setLineWrap(true);
				
				JLabel lblNewLabel_2 = new JLabel("Time:");
				lblNewLabel_2.setBounds(231, 13, 94, 16);
				panel.add(lblNewLabel_2);
				
				JSeparator separator = new JSeparator();
				separator.setOrientation(SwingConstants.VERTICAL);
				separator.setBackground(UIManager.getColor("Separator.background"));
				separator.setForeground(UIManager.getColor("Separator.foreground"));
				separator.setBounds(218, 13, 1, 54);
				panel.add(separator);
				
				lblNumberOfTries = new JLabel("Number of tries:");
				lblNumberOfTries.setBounds(231, 48, 94, 16);
				panel.add(lblNumberOfTries);
				
				lbl_primes_time = new JLabel("");
				lbl_primes_time.setBounds(337, 13, 56, 16);
				panel.add(lbl_primes_time);
				
				lbl_primes_tries = new JLabel("");
				lbl_primes_tries.setBounds(337, 48, 56, 16);
				panel.add(lbl_primes_tries);
		
		JPanel panel_euklid = new JPanel();
		tabbed_main.addTab("Euklid", null, panel_euklid, null);
		panel_euklid.setLayout(null);
		
		JLabel lbl_euklid_numberA = new JLabel("Number a");
		lbl_euklid_numberA.setBounds(10, 14, 56, 14);
		panel_euklid.add(lbl_euklid_numberA);
		
		JLabel lbl_euklid_numberB = new JLabel("Number b");
		lbl_euklid_numberB.setBounds(10, 45, 56, 14);
		panel_euklid.add(lbl_euklid_numberB);
		
		txt_euklid_numberA = new JTextField();
		txt_euklid_numberA.setBounds(78, 11, 86, 20);
		panel_euklid.add(txt_euklid_numberA);
		txt_euklid_numberA.setColumns(10);
		
		txt_euklid_numberB = new JTextField();
		txt_euklid_numberB.setColumns(10);
		txt_euklid_numberB.setBounds(78, 42, 86, 20);
		panel_euklid.add(txt_euklid_numberB);
		
		JLabel lbl_euklid_result = new JLabel("gcd");
		lbl_euklid_result.setBounds(10, 76, 46, 14);
		panel_euklid.add(lbl_euklid_result);
		
		txt_euklid_result = new JTextField();
		txt_euklid_result.setColumns(10);
		txt_euklid_result.setBounds(78, 73, 86, 20);
		panel_euklid.add(txt_euklid_result);
		
		JButton btn_euklid_calc = new JButton("Calculate");
		btn_euklid_calc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btn_euklid_calc.setBackground(UIManager.getColor("Button.background"));
		btn_euklid_calc.setBounds(10, 267, 447, 23);
		panel_euklid.add(btn_euklid_calc);
		
		JPanel panel_powerFast = new JPanel();
		tabbed_main.addTab("Power fast", null, panel_powerFast, null);
		panel_powerFast.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Basis");
		lblNewLabel.setBounds(12, 13, 56, 16);
		panel_powerFast.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Exponent");
		lblNewLabel_1.setBounds(12, 48, 56, 16);
		panel_powerFast.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(80, 10, 116, 22);
		panel_powerFast.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(80, 45, 116, 22);
		panel_powerFast.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblModul = new JLabel("Modul");
		lblModul.setBounds(12, 83, 56, 16);
		panel_powerFast.add(lblModul);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(80, 80, 116, 22);
		panel_powerFast.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(80, 115, 116, 22);
		panel_powerFast.add(textField_3);
		
		JLabel lblResult = new JLabel("Result");
		lblResult.setBounds(12, 118, 56, 16);
		panel_powerFast.add(lblResult);
		
		JButton btnNewButton = new JButton("Calculate");
		btnNewButton.setBounds(12, 265, 97, 25);
		panel_powerFast.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Empty");
		btnNewButton_1.setBounds(120, 265, 97, 25);
		panel_powerFast.add(btnNewButton_1);
		
		JPanel panel_block_chiffre = new JPanel();
		tabbed_main.addTab("Block-chiffre", null, panel_block_chiffre, null);
		panel_block_chiffre.setLayout(null);
		
		JLabel lblBlockLength = new JLabel("Block length");
		lblBlockLength.setBounds(12, 13, 68, 16);
		panel_block_chiffre.add(lblBlockLength);
		
		textField_4 = new JTextField();
		textField_4.setBounds(92, 10, 116, 22);
		panel_block_chiffre.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblCleartext = new JLabel("Clear-Text");
		lblCleartext.setBounds(12, 42, 68, 16);
		panel_block_chiffre.add(lblCleartext);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(12, 71, 196, 219);
		panel_block_chiffre.add(scrollBar);
		
		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setEnabled(false);
		toolBar_1.setFloatable(false);
		toolBar_1.setBounds(5, 509, 101, 18);
		pane_main.add(toolBar_1);
		
		JLabel lblNewLabel_3 = new JLabel("v 1.0");
		toolBar_1.add(lblNewLabel_3);
		
		progressBar = new JProgressBar();
		progressBar.setBounds(652, 513, 70, 14);
		pane_main.add(progressBar);
//		progressBar.setStringPainted(true);
//		progressBar.setMaximum(10);
//		progressBar.setMinimum(0);
//		progressBar.setValue(5);
	
		setAllComboBoxes();
		
	}
	
	public JTextField getTxt_decrypt_k1() {
		return txt_decrypt_k1;
	}

	public void setTxt_decrypt_k1(JTextField txt_decrypt_k1) {
		this.txt_decrypt_k1 = txt_decrypt_k1;
	}

	public JTextField getTxt_decrypt_l1() {
		return txt_decrypt_l1;
	}

	public void setTxt_decrypt_l1(JTextField txt_decrypt_l1) {
		this.txt_decrypt_l1 = txt_decrypt_l1;
	}

	public JComboBox<String> getCb_decrypt_charset1() {
		return cb_decrypt_charset1;
	}

	public void setCb_decrypt_charset1(JComboBox<String> cb_decrypt_charset1) {
		this.cb_decrypt_charset1 = cb_decrypt_charset1;
	}

	public JTextField getTxt_decrypt_k2() {
		return txt_decrypt_k2;
	}

	public void setTxt_decrypt_k2(JTextField txt_decrypt_k2) {
		this.txt_decrypt_k2 = txt_decrypt_k2;
	}

	public JTextField getTxt_decrypt_l2() {
		return txt_decrypt_l2;
	}

	public void setTxt_decrypt_l2(JTextField txt_decrypt_l2) {
		this.txt_decrypt_l2 = txt_decrypt_l2;
	}

	public JComboBox<String> getCb_decrypt_charset2() {
		return cb_decrypt_charset2;
	}

	public void setCb_decrypt_charset2(JComboBox<String> cb_decrypt_charset2) {
		this.cb_decrypt_charset2 = cb_decrypt_charset2;
	}

	private void setAllComboBoxes() {
		List<String> availableCharsets = Functions.getAvailableCharsetNames();
		
		for(int i=0;i<availableCharsets.size();i++){
			cb_keys_charset.addItem(availableCharsets.get(i));
			cb_encrypt_charset.addItem(availableCharsets.get(i));
			cb_encrypt_charset2.addItem(availableCharsets.get(i));
			cb_decrypt_charset1.addItem(availableCharsets.get(i));
			cb_decrypt_charset2.addItem(availableCharsets.get(i));
		}
	}

	public JTextField getTxt_encrypt_k2() {
		return txt_encrypt_k2;
	}

	public void setTxt_encrypt_k2(JTextField txt_encrypt_k2) {
		this.txt_encrypt_k2 = txt_encrypt_k2;
	}

	public JTextField getTxt_encrypt_l2() {
		return txt_encrypt_l2;
	}

	public void setTxt_encrypt_l2(JTextField txt_encrypt_l2) {
		this.txt_encrypt_l2 = txt_encrypt_l2;
	}

	public JComboBox<String> getCb_encrypt_charset2() {
		return cb_encrypt_charset2;
	}

	public void setCb_encrypt_charset2(JComboBox<String> cb_encrypt_charset2) {
		this.cb_encrypt_charset2 = cb_encrypt_charset2;
	}

	public JTextField getTxt_encrypt_blockSize() {
		return txt_encrypt_blockSize;
	}

	public JTextField getTxt_encrypt_l() {
		return txt_encrypt_l;
	}

	public JComboBox<String> getCb_encrypt_charset() {
		return cb_encrypt_charset;
	}

	public JComboBox<String> getCb_decrypt_charset() {
		return cb_decrypt_charset1;
	}

	public void setCb_decrypt_charset(JComboBox<String> cb_decrypt_charset) {
		this.cb_decrypt_charset1 = cb_decrypt_charset;
	}

	public JTextArea getTxt_decrypt_decryptedNumbers() {
		return txt_decrypt_decryptedNumbers;
	}

	public void setTxt_decrypt_decryptedNumbers(JTextArea txt_decrypt_decryptedNumbers) {
		this.txt_decrypt_decryptedNumbers = txt_decrypt_decryptedNumbers;
	}

	public JTextField getTxt_decrypt_l() {
		return txt_decrypt_l1;
	}

	public void setTxt_decrypt_l(JTextField txt_decrypt_l) {
		this.txt_decrypt_l1 = txt_decrypt_l;
	}

	public JTextArea getTxt_decrypt_decryptedText() {
		return txt_decrypt_decryptedText;
	}

	public void setTxt_decrypt_decryptedText(JTextArea txt_decrypt_decryptedText) {
		this.txt_decrypt_decryptedText = txt_decrypt_decryptedText;
	}

	public JTextField getTxt_encrypt_l1() {
		return txt_encrypt_l;
	}

	public void setTxt_encrypt_l(JTextField txt_encrypt_l) {
		this.txt_encrypt_l = txt_encrypt_l;
	}

	public JComboBox<String> getCb_encrypt_charset1() {
		return cb_encrypt_charset;
	}

	public void setCb_encrypt_charset(JComboBox<String> cb_encrypt_charset) {
		this.cb_encrypt_charset = cb_encrypt_charset;
	}

	public JButton getBtn_keys_calculate() {
		return btn_keys_calculate;
	}

	public void setBtn_keys_calculate(JButton btn_keys_calculate) {
		this.btn_keys_calculate = btn_keys_calculate;
	}

	public JLabel getLblMinimumL() {
		return lblMinimumL;
	}

	public void setLblMinimumL(JLabel lblMinimumL) {
		this.lblMinimumL = lblMinimumL;
	}

	public JLabel getLblMaximumK() {
		return lblMaximumK;
	}

	public void setLblMaximumK(JLabel lblMaximumK) {
		this.lblMaximumK = lblMaximumK;
	}

	public JComboBox<String> getCb_keys_charset() {
		return cb_keys_charset;
	}

	public void setCb_keys_charset(JComboBox<String> cb_keys_charset) {
		this.cb_keys_charset = cb_keys_charset;
	}

	public JTextArea getTxt_decrypt_d() {
		return txt_decrypt_d;
	}

	public void setTxt_decrypt_d(JTextArea txt_decrypt_d) {
		this.txt_decrypt_d = txt_decrypt_d;
	}

	public JTextArea getTxt_decrypt_n() {
		return txt_decrypt_n;
	}

	public void setTxt_decrypt_n(JTextArea txt_decrypt_n) {
		this.txt_decrypt_n = txt_decrypt_n;
	}

	public JTextArea getTxt_decrypt_decryptedTxt() {
		return txt_decrypt_decryptedText;
	}

	public void setTxt_decrypt_decryptedTxt(JTextArea txt_decrypt_decryptedTxt) {
		this.txt_decrypt_decryptedText = txt_decrypt_decryptedTxt;
	}

	public JTextArea getTxt_decrypt_encryptedTxt() {
		return txt_decrypt_encryptedTxt;
	}

	public void setTxt_decrypt_encryptedTxt(JTextArea txt_decrypt_encryptedTxt) {
		this.txt_decrypt_encryptedTxt = txt_decrypt_encryptedTxt;
	}

	public JTextField getTxt_decrypt_blockSize() {
		return txt_decrypt_k1;
	}

	public void setTxt_decrypt_blockSize(JTextField txt_decrypt_blockSize) {
		this.txt_decrypt_k1 = txt_decrypt_blockSize;
	}

	public JTextField getTxt_encrypt_k1() {
		return txt_encrypt_blockSize;
	}

	public void setTxt_encrypt_blockSize(JTextField txt_encrypt_blockSize) {
		this.txt_encrypt_blockSize = txt_encrypt_blockSize;
	}

	public JTextArea getTxt_encrypt_txt() {
		return txt_encrypt_txt;
	}

	public void setTxt_encrypt_txt(JTextArea txt_encrypt_txt) {
		this.txt_encrypt_txt = txt_encrypt_txt;
	}

	public JTextArea getTxt_encrypt_chiffreTxt() {
		return txt_encrypt_chiffreTxt;
	}

	public void setTxt_encrypt_chiffreTxt(JTextArea txt_encrypt_chiffreTxt) {
		this.txt_encrypt_chiffreTxt = txt_encrypt_chiffreTxt;
	}

	public JTextArea getTxt_encrypt_chiffreNumber() {
		return txt_encrypt_chiffreNumber;
	}

	public void setTxt_encrypt_chiffreNumber(JTextArea txt_encrypt_chiffreNumber) {
		this.txt_encrypt_chiffreNumber = txt_encrypt_chiffreNumber;
	}

	public JTextArea getTxt_encrypt_n() {
		return txt_encrypt_n;
	}

	public void setTxt_encrypt_n(JTextArea txt_encrypt_n) {
		this.txt_encrypt_n = txt_encrypt_n;
	}

	public JTextArea getTxt_encrypt_e() {
		return txt_encrypt_e;
	}

	public void setTxt_encrypt_e(JTextArea txt_encrypt_e) {
		this.txt_encrypt_e = txt_encrypt_e;
	}

	public JLabel getLbl_keys_time() {
		return lbl_keys_time;
	}

	public void setLbl_keys_time(JLabel lbl_keys_time) {
		this.lbl_keys_time = lbl_keys_time;
	}

	public JTextArea getTxt_keys_phiN() {
		return txt_keys_phiN;
	}

	public void setTxt_keys_phiN(JTextArea txt_keys_phiN) {
		this.txt_keys_phiN = txt_keys_phiN;
	}

	public JTextArea getTxt_keys_d() {
		return txt_keys_d;
	}

	public void setTxt_keys_d(JTextArea txt_keys_d) {
		this.txt_keys_d = txt_keys_d;
	}

	public JTextArea getTxt_keys_e() {
		return txt_keys_e;
	}

	public void setTxt_keys_e(JTextArea txt_keys_e) {
		this.txt_keys_e = txt_keys_e;
	}

	public JTextArea getTxt_keys_n() {
		return txt_keys_n;
	}

	public void setTxt_keys_n(JTextArea txt_keys_n) {
		this.txt_keys_n = txt_keys_n;
	}

	public JTextArea getTxt_keys_q() {
		return txt_keys_q;
	}

	public void setTxt_keys_q(JTextArea txt_keys_q) {
		this.txt_keys_q = txt_keys_q;
	}

	public JTextArea getTxt_keys_p() {
		return txt_keys_p;
	}

	public void setTxt_keys_p(JTextArea txt_keys_p) {
		this.txt_keys_p = txt_keys_p;
	}

	public JTextField getTxt_keys_binaryLength() {
		return txt_keys_binaryLength;
	}

	public void setTxt_keys_binaryLength(JTextField txt_keys_binaryLength) {
		this.txt_keys_binaryLength = txt_keys_binaryLength;
	}

	public JTextField getTxt_keys_probability() {
		return txt_keys_probability;
	}

	public void setTxt_keys_probability(JTextField txt_keys_probability) {
		this.txt_keys_probability = txt_keys_probability;
	}

	public String getLbl_primes_time(){
		return this.lbl_primes_time.getText();
	}
	
	public void setLbl_primes_time(String text){
		this.lbl_primes_time.setText(text);
	}
	
	public String getLbl_primes_tries(){
		return this.lbl_primes_tries.getText();
	}
	
	public void setLbl_primes_tries(String text){
		this.lbl_primes_tries.setText(text);
	}
	
	public void setTxt_euklid_numberA(JTextField txt_euklid_numberA) {
		this.txt_euklid_numberA = txt_euklid_numberA;
	}

	public JTextField getTxt_euklid_numberB() {
		return txt_euklid_numberB;
	}

	public void setTxt_euklid_numberB(JTextField txt_euklid_numberB) {
		this.txt_euklid_numberB = txt_euklid_numberB;
	}

	public JTextField getTxt_euklid_result() {
		return txt_euklid_result;
	}

	public void setTxt_euklid_result(JTextField txt_euklid_result) {
		this.txt_euklid_result = txt_euklid_result;
	}

	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public JTextField getTextField_1() {
		return textField_1;
	}

	public void setTextField_1(JTextField textField_1) {
		this.textField_1 = textField_1;
	}

	public JTextField getTextField_2() {
		return textField_2;
	}

	public void setTextField_2(JTextField textField_2) {
		this.textField_2 = textField_2;
	}

	public JTextField getTextField_3() {
		return textField_3;
	}

	public void setTextField_3(JTextField textField_3) {
		this.textField_3 = textField_3;
	}

	public JTextField getTxt_primes_bitLength() {
		return txt_primes_bitLength;
	}

	public void setTxt_primes_bitLength(JTextField txt_primes_bitLength) {
		this.txt_primes_bitLength = txt_primes_bitLength;
	}

	public JTextField getTxt_primes_probability() {
		return txt_primes_probability;
	}

	public void setTxt_primes_probability(JTextField txt_primes_probability) {
		this.txt_primes_probability = txt_primes_probability;
	}

	public JTextArea getTxt_primes_result() {
		return txt_primes_result;
	}

	public void setTxt_primes_result(JTextArea txt_primes_result) {
		this.txt_primes_result = txt_primes_result;
	}
	
	public void activateProgressbar(){
		this.progressBar.setIndeterminate(true);
	}
	
	public void deactivateProgressbar(){
		this.progressBar.setIndeterminate(false);
	}
}
