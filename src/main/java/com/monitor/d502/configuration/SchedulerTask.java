package com.monitor.d502.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.monitor.d502.model.repository.RecordRepository;

@Component
public class SchedulerTask {
	@Autowired
	RecordRepository recordRepository;
//	@Scheduled(cron="30 * * * * ?")
//    private void test(){
//        int i = recordRepository.deleteBydId("1");
//    }
}
