# Java_RMI_demo
A demo of a 3-Tier Object-Orientated-Design System using Remote Method Invocation (RMI) with Java

I designed my three tier system with generic data handling in mind. My architecture allows me to adapt the system to use many different data types with different numbers or types of attributes. In the end, I included two types of sample data: a list of CPU’s (computer chips) and a list of HDD’s (hard drives).  I chose theses data types as I am interested in technology and computers. I have built my own custom pc, and found it difficult to compare different parts for my build. You can change the data set handled by the server simply by changing the DATA_TYPE global variable in the factory class DataManager.java. Valid strings are "CPU" and "HDD".

## Features

### Threaded Server Tier
Each CRUD remote method of my server tier is threaded. The remote methods are processor and IO heavy because of the serialization being performed in each. On each function call, a thread is created to carry out the desired operation. The serialize function shared between these methods is synchronized to avoid data corruption.

### Localization/Internationalization
I have externalized my strings in my GUI to allow different languages to be implemented. Right now, English has full support, while French is partially implemented as a proof of concept. Using French will show several missing localization errors. You can change language through the file menu.

### Batch launchers
My project includes a set of windows batch files to allow easier running and testing of the application. I wrote these small programs in order to remove the overhead of messing with the command window every time the application needs to be run. If you want to run them, you must make sure that they point to the correct project/bin directory and the correct java files.

- LaunchRMI.bat finds the rmiregistry and launches it in the specified java project\bin directory. Be sure to set ..\JDK\1.7 to ..\JDK\1.8 if using Java8.
- LaunchServer.bat launches the Server tier from the specified project\bin and java file.
- LaunchClient.bat launches the Client tier from the specified project\bin and java file.
