package code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends JPanel implements ActionListener {

    private final int B_WIDTH = 300;
    private final int B_HEIGHT = 300;
    private final int DOT_SIZE = 10;
    private final int ALL_DOTS = 900;
    private final int RAND_POS = 29;
    private final int DELAY = 140;

    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];

    private int dots;
    private int apple_x;
    private int apple_y;

    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;

    private Timer timer;
    private Image ball;
    private Image apple;
    private Image head;

    public Board(){
    }

    private void initBoard(){

        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        loadImage();
        initGame();
    }

    private void loadImage(){

        ImageIcon iia = new ImageIcon("src/resources/apple.png");
        apple = iia.getImage();

        ImageIcon iib = new ImageIcon("src/resources/dot.png");
        ball = iib.getImage();

        ImageIcon iih = new ImageIcon("src/resources/head.png");
        head = iih.getImage();
    }

    private void initGame(){

        dots = 3;

        for(int z = 0; z < dots; z++){
            x[z] = 50 - z * 10;
            y[z] = 50;
        }

        locateApple();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    private void checkCollision(){

        for(int z = dots; z > 0; z--){

            if((z > 4) && (x[0] == x[z]) && (y[0] == y[z])){
                inGame = false;
            }
        }

        if (y[0] >= B_HEIGHT) {
            inGame = false;
        }

        if (y[0] < 0) {
            inGame = false;
        }

        if (x[0] >= B_WIDTH) {
            inGame = false;
        }

        if (x[0] < 0) {
            inGame = false;
        }

        if (!inGame) {
            timer.stop();
        }
    }



    private void locateApple(){

        int r = (int) (Math.random() * RAND_POS);
        apple_x = r * DOT_SIZE;

        r = (int) (Math.random() * RAND_POS);
        apple_y = r * DOT_SIZE;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
    }

    private class TAdapter extends KeyAdapter{

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_UP) && (!downDirection)) {
                upDirection = true;
                leftDirection = false;
                rightDirection = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
                downDirection = true;
                leftDirection = false;
                rightDirection = false;
            }
        }
    }
}
