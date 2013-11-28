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

package org.investovator.analysis.signalgen.timeseries;

import org.investovator.analysis.signalgen.events.AnalysisEvent;
import org.investovator.analysis.signalgen.events.MarketClosedEvent;
import org.investovator.analysis.signalgen.timeseries.utils.SigGenTSParams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @author rajith
 * @version ${Revision}
 */
public class SigGenFromMA extends SigGenFromTS {

    private HashMap<String, List<Number>> closingPrices;
    private HashMap<String, Number> currentMAPPO;

    public SigGenFromMA(SigGenTSParams params) {
        super(params);
        this.closingPrices = new HashMap<>();
        this.currentMAPPO = new HashMap<>();
    }

    @Override
    public void eventOccurred(AnalysisEvent event) {
        if(event instanceof MarketClosedEvent){
            addToClosingPrices((MarketClosedEvent) event);

        }
    }

    private Number calculatePPO(){
        return 0; //TODO
    }

    private void addToClosingPrices(MarketClosedEvent event) {
        String stockId = event.getStockId();
        if(!closingPrices.containsKey(stockId)){
            List<Number> prices = new ArrayList<>();
            prices.add(event.getClosePrice());
            closingPrices.put(stockId, prices);
        } else {
            List<Number> prices = closingPrices.get(stockId);
            Collections.sort(prices, Collections.reverseOrder());

            if(prices.size() > params.getSlowPeriodAverage() - 1){
                closingPrices.put(stockId, prices.subList(0, (params.getSlowPeriodAverage() - 1)));
            } else
                closingPrices.put(stockId, prices);
        }
    }
}
