package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import controller.RSAController;
import model.Encoding;

@SuppressWarnings("serial")
public class RSAView extends JFrame {

	private final JPanel pane_main;
	private JTextField txt_primes_bitLength;
	private JTextField txt_primes_probability;
	private JTextArea txt_primes_result;
	private final JProgressBar progressBar;
	private JLabel lbl_keys_pNEq;
	private JLabel lbl_keys_gcd;
	private JLabel lbl_keys_eMulD;

	public JLabel getLbl_keys_pNEq() {
		return this.lbl_keys_pNEq;
	}

	public void setLbl_keys_pNEq(final JLabel lbl_keys_pNEq) {
		this.lbl_keys_pNEq = lbl_keys_pNEq;
	}

	public JLabel getLbl_keys_gcd() {
		return this.lbl_keys_gcd;
	}

	public void setLbl_keys_gcd(final JLabel lbl_keys_gcd) {
		this.lbl_keys_gcd = lbl_keys_gcd;
	}

	public JLabel getLbl_keys_eMulD() {
		return this.lbl_keys_eMulD;
	}

	public void setLbl_keys_eMulD(final JLabel lbl_keys_eMulD) {
		this.lbl_keys_eMulD = lbl_keys_eMulD;
	}

	private final RSAController controler;
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
	private JTextArea txt_sign_text;
	private JTextArea txt_sign_n;
	private JButton btn_sign_sign;
	private JTextArea txt_sign_signature;
	private JComboBox<String> cb_sign_hash;
	private JTextArea txt_sign_d;
	private JTextArea txt_verifySig_signature;
	private JTextArea txt_verifySig_e;
	private JTextArea txt_verifySig_n;
	private JComboBox<String> cb_verifySig_hashFunction;
	private JButton btn_verifySig_verify;
	private JTextArea txt_verifySig_signedChiffrat;
	private JLabel lbl_verifySig_result;

	/**
	 * Launch the application.
	 */
	public static void main(final String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (final Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					final RSAView frame = new RSAView();
					frame.setVisible(true);
				} catch (final Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RSAView() {
		this.setTitle("Kryptographie 2016");
		this.controler = new RSAController(this);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 750, 600);

		final JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);

		final JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);

		final JMenuItem mntmNewMenuItem = new JMenuItem("Exit");
		mnNewMenu.add(mntmNewMenuItem);

		final JMenu mnNewMenu_2 = new JMenu("Help");
		menuBar.add(mnNewMenu_2);

		final JMenuItem mntmNewMenuItem_1 = new JMenuItem("About");
		mnNewMenu_2.add(mntmNewMenuItem_1);
		this.pane_main = new JPanel();
		this.pane_main.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(this.pane_main);
		this.pane_main.setLayout(null);

		final JTabbedPane tabbed_main = new JTabbedPane(JTabbedPane.TOP);
		tabbed_main.setBounds(5, 0, 717, 500);
		this.pane_main.add(tabbed_main);

		final JPanel panel_createKeys = new JPanel();
		// panel_createKeys.addKeyListener(new KeyAdapter()
		// {
		// public void keyPressed(KeyEvent evt)
		// {
		// if(evt.getKeyCode() == KeyEvent.VK_ENTER)
		// {
		// Kryptographie.this.getBtn_keys_calculate().doClick();
		// }
		// }
		// });
		tabbed_main.addTab("Create Keys", null, panel_createKeys, null);
		panel_createKeys.setLayout(null);

		final JLabel lblNewLabel_4 = new JLabel("Binary length per prime");
		lblNewLabel_4.setBounds(12, 13, 140, 16);
		panel_createKeys.add(lblNewLabel_4);

		this.txt_keys_binaryLength = new JTextField();
		this.txt_keys_binaryLength.setToolTipText("Binary length per prime");
		this.txt_keys_binaryLength.setText("512");
		this.txt_keys_binaryLength.setBounds(164, 10, 69, 22);
		panel_createKeys.add(this.txt_keys_binaryLength);
		this.txt_keys_binaryLength.setColumns(10);

		final JLabel lblNewLabel_5 = new JLabel("Probability of primes");
		lblNewLabel_5.setBounds(12, 50, 140, 16);
		panel_createKeys.add(lblNewLabel_5);

		this.txt_keys_probability = new JTextField();
		this.txt_keys_probability.setToolTipText("Probability of primes");
		this.txt_keys_probability.setText("99.999");
		this.txt_keys_probability.setColumns(10);
		this.txt_keys_probability.setBounds(164, 47, 69, 22);
		panel_createKeys.add(this.txt_keys_probability);

		final JLabel lblP = new JLabel("p");
		lblP.setBounds(12, 80, 56, 16);
		panel_createKeys.add(lblP);

		final JLabel lblQ = new JLabel("q");
		lblQ.setBounds(245, 80, 56, 16);
		panel_createKeys.add(lblQ);

		final JLabel lblN = new JLabel("e");
		lblN.setBounds(12, 220, 56, 16);
		panel_createKeys.add(lblN);

		final JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(245, 109, 221, 99);
		panel_createKeys.add(scrollPane_2);

		this.txt_keys_q = new JTextArea();
		scrollPane_2.setViewportView(this.txt_keys_q);
		this.txt_keys_q.setWrapStyleWord(true);
		this.txt_keys_q.setLineWrap(true);
		this.txt_keys_q.setEditable(false);
		this.txt_keys_q.setBounds(174, 175, 221, 99);

		final JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(478, 109, 222, 99);
		panel_createKeys.add(scrollPane_3);

		this.txt_keys_n = new JTextArea();
		this.txt_keys_n.setWrapStyleWord(true);
		this.txt_keys_n.setLineWrap(true);
		this.txt_keys_n.setEditable(false);
		this.txt_keys_n.setBounds(398, 225, 222, 99);
		scrollPane_3.setViewportView(this.txt_keys_n);

		final JLabel lblN_1 = new JLabel("n");
		lblN_1.setBounds(478, 80, 56, 16);
		panel_createKeys.add(lblN_1);

		final JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(12, 247, 221, 99);
		panel_createKeys.add(scrollPane_4);

		this.txt_keys_e = new JTextArea();
		this.txt_keys_e.setWrapStyleWord(true);
		this.txt_keys_e.setLineWrap(true);
		this.txt_keys_e.setEditable(false);
		this.txt_keys_e.setBounds(146, 276, 221, 99);
		scrollPane_4.setViewportView(this.txt_keys_e);

		final JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(478, 247, 222, 99);
		panel_createKeys.add(scrollPane_6);

		this.txt_keys_phiN = new JTextArea();
		this.txt_keys_phiN.setWrapStyleWord(true);
		this.txt_keys_phiN.setLineWrap(true);
		this.txt_keys_phiN.setEditable(false);
		this.txt_keys_phiN.setBounds(427, 276, 222, 99);
		scrollPane_6.setViewportView(this.txt_keys_phiN);

		final JLabel lblD = new JLabel("d");
		lblD.setBounds(245, 220, 56, 16);
		panel_createKeys.add(lblD);

		final JLabel lblPhin = new JLabel("phi(n)");
		lblPhin.setBounds(478, 220, 56, 16);
		panel_createKeys.add(lblPhin);

		final JLabel lblP_1 = new JLabel("p\u2260q");
		lblP_1.setBounds(12, 444, 30, 16);
		panel_createKeys.add(lblP_1);

		this.lbl_keys_pNEq = new JLabel("");
		this.lbl_keys_pNEq.setBounds(131, 417, 102, 16);
		panel_createKeys.add(this.lbl_keys_pNEq);

		final JLabel lblGcdephin = new JLabel("gcd(e,phi(n))");
		lblGcdephin.setBounds(12, 388, 96, 16);
		panel_createKeys.add(lblGcdephin);

		final JLabel lblEdmodPhin = new JLabel("e*d \u2261");
		lblEdmodPhin.setBounds(12, 417, 56, 16);
		panel_createKeys.add(lblEdmodPhin);

		this.lbl_keys_gcd = new JLabel("");
		this.lbl_keys_gcd.setBounds(131, 388, 102, 16);
		panel_createKeys.add(this.lbl_keys_gcd);

		this.lbl_keys_eMulD = new JLabel("");
		this.lbl_keys_eMulD.setBounds(131, 444, 102, 16);
		panel_createKeys.add(this.lbl_keys_eMulD);

		this.btn_keys_calculate = new JButton("Calculate");
		this.btn_keys_calculate.setBounds(478, 10, 222, 59);
		panel_createKeys.add(this.btn_keys_calculate);

		final JLabel Time = new JLabel("Time to generate keys:");
		Time.setBounds(478, 444, 140, 16);
		panel_createKeys.add(Time);

		this.lbl_keys_time = new JLabel("");
		this.lbl_keys_time.setBounds(609, 444, 91, 16);
		panel_createKeys.add(this.lbl_keys_time);

		final JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 109, 221, 99);
		panel_createKeys.add(scrollPane_1);

		this.txt_keys_p = new JTextArea();
		this.txt_keys_p.setWrapStyleWord(true);
		this.txt_keys_p.setLineWrap(true);
		this.txt_keys_p.setEditable(false);
		this.txt_keys_p.setBounds(0, 0, 221, 99);
		scrollPane_1.setViewportView(this.txt_keys_p);

		this.btn_keys_calculate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				RSAView.this.controler.btn_keys_calculate(e);
			}
		});

		final JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(245, 247, 221, 99);
		panel_createKeys.add(scrollPane_5);

		this.txt_keys_d = new JTextArea();
		this.txt_keys_d.setWrapStyleWord(true);
		this.txt_keys_d.setLineWrap(true);
		this.txt_keys_d.setEditable(false);
		this.txt_keys_d.setBounds(192, 248, 221, 99);
		scrollPane_5.setViewportView(this.txt_keys_d);

		this.cb_keys_charset = new JComboBox<String>();
		this.cb_keys_charset.setBounds(299, 11, 167, 20);
		panel_createKeys.add(this.cb_keys_charset);

		final JLabel lblNewLabel_8 = new JLabel("Charset");
		lblNewLabel_8.setBounds(243, 14, 46, 14);
		panel_createKeys.add(lblNewLabel_8);

		this.lblMaximumK = new JLabel("Maximum k: ");
		this.lblMaximumK.setBounds(478, 389, 222, 14);
		panel_createKeys.add(this.lblMaximumK);

		this.lblMinimumL = new JLabel("Minimum l: ");
		this.lblMinimumL.setBounds(478, 418, 222, 14);
		panel_createKeys.add(this.lblMinimumL);

		final JPanel encrypt = new JPanel();
		tabbed_main.addTab("Encrypt", null, encrypt, null);
		encrypt.setLayout(null);

		final JLabel lblText = new JLabel("Text");
		lblText.setBounds(12, 13, 56, 16);
		encrypt.add(lblText);

		final JLabel lblNewLabel_6 = new JLabel("e");
		lblNewLabel_6.setBounds(360, 13, 56, 16);
		encrypt.add(lblNewLabel_6);

		final JScrollPane scrollPane_8 = new JScrollPane();
		scrollPane_8.setBounds(360, 30, 340, 89);
		encrypt.add(scrollPane_8);

		this.txt_encrypt_e = new JTextArea();
		this.txt_encrypt_e.setLineWrap(true);
		this.txt_encrypt_e.setWrapStyleWord(true);
		scrollPane_8.setViewportView(this.txt_encrypt_e);

		final JScrollPane scrollPane_9 = new JScrollPane();
		scrollPane_9.setBounds(360, 148, 340, 89);
		encrypt.add(scrollPane_9);

		this.txt_encrypt_n = new JTextArea();
		this.txt_encrypt_n.setLineWrap(true);
		this.txt_encrypt_n.setWrapStyleWord(true);
		scrollPane_9.setViewportView(this.txt_encrypt_n);

		final JLabel lblN_2 = new JLabel("n");
		lblN_2.setBounds(360, 132, 56, 16);
		encrypt.add(lblN_2);

		final JLabel lblChiffretext = new JLabel("Encrypted numbers");
		lblChiffretext.setBounds(12, 314, 102, 16);
		encrypt.add(lblChiffretext);

		final JScrollPane scrollPane_10 = new JScrollPane();
		scrollPane_10.setBounds(12, 341, 340, 116);
		encrypt.add(scrollPane_10);

		this.txt_encrypt_chiffreNumber = new JTextArea();
		this.txt_encrypt_chiffreNumber.setLineWrap(true);
		this.txt_encrypt_chiffreNumber.setWrapStyleWord(true);
		scrollPane_10.setViewportView(this.txt_encrypt_chiffreNumber);

		final JScrollPane scrollPane_7 = new JScrollPane();
		scrollPane_7.setBounds(12, 30, 340, 207);
		encrypt.add(scrollPane_7);

		this.txt_encrypt_txt = new JTextArea();
		this.txt_encrypt_txt.setWrapStyleWord(true);
		this.txt_encrypt_txt.setLineWrap(true);
		scrollPane_7.setViewportView(this.txt_encrypt_txt);

		this.txt_encrypt_txt.setText(
				"Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.");

		final JScrollPane scrollPane_11 = new JScrollPane();
		scrollPane_11.setBounds(360, 341, 340, 116);
		encrypt.add(scrollPane_11);

		this.txt_encrypt_chiffreTxt = new JTextArea();
		this.txt_encrypt_chiffreTxt.setLineWrap(true);
		this.txt_encrypt_chiffreTxt.setWrapStyleWord(true);
		scrollPane_11.setViewportView(this.txt_encrypt_chiffreTxt);

		final JLabel lblChiffretext_1 = new JLabel("Encrypted text");
		lblChiffretext_1.setBounds(360, 314, 102, 16);
		encrypt.add(lblChiffretext_1);

		final JButton btn_encrypt_calculate = new JButton("Encrypt with charset 1");
		btn_encrypt_calculate.setBounds(187, 248, 165, 58);
		encrypt.add(btn_encrypt_calculate);
		btn_encrypt_calculate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				RSAView.this.controler.btn_encrypt_calculate(e, false);
			}
		});

		this.txt_encrypt_blockSize = new JTextField();
		this.txt_encrypt_blockSize.setToolTipText("blockSize");
		this.txt_encrypt_blockSize.setBounds(35, 251, 50, 22);
		encrypt.add(this.txt_encrypt_blockSize);
		this.txt_encrypt_blockSize.setColumns(10);

		final JLabel lblNewLabel_7 = new JLabel("k1:");
		lblNewLabel_7.setBounds(12, 254, 25, 16);
		encrypt.add(lblNewLabel_7);

		final JLabel lblL = new JLabel("l1:");
		lblL.setBounds(101, 254, 16, 16);
		encrypt.add(lblL);

		this.txt_encrypt_l = new JTextField();
		this.txt_encrypt_l.setToolTipText("blockSize");
		this.txt_encrypt_l.setColumns(10);
		this.txt_encrypt_l.setBounds(127, 251, 50, 22);
		encrypt.add(this.txt_encrypt_l);

		this.cb_encrypt_charset = new JComboBox<String>();
		this.cb_encrypt_charset.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(final ItemEvent e) {
				RSAView.this.controler.cb_encrypt_charset1_action();

			}
		});
		this.cb_encrypt_charset.setBounds(75, 283, 102, 20);
		encrypt.add(this.cb_encrypt_charset);

		final JLabel lblCharset = new JLabel("Charset 1:");
		lblCharset.setBounds(12, 287, 56, 14);
		encrypt.add(lblCharset);

		final JLabel lblCharset_1 = new JLabel("Charset 2:");
		lblCharset_1.setBounds(360, 283, 62, 14);
		encrypt.add(lblCharset_1);

		this.cb_encrypt_charset2 = new JComboBox<String>();
		this.cb_encrypt_charset2.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(final ItemEvent e) {
				RSAView.this.controler.cb_encrypt_charset2_action();

			}
		});
		this.cb_encrypt_charset2.setBounds(423, 283, 102, 20);
		encrypt.add(this.cb_encrypt_charset2);

		final JLabel lblK = new JLabel("k2:");
		lblK.setBounds(360, 254, 25, 16);
		encrypt.add(lblK);

		this.txt_encrypt_k2 = new JTextField();
		this.txt_encrypt_k2.setToolTipText("blockSize");
		this.txt_encrypt_k2.setColumns(10);
		this.txt_encrypt_k2.setBounds(385, 251, 50, 22);
		encrypt.add(this.txt_encrypt_k2);

		final JLabel lblL_1 = new JLabel("l2:");
		lblL_1.setBounds(445, 254, 16, 16);
		encrypt.add(lblL_1);

		this.txt_encrypt_l2 = new JTextField();
		this.txt_encrypt_l2.setToolTipText("blockSize");
		this.txt_encrypt_l2.setColumns(10);
		this.txt_encrypt_l2.setBounds(475, 251, 50, 22);
		encrypt.add(this.txt_encrypt_l2);

		final JButton btnCalculateSpecialRsa = new JButton("Encrypt with \r\ncharset 1 & 2");
		btnCalculateSpecialRsa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				RSAView.this.controler.btn_encrypt_calculate(e, true);
			}
		});
		btnCalculateSpecialRsa.setBounds(535, 248, 165, 58);
		encrypt.add(btnCalculateSpecialRsa);

		final JPanel decrypt = new JPanel();
		decrypt.setLayout(null);
		tabbed_main.addTab("Decrypt", null, decrypt, null);

		final JLabel lblEncryptedMessage = new JLabel("Encrypted message");
		lblEncryptedMessage.setBounds(12, 13, 112, 16);
		decrypt.add(lblEncryptedMessage);

		final JLabel lblD_1 = new JLabel("d");
		lblD_1.setBounds(360, 13, 56, 16);
		decrypt.add(lblD_1);

		final JScrollPane scrollPane_12 = new JScrollPane();
		scrollPane_12.setBounds(360, 30, 340, 89);
		decrypt.add(scrollPane_12);

		this.txt_decrypt_d = new JTextArea();
		this.txt_decrypt_d.setWrapStyleWord(true);
		this.txt_decrypt_d.setLineWrap(true);
		scrollPane_12.setViewportView(this.txt_decrypt_d);

		final JScrollPane scrollPane_13 = new JScrollPane();
		scrollPane_13.setBounds(360, 148, 340, 89);
		decrypt.add(scrollPane_13);

		this.txt_decrypt_n = new JTextArea();
		this.txt_decrypt_n.setWrapStyleWord(true);
		this.txt_decrypt_n.setLineWrap(true);
		scrollPane_13.setViewportView(this.txt_decrypt_n);

		final JLabel label_2 = new JLabel("n");
		label_2.setBounds(360, 132, 56, 16);
		decrypt.add(label_2);

		final JLabel lblDecryptedText = new JLabel("Decrypted numbers");
		lblDecryptedText.setBounds(12, 314, 102, 16);
		decrypt.add(lblDecryptedText);

		final JScrollPane scrollPane_14 = new JScrollPane();
		scrollPane_14.setBounds(12, 341, 340, 116);
		decrypt.add(scrollPane_14);

		this.txt_decrypt_decryptedNumbers = new JTextArea();
		this.txt_decrypt_decryptedNumbers.setLineWrap(true);
		this.txt_decrypt_decryptedNumbers.setWrapStyleWord(true);
		scrollPane_14.setViewportView(this.txt_decrypt_decryptedNumbers);

		final JScrollPane scrollPane_15 = new JScrollPane();
		scrollPane_15.setBounds(12, 30, 340, 207);
		decrypt.add(scrollPane_15);

		this.txt_decrypt_encryptedTxt = new JTextArea();
		this.txt_decrypt_encryptedTxt.setWrapStyleWord(true);
		this.txt_decrypt_encryptedTxt.setLineWrap(true);
		scrollPane_15.setViewportView(this.txt_decrypt_encryptedTxt);

		final JButton btn_decrypt_calculate = new JButton("Decrypt with charset 1");
		btn_decrypt_calculate.setBounds(187, 248, 165, 58);
		decrypt.add(btn_decrypt_calculate);

		btn_decrypt_calculate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				RSAView.this.controler.btn_decrypt_calculate(e, false);
			}
		});

		this.txt_decrypt_k1 = new JTextField();
		this.txt_decrypt_k1.setToolTipText("blockSize");
		this.txt_decrypt_k1.setColumns(10);
		this.txt_decrypt_k1.setBounds(35, 251, 50, 22);
		decrypt.add(this.txt_decrypt_k1);

		final JLabel lblK_1 = new JLabel("k1:");
		lblK_1.setBounds(12, 254, 25, 16);
		decrypt.add(lblK_1);

		final JLabel lblCharset_2 = new JLabel("Charset 1:");
		lblCharset_2.setBounds(12, 287, 63, 14);
		decrypt.add(lblCharset_2);

		this.cb_decrypt_charset1 = new JComboBox<String>();
		this.cb_decrypt_charset1.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(final ItemEvent e) {
				RSAView.this.controler.cb_decrypt_charset1_action();

			}
		});
		this.cb_decrypt_charset1.setBounds(75, 283, 102, 20);
		decrypt.add(this.cb_decrypt_charset1);

		final JScrollPane scrollPane_16 = new JScrollPane();
		scrollPane_16.setBounds(360, 341, 340, 116);
		decrypt.add(scrollPane_16);

		this.txt_decrypt_decryptedText = new JTextArea();
		scrollPane_16.setViewportView(this.txt_decrypt_decryptedText);

		final JLabel label_1 = new JLabel("Decrypted text");
		label_1.setBounds(360, 314, 102, 16);
		decrypt.add(label_1);

		final JLabel lblNewLabel_9 = new JLabel("l1:");
		lblNewLabel_9.setBounds(101, 254, 16, 16);
		decrypt.add(lblNewLabel_9);

		this.txt_decrypt_l1 = new JTextField();
		this.txt_decrypt_l1.setToolTipText("blockSize");
		this.txt_decrypt_l1.setColumns(10);
		this.txt_decrypt_l1.setBounds(127, 251, 50, 22);
		decrypt.add(this.txt_decrypt_l1);

		final JLabel lblK_2 = new JLabel("k2:");
		lblK_2.setBounds(360, 254, 25, 16);
		decrypt.add(lblK_2);

		this.txt_decrypt_k2 = new JTextField();
		this.txt_decrypt_k2.setToolTipText("blockSize");
		this.txt_decrypt_k2.setColumns(10);
		this.txt_decrypt_k2.setBounds(385, 251, 50, 22);
		decrypt.add(this.txt_decrypt_k2);

		final JLabel lblL_2 = new JLabel("l2:");
		lblL_2.setBounds(445, 254, 16, 16);
		decrypt.add(lblL_2);

		this.txt_decrypt_l2 = new JTextField();
		this.txt_decrypt_l2.setToolTipText("blockSize");
		this.txt_decrypt_l2.setColumns(10);
		this.txt_decrypt_l2.setBounds(475, 251, 50, 22);
		decrypt.add(this.txt_decrypt_l2);

		this.cb_decrypt_charset2 = new JComboBox<String>();
		this.cb_decrypt_charset2.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(final ItemEvent e) {
				RSAView.this.controler.cb_decrypt_charset2_action();

			}
		});
		this.cb_decrypt_charset2.setBounds(423, 283, 102, 20);
		decrypt.add(this.cb_decrypt_charset2);

		final JLabel lblCharset_3 = new JLabel("Charset 2:");
		lblCharset_3.setBounds(360, 283, 56, 14);
		decrypt.add(lblCharset_3);

		final JButton btn_decrypt_calculate2 = new JButton("Decrypt with charset 1 & 2");
		btn_decrypt_calculate2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				RSAView.this.controler.btn_decrypt_calculate(e, true);
			}
		});
		btn_decrypt_calculate2.setBounds(535, 248, 165, 58);
		decrypt.add(btn_decrypt_calculate2);
				
				final JPanel panel_signaturCreate = new JPanel();
				tabbed_main.addTab("Signatur - Create", null, panel_signaturCreate, null);
				panel_signaturCreate.setLayout(null);
				
				final JScrollPane scrollPane_22 = new JScrollPane();
				scrollPane_22.setBounds(10, 28, 340, 207);
				panel_signaturCreate.add(scrollPane_22);
				
				this.txt_sign_text = new JTextArea();
				this.txt_sign_text.setLineWrap(true);
				scrollPane_22.setViewportView(this.txt_sign_text);
				
				final JLabel lblTextToSign = new JLabel("Text to sign");
				lblTextToSign.setBounds(10, 11, 118, 16);
				panel_signaturCreate.add(lblTextToSign);
				
				final JLabel lblD_2 = new JLabel("d");
				lblD_2.setBounds(358, 11, 56, 16);
				panel_signaturCreate.add(lblD_2);
				
				final JLabel label_6 = new JLabel("n");
				label_6.setBounds(358, 130, 56, 16);
				panel_signaturCreate.add(label_6);
				
				final JScrollPane scrollPane_23 = new JScrollPane();
				scrollPane_23.setBounds(360, 28, 337, 87);
				panel_signaturCreate.add(scrollPane_23);
				
				this.txt_sign_d = new JTextArea();
				this.txt_sign_d.setLineWrap(true);
				scrollPane_23.setViewportView(this.txt_sign_d);
				
				final JScrollPane scrollPane_24 = new JScrollPane();
				scrollPane_24.setBounds(360, 146, 337, 87);
				panel_signaturCreate.add(scrollPane_24);
				
				this.txt_sign_n = new JTextArea();
				this.txt_sign_n.setLineWrap(true);
				scrollPane_24.setViewportView(this.txt_sign_n);
				
				this.btn_sign_sign = new JButton("Sign");
				this.btn_sign_sign.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(final ActionEvent e) {
						RSAView.this.controler.btn_sign_sign(e);
					}
				});
				this.btn_sign_sign.setBounds(541, 244, 156, 41);
				panel_signaturCreate.add(this.btn_sign_sign);
				
				final JScrollPane scrollPane_25 = new JScrollPane();
				scrollPane_25.setBounds(10, 296, 688, 165);
				panel_signaturCreate.add(scrollPane_25);
				
				this.txt_sign_signature = new JTextArea();
				this.txt_sign_signature.setLineWrap(true);
				scrollPane_25.setViewportView(this.txt_sign_signature);
				
				final JLabel lblNewLabel_10 = new JLabel("Hashfunction");
				lblNewLabel_10.setBounds(358, 258, 63, 14);
				panel_signaturCreate.add(lblNewLabel_10);
				
				this.cb_sign_hash = new JComboBox<String>();
				this.cb_sign_hash.addItem("SHA1");
				this.cb_sign_hash.addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(final ItemEvent e) {
						RSAView.this.controler.cb_sign_hash_action();
					}
				});
				this.cb_sign_hash.setBounds(435, 254, 102, 20);
				panel_signaturCreate.add(this.cb_sign_hash);
				
				final JLabel lblSignature = new JLabel("Signature");
				lblSignature.setBounds(10, 277, 118, 16);
				panel_signaturCreate.add(lblSignature);
		
				final JPanel panel_signaturVerification = new JPanel();
				tabbed_main.addTab("Signatur - Verification", null, panel_signaturVerification, null);
				panel_signaturVerification.setLayout(null);
				
				final JScrollPane scrollPane_17 = new JScrollPane();
				scrollPane_17.setBounds(10, 28, 340, 207);
				panel_signaturVerification.add(scrollPane_17);
				
				this.txt_verifySig_signature = new JTextArea();
				this.txt_verifySig_signature.setLineWrap(true);
				scrollPane_17.setViewportView(this.txt_verifySig_signature);
				
				final JLabel lblSignature_1 = new JLabel("Signature");
				lblSignature_1.setBounds(10, 11, 118, 16);
				panel_signaturVerification.add(lblSignature_1);
				
				final JScrollPane scrollPane_19 = new JScrollPane();
				scrollPane_19.setBounds(360, 28, 337, 87);
				panel_signaturVerification.add(scrollPane_19);
				
				this.txt_verifySig_e = new JTextArea();
				this.txt_verifySig_e.setLineWrap(true);
				scrollPane_19.setViewportView(this.txt_verifySig_e);
				
				final JScrollPane scrollPane_20 = new JScrollPane();
				scrollPane_20.setBounds(360, 146, 337, 87);
				panel_signaturVerification.add(scrollPane_20);
				
				this.txt_verifySig_n = new JTextArea();
				this.txt_verifySig_n.setLineWrap(true);
				scrollPane_20.setViewportView(this.txt_verifySig_n);
				
				this.cb_verifySig_hashFunction = new JComboBox<String>();
				this.cb_verifySig_hashFunction.addItem("SHA1");
				this.cb_verifySig_hashFunction.addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(final ItemEvent e) {
					}
				});
				this.cb_verifySig_hashFunction.setBounds(435, 254, 102, 20);
				panel_signaturVerification.add(this.cb_verifySig_hashFunction);
				
				final JLabel label_3 = new JLabel("Hashfunction");
				label_3.setBounds(358, 258, 63, 14);
				panel_signaturVerification.add(label_3);
				
				this.btn_verifySig_verify = new JButton("Verify");
				this.btn_verifySig_verify.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(final ActionEvent e) {
						RSAView.this.controler.btn_verifySig_verify(e);
					}
				});
				this.btn_verifySig_verify.setBounds(541, 244, 156, 41);
				panel_signaturVerification.add(this.btn_verifySig_verify);
				
				final JLabel label_4 = new JLabel("n");
				label_4.setBounds(358, 130, 56, 16);
				panel_signaturVerification.add(label_4);
				
				final JLabel lblE = new JLabel("e");
				lblE.setBounds(358, 11, 56, 16);
				panel_signaturVerification.add(lblE);
				
				final JLabel lblChiffrat = new JLabel("Signed chiffrat");
				lblChiffrat.setBounds(10, 238, 118, 16);
				panel_signaturVerification.add(lblChiffrat);
				
				final JScrollPane scrollPane_18 = new JScrollPane();
				scrollPane_18.setBounds(10, 254, 340, 207);
				panel_signaturVerification.add(scrollPane_18);
				
				this.txt_verifySig_signedChiffrat = new JTextArea();
				scrollPane_18.setViewportView(this.txt_verifySig_signedChiffrat);
				
				final JLabel lblHashsignedChiffrat = new JLabel("hash(signedChiffrat) == signature^e (mod n)");
				lblHashsignedChiffrat.setBounds(360, 302, 228, 20);
				panel_signaturVerification.add(lblHashsignedChiffrat);
				
				this.lbl_verifySig_result = new JLabel("");
				this.lbl_verifySig_result.setBounds(598, 305, 104, 14);
				panel_signaturVerification.add(this.lbl_verifySig_result);

		final JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setEnabled(false);
		toolBar_1.setFloatable(false);
		toolBar_1.setBounds(5, 509, 101, 18);
		this.pane_main.add(toolBar_1);

		final JLabel lblNewLabel_3 = new JLabel("v 1.0");
		toolBar_1.add(lblNewLabel_3);

		this.progressBar = new JProgressBar();
		this.progressBar.setBounds(652, 513, 70, 14);
		this.pane_main.add(this.progressBar);
		// progressBar.setStringPainted(true);
		// progressBar.setMaximum(10);
		// progressBar.setMinimum(0);
		// progressBar.setValue(5);

		this.setAllComboBoxes();

	}

	public JLabel getLbl_verifySig_result() {
		return this.lbl_verifySig_result;
	}

	public void setLbl_verifySig_result(final JLabel lbl_verifySig_result) {
		this.lbl_verifySig_result = lbl_verifySig_result;
	}

	public JTextArea getTxt_verifySig_signature() {
		return this.txt_verifySig_signature;
	}

	public void setTxt_verifySig_signature(final JTextArea txt_verifySig_signature) {
		this.txt_verifySig_signature = txt_verifySig_signature;
	}

	public JTextArea getTxt_verifySig_e() {
		return this.txt_verifySig_e;
	}

	public void setTxt_verifySig_e(final JTextArea txt_verifySig_e) {
		this.txt_verifySig_e = txt_verifySig_e;
	}

	public JTextArea getTxt_verifySig_n() {
		return this.txt_verifySig_n;
	}

	public void setTxt_verifySig_n(final JTextArea txt_verifySig_n) {
		this.txt_verifySig_n = txt_verifySig_n;
	}

	public JComboBox<String> getCb_verifySig_hashFunction() {
		return this.cb_verifySig_hashFunction;
	}

	public void setCb_verifySig_hashFunction(final JComboBox<String> cb_verifySig_hashFunction) {
		this.cb_verifySig_hashFunction = cb_verifySig_hashFunction;
	}

	public JButton getBtn_verifySig_verify() {
		return this.btn_verifySig_verify;
	}

	public void setBtn_verifySig_verify(final JButton btn_verifySig_verify) {
		this.btn_verifySig_verify = btn_verifySig_verify;
	}

	public JTextArea getTxt_verifySig_signedChiffrat() {
		return this.txt_verifySig_signedChiffrat;
	}

	public void setTxt_verifySig_signedChiffrat(final JTextArea txt_verifySig_signedChiffrat) {
		this.txt_verifySig_signedChiffrat = txt_verifySig_signedChiffrat;
	}

	public JTextArea getTxt_sign_text() {
		return this.txt_sign_text;
	}

	public void setTxt_sign_text(final JTextArea txt_sign_text) {
		this.txt_sign_text = txt_sign_text;
	}

	public JTextArea getTxt_sign_n() {
		return this.txt_sign_n;
	}

	public void setTxt_sign_n(final JTextArea txt_sign_n) {
		this.txt_sign_n = txt_sign_n;
	}

	public JButton getBtn_sign_sign() {
		return this.btn_sign_sign;
	}

	public void setBtn_sign_sign(final JButton btn_sign_sign) {
		this.btn_sign_sign = btn_sign_sign;
	}

	public JTextArea getTxt_sign_signature() {
		return this.txt_sign_signature;
	}

	public void setTxt_sign_signature(final JTextArea txt_sign_signature) {
		this.txt_sign_signature = txt_sign_signature;
	}

	public JComboBox<String> getCb_sign_hash() {
		return this.cb_sign_hash;
	}

	public void setCb_sign_hash(final JComboBox<String> cb_sign_hash) {
		this.cb_sign_hash = cb_sign_hash;
	}

	public JTextArea getTxt_sign_d() {
		return this.txt_sign_d;
	}

	public void setTxt_sign_d(final JTextArea txt_sign_d) {
		this.txt_sign_d = txt_sign_d;
	}

	public JTextField getTxt_decrypt_k1() {
		return this.txt_decrypt_k1;
	}

	public void setTxt_decrypt_k1(final JTextField txt_decrypt_k1) {
		this.txt_decrypt_k1 = txt_decrypt_k1;
	}

	public JTextField getTxt_decrypt_l1() {
		return this.txt_decrypt_l1;
	}

	public void setTxt_decrypt_l1(final JTextField txt_decrypt_l1) {
		this.txt_decrypt_l1 = txt_decrypt_l1;
	}

	public JComboBox<String> getCb_decrypt_charset1() {
		return this.cb_decrypt_charset1;
	}

	public void setCb_decrypt_charset1(final JComboBox<String> cb_decrypt_charset1) {
		this.cb_decrypt_charset1 = cb_decrypt_charset1;
	}

	public JTextField getTxt_decrypt_k2() {
		return this.txt_decrypt_k2;
	}

	public void setTxt_decrypt_k2(final JTextField txt_decrypt_k2) {
		this.txt_decrypt_k2 = txt_decrypt_k2;
	}

	public JTextField getTxt_decrypt_l2() {
		return this.txt_decrypt_l2;
	}

	public void setTxt_decrypt_l2(final JTextField txt_decrypt_l2) {
		this.txt_decrypt_l2 = txt_decrypt_l2;
	}

	public JComboBox<String> getCb_decrypt_charset2() {
		return this.cb_decrypt_charset2;
	}

	public void setCb_decrypt_charset2(final JComboBox<String> cb_decrypt_charset2) {
		this.cb_decrypt_charset2 = cb_decrypt_charset2;
	}

	private void setAllComboBoxes() {
		final List<String> availableCharsets = Encoding.getAvailableCharsetNames();

		for (int i = 0; i < availableCharsets.size(); i++) {
			this.cb_keys_charset.addItem(availableCharsets.get(i));
			this.cb_encrypt_charset.addItem(availableCharsets.get(i));
			this.cb_encrypt_charset2.addItem(availableCharsets.get(i));
			this.cb_decrypt_charset1.addItem(availableCharsets.get(i));
			this.cb_decrypt_charset2.addItem(availableCharsets.get(i));
		}
	}

	public JTextField getTxt_encrypt_k2() {
		return this.txt_encrypt_k2;
	}

	public void setTxt_encrypt_k2(final JTextField txt_encrypt_k2) {
		this.txt_encrypt_k2 = txt_encrypt_k2;
	}

	public JTextField getTxt_encrypt_l2() {
		return this.txt_encrypt_l2;
	}

	public void setTxt_encrypt_l2(final JTextField txt_encrypt_l2) {
		this.txt_encrypt_l2 = txt_encrypt_l2;
	}

	public JComboBox<String> getCb_encrypt_charset2() {
		return this.cb_encrypt_charset2;
	}

	public void setCb_encrypt_charset2(final JComboBox<String> cb_encrypt_charset2) {
		this.cb_encrypt_charset2 = cb_encrypt_charset2;
	}

	public JTextField getTxt_encrypt_blockSize() {
		return this.txt_encrypt_blockSize;
	}

	public JTextField getTxt_encrypt_l() {
		return this.txt_encrypt_l;
	}

	public JComboBox<String> getCb_encrypt_charset() {
		return this.cb_encrypt_charset;
	}

	public JComboBox<String> getCb_decrypt_charset() {
		return this.cb_decrypt_charset1;
	}

	public void setCb_decrypt_charset(final JComboBox<String> cb_decrypt_charset) {
		this.cb_decrypt_charset1 = cb_decrypt_charset;
	}

	public JTextArea getTxt_decrypt_decryptedNumbers() {
		return this.txt_decrypt_decryptedNumbers;
	}

	public void setTxt_decrypt_decryptedNumbers(final JTextArea txt_decrypt_decryptedNumbers) {
		this.txt_decrypt_decryptedNumbers = txt_decrypt_decryptedNumbers;
	}

	public JTextField getTxt_decrypt_l() {
		return this.txt_decrypt_l1;
	}

	public void setTxt_decrypt_l(final JTextField txt_decrypt_l) {
		this.txt_decrypt_l1 = txt_decrypt_l;
	}

	public JTextArea getTxt_decrypt_decryptedText() {
		return this.txt_decrypt_decryptedText;
	}

	public void setTxt_decrypt_decryptedText(final JTextArea txt_decrypt_decryptedText) {
		this.txt_decrypt_decryptedText = txt_decrypt_decryptedText;
	}

	public JTextField getTxt_encrypt_l1() {
		return this.txt_encrypt_l;
	}

	public void setTxt_encrypt_l(final JTextField txt_encrypt_l) {
		this.txt_encrypt_l = txt_encrypt_l;
	}

	public JComboBox<String> getCb_encrypt_charset1() {
		return this.cb_encrypt_charset;
	}

	public void setCb_encrypt_charset(final JComboBox<String> cb_encrypt_charset) {
		this.cb_encrypt_charset = cb_encrypt_charset;
	}

	public JButton getBtn_keys_calculate() {
		return this.btn_keys_calculate;
	}

	public void setBtn_keys_calculate(final JButton btn_keys_calculate) {
		this.btn_keys_calculate = btn_keys_calculate;
	}

	public JLabel getLblMinimumL() {
		return this.lblMinimumL;
	}

	public void setLblMinimumL(final JLabel lblMinimumL) {
		this.lblMinimumL = lblMinimumL;
	}

	public JLabel getLblMaximumK() {
		return this.lblMaximumK;
	}

	public void setLblMaximumK(final JLabel lblMaximumK) {
		this.lblMaximumK = lblMaximumK;
	}

	public JComboBox<String> getCb_keys_charset() {
		return this.cb_keys_charset;
	}

	public void setCb_keys_charset(final JComboBox<String> cb_keys_charset) {
		this.cb_keys_charset = cb_keys_charset;
	}

	public JTextArea getTxt_decrypt_d() {
		return this.txt_decrypt_d;
	}

	public void setTxt_decrypt_d(final JTextArea txt_decrypt_d) {
		this.txt_decrypt_d = txt_decrypt_d;
	}

	public JTextArea getTxt_decrypt_n() {
		return this.txt_decrypt_n;
	}

	public void setTxt_decrypt_n(final JTextArea txt_decrypt_n) {
		this.txt_decrypt_n = txt_decrypt_n;
	}

	public JTextArea getTxt_decrypt_decryptedTxt() {
		return this.txt_decrypt_decryptedText;
	}

	public void setTxt_decrypt_decryptedTxt(final JTextArea txt_decrypt_decryptedTxt) {
		this.txt_decrypt_decryptedText = txt_decrypt_decryptedTxt;
	}

	public JTextArea getTxt_decrypt_encryptedTxt() {
		return this.txt_decrypt_encryptedTxt;
	}

	public void setTxt_decrypt_encryptedTxt(final JTextArea txt_decrypt_encryptedTxt) {
		this.txt_decrypt_encryptedTxt = txt_decrypt_encryptedTxt;
	}

	public JTextField getTxt_decrypt_blockSize() {
		return this.txt_decrypt_k1;
	}

	public void setTxt_decrypt_blockSize(final JTextField txt_decrypt_blockSize) {
		this.txt_decrypt_k1 = txt_decrypt_blockSize;
	}

	public JTextField getTxt_encrypt_k1() {
		return this.txt_encrypt_blockSize;
	}

	public void setTxt_encrypt_blockSize(final JTextField txt_encrypt_blockSize) {
		this.txt_encrypt_blockSize = txt_encrypt_blockSize;
	}

	public JTextArea getTxt_encrypt_txt() {
		return this.txt_encrypt_txt;
	}

	public void setTxt_encrypt_txt(final JTextArea txt_encrypt_txt) {
		this.txt_encrypt_txt = txt_encrypt_txt;
	}

	public JTextArea getTxt_encrypt_chiffreTxt() {
		return this.txt_encrypt_chiffreTxt;
	}

	public void setTxt_encrypt_chiffreTxt(final JTextArea txt_encrypt_chiffreTxt) {
		this.txt_encrypt_chiffreTxt = txt_encrypt_chiffreTxt;
	}

	public JTextArea getTxt_encrypt_chiffreNumber() {
		return this.txt_encrypt_chiffreNumber;
	}

	public void setTxt_encrypt_chiffreNumber(final JTextArea txt_encrypt_chiffreNumber) {
		this.txt_encrypt_chiffreNumber = txt_encrypt_chiffreNumber;
	}

	public JTextArea getTxt_encrypt_n() {
		return this.txt_encrypt_n;
	}

	public void setTxt_encrypt_n(final JTextArea txt_encrypt_n) {
		this.txt_encrypt_n = txt_encrypt_n;
	}

	public JTextArea getTxt_encrypt_e() {
		return this.txt_encrypt_e;
	}

	public void setTxt_encrypt_e(final JTextArea txt_encrypt_e) {
		this.txt_encrypt_e = txt_encrypt_e;
	}

	public JLabel getLbl_keys_time() {
		return this.lbl_keys_time;
	}

	public void setLbl_keys_time(final JLabel lbl_keys_time) {
		this.lbl_keys_time = lbl_keys_time;
	}

	public JTextArea getTxt_keys_phiN() {
		return this.txt_keys_phiN;
	}

	public void setTxt_keys_phiN(final JTextArea txt_keys_phiN) {
		this.txt_keys_phiN = txt_keys_phiN;
	}

	public JTextArea getTxt_keys_d() {
		return this.txt_keys_d;
	}

	public void setTxt_keys_d(final JTextArea txt_keys_d) {
		this.txt_keys_d = txt_keys_d;
	}

	public JTextArea getTxt_keys_e() {
		return this.txt_keys_e;
	}

	public void setTxt_keys_e(final JTextArea txt_keys_e) {
		this.txt_keys_e = txt_keys_e;
	}

	public JTextArea getTxt_keys_n() {
		return this.txt_keys_n;
	}

	public void setTxt_keys_n(final JTextArea txt_keys_n) {
		this.txt_keys_n = txt_keys_n;
	}

	public JTextArea getTxt_keys_q() {
		return this.txt_keys_q;
	}

	public void setTxt_keys_q(final JTextArea txt_keys_q) {
		this.txt_keys_q = txt_keys_q;
	}

	public JTextArea getTxt_keys_p() {
		return this.txt_keys_p;
	}

	public void setTxt_keys_p(final JTextArea txt_keys_p) {
		this.txt_keys_p = txt_keys_p;
	}

	public JTextField getTxt_keys_binaryLength() {
		return this.txt_keys_binaryLength;
	}

	public void setTxt_keys_binaryLength(final JTextField txt_keys_binaryLength) {
		this.txt_keys_binaryLength = txt_keys_binaryLength;
	}

	public JTextField getTxt_keys_probability() {
		return this.txt_keys_probability;
	}

	public void setTxt_keys_probability(final JTextField txt_keys_probability) {
		this.txt_keys_probability = txt_keys_probability;
	}

	public JTextField getTxt_primes_bitLength() {
		return this.txt_primes_bitLength;
	}

	public void setTxt_primes_bitLength(final JTextField txt_primes_bitLength) {
		this.txt_primes_bitLength = txt_primes_bitLength;
	}

	public JTextField getTxt_primes_probability() {
		return this.txt_primes_probability;
	}

	public void setTxt_primes_probability(final JTextField txt_primes_probability) {
		this.txt_primes_probability = txt_primes_probability;
	}

	public JTextArea getTxt_primes_result() {
		return this.txt_primes_result;
	}

	public void setTxt_primes_result(final JTextArea txt_primes_result) {
		this.txt_primes_result = txt_primes_result;
	}

	public void activateProgressbar() {
		this.progressBar.setIndeterminate(true);
	}

	public void deactivateProgressbar() {
		this.progressBar.setIndeterminate(false);
	}
}
