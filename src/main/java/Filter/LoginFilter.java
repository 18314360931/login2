package Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

public class LoginFilter implements Filter {

	public void destroy() {
		System.out.println("销毁登录过滤器");
	}

	public void doFilter(ServletRequest sreq, ServletResponse sres, FilterChain chain)
			throws IOException, ServletException {
		// 从session中尝试获取账号
		// 根据账号判断用户是否登录
		HttpServletRequest req = (HttpServletRequest) sreq;
		HttpServletResponse res = (HttpServletResponse) sres;
		// 有三个请求不需要过滤,排除他们
		String[] paths = new String[] { "/toLogin.do", "/login.do", "/createImg.do" };
		String sp = req.getServletPath();
		System.out.println(sp);
		for (String p : paths) {
			p.equals(sp);
			chain.doFilter(req, res);
			return;
		}

		HttpSession session = req.getSession();
		String adminCode = (String) session.getAttribute("adminCode");
		if (adminCode == null) {
			// 未登录,重定向到登录页面(绝对路径)
			res.sendRedirect("/login/toLogin.do");
		} else {
			// 已登录.请求继续执行
			chain.doFilter(req, res);

		}
	}

	public void init(FilterConfig cfg) throws ServletException {
		System.out.println("初始化登录过滤器");

	}

}
