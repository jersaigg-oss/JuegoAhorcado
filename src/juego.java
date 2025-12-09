
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
    
    public void reiniciar() {
        intentosRestantes = maxIntentos;
    }

    
    public int getIntentosRestantes() {return intentosRestantes;}
    
    public boolean quedanOportunidades() {return intentosRestantes > 0;}

    public int getMaxIntentos() {return maxIntentos;}
    
    @Override
    public String toString() {
        return "Te quedan " + intentosRestantes + " oportunidades de " + maxIntentos + ". ";
    }

}// clase oportunidades

class PilaLetras{
	
	private char[] elementos;  
    private int tope;          

    public PilaLetras(int capacidad) {
        elementos = new char[capacidad];
        tope = -1; // Pila vacía
    }
    
    public void push(char letra) {
        if (tope < elementos.length - 1) {
            tope++;
            elementos[tope] = letra;
        } else {
            System.out.println("No se puede insertar mas letras, esta llena la pila. ");
        }
    }
    
    public char pop() {
        if (!isEmpty()) {
            char letra = elementos[tope];
            tope--;
            return letra;
        } else {
            System.out.println("La pila está vacía.");
            return ' ';
        }
    }
    
    public char peek() {
        if (!isEmpty()) {
            return elementos[tope];
        } else {
            System.out.println("La pila está vacía.");
            return ' ';
        }
    }
	
}// clase pilas letras






public class juego {
	public static void main(String[] args) {
		
	}// main

}
