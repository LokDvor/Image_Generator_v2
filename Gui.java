import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Gui extends JFrame implements ActionListener ,ChangeListener {
	
	static final int COLOR_MIN = 0;
	static final int COLOR_MAX = 8388608;
	static final int COLOR_INIT = 0;

	private JLabel size_x_label = new JLabel("Frame x:");
	private JLabel size_y_label = new JLabel("Frame y:");
	private JTextField size_x_field = new JTextField(10);
	private JTextField size_y_field = new JTextField(10);
	
	private JLabel bend_x_label = new JLabel("Bend x:");
	private JLabel bend_y_label = new JLabel("Bend y:");
	private JTextField bend_x_field = new JTextField(10);
	private JTextField bend_y_field = new JTextField(10);
	
	private JLabel distance_label = new JLabel("Distance:");
	private JTextField distance_field = new JTextField(10);
	
	private JLabel speed_label = new JLabel("Speed:");
	private JTextField speed_field = new JTextField(10);
	
	private JLabel num_label = new JLabel("Frames:");
	private JTextField num_field = new JTextField(10);
	
	private JLabel fps_label = new JLabel("FPS:");
	private JTextField fps_field = new JTextField(10);
	
	JSlider bend_slider = new JSlider(JSlider.HORIZONTAL, COLOR_MIN, COLOR_MAX, COLOR_INIT);
	JSlider back_slider = new JSlider(JSlider.HORIZONTAL, COLOR_MIN, COLOR_MAX, COLOR_INIT);
	
	private JLabel bend_color_label = new JLabel("Bend Color:");
	private JLabel back_color_label = new JLabel("Back Color:");
	
	
	
	private SpringLayout layout = new SpringLayout();
	
	private JCheckBox textBox = new JCheckBox("Textures");
	private JButton gen = new JButton("Generate");
	
	public Gui(int size_x, int size_y)
	{
		init(size_x, size_y);
	}
	
	public void init(int size_x, int size_y)
	{
		
		   Container contentPane = this.getContentPane();
        this.setLayout(layout);
        this.setResizable(false);
       gen.addActionListener(this);
        
      //==============================================================
        
        this.add(size_x_label);
        this.add(size_x_field);
        
        layout.putConstraint(SpringLayout.WEST, size_x_label,
                5,
                SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, size_x_label,
                5,
                SpringLayout.NORTH, contentPane);


	layout.putConstraint(SpringLayout.WEST, size_x_field,
                5,
                SpringLayout.EAST, size_x_label);
	layout.putConstraint(SpringLayout.NORTH, size_x_field,
                5,
                SpringLayout.NORTH, contentPane);


	layout.putConstraint(SpringLayout.EAST, contentPane,
                5,
                SpringLayout.EAST, size_x_field);
	layout.putConstraint(SpringLayout.SOUTH, contentPane,
                5,
                SpringLayout.SOUTH, size_x_field);

	
	//==============================================================
	
	   this.add(size_y_label);
       this.add(size_y_field);
       
       layout.putConstraint(SpringLayout.WEST, size_y_label,
               5,
               SpringLayout.WEST, contentPane);
       
       layout.putConstraint(SpringLayout.NORTH, size_y_label,
               25,
               SpringLayout.NORTH, size_x_label);


	layout.putConstraint(SpringLayout.WEST, size_y_field,
               5,
               SpringLayout.EAST, size_y_label);
	layout.putConstraint(SpringLayout.NORTH, size_y_field,
               25,
               SpringLayout.NORTH, size_x_field);


	layout.putConstraint(SpringLayout.EAST, contentPane,
               5,
               SpringLayout.EAST, size_y_field);
	layout.putConstraint(SpringLayout.SOUTH, contentPane,
               5,
               SpringLayout.SOUTH, size_y_field);
		
	//==============================================================
	
	 this.add(bend_x_label);
     this.add(bend_x_field);
     
     layout.putConstraint(SpringLayout.WEST, bend_x_label,
             5,
             SpringLayout.WEST, contentPane);
     
     layout.putConstraint(SpringLayout.NORTH, bend_x_label,
             25,
             SpringLayout.NORTH, size_y_label);


	layout.putConstraint(SpringLayout.WEST, bend_x_field,
             11,
             SpringLayout.EAST, bend_x_label);
	
	layout.putConstraint(SpringLayout.NORTH, bend_x_field,
             25,
             SpringLayout.NORTH, size_y_field);


	layout.putConstraint(SpringLayout.EAST, contentPane,
             5,
             SpringLayout.EAST, bend_x_field);
	layout.putConstraint(SpringLayout.SOUTH, contentPane,
             5,
             SpringLayout.SOUTH, bend_x_field);
	
	//==============================================================
	
	this.add(bend_y_label);
    this.add(bend_y_field);
    
    layout.putConstraint(SpringLayout.WEST, bend_y_label,
            5,
            SpringLayout.WEST, contentPane);
    
    layout.putConstraint(SpringLayout.NORTH, bend_y_label,
            25,
            SpringLayout.NORTH, bend_x_label);


	layout.putConstraint(SpringLayout.WEST, bend_y_field,
            11,
            SpringLayout.EAST, bend_y_label);
	
	layout.putConstraint(SpringLayout.NORTH, bend_y_field,
            25,
            SpringLayout.NORTH, bend_x_field);


	layout.putConstraint(SpringLayout.EAST, contentPane,
            5,
            SpringLayout.EAST, bend_y_field);
	layout.putConstraint(SpringLayout.SOUTH, contentPane,
            5,
            SpringLayout.SOUTH, bend_y_field);
	
	//==============================================================
	
	this.add(distance_field);
	this.add(distance_label);
	
    layout.putConstraint(SpringLayout.WEST, distance_label,
            5,
            SpringLayout.WEST, contentPane);
    
    layout.putConstraint(SpringLayout.NORTH, distance_label,
            25,
            SpringLayout.NORTH, bend_y_label);


	layout.putConstraint(SpringLayout.WEST, distance_field,
            1,
            SpringLayout.EAST, distance_label);
	
	layout.putConstraint(SpringLayout.NORTH, distance_field,
            25,
            SpringLayout.NORTH, bend_y_field);


	layout.putConstraint(SpringLayout.EAST, contentPane,
            5,
            SpringLayout.EAST, distance_field);
	layout.putConstraint(SpringLayout.SOUTH, contentPane,
            5,
            SpringLayout.SOUTH, distance_field);
	
	//==============================================================
	
		this.add(speed_field);
		this.add(speed_label);
		
	    layout.putConstraint(SpringLayout.WEST, speed_label,
	            5,
	            SpringLayout.WEST, contentPane);
	    
	    layout.putConstraint(SpringLayout.NORTH, speed_label,
	            25,
	            SpringLayout.NORTH, distance_label);


		layout.putConstraint(SpringLayout.WEST, speed_field,
	            15,
	            SpringLayout.EAST, speed_label);
		
		layout.putConstraint(SpringLayout.NORTH, speed_field,
	            25,
	            SpringLayout.NORTH, distance_field);


		layout.putConstraint(SpringLayout.EAST, contentPane,
	            5,
	            SpringLayout.EAST, speed_field);
		layout.putConstraint(SpringLayout.SOUTH, contentPane,
	            5,
	            SpringLayout.SOUTH, speed_field);	
		//==============================================================
		
			this.add(num_field);
			this.add(num_label);
			
		    layout.putConstraint(SpringLayout.WEST, num_label,
		            5,
		            SpringLayout.WEST, contentPane);
		    
		    layout.putConstraint(SpringLayout.NORTH, num_label,
		            25,
		            SpringLayout.NORTH, speed_label);


			layout.putConstraint(SpringLayout.WEST, num_field,
		            15,
		            SpringLayout.EAST, speed_label);
			
			layout.putConstraint(SpringLayout.NORTH, num_field,
		            25,
		            SpringLayout.NORTH, speed_field);


			layout.putConstraint(SpringLayout.EAST, contentPane,
		            5,
		            SpringLayout.EAST, num_field);
			layout.putConstraint(SpringLayout.SOUTH, contentPane,
		            5,
		            SpringLayout.SOUTH, num_field);	
	//===============================================================
			
			
			this.add(fps_field);
			this.add(fps_label);
			
		    layout.putConstraint(SpringLayout.WEST, fps_label,
		            5,
		            SpringLayout.WEST, contentPane);
		    
		    layout.putConstraint(SpringLayout.NORTH, fps_label,
		            25,
		            SpringLayout.NORTH, num_label);


			layout.putConstraint(SpringLayout.WEST, fps_field,
		            15,
		            SpringLayout.EAST, fps_label);
			
			layout.putConstraint(SpringLayout.NORTH, fps_field,
		            25,
		            SpringLayout.NORTH, num_field);


			layout.putConstraint(SpringLayout.EAST, contentPane,
		            5,
		            SpringLayout.EAST, fps_field);
			layout.putConstraint(SpringLayout.SOUTH, contentPane,
		            5,
		            SpringLayout.SOUTH, fps_field);	
	//===============================================================
			
	
			this.add(bend_slider);
			this.add(bend_color_label);
			
			bend_slider.addChangeListener(this);
		    layout.putConstraint(SpringLayout.WEST, bend_color_label,
		            5,
		            SpringLayout.WEST, contentPane);
		    
		    layout.putConstraint(SpringLayout.NORTH, bend_color_label,
		            25,
		            SpringLayout.NORTH, fps_label);


			layout.putConstraint(SpringLayout.WEST, bend_slider,
		            15,
		            SpringLayout.EAST, bend_color_label);
			
			layout.putConstraint(SpringLayout.NORTH, bend_slider,
		            25,
		            SpringLayout.NORTH, fps_field);


			layout.putConstraint(SpringLayout.EAST, contentPane,
		            5,
		            SpringLayout.EAST, bend_slider);
			layout.putConstraint(SpringLayout.SOUTH, contentPane,
		            5,
		            SpringLayout.SOUTH, bend_slider);	
	//===============================================================
			
			
			this.add(back_slider);
			this.add(back_color_label);
			back_slider.addChangeListener(this);
		    layout.putConstraint(SpringLayout.WEST, back_color_label,
		            5,
		            SpringLayout.WEST, contentPane);
		    
		    layout.putConstraint(SpringLayout.NORTH, back_color_label,
		            25,
		            SpringLayout.NORTH, bend_color_label);


			layout.putConstraint(SpringLayout.WEST, back_slider,
		            15,
		            SpringLayout.EAST, back_color_label);
			
			layout.putConstraint(SpringLayout.NORTH, back_slider,
		            25,
		            SpringLayout.NORTH, bend_slider);


			layout.putConstraint(SpringLayout.EAST, contentPane,
		            5,
		            SpringLayout.EAST, bend_slider);
			layout.putConstraint(SpringLayout.SOUTH, contentPane,
		            5,
		            SpringLayout.SOUTH, back_slider);	
	//===============================================================
			
	this.add(textBox);
	textBox.setSelected(false);
	
	layout.putConstraint(SpringLayout.WEST, textBox,
            5,
            SpringLayout.WEST, contentPane);
    
    layout.putConstraint(SpringLayout.NORTH, textBox,
            25,
            SpringLayout.NORTH, back_color_label);

	layout.putConstraint(SpringLayout.SOUTH, contentPane,
            5,
            SpringLayout.SOUTH, textBox);	
	
	//===============================================================
			
			this.add(gen);	
			
			layout.putConstraint(SpringLayout.WEST, gen,
		            5,
		            SpringLayout.WEST, contentPane);
		    
		    layout.putConstraint(SpringLayout.NORTH, gen,
		            25,
		            SpringLayout.NORTH, textBox);

			layout.putConstraint(SpringLayout.SOUTH, contentPane,
		            5,
		            SpringLayout.SOUTH, gen);	
			
			
			
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);	
		
		this.pack();
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		
		

		if (arg0.getSource().equals(gen))
		{
		
			String[] args = new String[11];
			
			
			args[0] = size_x_field.getText();
			args[1] = size_y_field.getText();
			args[2] = bend_x_field.getText();
			args[3] = bend_y_field.getText();
			args[4] = distance_field.getText();
			args[5] = speed_field.getText();
			args[6] = num_field.getText();
			args[7] = fps_field.getText();
			args[8] = Integer.toString(bend_slider.getValue());
			args[9] = Integer.toString(back_slider.getValue());
			args[10] = Boolean.toString(textBox.isSelected() );
			Window window = new Window(args);
		}
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub
		bend_color_label.setForeground(new Color(bend_slider.getValue()));
		back_color_label.setForeground(new Color(back_slider.getValue()));

	}
	
}
