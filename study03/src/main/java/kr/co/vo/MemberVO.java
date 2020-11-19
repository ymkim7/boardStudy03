package kr.co.vo;

import java.util.Date;

public class MemberVO {
	
	private String userId;
	private String userPw;
	private String userName;
	private String userEmail;
	private Date userRegdate;
	private Date userUpdatedate;
	
	public MemberVO() {}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Date getUserRegdate() {
		return userRegdate;
	}

	public void setUserRegdate(Date userRegdate) {
		this.userRegdate = userRegdate;
	}

	public Date getUserUpdatedate() {
		return userUpdatedate;
	}

	public void setUserUpdatedate(Date userUpdatedate) {
		this.userUpdatedate = userUpdatedate;
	}

	public MemberVO(String userId, String userPw, String userName, String userEmail, Date userRegdate,
			Date userUpdatedate) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userRegdate = userRegdate;
		this.userUpdatedate = userUpdatedate;
	}

	@Override
	public String toString() {
		return "MemberVO [userId=" + userId + ", userPw=" + userPw + ", userName=" + userName + ", userEmail="
				+ userEmail + ", userRegdate=" + userRegdate + ", userUpdatedate=" + userUpdatedate + ", toString()="
				+ super.toString() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userEmail == null) ? 0 : userEmail.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + ((userPw == null) ? 0 : userPw.hashCode());
		result = prime * result + ((userRegdate == null) ? 0 : userRegdate.hashCode());
		result = prime * result + ((userUpdatedate == null) ? 0 : userUpdatedate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemberVO other = (MemberVO) obj;
		if (userEmail == null) {
			if (other.userEmail != null)
				return false;
		} else if (!userEmail.equals(other.userEmail))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (userPw == null) {
			if (other.userPw != null)
				return false;
		} else if (!userPw.equals(other.userPw))
			return false;
		if (userRegdate == null) {
			if (other.userRegdate != null)
				return false;
		} else if (!userRegdate.equals(other.userRegdate))
			return false;
		if (userUpdatedate == null) {
			if (other.userUpdatedate != null)
				return false;
		} else if (!userUpdatedate.equals(other.userUpdatedate))
			return false;
		return true;
	}

}
