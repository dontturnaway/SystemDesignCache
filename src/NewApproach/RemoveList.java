package NewApproach;

public class RemoveList {
    private RemoveListNode firstNode;
    private RemoveListNode lastNode;
    private int size;

    public void appendToBegin(RemoveListNode node) {
        if (this.initListWithFirstNode(node)) {
            return;
        }
        var exFirstNode = firstNode;
        node.setNextNode(exFirstNode);
        exFirstNode.setPrevNode(node);
        this.firstNode=node;
        size++;
    }

    public void appendToEnd(RemoveListNode node) {
        if (this.initListWithFirstNode(node)) {
            return;
        }
        var exLastNode = lastNode;
        node.setPrevNode(exLastNode);
        exLastNode.setNextNode(node);
        this.lastNode=node;
        size++;
    }

    public RemoveListNode popBegin() {
        var nodeToRemove = firstNode;
        this.delete(firstNode);
        size--;
        return nodeToRemove;
    }

    public RemoveListNode popEnd() {
        var nodeToRemove = lastNode;
        this.delete(lastNode);
        size--;
        return nodeToRemove;
    }

    public void delete(RemoveListNode node) {
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

    public void truncate() {
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

    @Override
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
