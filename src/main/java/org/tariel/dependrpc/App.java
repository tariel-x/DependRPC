package org.tariel.dependrpc;

import org.tariel.dependrpc.server.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

import org.apache.thrift.*;
import org.apache.thrift.protocol.*;
import org.apache.thrift.server.*;
import org.apache.thrift.transport.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tariel.jsonconfig.JsonConfig;

/**
 * Hello world!
 *
 */
public class App {

    private static Logger log = LoggerFactory.getLogger(App.class);

    public static class ServerThread implements Runnable {

        private Integer port;
        private final int THREAD_POOL_SIZE = 10;
        private final Boolean BLOCK = false;

        @SuppressWarnings("rawtypes")
        public ServerThread(ParseServer.Processor processor, Integer portNum) {
            port = portNum;
        }

        public void run() {
            try {
                if (BLOCK) {
                    // Initialize the transport socket
                    TServerTransport serverTransport = new TServerSocket(port);
                    TThreadPoolServer.Args args = new TThreadPoolServer.Args(serverTransport);
                    args.maxWorkerThreads(THREAD_POOL_SIZE);
                    args.processor(processor);
                    args.executorService(new ScheduledThreadPoolExecutor(THREAD_POOL_SIZE));
                    TServer server = new TThreadPoolServer(args);
                    server.serve();
                    log.info("server in running on port "+JsonConfig.get("server").get("port").asStr());
                } else {

                    // From https://github.com/m1ch1/mapkeeper/blob/eb798bb94090c7366abc6b13142bf91e4ed5993b/stubjava/StubServer.java#L93
                    TNonblockingServerTransport trans = new TNonblockingServerSocket(port);
                    TThreadedSelectorServer.Args args = new TThreadedSelectorServer.Args(trans);
                    args.transportFactory(new TFramedTransport.Factory());
                    args.protocolFactory(new TBinaryProtocol.Factory());
                    args.processor(processor);
                    args.selectorThreads(4);
                    args.workerThreads(32);
                    TServer server = new TThreadedSelectorServer(args);
                    log.info("Server in running on port "+JsonConfig.get("server").get("port").asStr());
                    server.serve();
                }

            } catch (Exception e) {
                log.error("Some error");
                e.printStackTrace();
            }
        }
    }

    public static ServiceImpl handler;
    public static ParseServer.Processor processor;

    public static void main(String[] args) {
        JsonConfig.init("application.conf");
        log.info("Config loaded");

        //org.apache.log4j.BasicConfigurator.configure();
        try {
            handler = new ServiceImpl();
            processor = new ParseServer.Processor(handler);
            Runnable r = new ServerThread(processor, JsonConfig.get("server").get("port").asInt());
            new Thread(r).start();
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

}