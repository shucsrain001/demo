import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void testConnection() {
        redisTemplate.opsForValue().set("testKey", "Hello Redis");
        String value = redisTemplate.opsForValue().get("testKey");
        System.out.println("获取Redis测试值: " + value);
    }
}