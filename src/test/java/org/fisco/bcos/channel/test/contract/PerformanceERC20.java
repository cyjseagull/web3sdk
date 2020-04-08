package org.fisco.bcos.channel.test.contract;
import com.google.common.util.concurrent.RateLimiter;
import java.math.BigInteger;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
import org.fisco.bcos.channel.client.Service;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.channel.ChannelEthereumService;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.utils.Web3AsyncThreadPoolSize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.security.SecureRandom;
import org.fisco.bcos.web3j.utils.Numeric;
import org.fisco.bcos.web3j.abi.datatypes.Address;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;

public class PerformanceERC20 {
    private static Logger logger = LoggerFactory.getLogger(PerformanceERC20.class);
    private static AtomicInteger sended = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {
        try {
            String groupId = args[3];
            ApplicationContext context =
                    new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
            Service service = context.getBean(Service.class);
            service.setGroupId(Integer.parseInt(groupId));
            service.run();

            System.out.println("Start ERC20 test...");
            System.out.println(
                    "===================================================================");

            ChannelEthereumService channelEthereumService = new ChannelEthereumService();
            channelEthereumService.setChannelService(service);

            Web3AsyncThreadPoolSize.web3AsyncCorePoolSize = 3000;
            Web3AsyncThreadPoolSize.web3AsyncPoolSize = 2000;

            ScheduledExecutorService scheduledExecutorService =
                    Executors.newScheduledThreadPool(500);
            Web3j web3 =
                    Web3j.build(
                            channelEthereumService,
                            15 * 100,
                            scheduledExecutorService,
                            Integer.parseInt(groupId));

            Credentials credentials = GenCredential.create();
            BigInteger gasPrice = new BigInteger("30000000");
            BigInteger gasLimit = new BigInteger("30000000");

            String command = args[0];
            Integer count = 0;
            Integer qps = 0;
            switch (command) {
                case "transfer":
                    count = Integer.parseInt(args[1]);
                    qps = Integer.parseInt(args[2]);
                    break;
                default:
                    System.out.println("Args: <transfer> <Total> <QPS> <GroupID>");
            }

            ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
            threadPool.setCorePoolSize(200);
            threadPool.setMaxPoolSize(500);
            threadPool.setQueueCapacity(count);

            threadPool.initialize();

            System.out.println("Deploying StandardToken contract...");
            String ethFundDeposit = "0x1";
            BigInteger currentSupply = new BigInteger("1000000000");
            HKMCToken erc20 = HKMCToken.deploy(web3, credentials, gasPrice, gasLimit, ethFundDeposit, currentSupply).send();

            PerformanceCollector collector = new PerformanceCollector();
            collector.setTotal(count);

            RateLimiter limiter = RateLimiter.create(qps);
            Integer area = count / 10;
            final Integer total = count;

            System.out.println("Start test，total：" + count);
            for (Integer i = 0; i < count; ++i) {
                threadPool.execute(
                        new Runnable() {
                            @Override
                            public void run() {
                                limiter.acquire();
                                PerformanceOkCallback callback = new PerformanceOkCallback();
                                callback.setCollector(collector);
                                try {
                                    int random = new SecureRandom().nextInt(100);
                                    String valueStr = "" + random;
                                    BigInteger value = new BigInteger(valueStr);
                                    String to = Integer.toHexString(random);
                                     
                                    erc20.transfer(to, value, callback);
                                    //BigInteger balance = erc20.balanceOf(to).send();
                                    //System.out.println("balance of 0x02 is: " + balance);

                                } catch (Exception e) {
                                    TransactionReceipt receipt = new TransactionReceipt();
                                    receipt.setStatus("-1");

                                    callback.onResponse(receipt);
                                    logger.info(e.getMessage());
                                }

                                int current = sended.incrementAndGet();

                                if (current >= area && ((current % area) == 0)) {
                                    System.out.println(
                                            "Already sended: "
                                                    + current
                                                    + "/"
                                                    + total
                                                    + " transactions");
                                }
                            }
                        });
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
            ;
        }
    }
}
