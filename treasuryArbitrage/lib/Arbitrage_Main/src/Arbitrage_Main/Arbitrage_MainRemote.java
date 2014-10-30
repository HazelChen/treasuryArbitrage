/*
 * MATLAB Compiler: 4.18.1 (R2013a)
 * Date: Thu Oct 30 21:11:24 2014
 * Arguments: "-B" "macro_default" "-W" "java:Arbitrage_Main,Arbitrage_Main" "-T" 
 * "link:lib" "-d" "E:\\nju\\2014»¨Æì±­\\fxy\\Arbitrage_Main\\src" "-w" 
 * "enable:specified_file_mismatch" "-w" "enable:repeated_file" "-w" 
 * "enable:switch_ignored" "-w" "enable:missing_lib_sentinel" "-w" "enable:demo_license" 
 * "-v" "class{Arbitrage_Main:E:\\nju\\2014»¨Æì±­\\fxy\\Arbitrage_Main.m}" 
 * "class{Open:E:\\nju\\2014»¨Æì±­\\fxy\\open.m}" 
 * "class{Close:E:\\nju\\2014»¨Æì±­\\fxy\\close.m}" 
 * "class{getFlen:E:\\nju\\2014»¨Æì±­\\fxy\\mygetFlen.m}" "-a" 
 * "E:\\nju\\2014»¨Æì±­\\fxy\\Arbitrage_Main.m" "-a" 
 * "E:\\nju\\2014»¨Æì±­\\fxy\\arbitrage_return.m" "-a" 
 * "E:\\nju\\2014»¨Æì±­\\fxy\\bias_ratio.m" "-a" "E:\\nju\\2014»¨Æì±­\\fxy\\close.m" "-a" 
 * "E:\\nju\\2014»¨Æì±­\\fxy\\max_occupying_fund.m" "-a" 
 * "E:\\nju\\2014»¨Æì±­\\fxy\\mygetFlen.m" "-a" "E:\\nju\\2014»¨Æì±­\\fxy\\open.m" 
 */

package Arbitrage_Main;

import com.mathworks.toolbox.javabuilder.pooling.Poolable;
import java.util.List;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * The <code>Arbitrage_MainRemote</code> class provides a Java RMI-compliant interface to 
 * the M-functions from the files:
 * <pre>
 *  E:\\nju\\2014»¨Æì±­\\fxy\\Arbitrage_Main.m
 * </pre>
 * The {@link #dispose} method <b>must</b> be called on a 
 * <code>Arbitrage_MainRemote</code> instance when it is no longer needed to ensure that 
 * native resources allocated by this class are properly freed, and the server-side proxy 
 * is unexported.  (Failure to call dispose may result in server-side threads not being 
 * properly shut down, which often appears as a hang.)  
 *
 * This interface is designed to be used together with 
 * <code>com.mathworks.toolbox.javabuilder.remoting.RemoteProxy</code> to automatically 
 * generate RMI server proxy objects for instances of Arbitrage_Main.Arbitrage_Main.
 */
public interface Arbitrage_MainRemote extends Poolable
{
    /**
     * Provides the standard interface for calling the <code>Arbitrage_Main</code> 
     * M-function with 4 input arguments.  
     *
     * Input arguments to standard interface methods may be passed as sub-classes of 
     * <code>com.mathworks.toolbox.javabuilder.MWArray</code>, or as arrays of any 
     * supported Java type (i.e. scalars and multidimensional arrays of any numeric, 
     * boolean, or character type, or String). Arguments passed as Java types are 
     * converted to MATLAB arrays according to default conversion rules.
     *
     * All inputs to this method must implement either Serializable (pass-by-value) or 
     * Remote (pass-by-reference) as per the RMI specification.
     *
     * No usage documentation is available for this function.  (To fix this, the function 
     * author should insert a help comment at the beginning of their M code.  See the 
     * MATLAB documentation for more details.)
     *
     * @param nargout Number of outputs to return.
     * @param rhs The inputs to the M function.
     *
     * @return Array of length nargout containing the function outputs. Outputs are 
     * returned as sub-classes of <code>com.mathworks.toolbox.javabuilder.MWArray</code>. 
     * Each output array should be freed by calling its <code>dispose()</code> method.
     *
     * @throws java.jmi.RemoteException An error has occurred during the function call or 
     * in communication with the server.
     */
    public Object[] Arbitrage_Main(int nargout, Object... rhs) throws RemoteException;
  
    /** Frees native resources associated with the remote server object */
    void dispose() throws RemoteException;
}
