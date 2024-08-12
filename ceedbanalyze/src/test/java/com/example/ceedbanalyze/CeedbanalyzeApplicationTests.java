package com.example.ceedbanalyze;

import com.example.ceedbanalyze.entity.SchoolRankMap;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CeedbanalyzeApplicationTests {

	@Test
	void contextLoads() {
		SchoolRankMap schoolRankMap = new SchoolRankMap();
		schoolRankMap.getSchoolRankMap().forEach((k,v)->{
			System.out.println(k+" "+v);
		});
	}

	@Test
	void test(){
		System.out.println("48674684684684");
	}
}
