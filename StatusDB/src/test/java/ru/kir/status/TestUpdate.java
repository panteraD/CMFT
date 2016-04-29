package ru.kir.status;

import junit.framework.Assert;
import org.junit.Ignore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.kir.status.service.ParamServiceImpl;

import static ru.kir.status.common.AttributeType.*;
/**
 * Created by Kirill Zhitelev on 09.03.2016.
 */
public class TestUpdate {

    //ignore test to deploy successfully
    @Ignore
    @org.junit.Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("/spring/db.config.xml");
        ParamServiceImpl paramService = context.getBean(ParamServiceImpl.class);
        String begin = "19:00";

        paramService.updateParam(BEGIN, LOGIN, "anna", begin);

        Assert.assertEquals(begin, paramService.getParam(BEGIN, LOGIN, "anna").getValue());
    }

}
