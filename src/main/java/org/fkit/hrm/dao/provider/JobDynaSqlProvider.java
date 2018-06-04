package org.fkit.hrm.dao.provider;

import org.apache.ibatis.jdbc.SQL;
import org.fkit.hrm.domain.Job;
import java.util.Map;
import static org.fkit.hrm.util.common.HrmConstants.JOBTABLE;

/**   
 * @Description: 职位动态SQL语句提供类
 */
public class JobDynaSqlProvider {
	// 分页动态查询
	public String selectWhitParam(final Map<String, Object> params){
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM(JOBTABLE);
				if(params.get("job") != null){
					Job job = (Job) params.get("job");
					if(job.getName() != null && !job.getName().equals("")){
						WHERE("  name LIKE CONCAT ('%',#{job.name},'%') ");
					}
				}
			}
		}.toString();
		
		if(params.get("pageModel") != null){
			sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
		}
		
		return sql;
	}	
	// 动态查询总数量
	public String count(final Map<String, Object> params){
		return new SQL(){
			{
				SELECT("count(*)");
				FROM(JOBTABLE);
				if(params.get("job") != null){
					Job job = (Job) params.get("job");
					if(job.getName() != null && !job.getName().equals("")){
						WHERE("  name LIKE CONCAT ('%',#{job.name},'%') ");
					}
				}
			}
		}.toString();
	}	
	// 动态插入
	public String insertJob(final Job job){
		
		return new SQL(){
			{
				INSERT_INTO(JOBTABLE);
				if(job.getName() != null && !job.getName().equals("")){
					VALUES("name", "#{name}");
				}
				if(job.getRemark() != null && !job.getRemark().equals("")){
					VALUES("remark", "#{remark}");
				}
			}
		}.toString();
	}
	// 动态更新
	public String updateJob(final Job job){
		
		return new SQL(){
			{
				UPDATE(JOBTABLE);
				if(job.getName() != null){
					SET(" name = #{name} ");
				}
				if(job.getRemark() != null){
					SET(" remark = #{remark} ");
				}
				WHERE(" id = #{id} ");
			}
		}.toString();
	}
	
	
}
