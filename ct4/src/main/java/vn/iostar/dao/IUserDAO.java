package vn.iostar.dao;

import java.util.List;

import vn.iostar.models.UserModel;

public interface IUserDAO {
	
	List<UserModel> findAll();
	
	UserModel findByID(int id);
	
	void insert(UserModel user); 
	
	UserModel findByUserName(String username);

	boolean checkExistEmail(String email);
	
	boolean checkExistUsername(String username);
	
	boolean checkExistPhone(String phone);

}
