package site.wtfu.framework.config.repeat;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @description: 重复提交注解切面处理
 * @author: Huozy
 * @time: 2021/1/8 13:39
 */
@Aspect
@Component
@Slf4j
public class RepeatSubmitAspect {

    private String httpHeaderName = "token";
    private String lockName="repeatSubmit";

    @Autowired
    private RedisRepeatLock redisLock;

    @Pointcut("@annotation(noRepeatSubmit)")
    public void pointCut(NoRepeatSubmit noRepeatSubmit) {
    }

    @Around("pointCut(noRepeatSubmit)")
    public Object around(ProceedingJoinPoint pjp, NoRepeatSubmit noRepeatSubmit) throws Throwable {
        int lockSeconds = noRepeatSubmit.lockTime();

        HttpServletRequest request = RequestUtils.getRequest();
        Assert.notNull(request, "request can not null");

        // 此处可以用token或者JSessionId
//        String token = request.getHeader("Authorization");
        String token = "hello";
        String path = request.getServletPath();
        String key = getKey(token, path);
        String clientId = getClientId();

        boolean isSuccess = redisLock.tryLock(key, clientId, lockSeconds);
        log.info("tryLock key = [{}], clientId = [{}]", key, clientId);

        if (isSuccess) {
            log.info("tryLock success, key = [{}], clientId = [{}]", key, clientId);
            // 获取锁成功
            Object result;

            try {
                // 执行进程
                result = pjp.proceed();
            } finally {
                // 解锁
                //redisLock.releaseLock(key, clientId);
                log.info("releaseLock success, key = [{}], clientId = [{}]", key, clientId);
            }

            return result;

        } else {
            // 获取锁失败，认为是重复提交的请求
            log.info("tryLock fail, key = [{}]", key);
            return  new JSONObject().fluentPut("500","重复请求，请稍后再试");
        }

    }

    private String getKey(String token, String path) {
        String key=new StringBuilder()
                .append(lockName)
                .append(":")
                .append(token)
                .append(":")
                .append(path)
                .toString();
        return key;
    }

    private String getClientId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
