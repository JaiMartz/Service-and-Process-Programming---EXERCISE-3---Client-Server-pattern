/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea04_psp;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Yleve
 */
public class Servidor {
    
    private Scanner sc = new Scanner(System.in);
    private Scanner oc = new Scanner(System.in);
    private int cantidad;
    private String opcion;
    private int port;
    private boolean seguir = true;
    
    //Manage the client connection with the server.
    //Gestionamos la conexion del cliente con el servidor
    public static void main(String[] args) {
        System.out.println("");
        
        Servidor servidor = new Servidor();
        servidor.iniciar();
    }
    
    //Method that iniciate the execution.
    public void iniciar(){
        System.out.println("Introduzca puerto del servidor:");
        port = sc.nextInt();
        ServidorConexion conexionServidor = new ServidorConexion(port);
        
        System.out.println("Se el servidor se ha iniciado.\n");
        
        System.out.println("\nIntroduzca Stock de Chirimoyas inicial(NÚMERO):");
        cantidad = sc.nextInt();
        conexionServidor.stockInicial(cantidad);
        
        try{
        while(seguir){
            
            //Here we choose the action to realize, query or exit.
            System.out.println("\nElija la accion a realizar(NÚMERO): ");
            System.out.println("1 - Consultar");
            System.out.println("2 - Salir");
            System.out.println("Selecciona una opción");
            opcion = oc.nextLine();
            
            if(!opcion.isEmpty()){
                switch(opcion){
                    case "1":
                        System.out.println("Stock de Chirimoyas: "+conexionServidor.consultarStock());
                        break;
                    case "2":
                        System.out.println("Saliendo del servidor...");
                        seguir = false;
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Introduzca una opcion válida.");
                        break;
                    }
            }
        }
            System.out.println("Cerrando servidor");
         }catch(Exception ex){
            if(conexionServidor.servidor == null){
                System.out.println("Se ha producido un error...");
            }
        }finally{
            //Close the connection with the server
            conexionServidor.servidor=null;
            System.out.println("Se ha cerrado la conexion con el servidor.");
        }
    }
}
