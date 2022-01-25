import java.util.LinkedList;

class TrieNode {
    Object data;
    boolean isEnd;
    LinkedList<TrieNode> childList;

    public TrieNode(Object c) {
        childList = new LinkedList<>();
        isEnd = false;
        data = c;

    }
    public TrieNode getChild(char c) {
        if (childList != null)
            for (TrieNode eachChild : childList)
                if (eachChild.data.equals(c))
                    return eachChild;
        return null;
    }
}
class TrieDataStructure {
    private TrieNode root;

    public TrieDataStructure() {
        root = new TrieNode(' ');
    }

    public void insert(String word, Object bank) {
        if (search(word))
            return;
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            TrieNode child = current.getChild(ch);
            if (child != null)
                current = child;
            else {
                current.childList.add(new TrieNode(ch));
                current = current.getChild(ch);
            }

        }
        current.isEnd = true;
        current.childList.add(new TrieNode(bank));

    }

    public boolean search(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            if (current.getChild(ch) == null)
                return false;
            else
                current = current.getChild(ch);
        }
        return current.isEnd;
    }

    public Object get(String Name) {
        if (search(Name)) {
            TrieNode current = root;
            for (char ch : Name.toCharArray()) {
                current = current.getChild(ch);
            }
            return current.childList.get(0).data;
        } else
            return null;
    }
}