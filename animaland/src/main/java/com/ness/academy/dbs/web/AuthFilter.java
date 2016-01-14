package com.ibm.itacademy.animaland.web;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/faces/*")
public class AuthFilter implements Filter {
	@Override
	public void destroy() {
		// no destroy
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpSR = (HttpServletRequest) request;

			String requestURI = httpSR.getRequestURI();

			if (httpSR.getRequestURI().endsWith("/animals.xhtml")) {
				HttpSession session = httpSR.getSession();
				// -----------------------
				LoginBean loginBean = (LoginBean) session
						.getAttribute("loginBean");
				// ----------------------
				if (loginBean == null || ! loginBean.isAuthenticated()) {
					redirectToLoginPage(httpSR, response);
					return;
				}

			}
			chain.doFilter(request, response);
		}

	}

	private void redirectToLoginPage(HttpServletRequest httpServletRequest,
			ServletResponse response) throws IOException {

		if (response instanceof HttpServletResponse) {
			String contextPath = httpServletRequest.getServletContext().getContextPath();
			
			HttpServletResponse httpServletResponse = (HttpServletResponse) response;
			httpServletResponse.sendRedirect(contextPath + "/faces/login.xhtml");
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		//

	}

}
