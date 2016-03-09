import com.anicloud.sunny.infrastructure.persistence.domain.share.TriggerType;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.EnumSet;

/**
 * Created by zhaoyu on 15-5-26.
 */
public class TestApp {
    @Test
    public void testApp() {
        EnumSet<TriggerType> triggerTypeEnumSet = EnumSet.allOf(TriggerType.class);
        for (TriggerType triggerType : triggerTypeEnumSet) {
            System.out.println(triggerType.ordinal() + " : " + triggerType.name() + " : " + triggerType.toString());
        }
    }

    @Test
    public void testFileWrite() throws IOException {
        InputStream inputStream = TestApp.class.getResourceAsStream("log4j.properties");
        BufferedReader bufferedReader =
                new BufferedReader(new InputStreamReader(inputStream));
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
        bufferedReader.close();
    }
}
