package com.github.perryvaldez.seebooks.config.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.github.perryvaldez.seebooks.models.User;
import com.github.perryvaldez.seebooks.models.impl.hibernate.HibUser;
import com.github.perryvaldez.seebooks.models.types.KeyType;
import com.github.perryvaldez.seebooks.models.types.impl.NumericKeyType;

@Service("dbUserDetailsService")
public class DbUserDetailsService implements UserDetailsService {
	private static final Logger LOGGER = LogManager.getLogger(DbUserDetailsService.class);
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
    
	public DbUserDetailsService(DataSource dataSource) {
	    this.dataSource = dataSource;	    
	    this.jdbcTemplate = new JdbcTemplate(this.dataSource);    
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User ud;
		
		try {
		    String sql = "select u.id, u.email, u.password from tbl_users u where email = ?";
		    ud = (User) jdbcTemplate.queryForObject(sql, new Object[] { username }, 
		    		new RowMapper<User>() {
				        @Override
				        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				           User ud = new HibUser();
				           ud.setEmail(rs.getString("email"));
				           ud.setPassword(rs.getString("password"));
				           
				           KeyType id = new NumericKeyType(rs.getLong("id"));
				           ud.setId(id);
				           
					       return ud;
				        }});
			
		} catch(Exception ex) {
			LOGGER.error("==== Exception thrown by SQL query: ", ex);
			ud = null;
		}
	    	
	    if(ud != null) {
			return new org.springframework.security.core.userdetails.User(username, ud.getPassword(), 
                true, true, true, true, AuthorityUtils.createAuthorityList("USER"));	
	    }

        throw new UsernameNotFoundException("Unknown username: " + username);
	}
}
