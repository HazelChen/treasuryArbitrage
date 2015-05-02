package edu.nju.treasuryArbitrage.contriller.dataInterface;

import edu.nju.treasuryArbitrage.controller.dataInterface.DataInterface;
import edu.nju.treasuryArbitrage.controller.dataInterface.DataInterfaceFactory;
import edu.nju.treasuryArbitrage.model.calculation.Lambda;
import edu.nju.treasuryArbitrage.model.calculation.OptimalKT;
import edu.nju.treasuryArbitrage.model.calculation.Xyz;
import org.junit.Test;

/**
 * Created by hazel on 2015-05-02.
 */
public class TestDataInterface {

    @Test
    public void testCalculationModel() {
        DataInterface dataInterface = DataInterfaceFactory.getInstance()
                .getDataInterfaceToServer();
        Xyz xyzGroup1 = dataInterface.getTodayXyz(1);
        System.out.println("xyz for group 1:");
        System.out.println(xyzGroup1);

        Xyz xyzGroup2 = dataInterface.getTodayXyz(2);
        System.out.println("xyz for group 2:");
        System.out.println(xyzGroup2);

        Xyz xyzGroup3 = dataInterface.getTodayXyz(3);
        System.out.println("xyz for group 3:");
        System.out.println(xyzGroup3);

        Lambda lambdaGroup1 = dataInterface.getTodayLambda(1);
        System.out.println("lambda for group 1:");
        System.out.println(lambdaGroup1);

        Lambda lambdaGroup2 = dataInterface.getTodayLambda(2);
        System.out.println("lambda for group 2:");
        System.out.println(lambdaGroup2);

        Lambda lambdaGroup3 = dataInterface.getTodayLambda(3);
        System.out.println("lambda for group 3:");
        System.out.println(lambdaGroup3);

        OptimalKT optimalKT1 = dataInterface.getTodayKt(1);
        System.out.println("optimalKT for group 1:");
        System.out.println(optimalKT1);

        OptimalKT optimalKT2 = dataInterface.getTodayKt(2);
        System.out.println("optimalKT for group 2:");
        System.out.println(optimalKT2);

        OptimalKT optimalKT3 = dataInterface.getTodayKt(3);
        System.out.println("optimalKT for group 3:");
        System.out.println(optimalKT3);

    }
}
