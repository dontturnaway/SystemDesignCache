package NewApproach;

public class RemoveListNode {

    private String key;
    private String value;
    private RemoveListNode prevNode;
    private RemoveListNode nextNode;

    public RemoveListNode(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public RemoveListNode getPrevNode() {
        return prevNode;
    }

    public void setPrevNode(RemoveListNode prevNode) {
        this.prevNode = prevNode;
    }

    public RemoveListNode getNextNode() {
        return nextNode;
    }

    public void setNextNode(RemoveListNode nextNode) {
        this.nextNode = nextNode;
    }

    @Override
    public String toString() {
        return "{Key:" + this.key + " Value:'" + this.value +"'}";
    }

}
