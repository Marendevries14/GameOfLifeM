import javax.swing.JFrame;
import java.awt.*;

// setup frame GUI
/*Option: JFrame f = new JFrame ();
 * f.add(new LifePanel());
 * f.setSize(900,900);
 * etc */

public static class GameFrame extends JFrame {

    public GameFrame() {
        add(new LifePanel());

        // settings frame
        setSize(1300, 700);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}

public static void main(String[] args) {
    new GameFrame();
}


