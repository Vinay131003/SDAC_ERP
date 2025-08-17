package Implementor;

import Consumer_Dao.DeleteConsumerProfileDao;
import Consumer_Dao.LoginConsumerDao;
import Consumer_Dao.RegisterConsumerDao;
import Consumer_Dao.UpdateConsumerProfileDao;
import Consumer_Dao.ViewConsumerDao;
import Operation.Consumer;
import Pojo.ConsumerPort;

import java.util.List;

public class ConsumerImp implements Consumer {

    private RegisterConsumerDao registerDao = new RegisterConsumerDao();
    private LoginConsumerDao loginDao = new LoginConsumerDao();
    private UpdateConsumerProfileDao updateDao = new UpdateConsumerProfileDao();
    private DeleteConsumerProfileDao deleteDao = new DeleteConsumerProfileDao();
    private ViewConsumerDao viewDao = new ViewConsumerDao();

    @Override
    public void registerConsumer(ConsumerPort consumer) {
        registerDao.registerConsumer(consumer);
    }

    @Override
    public boolean loginConsumer(ConsumerPort consumer) {
        return loginDao.loginConsumer(consumer);
    }

    @Override
    public void updateConsumerProfile(ConsumerPort consumer) {
        updateDao.updateConsumerProfile(consumer);
    }

    @Override
    public void deleteConsumerProfile(ConsumerPort consumer) {
        deleteDao.deleteConsumerProfile(consumer);
    }

    @Override
    public List<ConsumerPort> viewConsumers() {
        return viewDao.viewConsumers();
    }
}
