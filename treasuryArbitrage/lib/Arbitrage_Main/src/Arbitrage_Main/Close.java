/*
 * MATLAB Compiler: 4.18.1 (R2013a)
 * Date: Thu Oct 30 21:11:24 2014
 * Arguments: "-B" "macro_default" "-W" "java:Arbitrage_Main,Arbitrage_Main" "-T" 
 * "link:lib" "-d" "E:\\nju\\2014花旗杯\\fxy\\Arbitrage_Main\\src" "-w" 
 * "enable:specified_file_mismatch" "-w" "enable:repeated_file" "-w" 
 * "enable:switch_ignored" "-w" "enable:missing_lib_sentinel" "-w" "enable:demo_license" 
 * "-v" "class{Arbitrage_Main:E:\\nju\\2014花旗杯\\fxy\\Arbitrage_Main.m}" 
 * "class{Open:E:\\nju\\2014花旗杯\\fxy\\open.m}" 
 * "class{Close:E:\\nju\\2014花旗杯\\fxy\\close.m}" 
 * "class{getFlen:E:\\nju\\2014花旗杯\\fxy\\mygetFlen.m}" "-a" 
 * "E:\\nju\\2014花旗杯\\fxy\\Arbitrage_Main.m" "-a" 
 * "E:\\nju\\2014花旗杯\\fxy\\arbitrage_return.m" "-a" 
 * "E:\\nju\\2014花旗杯\\fxy\\bias_ratio.m" "-a" "E:\\nju\\2014花旗杯\\fxy\\close.m" "-a" 
 * "E:\\nju\\2014花旗杯\\fxy\\max_occupying_fund.m" "-a" 
 * "E:\\nju\\2014花旗杯\\fxy\\mygetFlen.m" "-a" "E:\\nju\\2014花旗杯\\fxy\\open.m" 
 */

package Arbitrage_Main;

import com.mathworks.toolbox.javabuilder.*;
import com.mathworks.toolbox.javabuilder.internal.*;
import java.util.*;

/**
 * The <code>Close</code> class provides a Java interface to the M-functions
 * from the files:
 * <pre>
 *  E:\\nju\\2014花旗杯\\fxy\\close.m
 * </pre>
 * The {@link #dispose} method <b>must</b> be called on a <code>Close</code> instance 
 * when it is no longer needed to ensure that native resources allocated by this class 
 * are properly freed.
 * @version 0.0
 */
public class Close extends MWComponentInstance<Close>
{
    /**
     * Tracks all instances of this class to ensure their dispose method is
     * called on shutdown.
     */
    private static final Set<Disposable> sInstances = new HashSet<Disposable>();

    /**
     * Maintains information used in calling the <code>close</code> M-function.
     */
    private static final MWFunctionSignature sCloseSignature =
        new MWFunctionSignature(/* max outputs = */ 3,
                                /* has varargout = */ false,
                                /* function name = */ "close",
                                /* max inputs = */ 10,
                                /* has varargin = */ false);

    /**
     * Shared initialization implementation - private
     */
    private Close (final MWMCR mcr) throws MWException
    {
        super(mcr);
        // add this to sInstances
        synchronized(Close.class) {
            sInstances.add(this);
        }
    }

    /**
     * Constructs a new instance of the <code>Close</code> class.
     */
    public Close() throws MWException
    {
        this(Arbitrage_MainMCRFactory.newInstance());
    }
    
    private static MWComponentOptions getPathToComponentOptions(String path)
    {
        MWComponentOptions options = new MWComponentOptions(new MWCtfExtractLocation(path),
                                                            new MWCtfDirectorySource(path));
        return options;
    }
    
    /**
     * @deprecated Please use the constructor {@link #Close(MWComponentOptions componentOptions)}.
     * The <code>com.mathworks.toolbox.javabuilder.MWComponentOptions</code> class provides API to set the
     * path to the component.
     * @param pathToComponent Path to component directory.
     */
    public Close(String pathToComponent) throws MWException
    {
        this(Arbitrage_MainMCRFactory.newInstance(getPathToComponentOptions(pathToComponent)));
    }
    
    /**
     * Constructs a new instance of the <code>Close</code> class. Use this constructor to 
     * specify the options required to instantiate this component.  The options will be 
     * specific to the instance of this component being created.
     * @param componentOptions Options specific to the component.
     */
    public Close(MWComponentOptions componentOptions) throws MWException
    {
        this(Arbitrage_MainMCRFactory.newInstance(componentOptions));
    }
    
    /** Frees native resources associated with this object */
    public void dispose()
    {
        try {
            super.dispose();
        } finally {
            synchronized(Close.class) {
                sInstances.remove(this);
            }
        }
    }
  
    /**
     * Invokes the first m-function specified by MCC, with any arguments given on
     * the command line, and prints the result.
     */
    public static void main (String[] args)
    {
        try {
            MWMCR mcr = Arbitrage_MainMCRFactory.newInstance();
            mcr.runMain( sCloseSignature, args);
            mcr.dispose();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
    
    /**
     * Calls dispose method for each outstanding instance of this class.
     */
    public static void disposeAllInstances()
    {
        synchronized(Close.class) {
            for (Disposable i : sInstances) i.dispose();
            sInstances.clear();
        }
    }

    /**
     * Provides the interface for calling the <code>close</code> M-function 
     * where the first input, an instance of List, receives the output of the M-function and
     * the second input, also an instance of List, provides the input to the M-function.
     * <p>M-documentation as provided by the author of the M function:
     * <pre>
     * %l_signal当前交易状态（状态，取-3,-2，-1,0,1,2,3）
     * % 输出：signal按照当前的最新价格，是否应当交易。signal=1，正向套利建仓；
     * %signal=-1，反向套利建仓；signal=0，不交易；signal=2，止盈平仓；signal=-2,止
     * %损平仓,signal=3，正向套利平仓；signal=-3，反向套利平仓；
     * </pre>
     * </p>
     * @param lhs List in which to return outputs. Number of outputs (nargout) is
     * determined by allocated size of this List. Outputs are returned as
     * sub-classes of <code>com.mathworks.toolbox.javabuilder.MWArray</code>.
     * Each output array should be freed by calling its <code>dispose()</code>
     * method.
     *
     * @param rhs List containing inputs. Number of inputs (nargin) is determined
     * by the allocated size of this List. Input arguments may be passed as
     * sub-classes of <code>com.mathworks.toolbox.javabuilder.MWArray</code>, or
     * as arrays of any supported Java type. Arguments passed as Java types are
     * converted to MATLAB arrays according to default conversion rules.
     * @throws MWException An error has occurred during the function call.
     */
    public void close(List lhs, List rhs) throws MWException
    {
        fMCR.invoke(lhs, rhs, sCloseSignature);
    }

    /**
     * Provides the interface for calling the <code>close</code> M-function 
     * where the first input, an Object array, receives the output of the M-function and
     * the second input, also an Object array, provides the input to the M-function.
     * <p>M-documentation as provided by the author of the M function:
     * <pre>
     * %l_signal当前交易状态（状态，取-3,-2，-1,0,1,2,3）
     * % 输出：signal按照当前的最新价格，是否应当交易。signal=1，正向套利建仓；
     * %signal=-1，反向套利建仓；signal=0，不交易；signal=2，止盈平仓；signal=-2,止
     * %损平仓,signal=3，正向套利平仓；signal=-3，反向套利平仓；
     * </pre>
     * </p>
     * @param lhs array in which to return outputs. Number of outputs (nargout)
     * is determined by allocated size of this array. Outputs are returned as
     * sub-classes of <code>com.mathworks.toolbox.javabuilder.MWArray</code>.
     * Each output array should be freed by calling its <code>dispose()</code>
     * method.
     *
     * @param rhs array containing inputs. Number of inputs (nargin) is
     * determined by the allocated size of this array. Input arguments may be
     * passed as sub-classes of
     * <code>com.mathworks.toolbox.javabuilder.MWArray</code>, or as arrays of
     * any supported Java type. Arguments passed as Java types are converted to
     * MATLAB arrays according to default conversion rules.
     * @throws MWException An error has occurred during the function call.
     */
    public void close(Object[] lhs, Object[] rhs) throws MWException
    {
        fMCR.invoke(Arrays.asList(lhs), Arrays.asList(rhs), sCloseSignature);
    }

    /**
     * Provides the standard interface for calling the <code>close</code>
     * M-function with 10 input arguments.
     * Input arguments may be passed as sub-classes of
     * <code>com.mathworks.toolbox.javabuilder.MWArray</code>, or as arrays of
     * any supported Java type. Arguments passed as Java types are converted to
     * MATLAB arrays according to default conversion rules.
     *
     * <p>M-documentation as provided by the author of the M function:
     * <pre>
     * %l_signal当前交易状态（状态，取-3,-2，-1,0,1,2,3）
     * % 输出：signal按照当前的最新价格，是否应当交易。signal=1，正向套利建仓；
     * %signal=-1，反向套利建仓；signal=0，不交易；signal=2，止盈平仓；signal=-2,止
     * %损平仓,signal=3，正向套利平仓；signal=-3，反向套利平仓；
     * </pre>
     * </p>
     * @param nargout Number of outputs to return.
     * @param rhs The inputs to the M function.
     * @return Array of length nargout containing the function outputs. Outputs
     * are returned as sub-classes of
     * <code>com.mathworks.toolbox.javabuilder.MWArray</code>. Each output array
     * should be freed by calling its <code>dispose()</code> method.
     * @throws MWException An error has occurred during the function call.
     */
    public Object[] close(int nargout, Object... rhs) throws MWException
    {
        Object[] lhs = new Object[nargout];
        fMCR.invoke(Arrays.asList(lhs), 
                    MWMCR.getRhsCompat(rhs, sCloseSignature), 
                    sCloseSignature);
        return lhs;
    }
}
