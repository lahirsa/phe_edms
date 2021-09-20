package com.utils;

import oracle.stellent.ridc.IdcClient;
import oracle.stellent.ridc.IdcClientException;
import oracle.stellent.ridc.IdcClientManager;
import oracle.stellent.ridc.IdcContext;

public class ConnectHandler {

    private IdcClient client;
    private IdcContext connectionContext;

    public IdcClient getClient() {
        return client;
    }

    public void setClient(IdcClient client) {
        this.client = client;
    }

    public IdcContext getConnectionContext() {
        return connectionContext;
    }

    public void setConnectionContext(IdcContext connectionContext) {
        this.connectionContext = connectionContext;
    }

    public String createConnection(final String connectURL, String username,
                                   String password) throws IdcClientException {
        System.out.println("Connecting to content server at " + connectURL +
                           " using username " + username + " and password");
        try {
            IdcClientManager idcClientManager = new IdcClientManager();
            this.client = idcClientManager.createClient(connectURL);
        } catch (IdcClientException e) {
            System.out.println("Error occurred while establishing client");
            throw e;
        }
        this.connectionContext = new IdcContext(username, password);
        System.out.println("Succesfully connected RIDC client to " +
                           connectURL);
        return null;
    }
}
