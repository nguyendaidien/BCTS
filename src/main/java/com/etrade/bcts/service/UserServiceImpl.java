/**
 * NAME		:UserServiceImpl.java
 * DATE		:11/10/2018
 * AUTHOR	:Ajaya Samanta
 * 
 * 
 * Modified By			Modified On				Reason
 * -----------			------------			--------------
 * 
 */
package com.etrade.bcts.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etrade.bcts.dao.UserDao;
import com.etrade.bcts.model.User;
import com.etrade.bcts.util.BctsConstants;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao dao;

	@Autowired
    private PasswordEncoder passwordEncoder;
	
	public User findById(int id) {
		return dao.findById(id);
	}

	public User findByUserId(String sso) {
		User user = dao.findByUserId(sso);
		return user;
	}
	
	
	/**
	 * While creating new user it will set default value
	 */
	public void saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setAccountLocked(BctsConstants.ACCTNOTLOCKED);//not locked for initial insert i.e. 1
		user.setAcctExpired(BctsConstants.CREDNOTEXPIRED);
		user.setCrdExpired(BctsConstants.CREDNOTEXPIRED);
		user.setUserEnabled(BctsConstants.USERENABLED);
		user.setCreatedDate(new Date());
		user.setPwdUpdatedDate(new Date());
		//user.setCreatedDate(new Date());
		//Date newDate = DateUtils.addMonths(new Date(), 1);
		Calendar cal = Calendar.getInstance(); 
		cal.add(Calendar.MONTH, 1);
		Date expDt=cal.getTime();
		user.setPwdExpiredDate(expDt);
		dao.save(user);
	}
	
	

	
	
	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateUser(User user) {
		User entity = dao.findById(user.getId());
		if(entity!=null){
			entity.setSsoId(user.getSsoId());
			if(!user.getPassword().equals(entity.getPassword())){
				entity.setPassword(passwordEncoder.encode(user.getPassword()));
			}
			entity.setFirstName(user.getFirstName());
			entity.setLastName(user.getLastName());
			entity.setEmail(user.getEmail());
			entity.setUserProfiles(user.getUserProfiles());
		}
	}

	@Override
	public void updateUserPwd(User user, String chgpwd) {
		user.setPassword(passwordEncoder.encode(chgpwd));
		dao.updateUserPwd(user, chgpwd);
						
	}
	
	public void deleteUserBySSO(String sso) {
		dao.deleteBySSO(sso);
	}

	public List<User> findAllUsers() {
		return dao.findAllUsers();
	}

	public boolean isUserIdUnique(Integer id, String sso) {
		User user = findByUserId(sso);
		return ( user == null || ((id != null) && (user.getId() == id)));
	}

	/* (non-Javadoc)
	 * @see com.etrade.bcts.service.UserService#updateUserAcct(com.etrade.bcts.model.User)
	 */
	@Override
	public void updateUserAcct(User user) {
		dao.updateUserAcct(user);
	}
	
	
}
