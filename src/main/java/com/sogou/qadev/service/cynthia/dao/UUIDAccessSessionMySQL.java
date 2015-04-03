/**
* @Title: FlowAccessSessionMySQL.java
* @Package : com.sogou.qadev.service.cynthia.mysql
* @Description : 
* @author : liming
* @date : 2013-8-26
* @version : v1.0
*/
package com.sogou.qadev.service.cynthia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Logger;
import com.sogou.qadev.service.cynthia.idmanager.IDGeneratorFactory;
import com.sogou.qadev.service.cynthia.service.DbPoolConnection;

/**
 * @ClassName : FlowAccessSessionMySQL
 * @Description : 
 * @author : liming
 * @date 2013-8-26
 */
public class UUIDAccessSessionMySQL {
		
	private static Logger logger = Logger.getLogger(UUIDAccessSessionMySQL.class.getName());
		
		
	public synchronized String getUUID(){
		Connection conn = null;
		PreparedStatement seq_ptmt = null;
		ResultSet seq_rs = null;
		try {
			conn = DbPoolConnection.getInstance().getConnection();
			String uuid = "";
			String sql = "select SEQ_CYNTHIA.nextval from dual";
			seq_ptmt =  conn.prepareStatement(sql);
			seq_rs = seq_ptmt.executeQuery();
			if(seq_rs.next()){
				uuid = String.valueOf(seq_rs.getInt(1));
			}
			return uuid;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DbPoolConnection.getInstance().closeResultSet(seq_rs);
			DbPoolConnection.getInstance().closeStatment(seq_ptmt);
			DbPoolConnection.getInstance().closeConn(conn);
		}
		return "";
	}
	
	/**
	 * @description:get a new uuid by type
	 * @date:2014-5-6 下午6:05:08
	 * @version:v1.0
	 * @param type
	 * @return
	 */
	public synchronized String add(String type){
		Connection conn = null;
		PreparedStatement ptmt = null;
//		PreparedStatement seq_ptmt = null;
//		ResultSet seq_rs = null;
		ResultSet rs = null;
		try {
			conn = DbPoolConnection.getInstance().getConnection();
			String uuid = getUUID();
//			String sql = "select SEQ_CYNTHIA.nextval from dual";
//			seq_ptmt =  conn.prepareStatement(sql);
//			seq_rs = seq_ptmt.executeQuery();
//			if(seq_rs.next()){
//				uuid = String.valueOf(seq_rs.getInt(1));
//			}
			ptmt = conn.prepareStatement("insert into uuid (id,type) values(?,?)");
			ptmt.setString(1, uuid);
			ptmt.setString(2, type);
			ptmt.execute();
			return uuid;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DbPoolConnection.getInstance().closeResultSet(rs);
			DbPoolConnection.getInstance().closeStatment(ptmt);
			DbPoolConnection.getInstance().closeConn(conn);
		}
		return "";
	}
	
	
}
