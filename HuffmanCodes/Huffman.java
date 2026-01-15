package dsa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Map.Entry;

public class Huffman {
   HashMap<Character, Integer> freq;
   PriorityQueue<CharNode> pq;
   HashMap<Character, String> codes = new HashMap();
   HashMap<String, Character> rcodes = new HashMap();
   CharNode root;

   public Huffman(HashMap<Character, Integer> m) {
      this.freq = m;
      m.remove('\r');
      m.remove('\ufeff');
      List<CharNode> ls = new ArrayList();
      Iterator var4 = this.freq.entrySet().iterator();

      while(var4.hasNext()) {
         Entry<Character, Integer> e = (Entry)var4.next();
         ls.add(new CharNode((Integer)e.getValue(), (Character)e.getKey(), (CharNode)null, (CharNode)null));
      }

      this.pq = new PriorityQueue(ls);

      while(this.pq.size() > 1) {
         CharNode t1 = (CharNode)this.pq.poll();
         CharNode t2 = (CharNode)this.pq.poll();
         this.pq.add(new CharNode(t1.freq + t2.freq, (Character)null, t1, t2));
      }

      this.root = (CharNode)this.pq.peek();
      this.getcodes("", (CharNode)this.pq.poll());
   }

   public void getcodes(String s, CharNode n) {
      if (n.left == null && n.right == null) {
         this.codes.put(n.val, s);
         this.rcodes.put(s, n.val);
      } else {
         if (n.left != null) {
            this.getcodes(s + "0", n.left);
         }

         if (n.right != null) {
            this.getcodes(s + "1", n.right);
         }
      }

   }
}
