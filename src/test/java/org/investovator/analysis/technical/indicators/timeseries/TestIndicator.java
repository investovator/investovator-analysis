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

    protected static final String OHLC_DATE_FORMAT = "MM/dd/yyyy";
    protected static String RESOURCE_DIR_PATH = "src" + File.separator + "test" + File.separator
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
