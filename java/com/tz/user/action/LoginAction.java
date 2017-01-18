package com.tz.user.action;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tz.entity.User;
import com.tz.user.service.impl.UserServiceImpl;

@SuppressWarnings("serial")
@WebServlet(name = "LoginAction", urlPatterns = "/user/*")
public class LoginAction extends HttpServlet {
	ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
	UserServiceImpl userService=(UserServiceImpl) ac.getBean("userServiceImpl");

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session=request.getSession();
		String base = request.getContextPath();

		String pathInfo = request.getPathInfo();

		String nextPage = "";
		//POST方式提交参数后,获取参数如果含有中文信息,则需要设置请求字符集
		

		if ("/login".equals(pathInfo)) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String token = request.getParameter("token");
			String code = (String) getServletContext().getAttribute("token");
			
			String noLogin = request.getParameter("noLogin");
			Cookie cookie = null;
			if (noLogin != null) {
				//说明要七天免登陆
				cookie = new Cookie("userInfo", URLEncoder.encode(username + ":"
						+ password, "UTF-8"));
				cookie.setMaxAge(7 * 24 * 60 * 60);
				//设置Cookie作用域
				cookie.setPath("/");
				response.addCookie(cookie);
			}
			if (code.equals(token)) {
				User user = userService.login(username, password);
				if (user != null) {
					//把用户名放入到共享数据范围
					session.setAttribute("user", user);
					//设置Session的最长访问时间间隔
					session.setMaxInactiveInterval(60*15);
					//说明登陆成功
					nextPage = "/permission/emp/list";
				} else {
					//说明登陆失败
					nextPage = "/index.jsp?info=1";
				}
			} else {
				nextPage = "/index.jsp?info=2";
			}
		} else if ("/logout".equals(pathInfo)) {
			//从共享数据范围删除用户登陆信息
			//销毁Session
			if (session != null) {
				session.invalidate();
			}
			//设置跳转页面
			nextPage = "/index.jsp";
		} else {
			//发送错误码
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "暂时无法提供服务...");
			return;
		}
		//跳转页面--重定向
		response.sendRedirect(base + nextPage);

	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
