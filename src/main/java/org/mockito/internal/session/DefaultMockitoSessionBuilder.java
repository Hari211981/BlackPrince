/*
 * Copyright (c) 2017 Mockito contributors
 * This program is made available under the terms of the MIT License.
 */
package org.mockito.internal.session;

import org.mockito.MockitoSession;
import org.mockito.internal.framework.DefaultMockitoSession;
import org.mockito.internal.util.Checks;
import org.mockito.internal.util.ConsoleMockitoLogger;
import org.mockito.quality.Strictness;
import org.mockito.session.MockitoSessionBuilder;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.internal.util.Checks.checkItemsNotNull;
import static org.mockito.internal.util.Checks.checkNotNull;

public class DefaultMockitoSessionBuilder implements MockitoSessionBuilder {

    private List<Object> testInstances = new LinkedList<Object>();
    private Strictness strictness = Strictness.STRICT_STUBS;

    @Override
    public MockitoSessionBuilder initMocks(List<Object>  testClassInstance) {
        checkItemsNotNull(testClassInstance,"testClassInstance");
        this.testInstances=testClassInstance;
        return this;
    }

    @Override
    public MockitoSessionBuilder strictness(Strictness strictness) {
        checkNotNull(strictness,"strictness");
        this.strictness = strictness;
        return this;
    }

    @Override
    public MockitoSession startMocking() {
        //Configure default values


        return new DefaultMockitoSession(testInstances, strictness, new ConsoleMockitoLogger());
    }
}
