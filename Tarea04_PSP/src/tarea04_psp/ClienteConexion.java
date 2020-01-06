/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea04_psp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jairo
 */
public class ClienteConexion extends Thread {
    int port;
    private String addressIP;
    private Socket cliente;
    public String peticion;
    private String resultado="";
    boolean exit = false;

    private ObjectOutputStream oos = null;
    private ObjectInputStream ois = null;
    Cliente usuario = new Cliente();
    
    /**
     * Builder of the class ClienteConexion.
     * Constructor de la clase ClienteConexion.
     * @param addressIP valor de la IP que pasamos para su conexion con el servidor. IP value that we set for the connection in the server.
     * @param port valor del puerto que pasamos para la conexion con el servidor. Port value that we set for the connection in the server.
     */
    public ClienteConexion(String addressIP, int port){
        this.addressIP=addressIP;
        this.port=port;
        this.start();
    }
    
    @Override
    public void run(){
        try{
            cliente = new Socket(addressIP, port);
            oos = new ObjectOutputStream(cliente.getOutputStream());
            ois = new ObjectInputStream(cliente.getInputStream());
            
            while(!exit){
                synchronized (this) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        System.err.println("Interrupción: "+e.toString());
                    } 
                        if(!peticion.isEmpty()){
                            switch(peticion){
                                case "1":
                                    oos.writeObject(peticion);
                                    break;
                                case "2":
                                    oos.writeObject(peticion);
                                    break;
                                case "3":
                                    oos.writeObject(peticion);
                                    break;
                                case "4":
                                    oos.writeObject(peticion);
                                    exit = true;
                                    break;
                                default:
                                    resultado =(String)ois.readObject();
                                    usuario.mostrarMensaje(resultado);
                                    break;
                            }
                        }else{
                            System.out.println("No hay ninguna petición.");
                        }
                        resultado =(String)ois.readObject();
                        usuario.mostrarMensaje(resultado);
                }
            }
        }catch(Exception ex){
            ex.getStackTrace();
        }finally{
            try{
                usuario.mostrarMensaje("Se ha cerrado la conexion.");
                if(cliente != null) cliente.close();
                if(oos != null) oos.close();
                if(ois != null) ois.close();
            }catch(IOException ioe){
                ioe.getStackTrace();
            }
        }
    }

}
    