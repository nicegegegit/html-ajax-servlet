package com.nice.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

/**
 * Servlet implementation class CommServlet
 */
//@WebServlet("/CommServlet")
public class CommServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static DataBaseInfo db = new DataBaseInfo();
	
	
    /**
     * Default constructor. 
     */
    public CommServlet() {
        // TODO Auto-generated constructor stub
    	System.out.println("servlet init-------");
    	db.conneciton();
    }
    @Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    	super.init();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json;charset=utf-8");
		
		String txType = request.getParameter("txtype");
		System.out.println("txtype="+txType);
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		if(txType == null) {
			out.println("交易类型错误");
			return;
		}
		
		if(txType.equalsIgnoreCase("login")) {
			String username = request.getParameter("username");
			String password = request.getParameter("userpass");
			if(username != null && username.equalsIgnoreCase("admin") && password != null && password.equalsIgnoreCase("abc123")) {
				response.sendRedirect("/nice/page/main.html");
			}else {
				response.sendRedirect("/nice/page/error.html");
			}
		}else if(txType.equalsIgnoreCase("query")){//查询
			json = db.getSite();
		}else if(txType.equalsIgnoreCase("add")) {//新增
			String jsonString = request.getParameter("jsonData");
			System.out.println("jsonData="+jsonString);
			JSONObject json1 = new JSONObject(jsonString);
			json = db.addSite(json1);
		}else if(txType.equalsIgnoreCase("update")) {//修改
			String jsonString = request.getParameter("jsonData");
			System.out.println("jsonData="+jsonString);
			JSONObject json1 = new JSONObject(jsonString);
			json = db.updateSite(json1);
		}else if(txType.equalsIgnoreCase("delete")) {//删除
			String jsonString = request.getParameter("jsonData");
			System.out.println("jsonData="+jsonString);
			JSONObject json1 = new JSONObject(jsonString);
			json = db.deleteSite(json1);
		}else {
			response.sendRedirect("/nice/page/error.html");
		}
		System.out.println("返回数据:"+json.toString());
		out.println(json.toString());
		
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		db.close();
		super.destroy();
	}
}
