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

import com.tictactec.ta.lib.Core;
import com.tictactec.ta.lib.MInteger;
import com.tictactec.ta.lib.RetCode;
import org.investovator.analysis.exceptions.AnalysisException;
import org.investovator.analysis.exceptions.InvalidParamException;
import org.investovator.analysis.technical.indicators.timeseries.utils.TimeSeriesGraph;
import org.investovator.analysis.technical.indicators.timeseries.utils.TimeSeriesParams;
import org.investovator.analysis.technical.indicators.timeseries.utils.TimeSeriesResultSet;
import org.investovator.analysis.technical.utils.Params;
import org.investovator.analysis.technical.utils.ResultsSet;
import org.investovator.analysis.utils.DataUtils;
import org.investovator.core.data.api.utils.TradingDataAttribute;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * @author rajith
 * @version ${Revision}
 */
public class SimpleMovingAverage extends TimeSeriesIndicator {


    /**
     *
     * {@inheritDoc}
     */
    @Override
    public ResultsSet calculate(Params parameters) throws InvalidParamException, AnalysisException {
        TimeSeriesParams timeSeriesParams = (TimeSeriesParams) parameters;

        if(isParametersValid(timeSeriesParams)){
            try {
                ArrayList <TradingDataAttribute> attributes = new ArrayList<>();
                attributes.add(TradingDataAttribute.CLOSING_PRICE);
                HashMap<Date, HashMap<TradingDataAttribute, String>> data = DataUtils
                        .getDataValues(timeSeriesParams, attributes);
                double[] closingPrices = DataUtils.getPriceToDoubles(data);
                Date[] dates = DataUtils.getDatesToArray(data);

                MInteger begin = new MInteger();
                MInteger length = new MInteger();
                double[] out = new double[closingPrices.length];

                Core core = new Core();
                RetCode retCode = core.sma(0, (closingPrices.length - 1), closingPrices,
                        timeSeriesParams.getPeriodAverage(), begin, length, out);

                if(retCode == RetCode.Success){
                    TimeSeriesResultSet resultSet = new TimeSeriesResultSet(timeSeriesParams.getStockId());

                    resultSet.setGraph(TimeSeriesGraph.ORIGINAL, dates, closingPrices, 0, closingPrices.length);
                    resultSet.setGraph(TimeSeriesGraph.SIMPLE_AVERAGE, dates, out, begin.value, length.value);
                    return resultSet;
                } else {
                    throw new AnalysisException(retCode.toString());
                }
            } catch (Exception e) {
                throw new AnalysisException(e);
            }
        } else {
            throw new InvalidParamException();
        }

    }

}
