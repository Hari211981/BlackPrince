/*
 * Copyright (c) 2018 Mockito contributors
 * This program is made available under the terms of the MIT License.
 */
package org.mockitousage;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class InjectMocksTest {

    @Nested
    class ParentWithFieldInjection {
        @InjectMocks
        private ParentForFieldInjection parent;

        @Mock
        private Child childMock;

        @Test
        void properly_injects_mock_into_parent() {
            assertThat(childMock).isNotNull();
            assertThat(parent.child).isSameAs(childMock);
        }
    }

    @Nested
    class ParentWithConstructorInjection {
        @InjectMocks
        private ParentForConstructorInjection parent;

        @Mock
        private Child childMock;

        @Test
        void properly_injects_mock_into_parent() {
            assertThat(childMock).isNotNull();
            assertThat(parent.child).isSameAs(childMock);
        }
    }

    @Nested
    class ParentWithConstructorInjectionWithOtherArguments {
        @InjectMocks
        private ParentForConstructorInjectionWithOtherArguments parent;

        @Mock
        private Child childMock;

        @Test
        void properly_injects_mock_into_parent() {
            assertThat(childMock).isNotNull();
            assertThat(parent.child).isSameAs(childMock);
        }
    }

    static class Child {
    }

    private static class ParentForFieldInjection {
        private Child child;
    }
    private static class ParentForConstructorInjection {
        private Child child;
        ParentForConstructorInjection(Child child) {
            this.child = child;
        }
    }
    private static class ParentForConstructorInjectionWithOtherArguments {
        private Child child;
        ParentForConstructorInjectionWithOtherArguments(Child child, String otherArgument) {
            this.child = child;
        }
    }

}
