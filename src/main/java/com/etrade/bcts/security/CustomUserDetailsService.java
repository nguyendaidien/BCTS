/**
 * NAME		:CustomUserDetailsService.java
 * DATE		:11/10/2018
 * AUTHOR	:Ajaya Samanta
 * 
 * 
 * Modified By			Modified On				Reason
 * -----------			------------			--------------
 * 
 */
package com.etrade.bcts.security;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etrade.bcts.dao.UserAttemptDao;
import com.etrade.bcts.model.User;
import com.etrade.bcts.model.UserProfile;
import com.etrade.bcts.service.UserService;


@Service("userDetailsService")
public class CustomUserDetailsService extends JdbcDaoImpl implements UserDetailsService{

	static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
	
	@Autowired
	private UserService userService;
	
	/*@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;*/
	
	@Autowired
	UserAttemptDao userAttemptDao;
	
	
	
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String ssoId) throws AuthenticationException{
		boolean enabledFlag=false;
		boolean accountEpiredflag=false;
		boolean credExpiredFlag=false;
		boolean accountLockedFalg=false;
		User user = userService.findByUserId(ssoId, true);
		logger.info("User : {}", user);
		if(user==null){
			logger.info("User not found");
			throw new UsernameNotFoundException("Username not found");
		}
		
		enabledFlag="1".equals(user.getUserEnabled())?true:false;
		accountEpiredflag="1".equals(user.getAcctExpired())?true:false;
		credExpiredFlag="1".equals(user.getCrdExpired())?true:false;
		accountLockedFalg="1".equals(user.getAccountLocked())?true:false;
		
		return new org.springframework.security.core.userdetails.User(user.getSsoId(), user.getPassword(), 
				enabledFlag, accountEpiredflag, credExpiredFlag, accountLockedFalg, getGrantedAuthorities(user));
	}

	
	
	
	private List<GrantedAuthority> getGrantedAuthorities(User user){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for(UserProfile userProfile : user.getUserProfiles()){
			logger.info("UserProfile : {}", userProfile);
			authorities.add(new SimpleGrantedAuthority("ROLE_"+userProfile.getRoleType()));
		}
		logger.info("authorities : {}", authorities);
		return authorities;
	}
	
	
	@Autowired
	private DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}
	
	@Override
	@Value("select * from APP_USER where sso_id = ?")
	public void setUsersByUsernameQuery(String usersByUsernameQueryString) {
		logger.info("setUsersByUsernameQuery() setUsersByUsernameQuery: {}", usersByUsernameQueryString);
		super.setUsersByUsernameQuery(usersByUsernameQueryString);
	}
	
	@Override
	@Value("select U.sso_id, P.TYPE from BCTS_APP_USER_PROFILE E,APP_USER U, USER_PROFILE P where U.sso_id =? and U.ID=E.USER_ID and E.user_profile_id=P.TRANSACTION_ID")
	public void setAuthoritiesByUsernameQuery(String queryString) {
		logger.info("setAuthoritiesByUsernameQuery() queryString: {}", queryString);
		super.setAuthoritiesByUsernameQuery(queryString);
	}
	
	
	@Override
	public List<UserDetails> loadUsersByUsername(String username) {
	  return getJdbcTemplate().query(super.getUsersByUsernameQuery(), new String[] { username },
		new RowMapper<UserDetails>() {
		  public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
			  logger.info("loadUsersByUsername() rowNum: {}", rowNum);
			String username = rs.getString("sso_id");
			String password = rs.getString("password");
			boolean enabled = rs.getBoolean("USER_ENABLED");
			boolean accountNonExpired = rs.getBoolean("ACCT_EXPIRED");
			boolean credentialsNonExpired = rs.getBoolean("CREDENTIAL_EXPIRED");
			boolean accountNonLocked = rs.getBoolean("ACCT_LOCKED");
			 logger.info("loadUsersByUsername() username: {}", username);
			 logger.info("loadUsersByUsername() password: {}", password);
			 logger.info("loadUsersByUsername() enabled: {}", enabled);
			 logger.info("loadUsersByUsername() accountNonExpired: {}", accountNonExpired);
			 logger.info("loadUsersByUsername() credentialsNonExpired: {}", credentialsNonExpired);
			 logger.info("loadUsersByUsername() accountNonLocked: {}", accountNonLocked);
			return new org.springframework.security.core.userdetails.User(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, AuthorityUtils.NO_AUTHORITIES);
		  }

	  });
	}
	
	
	@Override
	public UserDetails createUserDetails(String username, UserDetails userFromUserQuery,
			List<GrantedAuthority> combinedAuthorities) {
		String returnUsername = userFromUserQuery.getUsername();

		if (super.isUsernameBasedPrimaryKey()) {
		  returnUsername = username;
		}

		return new org.springframework.security.core.userdetails.User(returnUsername, userFromUserQuery.getPassword(), 
                       userFromUserQuery.isEnabled(),
		       userFromUserQuery.isAccountNonExpired(), 
                       userFromUserQuery.isCredentialsNonExpired(),
			userFromUserQuery.isAccountNonLocked(), combinedAuthorities);
	}

	
	
}
