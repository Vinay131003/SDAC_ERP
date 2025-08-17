package Operation;

import Pojo.ConsumerPort;
import java.util.List;

public interface Consumer {
    
    // Register a new consumer
    public void registerConsumer(ConsumerPort consumer);

    // Login as a consumer
    boolean loginConsumer(ConsumerPort consumer);

    // Update consumer profile
    public void updateConsumerProfile(ConsumerPort consumer);

    // Delete consumer profile
    public void deleteConsumerProfile(ConsumerPort consumer);

    // View all consumers
    public List<ConsumerPort> viewConsumers();
    //boolean loginConsumer(ConsumerPort consumer);
}
