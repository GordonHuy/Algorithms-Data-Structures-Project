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

    public class Board extends JPanel{

        public void paintComponent(Graphics g){
            g.setColor(Color.lightGray);
            g.fillRect(0,0, 1280,720);
            for(int x = 0; x < 16; x++){
                for(int y = 0; y < 8; y++){
                    g.setColor(Color.darkGray);  
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
           System.out.println("x: " + mx + ",Y: " + my);
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
