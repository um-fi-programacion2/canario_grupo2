
public class PC extends ObjetoInventariable {
	private Gabinete gab;
	private Teclado teclado;
	private Monitor mon;
	


public PC(int id) {
	this.id = id;
}

public void setGabinete(Gabinete gab) {
	this.gab = gab;
}

public void setTeclado(Teclado teclado) {
	this.teclado = teclado;
}

public void setMonitor(Monitor mon) {
	this.mon = mon;
}

}