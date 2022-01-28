
class TrieNode {
    Object data;
    boolean isEnd;
    private final static int size = 26;
    TrieNode[] alp = new TrieNode[size];


    public TrieNode(Object c) {
        for (int i = 0; i < size; i++) {
            alp[i] = null;
        }
        isEnd = false;
        data = c;

    }

}

class TrieDataStructure {
    private TrieNode root;

    public TrieDataStructure() {
        root = new TrieNode(null);
    }

    public void insert(String word, Object bank) {
        if (search(word))
            return;
        TrieNode current = root;
        int index;
        for (int i = 0; i < word.length(); i++) {
            index = word.charAt(i) - 'a';
            if (current.alp[index] == null) {
                current.alp[index] = new TrieNode(null);
            }
            current = current.alp[index];
        }
        current.isEnd = true;
        current.data = bank;
    }

    public boolean search(String word) {
        int index = 0;
        TrieNode temp = root;
        for (int i = 0; i < word.length(); i++) {
            index = word.charAt(i) - 'a';
            if (temp.alp[index] == null) {
                return false;
            }
            temp = temp.alp[index];
        }
        return true;
    }

    public Object get(String word) {
        int index = 0;
        TrieNode temp = root;
        for (int i = 0; i < word.length(); i++) {
            index = word.charAt(i) - 'a';
            if (temp.alp[index] == null) {
                return null;
            }
            temp = temp.alp[index];
        }
        return temp.data;
    }

    int max = 0;
    Branch getMax() {
        max = 0;
        return printPattern(root, null);
    }

    Branch printPattern(TrieNode trieNode, Branch branch) {
        Branch maxBr = branch;
        if (trieNode == null)
            return maxBr;
        for (int i = 0; i < 26; i++) {
            maxBr = printPattern(trieNode.alp[i], branch);
            if (trieNode.data != null) {
                if (((Branch) trieNode.data).children().size > max) {
                    max = ((Branch) trieNode.data).children().size;
                    maxBr = ((Branch) trieNode.data);
                }
            }
        }
        return maxBr;
    }
}