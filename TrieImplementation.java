class Node {
    int characterBitMap;
    int wordEndingBitMap;
    Node[] nextCharacters;
    public Node() {
        characterBitMap = 0;
        wordEndingBitMap = 0;
        nextCharacters = new Node[26];
    }
}

class Trie {

    Node root;

    public Trie() {
        root = new Node();
    }
    
    public void insert(String word) {
        char[] charactersInWord = word.toCharArray();
        Node currentNode = root;
        for(int i = 0; i < charactersInWord.length; i++) {
            int characterNumber = charactersInWord[i] - 'a';
            currentNode.characterBitMap = currentNode.characterBitMap | (1 << characterNumber);
            if(i == charactersInWord.length - 1) {
                currentNode.wordEndingBitMap = currentNode.wordEndingBitMap | (1 << characterNumber);
            } else {
                if(currentNode.nextCharacters[characterNumber] == null) {
                    currentNode.nextCharacters[characterNumber] = new Node();
                }
                currentNode = currentNode.nextCharacters[characterNumber];
            }
        }
    }
    
    public boolean search(String word) {
        char[] charactersInWord = word.toCharArray();
        Node currentNode = root;
        for(int i = 0; i < charactersInWord.length; i++) {
            int characterNumber = charactersInWord[i] - 'a';
            if(((currentNode.characterBitMap) & (1 << characterNumber)) > 0) {
                if(i == charactersInWord.length - 1) {
                    if(((currentNode.wordEndingBitMap) & (1 << characterNumber)) > 0) {
                        return true;
                    } else {
                        return false;
                    }
                }
            } else {
                return false;
            }
            currentNode = currentNode.nextCharacters[characterNumber];
            if(currentNode == null) {
                return false;
            }
        }
        return false;
    }
    
    public boolean startsWith(String prefix) {
        char[] charactersInWord = prefix.toCharArray();
        Node currentNode = root;
        for(int i = 0; i < charactersInWord.length; i++) {
            int characterNumber = charactersInWord[i] - 'a';
            if(((currentNode.characterBitMap) & (1 << characterNumber)) > 0) {
                if(i == charactersInWord.length - 1) {
                    return true;
                }
            } else {
                return false;
            }
            currentNode = currentNode.nextCharacters[characterNumber];
            if(currentNode == null) {
                return false;
            }
        }
        return false;
    }

}
