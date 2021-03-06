/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.dataformat.bindy.format;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Locale;

public class BigDecimalPatternFormat extends NumberPatternFormat<BigDecimal> {

    public BigDecimalPatternFormat() {
    }

    public BigDecimalPatternFormat(String pattern, Locale locale, int precision, String rounding, String decimalSeparator, String groupingSeparator) {
        super(pattern, locale, precision, rounding, decimalSeparator, groupingSeparator);
    }

    @Override
    public BigDecimal parse(String string) throws Exception {
        if (getNumberFormat() != null) {
            DecimalFormat df = (DecimalFormat)getNumberFormat();
            df.setParseBigDecimal(true);
            BigDecimal bd = (BigDecimal)df.parse(string.trim());
            if (super.getPrecision() != -1) {
                bd = bd.setScale(super.getPrecision(), RoundingMode.valueOf(super.getRounding()));
            }
            return bd;
        } else {
            return new BigDecimal(string.trim());
        }
    }
}
