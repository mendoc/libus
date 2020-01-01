package com.dimitriongoua.libus.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.crashlytics.android.Crashlytics;
import com.dimitriongoua.libus.model.LibusButton;

import io.realm.Realm;
import io.realm.Sort;

public class Master {


    public Master() {

    }

    public List<LibusButton> getLibelles() {

        Realm realm = Realm.getDefaultInstance();

        return new ArrayList<>(realm.where(LibusButton.class).findAll().sort("created", Sort.DESCENDING));
    }

    public String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.FRENCH);
        Date date = new Date();
        return dateFormat.format(date);

    }

    public static int getCurrentVersion(Context context) {
        PackageInfo pInfo = null;
        try {
            pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            Crashlytics.logException(e);
        }
         return pInfo.versionCode;
    }
}
