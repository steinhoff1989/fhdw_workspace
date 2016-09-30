package view;

import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JTextField;

import application.Application;
import model.ConcreteObservee;
import model.ConcreteObserver;
import model.ConcreteObserverParallel;
import model.ConcreteObserverSequential;
import model.ConcreteObserverViewer;

@SuppressWarnings("serial")
public class ConcreteObserverView extends JFrame implements ConcreteObserverViewer {

	private ConcreteObservee observee;
	private ConcreteObserver observer;
	
	private javax.swing.JPanel jContentPane = null;
	private JToolBar jToolBar = null;
	private JButton registerParallelCommand = null;
	private JButton deregisterCommand = null;
	private JTextField observerView = null;
	private JButton registerSequentialButton = null;

	public ConcreteObserverView() {
		super();
		initialize();
		this.observee = Application.getObservee();
	}

	private void initialize() {
		this.setSize(700, 80 + (int) Application.fontSize);
		this.setContentPane(getJContentPane());
		this.setTitle("Observer");
	}

	private javax.swing.JPanel getJContentPane() {
		if(jContentPane == null) {
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(new java.awt.BorderLayout());
			jContentPane.add(getJToolBar(), java.awt.BorderLayout.SOUTH);
		}
		return jContentPane;
	}
   
	private JToolBar getJToolBar() {
		if (jToolBar == null) {
			jToolBar = new JToolBar();
			jToolBar.add(getRegisterParallelCommand());
			jToolBar.add(getRegisterSequentialButton());
			jToolBar.add(getDeregisterCommand());
			jToolBar.add(getObserverView());
		}
		return jToolBar;
	}
   
	private JButton getRegisterParallelCommand() {
		if (registerParallelCommand == null) {
			registerParallelCommand = new JButton();
			registerParallelCommand.setText("Register (parallel)");
			registerParallelCommand.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					registerParallelCommand();
				}
			});
		}
		return registerParallelCommand;
	}
	private void switchRegisterDeregisterEnabled(){
		this.getRegisterParallelCommand().setEnabled(!this.getRegisterParallelCommand().isEnabled());
		this.getRegisterSequentialButton().setEnabled(!this.getRegisterSequentialButton().isEnabled());
		this.getDeregisterCommand().setEnabled(!this.getDeregisterCommand().isEnabled());
	}
	private JButton getDeregisterCommand() {
		if (deregisterCommand == null) {
			deregisterCommand = new JButton();
			deregisterCommand.setText("Deregister");
			deregisterCommand.setEnabled(false);
			deregisterCommand.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					deregisterCommand();
				}
			});
		}
		return deregisterCommand;
	}
	private void deregisterCommand(){
		this.observee.deregister(this.observer);
		this.switchRegisterDeregisterEnabled();
	}
	private JTextField getObserverView() {
		if (observerView == null) {
			observerView = new JTextField();
		}
		return observerView;
	}
	private int value = 0;
	public void setValue(int value){
		this.value = value;
		this.observerView.setText(new Integer(this.getValue()).toString());
	}
	public int getValue(){
		return this.value;
	}

	/**
	 * This method initializes registerSequentialButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getRegisterSequentialButton() {
		if (registerSequentialButton == null) {
			registerSequentialButton = new JButton();
			registerSequentialButton.setText("Register (sequential)");
			registerSequentialButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					registerSequentialCommand();
				}
			});
		}
		return registerSequentialButton;
	}
	protected void registerSequentialCommand() {
		this.observer = ConcreteObserverSequential.create(this);
		this.observee.register(this.observer); 
		this.switchRegisterDeregisterEnabled();
	}

	public void registerParallelCommand(){
		this.observer = ConcreteObserverParallel.create(this);
		this.observee.register(this.observer); 
		this.switchRegisterDeregisterEnabled();
	}

	
}  //  @jve:decl-index=0:visual-constraint="10,10"
