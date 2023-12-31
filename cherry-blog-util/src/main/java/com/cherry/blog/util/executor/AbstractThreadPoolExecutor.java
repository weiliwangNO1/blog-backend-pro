package com.cherry.blog.util.executor;

import com.cherry.blog.util.enums.ThreadPoolExecutorEnum;
import com.cherry.blog.util.tools.ConstantValue;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池
 * @author weili.wang
 * @date 2023/12/31
 */
public abstract class AbstractThreadPoolExecutor implements BaseThreadPoolExecutor{

    /**
     * 线程工厂
     */
    private class AppointThreadFactory implements ThreadFactory {

        private final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        public AppointThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = getThreadPoolName()
                    .concat(ConstantValue.SPLIT_STR1);
        }

        @Override
        public Thread newThread(Runnable runnable) {
            //设置线程名称
            Thread t = new Thread(group, runnable,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }

    /**
     * 根据默认配置获取线程池
     * @return
     */
    public ThreadPoolExecutor getDefaultThreadPool() {
        return new ThreadPoolExecutor(getCoreSize(),
                getMaximumPoolSize(),
                getKeepAliveTime(),
                getTimeUnit(),
                new LinkedBlockingQueue<>(getBlockingQueueSize()),
                new AppointThreadFactory(),
                getRejectedExecutionHandler());
    }

    /**
     * 获取线程池
     * @param corePoolSize
     * @param maximumPoolSize
     * @return
     */
    public ThreadPoolExecutor getThreadPool(int corePoolSize,
                                            int maximumPoolSize) {
        return new ThreadPoolExecutor(corePoolSize,
                maximumPoolSize,
                getKeepAliveTime(),
                getTimeUnit(),
                new LinkedBlockingQueue<>(getBlockingQueueSize()),
                new AppointThreadFactory(),
                getRejectedExecutionHandler());
    }

    /**
     * 获取线程池
     * @param corePoolSize
     * @param maximumPoolSize
     * @param keepAliveTime
     * @param unit
     * @return
     */
    public ThreadPoolExecutor getThreadPool(int corePoolSize,
                                            int maximumPoolSize,
                                            long keepAliveTime,
                                            TimeUnit unit) {
        return new ThreadPoolExecutor(corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                unit,
                new LinkedBlockingQueue<>(getBlockingQueueSize()),
                new AppointThreadFactory(),
                getRejectedExecutionHandler());
    }

    /**
     * 获取线程池
     * @param corePoolSize
     * @param maximumPoolSize
     * @param keepAliveTime
     * @param unit
     * @param workQueue
     * @return
     */
    public ThreadPoolExecutor getThreadPool(int corePoolSize,
                                            int maximumPoolSize,
                                            long keepAliveTime,
                                            TimeUnit unit,
                                            BlockingQueue<Runnable> workQueue) {
        return new ThreadPoolExecutor(corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                unit,
                workQueue,
                new AppointThreadFactory(),
                getRejectedExecutionHandler());
    }

    /**
     * 获取线程池
     * @param corePoolSize
     * @param maximumPoolSize
     * @param keepAliveTime
     * @param unit
     * @param workQueue
     * @param threadFactory
     * @return
     */
    public ThreadPoolExecutor getThreadPool(int corePoolSize,
                                            int maximumPoolSize,
                                            long keepAliveTime,
                                            TimeUnit unit,
                                            BlockingQueue<Runnable> workQueue,
                                            ThreadFactory threadFactory) {
        return new ThreadPoolExecutor(corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                unit,
                workQueue,
                threadFactory,
                getRejectedExecutionHandler());
    }

    /**
     * 获取线程池
     * @param corePoolSize
     * @param maximumPoolSize
     * @param keepAliveTime
     * @param unit
     * @param workQueue
     * @param handler
     * @return
     */
    public ThreadPoolExecutor getThreadPool(int corePoolSize,
                                            int maximumPoolSize,
                                            long keepAliveTime,
                                            TimeUnit unit,
                                            BlockingQueue<Runnable> workQueue,
                                            RejectedExecutionHandler handler) {
        return new ThreadPoolExecutor(corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                unit,
                workQueue,
                new AppointThreadFactory(),
                handler);
    }

    /**
     * 获取线程池
     * @param corePoolSize
     * @param maximumPoolSize
     * @param keepAliveTime
     * @param unit
     * @param workQueue
     * @param threadFactory
     * @param handler
     * @return
     */
    public ThreadPoolExecutor getThreadPool(int corePoolSize,
                                            int maximumPoolSize,
                                            long keepAliveTime,
                                            TimeUnit unit,
                                            BlockingQueue<Runnable> workQueue,
                                            ThreadFactory threadFactory,
                                            RejectedExecutionHandler handler) {
        return new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);

    }

}
