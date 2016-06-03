package Server;

import java.io.Serializable;

public interface FileInformation extends Serializable {
	   String  getName();
	   byte[]  getContent();
	   void    setInformation(String name , byte[] content);
	};