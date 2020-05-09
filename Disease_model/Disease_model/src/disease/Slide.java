package disease;

import java.awt.BorderLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Slide extends JFrame{
	  private JLabel label = new JLabel("Valeur actuelle : 30");
	  public Slide(){
	    this.setSize(250, 150);
	    this.setTitle("Slider");
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    JSlider slide = new JSlider();

	    slide.setMaximum(100);
	    slide.setMinimum(0);
	    slide.setValue(30);
	    slide.setPaintTicks(true);
	    slide.setPaintLabels(true);
	    slide.setMinorTickSpacing(10);
	    slide.setMajorTickSpacing(20);
	    slide.addComponentListener(new ComponentListener(){
	      public void stateChanged(ChangeEvent event){
	        label.setText("Valeur actuelle : " + ((JSlider)event.getSource()).getValue());
	      }

		@Override
		public void componentHidden(ComponentEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void componentMoved(ComponentEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void componentResized(ComponentEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void componentShown(ComponentEvent e) {
			// TODO Auto-generated method stub
			
		}
	    });
	    this.getContentPane().add(slide, BorderLayout.CENTER);
	    this.getContentPane().add(label, BorderLayout.SOUTH);
	  }
	  public static void main(String[] args){
	    Slide slide = new Slide();
	    slide.setVisible(true);
	  }
	}
