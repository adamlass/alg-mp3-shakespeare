package sp2;

import java.util.Set;
import java.util.List;
import java.util.LinkedList;

public class MySuffixTrie {

  private List<MyNode> nodes = new LinkedList<>();

  public MySuffixTrie(String[] seqs) {

    for (String seq : seqs) {
      for (int i = 0; i < seq.length(); i++) {
        boolean located = false;
        String sub = seq.substring(i);
        char indexChar = sub.charAt(0);

        // adding children to their respective nodes
        for (MyNode node : nodes) {
          if (indexChar == node.getIndexChar()) {
            located = true;

            node.addChild(sub, seq);
          }
        }

        // If we never located the indexChar, we will add a new node to our list
        if (!located) {
          MyNode newNode = new MyNode(indexChar, 1);
          newNode.addChild(sub, seq);

          // Adding
          this.nodes.add(newNode);
        }
      }
    }
  }

  public Set<String> find(String sub) {
    char root = sub.charAt(0);

    for (MyNode node : nodes)
      if (root == node.getIndexChar())
        return node.locate(sub, sub);

    return null;
  }
}