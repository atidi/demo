package com.example.demo.Util;

import com.example.demo.model.Role;

import java.util.Set;

public class Util {

    public static boolean isAdmin(Set<Role> roles){
        for(Role role : roles) {
            if (role.getName().equals("ADMIN")) {
                return true;
            }
        }
        return false;
    }
}
