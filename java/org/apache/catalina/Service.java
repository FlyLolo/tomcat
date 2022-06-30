/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.catalina;

import org.apache.catalina.connector.Connector;
import org.apache.catalina.mapper.Mapper;

/**
 * A <strong>Service</strong> is a group of one or more
 * <strong>Connectors</strong> that share a single <strong>Container</strong>
 * to process their incoming requests.  This arrangement allows, for example,
 * a non-SSL and SSL connector to share the same population of web apps.
 * <p>
 * A given JVM can contain any number of Service instances; however, they are
 * completely independent of each other and share only the basic JVM facilities
 * and classes on the system class path.
 *
 * Service包含一个或多个连接器，它们共享一个容器来处理它们的传入请求。
 * 例如，这种安排允许非 SSL 和 SSL 连接器共享相同的 Web 应用程序群。
 *
 * 一个给定的 JVM 可以包含任意数量的 Service 实例；
 * 但是，它们彼此完全独立，并且仅共享系统类路径上的基本 JVM 设施和类。
 *
 * @author Craig R. McClanahan
 */
public interface Service extends Lifecycle {

    // ------------------------------------------------------------- Properties

    /**
     * @return the <code>Engine</code> that handles requests for all
     * <code>Connectors</code> associated with this Service.
     *
     * 返回处理与此服务关联的所有 <code>Connectors</code> 请求的 <code>Engine</code>。
     */
    public Engine getContainer();

    /**
     * Set the <code>Engine</code> that handles requests for all
     * <code>Connectors</code> associated with this Service.
     *
     * @param engine The new Engine
     */
    public void setContainer(Engine engine);

    /**
     * @return the name of this Service.
     */
    public String getName();

    /**
     * Set the name of this Service.
     *
     * @param name The new service name
     */
    public void setName(String name);

    /**
     * @return the <code>Server</code> with which we are associated (if any).
     *
     * 返回关联的 <code>Server</code> （如果有的话）。
     */
    public Server getServer();

    /**
     * Set the <code>Server</code> with which we are associated (if any).
     *
     * @param server The server that owns this Service
     */
    public void setServer(Server server);

    /**
     * @return the parent class loader for this component. If not set, return
     * {@link #getServer()} {@link Server#getParentClassLoader()}. If no server
     * has been set, return the system class loader.
     *
     * 返回此组件的父类加载器。
     * 如果未设置，则返回 {@link #getServer()} {@link Server#getParentClassLoader()}。
     * 如果没有设置Server，则返回系统类加载器。
     */
    public ClassLoader getParentClassLoader();

    /**
     * Set the parent class loader for this service.
     *
     * @param parent The new parent class loader
     */
    public void setParentClassLoader(ClassLoader parent);

    /**
     * @return the domain under which this container will be / has been
     * registered.
     *
     * 返回此容器将/已注册的域。
     */
    public String getDomain();


    // --------------------------------------------------------- Public Methods

    /**
     * Add a new Connector to the set of defined Connectors, and associate it
     * with this Service's Container.
     *
     * 将新的连接器添加到已定义的连接器集中，并将其与此Service的Container相关联。
     *
     * @param connector The Connector to be added
     */
    public void addConnector(Connector connector);

    /**
     * Find and return the set of Connectors associated with this Service.
     *
     * @return the set of associated Connectors
     *
     * 查找并返回与此Service关联的Connectors集。
     * 返回一组关联的连接器
     */
    public Connector[] findConnectors();

    /**
     * Remove the specified Connector from the set associated from this
     * Service.  The removed Connector will also be disassociated from our
     * Container.
     *
     * 从与此服务关联的集合中删除指定的连接器。 移除的连接器也将与我们的容器解除关联。
     *
     * @param connector The Connector to be removed
     */
    public void removeConnector(Connector connector);

    /**
     * Adds a named executor to the service
     * @param ex Executor
     *
     * 向service添加一个已命名的Executor
     */
    public void addExecutor(Executor ex);

    /**
     * Retrieves all executors
     * @return Executor[]
     */
    public Executor[] findExecutors();

    /**
     * Retrieves executor by name, null if not found
     * @param name String
     * @return Executor
     *
     * 按名称检索执行者，如果没有找到则为空
     */
    public Executor getExecutor(String name);

    /**
     * Removes an executor from the service
     * @param ex Executor
     */
    public void removeExecutor(Executor ex);

    /**
     * @return the mapper associated with this Service.
     *
     * 返回与此服务关联的映射器。
     */
    Mapper getMapper();
}
