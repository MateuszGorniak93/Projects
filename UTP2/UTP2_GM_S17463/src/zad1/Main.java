/**
 *
 *  @author Górniak Mateusz S17463
 *
 */

package zad1;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/*<--
 *  niezbędne importy
 */
public class Main {
  public static void main(String[] args) {

    Function<Object,List<String>> flines = e ->{
      List<String> list = new ArrayList<>();
      if (e instanceof String){
        File file = new File((String)e);
        if(file.exists()){
          try {
            BufferedReader br = new BufferedReader(new FileReader((String) e));
            String current;
            while ((current = br.readLine()) != null){
              list.add(current);
            }
          } catch (IOException e1) {
            e1.printStackTrace();
          }
        }else{
          return list;
        }
      }else if(e instanceof List){
        for (int i = 0; i < ((List)e).size(); i++) {
          String tmp = ((List<String>)e).get(i);
          File file = new File(tmp);
          if(file.exists()){
            try {
              BufferedReader br = new BufferedReader(new FileReader(tmp));
              String current;
              while ((current = br.readLine()) != null){
                list.add(current);
              }
            } catch (IOException e1) {
              e1.printStackTrace();
            }
          }

        }
      }
      return list;
    };

    Function<List<String>, String> join = f ->{
      String tmp = "";
      for (int i = 0; i < f.size() ; i++) {
       tmp += f.get(i) + " ";
      }
      return tmp;
    };

    Function<String, List<Integer>> collectInts = s ->{

      List<Integer> tmpList = new ArrayList<>();
      String tmpNum = "";
      for (int i = 0; i < s.length(); i++) {
        char tempS = s.charAt(i);
        if (tempS >= 48 && tempS <= 57){
          tmpNum += tempS;
        }else if (tmpNum.length() > 0){
          tmpList.add(Integer.parseInt(tmpNum));
          tmpNum = "";
        }
      }

      return tmpList;
    };

    Function<List<Integer>,Integer> sum = g ->{
      Integer tmp = 0;
      for (int i = 0; i < g.size(); i++) {
        tmp += g.get(i);
      }
      return tmp;
    };


    String fname = System.getProperty("user.home") + "/LamComFile.txt";
    InputConverter<String> fileConv = new InputConverter<>(fname);
    List<String> lines = fileConv.convertBy(flines);
    String text = fileConv.convertBy(flines, join);
    List<Integer> ints = fileConv.convertBy(flines, join, collectInts);
    Integer sumints = fileConv.convertBy(flines, join, collectInts, sum);

    System.out.println(lines);
    System.out.println(text);
    System.out.println(ints);
    System.out.println(sumints);

    List<String> arglist = Arrays.asList(args);
    InputConverter<List<String>> slistConv = new InputConverter<>(arglist);
    sumints = slistConv.convertBy(join, collectInts, sum);
    System.out.println(sumints);

  }
}
