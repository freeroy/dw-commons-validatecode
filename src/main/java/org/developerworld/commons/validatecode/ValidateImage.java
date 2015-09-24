package org.developerworld.commons.validatecode;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import org.apache.commons.lang.math.RandomUtils;

/**
 * 验证图片类
 * @author Roy Huang
 * @version 20110111
 *
 */
public class ValidateImage {

	private int width,height;
	private String fontFamily="Arial, Helvetica, sans-serif";
	private int fontSize=14;
	
	public ValidateImage(int width,int height){
		this.width=width;
		this.height=height;
	}
	
	public void setFontFamily(String temp){
		this.fontFamily=temp;
	}
	
	public void setFontSize(int temp){
		this.fontSize=temp;
	}
	
	public void drawImage(Graphics g,String code){
		//设置字体属性
		Font font=new Font(this.fontFamily,Font.BOLD,this.fontSize);	
		g.setFont(font);
		int fWidth=this.width/code.length();
		//随机字
		for(int i=0;i<code.length();i++){
			String temp=code.substring(i,i+1);
			FontMetrics fm=getFontMetrics(font, temp);
			g.setColor(getRandomColor(10, 150));
			g.drawString(temp,fWidth*i+(fWidth-fm.stringWidth(temp))/2,(height-fm.getHeight())/2+fm.getAscent());
		}
	}
	
	/**
	 * 获取随机颜色
	 * @param begin
	 * @param end
	 * @return
	 */
	private Color getRandomColor(int begin, int end) {
		int temp = end - begin + 1;
		int red = begin + RandomUtils.nextInt(temp);
		int green = begin + RandomUtils.nextInt(temp);
		int blue = begin + RandomUtils.nextInt(temp);
		return new Color(red, green, blue);
	}
	
	/**
	 * 获取字体FontMetrics对象
	 * @param font
	 * @param str
	 * @return
	 */
	private FontMetrics getFontMetrics(Font font, String str) {
		FontMetrics fm = null;
		BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
		Graphics g = img.getGraphics();
		g.drawString(str, 0, 0);
		fm = g.getFontMetrics(font);
		g.dispose();
		return fm;
	}
}
