/*
 * MATLAB Compiler: 4.18.1 (R2013a)
 * Date: Wed Oct 29 09:12:12 2014
 * Arguments: "-B" "macro_default" "-W" "java:Arbitrage_Main,Arbitrage_Main" "-T" 
 * "link:lib" "-d" "E:\\nju\\2014»¨Æì±­\\fxy\\Arbitrage_Main\\src" "-w" 
 * "enable:specified_file_mismatch" "-w" "enable:repeated_file" "-w" 
 * "enable:switch_ignored" "-w" "enable:missing_lib_sentinel" "-w" "enable:demo_license" 
 * "-v" "class{Arbitrage_Main:E:\\nju\\2014»¨Æì±­\\fxy\\Arbitrage_Main.m}" 
 * "class{Open:E:\\nju\\2014»¨Æì±­\\fxy\\open.m}" 
 * "class{Close:E:\\nju\\2014»¨Æì±­\\fxy\\close.m}" "-a" 
 * "E:\\nju\\2014»¨Æì±­\\fxy\\Arbitrage_Main.m" "-a" 
 * "E:\\nju\\2014»¨Æì±­\\fxy\\arbitrage_return.m" "-a" 
 * "E:\\nju\\2014»¨Æì±­\\fxy\\bias_ratio.m" "-a" "E:\\nju\\2014»¨Æì±­\\fxy\\close.m" "-a" 
 * "E:\\nju\\2014»¨Æì±­\\fxy\\max_occupying_fund.m" "-a" 
 * "E:\\nju\\2014»¨Æì±­\\fxy\\open.m" 
 */

package Arbitrage_Main;

import com.mathworks.toolbox.javabuilder.*;
import com.mathworks.toolbox.javabuilder.internal.*;

/**
 * <i>INTERNAL USE ONLY</i>
 */
public class Arbitrage_MainMCRFactory
{
   
    
    /** Component's uuid */
    private static final String sComponentId = "Arbitrage_Ma_0230E73AD0B3C5B81EEE91E9820F169E";
    
    /** Component name */
    private static final String sComponentName = "Arbitrage_Main";
    
   
    /** Pointer to default component options */
    private static final MWComponentOptions sDefaultComponentOptions = 
        new MWComponentOptions(
            MWCtfExtractLocation.EXTRACT_TO_CACHE, 
            new MWCtfClassLoaderSource(Arbitrage_MainMCRFactory.class)
        );
    
    
    private Arbitrage_MainMCRFactory()
    {
        // Never called.
    }
    
    public static MWMCR newInstance(MWComponentOptions componentOptions) throws MWException
    {
        if (null == componentOptions.getCtfSource()) {
            componentOptions = new MWComponentOptions(componentOptions);
            componentOptions.setCtfSource(sDefaultComponentOptions.getCtfSource());
        }
        return MWMCR.newInstance(
            componentOptions, 
            Arbitrage_MainMCRFactory.class, 
            sComponentName, 
            sComponentId,
            new int[]{8,1,0}
        );
    }
    
    public static MWMCR newInstance() throws MWException
    {
        return newInstance(sDefaultComponentOptions);
    }
}
