package org.developerworld.commons.validatecode;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 
 * @author Roy
 * 
 */
public class HttpValidateCodeBuilder {

	private String contentType = "image/jpeg";
	private String outputImageType = "JPEG";
	private int codeCount = 4;
	private int backgroundType = ValidateBackground.LINE_BACKGROUND;
	private int width = 100;
	private int height = 50;
	private String codeType = ValidateCode.CODE_TYPE_NUMBER;
	private ValidateCode validateCode;
	private ValidateBackground validateBackground;
	private ValidateImage validateImage;

	public void setOutputImageType(String outputImageType) {
		this.outputImageType = outputImageType;
	}

	public void setCodeCount(int codeCount) {
		this.codeCount = codeCount;
		validateCode = null;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
		validateCode = null;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setBackgroundType(int backgroundType) {
		this.backgroundType = backgroundType;
		validateBackground = null;
	}

	public void setWidth(int width) {
		this.width = width;
		validateImage = null;
		validateBackground = null;
	}

	public void setHeight(int height) {
		this.height = height;
		validateImage = null;
		validateBackground = null;
	}

	/**
	 * 初始化检查
	 */
	private void init() {
		if (validateCode == null)
			validateCode = new ValidateCode(codeType, codeCount);
		if (validateImage == null)
			validateImage = new ValidateImage(width, height);
		if (validateBackground == null) {
			validateBackground = new ValidateBackground(width, height);
			validateBackground.setType(backgroundType);
		}
	}

	/**
	 * 执行验证码生成
	 * 
	 * @param request
	 * @param response
	 * @param sessionAttribute
	 * @throws IOException
	 */
	public void build(HttpServletRequest request, HttpServletResponse response,
			String sessionAttribute) throws IOException {
		// 初始化
		init();
		// 执行程序
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType(contentType);
		// 获取随机字符
		String code = validateCode.getRandomCode();
		// 创建基础图片和画笔对象
		BufferedImage bi = null;
		Graphics g = null;
		bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
		g = bi.getGraphics();
		// 获取干扰背景并画出来
		validateBackground.drawBackground(g);
		// 获取图片并画出来
		validateImage.drawImage(g, code);
		request.getSession().setAttribute(sessionAttribute, code);// 记录随机码
		ImageIO.write(bi, outputImageType, response.getOutputStream());// 输出图象
		// JPEGImageEncoder encoder =
		// JPEGCodec.createJPEGEncoder(response.getOutputStream());
		// encoder.encode(image);
		g.dispose();
		bi.flush();
	}

}
