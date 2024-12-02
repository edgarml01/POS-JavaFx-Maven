/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject2;

import models.User;

/**
 *
 * @author egarm
 */
public class Session {
    private static User user;

	public static User getUser() {
		return Session.user;
	}

	public static void setUser(User user) {
		Session.user = user;
	}
    

}
