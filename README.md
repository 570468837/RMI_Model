# RMI_Model
As is universally acknowledged, Java RMI is sometimes difficult and complex to deal with, so I created this repository to remind 
myself of this particular technology in the following days.

This version is based on and the combination of the following two websites. Thanks for the authors' selfless share.
http://lavasoft.blog.51cto.com/62575/91679/
http://www.cnblogs.com/adolfmc/archive/2012/11/22/2782729.html
The first one talks about the basic use of RMI, the other one discusses about the file transportation of RMI. I combined the two 
in the same interface(IHello).

You should pay attention to this:

1. The class FileInformationSev should implement the interface FileInformation, which extends Serializable, instead of being 
isolated, since all of its attributes(String and byte[]) must be serializable. Therefore, this is the easiest method. Otherwise, 
it will throw out "java.io.InvalidClassException:XXX class invalid for deserialization."

2. If Server and Client are in different projects, both sides should have IHello(RMI interface), FileInformation and FileInformationSev containing the same codes. In addition, they must belong to the same package!!!
