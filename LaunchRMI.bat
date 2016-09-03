:: ********************************************************************************
:: * LaunchRMI.bat                                                                *
:: * @author RichardFlanagan                                                      *
:: * Launches the rmiregistry from the jdk 1.7 in the specified project directory *
:: *                                                                              *
:: * ===INSTRUCTIONS===                                                           *
:: * Set line 22 to your project directory\bin                                    *
:: * Double click to run                                                          *
:: ********************************************************************************

:: Print commands as false and allow local vars
@ECHO OFF &SETLOCAL  

:: Print feedback
ECHO LaunchRMI.bat by RichardFlanagan 
ECHO If nothing appears below, RMI Registry should be running. 

:: Find the java install directory from the machine registry
FOR /F "tokens=2*" %%a IN ('REG QUERY "HKEY_LOCAL_MACHINE\SOFTWARE\JavaSoft\Java Development Kit\1.7" /v JavaHome') DO set "Java17=%%b\bin\"

:: The location of your project bin folder. Change as needed
SET Project=E:\STUFF\SoftwareDevAssignment\RMISoftwareDevelopmentProject\bin

:: Change directory & run the rmiregistry
CD /D %Project% && %Java17%rmiregistry.exe

:: Pause the cmd window
PAUSE