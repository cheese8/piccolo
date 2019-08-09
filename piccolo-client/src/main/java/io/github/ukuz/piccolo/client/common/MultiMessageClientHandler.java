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
package io.github.ukuz.piccolo.client.common;

import io.github.ukuz.piccolo.api.connection.Connection;
import io.github.ukuz.piccolo.api.exchange.ExchangeException;
import io.github.ukuz.piccolo.api.exchange.handler.ChannelHandler;
import io.github.ukuz.piccolo.api.exchange.support.MultiMessage;
import io.github.ukuz.piccolo.api.external.common.Assert;

/**
 * @author ukuz90
 */
public class MultiMessageClientHandler implements ChannelHandler {
    private ChannelHandler handler;

    public MultiMessageClientHandler(ChannelHandler handler) {
        Assert.notNull(handler, "handler must not be null");
        this.handler = handler;
    }

    @Override
    public void connected(Connection connection) throws ExchangeException {
        handler.connected(connection);
    }

    @Override
    public void disconnected(Connection connection) throws ExchangeException {
        handler.disconnected(connection);
    }

    @Override
    public void sent(Connection connection, Object message) throws ExchangeException {
        handler.sent(connection, message);
    }

    @Override
    public void received(Connection connection, Object message) throws ExchangeException {
        if (message instanceof MultiMessage) {
            ((MultiMessage) message).forEach(msg -> handler.received(connection, msg));
        } else {
            handler.received(connection, message);
        }
    }

    @Override
    public void caught(Connection connection, Throwable exception) throws ExchangeException {
        handler.caught(connection, exception);
    }
}
