package org.openbpmn.bpmn.elements;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * The BaseElement is the abstract super class for most BPMN elements. It
 * provides a list of attributes with at least an id and a documentation. Other
 * element types can extend the attribute list.
 * 
 * @author rsoika
 */
public abstract class BPMNBaseElement {
    // Map<String, String> attributes=null;
    private NamedNodeMap attributeMap = null;
    private Node elementNode = null;

    public BPMNBaseElement() {
        super();
    }

    public BPMNBaseElement(Node node) {
        super();
        this.elementNode = node;
        if (this.elementNode.hasAttributes()) {
            // get attributes names and values
            this.attributeMap = this.elementNode.getAttributes();
        }
    }

    /**
     * Returns the Document object associated with this Element. The document
     * object can be used to create new nodes. 
     * @return
     */
    public Document getDoc() {
        Document doc = this.getElementNode().getOwnerDocument();
        return doc;
    }

    /**
     * Returns the ID of the element
     * 
     * @return
     */
    public String getId() {
        return getAttribute("id");
    }

    /**
     * Returns the name of the element
     * 
     * @return
     */
    public String getName() {
        return getAttribute("name");
    }

    /**
     * Returns the value of a given attribute by name.
     * <p>
     * The method operates directly on the attriubteMap loaded in the constructor.
     * 
     * @param name
     * @return
     */
    public String getAttribute(String name) {
        if (name == null || name.isEmpty() || attributeMap == null) {
            return null;
        }
        for (int i = 0; i < attributeMap.getLength(); i++) {
            Node node = attributeMap.item(i);
            if (name.equals(node.getNodeName())) {
                return node.getNodeValue();

            }
        }
        return null;
    }

    /**
     * Returns the corresponding dom element node
     * 
     * @return
     */
    public Node getElementNode() {
        return elementNode;
    }

    /**
     * Returns a map with all attributes of the Element node.
     * 
     * @param node
     * @return
     */
    public NamedNodeMap getAttributes() {
        return attributeMap;
    }

}
