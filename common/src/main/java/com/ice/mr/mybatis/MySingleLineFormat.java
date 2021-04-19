package com.ice.mr.mybatis;

import java.util.regex.Pattern;


import com.p6spy.engine.common.P6Util;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;

public class MySingleLineFormat implements MessageFormattingStrategy {

	@Override
	public String formatMessage(final int connectionId, final String now, final long elapsed, final String category, final String prepared, final String sql) {
		return Pattern.compile("[\\s]+").matcher(P6Util.singleLine(sql)).replaceAll(" ").trim();
	}
	
}
