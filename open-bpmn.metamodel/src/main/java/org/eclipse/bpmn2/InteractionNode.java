/**
 */
package org.eclipse.bpmn2;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Interaction Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.InteractionNode#getIncomingConversationLinks <em>Incoming Conversation Links</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.InteractionNode#getOutgoingConversationLinks <em>Outgoing Conversation Links</em>}</li>
 * </ul>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getInteractionNode()
 * @model
 * @generated
 */
public interface InteractionNode extends EObject {
    /**
     * Returns the value of the '<em><b>Incoming Conversation Links</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.ConversationLink}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Incoming Conversation Links</em>' reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getInteractionNode_IncomingConversationLinks()
     * @model resolveProxies="false" transient="true" changeable="false" volatile="true" derived="true" ordered="false"
     * @generated
     */
    EList<ConversationLink> getIncomingConversationLinks();

    /**
     * Returns the value of the '<em><b>Outgoing Conversation Links</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.ConversationLink}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Outgoing Conversation Links</em>' reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getInteractionNode_OutgoingConversationLinks()
     * @model transient="true" changeable="false" volatile="true" derived="true" ordered="false"
     * @generated
     */
    EList<ConversationLink> getOutgoingConversationLinks();

} // InteractionNode
