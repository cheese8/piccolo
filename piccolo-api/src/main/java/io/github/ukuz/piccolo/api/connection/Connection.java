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
package io.github.ukuz.piccolo.api.connection;

import io.github.ukuz.piccolo.api.protocol.Packet;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

/**
 * @author ukuz90
 */
public interface Connection {

    void init(Channel channel, boolean isSecurity);

    Channel getChannel();

    boolean sendSync(Packet packet);

    ChannelFuture sendAsync(Packet packet);

    ChannelFuture sendAsync(Packet packet, ChannelFutureListener listener);

    SessionContext getSessionContext();

    ChannelFuture close();

    boolean isClosed();

    boolean isConnected();

    boolean isReadTimeout();

    boolean isWriteTimeout();

    void updateLastReadTime();

    void updateLastWriteTime();

}