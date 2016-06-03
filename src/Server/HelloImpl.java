package Server;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/** 
* Created by IntelliJ IDEA. 
* User: leizhimin 
* Date: 2008-8-7 21:56:47 
* 远程的接口的实现 
*/ 
public class HelloImpl extends UnicastRemoteObject implements IHello { 
    /** 
     * 因为UnicastRemoteObject的构造方法抛出了RemoteException异常，因此这里默认的构造方法必须写，必须声明抛出RemoteException异常 
     * 
     * @throws RemoteException 
     */ 
    public HelloImpl() throws RemoteException { 
    } 

    /** 
     * 简单的返回“Hello World！"字样 
     * 
     * @return 返回“Hello World！"字样 
     * @throws java.rmi.RemoteException 
     */ 
    public String helloWorld() throws RemoteException { 
        return "Hello World!"; 
    } 

    /** 
     * 一个简单的业务方法，根据传入的人名返回相应的问候语 
     * 
     * @param someBodyName 人名 
     * @return 返回相应的问候语 
     * @throws java.rmi.RemoteException 
     */ 
    public String sayHelloToSomeBody(String someBodyName) throws RemoteException { 
        return "你好，" + someBodyName + "!"; 
    }

	@Override
	public void upLoadFile(FileInformation fileInfo) throws RemoteException{
	       BufferedOutputStream output = null ;
	       try{
	           // check paramter
	           if(fileInfo == null ){
	               throw new RemoteException("the paramter is null ");
	           }
	           //check fileName and content
	           String fileName = fileInfo.getName() ;
	           byte [] content = fileInfo.getContent() ;
	           if(fileName == null || content == null ){
	               throw new RemoteException("the file or the content  is null ");
	           }
	           //create file
	           String filePath = "file_server/"+fileName ;
	           File   file = new File(filePath);
	           if(!file.exists()){
	               file.createNewFile();
	           }
	           //save the content to the file
	           output = new BufferedOutputStream(new FileOutputStream(file));
	           output.write(content);
	       }catch(RemoteException ex){
	           throw ex ;
	       }catch(Exception ex){
	           throw new RemoteException(ex.getLocalizedMessage());
	       }finally{
	           if(output != null ){
	              try{
	                  output.close();
	                  output = null ;
	              }catch(Exception ex){
	              }
	           }
	       }
	}
}

