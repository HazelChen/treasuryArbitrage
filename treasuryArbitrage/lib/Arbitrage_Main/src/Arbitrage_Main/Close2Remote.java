/*
 * MATLAB Compiler: 4.18.1 (R2013a)
 * Date: Tue Apr 28 10:04:28 2015
 * Arguments: "-B" "macro_default" "-W" "java:Arbitrage_Main,Open" "-T" "link:lib" "-d" 
 * "E:\\nju\\2014花旗杯\\LHGroup\\NewTA\\treasuryArbitrage\\treasuryArbitrage\\lib\\Arbitrage_Main\\src" 
 * "-w" "enable:specified_file_mismatch" "-w" "enable:repeated_file" "-w" 
 * "enable:switch_ignored" "-w" "enable:missing_lib_sentinel" "-w" "enable:demo_license" 
 * "-S" "-v" "class{Open:E:\\nju\\2014花旗杯\\LHGroup\\MATLABbyFXY\\open.m}" 
 * "class{Close:E:\\nju\\2014花旗杯\\LHGroup\\MATLABbyFXY\\close.m}" 
 * "class{Open1:E:\\nju\\2014花旗杯\\LHGroup\\OC by WXY\\open1.m}" 
 * "class{Close1:E:\\nju\\2014花旗杯\\LHGroup\\OC by WXY\\close1.m}" 
 * "class{Open2:E:\\nju\\2014花旗杯\\LHGroup\\OC by DJ\\open2.m}" 
 * "class{Close2:E:\\nju\\2014花旗杯\\LHGroup\\OC by DJ\\close2.m}" 
 * "class{Arbitrage_Main:E:\\nju\\2014花旗杯\\LHGroup\\MATLABbyFXY\\Arbitrage_Main.m}" 
 * "class{Arbitrage_Main1:E:\\nju\\2014花旗杯\\LHGroup\\OC by WXY\\Arbitrage_Main_W.m}" 
 * "class{Arbitrage_Main2:E:\\nju\\2014花旗杯\\LHGroup\\OC by DJ\\Arbitrage_Main_D.m}" 
 * "-a" "E:\\nju\\2014花旗杯\\LHGroup\\MATLABbyFXY\\Arbitrage_Main.m" "-a" 
 * "E:\\nju\\2014花旗杯\\LHGroup\\OC by DJ\\Arbitrage_Main_D.m" "-a" 
 * "E:\\nju\\2014花旗杯\\LHGroup\\OC by WXY\\Arbitrage_Main_W.m" "-a" 
 * "E:\\nju\\2014花旗杯\\LHGroup\\MATLABbyFXY\\arbitrage_return.m" "-a" 
 * "E:\\nju\\2014花旗杯\\LHGroup\\OC by WXY\\Arbitrage_Return_W.m" "-a" 
 * "E:\\nju\\2014花旗杯\\LHGroup\\MATLABbyFXY\\bias_ratio.m" "-a" 
 * "E:\\nju\\2014花旗杯\\fxy\\close.m" "-a" "E:\\nju\\2014花旗杯\\LHGroup\\OC by 
 * WXY\\close1.m" "-a" "E:\\nju\\2014花旗杯\\LHGroup\\OC by DJ\\close2.m" "-a" 
 * "E:\\nju\\2014花旗杯\\WXYNew\\enumerate.m" "-a" 
 * "E:\\nju\\2014花旗杯\\WXYNew\\hanglieshi.m" "-a" 
 * "E:\\nju\\2014花旗杯\\WXYNew\\judge.m" "-a" "E:\\nju\\2014花旗杯\\LHGroup\\OC by 
 * WXY\\linreg.m" "-a" "E:\\nju\\2014花旗杯\\fxy\\max_occupying_fund.m" "-a" 
 * "E:\\nju\\2014花旗杯\\LHGroup\\OC by DJ\\model2.m" "-a" 
 * "E:\\nju\\2014花旗杯\\fxy\\mygetFlen.m" "-a" "E:\\nju\\2014花旗杯\\WXYNew\\nijuzhen.m" 
 * "-a" "E:\\nju\\2014花旗杯\\fxy\\open.m" "-a" "E:\\nju\\2014花旗杯\\LHGroup\\OC by 
 * WXY\\open1.m" "-a" "E:\\nju\\2014花旗杯\\LHGroup\\OC by DJ\\open2.m" "-a" 
 * "E:\\nju\\2014花旗杯\\LHGroup\\MATLABbyFXY\\return_sum.m" 
 */

package Arbitrage_Main;

import com.mathworks.toolbox.javabuilder.pooling.Poolable;
import java.util.List;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * The <code>Close2Remote</code> class provides a Java RMI-compliant interface to the 
 * M-functions from the files:
 * <pre>
 *  E:\\nju\\2014花旗杯\\LHGroup\\OC by DJ\\close2.m
 * </pre>
 * The {@link #dispose} method <b>must</b> be called on a <code>Close2Remote</code> 
 * instance when it is no longer needed to ensure that native resources allocated by this 
 * class are properly freed, and the server-side proxy is unexported.  (Failure to call 
 * dispose may result in server-side threads not being properly shut down, which often 
 * appears as a hang.)  
 *
 * This interface is designed to be used together with 
 * <code>com.mathworks.toolbox.javabuilder.remoting.RemoteProxy</code> to automatically 
 * generate RMI server proxy objects for instances of Arbitrage_Main.Close2.
 */
public interface Close2Remote extends Poolable
{
    /**
     * Provides the standard interface for calling the <code>close2</code> M-function 
     * with 11 input arguments.  
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
     * M-documentation as provided by the author of the M function:
     * <pre>
     * %close2 根据参数和价格决定平仓信号
     * %   Detailed explanation goes here
     * %   signal1: 平仓信号  保持正向持仓1  保持反向持仓-1  平仓止损-2  平仓止盈2  
     * 正向平仓套利3  反向平仓套利-3
     * </pre>
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
    public Object[] close2(int nargout, Object... rhs) throws RemoteException;
  
    /** Frees native resources associated with the remote server object */
    void dispose() throws RemoteException;
}
