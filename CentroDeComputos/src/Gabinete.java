
public class Gabinete extends ObjetoInventariable {
	public Memoria mem;
	public Disco disc;
	public CPU cpu;
	
	
	public Gabinete (int id, int idMem, int capMem, int idDisco, int capDisc, int idCPU, int velocidad) {
		this.id = id;
		this.mem = new Memoria(idMem, capMem);
		this.disc = new Disco(idDisco, capDisc);
		this.cpu = new CPU(idCPU, velocidad);
	}
}
