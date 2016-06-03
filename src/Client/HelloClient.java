package Client;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import Server.FileInformation;
import Server.FileInformationSev;
import Server.IHello;

public class HelloClient { 
    public static void main(String args[]){ 
    	HelloClient h = new HelloClient();
        try { 
            //在RMI服务注册表中查找名称为RHello的对象，并调用其上的方法  
            IHello rhello =(IHello) Naming.lookup("rmi://localhost:8888/RHello"); 
            System.out.println(rhello.helloWorld()); 
            System.out.println(rhello.sayHelloToSomeBody("熔岩")); 
            rhello.upLoadFile(h.getFileInformationSev());
        } catch (NotBoundException e) { 
            e.printStackTrace(); 
        } catch (MalformedURLException e) { 
            e.printStackTrace(); 
        } catch (RemoteException e) { 
            e.printStackTrace();   
        } 
    } 
    
    public FileInformation getFileInformationSev() throws RemoteException{
    	FileInformation fileInfo = null ;
        BufferedInputStream input = null ;
        String fileName = "test.txt" ;
            // get path
            String filePath = "file_client/"+ fileName ;
            File   file = new File(filePath);
            if(!file.exists()){
                throw new RemoteException("the file whose name is " + fileName + " not existed ");
            }
            // get content
            byte[] content = new byte[(int)file.length()];
            
            try {
				input = new BufferedInputStream(new FileInputStream(file));
				input.read(content);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            // set file name and content to fileInfo
            fileInfo = new FileInformationSev();
            fileInfo.setInformation(fileName , content);
            
            if(input != null ){
               try{
                   input.close();
                   input = null ;
               }catch(Exception ex){
               }
            }
        return fileInfo ;
    } 
}