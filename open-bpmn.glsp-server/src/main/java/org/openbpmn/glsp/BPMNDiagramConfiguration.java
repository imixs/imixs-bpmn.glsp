/********************************************************************************
 * Copyright (c) 2022 Imixs Software Solutions GmbH,
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You can receive a copy of the GNU General Public
 * License at http://www.gnu.org/licenses/gpl.html
 *
 * Project:
 *     https://github.com/imixs/open-bpmn
 *
 * Contributors:
 *     Imixs Software Solutions GmbH - Project Management
 *     Ralph Soika - Software Developer
 ********************************************************************************/
package org.openbpmn.glsp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.glsp.graph.DefaultTypes;
import org.eclipse.glsp.graph.GraphPackage;
import org.eclipse.glsp.server.diagram.BaseDiagramConfiguration;
import org.eclipse.glsp.server.layout.ServerLayoutKind;
import org.eclipse.glsp.server.types.EdgeTypeHint;
import org.eclipse.glsp.server.types.ShapeTypeHint;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.glsp.bpmn.BpmnPackage;
import org.openbpmn.glsp.utils.ModelTypes;

/**
 * Provides configuration constants for a specific diagram implementation (i.e.
 * diagram language), The corresponding configuration for a diagram
 * implementation is identified via its diagram type.
 */
public class BPMNDiagramConfiguration extends BaseDiagramConfiguration {

    /**
     * Returns the type mappings for the diagram implementation. Type mappings are
     * used by GSON to construct the correct {@link EClass} based on the "type"
     * property of the JSON object.
     *
     * @return A complete map of all type mappings for the diagram implementation.
     */
    @Override
    public Map<String, EClass> getTypeMappings() {
        Map<String, EClass> mappings = DefaultTypes.getDefaultTypeMappings();

        // Layout Model types
        mappings.put(ModelTypes.LABEL_HEADING, GraphPackage.Literals.GLABEL);
        mappings.put(ModelTypes.COMP_HEADER, GraphPackage.Literals.GCOMPARTMENT);
        mappings.put(ModelTypes.ICON, BpmnPackage.Literals.ICON);
        // mappings.put(ModelTypes.EVENT_PORT, GraphPackage.Literals.GPORT);
        mappings.put(ModelTypes.POOL, BpmnPackage.Literals.POOL);
        mappings.put(ModelTypes.STRUCTURE, GraphPackage.Literals.GCOMPARTMENT);

        // BPMN Types
        mappings.put(BPMNTypes.TASK, BpmnPackage.Literals.TASK_NODE);
        mappings.put(BPMNTypes.GATEWAY, BpmnPackage.Literals.GATEWAY_NODE);
        mappings.put(BPMNTypes.EVENT, BpmnPackage.Literals.EVENT_NODE);
        mappings.put(BPMNTypes.SEQUENCE_FLOW, BpmnPackage.Literals.SEQUENCE_FLOW);

        return mappings;
    }

    /**
     * Returns the diagram type of the diagram implementation that corresponds to
     * this configuration.
     *
     * @return The diagram type.
     */
    @Override
    public String getDiagramType() {
        return "BPMN 2.0";
    }

    /**
     * Returns the shape type hints for the diagram implementation. Shape type hints
     * are sent to the client and used to validate whether certain operations for
     * shapes/nodes are allowed without having to query the server again.
     *
     * @return List of all shape type hints for the diagram implementation.
     */
    @Override
    public List<ShapeTypeHint> getShapeTypeHints() {
        List<ShapeTypeHint> nodeHints = new ArrayList<>();

        // nodeHints.add(new ShapeTypeHint(DefaultTypes.NODE, true, true, true, false));
        nodeHints.add(new ShapeTypeHint(BPMNTypes.MANUAL_TASK, true, true, true, true));
        nodeHints.add(new ShapeTypeHint(BPMNTypes.SCRIPT_TASK, true, true, true, true));
        nodeHints.add(new ShapeTypeHint(BPMNTypes.SEND_TASK, true, true, true, true));
        nodeHints.add(new ShapeTypeHint(BPMNTypes.SERVICE_TASK, true, true, true, true));

        // Event ShapeTypeHints
        // Each event type has different containable EventDefinitions!
        nodeHints.add(createStartEventHint());
        nodeHints.add(createEndEventHint());
        nodeHints.add(createCatchEventHint());
        nodeHints.add(createThrowEventHint());

        // Gateway ShapeEventTypes
        nodeHints.add(new ShapeTypeHint(BPMNTypes.EXCLUSIVE_GATEWAY, true, true, false, true));
        nodeHints.add(new ShapeTypeHint(BPMNTypes.INCLUSIVE_GATEWAY, true, true, false, true));

        // Add Pool
        ShapeTypeHint catHint = new ShapeTypeHint(ModelTypes.POOL, true, true, true, true);
        catHint.setContainableElementTypeIds(BPMNModel.BPMN_FLOWELEMENTS);
        nodeHints.add(catHint);

        return nodeHints;
    }

    /**
     * Creates a StartEvent ShapeTypeHint
     * <p>
     * The method defines the containable Event Definitions
     *
     * @return
     */
    private ShapeTypeHint createStartEventHint() {
        List<String> definitions = Arrays.asList(new String[] { //
                BPMNTypes.EVENT_DEFINITION_CONDITIONAL, //
                BPMNTypes.EVENT_DEFINITION_TIMER, //
                BPMNTypes.EVENT_DEFINITION_SIGNAL, //
                BPMNTypes.EVENT_DEFINITION_MESSAGE });
        ShapeTypeHint shapeTypeHint = new ShapeTypeHint(BPMNTypes.START_EVENT, true, true, false, true);
        shapeTypeHint.setContainableElementTypeIds(definitions);
        return shapeTypeHint;
    }

    /**
     * Creates a EndEvent ShapeTypeHint
     * <p>
     * The method defines the containable Event Definitions
     *
     * @return
     */
    private ShapeTypeHint createEndEventHint() {
        List<String> definitions = Arrays.asList(new String[] { //
                BPMNTypes.EVENT_DEFINITION_COMPENSATION, //
                BPMNTypes.EVENT_DEFINITION_SIGNAL, //
                BPMNTypes.EVENT_DEFINITION_TERMINATE });
        ShapeTypeHint shapeTypeHint = new ShapeTypeHint(BPMNTypes.END_EVENT, true, true, false, true);
        shapeTypeHint.setContainableElementTypeIds(definitions);
        return shapeTypeHint;
    }

    /**
     * Creates a CatchEvent ShapeTypeHint
     * <p>
     * The method defines the containable Event Definitions
     *
     * @return
     */
    private ShapeTypeHint createCatchEventHint() {
        List<String> definitions = Arrays.asList(new String[] { //
                BPMNTypes.EVENT_DEFINITION_CONDITIONAL, //
                BPMNTypes.EVENT_DEFINITION_LINK, //
                BPMNTypes.EVENT_DEFINITION_SIGNAL });
        ShapeTypeHint shapeTypeHint = new ShapeTypeHint(BPMNTypes.CATCH_EVENT, true, true, false, true);
        shapeTypeHint.setContainableElementTypeIds(definitions);
        return shapeTypeHint;
    }

    /**
     * Creates a CatchEvent ShapeTypeHint
     * <p>
     * The method defines the containable Event Definitions
     *
     * @return
     */
    private ShapeTypeHint createThrowEventHint() {
        List<String> definitions = Arrays.asList(new String[] { //
                BPMNTypes.EVENT_DEFINITION_COMPENSATION, //
                BPMNTypes.EVENT_DEFINITION_LINK, //
                BPMNTypes.EVENT_DEFINITION_SIGNAL });
        ShapeTypeHint shapeTypeHint = new ShapeTypeHint(BPMNTypes.THROW_EVENT, true, true, false, true);
        shapeTypeHint.setContainableElementTypeIds(definitions);
        return shapeTypeHint;
    }

    /**
     * Returns the edge type hints for the diagram implementation. Edge type hints
     * are sent to the client and used to validate whether certain operations for
     * edges are allowed without having to query the server again.
     * <p>
     * TODO We need to define a EdgeTypeHint for all typs of flows in BPMN.
     * Currently we only support the SequenceFlow
     *
     * @return List of all edge type hints for the diagram implementation.
     */
    @Override
    public List<EdgeTypeHint> getEdgeTypeHints() {
        List<EdgeTypeHint> edgeHints = new ArrayList<>();

        // SequenceFLow
        EdgeTypeHint sequenceFlowHint = createDefaultEdgeTypeHint(BPMNTypes.SEQUENCE_FLOW);
        sequenceFlowHint.setSourceElementTypeIds(BPMNModel.BPMN_FLOWELEMENTS);
        sequenceFlowHint.setTargetElementTypeIds(BPMNModel.BPMN_FLOWELEMENTS);
        edgeHints.add(sequenceFlowHint);

        return edgeHints;
    }

    /**
     * Set the BPMN default shape hints.
     * <ul>
     * <li>repositionable=true
     * <li>deletable = true
     * <li>resizable = false
     * <li>reparentable = true
     * <p>
     */
    @Override
    public ShapeTypeHint createDefaultShapeTypeHint(final String elementId) {
        // Override the default-default
        return new ShapeTypeHint(elementId, true, true, false, true);
    }

    /**
     * <ul>
     * <li>repositionable=true
     * <li>deletable = true
     * <li>routeable = true
     */
    @Override
    public EdgeTypeHint createDefaultEdgeTypeHint(final String elementId) {
        EdgeTypeHint hint = super.createDefaultEdgeTypeHint(elementId);

        // TODO can we really define a default source/target here ?
        return hint;
    }

    /**
     * Returns the supported layout kind for this diagram implementation.
     *
     * @return The layout kind.
     */
    @Override
    public ServerLayoutKind getLayoutKind() {
        return ServerLayoutKind.MANUAL;
    }

    /**
     * Boolean flag to specific whether the diagram implementation expects the
     * client to provide layout information for certain diagram element. Default is
     * 'true'.
     *
     * @return Boolean flag to indicate whether the client needs to be involved in
     *         the layout process.
     */
    @Override
    public boolean needsClientLayout() {
        return true;
    }

}
