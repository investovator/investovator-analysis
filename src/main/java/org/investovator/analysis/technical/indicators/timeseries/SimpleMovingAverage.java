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
import org.apache.commons.lang.ArrayUtils;
import org.investovator.analysis.exceptions.AnalysisException;
import org.investovator.analysis.exceptions.InvalidParamException;
import org.investovator.analysis.technical.indicators.Indicator;
import org.investovator.analysis.technical.indicators.timeseries.utils.TimeSeriesParams;
import org.investovator.analysis.technical.utils.Params;
import org.investovator.analysis.technical.utils.ResultsSet;
import org.investovator.core.data.api.CompanyStockTransactionsData;
import org.investovator.core.data.api.CompanyStockTransactionsDataImpl;
import org.investovator.core.data.api.utils.StockTradingData;
import org.investovator.core.data.api.utils.TradingDataAttribute;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * @author rajith
 * @version ${Revision}
 */
public class SimpleMovingAverage implements Indicator {


    /**
     *
     * {@inheritDoc}
     */
    @Override
    public ResultsSet calculate(Params parameters) throws InvalidParamException, AnalysisException {
        TimeSeriesParams timeSeriesParams = (TimeSeriesParams) parameters;

        if(isParametersValid(timeSeriesParams)){
            CompanyStockTransactionsData transactionsData = new CompanyStockTransactionsDataImpl();
            ArrayList<TradingDataAttribute> attributes = new ArrayList<TradingDataAttribute >();
            attributes.add(TradingDataAttribute.CLOSING_PRICE);
            try {
                StockTradingData stockTradingData = transactionsData
                        .getTradingData(CompanyStockTransactionsData.DataType.OHLC,
                                timeSeriesParams.getStockId(), timeSeriesParams.getStartDate(),
                                timeSeriesParams.getEndDate(), Integer.MAX_VALUE, attributes);

                HashMap<Date, HashMap<TradingDataAttribute, String>> data = stockTradingData.getTradingData();

                ArrayList<Double> closePrice = new ArrayList<>();
                double[] out = new double[data.size()];

                for( HashMap<TradingDataAttribute, String> dataEntry : data.values()){
                    closePrice.add(Double.valueOf(dataEntry.get(TradingDataAttribute.CLOSING_PRICE)));
                }

                MInteger begin = new MInteger();
                MInteger length = new MInteger();

                double [] closingPrices  = ArrayUtils
                        .toPrimitive(closePrice.toArray(new Double[closePrice.size()]));
                Core core = new Core();
                RetCode retCode = core.sma(0, closePrice.size()-1, closingPrices,
                        timeSeriesParams.getPeriodAverage(), begin, length, out);

                if(retCode == RetCode.Success){

                }
                return null;  //ToDo
            } catch (Exception e) {
                throw new AnalysisException(e);
            }
        } else {
            throw new InvalidParamException();
        }

    }

    private boolean isParametersValid(TimeSeriesParams params){
         return params.getStockId()!=null && params.getStartDate()!=null
                 && params.getEndDate()!=null && params.getEndDate().after(params.getStartDate());
    }
}
