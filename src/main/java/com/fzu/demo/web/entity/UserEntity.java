package com.fzu.demo.web.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author zzx
 */
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String username;

	private String nickname;

	private String sex;

	private Date birthday;

	private String password;

	private byte[] photo;

	private List<GameEntity> games;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public List<GameEntity> getGames() {
		return games;
	}

	public void setGames(List<GameEntity> games) {
		this.games = games;
	}

	public UserEntity() {
	}

	public UserEntity(Integer id, String username, String nickname, String sex, Date birthday, String password, byte[] photo) {
		this.id = id;
		this.username = username;
		this.nickname = nickname;
		this.sex = sex;
		this.birthday = birthday;
		this.password = password;
		this.photo = photo;
	}

	public UserEntity(String username, String nickname, String sex, String password) {
		this.username = username;
		this.nickname = nickname;
		this.sex = sex;
		this.password = password;
	}
}