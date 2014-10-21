package com.fangruizhang.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.fangruizhang.entity.Request;
import com.fangruizhang.service.RequestService;
import com.fangruizhang.util.MybatisUtil;

public class RequestServiceImpl implements RequestService {

	SqlSession session=MybatisUtil.getSqlSession();
	RequestService mapperRequestService = session.getMapper(RequestService.class);
	public boolean insertValue(Request team) {
		boolean bresult=false;
		try {
			mapperRequestService.insertValue(team);
		    session.commit();
		    bresult=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bresult;
	}

	public boolean deleteById(int id) {
		boolean bresult=false;
		try {
			mapperRequestService.deleteById(id);
		    session.commit();
		    bresult=true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bresult;
	}

	public boolean updateValue(Request team) {
		boolean bresult=false;
		try {
			mapperRequestService.updateValue(team);
		    session.commit();
		    bresult=true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bresult;
	}

	public Request selectById(int id) {
		Request team=null;
		try {
			team=(Request)mapperRequestService.selectById(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return team;
	}

	public List<Request> selectAll() {
		List<Request> list =null;
		try {
			list=mapperRequestService.selectAll();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}


}
