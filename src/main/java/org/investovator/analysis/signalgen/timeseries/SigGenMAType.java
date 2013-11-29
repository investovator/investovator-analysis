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

    protected static MAType getMAType(SigGenMAType type){
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

    public static String getMAName(SigGenMAType type){
        switch (type) {
            case SMA:
                return "Simple Moving Average";
            case EMA:
                return "Exponential Moving Average";
            case DEMA:
                return "Double Exponential Moving Average";
            case TEMA:
                return "Triple Exponential Moving Average";
            case TRIMA:
                return "Triangular Moving Average";
            case KAMA:
                return "Kaufman Adaptive Moving Average";
        }
        return null;
    }

    public static String getDescription(SigGenMAType type){
        switch (type) {
            case SMA:
                return "A simple, or arithmetic, moving average that is calculated by " +
                        "adding the closing price of the security for a number of time " +
                        "periods and then dividing this total by the number of time periods. " +
                        "Short-term averages respond quickly to changes in the price of the " +
                        "underlying, while long-term averages are slow to react.";
            case EMA:
                return "A type of moving average that is similar to a simple moving average, " +
                        "except that more weight is given to the latest data. The exponential " +
                        "moving average is also known as - exponentially weighted moving average.";
            case DEMA:
                return "A technical indicator developed by Patrick Mulloy that first appeared " +
                        "in the February, 1994 Technical Analysis of Stocks & Commodities. " +
                        "The DEMA is a calculation based on both a single exponential moving " +
                        "average (EMA) and a double EMA";
            case TEMA:
                return "A technical indicator used for smoothing price and other data. It is a" +
                        " composite of a single exponential moving average, a double exponential" +
                        " moving average and a triple exponential moving average. Developed by " +
                        "Patrick Mulloy, the TEMA was first published in 1994.";
            case TRIMA:
                return "The triangular moving average (also known as the TMA) is similar to other " +
                        "moving averages in that it shows the mean price over a specified number " +
                        "of previous prices. However, the triangular moving average differs from " +
                        "most moving averages in that it is double smoothed (i.e. it is averaged twice).";
            case KAMA:
                return "The Adaptive Moving Average (AMA) aka Kaufman Adaptive Moving Average " +
                        "(KAMA) was created by Perry Kaufman and first presented in his book " +
                        "Smarter Trading (1995).  This moving average offered a significant advantage " +
                        "over previous attempts at 'intelligent' average calculations";
        }
        return null;
    }
}
