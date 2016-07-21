import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Window extends JFrame implements Runnable
{
	private JPanel render_panel = new JPanel();
	private BufferedImage image;
	private Color bend_color = Color.RED;
	private Color back_color = Color.BLUE;
	private Graphics g;
	private int size_x = 600, size_y = 600 ;
	private int frames = 1;
	private int speed = 4;
	private boolean error = false; 
	private int bend_width = 100;
	private int bend_height = 200;
	private int distance;
	private int y_cord = size_y/2;
	private int num = 10;
	private int tick;
	private int image_num = 0;
	private boolean isRunning;
	private boolean IsTextures = false;
	
	private BufferedImage image_array[] = new BufferedImage[16];
	private BufferedImage image_line;
	public Window(String[] args)
	{
		args_parser(args);
		init();
	}
	
	public void args_parser(String[] args)
	{
		size_x = Integer.parseInt(args[0]);
		size_y = Integer.parseInt(args[1]);
		
		if (size_x > 0 && size_y > 0  && size_x < 3000 && size_y < 3000)
		{
			System.out.println("Frame width = " + args[0]);
		
			System.out.println("Frame height = " + args[1]);
		}
		else 
		{
			System.out.println("Frame values out of range (1 - 2999)");
			System.exit(-1);
		}
		
		bend_width = Integer.parseInt(args[2]);
		bend_height = Integer.parseInt(args[3]);
		
		if (bend_width> 0 && bend_height > 0  && bend_width < size_x && bend_height < size_y)
		{
			System.out.println("Bend width = " + bend_width);
		
			System.out.println("Frame height = " + bend_height);
		}
		else 
		{
			System.out.println("Bend values out of range (1 - " + size_x + ")");
			System.out.println("Bend values out of range (1 - " + size_y + ")");
			error = true;
			 on_close();
		}
		
		distance = Integer.parseInt(args[4]);
		
		if (distance > size_y && distance < 5000)
		{
			System.out.println("Distance = " + distance);
		}
		else 
		{
			System.out.println("Distance values out of range ("  + (size_y + 1)+  " - 4999)");
			error = true;
			 on_close();
		}
		
		speed = Integer.parseInt(args[5]);
		
		if (speed < 50 && speed > -50)
		{
			System.out.println("Speed = " + speed);
		}
		else 
		{
			System.out.println("Speed values out of range (- 49 - 49)");
			error = true;
			 on_close();
		}
		
		num = Integer.parseInt(args[6]);
		
		if (num > 0 && num < 101)
		{
			System.out.println("Number of frames = " + num);
		}
		else 
		{
			System.out.println("Num of Frames values out of range (1 - 100)");
			error = true;
			 on_close();
		}
		
		frames = Integer.parseInt(args[7]);
		
		if (frames > 0 && frames < 100)
		{
			System.out.println("FPS = " + num);
		}
		else 
		{
			System.out.println("FPS values out of range (1 - 99)");
			error = true;
			 on_close();
		}
		
		bend_color = new Color(Integer.parseInt(args[8]));
		back_color = new Color(Integer.parseInt(args[9]));
		
		IsTextures = Boolean.parseBoolean(args[10]);
		
		
		
		if (IsTextures)
		{
			for (int i = 0; i < 16; i++)
			{
				try {
			    image_array[i] = ImageIO.read(new File("./Textures/"+ i +".png"));
			   
				} catch (IOException e) {
				System.err.println("./Textures/"+ i +".jpg");
				}
			}
			try{
			image_line = ImageIO.read(new File ("./Textures/Lines/I_R.png"));
			
			} catch (IOException e) {
		
			}
		}
		
	}
	
	
	public void init()
	{
		
		WindowListener exitListener = new WindowAdapter() {

		    @Override
		    public void windowClosing(WindowEvent e) {
		       on_close();
		        }
		    
		};
		
		this.addWindowListener(exitListener);
		this.setSize(size_x, size_y);
		this.setResizable(false);
		this.add(render_panel);
		this.setVisible(true);
		g = render_panel.getGraphics();
		
		render(g);
		isRunning = true;
		new Thread(this).start();
	}
	
	public int on_close()
	{
		isRunning = false;
		System.out.println("FINISHED:ERROR");
		return 1;
	}
	
	public void render(Graphics g)
	{
		image = new BufferedImage(size_x, size_y,  BufferedImage.TYPE_INT_ARGB);
		Graphics g2 = image.createGraphics();
		
		
		if (IsTextures)
		{
			int x_lim = size_x/100 + 2;
			int y_lim = size_y/100 + 2;
			int y_move = y_cord % 100 - 100;
		for (int i = 0; i < x_lim; i ++)
			{
			for (int j = 0; j < y_lim; j ++)
			{
				
				Random rand = new Random();
				if (rand.nextInt(100) < 15)
				{
					g2.drawImage(image_array[rand.nextInt(16)], 100*i, 100*j + y_move, null);
				}
				else 
				{
					g2.drawImage(image_array[1], 100*i, 100*j + y_move, null);

				}
			}
			}
		
		//	Image buff = image_line.getScaledInstance(bend_width, bend_height, Image.SCALE_DEFAULT);
		//	g2.drawImage(buff, (int)(size_x/2 - bend_width/2) , y_cord,  null);
		}
		else
		{
			g2.setColor(back_color);				
			g2.fillRect(0, 0, size_x, size_y);
			
			g2.setColor(bend_color);	
			g2.fillRect( (int)(size_x/2 - bend_width/2) , y_cord, bend_width, bend_height);
		}
		
		g2.setColor(bend_color);	
		g2.fillRect( (int)(size_x/2 - bend_width/2) , y_cord, bend_width, bend_height);
			
		
		g.drawImage(image, 0, 0, null);
		
		
		
	}
	
	
	
	public static void main(String[] args)
	{
		
		Gui gui = new Gui(350, 600);
	}


	@Override
	public void run() 
	{
		
		while (isRunning && !error)
		{
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
			
				e.printStackTrace();
			}
			
			render(g);
			
			if (y_cord < -1*bend_height)
			{
				y_cord = distance;
			}
			
			if (y_cord >  distance)
			{
				y_cord =  - 1*bend_height;
			}
			
			y_cord = y_cord + speed;
			tick++;
			
			
			if ((tick == frames) && (num != 0))
			{
			try 
			{  
				File outputFile = new File("./Stream/FRAME_" +("00000" + image_num).substring(Integer.toString(image_num).length())+ ".png");
				outputFile.getParentFile().mkdirs();
				ImageIO.write(image, "png", outputFile);
				image_num++;
				num--;
				tick = 0;
			}
		
				
			catch (IOException e) 	
			{
			e.printStackTrace();
			}
			
			if (num == 0)
			{
	
				isRunning = false;
				g.dispose();
				System.out.println("Finished: Success!!");
				this.dispose();
			}
		}
			
	}
		this.dispose();
	}
}
