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
package io.github.ukuz.piccolo.api.event;

import io.github.ukuz.piccolo.api.connection.Connection;

/**
 * @author ukuz90
 */
public class UserOnlineEvent implements ApplicationEvent {

    private final String userId;
    private final Connection connection;

    public UserOnlineEvent(String userId, Connection connection) {
        this.userId = userId;
        this.connection = connection;
    }

    public String getUserId() {
        return userId;
    }

    public Connection getConnection() {
        return connection;
    }
}
