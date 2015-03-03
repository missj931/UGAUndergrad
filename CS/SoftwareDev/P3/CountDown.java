import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


//*****************
// this class provides the countdown frame functionality
public class CountDown {

	Timer timer;
	
	//set the time to 5
	int counter = 5;

	public CountDown() {
		
		//initialize frame
		final JFrame frame = new JFrame("CountDown");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		final JLabel label = new JLabel("5");
		JPanel panel = new JPanel();
		panel.add(label, BorderLayout.SOUTH);
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);

		//listen for the update event
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//change the label to match the counter
				label.setText(String.valueOf(counter));
				//decrement the counter
				counter--;
				//special case when counter reaches 0
				if (counter < 0) {
					label.setText("Start");
					frame.dispose();
					timer.removeActionListener(this);
					timer.stop();

				}
			}
		});
		timer.start();
	}

}
