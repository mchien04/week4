package vn.iostar.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vn.iostar.configs.DBConnectMySQL;
import vn.iostar.dao.IUserDAO;
import vn.iostar.models.UserModel;

public class UserDaoImpl<ps> extends DBConnectMySQL implements IUserDAO {

	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;

	@Override
	public List<UserModel> findAll() {
		String sql = "select * from users";

		List<UserModel> list = new ArrayList<>(); // Tạo 1 List để truyền dữ liệu

		try {
			conn = super.getDatabaseConnection(); // kết nói database
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next() /* Next từng DÒNG tới cuối bàng */) {
				list.add(new UserModel(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
						rs.getString("email"), rs.getString("fullname"), rs.getString("images"), rs.getString("phone"),
						rs.getInt("roleid"), rs.getDate("createDate"))); // Add vào
			}
			return list;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public UserModel findByID(int id) {
		String sql = "SELECT * FROM users WHERE id = ?";
		UserModel user = null;

		try {
			conn = super.getDatabaseConnection();
			ps = conn.prepareStatement(sql);

			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				user = new UserModel(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
						rs.getString("email"), rs.getString("fullname"), rs.getString("images"), rs.getString("phone"),
						rs.getInt("roleid"), rs.getDate("createDate"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	@Override
	public void insert(UserModel user) {
		String sql = "INSERT INTO users(username, email, password, images, fullname, phone, roleid, createDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try {

			conn = super.getDatabaseConnection(); // kết nối database

			ps = conn.prepareStatement(sql);// ném câu sql vào cho thực thi

			ps.setString(1, user.getUsername());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getImages());
			ps.setString(5, user.getFullname());
			ps.setString(6, user.getPhone());
			ps.setInt(7, user.getRoleid());
			ps.setDate(8, user.getCreateDate());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public UserModel findByUserName(String username) {
		String sql = "SELECT * FROM users WHERE username = ?";

		try {
			conn = super.getDatabaseConnection();
			ps = conn.prepareStatement(sql);

			ps.setString(1, username);

			rs = ps.executeQuery();
			while (rs.next()) {
				UserModel user = new UserModel();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				user.setFullname(rs.getString("fullname"));
				user.setPassword(rs.getString("password"));
				user.setImages(rs.getString("images"));
				user.setRoleid(Integer.parseInt(rs.getString("roleid")));
				user.setPhone(rs.getString("phone"));
				user.setCreateDate(rs.getDate("createDate"));
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		UserDaoImpl<?> userDao = new UserDaoImpl<Object>();

		UserModel user = userDao.findByUserName("mchien");

		if (user != null) {
			System.out.println("User found: " + user);
		} else {
			System.out.println("User not found.");
		}
		/*
		 * UserDaoImpl<?> userDao = new UserDaoImpl<Object>();
		 * 
		 * UserModel user = userDao.findByID(4);
		 * 
		 * if (user != null) { System.out.println("User found: " + user); } else {
		 * System.out.println("User not found."); }
		 */

		/*
		 * UserDaoImpl userDao = new UserDaoImpl(); boolean registered =
		 * userDao.register(4, "newuser", "password123", "newuser@example.com",
		 * "New User", "newuser.jpg"); if (registered) {
		 * System.out.println("Đăng ký thành công."); } else {
		 * System.out.println("Tài khoản đã tồn tại."); }
		 */

		/*
		 * UserDaoImpl<?> userDao = new UserDaoImpl<Object>();
		 * 
		 * UserModel user = userDao.login(1, "123"); if (user != null) {
		 * System.out.println("Đăng nhập thành công: " + user); } else {
		 * System.out.println("Đăng nhập thất bại."); }
		 */

	}

	@Override
	public boolean checkExistEmail(String email) {
		boolean duplicate = false;
		String sql = "select * from users where email = ?";
		try {
			conn = super.getDatabaseConnection();
			ps = conn.prepareStatement(sql);

			ps.setString(1, email);

			rs = ps.executeQuery();
			if (rs.next()) {
				duplicate = true;
			}
			ps.close();
			conn.close();
		} catch (Exception ex) {
		}
		return duplicate;
	}

	@Override
	public boolean checkExistUsername(String username) {
		boolean duplicate = false;
		String sql = "select * from users where username = ?";
		try {
			conn = super.getDatabaseConnection();
			ps = conn.prepareStatement(sql);

			ps.setString(1, username);

			rs = ps.executeQuery();

			if (rs.next()) {
				duplicate = true;
			}
			ps.close();
			conn.close();
		} catch (Exception ex) {
		}
		return duplicate;
	}

	@Override
	public boolean checkExistPhone(String phone) {
		boolean duplicate = false;
		String sql = "select * from users where phone = ?";
		try {
			conn = super.getDatabaseConnection();
			ps = conn.prepareStatement(sql);

			ps.setString(1, phone);

			rs = ps.executeQuery();

			if (rs.next()) {
				duplicate = true;
			}
			ps.close();
			conn.close();
		} catch (Exception ex) {
		}
		return duplicate;
	}

}
