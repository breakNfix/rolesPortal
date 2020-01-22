package com.example.springboot.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Map;

import java.sql.*;
import org.json.JSONObject;
import org.json.JSONArray;

@RestController
public class MainController {

	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

  @RequestMapping("/aa")
	public String aa() {
		return "Greetings from Spring Boot! AA";
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/saveData", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
  @ResponseBody
	public String save(@RequestBody Map<String, Object> formData) {
		try{
			System.out.println("Body :"+formData);
			String  name =(String) formData.get("name");
			String  interest =(String) formData.get("interest");
			String  type = (String) formData.get("type");
			String  email = (String) formData.get("email");
			String  formNum = (String) formData.get("formNum");
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/rolesDB","root","");
			//here sonoo is database name, root is username and password


			String query = " insert into formData (name, interest,type,email,formNum)  values (?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, interest);
			preparedStmt.setString(3, type);
			preparedStmt.setString(4, email);
			preparedStmt.setString(5, formNum);

			// execute the preparedstatement
			preparedStmt.execute();

			con.close();
			return "Saved";
		}
		catch(Exception e){ System.out.println(e);
e.printStackTrace();
		}

		return "Not Saved";
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping("/getData")
	public String getData() {
		JSONArray res = new JSONArray();
		try{

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/rolesDB","root","");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from formData");

			while(rs.next())  {
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
				JSONObject resObj =  new JSONObject();
				resObj.put("id",rs.getInt(1));
				resObj.put("name",rs.getString(2));
				resObj.put("interest",rs.getString(3));
				resObj.put("type",rs.getString(4));
				resObj.put("email",rs.getString(5));
				resObj.put("formNum",rs.getInt(6));
				res.put(resObj);
			}

			con.close();

		}
		catch(Exception e){ System.out.println(e);}
		return res.toString();
	}

}
