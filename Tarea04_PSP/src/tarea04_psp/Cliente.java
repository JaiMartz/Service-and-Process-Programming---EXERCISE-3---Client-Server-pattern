/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea04_psp;

import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Jairo
 */
public class Cliente {
    
    int chirimoyas;
    
    public static void main(String[] args) {
        int port;
        String addressIP;
        
        boolean seguir = true;
 
        String opcion;
        
        Scanner in = new Scanner(System.in);
        Scanner on = new Scanner(System.in);
        
        System.out.println("El servidor se está iniciando...");
        System.out.println("Introduzca direccion IP del servidor:");
        addressIP = on.nextLine();
        
        System.out.println("Introduzca número de puerto del servidor:");
        port = on.nextInt();
        
        ClienteConexion gestionConexion = new ClienteConexion(addressIP, port);
        
        try{
            do{
                //Select the option to do, query, modify, withdraw or exit.

                System.out.println("Elija la opción que desea realizar (NÚMERO)");
                System.out.println("1 - Consultar stock");
                System.out.println("2 - Modificar stock");
                System.out.println("3 - Retirar stock");
                System.out.println("4 - Salir");
                System.out.println("Introduzca el número asociado a cada opción:");
                opcion = in.nextLine();
                
                if((opcion.toString().equals("1"))||(opcion.toString().equals("2"))
                        ||(opcion.toString().equals("3"))|| opcion.toString().equals("4")){
                    
                    switch(opcion){
                        case "1":
                            synchronized(gestionConexion){
                                gestionConexion.peticion=opcion;
                                gestionConexion.notify();
                            }
                            break;
                        case "2":
                            synchronized(gestionConexion){
                                gestionConexion.peticion=opcion;
                                gestionConexion.notify();
                            }
                            break;
                        case "3":
                            synchronized(gestionConexion){
                                gestionConexion.peticion=opcion;
                                gestionConexion.notify();
                            }
                            break;
                        case "4": 
                            seguir = false;
                            System.out.println("Saliendo...");
                            System.exit(0);
                            break;
                    }
                }
            }while(seguir);
            
        }catch(Exception e){
            System.out.println(e.getStackTrace());}
        }
    /**
     * Imprimimos el stock actual
     * @param mensaje parametro que pasa la cantidad en el stock.
     */
    public void mostrarMensaje(String mensaje){
        System.out.println("Stock actual: "+(String)mensaje);
    }
    
}
