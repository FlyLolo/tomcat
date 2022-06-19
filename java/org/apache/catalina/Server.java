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

import java.io.File;
import java.util.concurrent.ScheduledExecutorService;

import org.apache.catalina.deploy.NamingResourcesImpl;
import org.apache.catalina.startup.Catalina;

/**
 * A <code>Server</code> element represents the entire Catalina
 * servlet container.  Its attributes represent the characteristics of
 * the servlet container as a whole.  A <code>Server</code> may contain
 * one or more <code>Services</code>, and the top level set of naming
 * resources.
 * <p>
 * Normally, an implementation of this interface will also implement
 * <code>Lifecycle</code>, such that when the <code>start()</code> and
 * <code>stop()</code> methods are called, all of the defined
 * <code>Services</code> are also started or stopped.
 * <p>
 * In between, the implementation must open a server socket on the port number
 * specified by the <code>port</code> property.  When a connection is accepted,
 * the first line is read and compared with the specified shutdown command.
 * If the command matches, shutdown of the server is initiated.
 *
 * <code>Server</code> 组件代表整个 Catalina servlet 容器。
 * 它的属性代表了整个 servlet 容器的特征。
 * <code>Server</code> 可能包含一个或多个<code>Services</code>，以及顶级命名资源集合。
 * <p>
 * 通常，这个<code>Server</code>接口的一个实现也会实现<code>Lifecycle</code>，
 * 这样当<code>start()</code>和<code>stop()</code>方法被调用时，
 * 所有的 定义的 <code>Services</code> 也会启动或停止。
 * <p>
 * 在这两者之间，实现必须在 <code>port</code> 属性指定的端口号上打开一个server socket。
 * 当一个连接被接受时，第一行被读取并与指定的关闭命令进行比较。 如果命令匹配，则启动服务器关闭。
 *
 * @author Craig R. McClanahan
 */
public interface Server extends Lifecycle {

    // ------------------------------------------------------------- Properties

    /**
     * @return the global naming resources.
     * 返回公用的naming resources
     */
    public NamingResourcesImpl getGlobalNamingResources();


    /**
     * Set the global naming resources.
     *
     * @param globalNamingResources The new global naming resources
     */
    public void setGlobalNamingResources
        (NamingResourcesImpl globalNamingResources);


    /**
     * @return the global naming resources context.
     */
    public javax.naming.Context getGlobalNamingContext();


    /**
     * @return the port number we listen to for shutdown commands.
     * 返回用于监听关闭命令的端口
     *
     * @see #getPortOffset()
     * @see #getPortWithOffset()
     */
    public int getPort();


    /**
     * Set the port number we listen to for shutdown commands.
     *
     * @param port The new port number
     *
     * @see #setPortOffset(int)
     */
    public void setPort(int port);

    /**
     * Get the number that offsets the port used for shutdown commands.
     * For example, if port is 8005, and portOffset is 1000,
     * the server listens at 9005.
     *
     * 获取对于关闭命令的端口的偏移值。
     * 例如，如果port值是 8005，portOffset 是 1000，
     * 服务器在 9005 侦听。
     *
     * @return the port offset
     */
    public int getPortOffset();

    /**
     * Set the number that offsets the server port used for shutdown commands.
     * For example, if port is 8005, and you set portOffset to 1000,
     * connector listens at 9005.
     *
     * @param portOffset sets the port offset
     */
    public void setPortOffset(int portOffset);

    /**
     * Get the actual port on which server is listening for the shutdown commands.
     * If you do not set port offset, port is returned. If you set
     * port offset, port offset + port is returned.
     *
     * 获取服务器正在侦听关闭命令的实际端口。
     * 如果不设置端口偏移量，则返回端口。
     * 如果设置端口偏移，则返回端口偏移 + 端口。
     *
     * @return the port with offset
     */
    public int getPortWithOffset();

    /**
     * @return the address on which we listen to for shutdown commands.
     * 返回监听关闭命令的地址
     */
    public String getAddress();


    /**
     * Set the address on which we listen to for shutdown commands.
     *
     * @param address The new address
     */
    public void setAddress(String address);


    /**
     * @return the shutdown command string we are waiting for.
     */
    public String getShutdown();


    /**
     * Set the shutdown command we are waiting for.
     * 返回正在等待的关闭命令字符串。
     *
     * @param shutdown The new shutdown command
     */
    public void setShutdown(String shutdown);


    /**
     * @return the parent class loader for this component. If not set, return
     * {@link #getCatalina()} {@link Catalina#getParentClassLoader()}. If
     * catalina has not been set, return the system class loader.
     */
    public ClassLoader getParentClassLoader();


    /**
     * Set the parent class loader for this server.
     *
     * @param parent The new parent class loader
     */
    public void setParentClassLoader(ClassLoader parent);


    /**
     * @return the outer Catalina startup/shutdown component if present.
     */
    public Catalina getCatalina();

    /**
     * Set the outer Catalina startup/shutdown component if present.
     *
     * @param catalina the outer Catalina component
     */
    public void setCatalina(Catalina catalina);


    /**
     * @return the configured base (instance) directory. Note that home and base
     * may be the same (and are by default). If this is not set the value
     * returned by {@link #getCatalinaHome()} will be used.
     */
    public File getCatalinaBase();

    /**
     * Set the configured base (instance) directory. Note that home and base
     * may be the same (and are by default).
     *
     * @param catalinaBase the configured base directory
     */
    public void setCatalinaBase(File catalinaBase);


    /**
     * @return the configured home (binary) directory. Note that home and base
     * may be the same (and are by default).
     */
    public File getCatalinaHome();

    /**
     * Set the configured home (binary) directory. Note that home and base
     * may be the same (and are by default).
     *
     * @param catalinaHome the configured home directory
     */
    public void setCatalinaHome(File catalinaHome);


    /**
     * Get the utility thread count.
     * @return the thread count
     */
    public int getUtilityThreads();


    /**
     * Set the utility thread count.
     * @param utilityThreads the new thread count
     */
    public void setUtilityThreads(int utilityThreads);


    // --------------------------------------------------------- Public Methods


    /**
     * Add a new Service to the set of defined Services.
     *
     * @param service The Service to be added
     */
    public void addService(Service service);


    /**
     * Wait until a proper shutdown command is received, then return.
     */
    public void await();


    /**
     * Find the specified Service
     *
     * @param name Name of the Service to be returned
     * @return the specified Service, or <code>null</code> if none exists.
     */
    public Service findService(String name);


    /**
     * @return the set of Services defined within this Server.
     */
    public Service[] findServices();


    /**
     * Remove the specified Service from the set associated from this
     * Server.
     *
     * @param service The Service to be removed
     */
    public void removeService(Service service);


    /**
     * @return the token necessary for operations on the associated JNDI naming
     * context.
     */
    public Object getNamingToken();

    /**
     * @return the utility executor managed by the Service.
     */
    public ScheduledExecutorService getUtilityExecutor();

}
