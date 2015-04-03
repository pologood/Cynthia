package com.sogou.qadev.service.cynthia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.sogou.qadev.service.cynthia.service.DbPoolConnection;

/**
 * @description:default values db processor
 * @author:liming
 * @mail:liming@sogou-inc.com
 * @date:2014-5-6 下午5:28:19
 * @version:v1.0
 */
public class DefaultValueAccessSessionMySQL
{
	public DefaultValueAccessSessionMySQL()
	{
		super();
	}

	/**
	 * @description:set user template default values
	 * @date:2014-5-6 下午5:28:34
	 * @version:v1.0
	 * @param userName
	 * @param templateId
	 * @param defaultValueJson
	 * @return
	 */
	public boolean setDefaultValues(String userName, String templateId,String defaultValueJson) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try
		{
			conn = DbPoolConnection.getInstance().getConnection();
			
			String defaultValues = getDefaultValues(userName, templateId);
			String sql = "insert into user_default_value(default_value_json,user_name,template_id) values(?,?,?) ";
			
			if(!"".equals(defaultValues)){
				sql = " update user_default_value set default_value_json=? where user_name = ? and template_id=?";
			}
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, defaultValueJson);
			pstmt.setString(2, userName);
			pstmt.setString(3, templateId);
			
			boolean isSuccess =  pstmt.executeUpdate() >0;
			return isSuccess;
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}finally
		{
			DbPoolConnection.getInstance().closeStatment(pstmt);
			DbPoolConnection.getInstance().closeConn(conn);
		}
	}

	/**
	 * @description:get user template default values
	 * @date:2014-5-6 下午5:28:49
	 * @version:v1.0
	 * @param userName
	 * @param templateId
	 * @return
	 */
	public String getDefaultValues(String userName, String templateId) {
		PreparedStatement pstm = null;
		Connection conn = null;
		ResultSet rs = null;
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("select default_value_json from user_default_value where user_name=? and template_id = ?");
			conn = DbPoolConnection.getInstance().getReadConnection();
			pstm = conn.prepareStatement(sql.toString());
			pstm.setString(1, userName);
			pstm.setString(2, templateId);
			
			rs = pstm.executeQuery();
			if(rs.next()){
				return rs.getString("default_value_json");
			}else {
				return "";
			}
		}
		catch (Exception e){
			e.printStackTrace();
			return "";
		}
		finally{
			DbPoolConnection.getInstance().closeAll(rs, pstm, conn);
		}
	}
}
