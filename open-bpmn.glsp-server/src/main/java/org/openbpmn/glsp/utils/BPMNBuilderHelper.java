/********************************************************************************
 * Copyright (c) 2020 EclipseSource and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the Eclipse
 * Public License v. 2.0 are satisfied: GNU General Public License, version 2
 * with the GNU Classpath Exception which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 ********************************************************************************/
package org.openbpmn.glsp.utils;

import java.util.logging.Logger;

import org.eclipse.glsp.graph.GLabel;
import org.eclipse.glsp.graph.builder.impl.GLabelBuilder;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.glsp.bpmn.BaseElement;
import org.openbpmn.glsp.bpmn.Icon;
import org.openbpmn.glsp.elements.IconBuilder;

/**
 * The BPMNBuilderHelper provides helper methods to create GNode Elements
 *
 * @author rsoika
 *
 */
public class BPMNBuilderHelper {
    @SuppressWarnings("unused")
    private static Logger logger = Logger.getLogger(BPMNBuilderHelper.class.getName());

    public static Icon createCompartmentIcon(final BaseElement node) {
        return new IconBuilder(). //
                id(node.getId() + "_icon"). //
                build();
    }

    /**
     * Creates a GLabel for the name of a BaseElement
     *
     * @param node
     * @return GPort
     */
    public static GLabel createCompartmentHeader(final BaseElement node) {
        return new GLabelBuilder(ModelTypes.LABEL_HEADING) //
                .id(node.getId() + "_header") //
                .text(node.getName()).build();
    }

    /**
     * Creates a moveable BPMNLabel. This element is used for Event and Gateway
     * Nodes
     *
     */
    public static GLabel createBPMNLabel(final String id, final String name, final Double x, final Double y) {
        return new GLabelBuilder(BPMNTypes.BPMN_LABEL) //
                .id(id + "_bpmnlabel") //
                .position(x, y) //
                .text(name) //
                .build();
    }

}
