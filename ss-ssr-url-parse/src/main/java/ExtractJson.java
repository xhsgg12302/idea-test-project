import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;

import java.util.HashMap;
import java.util.Map;

public class ExtractJson {

    public static void main(String[] args) {
        String temp1 = "ssr://MTk0LjUuNzguMTkxOjMzODg6b3JpZ2luOnJjNDpwbGFpbjpiRzVqYmk1dmNtY2dPR1YwLz9vYmZzcGFyYW09JnJlbWFya3M9NUwtRTZMLWM1TGljUVEmZ3JvdXA9VEc1amJpNXZjbWM,ssr://Mi41Ni4yNDAuMTA2OjMzODg6b3JpZ2luOnJjNDpwbGFpbjpiRzVqYmk1dmNtY2dPR1YwLz9vYmZzcGFyYW09JnJlbWFya3M9NUwtRTZMLWM1TGljUWcmZ3JvdXA9VEc1amJpNXZjbWM,ssr://NDUuOC4xNTkuNDM6MzM4ODpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09HVjAvP29iZnNwYXJhbT0mcmVtYXJrcz01TC1FNkwtYzVMaWNRdyZncm91cD1URzVqYmk1dmNtYw,ssr://OTEuMjA2LjkyLjM4OjMzODg6b3JpZ2luOnJjNDpwbGFpbjpiRzVqYmk1dmNtY2dPR1YwLz9vYmZzcGFyYW09JnJlbWFya3M9NUwtRTZMLWM1TGljUkEmZ3JvdXA9VEc1amJpNXZjbWM,ssr://NDUuMTM1LjEzNS4xNjE6MzM4ODpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09UWm4vP29iZnNwYXJhbT0mcmVtYXJrcz02STZyNXBhdjU2ZVJSUSZncm91cD1URzVqYmk1dmNtYw,ssr://ODUuMTE3LjIzNS4yNTE6MzM4ODpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09UWm4vP29iZnNwYXJhbT0mcmVtYXJrcz02STZyNXBhdjU2ZVJSZyZncm91cD1URzVqYmk1dmNtYw,ssr://NDUuMTQ3LjIwMC43NzozMzg4Om9yaWdpbjpyYzQ6cGxhaW46Ykc1amJpNXZjbWNnT1Rabi8_b2Jmc3BhcmFtPSZyZW1hcmtzPTZJNnI1cGF2NTZlUlJ3Jmdyb3VwPVRHNWpiaTV2Y21j,ssr://NDUuMTQwLjE2OC4zNjozMzg4Om9yaWdpbjpyYzQ6cGxhaW46Ykc1amJpNXZjbWNnT1Rabi8_b2Jmc3BhcmFtPSZyZW1hcmtzPTZJNnI1cGF2NTZlUlNBJmdyb3VwPVRHNWpiaTV2Y21j,ssr://NDUuMTQ0LjMuMzM6MzM4ODpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09IbGkvP29iZnNwYXJhbT0mcmVtYXJrcz02STZyNXBhdjU2ZVJTUSZncm91cD1URzVqYmk1dmNtYw,ssr://NDUuMTM1LjEzNC4xOTQ6MzM4ODpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09IbGkvP29iZnNwYXJhbT0mcmVtYXJrcz02STZyNXBhdjU2ZVJTZyZncm91cD1URzVqYmk1dmNtYw,ssr://NDUuMTM1LjEzNS4xODA6MzM4ODpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09IbGkvP29iZnNwYXJhbT0mcmVtYXJrcz02STZyNXBhdjU2ZVJTdyZncm91cD1URzVqYmk1dmNtYw,ssr://NDUuMTM1LjEzNS4yMDE6MzM4ODpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09IbGkvP29iZnNwYXJhbT0mcmVtYXJrcz02STZyNXBhdjU2ZVJUQSZncm91cD1URzVqYmk1dmNtYw,ssr://MTM5LjI4LjIzNS4yNDM6MzM4ODpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ05taGwvP29iZnNwYXJhbT0mcmVtYXJrcz01clNiNXAySjU1LTJRU0JsY25JJmdyb3VwPVRHNWpiaTV2Y21j,ssr://MTM5LjI4LjIzNS44NDozMzg4Om9yaWdpbjpyYzQ6cGxhaW46Ykc1amJpNXZjbWNnTm1obC8_b2Jmc3BhcmFtPSZyZW1hcmtzPTVyU2I1cDJKNTUtMlFnJmdyb3VwPVRHNWpiaTV2Y21j,ssr://NDUuODIuMjU1LjExNTozMzg4Om9yaWdpbjpyYzQ6cGxhaW46Ykc1amJpNXZjbWNnTm1obC8_b2Jmc3BhcmFtPSZyZW1hcmtzPTVyU2I1cDJKNTUtMlF3Jmdyb3VwPVRHNWpiaTV2Y21j,ssr://NDUuMTIuMTA5LjEzNDozMzg4Om9yaWdpbjpyYzQ6cGxhaW46Ykc1amJpNXZjbWNnTm1obC8_b2Jmc3BhcmFtPSZyZW1hcmtzPTVyU2I1cDJKNTUtMlJDQWdaWEp5Jmdyb3VwPVRHNWpiaTV2Y21j";
        String temp = "ssr://MTk0LjUuNzguMTkxOjMzODg6b3JpZ2luOnJjNDpwbGFpbjpiRzVqYmk1dmNtY2dPR1YwLz9vYmZzcGFyYW09JnJlbWFya3M9NUwtRTZMLWM1TGljUVEmZ3JvdXA9VEc1amJpNXZjbWM,ssr://Mi41Ni4yNDAuMTA2OjMzODg6b3JpZ2luOnJjNDpwbGFpbjpiRzVqYmk1dmNtY2dPR1YwLz9vYmZzcGFyYW09JnJlbWFya3M9NUwtRTZMLWM1TGljUWcmZ3JvdXA9VEc1amJpNXZjbWM,ssr://NDUuOC4xNTkuNDM6MzM4ODpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09HVjAvP29iZnNwYXJhbT0mcmVtYXJrcz01TC1FNkwtYzVMaWNRdyZncm91cD1URzVqYmk1dmNtYw,ssr://OTEuMjA2LjkyLjM4OjMzODg6b3JpZ2luOnJjNDpwbGFpbjpiRzVqYmk1dmNtY2dPR1YwLz9vYmZzcGFyYW09JnJlbWFya3M9NUwtRTZMLWM1TGljUkEmZ3JvdXA9VEc1amJpNXZjbWM,ssr://NDUuMTM1LjEzNS4xNjE6MzM4ODpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09UWm4vP29iZnNwYXJhbT0mcmVtYXJrcz02STZyNXBhdjU2ZVJSUSZncm91cD1URzVqYmk1dmNtYw,ssr://ODUuMTE3LjIzNS4yNTE6MzM4ODpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09UWm4vP29iZnNwYXJhbT0mcmVtYXJrcz02STZyNXBhdjU2ZVJSZyZncm91cD1URzVqYmk1dmNtYw,ssr://NDUuMTQ3LjIwMC43NzozMzg4Om9yaWdpbjpyYzQ6cGxhaW46Ykc1amJpNXZjbWNnT1Rabi8_b2Jmc3BhcmFtPSZyZW1hcmtzPTZJNnI1cGF2NTZlUlJ3Jmdyb3VwPVRHNWpiaTV2Y21j,ssr://NDUuMTQwLjE2OC4zNjozMzg4Om9yaWdpbjpyYzQ6cGxhaW46Ykc1amJpNXZjbWNnT1Rabi8_b2Jmc3BhcmFtPSZyZW1hcmtzPTZJNnI1cGF2NTZlUlNBJmdyb3VwPVRHNWpiaTV2Y21j,ssr://NDUuMTQ0LjMuMzM6MzM4ODpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09IbGkvP29iZnNwYXJhbT0mcmVtYXJrcz02STZyNXBhdjU2ZVJTUSZncm91cD1URzVqYmk1dmNtYw,ssr://NDUuMTM1LjEzNC4xOTQ6MzM4ODpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09IbGkvP29iZnNwYXJhbT0mcmVtYXJrcz02STZyNXBhdjU2ZVJTZyZncm91cD1URzVqYmk1dmNtYw,ssr://NDUuMTM1LjEzNS4xODA6MzM4ODpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09IbGkvP29iZnNwYXJhbT0mcmVtYXJrcz02STZyNXBhdjU2ZVJTdyZncm91cD1URzVqYmk1dmNtYw,ssr://NDUuMTM1LjEzNS4yMDE6MzM4ODpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09IbGkvP29iZnNwYXJhbT0mcmVtYXJrcz02STZyNXBhdjU2ZVJUQSZncm91cD1URzVqYmk1dmNtYw,ssr://MTM5LjI4LjIzNS4yNDM6MzM4ODpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ05taGwvP29iZnNwYXJhbT0mcmVtYXJrcz01clNiNXAySjU1LTJRU0JsY25JJmdyb3VwPVRHNWpiaTV2Y21j,ssr://MTM5LjI4LjIzNS44NDozMzg4Om9yaWdpbjpyYzQ6cGxhaW46Ykc1amJpNXZjbWNnTm1obC8_b2Jmc3BhcmFtPSZyZW1hcmtzPTVyU2I1cDJKNTUtMlFnJmdyb3VwPVRHNWpiaTV2Y21j,ssr://NDUuODIuMjU1LjExNTozMzg4Om9yaWdpbjpyYzQ6cGxhaW46Ykc1amJpNXZjbWNnTm1obC8_b2Jmc3BhcmFtPSZyZW1hcmtzPTVyU2I1cDJKNTUtMlF3Jmdyb3VwPVRHNWpiaTV2Y21j,ssr://NDUuMTIuMTA5LjEzNDozMzg4Om9yaWdpbjpyYzQ6cGxhaW46Ykc1amJpNXZjbWNnTm1obC8_b2Jmc3BhcmFtPSZyZW1hcmtzPTVyU2I1cDJKNTUtMlJDQWdaWEp5Jmdyb3VwPVRHNWpiaTV2Y21j";
        String[] strArr = temp.split(",");

        for (String s : strArr) {
            ssrParse(decode(s.substring(6)));
        }
    }


    public static void ssrParse(String ssr){
        Map<String,Object> result = new HashMap<>();
        String prefix = ssr.substring(0,ssr.indexOf("?"));
        String suffix = ssr.substring(ssr.indexOf("?")+1);

        String[] preParams = prefix.split(":");
        String[] sufParams = suffix.split("&");

        for (int i = 0; i < preParams.length; i++) {
            switch (i){
                case 0: result.put("server",preParams[i]);break;
                case 1: result.put("server_port",Integer.parseInt(preParams[i]));break;
                case 2: result.put("protocol",preParams[i]);break;
                case 3: result.put("method",preParams[i]);break;
                case 4: result.put("obfs",preParams[i]);break;
                case 5: result.put("password",decode(preParams[i]));break;
            }
        }

        for (String param : sufParams) {
            result.put(param.substring(0,param.indexOf("=")),decode(param.substring(param.indexOf("=") + 1 )));
        }
        System.out.println(JSONObject.toJSON(result)+",");
    }


    /**
     * @param decode
     * @return
     */
    public static String decode(String decode) {
        byte[] bytes = decode.getBytes();
        return new String(Base64.decodeBase64(bytes));
    }

    /**
     * 二进制数据编码为BASE64字符串
     *
     * @param bytes
     * @return
     * @throws Exception
     */
    public static String encode(final byte[] bytes) {
        return new String(Base64.encodeBase64(bytes));
    }

}
