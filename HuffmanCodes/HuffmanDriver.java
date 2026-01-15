package dsa;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Map.Entry;

public class HuffmanDriver {
   private static HashMap map = new HashMap();

   public static void main(String[] args) throws IOException {
      Scanner scanner = new Scanner(System.in);
      System.out.println("Please enter the file path:");
      String userInput = scanner.nextLine();
      System.out.println("You entered: " + userInput);
      iterateFileCharacters(userInput);
      Huffman th = new Huffman(map);
      System.out.println("Encoding table:");
      Iterator var5 = th.codes.entrySet().iterator();

      while(var5.hasNext()) {
         Entry<Character, String> e = (Entry)var5.next();
         if ((Character)e.getKey() == '\n') {
            System.out.println("\\n: " + (String)e.getValue());
         } else if ((Character)e.getKey() == ' ') {
            System.out.println("' ': " + (String)e.getValue());
         } else {
            System.out.println(e.getKey() + ": " + (String)e.getValue());
         }
      }

      File ff = new File(userInput + ".ENCODED");
      FileWriter writer = new FileWriter(ff);
      FileReader fileReader = null;

      try {
         fileReader = new FileReader(userInput);

         int character;
         while((character = fileReader.read()) != -1) {
            if (th.codes.get((char)character) != null) {
               writer.write((String)th.codes.get((char)character));
            }
         }
      } catch (IOException var37) {
         System.out.println("An error occurred while reading the file: " + var37.getMessage());
      } finally {
         try {
            if (fileReader != null) {
               fileReader.close();
            }
         } catch (IOException var34) {
            System.out.println("Error while closing the file: " + var34.getMessage());
         }

      }

      System.out.println("Please enter the file path to decode (Note that \"" + userInput + ".ENCODED\" is the file I just encoded):");
      String useInput = scanner.nextLine();
      System.out.println("You entered: " + useInput);
      File fff = new File(useInput + ".DECODED");
      writer.close();
      writer = new FileWriter(fff);
      File ffff = new File(useInput);
      fileReader = null;
      String curr = "";

      try {
         fileReader = new FileReader(ffff);

         int character;
         while((character = fileReader.read()) != -1) {
            Character c = (char)character;
            if (c == '1' || c == '0') {
               curr = curr + c;
            }

            if (th.rcodes.get(curr) != null) {
               writer.write((Character)th.rcodes.get(curr));
               curr = "";
            }
         }
      } catch (IOException var35) {
         System.out.println("An error occurred while reading the file: " + var35.getMessage());
      } finally {
         try {
            if (fileReader != null) {
               fileReader.close();
            }
         } catch (IOException var33) {
            System.out.println("Error while closing the file: " + var33.getMessage());
         }

      }

      scanner.close();
      writer.close();
      System.out.println("\"" + useInput + ".DECODED\" is the decoded file's path. ");
   }

   public static void iterateFileCharacters(String filePath) {
      FileReader fileReader = null;

      try {
         fileReader = new FileReader(filePath);

         int character;
         while((character = fileReader.read()) != -1) {
            map.put((char)character, (Integer)map.getOrDefault((char)character, 0) + 1);
         }
      } catch (IOException var11) {
         System.out.println("An error occurred while reading the file: " + var11.getMessage());
      } finally {
         try {
            if (fileReader != null) {
               fileReader.close();
            }
         } catch (IOException var10) {
            System.out.println("Error while closing the file: " + var10.getMessage());
         }

      }

   }
}
