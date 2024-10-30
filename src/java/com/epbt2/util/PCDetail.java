/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epbt2.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author otasoft01
 */
public class PCDetail {

    Pattern macpt = null;

    public String MACAddress(String ip) {
        // Find OS and set command according to OS
        String OS = System.getProperty("os.name").toLowerCase();

        String[] cmd;
        if (OS.contains("win")) {
            // Windows
            macpt = Pattern
                    .compile("[0-9a-f]+-[0-9a-f]+-[0-9a-f]+-[0-9a-f]+-[0-9a-f]+-[0-9a-f]+");
            //String[] a = {"arp", "-a", ip};
            //String [] a = {"cmd","/c","for /f \"tokens=1*\" %i in ('nbtstat -A " + ip + " ^| find \"00\" ^| findstr -v GROUP') do %i"};
            //String [] a = {"cmd", "/c", "for /f \"tokens=1*\" %i in ('nbtstat -A " + ip + " ^| find \"00\" ^| findstr -v GROUP') do echo %i"};
            String [] a = {"cmd", "/c", "nbtstat -A " + ip + "  | find \"00\" | findstr -v GROUP"};
            cmd = a;
            for (int i = 0; i < a.length; i++) {
                System.out.println(a[i]);
            }
        } else {
            // Mac OS X, Linux
            String[] a = {"bash", "-c", "nmblookup -A " + ip + " | grep 'MAC' | awk '{print $4}' "};
            cmd = a;
        }

        try {
            // Run command
            Process p = Runtime.getRuntime().exec(cmd);
            p.waitFor();
            // read output with BufferedReader
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    p.getInputStream()));
            String line = reader.readLine();

            // Loop trough lines
           /* while (line != null) {
                Matcher m = macpt.matcher(line);

                // when Matcher finds a Line then return it as result
                if (m.find()) {
                    System.out.println("MAC: " + m.group(0));
                    return m.group(0);
                }
                System.out.println("LINE:" + line);
                line = reader.readLine();
            }*/
            
        if (OS.contains("win")) {
            while (line != null) {
                line = reader.readLine();
            }
            System.out.println("AYAT:"+line);
                return line;
        } else {
            while (line != null) {
                return line;
            }
        }
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Return empty string if no MAC is found
        return "";
    }

    public String compName(String ip) throws SQLException {
        // Find OS and set command according to OS
        String OS = System.getProperty("os.name").toLowerCase();

        String[] cmd;
        //String cmd = "";
        if (OS.contains("win")) {
            // Windows
            String[] a = {"cmd", "/C", "nbtstat -A " + ip + " | find \"00\" | findstr -v GROUP"};
            cmd = a;
            for (int i = 0; i < a.length; i++) {
                System.out.println(a[i]);
            }
        } else {
            // Mac OS X, Linux
            String[] a = {"bash", "-c", "nmblookup -A " + ip + " | grep '00' | grep -v GROUP | awk '{print $1}'"};
            cmd = a;
        }

        try {
            // Run command
            Process p = Runtime.getRuntime().exec(cmd);
            p.waitFor();
            // read output with BufferedReader
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    p.getInputStream()));
            String line = reader.readLine();
            System.out.println("PC:"+line);
            while (line != null) {
                return line;
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Return empty string if no MAC is found
        return "";
    }

    public String pcName(Connection conn) throws SQLException {
        String sql = "SELECT SYS_CONTEXT('USERENV','TERMINAL') FROM dual ";
        PreparedStatement stmt = null;
        ResultSet result = null;
        String dataConvert = "";

        try {
            stmt = conn.prepareStatement(sql);
            result = stmt.executeQuery();

            if (result.next()) {
                dataConvert = result.getString(1);
            }

        } finally {
            if (result != null) {
                result.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return dataConvert;
    }

    public String getHostName(InetAddress inaHost) throws UnknownHostException {
        try {
            Class clazz = Class.forName("java.net.InetAddress");
            Constructor[] constructors = clazz.getDeclaredConstructors();
            constructors[0].setAccessible(true);
            InetAddress ina = (InetAddress) constructors[0].newInstance();

            Field[] fields = ina.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.getName().equals("nameService")) {
                    field.setAccessible(true);
                    Method[] methods = field.get(null).getClass().getDeclaredMethods();
                    for (Method method : methods) {
                        if (method.getName().equals("getHostByAddr")) {
                            method.setAccessible(true);
                            return (String) method.invoke(field.get(null), inaHost.getAddress());
                        }
                    }
                }
            }
        } catch (ClassNotFoundException cnfe) {
        } catch (IllegalAccessException iae) {
        } catch (InstantiationException ie) {
        } catch (InvocationTargetException ite) {
            throw (UnknownHostException) ite.getCause();
        }
        return null;
    }
}
