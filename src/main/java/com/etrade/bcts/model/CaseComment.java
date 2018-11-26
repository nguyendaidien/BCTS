package com.etrade.bcts.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="BCTS_CASE_COMMENT")
public class CaseComment implements Serializable, Comparable<CaseComment>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name="COMMENT_SEQ", strategy="increment")
	@GeneratedValue(generator="COMMENT_SEQ")
	@Column(name="COMMENT_ID")
	private Integer commentId;
	
	@ManyToOne
	@JoinColumn(name="CASE_ID", nullable=false)
	private BCTSAlert bctsAlert;
	
//	@Column(name="CASE_ID")
//	private Integer caseId;
	
	@ManyToOne
	@JoinColumn(name="USER_ID", nullable=false)
	private User user;
	
	@Column(name="UPDATE_TIME")
	private Date updateTime;
	
	@Column(name="COMMENT_CONTENT")
	private String content;

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public BCTSAlert getBctsAlert() {
		return bctsAlert;
	}

	public void setBctsAlert(BCTSAlert bctsAlert) {
		this.bctsAlert = bctsAlert;
	}

//	public Integer getCaseId() {
//		return caseId;
//	}
//
//	public void setCaseId(Integer caseId) {
//		this.caseId = caseId;
//	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public int compareTo(CaseComment o) {
		return this.updateTime.compareTo(o.getUpdateTime());
	}
}
