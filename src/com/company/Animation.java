package com.company;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Animation extends JPanel implements ActionListener {

    private Image backBuffer;
    private Graphics2D screenGraphics;
    private Graphics2D bufferGraphics;
    private final Timer timer;
    private final int delay = 16;
    private final List<Figura> figures;

    public Animation(int initialWidth, int initialHeight) {
        super();
        this.setBackground(Color.WHITE);
        this.setOpaque(true);
        this.setPreferredSize(new Dimension(initialWidth, initialHeight));
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                createGraphicsContext(e.getComponent().getWidth(), e.getComponent().getHeight());
            }
        });

        figures = new ArrayList<>();
        timer = new Timer(delay, this);
    }

    public void addFigure(Class<? extends Figura> figureClass) {

        Figura figureToDraw = null;
        try {
            figureToDraw = figureClass.getDeclaredConstructor(Graphics2D.class, int.class, int.class, int.class)
                    .newInstance(bufferGraphics, delay, getWidth(), getHeight());
        } catch (Exception e) {
            e.printStackTrace();
        }

        figures.add(figureToDraw);
        figureToDraw.run();
    }

    public void deleteFigure(){
        if(figures.size() > 0) figures.remove(figures.size()-1);
    }

    public void toggleAnimation() {
        if (timer.isRunning()) {
            timer.stop();
        } else {
            timer.start();
        }
    }

    @Override
    public void setSize(int newWidth, int newHeight){
        super.setSize(newWidth, newHeight);
        this.createGraphicsContext(newWidth, newHeight);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (var figure : figures){
            figure.nextFrame(delay * 0.001);
            bufferGraphics.setColor(figure.getColor());
            bufferGraphics.fill(figure.getArea());
            bufferGraphics.draw(figure.getArea());
        }


        screenGraphics.drawImage(backBuffer, 0, 0, null);
        bufferGraphics.clearRect(0, 0,
                backBuffer.getWidth(null),
                backBuffer.getHeight(null));
    }

    private void applyRenderingHints() {
        bufferGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        screenGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    public void createGraphicsContext(int width, int height){
        backBuffer = createImage(width, height);
        bufferGraphics = (Graphics2D) backBuffer.getGraphics();
        screenGraphics = (Graphics2D) getGraphics();
        applyRenderingHints();
    }

}
