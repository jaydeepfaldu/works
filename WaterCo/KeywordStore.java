package com.myutility;

public class KeywordStore {
	
	
	 public static boolean isInternet = true;
	
	
	// Server Use	
	
	
	public static String ServerIP = "155.143.205.65";                       // NO DYNAMIC
	public static String ServerPort = "1883";								// NO DYNAMIC	
	public static String ClientID = "CLIENT01";
	public static String ServerUserName = "iccontech";						// NO DYNAMIC
	public static String ServerUserPassword = "iccontech123";				// NO DYNAMIC
	public static String DeviceID = "";					
	public static String ClientSubSctibe = ClientID+DeviceID;	
		
	
	//  Local Use
	
	
	public static String UserName ;
	
	public static String ip = "192.168.1.1";
	public static int port = 2000;   // RN1723
	//public static int port = 2323;   // IBALL WIFI
	public static String myip = "";
	//public static int port1 = 2001;
	
		
	public static boolean EXECUTE_CMD = true;
	public static boolean EXECUTE_RUNNING = false;
	
	public static int msgCounter = 0;
	public static boolean update = true;	
	public static String PreReceiveCounter1 = "01";
	public static String PreReceiveCounter2 = "00";
	
	public static String receivemsg = "0";
	public static int status = 0;
	public static int receiveDone = 0;

	public static boolean lastrun = false;
	
	public static String command = "OPEN";
	
	
	public static int SLIDECOUNT = 1;
	
	public static int CPM = 1 ;  // Pool Mode;
	public static int FPM = 0 ;  // Filter Mode;
	public static int LBE = 0 ;	// Light Mode;
	
	public static int poolcelsius = 0;
	public static int spacelsius = 0;
	
	
	public static int PROG1 = 0 ;	// Prog 1
	public static int PROG2 = 0 ;	// Prog 2
	public static int PROG3 = 0 ;	// Prog 3
	public static int PROG4 = 0 ;	// Prog 4
	public static int PROG5 = 0 ;	// Prog 5
	public static int PROG6 = 0 ;	// Prog 6
	public static int PROG7 = 0 ;	// Prog 7
	public static int PROG8 = 0 ;	// Prog 8
	public static int PROG9 = 0 ;	// Prog 9
	public static int PROG10 = 0 ;	// Prog 10
	public static int PROG11 = 0 ;	// Prog 11
	public static int PROG12 = 0 ;	// Prog 12
	public static int PROG13 = 0 ;	// Prog 13
	public static int PROG14 = 0 ;	// Prog 14
	public static int PROG15 = 0 ;	// Prog 15
	public static int PROG16 = 0 ;	// Prog 16
	public static int PROG17 = 0 ;	// Prog 17
	public static int PROG18 = 0 ;	// Prog 18
	public static int PROG19 = 0 ;	// Prog 19
	public static int PROG20 = 0 ;	// Prog 20
	public static int PROG21 = 0 ;	// Prog 21
	public static int PROG22 = 0 ;	// Prog 22
	public static int PROG23 = 0 ;	// Prog 23
	public static int PROG24 = 0 ;	// Prog 24
	
	
	
}
