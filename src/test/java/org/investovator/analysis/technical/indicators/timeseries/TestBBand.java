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

import org.investovator.analysis.exceptions.AnalysisException;
import org.investovator.analysis.exceptions.InvalidParamException;
import org.investovator.analysis.technical.Calculator;
import org.investovator.analysis.technical.CalculatorImpl;
import org.investovator.analysis.technical.indicators.timeseries.utils.TimeSeriesGraph;
import org.investovator.analysis.technical.indicators.timeseries.utils.TimeSeriesParams;
import org.investovator.analysis.technical.indicators.timeseries.utils.TimeSeriesResultSet;
import org.investovator.analysis.technical.utils.IndicatorType;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author rajith
 * @version ${Revision}
 */
public class TestBBand extends TestIndicator {

    @Test
    public void testBBandCalculate() throws AnalysisException, ParseException, InvalidParamException {
        Calculator calculator = new CalculatorImpl();
        String staringDate = "1/4/2010";
        String endDate = "3/30/2010";
        SimpleDateFormat format = new SimpleDateFormat(OHLC_DATE_FORMAT);

        TimeSeriesParams params = new TimeSeriesParams("SAMP", format.parse(staringDate), format.parse(endDate));
        params.setPeriod(14);
        params.setDevMultiUp(2.0);
        params.setDevMultiDown(2.0);
        params.setbBandMAType(TimeSeriesParams.BBandMAType.SMA);

        TimeSeriesResultSet resultSet = (TimeSeriesResultSet) calculator.calculateValues(IndicatorType.BBAND, params);
        assertTrue(resultSet.containsGraph(TimeSeriesGraph.ORIGINAL));
        assertTrue(resultSet.containsGraph(TimeSeriesGraph.BBAND_UPPER));
        assertTrue(resultSet.containsGraph(TimeSeriesGraph.BBAND_MIDDLE));
        assertTrue(resultSet.containsGraph(TimeSeriesGraph.BBAND_LOWER));

        String randomDate = "2/15/2010";
        assertEquals((resultSet.getGraph(TimeSeriesGraph.BBAND_UPPER)).get(format.parse(randomDate)), 231.51869084738541);
        assertEquals((resultSet.getGraph(TimeSeriesGraph.BBAND_MIDDLE)).get(format.parse(randomDate)), 221.0);
        assertEquals((resultSet.getGraph(TimeSeriesGraph.BBAND_LOWER)).get(format.parse(randomDate)), 210.48130915261459);
    }

}
