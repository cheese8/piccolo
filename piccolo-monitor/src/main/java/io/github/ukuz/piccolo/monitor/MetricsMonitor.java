/*
 * Copyright 2019 ukuz90
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.ukuz.piccolo.monitor;

import io.github.ukuz.piccolo.api.common.utils.StringUtils;
import io.github.ukuz.piccolo.api.external.common.Assert;
import io.micrometer.core.instrument.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;

/**
 * @author ukuz90
 */
public class MetricsMonitor {

    private static final String MONITOR_TAG = "piccolo_app_monitor";

    public static void gauge(String tag, String module, String name, Number value) {
        if (StringUtils.hasText(module)) {
            gauge(tag, value, "module", module, "name", name);
        } else {
            gauge(tag, value, "name", name);
        }
    }

    public static void gaugeWithStrongRef(String tag, String module, String name, Supplier<Number> supplier) {
        List<Tag> labelSets;
        if (StringUtils.hasText(module)) {
            labelSets = wrapLabelSet("module", module, "name", name);
        } else {
            labelSets = wrapLabelSet("name", name);
        }
        Gauge.builder(tag, supplier).tags(labelSets).register(Metrics.globalRegistry);
    }

    private static Counter counter(String metricName, String ...label) {
        return Metrics.counter(metricName, wrapLabelSet(label));
    }

    private static void gauge(String metricName, Number value, String ...label) {
        Metrics.gauge(metricName, wrapLabelSet(label), value);
    }

    private static List<Tag> wrapLabelSet(String ...label) {
        if (label.length > 0) {
            Assert.isTrue(label.length % 2 == 0, "invalid arguments");
        }
        List<Tag> labelSet = new ArrayList<>(label.length / 2);
        for (int i = 0; i < label.length; i += 2) {
            labelSet.add(new ImmutableTag(label[i], label[i+1]));
        }
        return labelSet;
    }

    public static final Counter getWebSocketQuestCount() {
        return counter(MONITOR_TAG, "name", "websocketQuestCount");
    }

    public static final Counter getWebSocketBytesCount() {
        return counter(MONITOR_TAG, "module", "net", "name", "websocketBytesCount");
    }

}
