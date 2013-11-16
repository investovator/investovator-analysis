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

import com.tictactec.ta.lib.MAType;
import org.investovator.analysis.technical.utils.ParamsImpl;

import java.util.Date;

/**
 * @author rajith
 * @version ${Revision}
 */
public class TimeSeriesParams extends ParamsImpl {

    private Date startDate;
    private Date endDate;
    private int period;

    private int slowPeriodAverage;
    private int quickPeriodAverage;
    private int signalPeriodAverage;

    private BBandMAType bBandMAType;
    private double devMultiUp;
    private double devMultiDown;

    public TimeSeriesParams (String stockId, Date startDate, Date endDate){
        super(stockId);
        this.startDate = startDate;
        this.endDate = endDate;

        /*period default*/
        this.period = 14;

        /*MACD default values*/
        this.slowPeriodAverage = 26;
        this.quickPeriodAverage = 12;
        this.signalPeriodAverage = 9;

        /*Default BBand values*/
        this.bBandMAType = BBandMAType.SMA;
        this.devMultiUp = 2.0;
        this.devMultiDown = 2.0;
    }


    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getMACDSlowPeriodAverage() {
        return slowPeriodAverage;
    }

    public void setMACDSlowPeriodAverage(int slowPeriodAverage) {
        this.slowPeriodAverage = slowPeriodAverage;
    }

    public int getMACDQuickPeriodAverage() {
        return quickPeriodAverage;
    }

    public void setMACDQuickPeriodAverage(int quickPeriodAverage) {
        this.quickPeriodAverage = quickPeriodAverage;
    }

    public int getMACDSignalPeriodAverage() {
        return signalPeriodAverage;
    }

    public void setMACDSignalPeriodAverage(int signalPeriodAverage) {
        this.signalPeriodAverage = signalPeriodAverage;
    }

    public BBandMAType getbBandMAType() {
        return bBandMAType;
    }

    public void setbBandMAType(BBandMAType bBandMAType) {
        this.bBandMAType = bBandMAType;
    }

    public double getBBandDevMultiUp() {
        return devMultiUp;
    }

    public void setBBandDevMultiUp(double devMultiUp) {
        this.devMultiUp = devMultiUp;
    }

    public double getBBandDevMultiDown() {
        return devMultiDown;
    }

    public void setBBandDevMultiDown(double devMultiDown) {
        this.devMultiDown = devMultiDown;
    }

    public enum BBandMAType {
        SMA, EMA, KAMA, MAMA, T3, DEMA, TEMA, TRIMA;

        public static MAType valueToTaLib(BBandMAType type){
            switch (type) {
                case SMA:
                    return MAType.Sma;
                case EMA:
                    return MAType.Ema;
                case KAMA:
                    return MAType.Kama;
                case MAMA:
                    return MAType.Mama;
                case TRIMA:
                    return MAType.Trima;
                case T3:
                    return MAType.T3;
                case DEMA:
                    return MAType.Dema;
                case TEMA:
                    return MAType.Tema;
            }
            return null;
        }
    }
}
