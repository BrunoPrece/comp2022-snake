import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;
/**
 * Write a description of class Snake here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Snake extends JPanel
{
    private String snake = "images/head.png";
    private String body = "images/body.png";
    private int dx; 
    private int dy;
    private int x;
    private int y;
    private Image image;
    private Snake proximo;

    public Snake() {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(snake));
        this.image = ii.getImage();

        this.x = 40;
        this.y = 60;
        this.proximo = null;
    }

    public Snake(int i){
        ImageIcon ii = new ImageIcon(this.getClass().getResource(body));
        this.image = ii.getImage();

        this.x = 0;
        this.y = 0;
        this.proximo = null;

    }

    public Snake fabrica(){
        return new Snake(1);
    }

    public void move() {
        this.x += 1;
        this.y += 1;
    }

    public int getX() {
        return x;
    }

    public void setX(int x){
        this.x =x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y){
        this.y = y;
    }

    public Snake getProximo(){
        return this.proximo;
    }

    public void setProximo(Snake snake){
        this.proximo = snake;
    }

    public Image getImage() {
        return this.image;
    }

    public void setImage(String snake){
        ImageIcon ii = new ImageIcon(this.getClass().getResource(snake));
        this.image = ii.getImage();
    }

}
