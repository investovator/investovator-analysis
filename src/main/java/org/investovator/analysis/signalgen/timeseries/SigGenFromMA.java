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

import com.tictactec.ta.lib.Core;
import com.tictactec.ta.lib.MAType;
import com.tictactec.ta.lib.MInteger;
import com.tictactec.ta.lib.RetCode;
import org.investovator.analysis.exceptions.AnalysisException;
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

    private HashMap<String, List<AnalysisEvent>> closingPrices;
    private HashMap<String, Number> currentMAPPO;
    private boolean initEventsAvailable;

    public SigGenFromMA(SigGenTSParams params) {
        super(params);
        initClosingPrices(params);
        this.currentMAPPO = new HashMap<>();
    }

    @Override
    public void eventOccurred(AnalysisEvent event) throws AnalysisException {
        if(event instanceof MarketClosedEvent){
            initEventCalculation();
            addToClosingPrices((MarketClosedEvent) event);
            updateStatus(event.getStockId());
        }
    }

    private void updateStatus(String stockId) throws AnalysisException {
        List<AnalysisEvent> prices = closingPrices.get(stockId);
        if(prices.size() < params.getSlowPeriod()){
            currentSecurityStatus.put(stockId, StockStatus.NA);
        } else {
            double[] priceArray = getDoubleArray(prices);
            double ppo = (double) calculate(priceArray);
            if(currentMAPPO.get(stockId) == null){
                currentSecurityStatus.put(stockId, StockStatus.NA);
            } else if((currentMAPPO.get(stockId).doubleValue() >= 0) && ppo < -2){
                currentSecurityStatus.put(stockId, StockStatus.SELL);
            } else if ((currentMAPPO.get(stockId).doubleValue() <= 0) && ppo > 2){
                currentSecurityStatus.put(stockId, StockStatus.BUY);
            } else {
                currentSecurityStatus.put(stockId, StockStatus.DO_NOTHING);
            }
            currentMAPPO.put(stockId, ppo);
        }
    }

    private double[] getDoubleArray(List<AnalysisEvent> prices) {
        double[] priceInDb = new double[prices.size()];

        for(int i = 0; i < prices.size(); i++){
            priceInDb[i] = ((MarketClosedEvent)prices.get(i)).getClosePrice();
        }
        return priceInDb;
    }

    private Number calculate(double [] prices) throws AnalysisException {
        Core core = new Core();
        MInteger begin = new MInteger();
        MInteger length = new MInteger();
        double[] out = new double[prices.length];
        MAType type = SigGenMAType.getMAType(params.getMaType());

        RetCode retCode = core.ppo(0, (prices.length - 1), prices, params.getQuickPeriod(),
                params.getSlowPeriod(), type, begin, length, out);

        if(retCode == RetCode.Success){
            return out[0];
        }  else {
            throw new AnalysisException(retCode.toString());
        }
    }

    private void addToClosingPrices(MarketClosedEvent event) {
        String stockId = event.getStockId();
        if(!closingPrices.containsKey(stockId)){
            List<AnalysisEvent> prices = new ArrayList<>();
            prices.add(event);
            closingPrices.put(stockId, prices);
        } else {
            List<AnalysisEvent> prices = closingPrices.get(stockId);
            prices.add(event);
            Collections.sort(prices, Collections.reverseOrder());

            if(prices.size() > params.getSlowPeriod() - 1){
                closingPrices.put(stockId, prices.subList(0, (params.getSlowPeriod())));
            } else
                closingPrices.put(stockId, prices);
        }
    }

    private void initClosingPrices(SigGenTSParams params) {
        if(params.getMarketEvents() != null){
            this.closingPrices = params.getMarketEvents();
            this.initEventsAvailable = true;
        } else {
            this.closingPrices = new HashMap<>();
            this.initEventsAvailable = false;
        }
    }

    private void initEventCalculation() throws AnalysisException {
        if(initEventsAvailable){
            for(String stockId : closingPrices.keySet()){
                currentMAPPO.put(stockId, calculate(getDoubleArray(closingPrices.get(stockId))));
            }
            initEventsAvailable = false;
        }
    }
}
