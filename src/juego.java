
class Oportunidades{
	
	private int intentosRestantes;
    private int maxIntentos;

    public Oportunidades(int maxIntentos) {
        this.maxIntentos = maxIntentos;
        this.intentosRestantes = maxIntentos;
    }
    
    public void perderOportunidad() {
        if (intentosRestantes > 0) {
            intentosRestantes--;
        }
    }
    
    
	
}// clase oportunidades


public class juego {
	public static void main(String[] args) {
		
	}// main

}
