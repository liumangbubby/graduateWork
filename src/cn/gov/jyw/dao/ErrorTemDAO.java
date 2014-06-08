package cn.gov.jyw.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapExecutor;

import cn.gov.jyw.pojo.error_exam_tem;

public class ErrorTemDAO extends SqlMapClientDaoSupport {
	public void addErrorTem(final List<error_exam_tem> errors){
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			@Override
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				executor.startBatch();
				for(int i=0,count=errors.size();i<count;i++)
                {    
                    executor.insert("InsertErrorTem", errors.get(i));
                }
                executor.executeBatch();
				return null;
			}
			
		});
	}
}
