import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.*;
import java.awt.*;
import java.awt.Color;

public class GUI extends JFrame{

    int spacing = 5;
    int a = 80; //Variable for change size of the square shape slots
    int neighs = 0;

    public int mx = -100; //Variable for mouse coordinates
    public int my = -100;

    public int SmileyX = 605;
    public int SmileyY = 5;

    public boolean happy = true;

    Random rand = new Random();

    //create array 16 by 8 
    int[][] mines = new int[16][8];
    int[][] neighbours = new int[16][8];
    boolean[][] revealed = new boolean[16][8];
    boolean[][] flags = new boolean[16][8];

    //Create the interface
    public GUI(){
        this.setTitle("Mine Sweeper");
        this.setSize(1296,758);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);

        for(int x = 0; x < 16; x++){
            for(int y = 0; y < 8; y++){
                // Mine will spawn in the board as in this case is 1/5 chance 
                if(rand.nextInt(100) < 40) //<-- Change this number to change overal difficulty of the game
                {
                    mines[x][y] = 1;
                }
                else{
                    mines[x][y] = 0;
                }
                revealed[x][y] = false; //Revealing the number of mines in the neighbour slots from the current slot after choosing 
            }
        }

        // Function for counting mines if they present in the neighbour slot
        for(int x = 0; x < 16; x++){
            for(int y = 0; y < 8; y++){
                neighs = 0;
                for(int z = 0; z < 16; z++){
                    for(int i = 0; i < 8; i++){
                        if(!(z == x && z == y)){
                            if (isN(x,y,z,i) == true){
                                neighs ++;
                            }
                        }
                    }
                    neighbours[x][y] = neighs;
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

    public class Board extends JPanel{

        public void paintComponent(Graphics g){

            g.setColor(Color.lightGray);
            g.fillRect(0,0, 1280,720);

            // creating square slots in this case is horizontal 16 slots, 8 vertical slots 
            for(int x = 0; x < 16; x++){
                for(int y = 0; y < 8; y++){
                    g.setColor(Color.darkGray);
                
                    /*
                    if (mines[x][y] == 1){ //Show mines in the board (Using for debugging)
                        g.setColor(Color.orange);
                    }
                    */

                    //Reveal the selected slots as white, mines as red
                    if (revealed[x][y] == true){
                        g.setColor(Color.white);
                        if (mines[x][y] == 1){
                            g.setColor(Color.red);
                        }
                    }

                    //Detect when the mouse pointer hover above the slots
                    if (mx >= spacing+x*a+spacing+2.2 && mx < spacing+x*a+a-1*spacing && my >= spacing+y*a+a+26 && my < spacing+y*a+26+a+a-2*spacing){
                        g.setColor(Color.gray);
                    }

                    g.fillRect(spacing+x*a, spacing+y*a+a, a-2*spacing, a-2*spacing); //Numbers of the slots created

                    // Reveal the number of potential neighbour slots that contain mine
                    if (revealed[x][y] == true){
                        g.setColor(Color.BLACK);
                        //Show number of mines that in the neighbours slots (and color for each number)

                        if (mines[x][y] == 0 && neighbours[x][y] != 0 ){
                            if (neighbours[x][y] == 1){
                                g.setColor(new Color(157,34,53));
                            }
                            else if (neighbours[x][y] == 2){
                                g.setColor(Color.orange);
                            }
                            else if (neighbours[x][y] == 3){
                                g.setColor(new Color(3,37,126));
                            }
                            else if (neighbours[x][y] == 4){
                                g.setColor(Color.green);
                            }
                            else if (neighbours[x][y] == 5){
                                g.setColor(Color.blue);
                            }
                            else if (neighbours[x][y] == 6){
                                g.setColor(new Color(106,13,173));
                            }
                            else if (neighbours[x][y] == 7){
                                g.setColor(new Color(77,77,225));
                            }
                            else if (neighbours[x][y] == 8){
                                g.setColor(new Color(219,62,177));
                            }

                            g.setFont(new Font("Arial", Font.BOLD, 42 ));
                            g.drawString(Integer.toString(neighbours[x][y]), x*80+27, y*80+80+55);
                        }
                        //Shape of the mines 
                        else if (mines[x][y] == 1){
                            g.fillRect(x*a+30, y*a+a+20, 20, 40); 
                            g.fillRect(x*a+20, y*a+a+30, 40, 20);
                            g.fillRect(x*a+25, y*a+a+25, 30, 30);
                            g.fillRect(x*a+38, y*a+a+15, 5, 50);
                            g.fillRect(x*a+15, y*a+a+38, 50, 4);
                        }
                    }
                }
            }

            //Smiley dude
            g.setColor(Color.yellow);
            g.fillOval(SmileyX, SmileyY, 70, 70);
            g.setColor(Color.black);
            g.fillOval(SmileyX+15, SmileyY+20, 10, 10);
            g.fillOval(SmileyX+45, SmileyY+20, 10, 10);
            if (happy == true){
                g.fillRect(SmileyX+20, SmileyY+50, 30, 5);
                g.fillRect(SmileyX+15, SmileyY+45, 5, 5);
                g.fillRect(SmileyX+50, SmileyY+45, 5, 5);
            }
            else {
                g.fillRect(SmileyX+20, SmileyY+45, 30, 5);
                g.fillRect(SmileyX+15, SmileyY+50, 5, 5);
                g.fillRect(SmileyX+50, SmileyY+50, 5, 5);
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

            if(inBoxX() != -1 && inBoxY() != -1){
                revealed[inBoxX()][inBoxY()] = true;
            }

            if(inBoxX() != -1 && inBoxY() != -1){
                System.out.println("The mouse is in the [" + inBoxX() + "," + inBoxY() + "], Number of mines neighbours: " + neighbours[inBoxX()][inBoxY()]);
            }
            else {
                System.out.println("The pointer is out of range");
            }
        
            //System.out.println("The mouse was clicked");

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

    //Detect if there is a click inside the slots or not  
    public int inBoxX(){
        for(int x = 0; x < 16; x++){
            for(int y = 0; y < 8; y++){
                if (mx >= spacing+x*a+spacing+2.2 && mx < spacing+x*a+a-1*spacing && my >= spacing+y*a+a+26 && my < spacing+y*a+26+a+a-2*spacing){
                    return x;
                }
            }
        }
        return -1; //its mean its not inside the slots
    }

    public int inBoxY(){
        for(int x = 0; x < 16; x++){
            for(int y = 0; y < 8; y++){
                if (mx >= spacing+x*a+spacing+2.2 && mx < spacing+x*a+a-1*spacing && my >= spacing+y*a+a+26 && my < spacing+y*a+26+a+a-2*spacing){
                    return y;
                }
            }
        }
        return -1;
    }

    //Function for knowing the distance from neighbour slot to current slot along the X,Y axis and knowing there is the mines in that neighbour slor or not
    public boolean isN(int mX, int mY, int cX, int cY){
        if (mX  - cX < 2 && mX - cX > -2 && mY - cY < 2 && mY - cY > -2 && mines[cX][cY] == 1){
            return true;
        }
        return false;
    }
}
