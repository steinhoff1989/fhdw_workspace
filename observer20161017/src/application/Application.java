package application;

import javax.swing.UIManager;

import model.ConcreteObservee;
import view.ConcreteObserveeView;
import view.ConcreteObserverView;

public class Application {
	
	public static final float fontSize = 20;

	private static ConcreteObserveeView observeeView;
	private static ConcreteObservee observee;
	
	public static ConcreteObservee getObservee(){
		return observee;
	}
	
	public static void main(String[] args) {
		changeFontSizes();
		int leftMargin = 100;
		int topMargin = 100;
		int deltaTopMargin = 80 + 3 * (int) fontSize;
		observeeView = new ConcreteObserveeView();
		observee = ConcreteObservee.create();
		observeeView.setObservee(observee);
		observeeView.setLocation(leftMargin,topMargin);
		observeeView.setVisible(true);
		for(int i = 0; i < 3; i++){
			ConcreteObserverView observer = new ConcreteObserverView();
			topMargin = topMargin + deltaTopMargin;
			observer.setLocation(leftMargin,topMargin);
			observer.setVisible(true);
			observer.registerParallelCommand();//TODO Hint: Initial registration is performed here!
		}	
	}
	private static void changeFontSizes() {
		java.util.Enumeration<Object> keys = UIManager.getDefaults().keys();
	    while (keys.hasMoreElements()) {
	      Object key = keys.nextElement();
	      Object value = UIManager.get (key);
	      if (value != null && value instanceof javax.swing.plaf.FontUIResource){
	    	javax.swing.plaf.FontUIResource font = (javax.swing.plaf.FontUIResource) value;
	    	javax.swing.plaf.FontUIResource derivedFont = new javax.swing.plaf.FontUIResource(font.deriveFont(fontSize));
	        UIManager.put (key, derivedFont);
	      }
	    } 		
	}

}
