/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.blacklistvalidator;

import java.util.List;

/**
 *
 * @author hcadavid
 */
public class Main {
    
    public static void main(String a[]) throws InterruptedException{
    	long inicioTiempo = System.currentTimeMillis();
        HostBlackListsValidator hblv=new HostBlackListsValidator();
        //Se cambio la clase Main, para poder obtener los datos al ejecutar con cierta cantidad de Threads
        int n = Runtime.getRuntime().availableProcessors();
        System.out.println("ss: "+n);
        List<Integer> blackListOcurrences=hblv.checkHost("200.24.34.55", 500);
        System.out.println("The host was found in the following blacklists:"+blackListOcurrences);
        long finTiempo = System.currentTimeMillis();
        System.out.println(finTiempo - inicioTiempo);
    }
    
}
