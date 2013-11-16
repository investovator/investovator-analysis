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

package org.investovator.analysis.utils;

import org.apache.commons.lang.ArrayUtils;
import org.investovator.analysis.technical.indicators.timeseries.utils.TimeSeriesParams;
import org.investovator.core.data.api.CompanyStockTransactionsData;
import org.investovator.core.data.api.CompanyStockTransactionsDataImpl;
import org.investovator.core.data.api.utils.StockTradingData;
import org.investovator.core.data.api.utils.TradingDataAttribute;
import org.investovator.core.data.exeptions.DataAccessException;
import org.investovator.core.data.exeptions.DataNotFoundException;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * @author rajith
 * @version ${Revision}
 */
public class DataUtils {

    public static HashMap<Date, HashMap<TradingDataAttribute, String>> getDataValues(TimeSeriesParams timeSeriesParams,
                                                                                     ArrayList<TradingDataAttribute> attributes)
            throws DataNotFoundException, DataAccessException {
        CompanyStockTransactionsData transactionsData = new CompanyStockTransactionsDataImpl();

        StockTradingData stockTradingData = transactionsData.getTradingData(CompanyStockTransactionsData.DataType.OHLC,
                timeSeriesParams.getStockId(), timeSeriesParams.getStartDate(), timeSeriesParams.getEndDate(),
                Integer.MAX_VALUE, attributes);

        return stockTradingData.getTradingData();
    }

    public static double [] getPriceToDoubles(HashMap<Date, HashMap<TradingDataAttribute, String>> data,
                                              TradingDataAttribute attribute){
        ArrayList<Double> closePrice = new ArrayList<>();
        for(HashMap<TradingDataAttribute, String> dataEntry : data.values()){
            closePrice.add(Double.valueOf(dataEntry.get(attribute)));
        }

        return ArrayUtils.toPrimitive(closePrice.toArray(new Double[closePrice.size()]));
    }

    public static Date[] getDatesToArray(HashMap<Date, HashMap<TradingDataAttribute, String>> data) {
        return (data.keySet()).toArray(new Date[(data.keySet()).size()]);
    }
}
