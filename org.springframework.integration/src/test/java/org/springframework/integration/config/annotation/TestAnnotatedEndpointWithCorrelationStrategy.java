/*
 * Copyright 2002-2008 the original author or authors.
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

package org.springframework.integration.config.annotation;

import java.util.List;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Aggregator;
import org.springframework.integration.annotation.CompletionStrategy;
import org.springframework.integration.annotation.CorrelationStrategy;

/**
 * @author: Marius Bogoevici
 */
@MessageEndpoint("endpointWithCorrelationStrategy")
public class TestAnnotatedEndpointWithCorrelationStrategy {

    @Aggregator(inputChannel = "inputChannel")
    public String aggregatingMethod(List<String> payloads) {
        StringBuffer buffer = new StringBuffer();
        for (String s: payloads)  {
            buffer.append(s);
        }
        return buffer.toString();
    }

    @CompletionStrategy
    public boolean isComplete(List<String> payloads) {
        return payloads.size() == 3;
    }

    @CorrelationStrategy
    public String correlate(String payload) {
        return payload.substring(0, 1);
    }

}