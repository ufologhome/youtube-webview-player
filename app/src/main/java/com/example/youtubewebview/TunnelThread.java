package com.example.vpn;

import java.io.*;
import java.net.Socket;

public class TunnelThread extends Thread {

    private final FileDescriptor tun;

    // IP ТВОЕГО СЕРВЕРА
    private static final String SERVER_IP = "192.168.0.150";
    private static final int SERVER_PORT = 8881;

    public TunnelThread(FileDescriptor tun) {
        this.tun = tun;
    }

    @Override
    public void run() {
        try (
            FileInputStream in = new FileInputStream(tun);
            FileOutputStream out = new FileOutputStream(tun);
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream()
        ) {
            byte[] buf = new byte[32767];

            while (true) {
                int len = in.read(buf);
                if (len > 0) {
                    sout.write(buf, 0, len);
                }

                if (sin.available() > 0) {
                    len = sin.read(buf);
                    out.write(buf, 0, len);
                }
            }
        } catch (Exception ignored) {}
    }
}
