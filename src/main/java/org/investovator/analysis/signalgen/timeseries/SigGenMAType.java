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

import com.tictactec.ta.lib.MAType;

/**
 * @author rajith
 * @version ${Revision}
 */
public enum SigGenMAType {
    SMA,
    EMA,
    TEMA,
    TRIMA,
    DEMA,
    KAMA;

    protected MAType getMAType(SigGenMAType type){
        switch (type) {
            case SMA:
                return MAType.Sma;
            case EMA:
                return MAType.Ema;
            case DEMA:
                return MAType.Tema;
            case TEMA:
                return MAType.Trima;
            case TRIMA:
                return MAType.Dema;
            case KAMA:
                return MAType.Kama;
        }
        return null;
    }

}
