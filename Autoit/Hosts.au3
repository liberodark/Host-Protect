#RequireAdmin
#Region ;**** Directives created by AutoIt3Wrapper_GUI ****
#AutoIt3Wrapper_Res_requestedExecutionLevel=requireAdministrator
#EndRegion ;**** Directives created by AutoIt3Wrapper_GUI ****
#cs ----------------------------------------------------------------------------

 AutoIt Version: 3.3.14.2
 Author:         liberodark

 Script Function:
	SecuZer.

#ce ----------------------------------------------------------------------------

#include <WinApi.au3>
#include <GuiConstants.au3>
#include <GUIConstantsEx.au3>
#include <File.au3>
#include <FileConstants.au3>
#include <InetConstants.au3>
#include <MsgBoxConstants.au3>
#include <WinAPIFiles.au3>
#include "license.au3"

; ==================
; Licence

;setinfo(True) ; show "you have 10 days left" when the license is gonna expire?

;If Not license_registered() Then
;   license_warning()
;EndIf

; ==================
; save & install hosts

If DirCreate ( @AppDataCommonDir & "\SecuZer" ) Then ; create SecuZer folder in C:\ProgramData
_FileWriteLog(@AppDataCommonDir & "\SecuZer\Info.log", "Run") ; create info.log in C:\ProgramData\SecuZer
Global $sHostsPath = "C:\Windows\System32\drivers\etc\hosts"
If Not FileExists($sHostsPath) Then Exit _FileWriteLog(@AppDataCommonDir & "\SecuZer\Info.log", "Hosts absent")
Global $savedHosts = "C:\Windows\System32\drivers\etc\hosts.bak"
If Not FileExists($savedHosts) Then FileCopy($sHostsPath, $savedHosts) ; backup in launch
Global $backup = "C:\Windows\System32\drivers\etc\hosts.bak"
RegWrite("HKLM\SYSTEM\CurrentControlSet\services\Dnscache /v Start /t REG_DWORD /d 3 /f")
RunWait("sc config Dnscache start= disabled", @ScriptDir, @SW_HIDE)
RunWait("net stop Dnscache", @ScriptDir, @SW_HIDE)
Local $sFilePath = @AppDataCommonDir & "\SecuZer\hosts.txt"
Local $hDownload = InetGet("http://liberodark:9iGL&cH$3V@www.secuzer.ovh/hosts/hosts.txt", $sFilePath, $INET_FORCERELOAD, $INET_DOWNLOADBACKGROUND)
Do
Sleep(250)
Until InetGetInfo($hDownload, $INET_DOWNLOADCOMPLETE)
_FileWriteLog(@AppDataCommonDir & "\SecuZer\Info.log", "New hosts is Updated")
FileMove (@AppDataCommonDir & "\SecuZer\hosts.txt", "C:\Windows\System32\drivers\etc\hosts", 1)
FileDelete("C:\Windows\System32\drivers\etc\hosts.tmp")
_FileWriteLog(@AppDataCommonDir & "\SecuZer\Info.log", "New hosts is installed")
RunWait("Run.bat", @ScriptDir, @SW_HIDE) ; REM Create Task for AutoReactivation
_FileWriteLog(@AppDataCommonDir & "\SecuZer\Info.log", "Auto Launch Installed")
Endif

Exit