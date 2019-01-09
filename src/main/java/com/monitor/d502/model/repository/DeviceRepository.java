package com.monitor.d502.model.repository;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.monitor.d502.model.Device;

public interface DeviceRepository extends JpaRepository<Device, Long>  {
	@Query(value = "select deviceId from SysUserDevice b where b.uid=?1", nativeQuery = true)
	List<String> findByUid(long uid);
	
	@Query(value = "select * from device b where b.id=?1",nativeQuery = true)
	Device findbyId(String id);
}
 
