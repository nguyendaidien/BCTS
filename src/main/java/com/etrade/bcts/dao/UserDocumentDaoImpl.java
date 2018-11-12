package com.etrade.bcts.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.etrade.bcts.model.UserDocument;

@Repository("userDocumentDao")
public class UserDocumentDaoImpl extends AbstractDao<Integer, UserDocument> implements UserDocumentDao{

	@SuppressWarnings("unchecked")
	public List<UserDocument> findAll() {
		Criteria crit = createEntityCriteria();
		return (List<UserDocument>)crit.list();
	}

	public void save(UserDocument document) {
		persist(document);
	}

	
	public UserDocument findById(int id) {
		return getByKey(id);
	}

	@SuppressWarnings("unchecked")
	public List<UserDocument> findAllByUserId(String userId){
		Criteria crit = createEntityCriteria();
		Criteria userCriteria = crit.createCriteria("user");
		userCriteria.add(Restrictions.eq("ssoId", userId));
		return (List<UserDocument>)crit.list();
	}

	@SuppressWarnings("unchecked")
	public List<UserDocument> findAllByPermitNo(String permitNo){
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("permitNo", permitNo));
		return (List<UserDocument>)crit.list();
	}

	public void deleteById(int id) {
		UserDocument document =  getByKey(id);
		delete(document);
	}

}
