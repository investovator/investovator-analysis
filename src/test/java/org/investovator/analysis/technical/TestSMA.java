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

package org.investovator.analysis.technical;

import junit.framework.TestCase;
import org.investovator.analysis.exceptions.AnalysisException;
import org.investovator.analysis.exceptions.InvalidParamException;
import org.investovator.analysis.technical.indicators.timeseries.utils.TimeSeriesParams;
import org.investovator.analysis.technical.utils.IndicatorType;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author rajith
 * @version ${Revision}
 */
public class TestSMA extends TestCase {

    private static final String OHLC_DATE_FORMAT = "MM/dd/yyyy";

    public void testSMACalculate() throws AnalysisException, ParseException, InvalidParamException {
        Calculator calculator = new CalculatorImpl();
        String staringDate = "1/4/2010";
        String endDate = "12/31/2012";
        SimpleDateFormat format = new SimpleDateFormat(OHLC_DATE_FORMAT);

        TimeSeriesParams params = new TimeSeriesParams("SAMP", format.parse(staringDate), format.parse(endDate));

        calculator.calculateValues(IndicatorType.SMA, params);
    }
}
