/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epbt2.util;

/**
 *
 * @author indah
 */
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Enumeration;
import org.apache.struts2.ServletActionContext;

public class GetNetworkAddress {

    public static String[] getAddress() {
        String[] address = new String[3];
        InetAddress lanIp = null;
        try {

            String ipAddress = null;
            Enumeration<NetworkInterface> net = null;
            net = NetworkInterface.getNetworkInterfaces();

            System.out.println(net.toString());

            while (net.hasMoreElements()) {
                NetworkInterface element = net.nextElement();
                Enumeration<InetAddress> addresses = element.getInetAddresses();

                System.out.println("1)" + addresses.hasMoreElements());
                System.out.println("2)" + element.getHardwareAddress());
                System.out.println("3)" + isVMMac(element.getHardwareAddress()));

                while (addresses.hasMoreElements() && !isVMMac(element.getHardwareAddress())) {
                    InetAddress ip = addresses.nextElement();

                    System.out.println("GET IP:" + ip.getHostAddress());
                    if (ip instanceof Inet4Address) {
                       // if (ip.isSiteLocalAddress()) {
                            ipAddress = ip.getHostAddress();
                            lanIp = InetAddress.getByName(ipAddress);
                            System.out.println(ipAddress+":"+lanIp);
                        //}

                    }

                }
            }
            if (lanIp == null) {
                System.out.println("SYARAT 1");
                address[0] = ServletActionContext.getRequest().getRemoteHost();
              //  address[1] = getMacAddress(lanIp);
                address[2] = InetAddress.getLocalHost().getHostName();

                /* if (ServletActionContext.getRequest().getRemoteHost().equals("0:0:0:0:0:0:0:1")) {
                 ipAddress = InetAddress.getLocalHost().getHostAddress();
                 } else {
                 ipAddress = ServletActionContext.getRequest().getRemoteHost();
                 }
                 log.debug("IP ADRESS :"+ipAddress);*/
            } else {
                System.out.println("SYARAT 2");
                // IP ADDRESS
                address[0] = lanIp.toString().replaceAll("^/+", "");

                // MAC
             //   address[1] = getMacAddress(lanIp);

                // PC NAME 
                address[2] = InetAddress.getLocalHost().getHostName();
            }

            /*
             if (lanIp == null) {
             return null;
             }

             if (addressType.equals("ip")) {

             address = lanIp.toString().replaceAll("^/+", "");

             } else if (addressType.equals("mac")) {

             address = getMacAddress(lanIp);

             } else if (addressType.equals("pc")) {

             InetAddress ip = InetAddress.getLocalHost();
             
             address =  ip.getHostName();

             } else {

             throw new Exception("Specify \"ip\" or \"mac\" or \"pc\"");

             }*/
        } catch (UnknownHostException ex) {

            ex.printStackTrace();

        } catch (SocketException ex) {

            ex.printStackTrace();

        } catch (Exception ex) {

            ex.printStackTrace();

        }

        return address;

    }

    private static String getMacAddress(InetAddress ip) {
        String address = null;
        try {

            NetworkInterface network = NetworkInterface.getByInetAddress(ip);

            byte[] mac = network.getHardwareAddress();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }
            address = sb.toString();

        } catch (SocketException ex) {

            ex.printStackTrace();

        }

        return address;
    }

    private static boolean isVMMac(byte[] mac) {
        try {
            if (null == mac) {
                return false;
            }
            byte invalidMacs[][] = {
                {0x00, 0x05, 0x69}, //VMWare
                {0x00, 0x1C, 0x14}, //VMWare
                {0x00, 0x0C, 0x29}, //VMWare
                {0x00, 0x50, 0x56}, //VMWare
                {0x08, 0x00, 0x27}, //Virtualbox
                {0x0A, 0x00, 0x27}, //Virtualbox
                {0x00, 0x03, (byte) 0xFF}, //Virtual-PC
                {0x00, 0x15, 0x5D} //Hyper-V
            };

            for (byte[] invalid : invalidMacs) {
                if (invalid[0] == mac[0] && invalid[1] == mac[1] && invalid[2] == mac[2]) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}
