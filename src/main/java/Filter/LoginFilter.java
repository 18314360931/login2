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
		System.out.println("���ٵ�¼������");
	}

	public void doFilter(ServletRequest sreq, ServletResponse sres, FilterChain chain)
			throws IOException, ServletException {
		// ��session�г��Ի�ȡ�˺�
		// �����˺��ж��û��Ƿ��¼
		HttpServletRequest req = (HttpServletRequest) sreq;
		HttpServletResponse res = (HttpServletResponse) sres;
		// ������������Ҫ����,�ų�����
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
			// δ��¼,�ض��򵽵�¼ҳ��(����·��)
			res.sendRedirect("/login/toLogin.do");
		} else {
			// �ѵ�¼.�������ִ��
			chain.doFilter(req, res);

		}
	}

	public void init(FilterConfig cfg) throws ServletException {
		System.out.println("��ʼ����¼������");

	}

}
