package com.nt.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.ResultSetMetaData;
import com.nt.bo.StudentBO;
import com.nt.bo.StudentRMBO;
import com.nt.util.SPutil;

@Repository
public class StudentDaoImpl implements StudentDAO {
	private final Logger log=LoggerFactory.getLogger(this.getClass()); 
      @Autowired
	private DataSource ds;
	@Override
	public List<StudentRMBO>  getAllStudent() {
		// TODO Auto-generated method stub
		StudentRMBO bo=null;
		List<StudentRMBO> lrmbo=null;
		try {
			
			Map<String ,RowMapper<?>> logginMapper=new HashMap<String , RowMapper<?>>();
			logginMapper.put("result-1", new RowMapper<StudentBO>() {

				@Override
				public StudentBO mapRow(ResultSet rs, int pos) throws SQLException {
					// TODO Auto-generated method stub
					StudentBO sbo=new StudentBO();
					List<StudentBO> lbo=new ArrayList<StudentBO>();
					ResultSetMetaData rsmd=(ResultSetMetaData) rs.getMetaData();
					int columnCount=rsmd.getColumnCount();
			
					if(columnCount>1) {
					
						sbo.setName(rs.getString("name"));
						sbo.setRollNumber(rs.getString("rollNumber"));
						sbo.setMaths(rs.getInt("Maths"));
						sbo.setEnglish(rs.getInt("English"));
						sbo.setScience(rs.getInt("Science"));
						lbo.add(sbo);
						
					}
					
					return sbo;
				}
			});
			
			bo=new StudentRMBO();
			lrmbo=new ArrayList<StudentRMBO>();
			Map<String ,Integer> outParam=SPutil.getEmptyOutputParams();
			MapSqlParameterSource inputParam=SPutil.spInputParameters();
			Map<String, Object> m=SPutil.executeMultiple(ds, null, SPutil.get_Student, inputParam, outParam, logginMapper);
			ArrayList al=(ArrayList) m.get("result-1");
			System.out.println("array in dao "+al);
			System.out.println("array size "+al.size());

			for(int i=0 ;i<=al.size()-1;i++) {
			bo.setBo(  (StudentBO) al.get(i));
			lrmbo.add(bo);
			System.out.println("in for i= "+ i);
			System.out.println("in for "+ bo);
			}
			
					}
		catch (Exception e) {
			// TODO: handle exception
		}
		

		System.out.println("student info"+bo);

		return lrmbo;
	}
  
	}
