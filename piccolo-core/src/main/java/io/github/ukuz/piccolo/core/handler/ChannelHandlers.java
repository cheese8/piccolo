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
package io.github.ukuz.piccolo.core.handler;

import io.github.ukuz.piccolo.api.PiccoloContext;
import io.github.ukuz.piccolo.api.exchange.handler.ChannelHandler;
import io.github.ukuz.piccolo.api.exchange.handler.MultiMessageHandler;
import io.github.ukuz.piccolo.common.message.IdGenMessage;

/**
 * @author ukuz90
 */
public class ChannelHandlers {

    private ChannelHandlers() {}

    public static ChannelHandler newConnectChannelHandler(PiccoloContext piccoloContext) {
        DispatcherHandler dispatcherHandler = new DispatcherHandler(piccoloContext, null);
        BindUserHandler bindUserHandler = new BindUserHandler(piccoloContext, dispatcherHandler);
        FastConnectHandler fastConnectHandler = new FastConnectHandler(piccoloContext, bindUserHandler);
        HandshakeServerHandler handshakeServerHandler = new HandshakeServerHandler(piccoloContext, fastConnectHandler);
        HeartbeatHandler heartbeatHandler = new HeartbeatHandler(piccoloContext, handshakeServerHandler);
        return new MultiMessageHandler(piccoloContext, heartbeatHandler);
    }

    public static ChannelHandler newGatewayChannelHandler(PiccoloContext piccoloContext) {
        PushHandler pushHandler = new PushHandler(piccoloContext);
        return new MultiMessageHandler(piccoloContext, new IdGenHandler(piccoloContext, pushHandler));
    }

}
