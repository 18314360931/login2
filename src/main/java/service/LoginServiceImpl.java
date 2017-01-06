package service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import dao.AdminDao;
import entity.Admin;

/**
 * 业务层实现
 *
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {

	@Resource(name = "adminDao")
	private AdminDao dao;

	public Admin checkLogin(String code, String pwd) {
		Admin admin = null;
		admin = dao.findByCode(code);
		
		if (admin == null) {
			// 帐号不存在
			// 抛出一个应用异常(
			// 用户操作引起的异常)。
			throw new ApplicationException("帐号不存在");
		}
		if (!admin.getPassword().equals(pwd)) {
			// 密码错误
			throw new ApplicationException("密码错误");
		}
		// 登录成功
		return admin;
	}

}
