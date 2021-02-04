package edu.eci.blacklistvalidator;

import java.util.LinkedList;

import edu.eci.spamkeywordsdatasource.HostBlacklistsDataSourceFacade;

public class HostBlackListThread extends Thread{

	private int ocurrencias;
	private int inicio;
	private int fin;
	private int contadorListas;
	private String direccionIP;
	private HostBlacklistsDataSourceFacade skds;
	private LinkedList<Integer> listaOcurrencias;
	private static final int BLACK_LIST_ALARM_COUNT=5; //alarm	
	
	public HostBlackListThread(String direccionIP, int inicio, int fin, HostBlacklistsDataSourceFacade skds) {
		this.direccionIP = direccionIP;
		this.inicio = inicio;
		this.fin = fin;
		this.contadorListas = contadorListas;
		this.skds = skds;
		ocurrencias = 0;
		listaOcurrencias = new LinkedList<Integer>();
		
	}
	
	public void run() {
		for(int i = inicio; i < fin; i++) {
			contadorListas = contadorListas + 1;
			if(skds.isInBlackListServer(i, direccionIP)) {
				listaOcurrencias.add(i);
				ocurrencias = ocurrencias + 1;
			}
		}
	}
	
	public int getContadorListas() {
		return contadorListas;
	}
	
	public LinkedList<Integer> getListaOcurrencias(){
		return listaOcurrencias;
	}
	
	public int getOcurrencias() {
		return ocurrencias;
	}
	
}
