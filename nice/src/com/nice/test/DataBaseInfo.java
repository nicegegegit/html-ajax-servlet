/**
 * 
 */
package com.nice.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONObject;

/**
 * @author Administrator
 *
 */
public class DataBaseInfo {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306/mysql?useSSL=false&serverTimezone=UTC";
    static final String USER = "scott";
    static final String PASS = "abc123";
    static Connection conn = null;
    public void close() {
    	System.out.println("数据库关闭");
    	try {
    		if(conn != null) {
    			conn.close();
    		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void conneciton() {
    	 System.out.println("连接数据库...");
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
            // 打开链接
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public JSONObject getSite() {
    	JSONObject json =  new JSONObject();
        Statement stmt = null;
        try{
            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql = "SELECT id, name, url, alexa, country FROM websites";
            ResultSet rs = stmt.executeQuery(sql);
        
            // 展开结果集数据库
            while(rs.next()){
            	JSONObject jsonItem =  new JSONObject();
                // 通过字段检索
                int id  = rs.getInt("id");
                String name = rs.getString("name");
                String url = rs.getString("url");
                int num = rs.getInt("alexa");
                String country = rs.getString("country");
                
                jsonItem.put("id", id);
                jsonItem.put("name", name);
                jsonItem.put("url", url);
                jsonItem.put("num", num);
                jsonItem.put("country", country);
                
                json.append("DB_RESULT", jsonItem);
            }
            // 完成后关闭
            rs.close();
            stmt.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }
        return json;
    }
    
    public JSONObject addSite(JSONObject json) {
    	JSONObject json1 =  new JSONObject();
    	String maxidSQL = "SELECT MAX(id)+1 FROM websites WHERE 1=1";
    	String insertSQL = "INSERT INTO websites VALUE(?, ?, ?, ?, ?);";
    	Statement stmt;
		try {
			int id = 0;
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(maxidSQL);
            while(rs.next()){
            	id = rs.getInt(1);
            }
            rs.close();
            stmt.close();
            
            System.out.println(json.toString());
            
            PreparedStatement ps = conn.prepareStatement(insertSQL);
            ps.setInt(1, id);
            ps.setString(2, json.getString("name"));
            ps.setString(3, json.getString("url"));
            ps.setInt(4, json.getInt("num"));
            ps.setString(5, json.getString("country"));
            
            int affect_row = ps.executeUpdate();
            JSONObject jsonItem =  new JSONObject();
            jsonItem.put("ROWNUM", affect_row);
            json1.append("DB_RESULT", jsonItem);
            
            ps.close();
            
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json1;
    }
    
    public JSONObject updateSite(JSONObject json) {
    	JSONObject json1 =  new JSONObject();
    	
        System.out.println(" 实例化Statement对象...");
        Statement stmt =  null;
		try {
			stmt = conn.createStatement();
	        String sql = "UPDATE websites SET name=?, url=?, alexa=?, country=? WHERE id=?";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setString(1, json.getString("name"));
	        ps.setString(2, json.getString("url"));
	        ps.setInt(3, json.getInt("num"));
	        ps.setString(4, json.getString("country"));
	        
	        ps.setInt(5, json.getInt("id"));
	        int affect_row = ps.executeUpdate();
	        JSONObject jsonItem =  new JSONObject();
	        jsonItem.put("ROWNUM", affect_row);
	        json1.append("DB_RESULT", jsonItem);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	return json1;
    }
    
    public JSONObject deleteSite(JSONObject json) {
    	JSONObject json1 =  new JSONObject();
        // 执行查询
        System.out.println(" 实例化Statement对象...");
        Statement stmt =  null;
		try {
			stmt = conn.createStatement();
	        String sql = "DELETE FROM websites WHERE id=?";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setInt(1, json.getInt("id"));
	        int affect_row = ps.executeUpdate();
	        JSONObject jsonItem =  new JSONObject();
	        jsonItem.put("ROWNUM", affect_row);
	        json1.append("DB_RESULT", jsonItem);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		return json1;
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DataBaseInfo db = new DataBaseInfo();
		db.conneciton();
		System.out.println(db.getSite().toString());
		db.close();
	}

}
