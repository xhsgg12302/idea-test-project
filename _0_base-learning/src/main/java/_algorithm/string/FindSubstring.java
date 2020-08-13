package _algorithm.string;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 30. 串联所有单词的子串
 *
 */
public class FindSubstring {
    public static void main(String[] args) {
        String s= "pjzkrkevzztxductzzxmxsvwjkxpvukmfjywwetvfnujhweiybwvvsrfequzkhossmootkmyxgjgfordrpapjuunmqnxxdrqrfgkrsjqbszgiqlcfnrpjlcwdrvbumtotzylshdvccdmsqoadfrpsvnwpizlwszrtyclhgilklydbmfhuywotjmktnwrfvizvnmfvvqfiokkdprznnnjycttprkxpuykhmpchiksyucbmtabiqkisgbhxngmhezrrqvayfsxauampdpxtafniiwfvdufhtwajrbkxtjzqjnfocdhekumttuqwovfjrgulhekcpjszyynadxhnttgmnxkduqmmyhzfnjhducesctufqbumxbamalqudeibljgbspeotkgvddcwgxidaiqcvgwykhbysjzlzfbupkqunuqtraxrlptivshhbihtsigtpipguhbhctcvubnhqipncyxfjebdnjyetnlnvmuxhzsdahkrscewabejifmxombiamxvauuitoltyymsarqcuuoezcbqpdaprxmsrickwpgwpsoplhugbikbkotzrtqkscekkgwjycfnvwfgdzogjzjvpcvixnsqsxacfwndzvrwrycwxrcismdhqapoojegggkocyrdtkzmiekhxoppctytvphjynrhtcvxcobxbcjjivtfjiwmduhzjokkbctweqtigwfhzorjlkpuuliaipbtfldinyetoybvugevwvhhhweejogrghllsouipabfafcxnhukcbtmxzshoyyufjhzadhrelweszbfgwpkzlwxkogyogutscvuhcllphshivnoteztpxsaoaacgxyaztuixhunrowzljqfqrahosheukhahhbiaxqzfmmwcjxountkevsvpbzjnilwpoermxrtlfroqoclexxisrdhvfsindffslyekrzwzqkpeocilatftymodgztjgybtyheqgcpwogdcjlnlesefgvimwbxcbzvaibspdjnrpqtyeilkcspknyylbwndvkffmzuriilxagyerjptbgeqgebiaqnvdubrtxibhvakcyotkfonmseszhczapxdlauexehhaireihxsplgdgmxfvaevrbadbwjbdrkfbbjjkgcztkcbwagtcnrtqryuqixtzhaakjlurnumzyovawrcjiwabuwretmdamfkxrgqgcdgbrdbnugzecbgyxxdqmisaqcyjkqrntxqmdrczxbebemcblftxplafnyoxqimkhcykwamvdsxjezkpgdpvopddptdfbprjustquhlazkjfluxrzopqdstulybnqvyknrchbphcarknnhhovweaqawdyxsqsqahkepluypwrzjegqtdoxfgzdkydeoxvrfhxusrujnmjzqrrlxglcmkiykldbiasnhrjbjekystzilrwkzhontwmehrfsrzfaqrbbxncphbzuuxeteshyrveamjsfiaharkcqxefghgceeixkdgkuboupxnwhnfigpkwnqdvzlydpidcljmflbccarbiegsmweklwngvygbqpescpeichmfidgsjmkvkofvkuehsmkkbocgejoiqcnafvuokelwuqsgkyoekaroptuvekfvmtxtqshcwsztkrzwrpabqrrhnlerxjojemcxel";
        String [] abc = new String[]{"dhvf","sind","ffsl","yekr","zwzq","kpeo","cila","tfty"};
        /*String[] result = solution(abc);
        for (String s1 : result) {
            System.out.println(s1);
        }*/
        enter(s,abc);

    }

    public static void enter(String s, String[] words){
        List<Integer> list = new ArrayList<>();
        String [] result = solution(words);
        for (String st : result) {
            answer(st,s,list);
        }

        System.out.println(list);
    }


    public static void answer(String slidingWindows,String s,List<Integer> list){
        int length = slidingWindows.length();
        for (int i = 0; i <= s.length() - length; i++) {
            if(slidingWindows.equals(s.substring(i,i+length))){
                list.add(i);
            }
        }
    }

    public static String[] solution(String[] origin){
        if(origin.length == 1){
            return origin;
        }

        String [] finalResult = new String[0];
        for (int i = 0; i < origin.length; i++) {
            String current = origin[i];
            String [] others = get(origin,i);
            finalResult = comArray(finalResult,combination(current,solution(others)));
        }
        return finalResult;
    }

    public static String [] get(String [] strings , int i){
        int length = strings.length - 1;
        String [] result = new String[length];
        for (int i1 = 0 ,z = 0; z < length && i1 < strings.length; i1++ ,z ++) {
            if(i1 != i){
                result[z] = strings[i1];
            }else{
                result[z] = strings[++i1];
            }
        }
        return result;
    }


    public static String[] combination(String s1 ,String[] s2){
        int length = s2.length;
        String [] temp = new String[length];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = s1 + s2[i];
        }
        return temp;
    }

    public static String[] comArray(String [] a1,String [] a2){
        int length = a1.length + a2.length;
        String[] result = new String[length];
        int index = 0;
        boolean flag = true;
        for (int i = 0; i < result.length; i++) {
            if(flag){
                try{
                    result[i] = a1[i];
                }catch (Exception e){
                    flag = false;
                    i--;
                }
            }else{
                result[i] = a2[index++];
            }
        }
        return result;
    }
}
