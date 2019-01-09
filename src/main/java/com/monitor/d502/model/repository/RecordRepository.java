package com.monitor.d502.model.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.monitor.d502.model.Record;
import com.monitor.d502.model.User;




public interface RecordRepository extends JpaRepository<Record, String> {

	@Modifying
	@Transactional
	@Query(value="delete from record  where record.did=?1 ",nativeQuery=true)
	int deleteBydId(String did);
	
	@Query(value="select * from record r WHERE r.did=?1  order by r.report_time desc LIMIT 0,1",nativeQuery=true)
	Record finddid(String did);
}
