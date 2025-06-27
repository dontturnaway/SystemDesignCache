package NewApproach;

public class RemoveList {
    private RemoveListNode firstNode;
    private RemoveListNode lastNode;
    private int size;

    public void addNodeBegin(RemoveListNode node) {
        if (this.initListWithFirstNode(node)) {
            return;
        }
        node.setNextNode(firstNode);
        this.firstNode.setPrevNode(node);
        this.firstNode=node;
        size++;
    }

    public void addNodeEnd(RemoveListNode node) {
        if (this.initListWithFirstNode(node)) {
            return;
        }
        var lastNodeOld = lastNode;
        node.setPrevNode(lastNodeOld);
        lastNodeOld.setNextNode(node);
        this.lastNode=node;
        size++;
    }

    public RemoveListNode popBegin() {
        var nodeToRemove = firstNode;
        this.deleteNode(firstNode);
        size--;
        return nodeToRemove;
    }

    public RemoveListNode popEnd() {
        var nodeToRemove = lastNode;
        this.deleteNode(lastNode);
        size--;
        return nodeToRemove;
    }

    public void deleteNode(RemoveListNode node) {
        if (node == null) return;
        if (node == firstNode) {
            firstNode = node.getNextNode();
        }
        if (node == lastNode) {
            lastNode = node.getPrevNode();
        }
        if (node.getPrevNode() != null) {
            node.getPrevNode().setNextNode(node.getNextNode());
        }
        if (node.getNextNode() != null) {
            node.getNextNode().setPrevNode(node.getPrevNode());
        }
        node.setPrevNode(null);
        node.setNextNode(null);
        size--;
    }

    public void clear() {
        firstNode=null;
        lastNode=null;
        size=0;
    }

    private boolean initListWithFirstNode(RemoveListNode node) {
        if (!(firstNode==null)) {
            return false;
        }
        firstNode=node;
        lastNode=node;
        size++;
        return true;
    }

    public int getSize() {
        return size;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        var currentNode = firstNode;
        while (currentNode != null) {
            result.append(currentNode).append(" ");
            currentNode = currentNode.getNextNode();
        }
        return result.toString();
    }
}
