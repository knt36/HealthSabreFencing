package Driver;

import Screens.FrameMain;
import Screens.ScrMain;

public class driver {
	public static void main (String args [] ){
		ScrMain scrMain = new ScrMain();
		new FrameMain().add(scrMain);
		
		
	}
}
