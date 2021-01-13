
package blockshifter.apps.swing;

import blockshifter.view.BlockDisplay;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;

public class BlockPanel extends JPanel implements BlockDisplay{
    
    private int x;
    private int y;
    public int MAX;
    private Moved moved;

    public BlockPanel(int max) {
        MouseHandler mouseHandler = new MouseHandler();
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
        this.MAX = max;
    }
    
    
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 700, 700);
        
        g.setColor(Color.BLACK);
        int max = MAX*SIZE;
        for (int i = 0; i <= MAX; i++){
            int d = i*SIZE;
            g.drawLine(d, 0, d, max);
            g.drawLine(0, d, max, d);
        }
        
        g.setColor(Color.RED);
        g.fillRect(x, y, SIZE, SIZE);
    }

    @Override
    public void paintBlock(int x, int y) {
        this.x = x;
        this.y = y;
        repaint();
    }

    @Override
    public void on(Moved moved) {
        this.moved = moved;
    }
    
    private class MouseHandler implements MouseListener, MouseMotionListener{
        private boolean pressed = false;
        @Override
        public void mouseClicked(MouseEvent e) {
            
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getX() < x || e.getY() < y || e.getX() > x+SIZE || e.getY() > y+SIZE) return;
            pressed = true;
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            pressed = false;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            
        }

        @Override
        public void mouseExited(MouseEvent e) {
            
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            moved.to(e.getX(), e.getY());
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            
        }
        
    }
}
