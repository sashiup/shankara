package com.proto.shankara.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.proto.shankara.data.FeedReader;
import com.proto.shankara.data.MysqlConnector;
import com.proto.shankara.pojo.Employee;
import com.proto.shankara.pojo.SFeed;


public class ShankaraProtoServlet extends HttpServlet {
	private static final long serialVersionUID = 1793370959800736435L;
	protected static final String EMP_SRCH = "EMP";
	protected static final String FEED = "FEED";

	private Logger servLog = Logger.getLogger(ShankaraProtoServlet.class);

	public ShankaraProtoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reqType = request.getParameter("reqType");
		Map<String,String> srch = new HashMap<String,String>();
		srch.put("empLastName",request.getParameter("lstName"));
		srch.put("empFirstName",request.getParameter("fstName"));

		PrintWriter out = response.getWriter();
		response.setContentType("json");
		response.setHeader("Cache-control", "no-cache, no-store");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "-1");

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type");
		response.setHeader("Access-Control-Max-Age", "86400");

		Gson gson = new Gson(); 
		JsonObject myObj = new JsonObject();

		if(EMP_SRCH.equalsIgnoreCase(reqType)){
			Employee emp = getEmployee(srch);
			servLog.debug("EMPLOYEE:::"+emp);
			JsonElement empObj = gson.toJsonTree(emp);
			if(emp.getFirstName() == null){
				myObj.addProperty("success", false);
			}
			else {
				myObj.addProperty("success", true);
			}
			myObj.add("empInfo", empObj);
		}else if(FEED.equalsIgnoreCase(reqType)){
			String feedUrl = request.getParameter("feedurl");
			SFeed sfeed = getFeed(feedUrl);
			servLog.debug("Feed details:::"+sfeed);
			JsonElement sfeedObj = gson.toJsonTree(sfeed);
			if(sfeed.getTitle() == null){
				myObj.addProperty("success", false);
			}
			else {
				myObj.addProperty("success", true);
			}
			myObj.add("sfeedObj", sfeedObj);
		}
		
		out.println(myObj.toString());
		out.close();
	}
	protected Employee getEmployee(Map<String,String> srch){
		MysqlConnector dataProv = new MysqlConnector();
		List<Employee> employees = dataProv.searchEmployee(srch);
		if(!employees.isEmpty()){
			return employees.get(0);
		}
		return new Employee();
	}

	protected SFeed getFeed(String url){
		FeedReader fReader = new FeedReader();
		servLog.debug("URL::"+url);
		SFeed feed = fReader.readFeed(url);
		return feed;
	}
}