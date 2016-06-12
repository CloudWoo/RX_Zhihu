package com.haomiao.cloud.rx_zhihu.utils;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @Project ZhiHu
 * @Packate com.haomiao.cloud.rx_zhihu.Utils
 * @Description
 * @Author Cloud Woo
 * @Email
 * @Date
 * @Version 1.0
 */
public class RxUtils {

    public static void unsubscribeIfNotNull(Subscription subscription) {
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }

    public static CompositeSubscription getNewCompositeSubIfUnsubscribed(CompositeSubscription subscription) {
        if (subscription == null || subscription.isUnsubscribed()) {
            return new CompositeSubscription();
        }

        return subscription;
    }
}