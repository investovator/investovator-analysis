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

import org.investovator.analysis.signalgen.timeseries.SigGenMAType;
import org.investovator.analysis.signalgen.utils.SigGenParams;

/**
 * @author rajith
 * @version ${Revision}
 */
public class SigGenTSParams implements SigGenParams {

    private int period;

    /*for moving averages*/
    private int slowPeriodAverage;
    private int quickPeriodAverage;
    private SigGenMAType maType;

    public SigGenTSParams(){
        this.period = 14;
        this.slowPeriodAverage = 26;
        this.quickPeriodAverage = 12;
        this.maType = SigGenMAType.EMA;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getSlowPeriodAverage() {
        return slowPeriodAverage;
    }

    public void setSlowPeriodAverage(int slowPeriodAverage) {
        this.slowPeriodAverage = slowPeriodAverage;
    }

    public int getQuickPeriodAverage() {
        return quickPeriodAverage;
    }

    public void setQuickPeriodAverage(int quickPeriodAverage) {
        this.quickPeriodAverage = quickPeriodAverage;
    }

    public SigGenMAType getMaType() {
        return maType;
    }

    public void setMaType(SigGenMAType maType) {
        this.maType = maType;
    }
}
