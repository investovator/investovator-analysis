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

package org.investovator.analysis.signalgen.events;

import java.util.Date;

/**
 * @author rajith
 * @version ${Revision}
 */
public abstract class MarketEvent implements AnalysisEvent {

    /**
     * The time at which this event occurred.
     */
    protected Date timestamp;

    /**
     * stockId of the event related to
     */
    protected String stockId;

    public MarketEvent(String stockId, Date time){
        this.timestamp = time;
        this.stockId = stockId;
    }

    @Override
    public String getStockId(){
       return stockId;
    }

    @Override
    public Date getTime(){
        return timestamp;
    }
}
