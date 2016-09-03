:: ********************************************************************************
:: * LaunchClient.bat                                                             *
:: * @author RichardFlanagan                                                      *
:: * Launches the RMI client class in your java project                           *
:: *                                                                              *
:: * ===INSTRUCTIONS===                                                           *
:: * MUST LAUNCH RMIREGISTRY AND SERVER CLASS FIRST!                              *
:: * Set line 21 to your project directory\bin                                    *
:: * Set line 24 to your class file ("client" = package, "ClientMain" = class)    *
:: * If you have no package, leave that part out                                  *
:: * Double click to run                                                          *
:: ********************************************************************************

:: Print commands as false and allow local vars
@ECHO OFF &SETLOCAL  

:: Print feedback
ECHO LaunchClient.bat by RichardFlanagan 

:: The location of your project bin folder. Change as needed
SET Project=I:\SoftwareDevelopmentProject2\RMISoftwareDevelopmentProject\bin

:: Change directory & run the Client
CD /D %Project% & java client.ClientMain

:: Pause the cmd window
PAUSE