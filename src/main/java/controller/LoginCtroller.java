package controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import entity.Admin;
import service.ApplicationException;
import service.LoginService;
import utils.ImageUtil;

/**
 * 1.不用实现controller接口 2.可以在一个处理器类中,添加多个方法. 没一个方法处理一种类型的请求.
 * 3.方法名不做要求,返回类型可以是ModelAndView,也可以是String. 4.使用@Controller,将该处理器纳入容器进行管理 5.
 * 5,使用@RequestMapping,告诉前端控制器(DispatcherServlet),请求路径与处理器的关系(
 * spring配置文件不用配置HandlerMapping了)
 * 
 * @author Student
 *
 */
@Controller
public class LoginCtroller {
	@Resource(name = "loginService")
	private LoginService service;

	// 这是一个异常处理方法。
	// ex:是其它方法所抛出的异常。

	@ExceptionHandler
	public String exHandle(Exception ex, HttpServletRequest request) {
		System.out.println("exHandle()");
		if (ex instanceof ApplicationException) {
			// 应用异常
			request.setAttribute("error", ex.getMessage());
			return "login";
		}
		// 系统异常
		return "error";
	}

	@RequestMapping("/toLogin.do")
	public String toLogin() {
		System.out.println("toLogin()");

		return "login";// 视图名//login.jsp
	}

	@RequestMapping("/login.do")
	public String login(HttpServletRequest request, HttpSession session) {
		System.out.println("login()");
		String adminCode = request.getParameter("adminCode");
		String pwd = request.getParameter("password");
		System.out.println(adminCode + " " + pwd);
		String code = request.getParameter("code");// 接收验证码
		session = request.getSession();
		String imgcode = (String) session.getAttribute("imgcode");
		// 调用业务层服务
		Admin admin = service.checkLogin(adminCode, pwd);
		session.setAttribute("admin", admin);
		// 把admin对象绑订到session,
		// 用于session验证。
		// 检查验证码是否正确
		if (code == null || !code.equalsIgnoreCase(imgcode)) {
			request.setAttribute("error", "验证码错误");
			return "login";// 验证码错误就不再检查账号密码了
		}
		return "index";// 视图名//login.jsp

	}

	@RequestMapping("/createImg.do")
	// 生成验证码
	protected void createImg(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("createImg(");
		// 生成验证码及图片
		Object[] objs = ImageUtil.createImage();
		// 将验证码存入session
		HttpSession session = req.getSession();
		session.setAttribute("imgcode", objs[0]);
		// 将图片输出给浏览器
		res.setContentType("image/png");
		// 获取输出流，该流由服务器自动创建，他所输出的目标就是当前访问的浏览器
		OutputStream os = res.getOutputStream();
		// 获取图片
		BufferedImage img = (BufferedImage) objs[1];
		ImageIO.write(img, "png", os);// 图片 格式 流
		os.close();
	}

}