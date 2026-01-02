package com.example.vpn;

import android.net.VpnService;
import android.os.ParcelFileDescriptor;

public class MyVpnService extends VpnService {

    @Override
    public int onStartCommand(android.content.Intent intent, int flags, int startId) {
        new Thread(this::startVpn).start();
        return START_STICKY;
    }

    private void startVpn() {
        try {
            Builder builder = new Builder();
            builder.setSession("YT-VPN");
            builder.addAddress("10.0.0.2", 32);
            builder.addRoute("0.0.0.0", 0);
            builder.addDnsServer("8.8.8.8");

            ParcelFileDescriptor tun = builder.establish();
            new TunnelThread(tun.getFileDescriptor()).start();

        } catch (Exception ignored) {}
    }
}
