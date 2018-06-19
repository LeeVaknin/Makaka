package ClientHandler;
import java.io.InputStream;
import java.io.OutputStream;

public interface ClientHandler<T,S> {

    public void handle(InputStream inFromClient, OutputStream outToClient);

}
