/*
 * Copyright (c) 2011, Francis Galiegue <fgaliegue@gmail.com>
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

package eel.kitchen.jsonschema.v2.validation.base;

import eel.kitchen.jsonschema.v2.validation.ValidatorFactory;
import eel.kitchen.util.CollectionUtils;
import org.codehaus.jackson.JsonNode;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class RequiredPropertiesValidator
    extends SimpleValidator
{
    private final Set<String> required = new HashSet<String>();

    public RequiredPropertiesValidator(final ValidatorFactory ignored,
        final JsonNode schema, final JsonNode instance)
    {
        super(ignored, schema, instance);

        final JsonNode node = schema.get("properties");

        final Map<String, JsonNode> map
            = CollectionUtils.toMap(node.getFields());

        for (final Map.Entry<String, JsonNode> entry: map.entrySet())
            if (entry.getValue().path("required").asBoolean(false))
                required.add(entry.getKey());
    }

    @Override
    protected void validateInstance()
    {
        final Set<String> set = new HashSet<String>(required);

        final Set<String> fieldNames
            = CollectionUtils.toSet(instance.getFieldNames());

        set.removeAll(fieldNames);

        for (final String missing: set)
            report.addMessage("property " + missing + " is missing");
    }
}
