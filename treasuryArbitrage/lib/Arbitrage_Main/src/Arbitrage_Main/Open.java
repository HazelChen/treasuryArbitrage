/*
 * MATLAB Compiler: 4.18.1 (R2013a)
 * Date: Tue Apr 28 10:04:27 2015
 * Arguments: "-B" "macro_default" "-W" "java:Arbitrage_Main,Open" "-T" "link:lib" "-d" 
 * "E:\\nju\\2014���챭\\LHGroup\\NewTA\\treasuryArbitrage\\treasuryArbitrage\\lib\\Arbitrage_Main\\src" 
 * "-w" "enable:specified_file_mismatch" "-w" "enable:repeated_file" "-w" 
 * "enable:switch_ignored" "-w" "enable:missing_lib_sentinel" "-w" "enable:demo_license" 
 * "-S" "-v" "class{Open:E:\\nju\\2014���챭\\LHGroup\\MATLABbyFXY\\open.m}" 
 * "class{Close:E:\\nju\\2014���챭\\LHGroup\\MATLABbyFXY\\close.m}" 
 * "class{Open1:E:\\nju\\2014���챭\\LHGroup\\OC by WXY\\open1.m}" 
 * "class{Close1:E:\\nju\\2014���챭\\LHGroup\\OC by WXY\\close1.m}" 
 * "class{Open2:E:\\nju\\2014���챭\\LHGroup\\OC by DJ\\open2.m}" 
 * "class{Close2:E:\\nju\\2014���챭\\LHGroup\\OC by DJ\\close2.m}" 
 * "class{Arbitrage_Main:E:\\nju\\2014���챭\\LHGroup\\MATLABbyFXY\\Arbitrage_Main.m}" 
 * "class{Arbitrage_Main1:E:\\nju\\2014���챭\\LHGroup\\OC by WXY\\Arbitrage_Main_W.m}" 
 * "class{Arbitrage_Main2:E:\\nju\\2014���챭\\LHGroup\\OC by DJ\\Arbitrage_Main_D.m}" 
 * "-a" "E:\\nju\\2014���챭\\LHGroup\\MATLABbyFXY\\Arbitrage_Main.m" "-a" 
 * "E:\\nju\\2014���챭\\LHGroup\\OC by DJ\\Arbitrage_Main_D.m" "-a" 
 * "E:\\nju\\2014���챭\\LHGroup\\OC by WXY\\Arbitrage_Main_W.m" "-a" 
 * "E:\\nju\\2014���챭\\LHGroup\\MATLABbyFXY\\arbitrage_return.m" "-a" 
 * "E:\\nju\\2014���챭\\LHGroup\\OC by WXY\\Arbitrage_Return_W.m" "-a" 
 * "E:\\nju\\2014���챭\\LHGroup\\MATLABbyFXY\\bias_ratio.m" "-a" 
 * "E:\\nju\\2014���챭\\fxy\\close.m" "-a" "E:\\nju\\2014���챭\\LHGroup\\OC by 
 * WXY\\close1.m" "-a" "E:\\nju\\2014���챭\\LHGroup\\OC by DJ\\close2.m" "-a" 
 * "E:\\nju\\2014���챭\\WXYNew\\enumerate.m" "-a" 
 * "E:\\nju\\2014���챭\\WXYNew\\hanglieshi.m" "-a" 
 * "E:\\nju\\2014���챭\\WXYNew\\judge.m" "-a" "E:\\nju\\2014���챭\\LHGroup\\OC by 
 * WXY\\linreg.m" "-a" "E:\\nju\\2014���챭\\fxy\\max_occupying_fund.m" "-a" 
 * "E:\\nju\\2014���챭\\LHGroup\\OC by DJ\\model2.m" "-a" 
 * "E:\\nju\\2014���챭\\fxy\\mygetFlen.m" "-a" "E:\\nju\\2014���챭\\WXYNew\\nijuzhen.m" 
 * "-a" "E:\\nju\\2014���챭\\fxy\\open.m" "-a" "E:\\nju\\2014���챭\\LHGroup\\OC by 
 * WXY\\open1.m" "-a" "E:\\nju\\2014���챭\\LHGroup\\OC by DJ\\open2.m" "-a" 
 * "E:\\nju\\2014���챭\\LHGroup\\MATLABbyFXY\\return_sum.m" 
 */

package Arbitrage_Main;

import com.mathworks.toolbox.javabuilder.*;
import com.mathworks.toolbox.javabuilder.internal.*;
import java.util.*;

/**
 * The <code>Open</code> class provides a Java interface to the M-functions
 * from the files:
 * <pre>
 *  E:\\nju\\2014���챭\\LHGroup\\MATLABbyFXY\\open.m
 * </pre>
 * The {@link #dispose} method <b>must</b> be called on a <code>Open</code> instance when 
 * it is no longer needed to ensure that native resources allocated by this class are 
 * properly freed.
 * @version 0.0
 */
public class Open extends MWComponentInstance<Open>
{
    /**
     * Tracks all instances of this class to ensure their dispose method is
     * called on shutdown.
     */
    private static final Set<Disposable> sInstances = new HashSet<Disposable>();

    /**
     * Maintains information used in calling the <code>open</code> M-function.
     */
    private static final MWFunctionSignature sOpenSignature =
        new MWFunctionSignature(/* max outputs = */ 3,
                                /* has varargout = */ false,
                                /* function name = */ "open",
                                /* max inputs = */ 7,
                                /* has varargin = */ false);

    /**
     * Shared initialization implementation - private
     */
    private Open (final MWMCR mcr) throws MWException
    {
        super(mcr);
        // add this to sInstances
        synchronized(Open.class) {
            sInstances.add(this);
        }
    }

    /**
     * Constructs a new instance of the <code>Open</code> class.
     */
    public Open() throws MWException
    {
        this(Arbitrage_MainSharedMCRFactory.newInstance());
    }
    
    private static MWComponentOptions getPathToComponentOptions(String path)
    {
        MWComponentOptions options = new MWComponentOptions(new MWCtfExtractLocation(path),
                                                            new MWCtfDirectorySource(path));
        return options;
    }
    
    /**
     * @deprecated Please use the constructor {@link #Open(MWComponentOptions componentOptions)}.
     * The <code>com.mathworks.toolbox.javabuilder.MWComponentOptions</code> class provides API to set the
     * path to the component.
     * @param pathToComponent Path to component directory.
     */
    public Open(String pathToComponent) throws MWException
    {
        this(Arbitrage_MainSharedMCRFactory.newInstance(getPathToComponentOptions(pathToComponent)));
    }
    
    /**
     * Constructs a new instance of the <code>Open</code> class. Use this constructor to 
     * specify the options required to instantiate this component.  The options will be 
     * specific to the instance of this component being created.
     * @param componentOptions Options specific to the component.
     */
    public Open(MWComponentOptions componentOptions) throws MWException
    {
        this(Arbitrage_MainSharedMCRFactory.newInstance(componentOptions));
    }
    
    /** Frees native resources associated with this object */
    public void dispose()
    {
        try {
            super.dispose();
        } finally {
            synchronized(Open.class) {
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
            MWMCR mcr = Arbitrage_MainSharedMCRFactory.newInstance();
            mcr.runMain( sOpenSignature, args);
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
        synchronized(Open.class) {
            for (Disposable i : sInstances) i.dispose();
            sInstances.clear();
        }
    }

    /**
     * Provides the interface for calling the <code>open</code> M-function 
     * where the first input, an instance of List, receives the output of the M-function and
     * the second input, also an instance of List, provides the input to the M-function.
     * <p>M-documentation as provided by the author of the M function:
     * <pre>
     * %l_signal��ǰ����״̬��״̬��ȡ-2��-1,0,1,2��
     * % �����signal���յ�ǰ�����¼۸��Ƿ�Ӧ�����ס�signal=1�������������֣�
     * %signal=-1�������������֣�signal=0�������ף�signal=2��ֹӯƽ�֣�signal=-2,ֹ
     * %��ƽ�֡�
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
    public void open(List lhs, List rhs) throws MWException
    {
        fMCR.invoke(lhs, rhs, sOpenSignature);
    }

    /**
     * Provides the interface for calling the <code>open</code> M-function 
     * where the first input, an Object array, receives the output of the M-function and
     * the second input, also an Object array, provides the input to the M-function.
     * <p>M-documentation as provided by the author of the M function:
     * <pre>
     * %l_signal��ǰ����״̬��״̬��ȡ-2��-1,0,1,2��
     * % �����signal���յ�ǰ�����¼۸��Ƿ�Ӧ�����ס�signal=1�������������֣�
     * %signal=-1�������������֣�signal=0�������ף�signal=2��ֹӯƽ�֣�signal=-2,ֹ
     * %��ƽ�֡�
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
    public void open(Object[] lhs, Object[] rhs) throws MWException
    {
        fMCR.invoke(Arrays.asList(lhs), Arrays.asList(rhs), sOpenSignature);
    }

    /**
     * Provides the standard interface for calling the <code>open</code>
     * M-function with 7 input arguments.
     * Input arguments may be passed as sub-classes of
     * <code>com.mathworks.toolbox.javabuilder.MWArray</code>, or as arrays of
     * any supported Java type. Arguments passed as Java types are converted to
     * MATLAB arrays according to default conversion rules.
     *
     * <p>M-documentation as provided by the author of the M function:
     * <pre>
     * %l_signal��ǰ����״̬��״̬��ȡ-2��-1,0,1,2��
     * % �����signal���յ�ǰ�����¼۸��Ƿ�Ӧ�����ס�signal=1�������������֣�
     * %signal=-1�������������֣�signal=0�������ף�signal=2��ֹӯƽ�֣�signal=-2,ֹ
     * %��ƽ�֡�
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
    public Object[] open(int nargout, Object... rhs) throws MWException
    {
        Object[] lhs = new Object[nargout];
        fMCR.invoke(Arrays.asList(lhs), 
                    MWMCR.getRhsCompat(rhs, sOpenSignature), 
                    sOpenSignature);
        return lhs;
    }
}
