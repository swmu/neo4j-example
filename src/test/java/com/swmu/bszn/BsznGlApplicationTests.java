package com.swmu.bszn;

import com.swmu.bszn.neo4j.service.impl.Neo4jOperationServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BsznGlApplicationTests {

	@Autowired
	private Neo4jOperationServiceImpl neo4jOperationService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testNeotjConnection(){
		neo4jOperationService.buildGuideGraph();
//		neo4jOperationService.buildIntentionSynonymGraph();
//		neo4jOperationService.buildKeywordSynonymGraph();
	}

}
