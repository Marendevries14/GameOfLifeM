import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


//SETTINGS FOR PANEL
public class LifePanel extends JPanel implements ActionListener, MouseListener,
        MouseMotionListener, KeyListener {

    int xPanel = 1300;
    int yPanel = 700;
    int size = 10; //pixels
    int xWidth = xPanel / size;
    int yHeight = yPanel / size;
    int[][] life = new int[xWidth][yHeight];
    int[][] beforeLife = new int[xWidth][yHeight];
    int initial = -1;
    Timer time;

    boolean starts = true;

    //class methode
    public LifePanel() {

        setSize(xPanel, yPanel);
        setLayout(null);

        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
        setFocusable(true);

        time = new Timer(80, this);
        //time.start();

    }

    // java swing component
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        setBackground(Color.BLACK);
        grid(g);
        display(g);

    }

    private void grid(Graphics g) {

        g.setColor(Color.darkGray); //shows grid lines

        for (int i = 0; i < life.length; i++) {
            g.drawLine(0, i * size, xPanel, i * size); // row
            g.drawLine(i * size, 0, i * size, yPanel); //column
        }


    }

    private void spawn() {


        for (int x = 0; x < life.length; x++) {
            for (int y = 0; y < (yHeight); y++) {

                if ((int) (Math.random() * 5) == 0) {
                    beforeLife[x][y] = 1; //true
                }
            }


        }

    }

    private void display(Graphics g) {

        g.setColor(Color.ORANGE); //cells color

        copyArray();


        for (int x = 0; x < life.length; x++) {
            for (int y = 0; y < (yHeight); y++) {

                if (life[x][y] == 1) {
                    g.fillRect(x * size, y * size, size, size);
                }
            }


        }


    }

    private void copyArray() {

        for (int x = 0; x < life.length; x++) {
            for (int y = 0; y < (yHeight); y++) {

                life[x][y] = beforeLife[x][y]; // overrides new array.

            }
        }
    }


    private int check(int x, int y) {
        int alive = 0; //% xWidth zodat je geen out of bound expt krijgt.
        alive += life[(x + xWidth - 1) % xWidth][(y + yHeight - 1) % yHeight];
        alive += life[(x + xWidth) % xWidth][(y + yHeight - 1) % yHeight];

        alive += life[(x + xWidth + 1) % xWidth][(y + yHeight - 1) % yHeight];
        alive += life[(x + xWidth - 1) % xWidth][(y + yHeight) % yHeight];

        alive += life[(x + xWidth + 1) % xWidth][(y + yHeight) % yHeight];
        alive += life[(x + xWidth - 1) % xWidth][(y + yHeight + 1) % yHeight];

        alive += life[(x + xWidth) % xWidth][(y + yHeight + 1) % yHeight];
        alive += life[(x + xWidth + 1) % xWidth][(y + yHeight + 1) % yHeight];

        return alive;


    }

    private void clear() {
        for (int x = 0; x < life.length; x++) {
            for (int y = 0; y < (yHeight); y++) {
                beforeLife[x][y] = 0;
            }
        }
    }


    public void actionPerformed(ActionEvent e) {

        int alive;

        for (int x = 0; x < life.length; x++) {
            for (int y = 0; y < (yHeight); y++) {

                alive = check(x, y);
// setting rules
                if (alive == 3) {

                    beforeLife[x][y] = 1;

                } else if (alive == 2 && life[x][y] == 1) {
                    beforeLife[x][y] = 1;
                } else {
                    beforeLife[x][y] = 0;
                }
            }
        }

        repaint(); //refreshes the page
    }

    public void mouseDragged(MouseEvent e) {
        int x = e.getX() / size;
        int y = e.getY() / size;

        if (life[x][y] == 0 && initial == 0) {
            beforeLife[x][y] = 1;
        } else if (life[x][y] == 1 && initial == 1) {
            beforeLife[x][y] = 0;
        }
        repaint();

    }

    public void mouseMoved(MouseEvent e) {

    }

    public void mouseClicked(MouseEvent e) {


    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {


        int x = e.getX() / size;
        int y = e.getY() / size;

        if (life[x][y] == 0) {
            initial = 0;
        } else {
            initial = 1;
        }

    }

    public void mouseReleased(MouseEvent e) {
        initial = -1;

    }

    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();
        if (code == e.VK_R) {
            spawn();
            time.start();
        } else if (code == e.VK_C) {
            clear();
            time.stop();
        }
        else if (code == e.VK_S){
            time.start();
        }
        else if(code == e.VK_A){
            time.stop();
        }

        repaint();
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }


}
