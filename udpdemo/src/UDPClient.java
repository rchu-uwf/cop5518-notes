import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Sample UDP Client
 * https://youtu.be/A5fFxs_DUsQ
 */
public class UDPClient {

    public static void main(String[] args) throws IOException {

        DatagramSocket ds = new DatagramSocket();

        Integer i=7;
        byte[] b = i.toString().getBytes();

        InetAddress ia = InetAddress.getLocalHost();
        DatagramPacket dp = new DatagramPacket(b,b.length,ia,9999);
        ds.send(dp);

        byte[] b1 = new byte[1024];
        DatagramPacket dp1 = new DatagramPacket(b1,b1.length);
        ds.receive(dp1);

        String str = new String(dp1.getData());
        System.out.println("Result is " + str);

    }
}
