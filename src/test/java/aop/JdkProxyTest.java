package aop;

import core.aop.DynamicInvocationHandler;
import core.aop.Hello;
import core.aop.HelloTarget;
import core.aop.PrefixSayMatcher;
import net.sf.cglib.proxy.Proxy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JdkProxyTest {

    @Test
    void jdk_upper_test() {
        HelloTarget helloTarget = new HelloTarget();

        Hello targetProxy = (Hello) Proxy.newProxyInstance(JdkProxyTest.class.getClassLoader(),
                new Class[]{Hello.class},
                new DynamicInvocationHandler(helloTarget, new PrefixSayMatcher()));

        Assertions.assertAll(
                () -> org.assertj.core.api.Assertions.assertThat(targetProxy.sayHi("java")).isEqualTo("HI JAVA"),
                () -> org.assertj.core.api.Assertions.assertThat(targetProxy.sayHello("java")).isEqualTo("HELLO JAVA"),
                () -> org.assertj.core.api.Assertions.assertThat(targetProxy.sayHello("java")).isEqualTo("HELLO JAVA"),
                () -> org.assertj.core.api.Assertions.assertThat(targetProxy.pinpong("java")).isEqualTo("pingpong java")
        );
    }
}
