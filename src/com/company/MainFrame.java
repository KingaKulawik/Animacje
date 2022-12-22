package com.company;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private Animation animationPanel;
    private final JPanel buttonsPanel;
    private final JButton addShapeButton;
    private final JButton deleteShapeButton;
    private final JButton toggleAnimationButton;
    private final JRadioButton squareRadioButton;
    private final JRadioButton ellipseRadioButton;
    private final JRadioButton triangleRadioButton;
    private Class<? extends Figura> selectedFigure;

    public MainFrame(int minWidth, int minHeight) {
        super("Animacje");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setMinimumSize(new Dimension(minWidth, minHeight));
        this.setSize(minWidth, minHeight);

        buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        addShapeButton = new JButton("Dodaj nastepna figure");
        deleteShapeButton = new JButton("Usun figure");
        toggleAnimationButton = new JButton("Animacja");
        animationPanel = new Animation(minWidth, minHeight);
        squareRadioButton = new JRadioButton("Kwadrat");
        ellipseRadioButton = new JRadioButton("Kolko");
        triangleRadioButton = new JRadioButton("Trojkat");

        this.initializeComponents();
    }

    private void initializeComponents(){
        squareRadioButton.addActionListener((e) -> {
            selectedFigure = Kwadrat.class;
            ellipseRadioButton.setSelected(false);
            triangleRadioButton.setSelected(false);
        });
        ellipseRadioButton.addActionListener((e) -> {
            selectedFigure = Kolko.class;
            squareRadioButton.setSelected(false);
            triangleRadioButton.setSelected(false);
        });
        triangleRadioButton.addActionListener((e) -> {
            selectedFigure = Trojkat.class;
            ellipseRadioButton.setSelected(false);
            squareRadioButton.setSelected(false);
        });



        buttonsPanel.add(addShapeButton);
        buttonsPanel.add(deleteShapeButton);
        buttonsPanel.add(toggleAnimationButton);
        buttonsPanel.add(squareRadioButton);
        buttonsPanel.add(ellipseRadioButton);
        buttonsPanel.add(triangleRadioButton);

        squareRadioButton.doClick();

        addShapeButton.addActionListener((event) -> animationPanel.addFigure(selectedFigure));
        deleteShapeButton.addActionListener((event) -> animationPanel.deleteFigure());
        toggleAnimationButton.addActionListener((event) -> animationPanel.toggleAnimation());

        this.add(buttonsPanel, BorderLayout.SOUTH);
        this.add(animationPanel, BorderLayout.CENTER);
        this.pack();
    }


}
