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

package org.investovator.analysis.signalgen.timeseries.utils;

import org.investovator.analysis.exceptions.InvalidParamException;
import org.investovator.analysis.signalgen.events.AnalysisEvent;
import org.investovator.analysis.signalgen.timeseries.SigGenMAType;
import org.investovator.analysis.signalgen.utils.SigGenParams;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @author rajith
 * @version ${Revision}
 */
public class SigGenTSParams implements SigGenParams {

    private int period;

    /*for moving averages*/
    private int slowPeriod;
    private int quickPeriod;
    private SigGenMAType maType;
    private HashMap<String, List<AnalysisEvent>> marketEvents;

    public SigGenTSParams(){
        this.period = 14;
        this.slowPeriod = 26;
        this.quickPeriod = 12;
        this.maType = SigGenMAType.EMA;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getSlowPeriod() {
        return slowPeriod;
    }

    public void setSlowPeriod(int slowPeriodAverage) {
        this.slowPeriod = slowPeriodAverage;
    }

    public int getQuickPeriod() {
        return quickPeriod;
    }

    public void setQuickPeriod(int quickPeriodAverage) {
        this.quickPeriod = quickPeriodAverage;
    }

    public SigGenMAType getMaType() {
        return maType;
    }

    public void setMaType(SigGenMAType maType) {
        this.maType = maType;
    }

    public HashMap<String, List<AnalysisEvent>> getMarketEvents() {
        return marketEvents;
    }

    public void setMarketEvents(String stockId, List<AnalysisEvent> marketEventSet)
            throws InvalidParamException {
        if(marketEvents == null){
           marketEvents = new HashMap<>();
        }

        if(slowPeriod != marketEventSet.size()){
            throw new InvalidParamException("size mismatch");
        } else {
            Collections.sort(marketEventSet, Collections.reverseOrder());
            this.marketEvents.put(stockId, marketEventSet);
        }
    }
}
