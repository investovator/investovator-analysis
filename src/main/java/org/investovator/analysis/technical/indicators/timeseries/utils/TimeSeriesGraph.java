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

package org.investovator.analysis.technical.indicators.timeseries.utils;

/**
 * @author rajith
 * @version ${Revision}
 */
public enum TimeSeriesGraph {

    /** Default graph*/
    ORIGINAL_CLOSING,
    ORIGINAL_HIGH,
    ORIGINAL_LOW,

    /** Directional index graph*/
    DX,

    /** Average directional index graph*/
    ADX,

    /** Simple moving average graph*/
    SMA,

    /** Exponential moving average graph*/
    EMA,

    /** Double exponential moving average graph*/
    DEMA,

    /** Triple exponential moving average graph*/
    TEMA,

    /** Triangular moving average graph*/
    TRIMA,

    /** Kaufman adaptive moving average graph*/
    KAMA,

    /** Moving average convergence divergence graphs*/
    MACD,
    MACD_SIGNAL,
    MACD_HIST,

    /** Momentum graph*/
    MOM,

    /**  Chande momentum oscillator graph*/
    CMO,

    /** Commodity channel index graph*/
    CCI,

    /** Relative strength index graph*/
    RSI,

    /** Bollinger bands*/
    BBAND_UPPER,
    BBAND_MIDDLE,
    BBAND_LOWER

}
