package dsa;

public class CharNode implements Comparable<CharNode> {
   public CharNode left;
   public CharNode right;
   public int freq;
   public Character val;

   public CharNode(int frq, Character vl, CharNode l, CharNode r) {
      this.val = vl;
      this.right = r;
      this.left = l;
      this.freq = frq;
   }

   public int compareTo(CharNode cn) {
      if (this.freq < cn.freq) {
         return -1;
      } else if (this.freq > cn.freq) {
         return 1;
      } else if (this.val != null && this.val != 0) {
         return cn.val != null && cn.val != 0 ? this.val - cn.val : -1;
      } else {
         return 1;
      }
   }
}
