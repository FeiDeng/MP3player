import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class myplayer extends JFrame implements ActionListener {
	private static String[] mf;
    
	private static int no = 0, lastno = 30000;
	
	private static MP3 mp3 = null;
	JButton playButton = new JButton("play");
	 JButton pauseButton = new JButton("pause");
	// JButton resumeButton = new JButton("resume");
	 JButton stopButton = new JButton("stop");
	 JButton nextButton = new JButton("next");
	 JButton backButton = new JButton("back");
	 static DefaultListModel mylist = new DefaultListModel();
	 JList list = new JList(mylist);
	public myplayer()
	 {   
		 JFrame myFrame = new JFrame();
		 Container   c=myFrame.getContentPane(); 
		 c.setLayout(new FlowLayout(FlowLayout.CENTER));
		 myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 myFrame.setSize(400, 200);
		 myFrame.setTitle("Fei's mp3player");
		 myFrame.setVisible(true);
		 
		 playButton.addActionListener(this); 
		 pauseButton.addActionListener(this); 
		// resumeButton.addActionListener(this); 
		 stopButton.addActionListener(this); 
		 nextButton.addActionListener(this); 
		 backButton.addActionListener(this); 
		// list.addListSelectionListener(this);
		 list.setSelectedIndex(0);
		 list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		 list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		 myFrame.getContentPane().add(playButton);
		 myFrame.getContentPane().add(pauseButton);
		// myFrame.getContentPane().add(resumeButton);
		 myFrame.getContentPane().add(stopButton);
		 myFrame.getContentPane().add(nextButton);
		 myFrame.getContentPane().add(backButton);
		 myFrame.getContentPane().add(list);
		// JButton jButton6 = new JButton();
		// JButton jButton7 = new JButton();
		 File f = new File("../");
			mf = f.list();
			if (mf.length == 0) {
				System.out.println("No Songs!");
				System.exit(0);
			}
			no=(int) (Math.random()*mf.length); 
	 }
	
	 
	
	 public void actionPerformed(ActionEvent e) {
		 
		
		 if (e.getSource()==playButton){
			 //playNext();
			
			 no=list.getSelectedIndex();
			 System.out.println(list.getSelectedIndex());
			 System.out.println(no);
			 playNext();
			 
				
		 }
		 
		 
         if (e.getSource()==pauseButton){
        	 if (mp3 != null)
        	 {
        		
        		 if(pauseButton.getText()=="pause")
        		 {
        		
        		 mp3.pause();
            	 pauseButton.setText("resume");
            	 
        		 }
        		 else if(pauseButton.getText()=="resume")
        		 {
        		 mp3.resume();
            	 pauseButton.setText("pause");
        		 }
        	 }
        		  
		 }
        
        if (e.getSource()==stopButton){
        	if (mp3 != null)
				mp3.close();
			
         }
        if (e.getSource()==nextButton){
        	playNext();
        }
        if (e.getSource()==backButton){
        	playback();
        }
	 }
	 
	
	 
	 
	 

		public static void playByName(String name) {
			if (mp3 != null)
				mp3.close();
			System.out.println("Now playing "+mf[no]);
			mp3 = new MP3(name);
			mp3.play();
		}
		
		public static void playNext() {//play next song;
			lastno = no;
			for (int i = no + 1;;) {
				if (i >= mf.length) {
					i = 0;
				}
				if (mf[i].endsWith(".mp3")) {
					no = i;
					break;
				}

				++i;

				if (i == no + 1) {
					System.out.println("No Songs Available!");
					if(mp3!=null)mp3.close();
					System.exit(0);
				}
			}
			playByName("../" + mf[no]);

		}
		public static void playback() {//play back;
			if (lastno == 30000) {
				System.out.println("No Playback!\n");
				return;
			}
			int t = lastno;
			lastno = no;
			no = t;
			playByName("../" + mf[no]);
		}
		
		public static void showSongs(){
		
			for (int i = no; i < mf.length;) {
				
				if (mf[i].endsWith(".mp3")) {
					System.out.println(mf[i]);
					mylist.addElement(mf[i]);
				}
				++i;
			
				if(i==mf.length){
					i=0;
				}
				if(i==no){
					break;
				}
			}
		}
		
		 
		 public static void main(String[] args) {
			 myplayer mp3player=new myplayer();
			 showSongs();
				
		 }
		 
	 
	 
}
