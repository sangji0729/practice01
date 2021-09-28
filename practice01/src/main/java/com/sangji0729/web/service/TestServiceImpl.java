package com.sangji0729.web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sangji0729.web.common.CommandMap;
import com.sangji0729.web.dao.TestDAO;
@Service("testService")
public class TestServiceImpl implements TestService {
	Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private TestDAO testDAO;
	
	
	public List<Map<String, Object>> boardList() {
		return testDAO.boardList();
	}


	public int insertDb(Map<String, Object> get) {
		return testDAO.insertDb(get);
	}


	public List<Map<String, Object>> gList(CommandMap commandMap) {
		return testDAO.gList(commandMap);
	}


	

}
