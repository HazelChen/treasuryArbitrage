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

import com.mathworks.toolbox.javabuilder.*;
import com.mathworks.toolbox.javabuilder.internal.*;

/**
 * <i>INTERNAL USE ONLY</i>
 */
public class Arbitrage_MainMCRFactory
{
   
    
    /** Component's uuid */
    private static final String sComponentId = "Arbitrage_Ma_B57B426849620A6702B001EA69F1C67F";
    
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
