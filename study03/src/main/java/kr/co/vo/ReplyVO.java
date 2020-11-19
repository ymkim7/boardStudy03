package kr.co.vo;

import java.util.Date;

public class ReplyVO {
	
	private Integer rno;
	private Integer bno;
	private String replytext;
	private String replyer;
	private String userName;
	private Date regdate;
	private Date updatedate;
	private String secretReply;
	private String writer;
	
	public ReplyVO() {}

	public Integer getRno() {
		return rno;
	}

	public void setRno(Integer rno) {
		this.rno = rno;
	}

	public Integer getBno() {
		return bno;
	}

	public void setBno(Integer bno) {
		this.bno = bno;
	}

	public String getReplytext() {
		return replytext;
	}

	public void setReplytext(String replytext) {
		this.replytext = replytext;
	}

	public String getReplyer() {
		return replyer;
	}

	public void setReplyer(String replyer) {
		this.replyer = replyer;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}

	public String getSecretReply() {
		return secretReply;
	}

	public void setSecretReply(String secretReply) {
		this.secretReply = secretReply;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public ReplyVO(Integer rno, Integer bno, String replytext, String replyer, String userName, Date regdate,
			Date updatedate, String secretReply, String writer) {
		super();
		this.rno = rno;
		this.bno = bno;
		this.replytext = replytext;
		this.replyer = replyer;
		this.userName = userName;
		this.regdate = regdate;
		this.updatedate = updatedate;
		this.secretReply = secretReply;
		this.writer = writer;
	}

	@Override
	public String toString() {
		return "ReplyVO [rno=" + rno + ", bno=" + bno + ", replytext=" + replytext + ", replyer=" + replyer
				+ ", userName=" + userName + ", regdate=" + regdate + ", updatedate=" + updatedate + ", secretReply="
				+ secretReply + ", writer=" + writer + ", toString()=" + super.toString() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bno == null) ? 0 : bno.hashCode());
		result = prime * result + ((regdate == null) ? 0 : regdate.hashCode());
		result = prime * result + ((replyer == null) ? 0 : replyer.hashCode());
		result = prime * result + ((replytext == null) ? 0 : replytext.hashCode());
		result = prime * result + ((rno == null) ? 0 : rno.hashCode());
		result = prime * result + ((secretReply == null) ? 0 : secretReply.hashCode());
		result = prime * result + ((updatedate == null) ? 0 : updatedate.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + ((writer == null) ? 0 : writer.hashCode());
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
		ReplyVO other = (ReplyVO) obj;
		if (bno == null) {
			if (other.bno != null)
				return false;
		} else if (!bno.equals(other.bno))
			return false;
		if (regdate == null) {
			if (other.regdate != null)
				return false;
		} else if (!regdate.equals(other.regdate))
			return false;
		if (replyer == null) {
			if (other.replyer != null)
				return false;
		} else if (!replyer.equals(other.replyer))
			return false;
		if (replytext == null) {
			if (other.replytext != null)
				return false;
		} else if (!replytext.equals(other.replytext))
			return false;
		if (rno == null) {
			if (other.rno != null)
				return false;
		} else if (!rno.equals(other.rno))
			return false;
		if (secretReply == null) {
			if (other.secretReply != null)
				return false;
		} else if (!secretReply.equals(other.secretReply))
			return false;
		if (updatedate == null) {
			if (other.updatedate != null)
				return false;
		} else if (!updatedate.equals(other.updatedate))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (writer == null) {
			if (other.writer != null)
				return false;
		} else if (!writer.equals(other.writer))
			return false;
		return true;
	}

}
