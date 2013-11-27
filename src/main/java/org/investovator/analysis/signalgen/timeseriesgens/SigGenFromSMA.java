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

package org.investovator.analysis.signalgen.timeseriesgens;

import org.investovator.analysis.signalgen.SignalGenerator;
import org.investovator.analysis.signalgen.events.AnalysisEvent;
import org.investovator.analysis.signalgen.events.MarketClosedEvent;

/**
 * @author rajith
 * @version ${Revision}
 */
public class SigGenFromSMA implements SignalGenerator {

    @Override
    public void eventOccurred(AnalysisEvent event) {
        if(event instanceof MarketClosedEvent){
           //ToDo
        }
    }

    @Override
    public StockStatus getCurrentStatus(String stockId) {
        return null;  //ToDo
    }
}
