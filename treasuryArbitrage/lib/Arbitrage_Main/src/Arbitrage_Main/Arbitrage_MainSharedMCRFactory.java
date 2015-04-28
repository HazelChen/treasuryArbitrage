/*
 * MATLAB Compiler: 4.18.1 (R2013a)
 * Date: Tue Apr 28 10:04:28 2015
 * Arguments: "-B" "macro_default" "-W" "java:Arbitrage_Main,Open" "-T" "link:lib" "-d" 
 * "E:\\nju\\2014»¨Æì±­\\LHGroup\\NewTA\\treasuryArbitrage\\treasuryArbitrage\\lib\\Arbitrage_Main\\src" 
 * "-w" "enable:specified_file_mismatch" "-w" "enable:repeated_file" "-w" 
 * "enable:switch_ignored" "-w" "enable:missing_lib_sentinel" "-w" "enable:demo_license" 
 * "-S" "-v" "class{Open:E:\\nju\\2014»¨Æì±­\\LHGroup\\MATLABbyFXY\\open.m}" 
 * "class{Close:E:\\nju\\2014»¨Æì±­\\LHGroup\\MATLABbyFXY\\close.m}" 
 * "class{Open1:E:\\nju\\2014»¨Æì±­\\LHGroup\\OC by WXY\\open1.m}" 
 * "class{Close1:E:\\nju\\2014»¨Æì±­\\LHGroup\\OC by WXY\\close1.m}" 
 * "class{Open2:E:\\nju\\2014»¨Æì±­\\LHGroup\\OC by DJ\\open2.m}" 
 * "class{Close2:E:\\nju\\2014»¨Æì±­\\LHGroup\\OC by DJ\\close2.m}" 
 * "class{Arbitrage_Main:E:\\nju\\2014»¨Æì±­\\LHGroup\\MATLABbyFXY\\Arbitrage_Main.m}" 
 * "class{Arbitrage_Main1:E:\\nju\\2014»¨Æì±­\\LHGroup\\OC by WXY\\Arbitrage_Main_W.m}" 
 * "class{Arbitrage_Main2:E:\\nju\\2014»¨Æì±­\\LHGroup\\OC by DJ\\Arbitrage_Main_D.m}" 
 * "-a" "E:\\nju\\2014»¨Æì±­\\LHGroup\\MATLABbyFXY\\Arbitrage_Main.m" "-a" 
 * "E:\\nju\\2014»¨Æì±­\\LHGroup\\OC by DJ\\Arbitrage_Main_D.m" "-a" 
 * "E:\\nju\\2014»¨Æì±­\\LHGroup\\OC by WXY\\Arbitrage_Main_W.m" "-a" 
 * "E:\\nju\\2014»¨Æì±­\\LHGroup\\MATLABbyFXY\\arbitrage_return.m" "-a" 
 * "E:\\nju\\2014»¨Æì±­\\LHGroup\\OC by WXY\\Arbitrage_Return_W.m" "-a" 
 * "E:\\nju\\2014»¨Æì±­\\LHGroup\\MATLABbyFXY\\bias_ratio.m" "-a" 
 * "E:\\nju\\2014»¨Æì±­\\fxy\\close.m" "-a" "E:\\nju\\2014»¨Æì±­\\LHGroup\\OC by 
 * WXY\\close1.m" "-a" "E:\\nju\\2014»¨Æì±­\\LHGroup\\OC by DJ\\close2.m" "-a" 
 * "E:\\nju\\2014»¨Æì±­\\WXYNew\\enumerate.m" "-a" 
 * "E:\\nju\\2014»¨Æì±­\\WXYNew\\hanglieshi.m" "-a" 
 * "E:\\nju\\2014»¨Æì±­\\WXYNew\\judge.m" "-a" "E:\\nju\\2014»¨Æì±­\\LHGroup\\OC by 
 * WXY\\linreg.m" "-a" "E:\\nju\\2014»¨Æì±­\\fxy\\max_occupying_fund.m" "-a" 
 * "E:\\nju\\2014»¨Æì±­\\LHGroup\\OC by DJ\\model2.m" "-a" 
 * "E:\\nju\\2014»¨Æì±­\\fxy\\mygetFlen.m" "-a" "E:\\nju\\2014»¨Æì±­\\WXYNew\\nijuzhen.m" 
 * "-a" "E:\\nju\\2014»¨Æì±­\\fxy\\open.m" "-a" "E:\\nju\\2014»¨Æì±­\\LHGroup\\OC by 
 * WXY\\open1.m" "-a" "E:\\nju\\2014»¨Æì±­\\LHGroup\\OC by DJ\\open2.m" "-a" 
 * "E:\\nju\\2014»¨Æì±­\\LHGroup\\MATLABbyFXY\\return_sum.m" 
 */

package Arbitrage_Main;

import java.util.concurrent.Callable;
import com.mathworks.toolbox.javabuilder.internal.MWMCR;
import com.mathworks.toolbox.javabuilder.MWException;
import com.mathworks.toolbox.javabuilder.MWComponentOptions;

/**
 * <i>INTERNAL USE ONLY</i>
 */
public class Arbitrage_MainSharedMCRFactory
{
    /// The singleton MWMCR instance for this component
    private static volatile MWMCR sSharedMCR = null;
    
    /// A shutdown task (Runnable) that disposes of the shared MWMCR instance
    // (initially do nothing, since sSharedMCR is lazily created)
    private static volatile Runnable sShutdownTask = new Runnable() { public void run () {} };
    
    private Arbitrage_MainSharedMCRFactory () {
        // Never called.
    }
    
    private static MWMCR getInstance (Callable<MWMCR> createInstance) throws MWException {
        synchronized(Arbitrage_MainSharedMCRFactory.class) {
            if (null == sSharedMCR) {
                try {
                    sSharedMCR = createInstance.call();
                } catch (Exception e) {
                    assert(e instanceof MWException);
                    throw (MWException)e;
                }                
                sShutdownTask = MWMCR.scheduleShutdownTask(new Runnable() {
                    public void run () {
                        synchronized(Arbitrage_MainSharedMCRFactory.class) {
                            assert( null != Arbitrage_MainSharedMCRFactory.sSharedMCR );
                            Arbitrage_MainSharedMCRFactory.sSharedMCR.dispose();
                            Arbitrage_MainSharedMCRFactory.sSharedMCR = null;
                        }
                    }
                });
            }
            sSharedMCR.use();
            return sSharedMCR;
        }
    }
    
    public static MWMCR newInstance () throws MWException {
        return getInstance(new Callable<MWMCR>() {
            public MWMCR call () throws Exception {
                return Arbitrage_MainMCRFactory.newInstance();
            }
        });
    }
    
    public static MWMCR newInstance (final MWComponentOptions componentOptions) throws MWException {
        return getInstance(new Callable<MWMCR>() {
            public MWMCR call () throws Exception {
                return Arbitrage_MainMCRFactory.newInstance(componentOptions);
            }
        });
    }

    /// Releases the shared MWMCR instance and cancels its associated shutdown task.  Subsequent calls to 
    ///  newInstance will create another MWMCR.  It is necessary to call this method if this class
    ///  is to be properly unloaded prior to JVM shutdown.  It is not necessary to call this method if 
    ///  this class does not need to be unloaded before normal JVM shutdown.
    public static void releaseSharedMCR () {
        sShutdownTask.run();
    }
}
