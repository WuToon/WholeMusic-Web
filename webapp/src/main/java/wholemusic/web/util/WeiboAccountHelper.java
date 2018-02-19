package wholemusic.web.util;

import com.belerweb.social.weibo.api.OAuth2;
import com.belerweb.social.weibo.api.Weibo;
import wholemusic.web.config.Constants;
import wholemusic.web.config.SessionKey;
import wholemusic.web.model.WeiboAuthInfo;
import wholemusic.web.model.domain.User;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("SpellCheckingInspection")
public class WeiboAccountHelper {

    public static final String FAKE_WEIBO_UID = "88888888";

    @SuppressWarnings("SpellCheckingInspection")
    public static String getWeiboLoginLink() {
        return getWeiboOAuth2().authorize();
    }

    @SuppressWarnings("SpellCheckingInspection")
    public static OAuth2 getWeiboOAuth2() {
        return getWeibo().getOAuth2();
    }

    @SuppressWarnings("SpellCheckingInspection")
    public static Weibo getWeibo() {
        Weibo weibo = new Weibo(Constants.WEIBO_CLIENT_ID, Constants.WEIBO_CLIENT_SECRET, Constants.WEIBO_CALLBACK);
        return weibo;
    }

    @SuppressWarnings("SpellCheckingInspection")
    public static void onWeiboLoggedOn(HttpSession session, String accessToken, User user) {
        session.setAttribute(SessionKey.WEIBO_ACCESS_TOKEN, accessToken);
        session.setAttribute(SessionKey.WEIBO_UID, user.getWeiboUid());
    }

    public static boolean isAdminUser(User user) {
        if (user != null) {
            String uid = user.getWeiboUid();
            List<String> admins = Arrays.asList("1173510540", FAKE_WEIBO_UID);
            return admins.contains(uid);
        } else {
            return false;
        }
    }
}
