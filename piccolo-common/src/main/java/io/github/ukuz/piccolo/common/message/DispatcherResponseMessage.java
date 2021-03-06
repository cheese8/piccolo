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
package io.github.ukuz.piccolo.common.message;

import io.github.ukuz.piccolo.api.connection.Connection;
import io.github.ukuz.piccolo.api.exchange.support.ByteBufMessage;
import io.netty.buffer.ByteBuf;

import java.util.Arrays;

import static io.github.ukuz.piccolo.common.constants.CommandType.DISPATCH;

/**
 * @author ukuz90
 */
public class DispatcherResponseMessage extends ByteBufMessage {

    public byte[] payload;

    public DispatcherResponseMessage(Connection connection) {
        super(connection, DISPATCH.getCmd());
    }

    @Override
    protected void decodeBody0(ByteBuf buf) {
        payload = readBytes(buf);
    }

    @Override
    protected void encodeBody0(ByteBuf buf) {
        writeBytes(buf, payload);
    }

    public static DispatcherResponseMessage build(Connection connection) {
        return new DispatcherResponseMessage(connection);
    }

    public DispatcherResponseMessage payload(byte[] payload) {
        this.payload = payload;
        return this;
    }

    @Override
    public String toString() {
        return "DispatcherResponseMessage{" +
                "payload=" + Arrays.toString(payload) +
                '}';
    }
}
