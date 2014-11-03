package edu.nju.treasuryArbitrage.logic.liveUpdate;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.AbstractButton;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalButtonUI;

public class MyButtonUI extends MetalButtonUI{

    int rectGapX;
    int rectGapY;
    int rectGapW;
    int rectGapH;

    public MyButtonUI() {
        rectGapX = UIManager.getInt("ButtonUI.dashedRectGapX");
        rectGapY = UIManager.getInt("ButtonUI.dashedRectGapY");
        rectGapW = UIManager.getInt("ButtonUI.dashedRectGapWidth");
        rectGapH = UIManager.getInt("ButtonUI.dashedRectGapHeight");
    }

    protected void paintFocus(Graphics g, AbstractButton b, Rectangle viewRect, 
  Rectangle textRect, Rectangle iconRect) {

        textRect = new Rectangle(rectGapX+3, rectGapY+3, b.getWidth()-rectGapW-6,    
                                 b.getHeight()-rectGapH-6);
        super.paintFocus(g, b, viewRect, textRect, iconRect);
    }
}