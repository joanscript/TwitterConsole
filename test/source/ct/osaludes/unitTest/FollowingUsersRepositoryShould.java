package ct.osaludes.unitTest;

import ct.osaludes.users.model.FollowingUsersRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FollowingUsersRepositoryShould {
    private FollowingUsersRepository followingUsersRepository;

    @Before
    public void setUp() throws Exception {
        followingUsersRepository = new FollowingUsersRepository();
    }

    @Test
    public void storeNewFollowingUser() throws Exception {
        String alias = "john";
        String followingAlias = "maria";
        followingUsersRepository.addFollowingUser(alias, followingAlias);

        HashSet<String> followingUsers = followingUsersRepository.findAllByAlias(alias);
        assertThat(followingUsers.size(), is(1));
        assertThat(followingUsers.iterator().next(), is(followingAlias));
    }

    @Test
    public void storeSeveralFollowingUsers() throws Exception {
        String alias = "john";
        String followingAlias = "maria";
        String followingAlias2 = "tina";
        String followingAlias3 = "roger";
        followingUsersRepository.addFollowingUser(alias, followingAlias);
        followingUsersRepository.addFollowingUser(alias, followingAlias2);
        followingUsersRepository.addFollowingUser(alias, followingAlias3);

        HashSet<String> followingUsers = followingUsersRepository.findAllByAlias(alias);

        assertThat(followingUsers.size(), is(3));
        Iterator<String> iterator = followingUsers.iterator();
        assertThat(iterator.next(), is(followingAlias));
        assertThat(iterator.next(), is(followingAlias2));
        assertThat(iterator.next(), is(followingAlias3));
    }

    @Test
    public void storeSeveralFollowingUsersToSeveralUsers() throws Exception {
        String alias1 = "john";
        String alias2 = "maria";
        String followingAlias = "maria";
        String followingAlias2 = "tina";
        String followingAlias3 = "roger";
        followingUsersRepository.addFollowingUser(alias1, followingAlias);
        followingUsersRepository.addFollowingUser(alias2, followingAlias2);
        followingUsersRepository.addFollowingUser(alias1, followingAlias3);

        assertThat(followingUsersRepository.findAllByAlias(alias1).size(), is(2));
        assertThat(followingUsersRepository.findAllByAlias(alias2).size(), is(1));
    }
}
