package Serie3.Exo3UDP;

import java.net.*;

public class TestClientUDP {

    final static int port = 9632;
    final static int taille = 1024;
    static byte buffer[] = new byte[taille];

    public static void main(String argv[]) throws Exception {
        try {
            InetAddress serveur = InetAddress.getByName("localhost"); //  Adresse du serveur 
            
            String text ="La COVID-19 affecte les individus de différentes manières. "
                            + "La plupart des personnes infectées développent une forme légère à modérée "
                            + "de la maladie et guérissent sans hospitalisation. ";  
            
            byte buffer[] = text.getBytes();  // conversion String to Byte[] (chaine à vecteur d'octet)
            int length = buffer.length;       // la taille du message en nombre d'octet
            
            DatagramSocket socket = new DatagramSocket();   // socket UDP client  
            
            DatagramPacket donneesEmises = new DatagramPacket(buffer, length, serveur, port); // le paquet d'envoi
            DatagramPacket donneesRecues = new DatagramPacket(new byte[taille], taille); // le paquet de reception 
            
            socket.setSoTimeout(3000);   // temps d'attente de la réponse du serveur 3 secondes
            socket.send(donneesEmises);   // envoi paquet
            socket.receive(donneesRecues); // reception de paquet réponse
            
            System.out.println("Message : " + new String(donneesRecues.getData(),
                            0, donneesRecues.getLength()));            
            System.out.println("de : " + donneesRecues.getAddress() + ":"
                            + donneesRecues.getPort());
            
        } catch (SocketTimeoutException ste) {
            System.out.println("Le delai pour la reponse a expire");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
