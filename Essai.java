
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Path2D;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
 
class Essai extends JPanel implements MouseListener, MouseMotionListener {
 
    private List<Shape> shapeList = new LinkedList<Shape>();
    private List<Point> pointList = new LinkedList<Point>();
    private boolean isEditing = false;
    private int mouseX;
    private int mouseY;
    private JFrame frame;
 
    public Essai() {
        
        addMouseListener(this);
        addMouseMotionListener(this);
    }
 
    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseMoved(MouseEvent event) {
        if (isEditing) {
            mouseX = event.getX();
            mouseY = event.getY();
            
        }
        //System.out.println("Click: x= "+event.getX()+" y = "+event.getY());
        repaint();
    }
 
    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseClicked(MouseEvent e) {
    	System.out.println("Position: x= "+e.getX()+" y = "+e.getY());
        if ((e.getModifiers() & InputEvent.BUTTON1_MASK) != 0) {
            // 1er click on demarre l'edition.
            if (!isEditing) {
                isEditing = true;
            }
            pointList.add(new Point(e.getX(), e.getY()));
            mouseX = e.getX();
            mouseY = e.getY();
        } else if ((e.getModifiers() & InputEvent.BUTTON3_MASK) != 0) {
            Point p = new Point(e.getX(), e.getY());
            // Avoids very small polygons.
            if (!p.equals(pointList.get(pointList.size() - 1))) {
                pointList.add(p);
            }
            isEditing = false;
            if (pointList.size() >= 2) {
                Path2D.Float path = new Path2D.Float();
                path.moveTo(pointList.get(0).x, pointList.get(0).y);
                for (int i = 1; i < pointList.size(); i++) {
                    path.lineTo(pointList.get(i).x, pointList.get(i).y);
                }
                path.closePath();
                shapeList.add(path);
            }
            pointList.clear();
        }
        repaint();
    }
 
    /**
     * {@inheritDoc}
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension size = getSize();
        Insets insets = getInsets();
        Graphics2D g2d = (Graphics2D) g.create(insets.left, insets.top, size.width - (insets.left + insets.right), size.height - (insets.top + insets.bottom));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        try {
            for (Shape shape : shapeList) {
                g2d.draw(shape);
            }
            if (isEditing) {
                g2d.setColor(Color.RED);
                for (int i = 0; i < pointList.size() - 1; i++) {
                    Point p1 = pointList.get(i);
                    Point p2 = pointList.get(i + 1);
                    g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
                    
                }
                g2d.setColor(Color.GREEN);
                Point p1 = pointList.get(pointList.size() - 1);
                g2d.drawLine(p1.x, p1.y, mouseX, mouseY);
            }
        } finally {
            g2d.dispose();
        }
    }
 
    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    	//System.out.println("Click: x= "+e.getX()+" y = "+e.getY());
    }
 
    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
 
    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
 
    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    } 
 
    @Override
    public void mouseDragged(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
 /*   public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
 
            @Override
            public void run() {
                JFrame fr = new JFrame();
                Essai e = new Essai(fr);
                
                fr.setSize(800, 800);
                fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                fr.add(e);
                fr.setVisible(true);
            }
        });
 
    }*/

 
   }
