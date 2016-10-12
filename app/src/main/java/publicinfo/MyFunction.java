package publicinfo;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Outline;
import android.net.ConnectivityManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.Toast;

import com.example.tyhj.schoolmsg.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import apis.userAndRoom.User;

/**
 * Created by Tyhj on 2016/10/9.
 */

public class MyFunction {

    private static User user;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        MyFunction.user = user;
    }

    //轮廓
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static ViewOutlineProvider getOutline(boolean b, final int x, final int y){
        if(b) {
            return  new ViewOutlineProvider() {
                @Override
                public void getOutline(View view, Outline outline) {
                    final int margin = Math.min(view.getWidth(), view.getHeight()) / x;
                    outline.setOval(margin, margin, view.getWidth() - margin, view.getHeight() - margin);
                }
            };
        }else {
            return new ViewOutlineProvider() {
                @Override
                public void getOutline(View view, Outline outline) {
                    final int margin = Math.min(view.getWidth(), view.getHeight()) / x;
                    outline.setRoundRect(margin, margin, view.getWidth() - margin, view.getHeight() - margin, y);
                    //outline.setOval(margin, margin, view.getWidth() - margin, view.getHeight() - margin);
                }
            };
        }

    }


    //是否有网络
    public static boolean isIntenet(Context context){
        ConnectivityManager con=(ConnectivityManager)context.getSystemService(Activity.CONNECTIVITY_SERVICE);
        boolean wifi=con.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
        boolean internet=con.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
        if(wifi||internet){
            return true;
        }else {
            Toast.makeText(context, context.getString(R.string.nointernet),Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    //手机号是否正确
    public static boolean isMobileNO(String mobiles,Context context) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        boolean is=m.matches();
        if(!is)
            Toast.makeText(context,"请输入正确的手机号码",Toast.LENGTH_SHORT).show();
        return is;
    }
    //邮箱是否正确
    public static boolean isEmail(String email,Context context) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        boolean is=m.matches();
        if(!is)
            Toast.makeText(context,"请输入正确的Email地址",Toast.LENGTH_SHORT).show();
        return is;
    }
}