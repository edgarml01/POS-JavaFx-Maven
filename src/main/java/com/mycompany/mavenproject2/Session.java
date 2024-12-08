/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject2;

import models.User;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author egarm
 */
public class Session {
    private static User user;
    private static SqlSession session = null;

	public static User getUser() {
		return Session.user;
	}

	public static void setUser(User user) {
		Session.user = user;
	}

	public static SqlSession getSQLSession() {
		if(Session.session == null)
		Session.session = MyBatisUtil.getSession();
		return Session.session;
	}
	public static void closeSqlSession(){
		session.close();
	}

	public static void setSession(SqlSession session) {
		Session.session = session;
	}
    

}
