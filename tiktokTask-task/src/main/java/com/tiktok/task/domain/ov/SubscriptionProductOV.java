package com.tiktok.task.domain.ov;

import com.tiktok.task.domain.LcProduct;
import com.tiktok.task.domain.LcSubscription;
import lombok.Data;

@Data
public class SubscriptionProductOV {
    private LcSubscription lcSubscription;
    private LcProduct lcProduct;
}
