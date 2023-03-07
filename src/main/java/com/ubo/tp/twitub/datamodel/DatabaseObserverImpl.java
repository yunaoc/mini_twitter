package main.java.com.ubo.tp.twitub.datamodel;

public class DatabaseObserverImpl implements IDatabaseObserver{
    @Override
    public void notifyTwitAdded(Twit addedTwit) {
        System.out.println("notifyTwitAdded : " + addedTwit.getText());
    }

    @Override
    public void notifyTwitDeleted(Twit deletedTwit) {
        System.out.println("notifyTwitDeleted");
    }

    @Override
    public void notifyTwitModified(Twit modifiedTwit) {
        System.out.println("notifyTwitModified");
    }

    @Override
    public void notifyUserAdded(User addedUser) {
        System.out.println("notifyUserAdded");
    }

    @Override
    public void notifyUserDeleted(User deletedUser) {
        System.out.println("notifyUserDeleted");
    }

    @Override
    public void notifyUserModified(User modifiedUser) {
        System.out.println("notifyUserModified");
    }
}
