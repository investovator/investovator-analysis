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

package org.investovator.analysis.technical.utils;

/**
 * @author rajith
 * @version ${Revision}
 */
public enum IndicatorType {

    DX,
    ADX,
    SMA,
    EMA,
    DEMA,
    TEMA,
    TRIMA,
    KAMA,
    RSI,
    CCI,
    CMO,
    MOM,
    MACD,
    BBAND;

    private static final String TIME_SERIES_INDICATORS
            = "org.investovator.analysis.technical.indicators.timeseries.";

    public static String getClassName(IndicatorType type){

        switch (type) {
            case DX:
                return TIME_SERIES_INDICATORS + "DirectionalMovIndex";
            case ADX:
                return TIME_SERIES_INDICATORS + "ADirectionalMovement";
            case SMA:
                return TIME_SERIES_INDICATORS + "SimpleMovingAverage";
            case EMA:
                return TIME_SERIES_INDICATORS + "ExponentialMA";
            case DEMA:
                return TIME_SERIES_INDICATORS + "DoubleExponentialMA";
            case TEMA:
                return TIME_SERIES_INDICATORS + "TripleExponentialMA";
            case TRIMA:
                return TIME_SERIES_INDICATORS + "TriangularMA";
            case RSI:
                return TIME_SERIES_INDICATORS + "RelativeStrengthIndex";
            case CCI:
                return TIME_SERIES_INDICATORS + "CommodityChannelIndex";
            case CMO:
                return TIME_SERIES_INDICATORS + "ChandeMomentumOscillator";
            case KAMA:
                return TIME_SERIES_INDICATORS + "KaufmanAdaptiveMA";
            case MOM:
                return TIME_SERIES_INDICATORS + "Momentum";
            case MACD:
                return TIME_SERIES_INDICATORS + "MovingAverageCD";
            case BBAND:
                return TIME_SERIES_INDICATORS + "BollingerBands";
        }
        return null;

    }
}
