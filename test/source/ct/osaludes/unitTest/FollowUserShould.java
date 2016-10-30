package ct.osaludes.unitTest;

import ct.osaludes.users.action.FollowUser;
import ct.osaludes.users.model.FollowingUsersRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class FollowUserShould {
    @Mock
    FollowingUsersRepository followingUsersRepository;

    private FollowUser followUser;

    @Before
    public void setUp() throws Exception {
        this.followUser = new FollowUser(followingUsersRepository);
    }

    @Test
    public void addUserToFollowingList() throws Exception {
        String alias = "john";
        String followingAlias = "maria";
        followUser.execute(alias, followingAlias);

        verify(followingUsersRepository).addFollowingUser(alias, followingAlias);
    }
}
