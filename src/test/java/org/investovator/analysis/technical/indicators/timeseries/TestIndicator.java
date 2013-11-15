package org.investovator.analysis.technical.indicators.timeseries;

import org.apache.cassandra.exceptions.ConfigurationException;
import org.apache.thrift.transport.TTransportException;
import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import org.investovator.core.commons.configuration.ConfigLoader;
import org.investovator.core.data.cassandraexplorer.CassandraManager;
import org.investovator.core.data.cassandraexplorer.CassandraManagerImpl;
import org.investovator.core.data.exeptions.DataAccessException;
import org.junit.After;
import org.junit.Before;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author rajith
 * @version ${Revision}
 */
public class TestIndicator {

    private static final String OHLC_DATE_FORMAT = "MM/dd/yyyy";
    private static String RESOURCE_DIR_PATH = "src" + File.separator + "test" + File.separator
            + "resources" + File.separator + "core" + File.separator + "artifacts" + File.separator;

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
