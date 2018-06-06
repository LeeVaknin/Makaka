package ClientHandler;
import java.io.InputStream;
import java.io.OutputStream;

public interface ClientHandler {

    public void handle(InputStream inFromClient, OutputStream outToClient);

}
