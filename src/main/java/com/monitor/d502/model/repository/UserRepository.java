package com.monitor.d502.model.repository;

import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.monitor.d502.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	@Query(value = "SELECT  p.url FROM  `user` u  LEFT JOIN  rolepermission rm  ON u.rid=rm.rid LEFT JOIN  permission p ON  rm.pid=p.pid WHERE u.uid=?1", nativeQuery = true)
	List<String> findUrl(long uid);

	User findUserByUserName(String userName);

	User findUserByUid(long uid);

	User findUserByWebsocketSession(String wsid);

	@Modifying
	@Query(value = "update user p set p.password=?1 where p.userName =?2", nativeQuery = true)
	@Transactional
	int updateStatusById(String password, String name);

	@Query(value = "UPDATE user SET websocketSession=?1 WHERE uid=?2", nativeQuery = true)
	int updateUserWebsocketId(String wsid, long uid);
}
