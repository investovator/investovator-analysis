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

import org.investovator.analysis.signalgen.SignalGenerator;
import org.investovator.analysis.signalgen.timeseries.utils.SigGenTSParams;

import java.util.HashMap;

/**
 * @author rajith
 * @version ${Revision}
 */
public abstract class SigGenFromTS implements SignalGenerator{

    protected SigGenTSParams params;
    protected HashMap<String, StockStatus> currentSecurityStatus;

    public SigGenFromTS(SigGenTSParams params){
        this.params = params;
        this.currentSecurityStatus = new HashMap<>();
    }

    @Override
    public StockStatus getCurrentStatus(String stockId) {
        return currentSecurityStatus.get(stockId);
    }
}
