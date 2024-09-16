package vn.iostar.services.impl;

import vn.iostar.dao.IUserDAO;
import vn.iostar.dao.impl.UserDaoImpl;
import vn.iostar.models.UserModel;
import vn.iostar.services.IUserService;

public class UserService implements IUserService{

	IUserDAO userDao = new UserDaoImpl();
	
	@Override
	public UserModel login(String username, String password) {
		UserModel user = this.FindByUserName(username);
		 
		if (user != null && password.equals(user.getPassword())) {
			 return user;
		 }
		 return null;
	}

	@Override
	public UserModel FindByUserName(String username) {
		return userDao.findByUserName(username);
	}

	public static void main(String[] args){
		IUserService userService = new UserService();
		System.out.println(userService.FindByUserName("mchien"));
	}
	

	@Override
	public void insert(UserModel user) {
		userDao.insert(user);
	}


	@Override
	public boolean checkExistEmail(String email) {
		return userDao.checkExistEmail(email);
	}

	@Override
	public boolean checkExistUsername(String username) {
		return userDao.checkExistUsername(username);
	}

	@Override
	public boolean checkExistPhone(String phone) {
		return userDao.checkExistPhone(phone);
	}

	@Override
	public boolean register(String email, String password, String username, String fullname, String phone) {
		return false;

	}



}
