/*
 * investovator, Stock Market Gaming framework
 * Copyright (C) 2013  investovator
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.investovator.analysis.technical.indicators.timeseries;

import org.apache.cassandra.exceptions.ConfigurationException;
import org.apache.thrift.transport.TTransportException;
import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import org.investovator.analysis.exceptions.AnalysisException;
import org.investovator.analysis.exceptions.InvalidParamException;
import org.investovator.analysis.technical.Calculator;
import org.investovator.analysis.technical.CalculatorImpl;
import org.investovator.analysis.technical.indicators.timeseries.utils.TimeSeriesGraph;
import org.investovator.analysis.technical.indicators.timeseries.utils.TimeSeriesParams;
import org.investovator.analysis.technical.indicators.timeseries.utils.TimeSeriesResultSet;
import org.investovator.analysis.technical.utils.IndicatorType;
import org.investovator.core.commons.configuration.ConfigLoader;
import org.investovator.core.data.cassandraexplorer.CassandraManager;
import org.investovator.core.data.cassandraexplorer.CassandraManagerImpl;
import org.investovator.core.data.exeptions.DataAccessException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author rajith
 * @version ${Revision}
 */
public class TestSMA {

    private static final String OHLC_DATE_FORMAT = "MM/dd/yyyy";
    private static String RESOURCE_DIR_PATH = "src" + File.separator + "test" + File.separator
            + "resources" + File.separator + "core" + File.separator + "artifacts" + File.separator;

    @Test
    public void testSMACalculate() throws AnalysisException, ParseException, InvalidParamException {
        Calculator calculator = new CalculatorImpl();
        String staringDate = "1/4/2010";
        String endDate = "3/30/2010";
        SimpleDateFormat format = new SimpleDateFormat(OHLC_DATE_FORMAT);

        TimeSeriesParams params = new TimeSeriesParams("SAMP", format.parse(staringDate), format.parse(endDate));
        params.setPeriodAverage(5);

        TimeSeriesResultSet resultSet = (TimeSeriesResultSet) calculator.calculateValues(IndicatorType.SMA, params);
        assertTrue(resultSet.containsGraph(TimeSeriesGraph.ORIGINAL));
        assertTrue(resultSet.containsGraph(TimeSeriesGraph.SIMPLE_AVERAGE));

        String randomDate = "2/15/2010";
        assertEquals((resultSet.getGraph(TimeSeriesGraph.SIMPLE_AVERAGE)).get(format.parse(randomDate)), 218.0);
    }

    @Before
    public void setEnvironment() throws InterruptedException, TTransportException, ConfigurationException,
            IOException, org.apache.commons.configuration.ConfigurationException, DataAccessException {
        ConfigLoader.loadProperties(RESOURCE_DIR_PATH + "resource.properties");
        EmbeddedCassandraServerHelper.startEmbeddedCassandra();

        String columnFamily = "ohlc_data";
        String rowKey = "SAMP";

        File file = new File(RESOURCE_DIR_PATH + "sampath_daily_test.csv");

        CassandraManager cassandraManager = CassandraManagerImpl.getCassandraManager();
        cassandraManager
                .importCSV(columnFamily, rowKey, OHLC_DATE_FORMAT, new FileInputStream(file));
    }

    @After
    public void destroy(){
        EmbeddedCassandraServerHelper.cleanEmbeddedCassandra();
    }
}
