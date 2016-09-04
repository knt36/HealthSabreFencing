package TextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TextFieldNumber extends TextFieldFactory {
	int storedValue = -1;
	public TextFieldNumber(int min) {
		// TODO Auto-generated constructor stub
		this.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {			
				try{
					String s = e.getDocument().getText(0, e.getDocument().getLength());
					int i = Integer.parseInt(s);
					storedValue = i;
				}catch(Exception f){
					try{
					getDocument().remove(0, getDocument().getLength());
					}catch(Exception g){
						
					}
				}
			}
		});
	}
}
