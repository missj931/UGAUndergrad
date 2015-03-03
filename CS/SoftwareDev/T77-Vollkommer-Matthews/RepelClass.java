import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RepelClass implements ActionListener{

	boolean repel;
	
	RepelClass(){
		repel = false;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		//when pressed, set repel to true
		repel = true;
	}

	public boolean getRepel(){
		
		//get repel
		return repel;
		

	}
}
