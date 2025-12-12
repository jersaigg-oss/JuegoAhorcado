import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;


class Oportunidades{
	
	private int intentosRestantes;
    private int maxIntentos;
    
    // Constructor: recibe el número máximo de intentos permitidos
    public Oportunidades(int maxIntentos) {
        this.maxIntentos = maxIntentos;
        this.intentosRestantes = maxIntentos;
    }
    
    // Método para perder una oportunidad
    public void perderOportunidad() {
        if (intentosRestantes > 0) {
            intentosRestantes--;
        }
    }
    
    // Reinicia las oportunidades
    public void reiniciar() {
        intentosRestantes = maxIntentos;
    }
    
    // Devuelve cuántos intentos quedan
    public int getIntentosRestantes() {return intentosRestantes;}
    
    // Verifica si aún quedan oportunidades
    public boolean quedanOportunidades() {return intentosRestantes > 0;}

    // Devuelve el número máximo de intentos configurados
    public int getMaxIntentos() {return maxIntentos;}
    

    // Representación en texto 
    @Override
    public String toString() {
        return "Te quedan " + intentosRestantes + " oportunidades de " + maxIntentos + ". ";
    }

}// clase oportunidades


class PilaLetras {
	private char[] elementos;  // Arreglo para almacenar las letras
    private int tope;          // Índice del último elemento

    // Constructor: inicializa la pila con una capacidad fija
    public PilaLetras(int capacidad) {
        elementos = new char[capacidad];
        tope = -1; // Pila vacía
    }

    // Insertar una letra en la pila
    public void push(char letra) {
        if (tope < elementos.length - 1) {
            tope++;
            elementos[tope] = letra;
        } else {
            System.out.println("La pila está llena, no se puede insertar más letras.");
        }
    }

    // Sacar la última letra ingresada
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

    // Ver la última letra sin sacarla
    public char peek() {
        if (!isEmpty()) {
            return elementos[tope];
        } else {
            System.out.println("La pila está vacía.");
            return ' ';
        }
    }

    // Verificar si la pila está vacía
    public boolean isEmpty() {
        return tope == -1;
    }

    // Verificar si una letra ya está en la pila
    public boolean contiene(char letra) {
        for (int i = 0; i <= tope; i++) {
            if (elementos[i] == letra) {
                return true;
            }
        }
        return false;
    }

    // Obtener todas las letras almacenadas en la pila como arreglo
    public char[] obtenerLetras() {
        char[] letras = new char[tope + 1];
        for (int i = 0; i <= tope; i++) {
            letras[i] = elementos[i];
        }
        return letras;
    }

}// clase pilas letras

	

	



class ArchivoPalabras{
	String reset="\u001B[0m";
	String green="\033[32m";
	String yellow="\033[33m";
	String blue="\033[34m"; 
	String red="\033[31m";
	String cyan="\033[36m"; 
	
	private String nombreArchivos;
	
	
	public ArchivoPalabras(String nombreArchivos) {
		this.nombreArchivos = nombreArchivos;
	}
	
	
	// Leer todas las palabras del archivo y devolverlas en un arreglo
	public String[] leerArchivo() {
        List<String> listaPalabras = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivos))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                
            	// Convertir a mayúsculas
                listaPalabras.add(linea.trim().toUpperCase());
            }
            
        } catch (IOException e) {
        	System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return listaPalabras.toArray(new String[0]);
    }// leer archivo
	
    // Guardar una palabra en el archivo
	public void guardarPalabra(String palabra) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivos, true))) {
            bw.write(palabra.toUpperCase());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar la palabra: " + e.getMessage());
        }
    }// guardar palabra
	
    // Borrar el archivo y crear uno nuevo vacío
	public void borrarArchivo() {
        try (FileWriter writer = new FileWriter(nombreArchivos)) {
        	// Sobrescribe con archivo vacío
            writer.write(""); 
            System.out.println(cyan + "El archivo ha sido borrado correctamente."+ reset);
        } catch (IOException e) {
            System.out.println("Error al borrar el archivo: " + e.getMessage());
        }

    }// borrar archivo
		
	
	
	public void ordenarPalabras(String[] palabras) {
        quickSort(palabras, 0, palabras.length - 1);
    }

    // Método QuickSort
    private void quickSort(String[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
            
        }
    }

    private int partition(String[] arr, int low, int high) {
        String pivot = arr[high]; 
        int i = (low - 1); 

        for (int j = low; j < high; j++) {
           
            if (arr[j].compareTo(pivot) <= 0) {
                i++;
                
                String temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        
        String temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        
        return i + 1;
        
    }// ordenar palabras
	
    public void mostrarPalabra(String[] palabras) {
		if (palabras == null || palabras.length == 0) {
			System.out.println("no hay palabras");
			return;
		}
		
		ordenarPalabras(palabras);
		
		System.out.println("palabras guardadas: ");
		for(String palabra : palabras) {
			System.out.println(palabra);
		}
	}
}// archivo palabras

class JuegoAhorcado{
	
	String reset="\u001B[0m";
	String green="\033[32m";
	String yellow="\033[33m";
	String blue="\033[34m"; 
	String red="\033[31m";
	String cyan="\033[36m"; 
	
	private PilaLetras letrasIngresadas; 
    private Oportunidades oportunidades;
    private ArchivoPalabras archivo;
    
    // Constructor: recibe el archivo y número máximo de intentos
    public JuegoAhorcado(ArchivoPalabras archivo, int maxIntentos) {
        this.archivo = archivo;
        this.oportunidades = new Oportunidades(maxIntentos);
        this.letrasIngresadas = new PilaLetras(50); // capacidad suficiente
    }
    
    // Cargar palabras desde archivo
    public String[] cargarPalabras() {
        return archivo.leerArchivo();
    }
    
    // Elegir palabra al azar
    public String elegirPalabra(String[] palabras) {
        Random rand = new Random();
        int indice = rand.nextInt(palabras.length);
        return palabras[indice];
    }// random oalabra
    
    
    // Verificar si se adivinó la palabra
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
       
     // Mostrar palabra con guiones bajos
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
        return resultado.toString(); // devuelve el resultado  toString
    }//mostrar palabra con guiones
     
    // Mostrar letras disponibles
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
	
    public String mostrarLetrasIngresadas() {
    	char[] letras = letrasIngresadas.obtenerLetras();

        // Ordenar alfabéticamente
        Arrays.sort(letras);

        StringBuilder sb = new StringBuilder();
        for (char c : letras) {
            sb.append(c);
        }

        return sb.toString();

    }
    
    
    
    
    // Iniciar el juego
    public void inicioAhorcado(String palabraSecreta) {
        

        System.out.println(green +"Bienvenido al juego del Ahorcado!" + reset);
        
        String[] palabras = archivo.leerArchivo(); archivo.mostrarPalabra(palabras);
        System.out.println();
        
        System.out.println("Estoy pensando en una palabra de " + palabraSecreta.length() + " letras.");
        System.out.println("------------");

        while (oportunidades.quedanOportunidades() && 
               !seAdivinoLaPalabra(palabraSecreta, letrasIngresadas.obtenerLetras())) {

            System.out.println(oportunidades);
            System.out.println("Letras disponibles: " + obtenerLetrasDisponibles(letrasIngresadas.obtenerLetras()));
            System.out.print(yellow + "Por favor ingresa una letra: " + reset);
            
            char letra = sc.next().toUpperCase().charAt(0);

            if (letrasIngresadas.contiene(letra)) {
                System.out.println("Letra ya ingresada ingrese otra letra: " + obtenerPalabraAdivinada(palabraSecreta, letrasIngresadas.obtenerLetras()));
            } else {
                letrasIngresadas.push(letra);
                if (palabraSecreta.indexOf(letra) >= 0) {
                    System.out.println("Bien hecho: " + obtenerPalabraAdivinada(palabraSecreta, letrasIngresadas.obtenerLetras()));
                } else {
                    oportunidades.perderOportunidad();
                    System.out.println(red + "Letra incorrecta vuelva a intentarlo: " + reset  + obtenerPalabraAdivinada(palabraSecreta, letrasIngresadas.obtenerLetras()));
                }
                
                System.out.println(mostrarLetrasIngresadas());
               
            }
            System.out.println(cyan + "------------" + reset);
        }
        

        if (seAdivinoLaPalabra(palabraSecreta, letrasIngresadas.obtenerLetras())) {
            System.out.println(green +"¡Felicidades, has GANADO!" + reset);
        } else {
            System.out.println(red + "Perdiste, sin oportunidades restantes." +reset);
            System.out.println("La palabra secreta era: " + palabraSecreta);
        }
        
    }// inicio juego ahorcado
    
    Scanner sc = new Scanner(System.in);
    private static final String NOMBRE_ARCHIVO = "palabras.txt";
	
    public void menuOpciones() {
        Scanner sc = new Scanner(System.in);
        ArchivoPalabras archivo = new ArchivoPalabras(NOMBRE_ARCHIVO);
        char opcion = '0';
        
        do {
            System.out.println("----- MENÚ -----");
            System.out.println("1. Verificar archivo");
            System.out.println("2. Llenar archivo con palabras");
            System.out.println("3. Borrar archivo");
            System.out.println("4. Jugar");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.next().charAt(0) ;
            sc.nextLine(); 
            
                        
            
            switch(opcion) {
                case '1': verificarArchivo(archivo); break;
                case '2': llenarArchivo(archivo, sc); break;
                case '3': archivo.borrarArchivo(); break;
                case '4': jugar(archivo); break;
                case '5': System.out.println(cyan + "Saliendo." + reset); break;
                default:
                    System.out.println(red + "Opción inválida." + reset);
            }
        } while(opcion != '5');

        sc.close();
    
    }
     
    private void verificarArchivo(ArchivoPalabras archivo) {
        String[] palabras = archivo.leerArchivo();
        System.out.println("Se encontraron " + palabras.length + " palabras en el archivo.");
    }// Opción 1: Verificar archivo
    
    private void llenarArchivo(ArchivoPalabras archivo, Scanner sc) {
        System.out.println("Ingresa palabras ya sea en español o ingles"+cyan+" (escribe '0' para terminar):"+reset);
        String palabra;
        do {
            palabra = sc.nextLine();
            if (!palabra.equalsIgnoreCase("0")) {
                archivo.guardarPalabra(palabra);
            }
        } while (!palabra.equalsIgnoreCase("0"));
    }// Opción 2: Llenar archivo con palabras

    private void jugar(ArchivoPalabras archivo) {
        String[] palabras = archivo.leerArchivo();
        if (palabras.length == 0) {
            System.out.println(red + "El archivo está vacío. Debes llenarlo primero." + reset);
            return;
        }
        JuegoAhorcado juego = new JuegoAhorcado(archivo, 10); //vidas
        String palabra = juego.elegirPalabra(palabras);
        juego.inicioAhorcado(palabra);
    }// Opción 4: Jugar
    
    
    
}// clase juego ahorcado


public class juego {
	
	public static void main(String[] args) {
		
		JuegoAhorcado ja = new JuegoAhorcado(null, 0);
		ja.menuOpciones();
		
		
		
		
	}// main

}
