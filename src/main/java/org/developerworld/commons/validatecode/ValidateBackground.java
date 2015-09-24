package org.developerworld.commons.validatecode;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import org.apache.commons.lang.math.RandomUtils;

/**
 * 背景渲染类
 * 
 * @author Roy Huang
 * @version 20110111
 * 
 */
public class ValidateBackground {

	public final static int LINE_BACKGROUND = 1;

	private Color GColor = Color.LIGHT_GRAY;
	private Color BGColor = Color.WHITE;
	private int type = LINE_BACKGROUND;
	private int disturbCount = 20;
	private int width, height;

	public ValidateBackground(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public void setType(int temp) {
		this.type = temp;
	}

	public void setDisturbCount(int temp) {
		disturbCount = temp;
	}

	public void setBGColor(Color temp) {
		BGColor = temp;
	}

	public void drawBackground(Graphics g) {
		g.setColor(BGColor);
		g.fillRect(0, 0, width, height);
		g.setColor(GColor);
		switch (type) {
		case LINE_BACKGROUND:
			createLineBackground(g);
			break;
		default:
			createLineBackground(g);
		}
	}

	/**
	 * 利用画笔画干扰线
	 * 
	 * @param bi
	 */
	private void createLineBackground(Graphics g) {
		for (int i = 0; i < disturbCount; i++) {
			Point p[] = { getRandomPoint(0, width, 0, height),
					getRandomPoint(0, width, 0, height) };
			g.drawLine((int) p[0].getX(), (int) p[0].getY(), (int) p[1].getX(),
					(int) p[1].getY());
		}
	}

	/**
	 * 获取随机点
	 * 
	 * @param xBegin
	 * @param xEnd
	 * @param yBegin
	 * @param yEnd
	 * @return
	 */
	private Point getRandomPoint(int xBegin, int xEnd, int yBegin, int yEnd) {
		int xTemp = xEnd - xBegin + 1;
		int yTemp = yEnd - yBegin + 1;
		return new Point(RandomUtils.nextInt(xTemp), RandomUtils.nextInt(yTemp));
	}
}
