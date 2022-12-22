package com.company;

import java.awt.*;
import java.awt.geom.*;

public class Kolko extends Figura {

    public Kolko(Graphics2D buffer, int delay, int width, int height) {
        super(buffer, delay, width, height);
        setShape(new Ellipse2D.Double(10,10, 30, 30));
        setArea(new Area(getShape()));
    }

}