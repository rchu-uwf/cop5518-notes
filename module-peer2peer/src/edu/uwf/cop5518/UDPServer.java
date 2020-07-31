package edu.uwf.cop5518;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/*
 * UDPServer.java
 * Systems and Networks II
 * Project 2
 *
 * This file describes the functions to be implemented by the UDPclient class
 * You may also implement any auxillary functions you deem necessary.
 */
public class UDPServer {

    private static final int BUFFER_SIZE = 256;
    private DatagramSocket _socket; // the socket for communication with a server

    /**
     * Constructs a TCPclient object.
     */
    public UDPServer() {
    }

    /**
     * Creates a datagram socket and binds it to a free port.
     *
     * @return - 0 or a negative number describing an error code if the connection could not be established
     */
    public int createSocket() {
        try {
            _socket = new DatagramSocket(61000);
        } catch (SocketException ex) {
            System.err.println("unable to create and bind socket");
            return -1;
        }

        return 0;
    }

    /**
     * Sends a request for service to the server. Do not wait for a reply in this function. This will be
     * an asynchronous call to the server.
     *
     * @param request  - the request to be sent
     * @param hostAddr - the ip or hostname of the server
     * @param port     - the port number of the server
     * @return - 0, if no error; otherwise, a negative number indicating the error
     */
    public int sendRequest(String request, String hostAddr, int port) {
        DatagramPacket newDatagramPacket = createDatagramPacket(request, hostAddr, port);
        if (newDatagramPacket != null) {
            try {
                _socket.send(newDatagramPacket);
            } catch (IOException ex) {
                System.err.println("unable to send message to server");
                return -1;
            }

            return 0;
        }

        System.err.println("unable to create message");
        return -1;
    }

    /**
     * Receives the server's response following a previously sent request.
     *
     * @return - the server's response or NULL if an error occured
     */
    public String receiveResponse() {
        byte[] buffer = new byte[BUFFER_SIZE];
        DatagramPacket newDatagramPacket = new DatagramPacket(buffer, BUFFER_SIZE);
        try {
            _socket.receive(newDatagramPacket);
        } catch (IOException ex) {
            System.err.println("unable to receive message from server");
            return null;
        }

        return new String(buffer).trim();

    }

    /*
     * Prints the response to the screen in a formatted way.
     *
     * response - the server's response as an XML formatted string
     *
     */
    public static void printResponse(String response) {
        System.out.println("FROM SERVER: " + response);
    }


    /*
     * Closes an open socket.
     *
     * @return - 0, if no error; otherwise, a negative number indicating the error
     */
    public int closeSocket() {
        _socket.close();

        return 0;
    }

    /**
     * The main function. Use this function for
     * testing your code. We will provide a new main function on the day of the lab demo.
     */
    public static void main(String[] args) {
        UDPServer server;
        String serverName;
        String req;
        String scratch;

        if (args.length != 2) {
            System.err.println("Usage: UDPServer <serverName> <port number>\n");
            return;
        }
        try {
            serverName = args[0];
        } catch (NullPointerException xcp) {
            System.err.println("Usage: UDPServer <serverName> <port number>\n");
            return;
        }

        int portNum;
        try {
            portNum = Integer.parseInt(args[1]);
        } catch (NumberFormatException xcp) {
            System.err.println("Usage: UDPServer <serverName> <port number>\n");
            return;
        }

        // construct client and client socket
        server = new UDPServer();
        if (server.createSocket() < 0) {
            return;
        }

        String response = server.receiveResponse();
        if (response != null) {
            UDPServer.printResponse(response.trim());
        } else {
            System.err.println("incorrect response from server");
        }

//        System.out.print("Enter a request: ");
//        req = System.console().readLine();

        scratch =  response.trim() + " Jupiter has rings too.";

        if (server.sendRequest(scratch, serverName, portNum) < 0) {
            server.closeSocket();
            return;
        }


        server.closeSocket();
    }

    /**
     * Creates a datagram from the specified request and destination host and port information.
     *
     * @param request  - the request to be submitted to the server
     * @param hostname - the hostname of the host receiving this datagram
     * @param port     - the port number of the host receiving this datagram
     * @return a complete datagram or null if an error occurred creating the datagram
     */
    private DatagramPacket createDatagramPacket(String request, String hostname, int port) {
        byte buffer[] = new byte[BUFFER_SIZE];

        // empty message into buffer
        for (int i = 0; i < BUFFER_SIZE; i++) {
            buffer[i] = '\0';
        }

        // copy message into buffer
        byte data[] = request.getBytes();
        System.arraycopy(data, 0, buffer, 0, Math.min(data.length, buffer.length));

        InetAddress hostAddr;
        try {
            hostAddr = InetAddress.getByName(hostname);
        } catch (UnknownHostException ex) {
            System.err.println("invalid host address");
            return null;
        }

        return new DatagramPacket(buffer, BUFFER_SIZE, hostAddr, port);
    }
}
