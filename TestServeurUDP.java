package Serie3.Exo3UDP;

import java.net.*;

public class TestServeurUDP {

    final static int port = 9632;                              // numéro du port d'échange
    final static int taille = 1024;                            // taille du Datagram (paquet)
    static byte buffer[] = new byte[taille];                   // buffer qui va contenir les data (en octet 1024 octet) 

    public static void main(String argv[]) throws Exception {
        
        DatagramSocket socket = new DatagramSocket(port);      // socket d'échange 
        
        String donnees = "";                     
        String message = "";
        int taille = 0;
        
        System.out.println("Lancement du serveur");
        
        while (true) {
            DatagramPacket paquet = new DatagramPacket(buffer, buffer.length); // un datagram de reception car 2 parametres taille et buffer)
            DatagramPacket envoi = null;            // datagram d'envoi vide
            socket.receive(paquet);                 // reception des données avec la socket et stockage dans paquet
            System.out.println("\n" + paquet.getAddress()); // DatagramPacket.getAddress() renvoie l'adresse source (c-a-d du client)
            taille = paquet.getLength();                    // DatagramPacket.getLength() renvoie la taille du paquet
            donnees = new String(paquet.getData(), 0, taille); // convertir les données de Byte (octet) à String
            System.out.println("Donnees reçues = " + donnees); 
            message = "Bonjour " + donnees;
            System.out.println("Donnees envoyees = " + message);
            envoi = new DatagramPacket(message.getBytes(),  
                            message.length(), paquet.getAddress(), paquet.getPort()); // Creer un paquet d'envoi donc 4 infos sont incluses, le message, sa taille, l'addresse destination et le port; 
            socket.send(envoi);    // envoyer les données 
        }
    }
}
