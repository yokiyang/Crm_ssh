package com.tz.user.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tz.entity.User;
import com.tz.user.service.IUserService;

@Controller
@RequestMapping("/user")
public class LoginController {
	@Resource
	private IUserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String username, String password, String token,
			String noLogin, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		String code = (String) request.getServletContext()
				.getAttribute("token");
		if ((username != null && username.length() != 0)
				&& (password != null && password.length() != 0)
				&& (token != null && token.length() != 0)) {
			Cookie cookie = null;
			if (noLogin != null) {
				//说明要七天免登陆
				cookie = new Cookie("userInfo", URLEncoder.encode(username
						+ ":" + password, "UTF-8"));
				cookie.setMaxAge(7 * 24 * 60 * 60);
				//设置Cookie作用域
				cookie.setPath("/");
				response.addCookie(cookie);
			}
			if (code.equals(token)) {
				User user = userService.login(username, password);
				if (user != null) {
					//把用户名放入到共享数据范围
					request.getSession().setAttribute("user", user);
					//设置Session的最长访问时间间隔
					request.getSession().setMaxInactiveInterval(60 * 15);
					//说明登陆成功
					return "redirect:/permission/emp/1/2/list.do";
				} else {
					//说明登陆失败
					return "redirect:/index.jsp?info=1";
				}
			} else {
				return "redirect:/index.jsp?info=2";
			}
		}else{
			return "redirect:/index.jsp?info=1";
		}
		
	}

	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		HttpSession session=request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		return "redirect:/index.jsp";
	}
}
