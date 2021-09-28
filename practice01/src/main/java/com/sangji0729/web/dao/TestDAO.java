package com.sangji0729.web.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sangji0729.web.common.CommandMap;

@Repository("testDAO")
public class TestDAO extends AbstractDAO{
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> boardList(){
		return (List<Map<String, Object>>) selectList("test.boardList");
	}

	public int insertDb(Map<String, Object> get) {
		return insert("test.insertDb", get);
	}

	public List<Map<String, Object>> gList(CommandMap commandMap) {
		return selectList("test.gList");
	}

	

	
}
