import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	
    public boolean isEmpty() {
        return tope == -1;
    }

    
    public boolean contiene(char letra) {
        for (int i = 0; i <= tope; i++) {
            if (elementos[i] == letra) {
                return true;
            }
        }
        return false;
    }
    
    
    public char[] obtenerLetras() {
        char[] letras = new char[tope + 1];
        for (int i = 0; i <= tope; i++) {
            letras[i] = elementos[i];
        }
        return letras;
    }
    
}// clase pilas letras

class ArchivoPalabras{
	
	private String nombreArchivos;
	
	public ArchivoPalabras(String nombreArchivos) {
		this.nombreArchivos = nombreArchivos;
	}
	
	public String[] leerArchivo() {
        List<String> listaPalabras = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivos))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                
            	
                listaPalabras.add(linea.trim().toUpperCase());
            }
            
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return listaPalabras.toArray(new String[0]);
    }// leer archivo
	
	public void guardarPalabra(String palabra) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivos, true))) {
            bw.write(palabra.toUpperCase());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar la palabra: " + e.getMessage());
        }
    }// guardar palabra
	
	
	public void borrarArchivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo))) {
            
            System.out.println("Archivo borrado y creado nuevamente vacío.");
        } catch (IOException e) {
            System.out.println("Error al borrar el archivo: " + e.getMessage());
        }
    }// borrar archivo
	
	
	
	
}// archivo palabras




public class juego {
	public static void main(String[] args) {
		
	}// main

}
