/*
 * This code is developed in Institut Laue-Langevin (France).
 * Its goal is the implementation of parameter search into ICAT Web Service
 * 
 * Created on 8 juil. 2010
 */

package uk.icat3.parametersearch;

import java.util.ArrayList;
import static org.junit.Assert.*;
import java.util.Date;
import java.util.List;
import junit.framework.JUnit4TestAdapter;
import uk.icat3.exceptions.ParameterSearchException;
import org.junit.Test;
import uk.icat3.entity.Datafile;
import uk.icat3.entity.Parameter;
import uk.icat3.entity.ParameterPK;
import uk.icat3.search.DatafileSearch;
import uk.icat3.search.parameter.ComparisonOperator;
import uk.icat3.search.parameter.ParameterComparisonCondition;
import uk.icat3.search.parameter.ParameterLogicalCondition;
import uk.icat3.search.parameter.ParameterType;
import uk.icat3.search.parameter.util.ParameterSearch;
import uk.icat3.util.LogicalOperator;

/**
 * This class show some examples of search
 * 
 * @author cruzcruz
 */
public class UsesExamples extends BaseParameterSearchTest {

    /**
     * Test between example
     * 
     * @throws ParameterSearchException
     */
    @Test
    public void comparisonExample () throws ParameterSearchException {

        // Get the parameter, manually or get from a service
        Parameter dateTime = new Parameter(new ParameterPK("yyyy-MM-dd HH:mm:ss", "time1"));
        // Create the parameter to compare with. Two argument: type of the parameter
        //  to compare and the parameter.
        ParameterSearch pamVal = new ParameterSearch(ParameterType.DATAFILE, dateTime);
        // Create the comparasion
        ParameterComparisonCondition comp1 = new ParameterComparisonCondition();
        // Add the parameterValued
        comp1.setParameterValued(pamVal);
        // Add the comparator
        comp1.setComparator(ComparisonOperator.BETWEEN);
        // Add the value to compare
        comp1.setDatetimeValue(new Date(0));
        // Add a second value if needed (only for BETWEEN)
        comp1.setDatetimeValueRight("2010-10-10 00:00:00");

        List<Datafile> ld = (List<Datafile>) DatafileSearch
                .searchByParameterCondition(VALID_USER_FOR_INVESTIGATION, comp1, em);

        assertTrue("Results of investigations should be 1 not " + ld.size(), (ld.size() == 1));
    }

    @Test
    public void parameterExample () throws ParameterSearchException {
        // List of parameter searchs
        List<ParameterSearch> lp = new ArrayList<ParameterSearch>();
        // Get the parameters manually or from a service
        Parameter voltage = new Parameter("V", "voltage");
        Parameter dateTime = new Parameter("yyyy-MM-dd HH:mm:ss", "time1");
        // Create the parameter searchs.
        ParameterSearch psSample = new ParameterSearch(ParameterType.SAMPLE, voltage);
        ParameterSearch psDatafile = new ParameterSearch(ParameterType.DATAFILE, dateTime);
        // Add to a list
        lp.add(psSample);
        lp.add(psDatafile);

        List<Datafile> li = (List<Datafile>) DatafileSearch
            .searchByParameterList(VALID_USER_FOR_INVESTIGATION, lp, 1, -1, em);

        assertTrue("Results of investigations should not be ZERO", (li.size() == 1));
    }

    @Test
    public void comparisonConditionExample () throws ParameterSearchException {

        // Get the parameters manually or from a service
        Parameter voltage = new Parameter("V", "voltage");
        Parameter string = new Parameter("str", "string1");
        Parameter dateTime = new Parameter("yyyy-MM-dd HH:mm:ss", "time1");
        // Create comparisons
        // voltage = 55.55
        ParameterComparisonCondition compVoltageSample =
                new ParameterComparisonCondition(
                        new ParameterSearch(ParameterType.SAMPLE, voltage),
                        ComparisonOperator.EQUAL,
                        new Double(55.55));
        // string like '%21%'
        ParameterComparisonCondition compStringDatafile =
                 new ParameterComparisonCondition(
                        new ParameterSearch(ParameterType.DATAFILE, string),
                        ComparisonOperator.CONTAIN, "number");
        // dateTime > Date(0)
        ParameterComparisonCondition compDateTimeDatafile =
                new ParameterComparisonCondition(
                        new ParameterSearch(ParameterType.DATAFILE, dateTime),
                        ComparisonOperator.GREATER_THAN,
                        new Date(0));
        // Create logical condition
        ParameterLogicalCondition op1 = new ParameterLogicalCondition(LogicalOperator.OR);
        ParameterLogicalCondition op2 = new ParameterLogicalCondition(LogicalOperator.AND);
        // Create the structure to compare.
        // compDataTimeDatafile OR (compVoltageSample AND compStringDatafile)
        op2.add(compVoltageSample);
        op2.add(compStringDatafile);
        op1.add(compDateTimeDatafile);
        op1.add(op2);

        List<Datafile> li = (List<Datafile>) DatafileSearch
                .searchByParameterCondition(VALID_USER_FOR_INVESTIGATION, op1, 1, -1, em);

        assertTrue("Results of investigations should be 1 not " + li.size(), (li.size() == 1));
    }

    /**
     * Test datetime parameter search
     *
     * @throws ParameterSearchException
     */
    @Test
    public void dateTime () throws ParameterSearchException {
        // Get the parameter, manually or get from a service
        Parameter datfile = new Parameter(new ParameterPK("yyyy-MM-dd HH:mm:ss", "time1"));

        // Create the parameter to compare with. Two argument: type of the parameter
        //  to compare and the parameter. (Try to find a better name to parameterValued)
        ParameterSearch pamVal = new ParameterSearch(ParameterType.DATAFILE, datfile);

        // Create the comparasion
        ParameterComparisonCondition comp1 = new ParameterComparisonCondition();

        // Add the parameterValued
        comp1.setParameterValued(pamVal);
        // Add the comparator
        comp1.setComparator(ComparisonOperator.LESS_EQUAL);
        // Add the value to compare
        comp1.setDatetimeValue(new Date());
        // Add a second value if needed (only for BETWEEN)
//        comp1.setValueRight(new Double (4));

        List<Datafile> ld = (List<Datafile>) DatafileSearch
                .searchByParameterCondition(VALID_USER_FOR_INVESTIGATION, comp1, em);

        assertTrue("Results of investigations should be 1 not " + ld.size(), (ld.size() == 1));
    }

    /**
     * Test stringdatetime parameter test
     * 
     * @throws ParameterSearchException
     */
    @Test
    public void stringDateTime () throws ParameterSearchException {
        // Get the parameter, manually or get from a service
        Parameter datfile = new Parameter(new ParameterPK("yyyy-MM-dd HH:mm:ss", "time1"));

        // Create the parameter to compare with. Two argument: type of the parameter
        //  to compare and the parameter. (Try to find a better name to parameterValued)
        ParameterSearch pamVal = new ParameterSearch(ParameterType.DATAFILE, datfile);

        // Create the comparasion
        ParameterComparisonCondition comp1 = new ParameterComparisonCondition();

        // Add the parameterValued
        comp1.setParameterValued(pamVal);
        // Add the comparator
        comp1.setComparator(ComparisonOperator.LESS_EQUAL);
        // Add the value to compare
        comp1.setDatetimeValue("2010-02-22 00:00:00");
        // Add a second value if needed (only for BETWEEN)
//        comp1.setValueRight(new Double (4));

        List<Datafile> ld = (List<Datafile>) DatafileSearch
                .searchByParameterCondition(VALID_USER_FOR_INVESTIGATION, comp1, em);

        assertTrue("Results of investigations should be 1 not " + ld.size(), (ld.size() == 1));
    }

    /**
     * Test string value parameter search
     * 
     * @throws ParameterSearchException
     */
    @Test
    public void stringValue () throws ParameterSearchException {
        // Get the parameter manually or get from a service
        Parameter datfile = new Parameter(new ParameterPK("str", "string1"));

        // Create the parameter to compare with. Two argument: type of the parameter
        //  to compare and the parameter. (Try to find a better name to parameterValued)
        ParameterSearch pamVal = new ParameterSearch(ParameterType.DATAFILE, datfile);

        // Create the comparasion
        ParameterComparisonCondition comp1 = new ParameterComparisonCondition();

        // Add the parameterValued
        comp1.setParameterValued(pamVal);
        // Add the comparator
        comp1.setComparator(ComparisonOperator.CONTAIN);
        // Add the value to compare
        comp1.setStringValue("number");
        // Add a second value if needed (only for BETWEEN)
//        comp1.setValueRight(new Double (4));

        List<Datafile> ld = (List<Datafile>) DatafileSearch
                .searchByParameterCondition(VALID_USER_FOR_INVESTIGATION, comp1, em);

        assertTrue("Results of investigations should be 1 not " + ld.size(), (ld.size() == 1));
    }

    /**
     * Test numericValue parameter search
     * 
     * @throws ParameterSearchException
     */
    @Test
    public void numericValue () throws ParameterSearchException {
        // Get the parameter manually or get from a service
        Parameter datfile = new Parameter(new ParameterPK("str", "string1"));

        // Create the parameter to compare with. Two argument: type of the parameter
        //  to compare and the parameter. (Try to find a better name to parameterValued)
        ParameterSearch pamVal = new ParameterSearch(ParameterType.DATAFILE, datfile);

        // Create the comparasion
        ParameterComparisonCondition comp1 = new ParameterComparisonCondition();

        // Add the parameterValued
        comp1.setParameterValued(pamVal);
        // Add the comparator
        comp1.setComparator(ComparisonOperator.CONTAIN);
        // Add the value to compare
        comp1.setNumericValue(21);
        // Add a second value if needed (only for BETWEEN)
//        comp1.setValueRight(new Double (4));

        List<Datafile> ld = (List<Datafile>) DatafileSearch
                .searchByParameterCondition(VALID_USER_FOR_INVESTIGATION, comp1, em);

        assertTrue("Results of investigations should be 1 not " + ld.size(), (ld.size() == 1));
    }

    public static junit.framework.Test suite(){
        return new JUnit4TestAdapter(UsesExamples.class);
    }
}