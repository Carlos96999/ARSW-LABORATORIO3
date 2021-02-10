package edu.eci.blacklistvalidator;

import java.util.LinkedList;
import edu.eci.blacklistvalidator.HostBlackListsValidator;
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
	private HostBlackListsValidator control;
	
	public HostBlackListThread(String direccionIP, int inicio, int fin, HostBlacklistsDataSourceFacade skds, HostBlackListsValidator control) {
		this.direccionIP = direccionIP;
		this.inicio = inicio;
		this.fin = fin;
		this.contadorListas = contadorListas;
		this.skds = skds;
		this.control = control;
		ocurrencias = 0;
		listaOcurrencias = new LinkedList<Integer>();
	}
	
	@SuppressWarnings("deprecation")
	public void run() {
		for(int i = inicio; i < fin && ocurrencias < BLACK_LIST_ALARM_COUNT; i++) {
			contadorListas = contadorListas + 1;
			
			if(control.getFin())
			{
				this.stop();
			}
			if(skds.isInBlackListServer(i, direccionIP)) {
				listaOcurrencias.add(i);
				ocurrencias = ocurrencias + 1;
				if(ocurrencias >= BLACK_LIST_ALARM_COUNT)
				{
					control.setFin(true);
				}
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
