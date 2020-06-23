package com.itranswarp.learnjava.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

@Component
public class UserService {

	@Autowired
	JdbcTemplate jdbcTemplate;
//以下是通过jdbctemplate实现的增删改查


	//根据id查询用户
	public User getUserById(long id) {
		return jdbcTemplate.execute((Connection conn) -> {
			try (var ps = conn.prepareStatement("SELECT * FROM users WHERE id = ?")) {
				ps.setObject(1, id);
				try (var rs = ps.executeQuery()) {
					if (rs.next()) {
						return new User( // new User object:
								rs.getLong("id"), // id
								rs.getString("email"), // email
								rs.getString("password"), // password
								rs.getString("name")); // name
					}
					throw new RuntimeException("user not found by id.");
				}
			}
		});
	}

	//根据name查询用户
	public User getUserByName(String name) {
		return jdbcTemplate.execute("SELECT * FROM users WHERE name = ?", (PreparedStatement ps) -> {
			ps.setObject(1, name);
			try (var rs = ps.executeQuery()) {
				if (rs.next()) {
					return new User( // new User object:
							rs.getLong("id"), // id
							rs.getString("email"), // email
							rs.getString("password"), // password
							rs.getString("name")); // name
				}
				throw new RuntimeException("user not found by id.");
			}
		});
	}


	//根据email查询用户
	public User getUserByEmail(String email) {
		return jdbcTemplate.queryForObject("SELECT * FROM users WHERE email = ?", new Object[]{email},
				(ResultSet rs, int rowNum) -> {
					return new User( // new User object:
							rs.getLong("id"), // id
							rs.getString("email"), // email
							rs.getString("password"), // password
							rs.getString("name")); // name
				});
	}


	public long getUsers() {
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users", null, (ResultSet rs, int rowNum) -> {
			return rs.getLong(1);
		});
	}

	public List<User> getUsers(int pageIndex) {
		int limit = 100;
		int offset = limit * (pageIndex - 1);
		return jdbcTemplate.query("SELECT * FROM users LIMIT ? OFFSET ?", new Object[]{limit, offset},
				new BeanPropertyRowMapper<>(User.class));
	}

	//判断是否存在该账号而进行登录
	public User login(String email, String password) {
		User user = getUserByEmail(email);
		if (user.getPassword().equals(password)) {
			System.out.println("登录成功");
			return user;
		}
		throw new RuntimeException("login failed.没有当前账户");
	}

	//添加对象
	public User register(String email, String password, String name) {
		KeyHolder holder = new GeneratedKeyHolder();
		if (1 != jdbcTemplate.update((conn) -> {
			var ps = conn.prepareStatement("INSERT INTO users(email,password,name) VALUES(?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setObject(1, email);
			ps.setObject(2, password);
			ps.setObject(3, name);
			return ps;
		}, holder)) {
			throw new RuntimeException("Insert failed.");
		}
		return new User(holder.getKey().longValue(), email, password, name);
	}

//	根据名字删除对象
	public void updateUser(String name) {
//		if (1 != jdbcTemplate.update("UPDATE user SET name = ? WHERE id=?", user.getName(), user.getId())) {
//			throw new RuntimeException("User not found by id");
//		}
		jdbcTemplate.update("DELETE FROM users WHERE name=?", name);
//		String sql = "DELETE FROM users WHERE name=?";
//		 jdbcTemplate.update(sql,name);
	}
//userService是专门提供数据库操作服务的类,user是建表和对象基本信息操作的类。


//	public void searchUser(String name) {
////		jdbcTemplate.execute("SELECT * from users");
////	}
//		String sql = "SELECT id FROM users WHERE name=?;";
//		jdbcTemplate.execute(sql);
//	}

	//根据id修改对象
	public void updates(String name,String email,long id){
		String sql = "UPDATE users SET name=?, email=? WHERE id=?";
		int i = jdbcTemplate.update(sql, name,email,id);
		System.out.println("影响的行数: " + i + "修改成功");
	}









}