/*
 * Copyright (c) 2017 Mockito contributors
 * This program is made available under the terms of the MIT License.
 */
package org.mockito.internal.session;

import org.mockito.MockitoSession;
import org.mockito.internal.framework.DefaultMockitoSession;
import org.mockito.internal.util.ConsoleMockitoLogger;
import org.mockito.quality.Strictness;
import org.mockito.session.MockitoSessionBuilder;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.mockito.internal.util.Checks.checkItemsNotNull;
import static org.mockito.quality.Strictness.STRICT_STUBS;

public class DefaultMockitoSessionBuilder implements MockitoSessionBuilder {

    private List<Object> testInstances = emptyList();
    private Strictness strictness = STRICT_STUBS;

    @Override
    public MockitoSessionBuilder initTestInstances(List<Object> testClassInstance) {
        checkItemsNotNull(testClassInstance, "testClassInstance");
        this.testInstances = testClassInstance;
        return this;
    }

    @Deprecated
    @Override
    public MockitoSessionBuilder initMocks(Object testClassInstance) {
        if (testClassInstance != null) {
            testInstances = singletonList(testClassInstance);
        }

        return this;
    }

    @Override
    public MockitoSessionBuilder strictness(Strictness strictness) {
        if (strictness == null){
            strictness = STRICT_STUBS;
        }
        this.strictness = strictness;
        return this;
    }

    @Override
    public MockitoSession startMocking() {
        return new DefaultMockitoSession(testInstances, strictness, new ConsoleMockitoLogger());
    }
}
