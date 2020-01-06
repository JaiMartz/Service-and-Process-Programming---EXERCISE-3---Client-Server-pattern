/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea04_psp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jairo
 */
public class ServidorConexion extends Thread {
    
    private int stockChirimoyas;
    private int port;
    private boolean seguir = true;
    private String enviar;
    private String peticion;
    ServerSocket servidor;
    Socket cliente;
    
    Servidor hiloPadre = new Servidor();
    
    ObjectInputStream ois = null;
    ObjectOutputStream oos = null;
    
    //Builder of ServidorConexion class
    public ServidorConexion(int port){
        this.port=port; 
        this.start();
    
    }
    
    @Override
    public void run(){
        
        try {
            
            servidor = new ServerSocket(port);
            cliente = servidor.accept();
            System.out.println("Conexion establecida desde: "+cliente.getInetAddress());
            ois = new ObjectInputStream(cliente.getInputStream());
            oos = new ObjectOutputStream(cliente.getOutputStream());
        
            
                while(seguir){
                    peticion = (String) ois.readObject();
                    if(!peticion.isEmpty()){
                        switch(peticion){
                            case "1":
                                //This option query for the amount of fruits
                                enviar = String.valueOf(this.consultarStock());
                                oos.writeObject(enviar);
                                 break;
                             case"2":
                                 //This option increase the amount fo fruits
                                 enviar = String.valueOf(this.aumentarStock());
                                 oos.writeObject(enviar);
                                 break;
                             case "3":
                                 //This option decrease the amount fo fruits
                                 enviar = String.valueOf(this.reducirStock());
                                 oos.writeObject(enviar);
                                 break;
                        }
                    }else{
                        System.out.println("No hay peticiones.");
                    }
                }
                
        } catch (IOException ex) {
            Logger.getLogger(ServidorConexion.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getStackTrace());
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(ServidorConexion.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    /**Method the determine the initial amount of fruits.
     * 
     * Metodo que determina la cantidad inicial de frutas.
     * 
     * @param cantidad cantidad a definir.
     */
    public void stockInicial(int cantidad){
        stockChirimoyas = cantidad;
    }
    /**
     * Method that allow to check the current amount fo fruits.
     * 
     * Metodo que permite consultar la cantidad actual de chirimoyas.
     * @return cantidad actual de chirimoyas.
     */
    public int consultarStock(){
       return this.stockChirimoyas;
    }
    /**Method that allow to increase the amount of fruits.
     * Metodo que permite incrementar la cantidad de chirimoyas.
     * @return 
     */
    public int aumentarStock(){
       return this.stockChirimoyas++;
    }
    /**
     * Method that allow to decrease the amount of fruits.
     * Metodo que permite reducir la cantidad de chirimoyas.
     * @return cantidad de chirimoyas
     */
    public int reducirStock(){
        return this.stockChirimoyas--;
    }
}
