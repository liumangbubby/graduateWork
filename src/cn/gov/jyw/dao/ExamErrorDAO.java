package cn.gov.jyw.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import cn.gov.jyw.pojo.exam_error;

import com.ibatis.sqlmap.client.SqlMapExecutor;



public class ExamErrorDAO extends SqlMapClientDaoSupport {
	public List<exam_error> queryExamErrorByUser(int userid){
		return this.getSqlMapClientTemplate().queryForList("QueryErrorByUser", userid);
	}
	public void addExamError(final List<exam_error> errors){
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			@Override
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				executor.startBatch();
				for(int i=0,count=errors.size();i<count;i++)
                {    
                    executor.insert("InsertErrors", errors.get(i));
                }
                executor.executeBatch();
				return null;
			}
			
		});
	}
}
