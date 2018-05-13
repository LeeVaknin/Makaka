package server;

import data.Models.Client;

public interface IServer {

    /////////
    //Methods
    /////////

    public void establishConnection(IClientHandler iClientHandler);
    public void terminateConnection(IClientHandler iClientHandler);





    //TODO: Validate if those functions are necessary
    public int connect(Client client);
    public int dissconect(Client client);


}
