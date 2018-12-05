package com.etrade.bcts.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.etrade.bcts.model.UserDocument;

@Repository("userDocumentDao")
public class UserDocumentDaoImpl extends AbstractDao<Integer, UserDocument> implements UserDocumentDao{
	static final Logger LOG = LoggerFactory.getLogger(UserDocumentDaoImpl.class);
	
	public List<UserDocument> findAll() {
		CriteriaQuery<UserDocument> crit = createEntityCriteria();
		Root<UserDocument> root = crit.from(UserDocument.class);
		crit.select(root);
		Query<UserDocument> query = getSession().createQuery(crit);
		List<UserDocument> userLst=null;
		try {
		userLst = query.getResultList();
		}catch(Exception e) {
			LOG.error("Error while findAll Document:",e);
		}
		return userLst;
	}

	public void save(UserDocument document) {
		persist(document);
	}

	
	public UserDocument findById(int id) {
		return getByKey(id);
	}

	public List<UserDocument> findAllByUserId(String userId){
		CriteriaQuery<UserDocument> crit = createEntityCriteria();
		Root<UserDocument> root = crit.from(UserDocument.class);
		CriteriaBuilder cbuilder=getCriteriaBuilder();
		Predicate condition = cbuilder.equal(root.get("ssoId"), userId);
		crit.where(condition).select(root).distinct(true);
		Query<UserDocument> query = getSession().createQuery(crit);
		List<UserDocument> docList=null;
		try {
			docList=query.getResultList();
		}catch(Exception e) {
			LOG.error("Error while findAllByUserId():",e);
		}
		return docList;
	}

	public List<UserDocument> findAllByPermitNo(String permitNo){
		CriteriaQuery<UserDocument> crit = createEntityCriteria();
		Root<UserDocument> root = crit.from(UserDocument.class);
		CriteriaBuilder cbuilder=getCriteriaBuilder();
		Predicate condition = cbuilder.equal(root.get("permitNo"), permitNo);
		crit.where(condition).select(root).distinct(true);
		Query<UserDocument> query = getSession().createQuery(crit);
		List<UserDocument> docList=null;
		try {
			docList=query.getResultList();
		}catch(Exception e) {
			LOG.error("Error while findAllByPermitNo():",e);
		}
		return docList;
	}

	public void deleteById(int id) {
		UserDocument document =  getByKey(id);
		delete(document);
	}

	@Override
	public List<UserDocument> findAllByCaseId(String caseId) {
		CriteriaQuery<UserDocument> crit = createEntityCriteria();
		Root<UserDocument> root = crit.from(UserDocument.class);
		CriteriaBuilder cbuilder=getCriteriaBuilder();
		Predicate condition = cbuilder.equal(root.get("caseId"), caseId);
		crit.where(condition).select(root).distinct(true);
		Query<UserDocument> query = getSession().createQuery(crit);
		List<UserDocument> docList=null;
		try {
			docList=query.getResultList();
		}catch(Exception e) {
			LOG.error("Error while findAllByPermitNo():",e);
		}
		return docList;
	}

}
