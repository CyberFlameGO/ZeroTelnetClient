/* 
 * Copyright (C) 2012-2017 Steven Lawson
 *
 * This file is part of FreedomTelnetClient.
 *
 * FreedomTelnetClient is free software: you can redistribute it and/or modify
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
package me.StevenLawson.BukkitTelnetClient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.*;

public class BTC_UsageDecoder
{
    private static final Pattern USAGE_MESSAGE = Pattern.compile(":\\[.+@BukkitTelnet\\]\\$ usage~(.+)");

    private BTC_UsageDecoder()
    {
        throw new AssertionError();
    }

    public static final boolean checkForUsageMessage(final String message)
    {
        final Matcher matcher = USAGE_MESSAGE.matcher(message);
        if (matcher.find())
        {
            final String data = matcher.group(1);
            try
            {
                final JSONObject json = new JSONObject(data);
                BTC_MainPanel.setTPS(getStringSafe(json, "tps"));
                return true;
            }
            catch (JSONException ex)
            {
            }
        }

        return false;
    }

    private static String getStringSafe(JSONObject json, String key)
    {
        try
        {
            return json.getString(key);
        }
        catch (JSONException ex)
        {
            return "null";
        }
    }
}
