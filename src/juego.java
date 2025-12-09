import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
	
	public void ordenarPalabras(String[] palabras) {
        // Algoritmo de ordenamiento burbuja
        for (int i = 0; i < palabras.length - 1; i++) {
            for (int j = 0; j < palabras.length - i - 1; j++) {
                if (palabras[j].compareTo(palabras[j + 1]) > 0) {
                    // Intercambiar
                    String temp = palabras[j];
                    palabras[j] = palabras[j + 1];
                    palabras[j + 1] = temp;
                }
            }
        }

        // Guardar las palabras ordenadas en el archivo
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (String palabra : palabras) {
                bw.write(palabra);
                bw.newLine();
            }
            System.out.println("Palabras ordenadas y guardadas.");
        } catch (IOException e) {
            System.out.println("Error al ordenar y guardar palabras: " + e.getMessage());
        }
    }// ordenar palabras
	
	
	
}// archivo palabras

class JuegoAhorcado{
	
	private String palabraSecreta;
    private PilaLetras letrasIngresadas;
    private Oportunidades oportunidades;
    private ArchivoPalabras archivo;

    
    public JuegoAhorcado(ArchivoPalabras archivo, int maxIntentos) {
        this.archivo = archivo;
        this.oportunidades = new Oportunidades(maxIntentos);
        this.letrasIngresadas = new PilaLetras(50); // capacidad suficiente
    }
    
    public String[] cargarPalabras() {
        return archivo.leerArchivo();
    }
    
    public String elegirPalabra(String[] palabras) {
        Random rand = new Random();
        int indice = rand.nextInt(palabras.length);
        return palabras[indice];
    }// random oalabra
    
    public boolean seAdivinoLaPalabra(String palabraSecreta, char[] letrasIngresadas) {
        for (int i = 0; i < palabraSecreta.length(); i++) {
            char letra = palabraSecreta.charAt(i);
            boolean encontrada = false;
            for (char l : letrasIngresadas) {
                if (l == letra) {
                    encontrada = true;
                    break;
                }
            }
            if (!encontrada) return false;
        }
        return true;
    }//verificador de la palabra
	
	
	
}// clase juego ahorcado




public class juego {
	public static void main(String[] args) {
		
	}// main

}
