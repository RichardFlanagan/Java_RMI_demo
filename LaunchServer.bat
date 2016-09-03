:: ********************************************************************************
:: * LaunchServer.bat                                                             *
:: * @author RichardFlanagan                                                      *
:: * Launches the RMI server class in your java project                           *
:: *                                                                              *
:: * ===INSTRUCTIONS===                                                           *
:: * MUST LAUNCH RMIREGISTRY FIRST!                                               *
:: * Set line 21 to your project directory\bin                                    *
:: * Set line 24 to your class file ("server" = package, "ServerMain" = class)    *
:: * If you have no package, leave that part out                                  *
:: * Double click to run                                                          *
:: ********************************************************************************

:: Print commands as false and allow local vars
@ECHO OFF &SETLOCAL  

:: Print feedback
ECHO LaunchServer.bat by RichardFlanagan 

:: The location of your project bin folder. Change as needed
SET Project=I:\SoftwareDevelopmentProject2\RMISoftwareDevelopmentProject\bin

:: Change directory & run the Server
CD /D %Project% & java server.ServerMain

:: Pause the cmd window
PAUSE