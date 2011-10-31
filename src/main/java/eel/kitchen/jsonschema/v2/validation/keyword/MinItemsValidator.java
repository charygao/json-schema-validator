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

package eel.kitchen.jsonschema.v2.validation.keyword;

import eel.kitchen.jsonschema.v2.validation.ValidatorFactory;
import eel.kitchen.jsonschema.v2.validation.base.SimpleValidator;
import org.codehaus.jackson.JsonNode;

public final class MinItemsValidator
    extends SimpleValidator
{
    private final int minItems;

    public MinItemsValidator(final ValidatorFactory ignored,
        final JsonNode schema, final JsonNode instance)
    {
        super(ignored, schema, instance);
        minItems = schema.get("minItems").getIntValue();
    }

    @Override
    protected void validateInstance()
    {
        if (instance.size() < minItems)
            report.addMessage("array has less than minItems elements");
    }
}
