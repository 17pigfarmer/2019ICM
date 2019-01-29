import util.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class main {
	

    public static void main(String[] args) {
    	

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
    
                MyFrame frame = new MyFrame();
                frame.btnStart.setSize(106, 35);
                frame.textArea.setSize(249, 180);
                frame.btnStart.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
                frame.btnStart.setText("Start");
                frame.textArea.setLocation(300, 47);

                frame.btnStart.setLocation(235, 258);
                frame.p.setBounds(10, 47, 278, 201);
                
                JLabel lblQueuesAtVertices = new JLabel("Queues at vertices");
                lblQueuesAtVertices.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 18));
                lblQueuesAtVertices.setBounds(53, 10, 196, 27);
                frame.getContentPane().add(lblQueuesAtVertices);
                
     
                frame.setVisible(true);
            }
        });
        
    }


    public static class MyFrame extends JFrame  implements ActionListener{
    	static public Graph gra = new Graph();
        public static final String TITLE = "Queues at vertices";
        public static MyPanel p;
        public static final int WIDTH = 500;
        public static final int HEIGHT = 400;
        public JButton btnStart;
        public JTextArea textArea;
        Timer timer = null;
        int time = 0;
        public MyFrame() {
            super();
            initFrame();
            
            for(int i = 0;i<gra.VertxNum;i++) {
    			if(i ==0 ) {
    				gra.Vertex[i]=new ExitNode(i,8);
    				continue;
    			}
    			//if(i == 4 ) {
    			//gra.Vertex[i]=new GuideNode(i,3);
    			//continue;
    			//}
    			gra.Vertex[i]=new Node(i,3);
    		}
    		
    		
    		for(Node node:gra.Vertex) {
    			if(node.getClass()!=ExitNode.class) {
        		node.ini.add(new People(0,0,3,0,0,0,3));
        		node.ini.add(new People(0,0,3,0,0,0,3));
        		node.ini.add(new People(0,0,3,0,0,0,3));
        		node.ini.add(new People(0,0,3,0,0,0,3));
    			node.ini.add(new People(0,0,3,0,0,0,3));
    			node.ini.add(new People(0,0,3,0,0,0,3));
    			node.ini.add(new People(0,0,3,0,0,0,3));
    			node.ini.add(new People(0,0,3,0,0,0,3));
    			node.ini.add(new People(0,0,3,0,0,0,3));
    			node.ini.add(new People(0,0,3,0,0,0,3));
    			node.ini.add(new People(0,0,3,0,0,0,2));
    			node.ini.add(new People(0,0,3,0,0,0,2));
    			node.ini.add(new People(0,0,3,0,0,0,1));
    			node.ini.add(new People(0,0,3,0,0,0,1));


    			}
    		}
    		
            
        }

        private void initFrame() {

   
            setTitle(TITLE);
            //this.setUndecorated(true); 
    		//this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
            setSize(WIDTH, HEIGHT);

            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            
            setLocationRelativeTo(null);
            
            JPanel contentPane = new JPanel();
            contentPane.setLayout(null);
            setContentPane(contentPane);
            MyPanel panel = new MyPanel(this,gra);
            panel.setBounds(0, 0, 250, 230);
            contentPane.add(panel);
            p = panel;
           
            
            btnStart = new JButton("start");
    		btnStart.setBounds(190,230, 93, 23);
    		contentPane.add(btnStart);
    		btnStart.addActionListener(this);
    		
    		textArea = new JTextArea();
    		contentPane.add(textArea);
    		textArea.setBounds(260, 63, 200, 180);
    		
    	

            
        }
        
        public void startThread() {
    		if(timer!=null) {
    			timer.cancel();
    		
    		}
    		timer = new Timer();
    		timer.scheduleAtFixedRate(new MyTask(), 100, 1000);;
    		
    	}
        

    	public class MyTask extends TimerTask{

    		@Override
    		public void run() {
    			p.updateUI();
    			
    			
    			time ++;
    			Graph.oneSecondPassed(gra, 0);
    			for(int k = 0;k<gra.VertxNum;k++) {
    				gra.isTrav[k] = 0;
    			}
    			if(Graph.hasNoPeople(gra)) {
    				textArea.append("Evacuation success! Time:"+time+"\n");
    				
    			}
    			else {
    				textArea.append("Evacuation proceeding! Time:"+time+"\n");
    			}

    		}
    			
    	}
    
        @Override
		public void actionPerformed(ActionEvent e) {
        	if (e.getSource() == btnStart) {
        		startThread();
        		}
        	}
	
		}

    public static class MyPanel extends JPanel {

        private MyFrame frame;
        private Graph gra;

        public MyPanel(MyFrame frame,Graph gra) {
            super();
            this.frame = frame;
            this.gra = gra;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            drawRect(g);

             drawArc(g);

        }


        private void drawRect(Graphics g) {
            
            Graphics2D g2d = (Graphics2D) g.create();

            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(Color.GRAY);

        
            for(int i=0;i<gra.VertxNum;i++) {
            	g2d.drawRect(20, i*20, 250, 20);
            }
            
            g2d.setColor(Color.BLACK);
            
            g2d.setFont(new Font(null, Font.PLAIN, 25));

            for(int i=0;i<gra.VertxNum;i++) {
            	g2d.drawString(new Integer(i).toString(), 0, i*20+20);
            }
            
           

            g2d.dispose();
        }

       
        private void drawArc(Graphics g) {
            
            Graphics2D g2d = (Graphics2D) g.create();

            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            

            g2d.setColor(new Color(144, 212, 236));

            for(int i = 0;i<gra.VertxNum;i++) {
            	
            	if(gra.Vertex[i].que.isEmpty()==false) {
            	for(int t=0;t<gra.Vertex[i].que.size();t++)
            	{
            		g2d.fillArc(20+t*10, i*20, 10, 10, 90, 360);
            	}
            	}
            }

            g2d.dispose();
        }


    }
}
