package cn.wwah.common.event.inner;


import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

import cn.wwah.common.event.EventSubscribe;
import rx.subscriptions.CompositeSubscription;

/**
 * @Description: 根据注解查找事件接收方法
 * @author: jeasinlee
 * @date: 2016-12-29 19:26
 */
public class EventFind {
    public static EventComposite findAnnotatedSubscriberMethods(Object listenerClass, CompositeSubscription compositeSubscription) {
        Set<EventSubscriber> producerMethods = new HashSet<>();
        return findAnnotatedMethods(listenerClass, producerMethods, compositeSubscription);
    }

    private static EventComposite findAnnotatedMethods(Object listenerClass, Set<EventSubscriber> subscriberMethods,
                                                       CompositeSubscription compositeSubscription) {
        for (Method method : listenerClass.getClass().getDeclaredMethods()) {
            if (method.isBridge()) {
                continue;
            }
            if (method.isAnnotationPresent(EventSubscribe.class)) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length != 1) {
                    throw new IllegalArgumentException("Method " + method + " has @Subscribe annotation but requires " + parameterTypes
                            .length + " arguments.  Methods must require a single argument.");
                }

                Class<?> parameterClazz = parameterTypes[0];
                if ((method.getModifiers() & Modifier.PUBLIC) == 0) {
                    throw new IllegalArgumentException("Method " + method + " has @EventSubscribe annotation on " + parameterClazz + " " +
                            "but is not 'public'.");
                }

                EventSubscribe annotation = method.getAnnotation(EventSubscribe.class);
                EventThread thread = annotation.thread();

                EventSubscriber subscriberEvent = new EventSubscriber(listenerClass, method, thread);
                if (!subscriberMethods.contains(subscriberEvent)) {
                    subscriberMethods.add(subscriberEvent);
                    compositeSubscription.add(subscriberEvent.getSubscription());
                }
            }
        }
        return new EventComposite(compositeSubscription, listenerClass, subscriberMethods);
    }
}
