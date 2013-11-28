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

import org.investovator.analysis.exceptions.AnalysisException;
import org.investovator.analysis.signalgen.SignalGenerator;
import org.investovator.analysis.signalgen.events.AnalysisEvent;
import org.investovator.analysis.signalgen.events.MarketClosedEvent;

import java.util.Date;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author rajith
 * @version ${Revision}
 */
public class MockEventGenerator {

    protected ConcurrentLinkedQueue<SignalGenerator> listeners;

    public MockEventGenerator(){
        listeners = new ConcurrentLinkedQueue<>();
    }

    public void fireEvent(String stockId, Date date, double price) throws AnalysisException {
        AnalysisEvent event = new MarketClosedEvent(stockId,date, price);
        notifyGenericListeners(event);
    }

    public void addListeners(SignalGenerator generator){
        listeners.add(generator);
    }

    private void notifyGenericListeners(AnalysisEvent event) throws AnalysisException {
        for (SignalGenerator listener : listeners) {
            listener.eventOccurred(event);
        }
    }

    public ConcurrentLinkedQueue<SignalGenerator> getListeners(){
        return listeners;
    }
}
