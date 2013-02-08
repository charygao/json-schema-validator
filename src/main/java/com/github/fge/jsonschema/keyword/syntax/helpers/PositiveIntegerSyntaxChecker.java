/*
 * Copyright (c) 2013, Francis Galiegue <fgaliegue@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the Lesser GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * Lesser GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.github.fge.jsonschema.keyword.syntax.helpers;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonschema.keyword.syntax.AbstractSyntaxChecker;
import com.github.fge.jsonschema.processing.ProcessingException;
import com.github.fge.jsonschema.ref.JsonPointer;
import com.github.fge.jsonschema.report.ProcessingReport;
import com.github.fge.jsonschema.tree.JsonSchemaTree;
import com.github.fge.jsonschema.util.NodeType;

import java.util.Collection;

import static com.github.fge.jsonschema.messages.SyntaxMessages.*;

public final class PositiveIntegerSyntaxChecker
    extends AbstractSyntaxChecker
{
    public PositiveIntegerSyntaxChecker(final String keyword)
    {
        super(keyword, NodeType.INTEGER);
    }

    @Override
    protected void checkValue(final Collection<JsonPointer> pointers,
        final ProcessingReport report, final JsonSchemaTree tree)
        throws ProcessingException
    {
        final JsonNode node = getNode(tree);

        if (!node.canConvertToInt()) {
            report.error(newMsg(tree, INTEGER_TOO_LARGE)
                .put("max", Integer.MAX_VALUE));
            return;
        }

        if (node.intValue() < 0)
            report.error(newMsg(tree, INTEGER_IS_NEGATIVE));
    }
}
