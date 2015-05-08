package com.rubik.bytecodem;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Mehedee Zaman on 5/7/2015.
 */

public class PreferenceStorage {

    public static Context appContext;

    public static void init(Context context){
        appContext = context;
    }


    public static boolean isFirstLaunch(){
        return !(PreferenceManager.getDefaultSharedPreferences(appContext).contains("notFirstLaunch"));
    }

    public static boolean setFirstLaunch(){

        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(appContext).edit();
        editor.putBoolean("notFirstLaunch", true);

        return editor.commit();
    }


    public static boolean isLoggedIn()
    {

        return PreferenceManager.getDefaultSharedPreferences(appContext).getBoolean("isLoggedIn", false);

    }

    public static boolean setLoggedIn(boolean loggedIn)
    {

        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(appContext).edit();
        editor.putBoolean("isLoggedIn", loggedIn);

        return editor.commit();
    }

    public static String getLoggerEmail()
    {
        return PreferenceManager.getDefaultSharedPreferences(appContext).getString("loggerEmail", "abc@example.com");

    }

    public static boolean setLoggerEmail(String email)
    {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(appContext).edit();
        editor.putString("loggerEmail", email);
        return editor.commit();

    }

    public static String getLoggerPassword()
    {
        return PreferenceManager.getDefaultSharedPreferences(appContext).getString("loggerPassword", "abc@example.com");

    }

    public static boolean setLoggerPassword(String pass)
    {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(appContext).edit();
        editor.putString("loggerPassword", pass);
        return editor.commit();

    }


    public static String getLoggerName()
    {
        return PreferenceManager.getDefaultSharedPreferences(appContext).getString("loggerName", "");
    }

    public static boolean setLoggerName(String name)
    {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(appContext).edit();
        editor.putString("loggerName", name);
        return editor.commit();
    }


    public static String getLoggerLocation()
    {
        return PreferenceManager.getDefaultSharedPreferences(appContext).getString("loggerLocation", "");
    }

    public static boolean setLoggerLocation(String name)
    {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(appContext).edit();
        editor.putString("loggerLocation", name);
        return editor.commit();

    }


    public static String getSessionToken()
    {
        return PreferenceManager.getDefaultSharedPreferences(appContext).getString("SessionToken", "");
    }

    public static boolean setSessionToken(String name)
    {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(appContext).edit();
        editor.putString("SessionToken", name);
        return editor.commit();

    }



    public static String getCurrentCardId()
    {
        return PreferenceManager.getDefaultSharedPreferences(appContext).getString("CurrentCardId", "");
    }

    public static boolean setCurrentCardId(String name)
    {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(appContext).edit();
        editor.putString("CurrentCardId", name);
        return editor.commit();

    }










}