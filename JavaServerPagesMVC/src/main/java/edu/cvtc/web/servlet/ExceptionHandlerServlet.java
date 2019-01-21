package edu.cvtc.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ExceptionHandlerServlet
 */
@WebServlet("/ExceptionHandlerServlet")
public class ExceptionHandlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
		final Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		
		String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
		if (servletName == null) {
			servletName = "Unknown";
		}
		
		String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
		if (requestUri == null) {
			requestUri = "Unknown";
		}
		
		response.setContentType("text/html");
		
		final PrintWriter out = response.getWriter();
		out.write("<html><head><title>Exception/Error Details</title></head><body>");
		
		if (statusCode != 500) {
			out.write("<h3>Error Details</h3>");
			out.write("<strong>Status Code</strong>: " + statusCode + "<br>");
			out.write("<strong>Requested URI</strong>: " + requestUri);
		} else {
			out.write("<h3>Exception Details</h3>");
			out.write("<ul><li>Requested URI</strong>: " + requestUri + "</li>");
			out.write("<li>Servlet Name: " + servletName + "</li>");
			out.write("<li>Exception Name: " + throwable.getClass().getName() + "</li>");
			out.write("<li>Exception Message: " + throwable.getMessage() + "</li>");
			out.write("</ul>");
		}
		
		out.write("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
