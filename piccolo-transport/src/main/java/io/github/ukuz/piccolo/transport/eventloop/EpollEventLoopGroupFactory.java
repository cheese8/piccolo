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
package io.github.ukuz.piccolo.transport.eventloop;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;

import java.util.concurrent.ThreadFactory;

/**
 * @author ukuz90
 */
public class EpollEventLoopGroupFactory implements EventLoopGroupFactory {
    @Override
    public EventLoopGroup newEventLoopGroup(int threadNum, int ioRatio, ThreadFactory threadFactory) {
        EpollEventLoopGroup eventLoopGroup = new EpollEventLoopGroup(threadNum, threadFactory);
        eventLoopGroup.setIoRatio(ioRatio);
        return eventLoopGroup;
    }
}