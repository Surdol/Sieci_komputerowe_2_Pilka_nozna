package siec;




import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;


public class PolaczenieKlient {
    Socket clientSocket = new Socket("127.0.0.1",13);

    public PolaczenieKlient() throws IOException {

    }

    public void odbierzKomunikat(){
        InputStream is = null;
        try {
            is = clientSocket.getInputStream();
            byte[] bytearr = new byte[1];
            while (true){
                int len = is.read(bytearr);
                if(len ==-1 || (char) bytearr[0] == 'K')break;
                System.out.write(bytearr,0,len);

            }
            System.out.println(bytearr);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
