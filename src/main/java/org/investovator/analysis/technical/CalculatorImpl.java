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

package org.investovator.analysis.technical;

import org.investovator.analysis.exceptions.AnalysisException;
import org.investovator.analysis.exceptions.InvalidParamException;
import org.investovator.analysis.technical.indicators.Indicator;
import org.investovator.analysis.technical.indicators.IndicatorFactory;
import org.investovator.analysis.technical.indicators.IndicatorFactoryImpl;
import org.investovator.analysis.technical.utils.IndicatorType;
import org.investovator.analysis.technical.utils.Params;
import org.investovator.analysis.technical.utils.ResultsSet;

/**
 * @author rajith
 * @version ${Revision}
 */
public class CalculatorImpl implements Calculator {

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public ResultsSet calculateValues(IndicatorType type, Params parameters)
            throws AnalysisException, InvalidParamException {
        IndicatorFactory indicatorFactory = new IndicatorFactoryImpl();
        Indicator indicator = indicatorFactory.createAnalysisIndicator(type);
        return indicator.calculate(parameters);
    }
}
