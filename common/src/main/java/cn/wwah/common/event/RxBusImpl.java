package cn.wwah.common.event;


import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import cn.wwah.common.event.inner.EventComposite;
import cn.wwah.common.event.inner.EventFind;
import cn.wwah.common.event.inner.EventHelper;
import rx.subscriptions.CompositeSubscription;

/**
 * @Description: RxBus事件管理
 * @author: jeasinlee
 * @date: 2016-12-19 15:07
 */
public class RxBusImpl extends EventHelper implements IBus {

    private ConcurrentMap<Object, EventComposite> mEventCompositeMap = new ConcurrentHashMap<>();

    @Override
    public void register(Object object) {
        if (object == null) {
            throw new NullPointerException("Object to register must not be null.");
        }
        CompositeSubscription compositeSubscription = new CompositeSubscription();
        EventComposite subscriberMethods = EventFind.findAnnotatedSubscriberMethods(object, compositeSubscription);
        mEventCompositeMap.put(object, subscriberMethods);

        if (!STICKY_EVENT_MAP.isEmpty()) {
            subscriberMethods.subscriberSticky(STICKY_EVENT_MAP);
        }
    }

    @Override
    public void unregister(Object object) {
        if (object == null) {
            throw new NullPointerException("Object to register must not be null.");
        }
        EventComposite subscriberMethods = mEventCompositeMap.get(object);
        if (subscriberMethods != null) {
            subscriberMethods.getCompositeSubscription().unsubscribe();
        }
        mEventCompositeMap.remove(object);
    }

    @Override
    public void post(IEvent event) {
        SUBJECT.onNext(event);
    }

    @Override
    public void postSticky(IEvent event) {
        STICKY_EVENT_MAP.put(event.getClass(), event);
        post(event);
    }
}
