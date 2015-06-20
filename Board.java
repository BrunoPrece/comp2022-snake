import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.io.File;

public class Board extends JPanel implements ActionListener {

    private Timer timer;
    private Score score;
    private Snake snake;
    static String direcao = "baixo";
    private Lista lista = new Lista();
    private boolean isPlaying = false;

    private Font font;

    public Board() {

        addKeyListener(new TAdapter());

        setFocusable(true);        
        setDoubleBuffered(true);
        setBackground(Color.WHITE);

        score = new Score();
        add(score); 
        Snake aux = lista.getInicio();
        while(aux.getProximo() != null){
            add(aux);
            aux = aux.getProximo();
        }
        isPlaying = true;
        timer = new Timer(5, this);
        timer.start();
    }

    public void paint(Graphics g) {
        super.paint(g);

        score.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g;
        //adicionar a comida e fazer um método
        if(isPlaying){

            Snake aux = lista.getInicio();
            if(aux.getProximo() == null){
                g2d.drawImage(aux.getImage(),aux.getX(), aux.getY(),this);
            }else{
                while(aux.getProximo()!= null){
                    g2d.drawImage(aux.getImage(),aux.getX(), aux.getY(),this);
                    aux = aux.getProximo();
                }
            }
        }else{
            //         gameover
        }
        Toolkit.getDefaultToolkit().sync();
        g.dispose();

    }

    //     public void paintIntro(Graphics g) {
    //         if(isPlaying){
    //             isPlaying = false;
    // 
    //             Graphics2D g2d = (Graphics2D) g;
    //             try{
    //                 File file = new File("fonts/VT323-Regular.ttf");
    //                 font = Font.createFont(Font.TRUETYPE_FONT, file);
    //                 GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    //                 ge.registerFont(font);
    //                 font = font.deriveFont(Font.PLAIN,40);
    //                 g2d.setFont(font);
    //             }catch (Exception e){
    //                 System.out.println(e.toString());
    //             }   
    //             g2d.drawString("S N A K E: " + this.score, 300, 300);
    //         }
    //     }

    public void actionPerformed(ActionEvent e) {
        lista.imageDirection(direcao);
        switch(direcao){
            case "esquerda":
            lista.moveCb(-1,0);
            break;

            case "direita":
            lista.moveCb(1,0);
            break;

            case "cima":
            lista.moveCb(0,-1);
            break;

            case "baixo":
            lista.moveCb(0,1);
            break;

        }
        repaint();  
    }

    public void adicionar(){
        switch(direcao){
            
            case "esquerda":
            lista.adiciona(20,0);
            break;

            case "direita":
            lista.adiciona(-20,0);
            break;

            case "cima":
            lista.adiciona(0,20);
            break;

            case "baixo":
            lista.adiciona(0,-20);
            break;
        }
    }
    
    private class TAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            // Obtém o código da tecla
            int key =  e.getKeyCode();

            switch (key){
                case KeyEvent.VK_ENTER:
                adicionar();
                break;

                case KeyEvent.VK_LEFT:
                direcao = "esquerda";
                break;

                case KeyEvent.VK_RIGHT:
                direcao = "direita";
                break;

                case KeyEvent.VK_UP:
                direcao = "cima";
                break;

                case KeyEvent.VK_DOWN:
                direcao = "baixo";
                break;
            }

        }
    }

}