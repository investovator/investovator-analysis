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

import org.investovator.analysis.exceptions.AnalysisException;
import org.investovator.analysis.signalgen.MASignalGenFactory;
import org.investovator.analysis.signalgen.SignalGenerator;
import org.investovator.analysis.signalgen.SignalGeneratorFactory;
import org.investovator.analysis.signalgen.timeseries.utils.MockEventGenerator;
import org.investovator.analysis.signalgen.timeseries.utils.SigGenTSParams;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertTrue;

/**
 * @author rajith
 * @version ${Revision}
 */
public class TestMASignalGen {

    @Test
    public void testMASignalGen() throws AnalysisException, InterruptedException {

        SignalGeneratorFactory generatorFactory = new MASignalGenFactory();

        SigGenTSParams params = new SigGenTSParams();
        params.setMaType(SigGenMAType.SMA);
        params.setQuickPeriod(12);
        params.setSlowPeriod(26);

        SigGenFromMA sigGenFromMA = (SigGenFromMA) generatorFactory.createGenerator(params);

        MockEventGenerator eventGenerator = new MockEventGenerator();
        eventGenerator.addListeners(sigGenFromMA);
        for (int i = 0; i < 60; i++){

            if(34 < i  && i < 57 ){
                eventGenerator.fireEvent("GOOG", new Date(), (i * i / 500 * i));
            }
            else {
                eventGenerator.fireEvent("GOOG", new Date(), (i * i));
            }
            Thread.sleep(500);

            if(i < 26){
                assertTrue(sigGenFromMA.getCurrentStatus("GOOG").equals(SignalGenerator.StockStatus.NA));
            } else if(i > 26 && i < 41){
                assertTrue(sigGenFromMA.getCurrentStatus("GOOG").equals(SignalGenerator.StockStatus.DO_NOTHING));
            } else if(i==42){
                assertTrue(sigGenFromMA.getCurrentStatus("GOOG").equals(SignalGenerator.StockStatus.BUY));
            } else if(i == 43){
                assertTrue(sigGenFromMA.getCurrentStatus("GOOG").equals(SignalGenerator.StockStatus.DO_NOTHING));
            } else if(i==57){
                assertTrue(sigGenFromMA.getCurrentStatus("GOOG").equals(SignalGenerator.StockStatus.SELL));
            }
        }
    }
}
