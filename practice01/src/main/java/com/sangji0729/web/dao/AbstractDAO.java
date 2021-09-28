package com.sangji0729.web.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractDAO {
	protected Log log = LogFactory.getLog(AbstractDAO.class);
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	protected void printQueryId(String queryId){
		if(log.isDebugEnabled()) {
			log.debug("\t QueryId \t: " + queryId);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public List selectList(String queryId) {
		printQueryId(queryId);
		return sqlSession.selectList(queryId);
	}
	
	public Map<String, Object> selectOne(String queryId, Map<String, Object> map){
		printQueryId(queryId);
		return sqlSession.selectOne(queryId, map);
	}
	
	public int insert(String queryId, Map<String, Object> map) {
		printQueryId(queryId);
		return sqlSession.insert(queryId, map);
	}
	
}
