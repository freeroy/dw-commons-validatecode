package org.developerworld.commons.validatecode.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.developerworld.commons.validatecode.HttpValidateCodeBuilder;
import org.developerworld.commons.validatecode.ValidateCode;

/**
 * Servlet类，用于部署到Web应用
 * 
 * @author Roy Huang
 * @version 20111019
 * 
 */
public class ValidateCodeServlet extends HttpServlet {

	private String sessionKey;
	private HttpValidateCodeBuilder builder;

	private static Log logger = LogFactory.getLog(ValidateCode.class);

	public ValidateCodeServlet() {
		super();
		logger.info(getClass() + " new!");
	}

	@Override
	public void init() throws ServletException {
		builder = new HttpValidateCodeBuilder();
		super.init();
		if (getServletConfig().getInitParameter("sessionKey") != null)
			sessionKey = getServletConfig().getInitParameter("sessionKey");
		else
			sessionKey = getServletConfig().getServletName();
		if (getServletConfig().getInitParameter("outputImageType") != null)
			builder.setOutputImageType(getServletConfig().getInitParameter(
					"outputImageType"));
		if (getServletConfig().getInitParameter("codeCount") != null)
			builder.setCodeCount(Integer.parseInt(getServletConfig()
					.getInitParameter("codeCount")));
		if (getServletConfig().getInitParameter("width") != null)
			builder.setWidth(Integer.parseInt(getServletConfig()
					.getInitParameter("width")));
		if (getServletConfig().getInitParameter("height") != null)
			builder.setHeight(Integer.parseInt(getServletConfig()
					.getInitParameter("height")));
		if (getServletConfig().getInitParameter("codeType") != null)
			builder.setCodeType(getServletConfig().getInitParameter("codeType"));
		if (getServletConfig().getInitParameter("backgroundType") != null)
			builder.setBackgroundType(Integer.parseInt(getServletConfig()
					.getInitParameter("backgroundType")));
		if (getServletConfig().getInitParameter("contentType") != null)
			builder.setContentType(getServletConfig().getInitParameter(
					"contentType"));
		logger.info("The " + getClass() + " init!");
	}

	@Override
	public void destroy() {
		builder = null;
		super.destroy();
		logger.info("The " + getClass() + " destroy!");
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		builder.build(request, response, sessionKey);
	}
}
