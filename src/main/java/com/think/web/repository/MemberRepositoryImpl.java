package com.think.web.repository;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.think.entity.Members;

@Repository
public class MemberRepositoryImpl implements MemberRepository {

	private String sql;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int save(Members member) {
		return 0;
	}

	@Override
	public List<Members> findAll() {
		
		sql = "SELECT * FROM members";
	
		return jdbcTemplate.query(sql,
				new RowMapper<Members>() {
					@Override
					public Members mapRow(ResultSet rs, int rowNum) throws SQLException{
						Members member = new Members(
								rs.getInt("memberId"),
								rs.getString("email"),
								rs.getString("password"),
								rs.getInt("grade"),
								rs.getString("dept"),
								rs.getString("major"),
								rs.getString("minor"),
								rs.getString("role"),
								rs.getDate("createdDate"),
								rs.getDate("modifiedDate"),
								rs.getBoolean("enabled"));
						
						member.setProfileName(rs.getString("profileName"));
						member.setProfileIntro(rs.getString("profileIntro"));
						
						Blob profileImage = rs.getBlob("profileImage");
						int profileImageLength = (int) profileImage.length();
						byte[] profileImageBytes = profileImage.getBytes(1, profileImageLength);
						member.setProfileImage(Base64.getEncoder().encodeToString(profileImageBytes));
						return member;
					}
				});
	}

	
	@Override
	public List<Members> findByLimit(int n) {
		sql = "SELECT * FROM members LIMIT ?";
		
		return jdbcTemplate.query(sql,
				new RowMapper<Members>() {
					@Override
					public Members mapRow(ResultSet rs, int rowNum) throws SQLException{
						Members member = new Members(
								rs.getInt("memberId"),
								rs.getString("email"),
								rs.getString("password"),
								rs.getInt("grade"),
								rs.getString("dept"),
								rs.getString("major"),
								rs.getString("minor"),
								rs.getString("role"),
								rs.getDate("createdDate"),
								rs.getDate("modifiedDate"),
								rs.getBoolean("enabled"));
						
						member.setProfileName(rs.getString("profileName"));
						member.setProfileIntro(rs.getString("profileIntro"));
						
						Blob profileImage = rs.getBlob("profileImage");
						int profileImageLength = (int) profileImage.length();
						byte[] profileImageBytes = profileImage.getBytes(1, profileImageLength);
						member.setProfileImage(Base64.getEncoder().encodeToString(profileImageBytes));
						return member;
					}
				}, n);
	}

	@Override
	public Members findById(int memberId) {
		// TODO Auto-generated method stub
		return null;
	}
}
