package sp2;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

class MyNode {
    private List<MyNode> children = new LinkedList<>();
    private List<String> fullSeqs = new LinkedList<>();

    private String seq;
    private String seqSub;

    private int depth;
    private char indexChar;

    public MyNode(char indexChar, int depth) {
        this.indexChar = indexChar;
        this.depth = depth;
    }

    public List<MyNode> getChildren() {
        return children;
    }

    public char getIndexChar() {
        return indexChar;
    }

    public Set<String> locate(String seq, String seqSave) {
        Set<String> res = new HashSet<>();

        String sub = seq.substring(1);

        if (sub.isEmpty())
            return collectSeqs();

        char indexChar = sub.charAt(0);

        for (MyNode node : this.children)
            if (node.getIndexChar() == indexChar)
                return node.locate(sub, seqSave);

        return res;

    }

    public void addChild(String sub, String fullSeq) {
        sub = sub.substring(1);

        if (sub.isEmpty() || sub == null) {

            if (this.seqSub != null && this.seqSub.isEmpty()) {
                this.fullSeqs.add(fullSeq);

                return;

            } else if (this.seq != fullSeq && this.seq != null) {
                String seqSave = this.seq;
                String seqSubSave = this.seqSub;

                char indexCharSave = seqSubSave.charAt(0);

                MyNode node = new MyNode(indexCharSave, depth + 1);
                node.addChild(seqSubSave, seqSave);

                this.children.add(node);
            }

            this.seqSub = sub;

            this.seq = fullSeq;

        } else if (this.seq == null && this.children.size() == 0) {

            this.seq = fullSeq;
            this.seqSub = sub;

        } else {
            char subIndexChar = sub.charAt(0);

            for (MyNode node : this.children) {
                if (node.getIndexChar() == subIndexChar) {
                    node.addChild(sub, fullSeq);
                    return;
                }
            }

            if (this.seqSub == null || this.seqSub.isEmpty()) {
                MyNode newNode = new MyNode(subIndexChar, depth + 1);
                newNode.addChild(sub, fullSeq);
                this.children.add(newNode);
                return;
            }

            String seqSave = this.seq;

            String seqSubSave = this.seqSub;
            char indexCharSave = seqSubSave.charAt(0);

            MyNode node = new MyNode(indexCharSave, depth + 1);

            if (subIndexChar == indexCharSave) {
                node.addChild(sub, fullSeq);
                node.addChild(seqSubSave, seqSave);

                this.children.add(node);

                this.seqSub = null;
                this.seq = null;
            } else {
                node.addChild(seqSubSave, seqSave);

                MyNode node2 = new MyNode(subIndexChar, depth + 1);

                node2.addChild(sub, fullSeq);

                this.children.add(node);
                this.children.add(node2);

                this.seq = null;
                this.seqSub = null;
            }

        }

    }

    public Set<String> collectSeqs() {
        Set<String> res = new HashSet<>();

        for (MyNode node : this.children) {
            res.addAll(node.collectSeqs());
        }

        if (this.seq != null) {
            res.add(this.seq);
            res.addAll(this.fullSeqs);
        }

        return res;
    }

}
