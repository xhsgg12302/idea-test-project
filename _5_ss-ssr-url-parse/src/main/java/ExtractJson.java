import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;

import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

public class ExtractJson {

    public static void main(String[] args) throws Exception{
        String temp1 = "ssr://MTk0LjUuNzguMTkxOjMzODg6b3JpZ2luOnJjNDpwbGFpbjpiRzVqYmk1dmNtY2dPR1YwLz9vYmZzcGFyYW09JnJlbWFya3M9NUwtRTZMLWM1TGljUVEmZ3JvdXA9VEc1amJpNXZjbWM,ssr://Mi41Ni4yNDAuMTA2OjMzODg6b3JpZ2luOnJjNDpwbGFpbjpiRzVqYmk1dmNtY2dPR1YwLz9vYmZzcGFyYW09JnJlbWFya3M9NUwtRTZMLWM1TGljUWcmZ3JvdXA9VEc1amJpNXZjbWM,ssr://NDUuOC4xNTkuNDM6MzM4ODpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09HVjAvP29iZnNwYXJhbT0mcmVtYXJrcz01TC1FNkwtYzVMaWNRdyZncm91cD1URzVqYmk1dmNtYw,ssr://OTEuMjA2LjkyLjM4OjMzODg6b3JpZ2luOnJjNDpwbGFpbjpiRzVqYmk1dmNtY2dPR1YwLz9vYmZzcGFyYW09JnJlbWFya3M9NUwtRTZMLWM1TGljUkEmZ3JvdXA9VEc1amJpNXZjbWM,ssr://NDUuMTM1LjEzNS4xNjE6MzM4ODpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09UWm4vP29iZnNwYXJhbT0mcmVtYXJrcz02STZyNXBhdjU2ZVJSUSZncm91cD1URzVqYmk1dmNtYw,ssr://ODUuMTE3LjIzNS4yNTE6MzM4ODpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09UWm4vP29iZnNwYXJhbT0mcmVtYXJrcz02STZyNXBhdjU2ZVJSZyZncm91cD1URzVqYmk1dmNtYw,ssr://NDUuMTQ3LjIwMC43NzozMzg4Om9yaWdpbjpyYzQ6cGxhaW46Ykc1amJpNXZjbWNnT1Rabi8_b2Jmc3BhcmFtPSZyZW1hcmtzPTZJNnI1cGF2NTZlUlJ3Jmdyb3VwPVRHNWpiaTV2Y21j,ssr://NDUuMTQwLjE2OC4zNjozMzg4Om9yaWdpbjpyYzQ6cGxhaW46Ykc1amJpNXZjbWNnT1Rabi8_b2Jmc3BhcmFtPSZyZW1hcmtzPTZJNnI1cGF2NTZlUlNBJmdyb3VwPVRHNWpiaTV2Y21j,ssr://NDUuMTQ0LjMuMzM6MzM4ODpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09IbGkvP29iZnNwYXJhbT0mcmVtYXJrcz02STZyNXBhdjU2ZVJTUSZncm91cD1URzVqYmk1dmNtYw,ssr://NDUuMTM1LjEzNC4xOTQ6MzM4ODpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09IbGkvP29iZnNwYXJhbT0mcmVtYXJrcz02STZyNXBhdjU2ZVJTZyZncm91cD1URzVqYmk1dmNtYw,ssr://NDUuMTM1LjEzNS4xODA6MzM4ODpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09IbGkvP29iZnNwYXJhbT0mcmVtYXJrcz02STZyNXBhdjU2ZVJTdyZncm91cD1URzVqYmk1dmNtYw,ssr://NDUuMTM1LjEzNS4yMDE6MzM4ODpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09IbGkvP29iZnNwYXJhbT0mcmVtYXJrcz02STZyNXBhdjU2ZVJUQSZncm91cD1URzVqYmk1dmNtYw,ssr://MTM5LjI4LjIzNS4yNDM6MzM4ODpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ05taGwvP29iZnNwYXJhbT0mcmVtYXJrcz01clNiNXAySjU1LTJRU0JsY25JJmdyb3VwPVRHNWpiaTV2Y21j,ssr://MTM5LjI4LjIzNS44NDozMzg4Om9yaWdpbjpyYzQ6cGxhaW46Ykc1amJpNXZjbWNnTm1obC8_b2Jmc3BhcmFtPSZyZW1hcmtzPTVyU2I1cDJKNTUtMlFnJmdyb3VwPVRHNWpiaTV2Y21j,ssr://NDUuODIuMjU1LjExNTozMzg4Om9yaWdpbjpyYzQ6cGxhaW46Ykc1amJpNXZjbWNnTm1obC8_b2Jmc3BhcmFtPSZyZW1hcmtzPTVyU2I1cDJKNTUtMlF3Jmdyb3VwPVRHNWpiaTV2Y21j,ssr://NDUuMTIuMTA5LjEzNDozMzg4Om9yaWdpbjpyYzQ6cGxhaW46Ykc1amJpNXZjbWNnTm1obC8_b2Jmc3BhcmFtPSZyZW1hcmtzPTVyU2I1cDJKNTUtMlJDQWdaWEp5Jmdyb3VwPVRHNWpiaTV2Y21j";
        String temp2 = "ssr://MTk0LjUuNzguMTkxOjMzODg6b3JpZ2luOnJjNDpwbGFpbjpiRzVqYmk1dmNtY2dPR1YwLz9vYmZzcGFyYW09JnJlbWFya3M9NUwtRTZMLWM1TGljUVEmZ3JvdXA9VEc1amJpNXZjbWM,ssr://Mi41Ni4yNDAuMTA2OjMzODg6b3JpZ2luOnJjNDpwbGFpbjpiRzVqYmk1dmNtY2dPR1YwLz9vYmZzcGFyYW09JnJlbWFya3M9NUwtRTZMLWM1TGljUWcmZ3JvdXA9VEc1amJpNXZjbWM,ssr://NDUuOC4xNTkuNDM6MzM4ODpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09HVjAvP29iZnNwYXJhbT0mcmVtYXJrcz01TC1FNkwtYzVMaWNRdyZncm91cD1URzVqYmk1dmNtYw,ssr://OTEuMjA2LjkyLjM4OjMzODg6b3JpZ2luOnJjNDpwbGFpbjpiRzVqYmk1dmNtY2dPR1YwLz9vYmZzcGFyYW09JnJlbWFya3M9NUwtRTZMLWM1TGljUkEmZ3JvdXA9VEc1amJpNXZjbWM,ssr://NDUuMTM1LjEzNS4xNjE6MzM4ODpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09UWm4vP29iZnNwYXJhbT0mcmVtYXJrcz02STZyNXBhdjU2ZVJSUSZncm91cD1URzVqYmk1dmNtYw,ssr://ODUuMTE3LjIzNS4yNTE6MzM4ODpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09UWm4vP29iZnNwYXJhbT0mcmVtYXJrcz02STZyNXBhdjU2ZVJSZyZncm91cD1URzVqYmk1dmNtYw,ssr://NDUuMTQ3LjIwMC43NzozMzg4Om9yaWdpbjpyYzQ6cGxhaW46Ykc1amJpNXZjbWNnT1Rabi8_b2Jmc3BhcmFtPSZyZW1hcmtzPTZJNnI1cGF2NTZlUlJ3Jmdyb3VwPVRHNWpiaTV2Y21j,ssr://NDUuMTQwLjE2OC4zNjozMzg4Om9yaWdpbjpyYzQ6cGxhaW46Ykc1amJpNXZjbWNnT1Rabi8_b2Jmc3BhcmFtPSZyZW1hcmtzPTZJNnI1cGF2NTZlUlNBJmdyb3VwPVRHNWpiaTV2Y21j,ssr://NDUuMTQ0LjMuMzM6MzM4ODpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09IbGkvP29iZnNwYXJhbT0mcmVtYXJrcz02STZyNXBhdjU2ZVJTUSZncm91cD1URzVqYmk1dmNtYw,ssr://NDUuMTM1LjEzNC4xOTQ6MzM4ODpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09IbGkvP29iZnNwYXJhbT0mcmVtYXJrcz02STZyNXBhdjU2ZVJTZyZncm91cD1URzVqYmk1dmNtYw,ssr://NDUuMTM1LjEzNS4xODA6MzM4ODpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09IbGkvP29iZnNwYXJhbT0mcmVtYXJrcz02STZyNXBhdjU2ZVJTdyZncm91cD1URzVqYmk1dmNtYw,ssr://NDUuMTM1LjEzNS4yMDE6MzM4ODpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09IbGkvP29iZnNwYXJhbT0mcmVtYXJrcz02STZyNXBhdjU2ZVJUQSZncm91cD1URzVqYmk1dmNtYw,ssr://MTM5LjI4LjIzNS4yNDM6MzM4ODpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ05taGwvP29iZnNwYXJhbT0mcmVtYXJrcz01clNiNXAySjU1LTJRU0JsY25JJmdyb3VwPVRHNWpiaTV2Y21j,ssr://MTM5LjI4LjIzNS44NDozMzg4Om9yaWdpbjpyYzQ6cGxhaW46Ykc1amJpNXZjbWNnTm1obC8_b2Jmc3BhcmFtPSZyZW1hcmtzPTVyU2I1cDJKNTUtMlFnJmdyb3VwPVRHNWpiaTV2Y21j,ssr://NDUuODIuMjU1LjExNTozMzg4Om9yaWdpbjpyYzQ6cGxhaW46Ykc1amJpNXZjbWNnTm1obC8_b2Jmc3BhcmFtPSZyZW1hcmtzPTVyU2I1cDJKNTUtMlF3Jmdyb3VwPVRHNWpiaTV2Y21j,ssr://NDUuMTIuMTA5LjEzNDozMzg4Om9yaWdpbjpyYzQ6cGxhaW46Ykc1amJpNXZjbWNnTm1obC8_b2Jmc3BhcmFtPSZyZW1hcmtzPTVyU2I1cDJKNTUtMlJDQWdaWEp5Jmdyb3VwPVRHNWpiaTV2Y21j";
        String temp3 = "ssr://MTk0LjUuNzguMTkxOjMyNjY6b3JpZ2luOnJjNDpwbGFpbjpiRzVqYmk1dmNtY2dPR1YzLz9vYmZzcGFyYW09JnJlbWFya3M9NUwtRTZMLWM1TGljUVEmZ3JvdXA9VEc1amJpNXZjbWM,ssr://Mi41Ni4yNDAuMTA2OjMyNjY6b3JpZ2luOnJjNDpwbGFpbjpiRzVqYmk1dmNtY2dPR1YzLz9vYmZzcGFyYW09JnJlbWFya3M9NUwtRTZMLWM1TGljUWcmZ3JvdXA9VEc1amJpNXZjbWM,ssr://NDUuOC4xNTkuNDM6MzI2NjpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09HVjMvP29iZnNwYXJhbT0mcmVtYXJrcz01TC1FNkwtYzVMaWNRdyZncm91cD1URzVqYmk1dmNtYw,ssr://OTEuMjA2LjkyLjM4OjMyNjY6b3JpZ2luOnJjNDpwbGFpbjpiRzVqYmk1dmNtY2dPR1YzLz9vYmZzcGFyYW09JnJlbWFya3M9NUwtRTZMLWM1TGljUkEmZ3JvdXA9VEc1amJpNXZjbWM,ssr://NDUuMTM1LjEzNS4xNjE6MzI2NjpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09HaGwvP29iZnNwYXJhbT0mcmVtYXJrcz02STZyNXBhdjU2ZVJSUSZncm91cD1URzVqYmk1dmNtYw,ssr://ODUuMTE3LjIzNS4yNTE6MzI2NjpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09HaGwvP29iZnNwYXJhbT0mcmVtYXJrcz02STZyNXBhdjU2ZVJSZyZncm91cD1URzVqYmk1dmNtYw,ssr://NDUuMTQ3LjIwMC43NzozMjY2Om9yaWdpbjpyYzQ6cGxhaW46Ykc1amJpNXZjbWNnT0dobC8_b2Jmc3BhcmFtPSZyZW1hcmtzPTZJNnI1cGF2NTZlUlJ3Jmdyb3VwPVRHNWpiaTV2Y21j,ssr://NDUuMTQwLjE2OC4zNjozMjY2Om9yaWdpbjpyYzQ6cGxhaW46Ykc1amJpNXZjbWNnT0dobC8_b2Jmc3BhcmFtPSZyZW1hcmtzPTZJNnI1cGF2NTZlUlNBJmdyb3VwPVRHNWpiaTV2Y21j,ssr://NDUuMTQ0LjMuMzM6MzI2NjpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09YVnIvP29iZnNwYXJhbT0mcmVtYXJrcz02STZyNXBhdjU2ZVJTUSZncm91cD1URzVqYmk1dmNtYw,ssr://NDUuMTM1LjEzNC4xOTQ6MzI2NjpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09YVnIvP29iZnNwYXJhbT0mcmVtYXJrcz02STZyNXBhdjU2ZVJTZyZncm91cD1URzVqYmk1dmNtYw,ssr://NDUuMTM1LjEzNS4xODA6MzI2NjpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09YVnIvP29iZnNwYXJhbT0mcmVtYXJrcz02STZyNXBhdjU2ZVJTdyZncm91cD1URzVqYmk1dmNtYw,ssr://NDUuMTM1LjEzNS4yMDE6MzI2NjpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09YVnIvP29iZnNwYXJhbT0mcmVtYXJrcz02STZyNXBhdjU2ZVJUQSZncm91cD1URzVqYmk1dmNtYw,ssr://MTM5LjI4LjIzNS4yNDM6MzI2NjpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09HaHkvP29iZnNwYXJhbT0mcmVtYXJrcz01clNiNXAySjU1LTJRU0JsY25JJmdyb3VwPVRHNWpiaTV2Y21j,ssr://MTM5LjI4LjIzNS44NDozMjY2Om9yaWdpbjpyYzQ6cGxhaW46Ykc1amJpNXZjbWNnT0doeS8_b2Jmc3BhcmFtPSZyZW1hcmtzPTVyU2I1cDJKNTUtMlFnJmdyb3VwPVRHNWpiaTV2Y21j,ssr://NDUuODIuMjU1LjExNTozMjY2Om9yaWdpbjpyYzQ6cGxhaW46Ykc1amJpNXZjbWNnT0doeS8_b2Jmc3BhcmFtPSZyZW1hcmtzPTVyU2I1cDJKNTUtMlF3Jmdyb3VwPVRHNWpiaTV2Y21j,ssr://NDUuMTIuMTA5LjEzNDozMjY2Om9yaWdpbjpyYzQ6cGxhaW46Ykc1amJpNXZjbWNnT0doeS8_b2Jmc3BhcmFtPSZyZW1hcmtzPTVyU2I1cDJKNTUtMlJDQmxjbkkmZ3JvdXA9VEc1amJpNXZjbWM";
        String temp4 = "ssr://MTk0LjUuNzguMTkxOjMyNjY6b3JpZ2luOnJjNDpwbGFpbjpiRzVqYmk1dmNtY2dPR1YzLz9vYmZzcGFyYW09JnJlbWFya3M9NUwtRTZMLWM1TGljUVEmZ3JvdXA9VEc1amJpNXZjbWM,ssr://Mi41Ni4yNDAuMTA2OjMyNjY6b3JpZ2luOnJjNDpwbGFpbjpiRzVqYmk1dmNtY2dPR1YzLz9vYmZzcGFyYW09JnJlbWFya3M9NUwtRTZMLWM1TGljUWcmZ3JvdXA9VEc1amJpNXZjbWM,ssr://NDUuOC4xNTkuNDM6MzI2NjpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09HVjMvP29iZnNwYXJhbT0mcmVtYXJrcz01TC1FNkwtYzVMaWNRdyZncm91cD1URzVqYmk1dmNtYw,ssr://OTEuMjA2LjkyLjM4OjMyNjY6b3JpZ2luOnJjNDpwbGFpbjpiRzVqYmk1dmNtY2dPR1YzLz9vYmZzcGFyYW09JnJlbWFya3M9NUwtRTZMLWM1TGljUkEmZ3JvdXA9VEc1amJpNXZjbWM,ssr://NDUuMTM1LjEzNS4xNjE6MzI2NjpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09HaGwvP29iZnNwYXJhbT0mcmVtYXJrcz02STZyNXBhdjU2ZVJSUSZncm91cD1URzVqYmk1dmNtYw,ssr://ODUuMTE3LjIzNS4yNTE6MzI2NjpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09HaGwvP29iZnNwYXJhbT0mcmVtYXJrcz02STZyNXBhdjU2ZVJSZyZncm91cD1URzVqYmk1dmNtYw,ssr://NDUuMTQ3LjIwMC43NzozMjY2Om9yaWdpbjpyYzQ6cGxhaW46Ykc1amJpNXZjbWNnT0dobC8_b2Jmc3BhcmFtPSZyZW1hcmtzPTZJNnI1cGF2NTZlUlJ3Jmdyb3VwPVRHNWpiaTV2Y21j,ssr://NDUuMTQwLjE2OC4zNjozMjY2Om9yaWdpbjpyYzQ6cGxhaW46Ykc1amJpNXZjbWNnT0dobC8_b2Jmc3BhcmFtPSZyZW1hcmtzPTZJNnI1cGF2NTZlUlNBJmdyb3VwPVRHNWpiaTV2Y21j,ssr://NDUuMTQ0LjMuMzM6MzI2NjpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09YVnIvP29iZnNwYXJhbT0mcmVtYXJrcz02STZyNXBhdjU2ZVJTUSZncm91cD1URzVqYmk1dmNtYw,ssr://NDUuMTM1LjEzNC4xOTQ6MzI2NjpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09YVnIvP29iZnNwYXJhbT0mcmVtYXJrcz02STZyNXBhdjU2ZVJTZyZncm91cD1URzVqYmk1dmNtYw,ssr://NDUuMTM1LjEzNS4xODA6MzI2NjpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09YVnIvP29iZnNwYXJhbT0mcmVtYXJrcz02STZyNXBhdjU2ZVJTdyZncm91cD1URzVqYmk1dmNtYw,ssr://NDUuMTM1LjEzNS4yMDE6MzI2NjpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09YVnIvP29iZnNwYXJhbT0mcmVtYXJrcz02STZyNXBhdjU2ZVJUQSZncm91cD1URzVqYmk1dmNtYw,ssr://MTM5LjI4LjIzNS4yNDM6MzI2NjpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09HaHkvP29iZnNwYXJhbT0mcmVtYXJrcz01clNiNXAySjU1LTJRU0JsY25JJmdyb3VwPVRHNWpiaTV2Y21j,ssr://MTM5LjI4LjIzNS44NDozMjY2Om9yaWdpbjpyYzQ6cGxhaW46Ykc1amJpNXZjbWNnT0doeS8_b2Jmc3BhcmFtPSZyZW1hcmtzPTVyU2I1cDJKNTUtMlFnJmdyb3VwPVRHNWpiaTV2Y21j,ssr://NDUuODIuMjU1LjExNTozMjY2Om9yaWdpbjpyYzQ6cGxhaW46Ykc1amJpNXZjbWNnT0doeS8_b2Jmc3BhcmFtPSZyZW1hcmtzPTVyU2I1cDJKNTUtMlF3Jmdyb3VwPVRHNWpiaTV2Y21j,ssr://NDUuMTIuMTA5LjEzNDozMjY2Om9yaWdpbjpyYzQ6cGxhaW46Ykc1amJpNXZjbWNnT0doeS8_b2Jmc3BhcmFtPSZyZW1hcmtzPTVyU2I1cDJKNTUtMlJDQmxjbkkmZ3JvdXA9VEc1amJpNXZjbWM";
        String temp5 = "ssr://MTk0LjUuNzguMTkxOjMyNjY6b3JpZ2luOnJjNDpwbGFpbjpiRzVqYmk1dmNtY2dPR1YzLz9vYmZzcGFyYW09JnJlbWFya3M9NUwtRTZMLWM1TGljUVEmZ3JvdXA9VEc1amJpNXZjbWM,ssr://Mi41Ni4yNDAuMTA2OjMyNjY6b3JpZ2luOnJjNDpwbGFpbjpiRzVqYmk1dmNtY2dPR1YzLz9vYmZzcGFyYW09JnJlbWFya3M9NUwtRTZMLWM1TGljUWcmZ3JvdXA9VEc1amJpNXZjbWM,ssr://NDUuOC4xNTkuNDM6MzI2NjpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09HVjMvP29iZnNwYXJhbT0mcmVtYXJrcz01TC1FNkwtYzVMaWNRdyZncm91cD1URzVqYmk1dmNtYw,ssr://OTEuMjA2LjkyLjM4OjMyNjY6b3JpZ2luOnJjNDpwbGFpbjpiRzVqYmk1dmNtY2dPR1YzLz9vYmZzcGFyYW09JnJlbWFya3M9NUwtRTZMLWM1TGljUkEmZ3JvdXA9VEc1amJpNXZjbWM,ssr://NDUuMTM1LjEzNS4xNjE6MzI2NjpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09HaGwvP29iZnNwYXJhbT0mcmVtYXJrcz02STZyNXBhdjU2ZVJSUSZncm91cD1URzVqYmk1dmNtYw,ssr://ODUuMTE3LjIzNS4yNTE6MzI2NjpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09HaGwvP29iZnNwYXJhbT0mcmVtYXJrcz02STZyNXBhdjU2ZVJSZyZncm91cD1URzVqYmk1dmNtYw,ssr://NDUuMTQ3LjIwMC43NzozMjY2Om9yaWdpbjpyYzQ6cGxhaW46Ykc1amJpNXZjbWNnT0dobC8_b2Jmc3BhcmFtPSZyZW1hcmtzPTZJNnI1cGF2NTZlUlJ3Jmdyb3VwPVRHNWpiaTV2Y21j,ssr://NDUuMTQwLjE2OC4zNjozMjY2Om9yaWdpbjpyYzQ6cGxhaW46Ykc1amJpNXZjbWNnT0dobC8_b2Jmc3BhcmFtPSZyZW1hcmtzPTZJNnI1cGF2NTZlUlNBJmdyb3VwPVRHNWpiaTV2Y21j,ssr://NDUuMTQ0LjMuMzM6MzI2NjpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09YVnIvP29iZnNwYXJhbT0mcmVtYXJrcz02STZyNXBhdjU2ZVJTUSZncm91cD1URzVqYmk1dmNtYw,ssr://NDUuMTM1LjEzNC4xOTQ6MzI2NjpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09YVnIvP29iZnNwYXJhbT0mcmVtYXJrcz02STZyNXBhdjU2ZVJTZyZncm91cD1URzVqYmk1dmNtYw,ssr://NDUuMTM1LjEzNS4xODA6MzI2NjpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09YVnIvP29iZnNwYXJhbT0mcmVtYXJrcz02STZyNXBhdjU2ZVJTdyZncm91cD1URzVqYmk1dmNtYw,ssr://NDUuMTM1LjEzNS4yMDE6MzI2NjpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09YVnIvP29iZnNwYXJhbT0mcmVtYXJrcz02STZyNXBhdjU2ZVJUQSZncm91cD1URzVqYmk1dmNtYw,ssr://MTM5LjI4LjIzNS4yNDM6MzI2NjpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09HaHkvP29iZnNwYXJhbT0mcmVtYXJrcz01clNiNXAySjU1LTJRU0JsY25JJmdyb3VwPVRHNWpiaTV2Y21j,ssr://MTM5LjI4LjIzNS44NDozMjY2Om9yaWdpbjpyYzQ6cGxhaW46Ykc1amJpNXZjbWNnT0doeS8_b2Jmc3BhcmFtPSZyZW1hcmtzPTVyU2I1cDJKNTUtMlFnJmdyb3VwPVRHNWpiaTV2Y21j,ssr://NDUuODIuMjU1LjExNTozMjY2Om9yaWdpbjpyYzQ6cGxhaW46Ykc1amJpNXZjbWNnT0doeS8_b2Jmc3BhcmFtPSZyZW1hcmtzPTVyU2I1cDJKNTUtMlF3Jmdyb3VwPVRHNWpiaTV2Y21j,ssr://NDUuMTIuMTA5LjEzNDozMjY2Om9yaWdpbjpyYzQ6cGxhaW46Ykc1amJpNXZjbWNnT0doeS8_b2Jmc3BhcmFtPSZyZW1hcmtzPTVyU2I1cDJKNTUtMlJDQmxjbkkmZ3JvdXA9VEc1amJpNXZjbWM";

        String temp = "ssr://MTk0LjUuNzguMTkxOjU2NTM6b3JpZ2luOnJjNDpwbGFpbjpiRzVqYmk1dmNtY2dOR2h5Lz9vYmZzcGFyYW09JnJlbWFya3M9NUwtRTZMLWM1TGljUVEmZ3JvdXA9VEc1amJpNXZjbWM,ssr://Mi41Ni4yNDAuMTA2OjU2NTM6b3JpZ2luOnJjNDpwbGFpbjpiRzVqYmk1dmNtY2dOR2h5Lz9vYmZzcGFyYW09JnJlbWFya3M9NUwtRTZMLWM1TGljUWcmZ3JvdXA9VEc1amJpNXZjbWM,ssr://NDUuOC4xNTkuNDM6NTY1MzpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ05HaHkvP29iZnNwYXJhbT0mcmVtYXJrcz01TC1FNkwtYzVMaWNRdyZncm91cD1URzVqYmk1dmNtYw,ssr://OTEuMjA2LjkyLjM4OjU2NTM6b3JpZ2luOnJjNDpwbGFpbjpiRzVqYmk1dmNtY2dOR2h5Lz9vYmZzcGFyYW09JnJlbWFya3M9NUwtRTZMLWM1TGljUkEmZ3JvdXA9VEc1amJpNXZjbWM,ssr://NDUuMTM1LjEzNS4xNjE6NTY1MzpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09EaG8vP29iZnNwYXJhbT0mcmVtYXJrcz02STZyNXBhdjU2ZVJSUSZncm91cD1URzVqYmk1dmNtYw,ssr://ODUuMTE3LjIzNS4yNTE6NTY1MzpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09EaG8vP29iZnNwYXJhbT0mcmVtYXJrcz02STZyNXBhdjU2ZVJSZyZncm91cD1URzVqYmk1dmNtYw,ssr://NDUuMTQ3LjIwMC43Nzo1NjUzOm9yaWdpbjpyYzQ6cGxhaW46Ykc1amJpNXZjbWNnT0Roby8_b2Jmc3BhcmFtPSZyZW1hcmtzPTZJNnI1cGF2NTZlUlJ3Jmdyb3VwPVRHNWpiaTV2Y21j,ssr://NDUuMTQwLjE2OC4zNjo1NjUzOm9yaWdpbjpyYzQ6cGxhaW46Ykc1amJpNXZjbWNnT0Roby8_b2Jmc3BhcmFtPSZyZW1hcmtzPTZJNnI1cGF2NTZlUlNBJmdyb3VwPVRHNWpiaTV2Y21j,ssr://NDUuMTQ0LjMuMzM6NTY1MzpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ00ycHkvP29iZnNwYXJhbT0mcmVtYXJrcz02STZyNXBhdjU2ZVJTUSZncm91cD1URzVqYmk1dmNtYw,ssr://NDUuMTM1LjEzNC4xOTQ6NTY1MzpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ00ycHkvP29iZnNwYXJhbT0mcmVtYXJrcz02STZyNXBhdjU2ZVJTZyZncm91cD1URzVqYmk1dmNtYw,ssr://NDUuMTM1LjEzNS4xODA6NTY1MzpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ00ycHkvP29iZnNwYXJhbT0mcmVtYXJrcz02STZyNXBhdjU2ZVJTdyZncm91cD1URzVqYmk1dmNtYw,ssr://NDUuMTM1LjEzNS4yMDE6NTY1MzpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ00ycHkvP29iZnNwYXJhbT0mcmVtYXJrcz02STZyNXBhdjU2ZVJTdyZncm91cD1URzVqYmk1dmNtYw,ssr://MTM5LjI4LjIzNS4yNDM6NTY1MzpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ09HaHkvP29iZnNwYXJhbT0mcmVtYXJrcz01clNiNXAySjU1LTJRU0JsY25JJmdyb3VwPVRHNWpiaTV2Y21j,ssr://MTM5LjI4LjIzNS44NDo1NjUzOm9yaWdpbjpyYzQ6cGxhaW46Ykc1amJpNXZjbWNnT0doeS8_b2Jmc3BhcmFtPSZyZW1hcmtzPTVyU2I1cDJKNTUtMlFnJmdyb3VwPVRHNWpiaTV2Y21j,ssr://NDUuODIuMjU1LjExNTo1NjUzOm9yaWdpbjpyYzQ6cGxhaW46Ykc1amJpNXZjbWNnT0doeS8_b2Jmc3BhcmFtPSZyZW1hcmtzPTVyU2I1cDJKNTUtMlF3Jmdyb3VwPVRHNWpiaTV2Y21j,ssr://NDUuMTIuMTA5LjEzNDo1NjUzOm9yaWdpbjpyYzQ6cGxhaW46Ykc1amJpNXZjbWNnT0doeS8_b2Jmc3BhcmFtPSZyZW1hcmtzPTVyU2I1cDJKNTUtMlJDQmxjbkkmZ3JvdXA9VEc1amJpNXZjbWM";

        String[] strArr = temp.split(",");

        StringBuffer stringBuffer = new StringBuffer();
        for (String s : strArr) {
            stringBuffer.append(ssrParse(decode(s.substring(6))) + ",");
        }
        System.out.println(stringBuffer.toString());
        replaceConfigs(stringBuffer.toString());
    }


    public static String ssrParse(String ssr){
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

        return JSONObject.toJSON(result) + "";

    }


    public static void replaceConfigs(String replaceStr) throws Exception{
        //0 先解析replaceStr
        replaceStr = "[" + replaceStr.substring(0,replaceStr.length()-1) + "]";
        Object replace = JSONArray.parse(replaceStr);


        //1读取原来的文件并转换为json对象
        FileChannel open = FileChannel.open(Paths.get("/Users/stevenobelia/Documents/export.json"), StandardOpenOption.READ ,
                StandardOpenOption.WRITE ,StandardOpenOption.CREATE);
        MappedByteBuffer readMappedByteBuffer = open.map(FileChannel.MapMode.READ_WRITE, 0, open.size());
        byte[] bytes = new byte[ readMappedByteBuffer.limit() ];
        readMappedByteBuffer.get(bytes, 0, bytes.length);
        open.close();
        //System.out.println(new String(bytes));
        JSONObject parse = (JSONObject) JSONObject.parse(new String(bytes));
        parse.put("configs",replace);


        //
        System.out.println(parse.toJSONString());
        byte[] temp = parse.toJSONString().getBytes();


        FileChannel write = FileChannel.open(Paths.get("/Users/stevenobelia/Documents/export.json"), StandardOpenOption.READ ,
                StandardOpenOption.WRITE ,StandardOpenOption.CREATE ,StandardOpenOption.TRUNCATE_EXISTING);
        MappedByteBuffer writeMappedByteBuffer = write.map(FileChannel.MapMode.READ_WRITE, 0, temp.length);
        writeMappedByteBuffer.put(temp,0, temp.length);
        writeMappedByteBuffer.clear();

        write.close();
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
