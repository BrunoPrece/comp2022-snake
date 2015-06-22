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
    String dirAnterior = "baixo";
    private Lista lista = new Lista();
    private boolean isPlaying = true;
    //     static boolean loser = false;
    private Food fries;
    private Font font;

    public Board() {

        addKeyListener(new TAdapter());

        setFocusable(true);        
        setDoubleBuffered(true);
        setBackground(Color.WHITE);

        score = new Score();
        add(score);
        fries = new Food();
        add(fries);
        Snake aux = lista.getInicio();
        while(aux.getProximo() != null){
            add(aux);
            aux = aux.getProximo();
        }

        timer = new Timer(5, this);
        timer.start();
    }

    public void paint(Graphics g) {
        super.paint(g);

        score.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g;
        //adicionar a comida e fazer um método
        verificar();
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
            g2d.drawImage(fries.getImage(), fries.getX(), fries.getY(), this);
        }else{
            g2d.drawString("GAME OVER",250,200);
            g2d.drawString("Press ENTER Try Again!",250,250);
            direcao = "baixo";
            dirAnterior = "baixo";
            score = new Score();
        }
        Toolkit.getDefaultToolkit().sync();
        g.dispose();

    }

    public void actionPerformed(ActionEvent e) {
        lista.imageDirection(direcao);

        if(isPlaying == true){

            if((dirAnterior.equals("esquerda"))&&(direcao.equals("direita"))){
                direcao = "esquerda";
            }else if((dirAnterior.equals("direita"))&&(direcao.equals("esquerda"))){
                direcao = "direita";
            }else if ((dirAnterior.equals("cima")&&(direcao.equals("baixo")))||
            ((dirAnterior.equals("baixo"))&&(direcao.equals("cima")))){
                direcao = dirAnterior;
            }

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
            dirAnterior = direcao;
            repaint();
        }

    }

    public void adicionar(){

        switch(direcao){
            case "esquerda":
            lista.adiciona(1,0);
            break;

            case "direita":
            lista.adiciona(-1,0);
            break;

            case "cima":
            lista.adiciona(0,1);
            break;

            case "baixo":
            lista.adiciona(0,-1);
            break;
        }
    }

    public void verificar(){
        Snake inicio = lista.getInicio();
        Snake aux = lista.getInicio();

        if(((lista.getInicio().getX()<= fries.getX()+10)&&(lista.getInicio().getX()>= fries.getX()-10))&&
        (lista.getInicio().getY()<= fries.getY()+15)&&(lista.getInicio().getY()>= fries.getY()-15)){
            for(int i = 0; i<20;i++){
                adicionar();
            }
            fries.setX();
            fries.setY();
            score.addScore(10);
        }

        while(aux.getProximo() != null){
            aux = aux.getProximo();
            if((inicio.getX() == aux.getX())&&(inicio.getY() == aux.getY())){
                isPlaying = false;
                lista.zerar();
            }
        }

        if(((inicio.getX()>=795)||(inicio.getX()==0))||(inicio.getY()>=595)||(inicio.getY() == 0)){
            isPlaying = false;
            lista.zerar();
        }

    }
    private class TAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            // Obtém o código da tecla
            int key =  e.getKeyCode();

            switch (key){
                case KeyEvent.VK_ENTER:
                isPlaying = true;
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