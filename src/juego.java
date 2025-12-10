import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

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
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivos))) {
            
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
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivos))) {
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
       
    public String obtenerPalabraAdivinada(String palabraSecreta, char[] letrasIngresadas) {
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < palabraSecreta.length(); i++) {
            char letra = palabraSecreta.charAt(i);
            boolean encontrada = false;
            for (char l : letrasIngresadas) {
                if (l == letra) {
                    encontrada = true;
                    break;
                }
            }
            if (encontrada) {
                resultado.append(letra);
            } else {
                resultado.append("_");
            }
            resultado.append(" ");
        }
        return resultado.toString();
    }//mostrar palabra con guiones
     
    public String obtenerLetrasDisponibles(char[] letrasIngresadas) {
    	
        String alfabeto = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
        StringBuilder disponibles = new StringBuilder();
        
        for (int i = 0; i < alfabeto.length(); i++) {
            char letra = alfabeto.charAt(i);
            boolean usada = false;
            for (char l : letrasIngresadas) {
                if (l == letra) {
                    usada = true;
                    break;
                }
            }
            if (!usada) disponibles.append(letra);
        }
        return disponibles.toString();
    }// letras disponibles
	
    public void inicioAhorcado(String palabraSecreta) {
        this.palabraSecreta = palabraSecreta;
        Scanner sc = new Scanner(System.in);

        System.out.println("Bienvenido al juego del Ahorcado!");
        System.out.println("Estoy pensando en una palabra de " + palabraSecreta.length() + " letras.");
        System.out.println("------------");

        while (oportunidades.quedanOportunidades() && 
               !seAdivinoLaPalabra(palabraSecreta, letrasIngresadas.obtenerLetras())) {

            System.out.println(oportunidades);
            System.out.println("Letras disponibles: " + obtenerLetrasDisponibles(letrasIngresadas.obtenerLetras()));
            System.out.print("Por favor ingresa una letra: ");
            
            char letra = sc.next().toUpperCase().charAt(0);

            if (letrasIngresadas.contiene(letra)) {
                System.out.println("Letra ya ingresada ingrese otra letra: " + obtenerPalabraAdivinada(palabraSecreta, letrasIngresadas.obtenerLetras()));
            } else {
                letrasIngresadas.push(letra);
                if (palabraSecreta.indexOf(letra) >= 0) {
                    System.out.println("Bien hecho: " + obtenerPalabraAdivinada(palabraSecreta, letrasIngresadas.obtenerLetras()));
                } else {
                    oportunidades.perderOportunidad();
                    System.out.println("Letra incorrecta vuelva a intentarlo: " + obtenerPalabraAdivinada(palabraSecreta, letrasIngresadas.obtenerLetras()));
                }
            }
            System.out.println("------------");
        }

        if (seAdivinoLaPalabra(palabraSecreta, letrasIngresadas.obtenerLetras())) {
            System.out.println("¡Felicidades, has GANADO!");
        } else {
            System.out.println("Perdiste, sin oportunidades restantes.");
            System.out.println("La palabra secreta era: " + palabraSecreta);
        }
        
    }// inicio juego ahorcado
    

    
    
}// clase juego ahorcado




public class juego {
	
	
	
	Scanner sc = new Scanner(System.in);
    private static final String NOMBRE_ARCHIVO = "palabras.txt";
	
    public void menuOpciones() {
        Scanner sc = new Scanner(System.in);
        ArchivoPalabras archivo = new ArchivoPalabras(NOMBRE_ARCHIVO);
        int opcion;
        
    
    }
	

	public static void main(String[] args) {
		
	}// main

}
