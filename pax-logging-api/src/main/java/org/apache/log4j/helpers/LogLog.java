/*
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

package org.apache.log4j.helpers;

import org.ops4j.pax.logging.PaxLogger;
import org.ops4j.pax.logging.spi.support.DefaultServiceLog;
import org.ops4j.pax.logging.spi.support.FallbackLogFactory;
import org.osgi.framework.FrameworkUtil;

/**
 * This class used to output log statements from within the log4j package.
 * 
 * <p>
 * Log4j components cannot make log4j logging calls. However, it is sometimes
 * useful for the user to learn about what log4j is doing. You can enable log4j
 * internal logging by defining the <b>log4j.configDebug</b> variable.
 * 
 * <p>
 * All log4j internal debug calls go to <code>System.out</code> where as
 * internal error messages are sent to <code>System.err</code>. All internal
 * messages are prepended with the string "log4j: ".
 * 
 * <p>In pax-logging, this class is configured to always delegate to
 * {@link DefaultServiceLog}
 *
 * @since 0.8.2
 * @author Ceki G&uuml;lc&uuml;
 */
public class LogLog {

    /**
     * Defining this value makes log4j print log4j-internal debug statements to
     * <code>System.out</code>.
     * 
     * <p>
     * The value of this string is <b>log4j.debug</b>.
     * 
     * <p>
     * Note that the search for all option names is case sensitive.
     */
    public static final String DEBUG_KEY = "log4j.debug";

    /**
     * Defining this value makes log4j components print log4j-internal debug
     * statements to <code>System.out</code>.
     * 
     * <p>
     * The value of this string is <b>log4j.configDebug</b>.
     * 
     * <p>
     * Note that the search for all option names is case sensitive.
     * 
     * @deprecated Use {@link #DEBUG_KEY} instead.
     */
    public static final String CONFIG_DEBUG_KEY = "log4j.configDebug";

    protected static boolean debugEnabled = false;

    /**
     * In quietMode not even errors generate any output.
     */
    private static boolean quietMode = false;

    private static PaxLogger log = FallbackLogFactory.createFallbackLog(FrameworkUtil.getBundle(LogLog.class), "log4j");

    /**
     * Allows to enable/disable log4j internal logging.
     */
    static public void setInternalDebugging(boolean enabled) {
	debugEnabled = enabled;
    }

    /**
     * This method is used to output log4j internal debug statements. Output goes to
     * <code>System.out</code>.
     */
    public static void debug(String msg) {
	if (debugEnabled && !quietMode) {
	    log.debug(msg, null);
	}
    }

    /**
     * This method is used to output log4j internal debug statements. Output goes to
     * <code>System.out</code>.
     */
    public static void debug(String msg, Throwable t) {
	if (debugEnabled && !quietMode) {
        log.debug(msg, t);
	}
    }

    /**
     * This method is used to output log4j internal error statements. There is no
     * way to disable error statements. Output goes to <code>System.err</code>.
     */
    public static void error(String msg) {
	if (quietMode)
	    return;
	log.error(msg, null);
    }

    /**
     * This method is used to output log4j internal error statements. There is no
     * way to disable error statements. Output goes to <code>System.err</code>.
     */
    public static void error(String msg, Throwable t) {
	if (quietMode)
	    return;

	log.error(msg, t);
    }

    /**
     * In quite mode no LogLog generates strictly no output, not even for errors.
     * 
     * @param quietMode A true for not
     */
    public static void setQuietMode(boolean quietMode) {
	LogLog.quietMode = quietMode;
    }

    /**
     * This method is used to output log4j internal warning statements. There is no
     * way to disable warning statements. Output goes to <code>System.err</code>.
     */
    public static void warn(String msg) {
	if (quietMode)
	    return;

	log.warn(msg, null);
    }

    /**
     * This method is used to output log4j internal warnings. There is no way to
     * disable warning statements. Output goes to <code>System.err</code>.
     */
    public static void warn(String msg, Throwable t) {
	if (quietMode)
	    return;

	log.warn(msg, t);
    }
}
