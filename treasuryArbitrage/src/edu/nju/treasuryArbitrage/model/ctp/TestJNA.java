package edu.nju.treasuryArbitrage.model.ctp;
import com.sun.jna.Callback;
import com.sun.jna.Library;
import com.sun.jna.Native;


public interface TestJNA extends  Library{
	public static final TestJNA 
	INSTANCE = (TestJNA)Native.loadLibrary("./DLL/TestJNA", TestJNA.class);
	
	void initial(String code);
	
	void shut();
	
	void callBack_String(Callback callback);
	
	void callBack_Double(Callback callback);
}

