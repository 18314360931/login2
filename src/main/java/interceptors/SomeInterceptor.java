package interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class SomeInterceptor implements HandlerInterceptor {

	/**
	 * 最后执行的方法。 注意：只有当preHandle方法返回值为true 时，该方法才会执行。 ex:是处理器所抛出的异常，可以写一个
	 * 拦截器，用来处理这些异常。
	 */
	public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object arg2, Exception ex)
			throws Exception {
		System.out.println("afterCompletion()");
	}

	/**
	 * 处理器(Controller)的方法已经执行 完毕，正准备将处理结果(ModelAndView 对象)返回给DispatcherServlet之前
	 * 执行postHandle方法。可以在该方法里面， 修改处理结果。
	 */
	public void postHandle(HttpServletRequest req, HttpServletResponse res, Object arg2, ModelAndView mav)
			throws Exception {
		System.out.println("postHandle()");
	}

	/**
	 * DispatcherServlet收到请求之后，会 先调用preHandle方法。如果该方法的 返回值是true,则继续向后调用；如果
	 * 返回值是false,则不再向后调用。 arg2: 描述处理器方法的一个对象。
	 */
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object arg2) throws Exception {
		System.out.println("preHandle()");
		return true;
	}

}
