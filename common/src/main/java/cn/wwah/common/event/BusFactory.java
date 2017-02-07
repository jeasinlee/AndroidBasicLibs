package cn.wwah.common.event;

/**
 * @Description: 事件工厂，可定制事件发送方式，可替换成EventBus。
 * @author: jeasinlee
 * @date: 2016-12-19 15:06
 */
public class BusFactory {
    private static IBus bus;

    public static IBus getBus() {
        if (bus == null) {
            synchronized (BusFactory.class) {
                if (bus == null) {
                    bus = new RxBusImpl();
                }
            }
        }
        return bus;
    }
}
