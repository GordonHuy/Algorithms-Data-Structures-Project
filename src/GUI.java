import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.*;
import java.awt.*;

public class GUI extends JFrame{

    //Create the interface
    public GUI(){
        this.setTitle("Mine Sweeper");
        this.setSize(1296,758);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);

        for(int x = 0; x < 16; x++){
            for(int y = 0; y < 8; y++){
                // mine will spawn as in this case is 1/5 chance 
                if(rand.nextInt(100) < 20){
                    mines[x][y] = 1;
                }
                else{
                    mines[x][y] = 0;
                }
            }
        }

        Board board = new Board(); //Create the board
        this.setContentPane(board);

        Movement move = new Movement(); //Detect mouse movement
        this.addMouseMotionListener(move);

        Click click = new Click(); //Detect mouse click
        this.addMouseListener(click);

    }
    int spacing = 5;
    int a = 80; //Variable for change size of the square shape slots

    public int mx = -100; //Variable for mouse cordinates
    public int my = -100;

    Random rand = new Random();

    //create array 16 by 8 
    int[][] mines = new int[16][8];
    int[][] neighbours = new int[16][8];
    boolean[][] revealed = new boolean[16][8];
    boolean[][] flags = new boolean[16][8];

    public class Board extends JPanel{

        public void paintComponent(Graphics g){
            g.setColor(Color.lightGray);
            g.fillRect(0,0, 1280,720);
            // creating square slots in this case is horizontal 16 slots, 8 vertical slots 
            for(int x = 0; x < 16; x++){
                for(int y = 0; y < 8; y++){
                    g.setColor(Color.darkGray);  

                    if (mines [x][y] == 1){
                        g.setColor(Color.orange);
                    }

                    if (mx >= spacing+x*a+spacing+2.2 && mx < spacing+x*a+a-1*spacing && my >= spacing+y*a+a+26 && my < spacing+y*a+26+a+a-2*spacing){
                        g.setColor(Color.red);
                    }

                    g.fillRect(spacing+x*a, spacing+y*a+a, a-2*spacing, a-2*spacing); //Numbers of the slots
                }
            }
        }
    }

    // Mouse interaction
    public class Movement implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {
           mx = e.getX(); //get cordinates for mouse 
           my = e.getY();
           //System.out.println("x: " + mx + ",Y: " + my);
        }
    }

    public class Click implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("The mouse was clicked");
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
